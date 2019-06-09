package com.example.comment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.BaseFragment;
import com.example.base.ui.PagedFragment;
import com.example.comment.CommentModule;
import com.example.comment.R;
import com.example.comment.adapter.CommentAdapter;
import com.example.comment.model.Comment;
import com.example.comment.network.CommentRepository;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.routerbase.RouterType;
import com.example.routerbase.annotation.Router;

import java.util.Arrays;
import java.util.List;

@Router(path = "/comment/list", type = RouterType.Fragment)
public class CommentListFragment extends BaseFragment {

    @Inject
    private CommentRepository commentRepository;
    private RecyclerView recyclerView;

    @Override
    protected void initData() {
        commentRepository.getCommentList(0, 10, new DataCallback<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {
                recyclerView.setAdapter(new CommentAdapter(comments, getContext()));
            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });
    }

    @Override
    protected void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.comment_list_fragment;
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new CommentModule());
    }
}
