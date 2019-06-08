package com.example.myapp;

import android.view.View;

import com.example.base.ui.BaseFragment;
import com.example.injection.Module;

import java.util.List;

public class MineFragment extends BaseFragment {
    @Override
    protected void initData() {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public List<? extends Module> getModules() {
        return null;
    }
}
