package com.example.login.network;

import com.example.network.Request;
import com.example.network.wrapper.annotation.Field;
import com.example.network.wrapper.annotation.POST;

public interface UserApi {
    @POST(path = "/HttpServer/login")
    Request login(@Field(name = "username") String userName, @Field(name = "password") String password);
}
