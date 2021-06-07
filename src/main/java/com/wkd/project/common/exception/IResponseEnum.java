package com.wkd.project.common.exception;

/**
 * @author wkd
 * @version 1.0.0
 * @className BaseException.java
 * @description 返回信息
 * @createTime 2021-06-07 11:20
 */
public interface IResponseEnum {

    /**
     * 获取返回信息枚举的 code
     *
     * @return
     */
    int getCode();

    /**
     * 获取返回信息枚举的 message
     *
     * @return
     */
    String getMessage();
}
