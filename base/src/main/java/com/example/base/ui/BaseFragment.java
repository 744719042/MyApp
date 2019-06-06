package com.example.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.injection.GlobalModuleRegistry;
import com.example.injection.Injector;
import com.example.injection.Module;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            initArgs(getArguments());
        }
    }

    protected void initArgs(Bundle arguments) {
        List<Module> list = new ArrayList<>();
        list.addAll(GlobalModuleRegistry.getGlobalModuleRegistry().getGlobalModules());
        list.addAll(getModules());
        Injector injector = new Injector(list.toArray(new Module[0]));
        injector.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void initViews(View view);

    public abstract int getLayoutResource();

    public abstract List<? extends Module> getModules();
}
