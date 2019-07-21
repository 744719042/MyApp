package com.example.myapp;

import com.example.base.BaseApplication;
import com.example.provider.AccountProvider;
import com.example.provider.manager.AccountManager;
import com.example.routerapi.RouterManager;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RouterManager.getInstance().init(getApplicationContext());
        AccountManager accountManager = (AccountManager) RouterManager.getInstance().with("/login/manager").navigate();
        AccountProvider.getInstance().initAccountMananger(accountManager);
    }
}
