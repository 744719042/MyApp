package com.example.myapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.permission.PermissionManager;
import com.example.base.permission.PermissionsListener;
import com.example.base.ui.BaseActivity;
import com.example.comment.activity.CommentListFragment;
import com.example.home.ui.fragment.HomeFragment;
import com.example.injection.Module;
import com.example.routerbase.annotation.Router;
import com.example.shop.ui.fragment.ShopListFragment;

import java.util.List;

@Router(path="/app/index")
public class MainActivity extends BaseActivity implements PermissionsListener {
    private static final String KEY_CURRENT_TAB = "key_current_tab";
    private static final String TAG_HOME = "home";
    private static final String TAG_SHOP = "shop";
    private static final String TAG_MINE = "mine";

    private static final String[] PERMISSIONS = new String[] {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private ImageView mHomeImage;
    private TextView mHomeText;
    private ImageView mShopImage;
    private TextView mShopText;
    private ImageView mMineImage;
    private TextView mMineText;

    protected String currentTab = TAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            currentTab = TAG_HOME;
            onHomeChecked();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CURRENT_TAB, currentTab);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentTab = savedInstanceState.getString(KEY_CURRENT_TAB);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!PermissionManager.getInstance().hasPermission(this, PERMISSIONS)) {
            PermissionManager.getInstance().requestPermissions(this, PERMISSIONS, this);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        FrameLayout homeButton = findViewById(R.id.main_bottom_menu_home);
        mHomeImage = findViewById(R.id.main_menu_home_image);
        mHomeText = findViewById(R.id.main_menu_home_text);
        FrameLayout shopButton = findViewById(R.id.main_bottom_menu_shop);
        mShopImage = findViewById(R.id.main_menu_shop_image);
        mShopText = findViewById(R.id.main_menu_shop_text);
        FrameLayout mineButton = findViewById(R.id.main_bottom_menu_about_me);
        mMineImage = findViewById(R.id.main_menu_me_image);
        mMineText = findViewById(R.id.main_menu_me_text);

        homeButton.setOnClickListener(view -> {
            if (!TAG_HOME.equals(currentTab)) {
                onHomeChecked();
            }
        });

        shopButton.setOnClickListener(view -> {
            if (!TAG_SHOP.equals(currentTab)) {
                onShopChecked();
            }
        });

        mineButton.setOnClickListener(view -> {
            if (!TAG_MINE.equals(currentTab)) {
                onMineChecked();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {                                       // [6.0,~)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {                         // [5.0,6.0)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(0x16000000);
        }
    }

    private void onMineChecked() {
        checkMine();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setCurrentFragment(TAG_MINE);
    }

    private void onShopChecked() {
        checkShop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setCurrentFragment(TAG_SHOP);
    }

    private void onHomeChecked() {
        checkHome();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setCurrentFragment(TAG_HOME);
    }

    private void checkHome() {
        setHomeChecked(true);
        setShopChecked(false);
        setMineChecked(false);
    }

    private void checkShop() {
        setHomeChecked(false);
        setShopChecked(true);
        setMineChecked(false);
    }

    private void checkMine() {
        setHomeChecked(false);
        setShopChecked(false);
        setMineChecked(true);
    }

    private void setMineChecked(boolean checked) {
        mMineImage.setSelected(checked);
        mMineText.setSelected(checked);
    }

    private void setShopChecked(boolean checked) {
        mShopText.setSelected(checked);
        mShopImage.setSelected(checked);
    }

    private void setHomeChecked(boolean checked) {
        mHomeImage.setSelected(checked);
        mHomeText.setSelected(checked);
    }

    private void setCurrentFragment(String tabTag) {
        hideFragment(currentTab);
        showFragment(tabTag);
    }

    private void showFragment(String tabTag) {
        currentTab = tabTag;
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tabTag);
        if (fragment == null) {
            switch (tabTag) {
                case TAG_HOME:
                    fragment = new HomeFragment();
                    break;
                case TAG_SHOP:
//                    fragment = new ShopListFragment();
                    fragment = new CommentListFragment();
                    break;
                case TAG_MINE:
                    fragment = new MineFragment();
                    break;
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().add(R.id.content_layout,
                        fragment, tabTag).commitAllowingStateLoss();
            }
        } else {
            getSupportFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
        }
    }

    public void hideFragment(String tabTag) {
        if (!TextUtils.isEmpty(tabTag)) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tabTag);
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public List<? extends Module> getModules() {
        return null;
    }

    @Override
    public void onPermissionResult(boolean grantedAll, int[] grantResult) {
        for (int i = 0; i < grantResult.length; i++) {
            if (grantResult[i] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), PERMISSIONS[i] + "未授权！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
