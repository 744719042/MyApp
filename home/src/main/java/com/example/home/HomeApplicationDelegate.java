package com.example.home;

import android.content.Context;

import com.example.base.utils.LogUtils;
import com.example.provider.delegate.ApplicationDelegate;

public class HomeApplicationDelegate implements ApplicationDelegate {
    private static final String TAG = "HomeApplicationDelegate";

    @Override
    public void init(Context context) {
        LogUtils.i(TAG, "init home application");
    }
}
