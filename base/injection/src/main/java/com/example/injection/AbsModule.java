package com.example.injection;

public abstract class AbsModule implements Module {
    private Binder binder;

    public AbsModule() {
        this.binder = new Binder();
    }

    public BindingBuilder bind(Class<?> clazz) {
        BindingBuilder builder = new BindingBuilder(binder, clazz);
        return builder;
    }

    @Override
    public Binder getBinder() {
        return binder;
    }

}
