package com.example.base.utils;

import android.util.Log;

import com.example.base.BuildConfig;

public class LogUtils {
    public static boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "MyApp";

    public static void e(String tag, String msg) {
        Log.e(TAG, tag + ": " + msg);
    }

    public static void i(String tag, String msg) {
        Log.i(TAG, tag + ": " + msg);
    }

    public static void w(String tag, String msg) {
        Log.w(TAG, tag + ": " + msg);
    }

    public static void v(String tag, String msg) {
        Log.v(TAG, tag + ": " + msg);
    }

    public static void printException(String tag, String msg,  Throwable throwable) {
        Log.e(TAG, tag + ": " + msg, throwable);
    }
}
