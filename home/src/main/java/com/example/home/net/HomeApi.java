package com.example.home.net;

import com.example.network.Request;
import com.example.network.wrapper.annotation.GET;

public interface HomeApi {

    @GET(path = "/home/horizontalbanners")
    Request getHorizontalBanners();

    @GET(path = "/home/verticalbanners")
    Request getVerticalBanners();

    @GET(path = "/home/cards")
    Request getCards();

    @GET(path = "/shop/list")
    Request getShopList(int page, int pageSize);
}
