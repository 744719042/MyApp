package com.example.injection;

import android.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class BindingBuilder {
    private Binder binder;
    private Class<?> keyClass;
    private Name name;
    private Class<?> targetClass;
    private Object targetObject;
    private boolean isEager;
    private boolean isSingleton;
    private Class<? extends Provider> provider;

    public BindingBuilder(Binder binder, Class<?> keyClass) {
        this.binder = binder;
        this.keyClass = keyClass;
    }

    public BindingBuilder eager() {
        isEager = true;
        return this;
    }

    public BindingBuilder singleton() {
        isSingleton = true;
        return this;
    }

    private Pair<Key, Binding> build() {
        Key key = new Key(keyClass, name);
        Binding binding = new Binding();
        binding.setEager(isEager);
        binding.setSingleton(isSingleton);
        binding.setProvider(provider);
        binding.setTargetClass(targetClass);
        binding.setTargetObject(targetObject);

        return new Pair<>(key, binding);
    }

    public BindingBuilder annotatedWith(Name name) {
        this.name = name;
        return this;
    }

    public void to(Class<?> clazz) {
        this.targetClass = clazz;
        Pair<Key, Binding> pair = build();
        binder.addBinding(pair.first, pair.second);
    }

    public void toInstance(Object object) {
        targetObject = object;
        Pair<Key, Binding> pair = build();
        binder.addBinding(pair.first, pair.second);
    }

    public void toProvider(Class<? extends Provider> provider) {
        if (provider.isAnnotationPresent(Singleton.class)) {
            singleton();
        }
        if (provider.isAnnotationPresent(Eager.class)) {
            eager();
        }

        Constructor<?> constructors[] = provider.getConstructors();
        List<Key> keyList = new ArrayList<>();
        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                Annotation[][] annotations = constructor.getParameterAnnotations();
                Class<?> paramTypes[] = constructor.getParameterTypes();
                if (paramTypes != null && paramTypes.length > 0) {
                    if (annotations != null && annotations.length > 0) {
                        for (int i = 0; i < annotations.length; i++) {
                            Annotation[] paramAnnotations = annotations[i];
                            Name name = null;
                            if (paramAnnotations != null && paramAnnotations.length > 0) {
                                for (Annotation annotation : paramAnnotations) {
                                    if (annotation instanceof Name) {
                                        name = (Name) annotation;
                                        break;
                                    }
                                }
                            }
                            Key key = new Key(paramTypes[i], name);
                            keyList.add(key);
                        }
                    }
                }

                break;
            }
        }
        this.provider = provider;
        Pair<Key, Binding> pair = build();
        pair.second.setDependencies(keyList);
        binder.addBinding(pair.first, pair.second);
    }
}
