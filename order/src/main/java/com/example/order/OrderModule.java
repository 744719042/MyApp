package com.example.order;

import com.example.injection.AbsModule;
import com.example.injection.Inject;
import com.example.injection.Provider;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.order.network.OrderApi;
import com.example.order.network.OrderRepository;
import com.example.provider.NetworkProvider;

public class OrderModule extends AbsModule {
    @Override
    public void configure() {
        bind(NetworkWrapper.class).toInstance(NetworkProvider.getInstance().getNetworkService());
        bind(OrderApi.class).toProvider(OrderApiProvider.class);
        bind(OrderRepository.class).toProvider(OrderRepositoryProvider.class);
    }

    private static class OrderApiProvider implements Provider<OrderApi> {

        private NetworkWrapper networkWrapper;

        @Inject
        public OrderApiProvider(NetworkWrapper networkWrapper) {
            this.networkWrapper = networkWrapper;
        }

        @Override
        public OrderApi get() {
            return networkWrapper.create(OrderApi.class);
        }
    }

    private static class OrderRepositoryProvider implements Provider<OrderRepository> {

        private OrderApi orderApi;
        private NetworkWrapper networkWrapper;

        @Inject
        public OrderRepositoryProvider(OrderApi orderApi, NetworkWrapper networkWrapper) {
            this.orderApi = orderApi;
            this.networkWrapper = networkWrapper;
        }

        @Override
        public OrderRepository get() {
            return new OrderRepository(orderApi, networkWrapper);
        }
    }
 }
