package com.example.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.widget.custom.AbNormalAdapter;
import com.example.base.widget.custom.AbNormalLayout;
import com.example.home.R;
import com.example.home.model.Card;
import com.example.imagefetcher.ImageFetcher;
import com.example.provider.ImageFetcherProvider;

import java.util.List;

public class CardAdapter extends AbNormalAdapter<Card> {
    private LayoutInflater inflater;
    private ImageFetcher imageFetcher;

    public CardAdapter(Context context, List<Card> data) {
        super(context, data);
        this.inflater = LayoutInflater.from(context);
        this.imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getCount()) {
            case 2:
                convertView =  initCardViews(position, R.layout.home_card_two_common_view, convertView, parent);
                initLayoutParams(convertView, 0.5f, 1.0f);
                return convertView;
            case 3:
                convertView = initCardViews(position, R.layout.home_card_three_common_view, convertView, parent);
                initLayoutParams(convertView, 0.33f, 1.0f);
                return convertView;
            case 4:
                if (position == 0) {
                    convertView = initCardViews(position, R.layout.home_card_large, convertView, parent);
                    initLayoutParams(convertView, 0.3f, 1.0f);
                } else if (position == 1) {
                    convertView = initCardViews(position, R.layout.home_card_long_view, convertView, parent);
                    initLayoutParams(convertView, 0.7f, 0.5f);
                } else {
                    convertView = initCardViews(position, R.layout.home_card_common_view, convertView, parent);
                    initLayoutParams(convertView, 0.35f, 0.5f);
                }
                return convertView;
            case 5:
                if (position == 0) {
                    convertView = initCardViews(position, R.layout.home_card_large, convertView, parent);
                    initLayoutParams(convertView, 0.3f, 1.0f);
                } else {
                    convertView = initCardViews(position, R.layout.home_card_common_view, convertView, parent);
                    initLayoutParams(convertView, 0.35f, 0.5f);
                }
                return convertView;
        }
        return null;
    }

    private void initLayoutParams(View convertView, float widthRatio, float heightRatio) {
        AbNormalLayout.LayoutParams params = (AbNormalLayout.LayoutParams) convertView.getLayoutParams();
        params.setWidthRatio(widthRatio);
        params.setHeightRatio(heightRatio);
    }

    private View initCardViews(int position, int layoutRes, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(layoutRes, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = convertView.findViewById(R.id.text_title);
            viewHolder.subTitle = convertView.findViewById(R.id.sub_title);
            viewHolder.imageView = convertView.findViewById(R.id.image);
            viewHolder.bgImageView = convertView.findViewById(R.id.card_bg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Card card = getItem(position);
        viewHolder.title.setText(card.getTitle());
        viewHolder.subTitle.setText(card.getSubTitle());
        imageFetcher.load(card.getUrl()).placeHolder(R.drawable.home_place_holder).into(viewHolder.imageView);
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView subTitle;
        ImageView imageView;
        ImageView bgImageView;
    }
}
