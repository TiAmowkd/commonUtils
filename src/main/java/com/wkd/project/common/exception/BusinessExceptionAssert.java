package com.wkd.project.common.exception;

import java.text.MessageFormat;

/**
 * @author wkd
 * @version 1.0.0
 * @className BusinessExceptionAssert.java
 * @description 断言判断接口
 * @createTime 2021-06-07 11:20
 */
public interface BusinessExceptionAssert extends IResponseEnum, Assert {

    /**
     * new出异常
     *
     * @param args
     * @return
     */
    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, msg, args);
    }

    /**
     * new出异常
     *
     * @param t
     * @param args
     * @return
     */
    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, t, msg, args);
    }
}
