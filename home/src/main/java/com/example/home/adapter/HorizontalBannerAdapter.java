package com.example.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.home.R;
import com.example.home.model.HorizontalBanner;

import java.util.List;

public class HorizontalBannerAdapter extends BaseAdapter {
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
    public HorizontalBanner getItem(int position) {
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
            convertView = inflater.inflate(R.layout.home_horizontal_banner_view, parent, false);
            viewHolder.imageView = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }

        // TODO
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
