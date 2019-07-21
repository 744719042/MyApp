package com.example.provider.manager;

import com.example.provider.model.AccountModel;

public interface AccountManager {
    interface AccountListener {
        void onLoginSuccess();
        void onLoginFailure();
        void onLogout();
    }

    AccountModel getAccount();
    boolean isLogin();
    void notifyLoginSuccess();
    void notifyLoginFailure();
    void notifyLogout();
    void registerListener(AccountListener listener);
    void unregisterListener(AccountListener listener);
}
