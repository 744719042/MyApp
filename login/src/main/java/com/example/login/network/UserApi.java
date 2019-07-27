package com.example.login.network;

import com.example.network.Request;
import com.example.network.wrapper.annotation.Field;
import com.example.network.wrapper.annotation.HTTPS;
import com.example.network.wrapper.annotation.POST;

public interface UserApi {
    @POST(path = "/HttpServer/login")
    @HTTPS
    Request login(@Field(name = "name") String userName, @Field(name = "password") String password);
}
