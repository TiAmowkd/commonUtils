package com.wkd.project.common.enums;

import com.wkd.project.common.exception.BusinessExceptionAssert;
import lombok.Getter;

/**
 * @author wkd
 * @version 1.0.0
 * @className ResultCode.java
 * @description 状态码枚举
 * @createTime 2021-07-29 11:49
 */
@Getter
public enum ResultCode implements StatusCode, BusinessExceptionAssert {
    /**
     * 状态码枚举
     */
    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    VALIDATE_ERROR(1002, "参数校验失败"),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败"),
    SERVER_ERROR(1004, "系统异常"),

    /* 参数错误：1000～1999 */
    /*PARAM_NOT_VALID(1004, "参数无效"),
    PARAM_IS_BLANK(1005, "参数为空"),
    PARAM_TYPE_ERROR(1006, "参数类型错误"),
    PARAM_NOT_COMPLETE(1007, "参数缺失"),*/

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    USER_NOT_NAME(2010, "用户名为空");


    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
