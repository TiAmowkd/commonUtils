package com.wkd.project.config;


import com.wkd.project.common.dto.Result;
import com.wkd.project.common.enums.ResultCode;
import com.wkd.project.common.exception.BusinessException;
import com.wkd.project.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author wkd
 * @version 1.0.0
 * @className GlobalExceptionHandler.java
 * @description 统一异常封装
 * @createTime 2021-11-02 14:19
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class, Exception.class})
    @ResponseBody
    public <T> Result<T> handLerException(Exception ex) {
        if (ex instanceof BusinessException) {
            log.warn("［全局业务异常］");
            ResultCode resultCode = (ResultCode) ((BusinessException) ex).getResponseEnum();
            if (StringUtils.isEmpty(ex.getMessage())) {
                return Result.info(resultCode.getCode(), resultCode.getMessage());
            } else {
                return Result.info(resultCode.getCode(), ex.getMessage());
            }
        } else if (ex instanceof MethodArgumentNotValidException) {
            log.warn("［方法参数无效异常］");
            MethodArgumentNotValidException methodArgumentNotVaLidException = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = methodArgumentNotVaLidException.getBindingResult();
            String msg = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).distinct()
                    .collect(Collectors.joining(","));
            return Result.info(ResultCode.VALIDATE_ERROR.getCode(), msg);
        } else if (ex instanceof BindException) {
            log.warn("［参数绑定异常］");
            BindException bindException = (BindException) ex;
            String msg = bindException.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).distinct().collect(Collectors.joining(","));
            return Result.info(ResultCode.VALIDATE_ERROR.getCode(), msg);
        } else {
            log.error(ex.getMessage());
            //发送报警邮件
            return Result.info(ResultCode.SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public <T> Result<T> handleAccessDeniedException(AccessDeniedException exception) {
        log.warn("［访问权限受限异常］", exception);
        return Result.info(ResultCode.USER_ACCOUNT_DISABLE);
    }
}
