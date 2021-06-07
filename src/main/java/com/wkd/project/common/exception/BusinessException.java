package com.wkd.project.common.exception;

/**
 * @author wkd
 * @version 1.0.0
 * @className BaseException.java
 * @description 业务异常
 * @createTime 2021-06-07 11:20
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 8977813949410635705L;

    public BusinessException(IResponseEnum responseEnum) {
        super(responseEnum);
    }

    public BusinessException(IResponseEnum responseEnum, String message, Object... args) {
        super(responseEnum, message, args);
    }

    public BusinessException(IResponseEnum responseEnum, Throwable throwable, String message, Object... args) {
        super(responseEnum, throwable, message, args);
    }
}
