package com.example.login.network;

import com.example.login.utils.UserPreference;
import com.example.network.Callback;
import com.example.network.Headers;
import com.example.network.HttpClient;
import com.example.network.MainThreadPoster;
import com.example.network.MyNetException;
import com.example.network.Request;
import com.example.network.Response;
import com.example.network.wrapper.core.DataCallback;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.provider.GsonProvider;
import com.example.provider.model.AccountModel;

public class UserRepository {
    private UserApi userApi;
    private NetworkWrapper networkWrapper;
    private MainThreadPoster mainThreadPoster;

    public UserRepository(UserApi userApi, NetworkWrapper networkWrapper, MainThreadPoster mainThreadPoster) {
        this.userApi = userApi;
        this.networkWrapper = networkWrapper;
        this.mainThreadPoster = mainThreadPoster;
    }

    public void login(String name, String password, final DataCallback<AccountModel> callback) {
        UserPreference.getInstance().clearAccountInfo();
        HttpClient httpClient = networkWrapper.getHttpClient();
        UserApi userApi = networkWrapper.create(UserApi.class);
        final Request request = userApi.login(name, password);
        httpClient.enqueue(request, new Callback() {
            @Override
            public void onSuccess(Response response) {
                Headers headers = response.getHeaders();
                String session = "";
                String cookieString = headers.getValues().get("Set-Cookies");
                String[] cookies = cookieString.split(";");
                for (String cookie : cookies) {
                    if (cookie.contains("JSESSIONID")) {
                        String[] sessions = cookie.split("=");
                        session = sessions[1];
                    }
                }

                String userInfo = response.getResponseBody().toString();
                final AccountModel accountModel = GsonProvider.getInstance().getGson().fromJson(userInfo, AccountModel.class);
                UserPreference.getInstance().saveLoginInfo(accountModel, session);

                mainThreadPoster.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onSuccess(accountModel);
                        }
                    }
                });
            }

            @Override
            public void onFailure(final int code, final MyNetException e) {
                UserPreference.getInstance().clearAccountInfo();
                mainThreadPoster.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onFailure(code, e);
                        }
                    }
                });
            }
        });
    }
}
