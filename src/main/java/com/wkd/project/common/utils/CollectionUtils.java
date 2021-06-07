package com.wkd.project.common.utils;

import java.util.Collection;
import java.util.Objects;

/**
 * @author wkd
 * @version 1.0.0
 * @className CollectionUtils.java
 * @description 集合工具类
 * @createTime 2021-06-07 10:36
 */
public class CollectionUtils {

    /**
     * * 判断一个Collection是否为空
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return Objects.isNull(coll) || coll.isEmpty();
    }
}
