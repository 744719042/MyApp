package com.example.home.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.PagedFragment;
import com.example.base.widget.HorizontalBannerView;
import com.example.base.widget.VerticalBannerView;
import com.example.base.widget.custom.AbNormalLayout;
import com.example.home.HomeModule;
import com.example.home.R;
import com.example.home.adapter.HorizontalBannerAdapter;
import com.example.home.adapter.ShopAdapter;
import com.example.home.adapter.VerticalBannerAdapter;
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
    private ViewGroup headerView;
    private HorizontalBannerView horizontalBannerView;
    private RecyclerView gridLayout;
    private VerticalBannerView verticalBannerView;
    private AbNormalLayout abNormalLayout;
    private ImageView bannnerView;

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        headerView = (ViewGroup) inflater.inflate(R.layout.home_header_view, recyclerView, false);
        recyclerView.addHeaderView(headerView);
        horizontalBannerView = headerView.findViewById(R.id.horizontal_banner_view);
        gridLayout = headerView.findViewById(R.id.home_grid_layout);
        verticalBannerView = headerView.findViewById(R.id.vertical_banner_view);
        abNormalLayout = headerView.findViewById(R.id.home_abnormal_layout);
        bannnerView = headerView.findViewById(R.id.banner_view);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new ShopAdapter(new ArrayList<Shop>(), getContext());
    }

    @Override
    public void loadNext(int page, int pageSize) {
        homeRepository.getShopList(page, pageSize, new DataCallback<List<Shop>>() {
            @Override
            public void onSuccess(List<Shop> shops) {
                if (!CollectionUtils.isEmpty(shops)) {
                    recyclerAdapter.appendData(shops);
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
                if (!CollectionUtils.isEmpty(horizontalBanners)) {
                    horizontalBannerView.setAdapter(new HorizontalBannerAdapter(horizontalBanners, getContext()));
                    horizontalBannerView.setVisibility(View.VISIBLE);
                } else {
                    horizontalBannerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(int code, MyNetException e) {
                horizontalBannerView.setVisibility(View.GONE);
            }
        });

        homeRepository.getVerticalBanners(new DataCallback<List<VerticalBanner>>() {
            @Override
            public void onSuccess(List<VerticalBanner> verticalBanners) {
                if (!CollectionUtils.isEmpty(verticalBanners)) {
                    verticalBannerView.setVisibility(View.VISIBLE);
                    verticalBannerView.setAdapter(new VerticalBannerAdapter(getContext(), verticalBanners));
                } else {
                    verticalBannerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(int code, MyNetException e) {
                verticalBannerView.setVisibility(View.GONE);
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
