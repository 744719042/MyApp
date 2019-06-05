package com.example.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            initArgs(getIntent());
        }

        setContentView(getLayoutResource());

        initViews();
        initData();
    }

    protected abstract void initArgs(Intent intent);

    public abstract int getLayoutResource();

    protected abstract void initViews();

    protected abstract void initData();

}