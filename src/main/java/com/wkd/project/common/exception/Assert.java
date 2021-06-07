package com.wkd.project.common.exception;


import com.wkd.project.common.utils.StringUtils;

import java.util.Objects;

/**
 * @author wkd
 * @version 1.0.0
 * @className AesUtil.java
 * @description 检验断言
 * @createTime 2021-06-07 10:44
 */
public interface Assert {

    /**
     * 基础异常生成
     *
     * @param args
     * @return
     */
    BaseException newException(Object... args);

    /**
     * 基础异常生成
     *
     * @param t
     * @param args
     * @return
     */
    BaseException newException(Throwable t, Object... args);

    /**
     * 判断对象为空则抛出异常
     *
     * @param obj
     */
    default void assertNotNull(Object obj) {
        if (Objects.isNull(obj)) {
            throw newException();
        }
    }

    /**
     * 判断对象不为空则抛出异常
     *
     * @param obj
     */
    default void assertNull(Object obj) {
        if (!Objects.isNull(obj)) {
            throw newException(obj);
        }
    }

    /**
     * 判断对象String为空则抛出异常
     *
     * @param str
     */
    default void assertNotBlank(String str) {
        if (StringUtils.isEmpty(str)) {
            throw newException();
        }
    }

    /**
     * 断言两个数相等  不相等抛出异常
     *
     * @param excepted 期望的数值
     * @param actual   实际的数值
     */
    default void assertEquals(Integer excepted, Integer actual) {
        if (excepted == null && actual == null) {
            return;
        }
        if (excepted != null && excepted.equals(actual)) {
            return;
        }
        throw newException(actual);
    }

    /**
     * 断言两个String相等  不相等抛出异常
     *
     * @param excepted 期望的数值
     * @param actual   实际的数值
     */
    default void assertEquals(String excepted, String actual) {
        if (StringUtils.isEmpty(excepted) && StringUtils.isEmpty(actual)) {
            return;
        }
        if (!StringUtils.isEmpty(excepted) && excepted.equals(actual)) {
            return;
        }
        throw newException(actual);
    }

    /**
     * 断言两个String不相等  相等抛出异常
     *
     * @param excepted 期望的数值
     * @param actual   实际的数值
     */
    default void assertNotEquals(String excepted, String actual) {
        if (StringUtils.isEmpty(excepted) && StringUtils.isEmpty(actual)) {
            return;
        }
        if (!StringUtils.isEmpty(excepted) && excepted.equals(actual)) {
            throw newException(actual);
        }

    }

    /**
     * 断言两个数不相等  相等抛出异常
     *
     * @param excepted 期望的数值
     * @param actual   实际的数值
     */
    default void assertNotEquals(Integer excepted, Integer actual) {
        if (excepted == null && actual == null) {
            throw newException(actual);
        }
        if (excepted != null && excepted.equals(actual)) {
            throw newException(actual);
        }
    }

    /**
     * 断言表达式为 true
     *
     * @param expression
     */
    default void assertTrue(boolean expression) {
        if (!expression) {
            throw newException();
        }
    }

    /**
     * 断言表达式不为 true
     *
     * @param expression
     */
    default void assertNotTrue(boolean expression) {
        if (expression) {
            throw newException();
        }
    }

}
