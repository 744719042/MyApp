package com.example.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.home.R;
import com.example.home.model.HorizontalBanner;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class HorizontalBannerAdapter extends PagerAdapter {
    private List<HorizontalBanner> bannerList;
    private Context context;
    private LayoutInflater inflater;

    public HorizontalBannerAdapter(List<HorizontalBanner> bannerList, Context context) {
        this.bannerList = bannerList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View convertView = inflater.inflate(R.layout.home_horizontal_banner_view, container, false);
        ImageView imageView = convertView.findViewById(R.id.image);
        container.addView(convertView);
        HorizontalBanner horizontalBanner = bannerList.get(position);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(horizontalBanner.getUrl()).placeHolder(R.drawable.home_place_holder).into(imageView);
        return convertView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
