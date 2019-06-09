package com.example.order.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.imagefetcher.ImageFetcher;
import com.example.order.R;
import com.example.order.model.Order;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class OrderAdapter extends BaseRecyclerAdapter<Order> {

    public OrderAdapter(List<Order> data, Context context) {
        super(data, context, R.layout.order_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Order order, int position) {
        holder.setText(R.id.shop_name, order.getName());
        holder.setText(R.id.order_status, order.getStatus());
        holder.setText(R.id.goods_name, order.getGoodsName());
        holder.setText(R.id.order_time, order.getOrderTime());
        holder.setText(R.id.total_price, order.getTotalPrice());

        ImageView imageView = holder.getView(R.id.image);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(order.getUrl()).placeHolder(R.drawable.order_place_holder).into(imageView);
    }
}