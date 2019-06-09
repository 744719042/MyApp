package com.example.shop.adapter;

import android.content.Context;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.shop.R;

import java.util.List;

public class CategoryAdapter extends BaseRecyclerAdapter<String> {
    public CategoryAdapter(List<String> data, Context context) {
        super(data, context, R.layout.shop_goods_category);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, String s, int position) {
        holder.setText(R.id.category, s);
    }
}
