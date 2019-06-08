package com.example.shop.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;
import com.example.shop.R;
import com.example.shop.model.Shop;

import java.util.List;

public class ShopAdapter extends BaseRecyclerAdapter<Shop> {

    public ShopAdapter(List<Shop> data, Context context) {
        super(data, context, R.layout.shop_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Shop shop, int position) {
        holder.setText(R.id.name, shop.getName());
        holder.setText(R.id.desc, shop.getDesc());

        ImageView imageView = holder.getView(R.id.image);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(shop.getUrl()).placeHolder(R.drawable.shop_place_holder).into(imageView);
    }
}