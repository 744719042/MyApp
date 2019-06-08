package com.example.order.network;

import com.example.network.Request;
import com.example.network.wrapper.core.DataCallback;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.order.model.Order;
import com.example.order.model.OrderDetail;

import java.util.List;

public class OrderRepository {
    private OrderApi shopApi;
    private NetworkWrapper networkWrapper;

    public OrderRepository(OrderApi shopApi, NetworkWrapper networkWrapper) {
        this.shopApi = shopApi;
        this.networkWrapper = networkWrapper;
    }

    public void getShopList(int page, int pageSize, DataCallback<List<Order>> callback) {
        Request request = shopApi.getShopList(page, pageSize);
        networkWrapper.enqueue(request, callback);
    }

    public void getShopDetail(int id, DataCallback<OrderDetail> callback) {
        Request request = shopApi.getShopDetail(id);
        networkWrapper.enqueue(request, callback);
    }
}
