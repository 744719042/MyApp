package com.example.order.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.PagedActivity;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.order.OrderModule;
import com.example.order.adapter.OrderAdapter;
import com.example.order.model.Order;
import com.example.order.network.OrderRepository;
import com.example.routerbase.annotation.Router;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Router(path = "/order/list")
public class OrderListActivity extends PagedActivity<Order> {

    @Inject
    private OrderRepository orderRepository;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new OrderAdapter(new ArrayList<Order>(), this);
    }

    @Override
    public void loadNext(final int page, int pageSize) {
        orderRepository.getShopList(page, pageSize, new DataCallback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> shops) {
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
                pullRefreshView.notifyRefreshComplete();
            }

            @Override
            public void onFailure(int code, MyNetException e) {
                pullRefreshView.notifyRefreshComplete();
                pageDelegate.onError();
            }
        });
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new OrderModule());
    }
}
