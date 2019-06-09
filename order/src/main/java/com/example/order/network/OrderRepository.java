package com.example.order.network;

import com.example.network.Request;
import com.example.network.wrapper.core.DataCallback;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.order.model.Order;

import java.util.List;

public class OrderRepository {
    private OrderApi orderApi;
    private NetworkWrapper networkWrapper;

    public OrderRepository(OrderApi orderApi, NetworkWrapper networkWrapper) {
        this.orderApi = orderApi;
        this.networkWrapper = networkWrapper;
    }

    public void getShopList(int page, int pageSize, DataCallback<List<Order>> callback) {
        Request request = orderApi.getOrderList(page, pageSize);
        networkWrapper.enqueue(request, callback);
    }
}
