package com.example.imagefetcher;

import android.net.Uri;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.Map;

public class LoadInfo {
    private int resourceId;
    private Uri uri;
    private ImageView imageView;
    private BitmapLoadListener loadListener;
    private int error;
    private int placeholder;
    private int targetWidth;
    private int targetHeight;
    private volatile boolean cancel = false;

    // 标识一次加载
    private String key;
    private Object tag;

    private ImageFetcher imageFetcher;

    public LoadInfo(Builder builder) {
        this.resourceId = builder.resId;
        this.error = builder.error;
        this.imageView = builder.imageView;
        this.loadListener = builder.loadListener;
        this.placeholder = builder.placeHolder;
        this.uri = builder.uri;
        this.tag = builder.tag;
        this.imageFetcher = builder.imageFetcher;
    }

    public int getResourceId() {
        return resourceId;
    }

    public Uri getUri() {
        return uri;
    }

    public int getError() {
        return error;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public BitmapLoadListener getLoadListener() {
        return loadListener;
    }

    public ImageFetcher getImageFetcher() {
        return imageFetcher;
    }

    public String getKey() {
        if (TextUtils.isEmpty(key)) {
            StringBuilder builder = new StringBuilder();
            if (resourceId > 0) {
                builder.append(resourceId);
            } else {
                builder.append(uri.toString());
            }

            builder.append(":").append(targetWidth);
            builder.append(":").append(targetHeight);
            key = builder.toString();
        }
        return key;
    }

    public Object getTag() {
        return tag;
    }

    public void cancel() {
        cancel = true;
    }

    public boolean isCancel() {
        return cancel;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    private void setTargetSize(int width, int height) {
        this.targetWidth = width;
        this.targetHeight = height;
    }

    @Override
    public String toString() {
        return "LoadInfo{" +
                "resourceId=" + resourceId +
                ", uri=" + uri +
                ", imageView=" + imageView +
                ", loadListener=" + loadListener +
                ", error=" + error +
                ", placeholder=" + placeholder +
                ", targetWidth=" + targetWidth +
                ", targetHeight=" + targetHeight +
                ", cancel=" + cancel +
                ", key='" + key + '\'' +
                ", tag=" + tag +
                ", imageFetcher=" + imageFetcher +
                '}';
    }

    public static class Builder {
        private ImageView imageView;
        private BitmapLoadListener loadListener;
        private int placeHolder;
        private int error;
        private Uri uri;
        private int resId;
        private Object tag;
        private Dispatcher dispatcher;
        private ImageFetcher imageFetcher;

        public Builder(Dispatcher dispatcher, ImageFetcher imageFetcher) {
            this.dispatcher = dispatcher;
            this.imageFetcher = imageFetcher;
        }

        public Builder url(String url){
            uri = Uri.parse(url);
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder error(int error) {
            this.error = error;
            return this;
        }

        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        public void into(final ImageView imageView) {
            this.imageView = imageView;
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            if (params != null) {
                int width = params.width;
                int height = params.height;

                if (width > 0 && height > 0) {
                    LoadInfo loadInfo = new LoadInfo(this);
                    loadInfo.setTargetSize(width, height);
                    dispatcher.submit(loadInfo);
                    return;
                }

                width = imageView.getWidth();
                height = imageView.getHeight();
                if (width > 0 && height > 0) {
                    LoadInfo loadInfo = new LoadInfo(this);
                    loadInfo.setTargetSize(width, height);
                    dispatcher.submit(loadInfo);
                    return;
                }
            }

            final Map<ImageView, ViewTreeObserver.OnPreDrawListener> map = imageFetcher.getViewOnPreDrawListenerMap();
            ViewTreeObserver.OnPreDrawListener layoutListener = map.remove(imageView);
            if (layoutListener != null) {
                imageView.getViewTreeObserver().removeOnPreDrawListener(layoutListener);
            }
            layoutListener = new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    int width = imageView.getWidth();
                    int height = imageView.getHeight();
                    if (width > 0 && height > 0) {
                        LoadInfo loadInfo = new LoadInfo(Builder.this);
                        loadInfo.setTargetSize(width, height);
                        dispatcher.submit(loadInfo);
                    }
                    ViewTreeObserver.OnPreDrawListener listener = map.remove(imageView);
                    if (listener != null && listener != this) {
                        map.put(imageView, listener);
                    }
                    imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return false;
                }
            };
            map.put(imageView, layoutListener);
            imageView.getViewTreeObserver().addOnPreDrawListener(layoutListener);
        }

        public void into(BitmapLoadListener loadListener) {
            this.loadListener = loadListener;
            LoadInfo loadInfo = new LoadInfo(this);
            dispatcher.submit(loadInfo);
        }
    }
}
