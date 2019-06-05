package com.example.myapp;

import com.example.base.BaseApplication;
import com.example.injection.Injector;
import com.example.provider.GlobalInjectorProvider;
import com.example.routerapi.RouterManager;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RouterManager.getInstance().init(getApplicationContext());
        Injector injector = GlobalInjectorProvider.getInstance().getGlobalInjector();



    }
}
