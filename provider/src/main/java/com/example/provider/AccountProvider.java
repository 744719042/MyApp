package com.example.provider;

import com.example.provider.manager.AccountManager;

public class AccountProvider {
    private AccountManager accountManager;

    private AccountProvider() {

    }

    public void initAccountMananger(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public static AccountProvider getInstance() {
        return AccountProviderHolder.INSTANCE;
    }

    private static class AccountProviderHolder {
        private static final AccountProvider INSTANCE = new AccountProvider();
    }
}
