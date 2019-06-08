package com.example.order.network;

import com.example.network.Request;
import com.example.network.wrapper.annotation.GET;
import com.example.network.wrapper.annotation.Query;

public interface OrderApi {
    @GET(path = "/HttpServer/shop/list")
    Request getShopList(@Query(name = "page") int page, @Query(name = "pageSize") int pageSize);

    @GET(path="/HttpServer/shop/detail")
    Request getShopDetail(@Query(name = "id") int id);
}
