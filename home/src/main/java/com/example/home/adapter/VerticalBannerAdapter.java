package com.example.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home.R;
import com.example.home.model.VerticalBanner;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class VerticalBannerAdapter extends BaseAdapter {
    private Context context;
    private List<VerticalBanner> bannerList;
    private LayoutInflater inflater;

    public VerticalBannerAdapter(Context context, List<VerticalBanner> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public VerticalBanner getItem(int position) {
        return bannerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_vertical_banner_view, parent, false);
            viewHolder.imageView = convertView.findViewById(R.id.image);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.subTitle = convertView.findViewById(R.id.sub_title);
            convertView.setTag(viewHolder);
        }

        VerticalBanner verticalBanner = getItem(position);
        ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
        imageFetcher.load(verticalBanner.getUrl()).placeHolder(R.drawable.home_place_holder).into(viewHolder.imageView);
        viewHolder.title.setText(verticalBanner.getName());
        viewHolder.subTitle.setText(verticalBanner.getDesc());
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView subTitle;
    }
}
