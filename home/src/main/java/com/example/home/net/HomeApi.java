package com.example.home.net;

import com.example.network.Request;
import com.example.network.wrapper.annotation.GET;
import com.example.network.wrapper.annotation.Query;

public interface HomeApi {

    @GET(path = "/HttpServer/home/horizontalbanners")
    Request getHorizontalBanners();

    @GET(path = "/HttpServer/home/verticalbanners")
    Request getVerticalBanners();

    @GET(path = "/HttpServer/home/cards")
    Request getCards();

    @GET(path = "/HttpServer/home/categories")
    Request getCategories();

    @GET(path = "/HttpServer/shop/list")
    Request getShopList(@Query(name = "page") int page, @Query(name="pageSize") int pageSize);
}
