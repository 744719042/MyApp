package com.example.provider;

import com.example.injection.GlobalModuleRegistry;
import com.example.injection.Injector;
import com.example.injection.Module;

import java.util.List;

public class GlobalInjectorProvider {

    private Injector injector;

    private GlobalInjectorProvider() {
        List<Module> moduleList = GlobalModuleRegistry.getGlobalModuleRegistry().getGlobalModules();
        injector = new Injector(moduleList.toArray(new Module[0]));
    }

    public Injector getGlobalInjector() {
        return injector;
    }

    public static GlobalInjectorProvider getInstance() {
        return GlobalInjectorProviderHolder.INSTANCE;
    }

    private static class GlobalInjectorProviderHolder {
        private static final GlobalInjectorProvider INSTANCE = new GlobalInjectorProvider();
    }
}
