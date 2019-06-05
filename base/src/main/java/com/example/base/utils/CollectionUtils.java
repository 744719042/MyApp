package com.example.base.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zhangxingzhong on 2018/1/15.
 * 集合相关的工具类
 */

public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
