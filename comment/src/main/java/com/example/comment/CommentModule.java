package com.example.comment;

import com.example.comment.network.CommentApi;
import com.example.comment.network.CommentRepository;
import com.example.injection.AbsModule;
import com.example.injection.Inject;
import com.example.injection.Provider;
import com.example.network.wrapper.core.NetworkWrapper;
import com.example.provider.NetworkProvider;

public class CommentModule extends AbsModule {
    @Override
    public void configure() {
        bind(NetworkWrapper.class).toInstance(NetworkProvider.getInstance().getNetworkService());
        bind(CommentApi.class).toProvider(CommentApiProvider.class);
        bind(CommentRepository.class).toProvider(CommentRepositoryProvider.class);
    }

    private static class CommentApiProvider implements Provider<CommentApi> {

        private NetworkWrapper networkWrapper;

        @Inject
        public CommentApiProvider(NetworkWrapper networkWrapper) {
            this.networkWrapper = networkWrapper;
        }

        @Override
        public CommentApi get() {
            return networkWrapper.create(CommentApi.class);
        }
    }

    private static class CommentRepositoryProvider implements Provider<CommentRepository> {

        private CommentApi orderApi;
        private NetworkWrapper networkWrapper;

        @Inject
        public CommentRepositoryProvider(CommentApi orderApi, NetworkWrapper networkWrapper) {
            this.orderApi = orderApi;
            this.networkWrapper = networkWrapper;
        }

        @Override
        public CommentRepository get() {
            return new CommentRepository(orderApi, networkWrapper);
        }
    }
 }
