package com.yogu.commons.api.interceptor;

import java.io.Serializable;

/**
 * 对后端对接时，需要用到的 key、secret描述 <br>
 * 
 * JFan 2015年1月15日 下午1:43:25
 */
public class OutboundApiKey implements Serializable {

    private static final long serialVersionUID = -7349514363660171307L;

    private String apiKey;

    private String apiSecret;

    private String domain;// cc sdk 中不配置这项

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
