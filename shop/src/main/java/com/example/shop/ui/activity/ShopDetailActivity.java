package com.example.shop.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.ui.BaseActivity;
import com.example.imagefetcher.ImageFetcher;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.provider.ImageFetcherProvider;
import com.example.routerbase.annotation.Router;
import com.example.shop.R;
import com.example.shop.ShopModule;
import com.example.shop.adapter.ShopDetailAdapter;
import com.example.shop.model.ShopDetail;
import com.example.shop.network.ShopRepository;
import com.example.shop.widget.MyNestedScrollView;

import java.util.Arrays;
import java.util.List;

@Router(path = "/shop/detail")
public class ShopDetailActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    private ShopRepository shopRepository;
    private FrameLayout shopHeader;
    private LinearLayout tablayout;
    private ViewPager viewPager;
    private TextView shopName;
    private TextView shopDesc;
    private ImageView shopImage;

    private MyNestedScrollView nestedScrollView;
    private FrameLayout goodsTabLayout;
    private FrameLayout commetTabLayout;
    private ImageView goodsTab;
    private ImageView commentTab;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initViews() {
        shopHeader = findViewById(R.id.shop_header);
        tablayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.content_layout);
        shopName = findViewById(R.id.shop_name);
        shopDesc = findViewById(R.id.shop_desc);
        shopImage = findViewById(R.id.shop_image);

        nestedScrollView = findViewById(R.id.nested_scroll_view);
        goodsTab = findViewById(R.id.goods_tab_image);
        commentTab = findViewById(R.id.comment_tab_image);
        goodsTabLayout = findViewById(R.id.goods_tab_layout);
        goodsTabLayout.setOnClickListener(this);
        commetTabLayout = findViewById(R.id.comment_tab_layout);
        commetTabLayout.setOnClickListener(this);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int totalHeight = nestedScrollView.getHeight();
                ViewGroup.LayoutParams params = viewPager.getLayoutParams();
                params.height = totalHeight - tablayout.getHeight();
                viewPager.setLayoutParams(params);

                nestedScrollView.setScrollHeight(shopHeader.getHeight());
                getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @Override
    protected void initData() {
        viewPager.setAdapter(new ShopDetailAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    goodsTab.setVisibility(View.VISIBLE);
                    commentTab.setVisibility(View.GONE);
                } else {
                    goodsTab.setVisibility(View.GONE);
                    commentTab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        shopRepository.getShopDetail(10, new DataCallback<ShopDetail>() {
            @Override
            public void onSuccess(ShopDetail shopDetail) {
                shopName.setText(shopDetail.getName());
                shopDesc.setText(shopDetail.getDesc());

                ImageFetcher imageFetcher = ImageFetcherProvider.getInstance().getImageFetcher();
                imageFetcher.load(shopDetail.getUrl()).placeHolder(R.drawable.shop_place_holder).into(shopImage);
            }

            @Override
            public void onFailure(int code, MyNetException e) {

            }
        });
    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new ShopModule());
    }

    @Override
    public void onClick(View v) {
        if (v == goodsTabLayout) {
            goodsTab.setVisibility(View.VISIBLE);
            commentTab.setVisibility(View.GONE);
            viewPager.setCurrentItem(0);
        } else {
            goodsTab.setVisibility(View.GONE);
            commentTab.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(1);
        }
    }
}
