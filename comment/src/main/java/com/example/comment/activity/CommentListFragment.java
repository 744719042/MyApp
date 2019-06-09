package com.example.comment.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.PagedFragment;
import com.example.comment.CommentModule;
import com.example.comment.adapter.CommentAdapter;
import com.example.comment.model.Comment;
import com.example.comment.network.CommentRepository;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.routerbase.annotation.Router;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Router(path = "/comment/list")
public class CommentListFragment extends PagedFragment {

    @Inject
    private CommentRepository commentRepository;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new CommentAdapter(new ArrayList<Comment>(), getContext());
    }

    @Override
    public void loadNext(final int page, int pageSize) {
        commentRepository.getCommentList(page, pageSize, new DataCallback<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> shops) {
                if (!CollectionUtils.isEmpty(shops)) {
                    if (page == 1) {
                        recyclerAdapter.resetData(shops);
                    } else {
                        recyclerAdapter.appendData(shops);
                    }
                    pageDelegate.onLoaded(-1, shops.size());
                } else {
                    pageDelegate.onError();
                }
                pullRefreshView.notifyRefreshComplete();
            }

            @Override
            public void onFailure(int code, MyNetException e) {
                pullRefreshView.notifyRefreshComplete();
                pageDelegate.onError();
            }
        });
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new CommentModule());
    }
}
