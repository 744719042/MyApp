package com.example.login;

import com.example.imagefetcher.utils.CollectionUtils;
import com.example.login.utils.UserPreference;
import com.example.provider.manager.AccountManager;
import com.example.provider.model.AccountModel;
import com.example.routerbase.RouterType;
import com.example.routerbase.annotation.Router;

import java.util.ArrayList;
import java.util.List;

@Router(path="/login/manager", type = RouterType.Service)
public class UserInfoManager implements AccountManager {
    private List<AccountListener> listeners = new ArrayList<>();

    @Override
    public AccountModel getAccount() {
        return UserPreference.getInstance().getAccountInfo();
    }

    @Override
    public boolean isLogin() {
        return UserPreference.getInstance().isLogin();
    }

    @Override
    public void notifyLoginSuccess() {
        if (!CollectionUtils.isEmpty(listeners)) {
            for (AccountListener listener : listeners) {
                listener.onLoginSuccess();
            }
        }
    }

    @Override
    public void notifyLoginFailure() {
        if (!CollectionUtils.isEmpty(listeners)) {
            for (AccountListener listener : listeners) {
                listener.onLoginFailure();
            }
        }
    }

    @Override
    public void notifyLogout() {
        if (!CollectionUtils.isEmpty(listeners)) {
            for (AccountListener listener : listeners) {
                listener.onLogout();
            }
        }
    }

    @Override
    public void registerListener(AccountListener listener) {
        if (listener == null) {
            return;
        }
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void unregisterListener(AccountListener listener) {
        if (listener == null) {
            return;
        }
        listeners.remove(listener);
    }
}
