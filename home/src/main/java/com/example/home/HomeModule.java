package com.example.home;

import android.util.Log;

import com.example.home.net.HomeApi;
import com.example.home.net.HomeRepository;
import com.example.injection.AbsModule;
import com.example.injection.Inject;
import com.example.injection.Provider;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.provider.NetworkProvider;

public class HomeModule extends AbsModule {
    private static final String TAG = "HomeModule";
    @Override
    public void configure() {
        bind(NetworkWrapper.class).toInstance(NetworkProvider.getInstance().getNetworkService());
        bind(HomeRepository.class).toProvider(HomeRepositoryProvider.class);
        bind(HomeApi.class).toProvider(HomeApiProvider.class);

        Log.e(TAG, "bindingMap = " + getBinder().getBindings());
    }

    public static class HomeRepositoryProvider implements Provider<HomeRepository> {

        private NetworkWrapper networkWrapper;
        private HomeApi homeApi;

        @Inject
        public HomeRepositoryProvider(NetworkWrapper networkWrapper, HomeApi homeApi) {
            this.networkWrapper = networkWrapper;
            this.homeApi = homeApi;
        }

        @Override
        public HomeRepository get() {
            return new HomeRepository(homeApi, networkWrapper);
        }
    }

    public static class HomeApiProvider implements Provider<HomeApi> {

        private NetworkWrapper networkWrapper;

        @Inject
        public HomeApiProvider(NetworkWrapper networkWrapper) {
            this.networkWrapper = networkWrapper;
        }

        @Override
        public HomeApi get() {
            return networkWrapper.create(HomeApi.class);
        }
    }
}
