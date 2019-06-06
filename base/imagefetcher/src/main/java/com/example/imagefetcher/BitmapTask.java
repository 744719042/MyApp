package com.example.imagefetcher;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.imagefetcher.loader.BitmapLoader;
import com.example.imagefetcher.loader.LoaderHelper;
import com.example.imagefetcher.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class BitmapTask implements Runnable {
    private static final String TAG = "BitmapTask";
    Future<?> future;
    LoadInfo loadInfo;
    List<LoadInfo> otherLoadInfo;
    private Dispatcher dispatcher;
    public volatile Bitmap result;

    public BitmapTask(LoadInfo loadInfo, Dispatcher dispatcher) {
        this.loadInfo = loadInfo;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        BitmapLoader bitmapLoader = LoaderHelper.findLoader(loadInfo);
        Log.e(TAG, "bitmaploader = " + bitmapLoader);

        if (bitmapLoader != null) {
            try {
                result = bitmapLoader.load(loadInfo);
                Log.e(TAG, "result = " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (result != null) {
            dispatcher.complete(this);
            Log.e(TAG, "complete = " + result);
        } else {
            dispatcher.fail(this);
            Log.e(TAG, "fail = " + result);
        }
    }

    public boolean cancel() {
        if (loadInfo != null) {
            return false;
        }

        if (!CollectionUtils.isEmpty(otherLoadInfo)) {
            return false;
        }

        return future.cancel(true);
    }

    public boolean isCancelled() {
        return future.isCancelled();
    }

    public void addInfo(LoadInfo info) {
        if (otherLoadInfo == null) {
            otherLoadInfo = new ArrayList<>();
        }
        otherLoadInfo.add(info);
    }

    public void removeInfo(LoadInfo info) {
        if (otherLoadInfo != null) {
            otherLoadInfo.remove(info);
        }
    }
}
