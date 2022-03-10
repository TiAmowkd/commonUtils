package com.wkd.project.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wkd.project.common.annotation.NotControllerResponseAdvice;
import com.wkd.project.common.dto.Result;
import com.wkd.project.common.enums.ResultCode;
import com.wkd.project.common.exception.BusinessException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author wkd
 * @Date: 2020/4/26 16:02
 * @Description: 使用@RestControllerAdvice(basePackages = {"com.wkd.project"})
 * 自动扫描了所有指定包下的controller，在Response时进行统一处理
 */
@RestControllerAdvice(basePackages = {"com.wkd.project"})
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 重写supports方法，也就是说，当返回类型已经是Result了，那就不需要封装了
     * 当不等于Result时才进行调用beforeBodyWrite方法，跟过滤器的效果是一样的
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // response是Result类型，或者注释了NotControllerResponseAdvice都不进行包装
        // methodParameter.getParameterType().isAssignableFrom(Result.class) 包装完成为true
        return !(methodParameter.getParameterType().isAssignableFrom(Result.class)
                || methodParameter.hasMethodAnnotation(NotControllerResponseAdvice.class));

    }

    /**
     * 最后重写我们的封装方法beforeBodyWrite，注意除了String的返回值有点特殊，无法直接封装成json
     * 我们需要进行特殊处理，其他的直接new Result(data);就ok了
     *
     * @param data
     * @param returnType
     * @param mediaType
     * @param aClass
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVo里后转换为json串进行返回
                return objectMapper.writeValueAsString(Result.info(1000, data.toString()));
            } catch (JsonProcessingException e) {
                throw new BusinessException(ResultCode.RESPONSE_PACK_ERROR, e.getMessage());
            }
        }
        // 否则直接包装成ResultVo返回
        return Result.success(data);
    }
}
