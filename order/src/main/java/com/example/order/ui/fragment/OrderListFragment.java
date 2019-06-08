package com.example.order.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.ui.PagedFragment;
import com.example.imagefetcher.utils.CollectionUtils;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.order.OrderModule;
import com.example.order.adapter.OrderAdapter;
import com.example.order.model.Order;
import com.example.order.network.OrderRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderListFragment extends PagedFragment {

    @Inject
    private OrderRepository orderRepository;

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new OrderAdapter(new ArrayList<Order>(), getContext());
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
                }
            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new OrderModule());
    }
}
