package com.example.provider;

import com.example.base.BaseApplication;
import com.example.imagefetcher.ImageFetcher;

public class ImageFetcherProvider {
    private ImageFetcher imageFetcher;

    public ImageFetcherProvider() {
        this.imageFetcher = new ImageFetcher.Builder()
                .client(NetworkProvider.getInstance().getNetworkService().getHttpClient())
                .context(BaseApplication.getContext())
                .memMaxSize(10 * 1024 * 1024)
                .diskMaxSize(50 * 1024 * 1024)
                .build();
    }

    public static ImageFetcherProvider getInstance() {
        return ImageFetcherProviderHolder.INSTANCE;
    }

    public ImageFetcher getImageFetcher() {
        return imageFetcher;
    }

    private static class ImageFetcherProviderHolder {
        private static final ImageFetcherProvider INSTANCE = new ImageFetcherProvider();
    }
}
