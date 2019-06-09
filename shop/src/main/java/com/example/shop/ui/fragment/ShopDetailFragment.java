package com.example.shop.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.base.ui.BaseFragment;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.shop.R;
import com.example.shop.ShopModule;
import com.example.shop.adapter.CategoryAdapter;
import com.example.shop.adapter.GoodsAdapter;
import com.example.shop.model.Goods;
import com.example.shop.network.ShopRepository;

import java.util.Arrays;
import java.util.List;

public class ShopDetailFragment extends BaseFragment {
    @Inject
    private ShopRepository shopRepository;
    private RecyclerView categoryRecyclerView;
    private RecyclerView goodsRecyclerView;

    private List<String> categoryList = Arrays.asList("电脑" , "平板", "鼠标", "键盘",
            "主机", "显示器", "内存", "耗材", "Android手机", "iPhone", "iPad", "硬盘", "主板");

    @Override
    protected void initData() {
        categoryRecyclerView.setAdapter(new CategoryAdapter(categoryList, getContext()));
        shopRepository.getGoodsList(10, new DataCallback<List<Goods>>() {

            @Override
            public void onSuccess(List<Goods> goods) {
                goodsRecyclerView.setAdapter(new GoodsAdapter(goods, getContext()));
            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });
    }

    @Override
    protected void initViews(View view) {
        categoryRecyclerView = view.findViewById(R.id.category_view);
        goodsRecyclerView = view.findViewById(R.id.goods_detail_view);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public int getLayoutResource() {
        return R.layout.shop_detail_goods_detail;
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new ShopModule());
    }
}
