package com.example.comment.network;

import com.example.network.Request;
import com.example.network.wrapper.annotation.GET;
import com.example.network.wrapper.annotation.Query;

public interface CommentApi {
    @GET(path = "/HttpServer/comment/list")
    Request getCommentList(@Query(name = "page") int page, @Query(name = "pageSize") int pageSize);
}
