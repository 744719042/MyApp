package com.example.home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.base.recycler.BaseRecyclerAdapter;
import com.example.base.recycler.BaseRecyclerViewHolder;
import com.example.home.R;
import com.example.home.model.Category;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class CategoryAdapter extends BaseRecyclerAdapter<Category> {

    public CategoryAdapter(List<Category> data, Context context) {
        super(data, context, R.layout.home_category);
    }

    @Override
    protected void bindView(BaseRecyclerViewHolder holder, Category category, int position) {
        holder.setText(R.id.text, category.getName());
        ImageView imageView = holder.getView(R.id.icon);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(category.getUrl()).placeHolder(R.drawable.home_place_holder).into(imageView);
    }
}
