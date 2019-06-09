package com.example.shop.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.PagedFragment;
import com.example.base.utils.UIUtils;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.provider.constant.RouterConstant;
import com.example.routerapi.RouterManager;
import com.example.shop.ShopModule;
import com.example.shop.adapter.ShopAdapter;
import com.example.shop.model.Shop;
import com.example.shop.network.ShopRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopListFragment extends PagedFragment {

    @Inject
    private ShopRepository shopRepository;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        view.setPadding(0, UIUtils.getStatusBarHeight(), 0, 0);
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new ShopAdapter(new ArrayList<Shop>(), getContext());
    }

    @Override
    protected void initData() {
        super.initData();
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {
                RouterManager.getInstance().with(RouterConstant.Shop.SHOP_DETAIL).withActivity(getActivity()).navigate();
            }
        });
    }

    @Override
    public void loadNext(final int page, int pageSize) {
        shopRepository.getShopList(page, pageSize, new DataCallback<List<Shop>>() {
            @Override
            public void onSuccess(List<Shop> shops) {
                if (!CollectionUtils.isEmpty(shops)) {
                    if (page == 1) {
                        recyclerAdapter.resetData(shops);
                    } else {
                        recyclerAdapter.appendData(shops);
                    }
                    pullRefreshView.notifyRefreshComplete();
                    pageDelegate.onLoaded(-1, shops.size());
                } else {
                    pageDelegate.onError();
                }
            }

            @Override
            public void onFailure(int code, MyNetException e) {
                pageDelegate.onError();
            }
        });
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new ShopModule());
    }
}
