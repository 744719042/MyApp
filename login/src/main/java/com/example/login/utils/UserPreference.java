package com.example.login.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.base.BaseApplication;
import com.example.provider.GsonProvider;
import com.example.provider.model.AccountModel;

public class UserPreference {
    private static final String KEY_USER_INFO = "key_user_info";
    private static final String KEY_LOGIN_SESSION = "key_login_session";
    private static final String TABLE_USER_INFO = "table_user_info";

    private SharedPreferences sharedPreferences;

    private UserPreference() {
        this.sharedPreferences = BaseApplication.getContext().getSharedPreferences(TABLE_USER_INFO, Context.MODE_PRIVATE);
    }

    private static class UserPreferenceHolder {
        private static final UserPreference INSTANCE = new UserPreference();
    }

    public static UserPreference getInstance() {
        return UserPreferenceHolder.INSTANCE;
    }

    public AccountModel getAccountInfo() {
        String userInfo = sharedPreferences.getString(KEY_USER_INFO, "");
        return GsonProvider.getInstance().parse(userInfo, AccountModel.class);
    }

    public boolean isLogin() {
        return sharedPreferences.contains(KEY_LOGIN_SESSION);
    }

    public void clearAccountInfo() {
        sharedPreferences.edit().remove(KEY_USER_INFO).remove(KEY_LOGIN_SESSION).commit();
    }

    public void saveLoginInfo(AccountModel accountModel, String session) {
        String userInfo = GsonProvider.getInstance().getGson().toJson(accountModel);
        sharedPreferences.edit().putString(KEY_USER_INFO, userInfo).putString(KEY_LOGIN_SESSION, session).commit();
    }
}
