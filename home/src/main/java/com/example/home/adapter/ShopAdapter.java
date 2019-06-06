package com.example.home.adapter;

import android.content.Context;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.home.R;
import com.example.home.model.Shop;

import java.util.List;

public class ShopAdapter extends BaseRecyclerAdapter<Shop> {

    public ShopAdapter(List<Shop> data, Context context) {
        super(data, context, R.layout.home_shop_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Shop shop, int position) {

    }
}
