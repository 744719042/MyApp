package com.example.base.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.base.R;
import com.example.base.paging.PageDelegate;
import com.example.base.paging.PageLoader;
import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.widget.PullRefreshView;

public abstract class PagedActivity<T> extends BaseActivity implements PageLoader, PullRefreshView.RefreshListener {
    private PageDelegate pageDelegate;
    private NPEDelegate npeDelegate;
    private BaseRecyclerAdapter<T> recyclerAdapter;
    private PullRefreshView pullRefreshView;
    private FrameLayout contentView;

    @Override
    public int getLayoutResource() {
        return R.layout.base_paged_layout;
    }

    @Override
    protected void initViews() {
        recyclerAdapter = createAdapter();
        pageDelegate = new PageDelegate(this, getLayoutManager(), recyclerAdapter, this);
        pullRefreshView = findViewById(R.id.activity_pull_refresh_root);
        pullRefreshView.setRefreshListener(this);
        pullRefreshView.addView(pageDelegate.getRecyclerView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        contentView = findViewById(R.id.activity_content);
        npeDelegate = new NPEDelegate(getErrorLayoutRes(), getEmptyLayoutRes(), getLoadingLayoutRes(), contentView);
    }

    protected int getLoadingLayoutRes() {
        return R.layout.base_loading_layout;
    }

    private int getErrorLayoutRes() {
        return R.layout.base_error_layout;
    }

    public int getEmptyLayoutRes() {
        return R.layout.base_empty_layout;
    }

    protected abstract BaseRecyclerAdapter<T> createAdapter();

    @Override
    protected void initData() {
        pageDelegate.refresh();
    }

    @Override
    public void onRefresh() {
        pageDelegate.refresh();
    }

    protected abstract RecyclerView.LayoutManager getLayoutManager();

}
