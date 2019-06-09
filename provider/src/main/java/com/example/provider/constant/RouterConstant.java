package com.example.provider.constant;

public interface RouterConstant {
    String ARG_TARGET_URL = "arg_target_url";

    interface Login {
        String LOGIN = "/login/login";
    }

    interface Order {
        String ORDER_LIST = "/order/list";
    }

    interface App {
        String MAIN = "/app/main";
    }

    interface Shop {
        String SHOP_LIST = "/shop/list";
        String SHOP_DETAIL = "/shop/detail";
    }

    interface Comment {
        String COMMENT_LIST = "/comment/list";
    }
}
