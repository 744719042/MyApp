package com.example.base.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.base.R;
import com.example.base.paging.PageDelegate;
import com.example.base.paging.PageLoader;
import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerView;
import com.example.base.widget.PullRefreshView;

public abstract class PagedFragment extends BaseFragment implements PageLoader, PullRefreshView.RefreshListener {
    protected PageDelegate pageDelegate;
    protected BaseRecyclerAdapter recyclerAdapter;
    protected PullRefreshView pullRefreshView;
    protected NPEDelegate npeDelegate;
    protected FrameLayout contentView;
    protected BaseRecyclerView recyclerView;

    @Override
    protected void initData() {
        pageDelegate.refresh();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    protected void initViews(View view) {
        recyclerAdapter = createAdapter();
        pageDelegate = new PageDelegate(getActivity(), getLayoutManager(), recyclerAdapter, this);
        pullRefreshView = view.findViewById(R.id.activity_pull_refresh_root);
        pullRefreshView.setRefreshListener(this);
        recyclerView = pageDelegate.getRecyclerView();
        pullRefreshView.addView(pageDelegate.getRecyclerView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        contentView = view.findViewById(R.id.activity_content);
        npeDelegate = new NPEDelegate(getErrorLayoutRes(), getEmptyLayoutRes(), getLoadingLayoutRes(), contentView);
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract BaseRecyclerAdapter createAdapter();

    @Override
    public int getLayoutResource() {
        return R.layout.base_paged_layout;
    }

    protected int getLoadingLayoutRes() {
        return R.layout.base_loading_layout;
    }

    protected int getErrorLayoutRes() {
        return R.layout.base_error_layout;
    }

    protected int getEmptyLayoutRes() {
        return R.layout.base_empty_layout;
    }
}
