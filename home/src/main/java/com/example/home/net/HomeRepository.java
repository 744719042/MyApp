package com.example.home.net;

import com.example.home.model.Card;
import com.example.home.model.HorizontalBanner;
import com.example.home.model.Shop;
import com.example.home.model.VerticalBanner;
import com.example.network.Request;
import com.example.network.wrapper.core.DataCallback;
import com.example.network.wrapper.core.NetworkWrapper;

import java.util.List;

public class HomeRepository {
    private HomeApi homeApi;
    private NetworkWrapper networkWrapper;

    public HomeRepository(HomeApi homeApi, NetworkWrapper networkWrapper) {
        this.networkWrapper = networkWrapper;
        this.homeApi = homeApi;
    }

    public void getHorizontalBanners(DataCallback<List<HorizontalBanner>> dataCallback) {
        Request request = homeApi.getHorizontalBanners();
        networkWrapper.enqueue(request, dataCallback);
    }

    public void getVerticalBanners(DataCallback<List<VerticalBanner>> dataCallback) {
        Request request = homeApi.getVerticalBanners();
        networkWrapper.enqueue(request, dataCallback);
    }

    public void getCards(DataCallback<List<Card>> dataCallback) {
        Request request = homeApi.getCards();
        networkWrapper.enqueue(request, dataCallback);
    }

    public void getShopList(int page, int pageSize, DataCallback<List<Shop>> dataCallback) {
        Request request = homeApi.getShopList(page, pageSize);
        networkWrapper.enqueue(request, dataCallback);
    }
}
