package com.wkd.project.common.exception;

/**
 * @author wkd
 * @version 1.0.0
 * @className BaseException.java
 * @description 基础异常
 * @createTime 2021-06-07 11:20
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1396208833342256295L;

    IResponseEnum responseEnum;

    public BaseException(IResponseEnum responseEnum) {
        super();
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponseEnum responseEnum, String message, Object... args) {
        super(String.format(message, args));
        this.responseEnum = responseEnum;
    }

    public BaseException(IResponseEnum responseEnum, Throwable throwable, String message, Object... args) {
        super(String.format(message, args), throwable);
        this.responseEnum = responseEnum;
    }

    public IResponseEnum getResponseEnum() {
        return responseEnum;
    }
}
