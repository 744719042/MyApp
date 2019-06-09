package com.example.login.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.base.ui.BaseActivity;
import com.example.injection.Inject;
import com.example.injection.Module;
import com.example.login.R;
import com.example.login.UserModule;
import com.example.login.network.UserRepository;
import com.example.network.MyNetException;
import com.example.network.wrapper.core.DataCallback;
import com.example.provider.constant.RouterConstant;
import com.example.provider.model.AccountModel;
import com.example.routerapi.RouterManager;
import com.example.routerbase.annotation.Router;

import java.util.Arrays;
import java.util.List;

@Router(path = "/login/login")
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    private UserRepository userRepository;
    private EditText name;
    private EditText password;
    private Button login;

    private String targetUrl;
    private Bundle bundle;

    @Override
    protected void initArgs(Intent intent) {
        super.initArgs(intent);
        if (intent != null) {
            targetUrl = intent.getStringExtra(RouterConstant.ARG_TARGET_URL);
            bundle = intent.getExtras();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        name = findViewById(R.id.user_name);
        password = findViewById(R.id.user_password);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public List<? extends Module> getModules() {
        return Arrays.asList(new UserModule());
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            login();
        }
    }

    private void login() {
        String account = name.getText().toString();
        String pass = password.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(), "登录信息填写不完全", Toast.LENGTH_SHORT).show();
            return;
        }

        userRepository.login(account, pass, new DataCallback<AccountModel>() {
            @Override
            public void onSuccess(AccountModel accountModel) {
                if (!TextUtils.isEmpty(targetUrl)) {
                    RouterManager.getInstance().with(targetUrl).withActivity(LoginActivity.this).withBundle(bundle).navigate();
                }
                setResult(Activity.RESULT_OK);
                finish();
            }

            @Override
            public void onFailure(int code, MyNetException e) {
                String msg = e == null ? "登录失败" : e.getMessage();
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
