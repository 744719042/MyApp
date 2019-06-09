package com.example.comment.network;

import com.example.comment.model.Comment;
import com.example.network.Request;
import com.example.network.wrapper.core.DataCallback;
import com.example.network.wrapper.core.NetworkWrapper;

import java.util.List;

public class CommentRepository {
    private CommentApi commentApi;
    private NetworkWrapper networkWrapper;

    public CommentRepository(CommentApi commentApi, NetworkWrapper networkWrapper) {
        this.commentApi = commentApi;
        this.networkWrapper = networkWrapper;
    }

    public void getCommentList(int page, int pageSize, DataCallback<List<Comment>> callback) {
        Request request = commentApi.getCommentList(page, pageSize);
        networkWrapper.enqueue(request, callback);
    }
}
