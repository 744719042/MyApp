package com.example.base.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import static com.example.base.permission.PermissionActivity.ARG_PERMISSIONS;
import static com.example.base.permission.PermissionUtils.findActivity;

public class PermissionManager {
    private PermissionManager() {

    }

    private WeakReference<PermissionsListener> mPermissionsListener;

    public void onRequestPermissionsResult(int[] result) {
        if (mPermissionsListener != null && mPermissionsListener.get() != null) {
            boolean grantedAll = true;
            if (result != null) {
                for (int i = 0; i < result.length; i++) {
                    if (result[i] != PackageManager.PERMISSION_GRANTED) {
                        grantedAll = false;
                        break;
                    }
                }
            }
            mPermissionsListener.get().onPermissionResult(grantedAll, result);
            mPermissionsListener.clear();
            mPermissionsListener = null;
        }
    }

    private static class PermissionManagerHolder {
        private static final PermissionManager INSTANCE = new PermissionManager();
    }

    public static PermissionManager getInstance() {
        return PermissionManagerHolder.INSTANCE;
    }

    public boolean hasPermission(Context context, String permission) {
        return hasPermission(context, new String[] { permission });
    }

    public boolean hasPermission(Context context, String[] permissions) {
        if (PermissionUtils.isPermissionSupported()) {
            for (String permission : permissions) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public void requestPermission(Context context, String permission, PermissionsListener listener) {
        requestPermissions(context, new String[] { permission }, listener);
    }

    public void requestPermissions(Context context, String[] permissions, PermissionsListener listener) {
        if (listener == null || permissions == null || permissions.length == 0) {
            return;
        }

        if (PermissionUtils.handleLowerVersions(permissions, listener)) {
            return;
        }

        if (context == null) {
            listener.onPermissionResult(false,null);
            return;
        }

        if (PermissionUtils.hasPermission(context, permissions, listener)) {
            return;
        }

        mPermissionsListener = new WeakReference<>(listener);
        Activity activity = findActivity(context);
        Intent intent = null;
        if (activity == null) {
            intent = new Intent(context.getApplicationContext(), PermissionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context = context.getApplicationContext();
        } else {
            intent = new Intent(activity, PermissionActivity.class);
        }
        Bundle bundle = new Bundle();
        bundle.putStringArray(ARG_PERMISSIONS, permissions);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
