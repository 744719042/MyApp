package com.example.base.utils;

import android.content.res.Resources;
import android.util.TypedValue;

import com.example.base.BaseApplication;

public class UIUtils {
    private UIUtils() {

    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    private static int getStatusBarHeight() {
        try {
            int result = dp2px(24);
            int resourceId = BaseApplication.getContext()
                    .getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = BaseApplication.getContext()
                        .getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Throwable t) {
            return dp2px(24);
        }
    }

    private static int getNavigationBarHeight() {
        try {
            int result = 0;
            int resourceId = BaseApplication.getContext()
                    .getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = BaseApplication.getContext()
                        .getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        } catch (Throwable t) {
            return 0;
        }
    }
}
