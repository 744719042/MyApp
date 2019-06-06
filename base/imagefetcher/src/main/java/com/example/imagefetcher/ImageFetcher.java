package com.example.imagefetcher;

import android.content.Context;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.network.HttpClient;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageFetcher {
    private ImageCache imageCache;
    private Context context;
    private ExecutorService executor;
    private HttpClient httpClient;
    private Dispatcher dispatcher;
    private Map<ImageView, ViewTreeObserver.OnPreDrawListener> viewOnPreDrawListenerMap = new ConcurrentHashMap<>();

    private ImageFetcher(Builder builder) {
        this.context = builder.context;
        File diskCacheDir = builder.diskCacheDir;
        if (diskCacheDir == null) {
            diskCacheDir = new File(context.getCacheDir() + "/imagefetcher/cache");
        }
        executor = Executors.newFixedThreadPool(5);
        imageCache = new ImageCache(builder.memMaxSize, builder.diskMaxSize, diskCacheDir, executor);
        httpClient = builder.httpClient;
        dispatcher = new Dispatcher(this);
    }

    public ImageCache getImageCache() {
        return imageCache;
    }

    public Context getContext() {
        return context;
    }

    public ExecutorService getExecutorService() {
        return executor;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public Map<ImageView, ViewTreeObserver.OnPreDrawListener> getViewOnPreDrawListenerMap() {
        return viewOnPreDrawListenerMap;
    }

    public LoadInfo.Builder load(String url) {
        return new LoadInfo.Builder(dispatcher, this).url(url);
    }

    public static class Builder {
        private int memMaxSize;
        private long diskMaxSize;
        private File diskCacheDir;
        private Context context;
        private HttpClient httpClient;

        public Builder() {

        }

        public Builder memMaxSize(int memMaxSize) {
            this.memMaxSize = memMaxSize;
            return this;
        }

        public Builder diskMaxSize(long diskMaxSize) {
            this.diskMaxSize = diskMaxSize;
            return this;
        }

        public Builder diskCacheDir(File diskCacheDir) {
            this.diskCacheDir = diskCacheDir;
            return this;
        }

        public Builder context(Context context) {
            this.context = context.getApplicationContext();
            return this;
        }

        public Builder client(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public ImageFetcher build() {
            return new ImageFetcher(this);
        }
    }
}
