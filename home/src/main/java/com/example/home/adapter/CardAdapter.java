package com.example.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.widget.custom.AbNormalAdapter;
import com.example.home.model.Card;

import java.util.List;

public class CardAdapter extends AbNormalAdapter<Card> {

    public CardAdapter(Context context, List<Card> data) {
        super(context, data);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
