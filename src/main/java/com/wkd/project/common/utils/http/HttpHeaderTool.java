package com.wkd.project.common.utils.http;

/**
 * @author wkd
 * @version 1.0.0
 * @className HttpHeaderTool.java
 * @description TODO
 * @createTime 2022-03-10 16:00
 */
public class HttpHeaderTool {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String HEADER_PREFIX = "Basic ";
    public static final String PORTAL_ID = "PortalId";
    public static final String FAWKES_ANALYSIS = "Fawkes-Analysis";

    public HttpHeaderTool() {
    }



    public static enum HeaderEnum {
        /**
         * openfeign发送请求头配置
         */
        AUTH_USERNAME("X-AUTH-USERNAME", "用户名称从全局用户获取"),
        IP_ADDRESS("X-IP-ADDRESS", "ip地址"),
        TX_ID("X-TX-ID", "全局id"),
        CURRENT_USER("CurrentUser", "全局用户");

        private String key;
        private String desc;

        public String getKey() {
            return this.key;
        }

        public String getDesc() {
            return this.desc;
        }

        private HeaderEnum(final String key, final String desc) {
            this.key = key;
            this.desc = desc;
        }
    }
}
