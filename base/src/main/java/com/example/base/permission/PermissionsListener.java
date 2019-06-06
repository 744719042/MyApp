package com.example.base.permission;

public interface PermissionsListener {
    void onPermissionResult(boolean grantedAll, int[] grantResult);
}
