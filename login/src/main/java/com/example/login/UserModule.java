package com.example.login;

import com.example.injection.AbsModule;
import com.example.injection.Inject;
import com.example.injection.Provider;
import com.example.login.network.UserApi;
import com.example.login.network.UserRepository;
import com.example.network.MainThreadPoster;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.provider.NetworkProvider;

public class UserModule extends AbsModule {
    @Override
    public void configure() {
        bind(NetworkWrapper.class).toInstance(NetworkProvider.getInstance().getNetworkService());
        bind(UserApi.class).toProvider(UserApiProvider.class);
        bind(UserRepository.class).toProvider(UserRepositoryProvider.class);
        bind(MainThreadPoster.class).toInstance(new MainThreadPoster());
    }

    private static class UserApiProvider implements Provider<UserApi> {

        private NetworkWrapper networkWrapper;

        @Inject
        public UserApiProvider(NetworkWrapper networkWrapper) {
            this.networkWrapper = networkWrapper;
        }

        @Override
        public UserApi get() {
            return networkWrapper.create(UserApi.class);
        }
    }

    private static class UserRepositoryProvider implements Provider<UserRepository> {
        private UserApi userApi;
        private NetworkWrapper networkWrapper;
        private MainThreadPoster mainThreadPoster;

        @Inject
        public UserRepositoryProvider(UserApi userApi, NetworkWrapper networkWrapper, MainThreadPoster mainThreadPoster) {
            this.userApi = userApi;
            this.networkWrapper = networkWrapper;
            this.mainThreadPoster = mainThreadPoster;
        }

        @Override
        public UserRepository get() {
            return new UserRepository(userApi, networkWrapper, mainThreadPoster);
        }
    }
}
