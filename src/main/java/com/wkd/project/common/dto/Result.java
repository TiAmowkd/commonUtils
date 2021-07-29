package com.wkd.project.common.dto;

import com.wkd.project.common.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wkd
 * @version 1.0.0
 * @className Result.java
 * @description 统一返回体
 * @createTime 2021-07-29 11:41
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7408016319129358045L;

    /**
     * 错误码类型为字符串类型，共5位，分成两个部分：错误产生来源+四位数字编号 错误产生来源分为A/B/C A
     * 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题 B 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题 C
     * 表示错误来源于第三方，比如 CDN 服务出错，消息投递超时等问题 四位数字编号从 0001 到 9999，大类之间的步长间距预留 100
     */
    private int code;

    private String message;

    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result<Void> success() {
        return new Result<Void>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String message,T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static Result<Void> info(int code, String message) {
        return new Result<Void>(code, message);
    }

    public static Result<Void> info(ResultCode resultCode) {
        return new Result<Void>(resultCode.getCode(), resultCode.getMessage());
    }

    public static <T> Result<T> info(int code, String message, T data) {
        return new Result<T>(code, message, data);
    }

}
