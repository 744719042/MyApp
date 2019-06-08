package com.example.myapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.ui.BaseFragment;
import com.example.injection.Module;

import java.util.List;

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private TextView mSeeAllOrder;
    private ViewGroup mTopItemNotPaid;
    private TextView mTopItemNotPaidCountTv;
    private ViewGroup mTopItemNotDelivery;
    private TextView mTopItemNotDeliveryCountTv;
    private ViewGroup mTopItemNotReceived;
    private TextView mTopItemNotReceivedCountTv;
    private ViewGroup mTopItemNotComment;
    private TextView mTopItemNotCommentCountTv;

    private ImageView mLogInButton;
    private ViewGroup mLoginLayout;
    private ImageView mAvatarImg;
    private TextView mAccountTxt;

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews(View view) {
        mSeeAllOrder = view.findViewById(R.id.about_more_order);
        mTopItemNotPaid = view.findViewById(R.id.about_unpaid_layout);
        mTopItemNotPaidCountTv = view.findViewById(R.id.about_unpaid_count);
        mTopItemNotDelivery = view.findViewById(R.id.about_undelivery_layout);
        mTopItemNotDeliveryCountTv = view.findViewById(R.id.about_undelivery_count);
        mTopItemNotReceived = view.findViewById(R.id.about_receive_layout);
        mTopItemNotReceivedCountTv = view.findViewById(R.id.about_unreceived_count);
        mTopItemNotComment = view.findViewById(R.id.about_uncomment_layout);
        mTopItemNotCommentCountTv = view.findViewById(R.id.about_uncomment_count);
        mSeeAllOrder.setOnClickListener(this);
        mTopItemNotPaid.setOnClickListener(this);
        mTopItemNotDelivery.setOnClickListener(this);
        mTopItemNotReceived.setOnClickListener(this);
        mTopItemNotComment.setOnClickListener(this);

        // 登录登出
        mLogInButton = view.findViewById(R.id.about_login_button);
        mLoginLayout = view.findViewById(R.id.about_login_layout);
        mAvatarImg = view.findViewById(R.id.about_account_avatar);
        mAccountTxt = view.findViewById(R.id.about_account_name);
        mLogInButton.setOnClickListener(this);
        mAvatarImg.setOnClickListener(this);
        mAccountTxt.setOnClickListener(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public List<? extends Module> getModules() {
        return null;
    }
    @Override
    public void onClick(View v) {
        if (!isAdded()) {
            return;
        }

        if (v == mAccountTxt || v == mAvatarImg) {

        } else if (v == mLogInButton) {

        } else if (v == mSeeAllOrder || v == mTopItemNotPaid || v == mTopItemNotDelivery
                || v == mTopItemNotReceived || v == mTopItemNotComment) {

        }
    }
}
