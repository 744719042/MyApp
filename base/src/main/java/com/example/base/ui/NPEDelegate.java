package com.example.base.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NPEDelegate {
    private int errorLayoutRes;
    private int emptyLayoutRes;
    private int loadingLayoutRes;
    private ViewGroup parent;

    private ViewGroup errorLayout;
    private ViewGroup emptyLayout;
    private ViewGroup loadingLayout;
    private LayoutInflater layoutInflater;

    public NPEDelegate(int errorLayout, int emptyLayout, int loadingLayout, ViewGroup parent) {
        this.errorLayoutRes = errorLayout;
        this.emptyLayoutRes = emptyLayout;
        this.loadingLayoutRes = loadingLayout;
        this.parent = parent;
        this.layoutInflater = LayoutInflater.from(parent.getContext());
    }

    public void setErrorLayoutRes(int errorLayoutRes) {
        this.errorLayoutRes = errorLayoutRes;
        if (errorLayout != null) {
            parent.removeView(errorLayout);
            errorLayout = null;
        }
    }

    public void setEmptyLayoutRes(int emptyLayoutRes) {
        this.emptyLayoutRes = emptyLayoutRes;
        if (emptyLayout != null) {
            parent.removeView(emptyLayout);
            emptyLayout = null;
        }
    }

    public void setLoadingLayoutRes(int loadingLayoutRes) {
        this.loadingLayoutRes = loadingLayoutRes;
        if (loadingLayout != null) {
            parent.removeView(loadingLayout);
            loadingLayout = null;
        }
    }

    public void showError() {
        if (errorLayout == null) {
            errorLayout = (ViewGroup) layoutInflater.inflate(errorLayoutRes, parent, false);
            parent.addView(emptyLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        errorLayout.setVisibility(View.VISIBLE);
        hideEmpty();
        hideLoading();
    }

    public void hideError() {
        if (errorLayout != null) {
            errorLayout.setVisibility(View.GONE);
        }
    }

    public void showEmpty() {
        if (emptyLayout == null) {
            emptyLayout = (ViewGroup) layoutInflater.inflate(emptyLayoutRes, parent, false);
            parent.addView(emptyLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        emptyLayout.setVisibility(View.VISIBLE);
        hideError();
        hideLoading();
    }

    public void hideEmpty() {
        if (emptyLayout != null) {
            emptyLayout.setVisibility(View.GONE);
        }
    }

    public void showLoading() {
        if (loadingLayout == null) {
            loadingLayout = (ViewGroup) layoutInflater.inflate(loadingLayoutRes, parent, false);
            parent.addView(loadingLayout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        loadingLayout.setVisibility(View.VISIBLE);
        hideError();
        hideEmpty();
    }

    public void hideLoading() {
        if (loadingLayout != null) {
            loadingLayout.setVisibility(View.GONE);
        }
    }
}
