package com.example.home;

import com.example.injection.AbsModule;
import com.example.injection.GlobalModuleRegistry;
import com.example.injection.NameImpl;
import com.example.provider.delegate.ApplicationDelegate;

public class HomeGlobalModule extends AbsModule {
    static {
        GlobalModuleRegistry.getGlobalModuleRegistry().registerGlobalModule(new HomeGlobalModule());
    }

    @Override
    public void configure() {
        bind(ApplicationDelegate.class).annotatedWith(NameImpl.valueOf("home")).to(HomeGlobalModule.class);
    }
}
