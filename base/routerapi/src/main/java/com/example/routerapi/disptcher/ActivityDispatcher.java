package com.example.routerapi.disptcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.routerapi.RouterCallback;
import com.example.routerapi.RouterRequest;

import static com.example.routerapi.utils.RouterUtils.notifyRouterFailure;
import static com.example.routerapi.utils.RouterUtils.notifyRouterSuccess;

public class ActivityDispatcher implements RouterDispatcher {
    @Override
    public Object dispatch(RouterRequest request) {
        Activity activity = request.getActivity();
        Context context = request.getContext();
        Fragment fragment = request.getFragment();

        Intent intent = new Intent();
        Bundle bundle = request.getParams();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        RouterCallback callback = request.getCallback();
        int requestCode = request.getRequestCode();
        if (activity != null) {
            intent.setClassName(activity.getPackageName(), request.getConfig().getClassName());
            if (requestCode > 0) {
                activity.startActivityForResult(intent, requestCode);
                notifyRouterSuccess(callback, request);
            } else {
                activity.startActivity(intent);
                notifyRouterSuccess(callback, request);
            }
        } else if (context != null) {
            intent.setClassName(context.getPackageName(), request.getConfig().getClassName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            notifyRouterSuccess(callback,request);
        } else if (fragment != null && fragment.isAdded()) {
            intent.setClassName(fragment.getActivity().getPackageName(), request.getConfig().getClassName());
            if (requestCode > 0) {
                fragment.startActivityForResult(intent, requestCode);
                notifyRouterSuccess(callback, request);
            } else {
                fragment.startActivity(intent);
                notifyRouterSuccess(callback, request);
            }
        } else {
            notifyRouterFailure(callback, new IllegalArgumentException("Arguments invalid"));
        }
        return null;
    }
}
