package com.example.shop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.provider.constant.RouterConstant;
import com.example.routerapi.RouterManager;
import com.example.routerapi.RouterRequest;
import com.example.shop.ui.fragment.ShopDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopDetailAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();
    public ShopDetailAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(new ShopDetailFragment());
        RouterRequest request = RouterManager.getInstance().with(RouterConstant.Comment.COMMENT_LIST).build();
        mFragments.add((Fragment) RouterManager.getInstance().navigate(request));
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
