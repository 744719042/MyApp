package com.example.shop.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;
import com.example.shop.R;
import com.example.shop.model.Goods;

import java.util.List;

public class GoodsAdapter extends BaseRecyclerAdapter<Goods> {

    public GoodsAdapter(List<Goods> data, Context context) {
        super(data, context, R.layout.shop_item);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Goods goods, int position) {
        holder.setText(R.id.name, goods.getName());
        holder.setText(R.id.desc, goods.getDesc());

        ImageView imageView = holder.getView(R.id.image);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(goods.getUrl()).placeHolder(R.drawable.shop_place_holder).into(imageView);
    }
}