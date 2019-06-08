package com.example.shop.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.PagedFragment;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
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
    protected BaseRecyclerAdapter createAdapter() {
        return new ShopAdapter(new ArrayList<Shop>(), getContext());
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
