package com.yogu.commons.api;

import com.yogu.commons.api.interceptor.ApiInterceptor;
import com.yogu.commons.api.storage.ApiRequestStorage;

/**
 * 域信息描述（获取方式） <br>
 * 
 * JFan 2015年1月4日 下午6:22:47
 */
public interface ApiDomain {

    /**
     * 获取 域信息（请保证1:末尾不含/ 2：不为空）
     */
    public String getHostUrl();

    /**
     * 得到 interceptor
     */
    public ApiInterceptor getInterceptor();

    /**
     * 得到 requestStorage
     */
    public ApiRequestStorage getRequestStorage();

}
