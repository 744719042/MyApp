package com.example.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.home.R;
import com.example.home.model.Shop;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class ShopAdapter extends BaseRecyclerAdapter<Shop> {

    public ShopAdapter(List<Shop> data, Context context) {
        super(data, context, R.layout.home_shop_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Shop shop, int position) {
        ImageView imageView = holder.getView(R.id.image);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(shop.getUrl()).placeHolder(R.drawable.home_place_holder).into(imageView);

        holder.setText(R.id.name, shop.getName());
        holder.setText(R.id.month_sale, shop.getSales());
        holder.setRating(R.id.rating_bar, shop.getScore());
    }
}
