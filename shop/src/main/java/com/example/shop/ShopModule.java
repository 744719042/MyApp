package com.example.shop;

import com.example.injection.AbsModule;
import com.example.injection.Inject;
import com.example.injection.Provider;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.provider.NetworkProvider;
import com.example.shop.network.ShopApi;
import com.example.shop.network.ShopRepository;

public class ShopModule extends AbsModule {
    @Override
    public void configure() {
        bind(NetworkWrapper.class).toInstance(NetworkProvider.getInstance().getNetworkService());
        bind(ShopRepository.class).toProvider(ShopRepositoryProvider.class);
        bind(ShopApi.class).toProvider(ShopApiProvider.class);
    }

    private static class ShopRepositoryProvider implements Provider<ShopRepository> {

        private ShopApi shopApi;
        private NetworkWrapper networkWrapper;

        @Inject
        public ShopRepositoryProvider(ShopApi shopApi, NetworkWrapper networkWrapper) {
            this.shopApi = shopApi;
            this.networkWrapper = networkWrapper;
        }

        @Override
        public ShopRepository get() {
            return new ShopRepository(shopApi, networkWrapper);
        }
    }

    private static class ShopApiProvider implements Provider<ShopApi> {

        private NetworkWrapper networkWrapper;

        @Inject
        public ShopApiProvider(NetworkWrapper networkWrapper) {
            this.networkWrapper = networkWrapper;
        }

        @Override
        public ShopApi get() {
            return networkWrapper.create(ShopApi.class);
        }
    }

}
