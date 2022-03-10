package com.wkd.project.common.utils;

import java.util.Objects;

/**
 * @author wkd
 * @version 1.0.0
 * @className StringUtils.java
 * @description string工具类
 * @createTime 2021-06-07 11:38
 */
public class StringUtils {

    /**
     * 空字符串
     */
    public static final String NULL_STRING = "";

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return Objects.isNull(str) || NULL_STRING.equals(str.trim());
    }

}
