package com.wkd.project.common.enums;

/**
 * @author wkd
 * @Date: 2020/4/26 16:28
 * @Description: 状态码接口
 */
public interface StatusCode {
    /**
     * 获取code
     *
     * @return
     */
    int getCode();

    /**
     * 获取信息
     *
     * @return
     */
    String getMessage();
}
