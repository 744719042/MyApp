package com.example.login;

import com.example.login.utils.UserPreference;
import com.example.routerapi.RouterCallback;
import com.example.routerapi.RouterRequest;
import com.example.routerapi.filter.FilterChain;
import com.example.routerbase.annotation.Filter;

import static com.example.provider.constant.RouterExtra.EXTRA_LOGIN;

@Filter(priority = 10)
public class LoginFilter implements com.example.routerapi.filter.Filter {

    @Override
    public Object doFilter(FilterChain chain) {
        RouterRequest request = chain.getRequest();
        if (request.getExtra() == EXTRA_LOGIN) {
            if (UserPreference.getInstance().isLogin()) {
                return chain.proceed();
            } else {
                RouterCallback routerCallback = request.getCallback();
                if (routerCallback != null) {
                    routerCallback.onIntercept(request, chain);
                    return null;
                }
            }
        }
        return chain.proceed();
    }
}
