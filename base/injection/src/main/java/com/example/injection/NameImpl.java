package com.example.injection;

import java.lang.annotation.Annotation;

public class NameImpl implements Name {
    private String name;

    private NameImpl(String name) {
        this.name = name;
    }

    public static Name valueOf(String name) {
        return new NameImpl(name);
    }

    @Override
    public String value() {
        return name;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Name.class;
    }
}
