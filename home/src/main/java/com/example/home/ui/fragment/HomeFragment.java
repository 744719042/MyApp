package com.example.home.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.base.ui.PagedFragment;
import com.example.home.HomeModule;
import com.example.home.R;
import com.example.home.model.Card;
import com.example.home.model.HorizontalBanner;
import com.example.home.model.Shop;
import com.example.home.model.VerticalBanner;
import com.example.home.net.HomeRepository;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends PagedFragment {

    @Inject
    private HomeRepository homeRepository;

    @Override
    protected void initViews(View view) {
        super.initViews(view);

    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new BaseRecyclerAdapter<Shop>(new ArrayList<Shop>(), getContext(), R.layout.home_shop_item) {
            @Override
            protected void bindView(BaseRecyclerViewHolder holder, Shop shop, int position) {

            }
        };
    }

    @Override
    public void loadNext(int page, int pageSize) {
        homeRepository.getShopList(page, pageSize, new DataCallback<List<Shop>>() {
            @Override
            public void onSuccess(List<Shop> shops) {
                if (!CollectionUtils.isEmpty(shops)) {
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
    protected void initData() {
        super.initData();
        homeRepository.getHorizontalBanners(new DataCallback<List<HorizontalBanner>>() {
            @Override
            public void onSuccess(List<HorizontalBanner> horizontalBanners) {

            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });

        homeRepository.getVerticalBanners(new DataCallback<List<VerticalBanner>>() {
            @Override
            public void onSuccess(List<VerticalBanner> verticalBanners) {

            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });

        homeRepository.getCards(new DataCallback<List<Card>>() {
            @Override
            public void onSuccess(List<Card> cards) {

            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new HomeModule());
    }
}
