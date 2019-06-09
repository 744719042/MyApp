package com.example.provider;

import android.util.Log;
import android.widget.Toast;

import com.example.base.BaseApplication;
import com.example.routerapi.ErrorHandler;
import com.example.routerbase.RouterType;
import com.example.routerbase.annotation.Router;

@Router(path = "/provider/error", type = RouterType.Service)
public class GlobalErrorHandler implements ErrorHandler {
    private static final String TAG = "GlobalErrorHandler";
    @Override
    public void onError(int code) {
        Toast.makeText(BaseApplication.getContext(), "没有发现跳转到的路径配置", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "No Router Config found");
    }
}
