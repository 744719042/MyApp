package com.example.routerapi.filter;

import com.example.routerapi.ErrorHandler;
import com.example.routerapi.RouterCallback;
import com.example.routerapi.disptcher.RouterDispatcher;
import com.example.routerapi.disptcher.RouterDispatcherHelper;
import com.example.routerbase.RouterConfig;
import com.example.routerapi.RouterManager;
import com.example.routerapi.RouterRequest;

public class RequestInitFilter implements Filter {

    @Override
    public Object doFilter(FilterChain chain) {
        RouterRequest request = chain.getRequest();
        RouterCallback callback = request.getCallback();

        RouterConfig routerConfig = RouterManager.getInstance().getRouterConfig(request.getPath());
        if (routerConfig == null) {
            if (callback != null) {
                callback.onLost(null);
            } else {
                String errorPath = "/provider/error";
                RouterRequest errorRequest = new RouterRequest.Builder(errorPath).build();
                RouterConfig errorConfig = RouterManager.getInstance().getRouterConfig(errorPath);
                errorRequest.setConfig(errorConfig);
                ErrorHandler errorHandler = (ErrorHandler) RouterDispatcherHelper.dispatchService(errorRequest);
                if (errorHandler != null) {
                    errorHandler.onError(-1);
                }
            }
            return null;
        } else {
            request.setConfig(routerConfig);
            if (callback != null) {
                callback.onFound(request);
            }
            return chain.proceed();
        }
    }
}
