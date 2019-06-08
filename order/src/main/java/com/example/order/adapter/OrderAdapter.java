package com.example.order.adapter;

import android.content.Context;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.order.R;
import com.example.order.model.Order;

import java.util.List;

public class OrderAdapter extends BaseRecyclerAdapter<Order> {

    public OrderAdapter(List<Order> data, Context context) {
        super(data, context, R.layout.order_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Order shop, int position) {

    }
}