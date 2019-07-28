package com.example.base.ui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.base.R;
import com.example.base.paging.PageDelegate;
import com.example.base.paging.PageLoader;
import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerView;
import com.example.base.widget.PullRefreshView;

public abstract class PagedActivity<T> extends BaseActivity implements PageLoader, PullRefreshView.RefreshListener {
    protected PageDelegate pageDelegate;
    protected NPEDelegate npeDelegate;
    protected BaseRecyclerAdapter<T> recyclerAdapter;
    protected PullRefreshView pullRefreshView;
    protected FrameLayout contentView;
    private BaseRecyclerView recyclerView;

    @Override
    public int getLayoutResource() {
        return R.layout.base_paged_layout;
    }

    @Override
    protected void initViews() {
        recyclerAdapter = createAdapter();
        pageDelegate = new PageDelegate(this, getLayoutManager(), this);
        pullRefreshView = findViewById(R.id.activity_pull_refresh_root);
        pullRefreshView.setRefreshListener(this);
        pullRefreshView.setContentView(pageDelegate.getRecyclerView());
        recyclerView = (BaseRecyclerView) pullRefreshView.getContentView();
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnScrollListener(pageDelegate);

        contentView = findViewById(R.id.activity_content);
        npeDelegate = new NPEDelegate(getErrorLayoutRes(), getEmptyLayoutRes(), getLoadingLayoutRes(), contentView);
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
