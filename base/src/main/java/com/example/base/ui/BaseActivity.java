package com.example.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.base.utils.CollectionUtils;
import com.example.injection.GlobalModuleRegistry;
import com.example.injection.Injector;
import com.example.injection.Module;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArgs(getIntent());

        setContentView(getLayoutResource());

        initViews();
        initData();
    }

    protected void initArgs(Intent intent) {
        List<Module> list = new ArrayList<>();
        list.addAll(GlobalModuleRegistry.getGlobalModuleRegistry().getGlobalModules());
        if (!CollectionUtils.isEmpty(getModules())) {
            list.addAll(getModules());
        }
        Injector injector = new Injector(list.toArray(new Module[0]));
        injector.inject(this);
    }

    public abstract int getLayoutResource();

    protected abstract void initViews();

    protected abstract void initData();

    public abstract List<? extends Module> getModules();
}