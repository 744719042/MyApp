package com.example.myapp;

import android.os.Bundle;

import com.example.base.permission.PermissionManager;
import com.example.base.ui.BaseActivity;
import com.example.home.ui.fragment.HomeFragment;
import com.example.injection.Module;
import com.example.routerbase.annotation.Router;

import java.util.List;

@Router(path="/app/index")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_layout, new HomeFragment())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public List<? extends Module> getModules() {
        return null;
    }
}
