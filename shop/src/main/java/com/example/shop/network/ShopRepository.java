package com.example.shop.network;

import com.example.network.Request;
import com.example.network.wrapper.core.DataCallback;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.shop.model.Shop;
import com.example.shop.model.ShopDetail;

import java.util.List;

public class ShopRepository {
    private ShopApi shopApi;
    private NetworkWrapper networkWrapper;

    public ShopRepository(ShopApi shopApi, NetworkWrapper networkWrapper) {
        this.shopApi = shopApi;
        this.networkWrapper = networkWrapper;
    }

    public void getShopList(int page, int pageSize, DataCallback<List<Shop>> callback) {
        Request request = shopApi.getShopList(page, pageSize);
        networkWrapper.enqueue(request, callback);
    }

    public void getShopDetail(int id, DataCallback<ShopDetail> callback) {
        Request request = shopApi.getShopDetail(id);
        networkWrapper.enqueue(request, callback);
    }
}
