package com.example.provider;

import com.example.network.HttpClient;
import com.example.network.wrapper.core.NetworkWrapper;

public class NetworkProvider {

    private NetworkWrapper networkWrapper;
    private HttpClient httpClient;
    private static final String HOST = "192.168.137.224";

    public NetworkProvider() {
        httpClient = new HttpClient.Builder()
                .connectTimeout(3000)
                .readTimeout(3000)
                .build();
        networkWrapper = new NetworkWrapper(HOST, httpClient, GsonProvider.getInstance().getGson());
    }

    public NetworkWrapper getNetworkService() {
        return networkWrapper;
    }

    public static NetworkProvider getInstance() {
        return NetworkProviderHolder.INSTANCE;
    }

    private static class NetworkProviderHolder {
        private static final NetworkProvider INSTANCE = new NetworkProvider();
    }
}
