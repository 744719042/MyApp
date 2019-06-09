package com.example.login.ui.activity;

import android.app.Activity;
import android.text.TextUtils;
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
import com.example.provider.model.AccountModel;
import com.example.routerbase.annotation.Router;

import java.util.Arrays;
import java.util.List;

@Router(path = "/login/login")
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Inject
    private UserRepository userRepository;
    private EditText name;
    private EditText passwordl;
    private Button login;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        name = findViewById(R.id.user_name);
        passwordl = findViewById(R.id.user_password);
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
            String account = name.getText().toString();
            String password = passwordl.getText().toString();
            if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "登录信息填写不完全", Toast.LENGTH_SHORT).show();
                return;
            }

            userRepository.login(account, password, new DataCallback<AccountModel>() {
                @Override
                public void onSuccess(AccountModel accountModel) {
                    setResult(Activity.RESULT_OK);
                }

                @Override
                public void onFailure(int code, MyNetException e) {
                    setResult(Activity.RESULT_CANCELED);
                }
            });
        }
    }
}
