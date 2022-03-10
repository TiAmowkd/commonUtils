package com.wkd.project.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wkd
 * @version 1.0.0
 * @className User.java
 * @description 加入此注解将不进行同一包装体返回
 * @createTime 2020/4/26 16:13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerResponseAdvice {
}

