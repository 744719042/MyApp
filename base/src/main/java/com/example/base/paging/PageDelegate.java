package com.example.base.paging;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.base.recycler.BaseRecyclerView;

public class PageDelegate extends RecyclerView.OnScrollListener {
    private PageIterator pageIterator;
    private RecyclerView recyclerView;
    private boolean mLoading = false;

    public PageDelegate(Activity activity, RecyclerView.LayoutManager layoutManager, PageLoader pageLoader) {
        this.pageIterator = new PageIterator(pageLoader);
        this.recyclerView = new BaseRecyclerView(activity);
        this.recyclerView.addOnScrollListener(this);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (shouldLoadMore() && !mLoading) {
                mLoading = true;
                pageIterator.next();
            }
        }
    }

    public void refresh() {
        if (!mLoading) {
            pageIterator.reset();
            mLoading = true;
            pageIterator.next();
        }
    }

    public void onLoaded(int total, int lastCount) {
        mLoading = false;
        pageIterator.onLoaded(total, lastCount);
    }

    public void onError() {
        mLoading = false;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    private boolean shouldLoadMore() {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        final int lastItemPosition = manager.getItemCount() - 1;
        if (manager instanceof LinearLayoutManager) {
            int lastVisiblePosition = ((LinearLayoutManager) manager).findLastCompletelyVisibleItemPosition();
            return lastVisiblePosition >= lastItemPosition;
        } else if (manager instanceof StaggeredGridLayoutManager) {
            int[] lastRow = ((StaggeredGridLayoutManager) manager).findLastCompletelyVisibleItemPositions(null);
            for (int pos : lastRow) {
                if (pos == lastItemPosition) return true;
            }

        }
        return false;
    }
}
