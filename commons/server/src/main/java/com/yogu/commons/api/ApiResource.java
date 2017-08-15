/**
 * 
 */
package com.yogu.commons.api;

import java.util.Map;
import java.util.SortedMap;

import com.yogu.commons.api.accept.StorageAccept;
import com.yogu.commons.api.interceptor.ApiInterceptor;
import com.yogu.commons.api.storage.ApiRequestStorage;
import com.yogu.commons.api.timelimit.TimeLimit;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.HttpClientUtils.HCMethod;

/**
 * 描述一个API资源 <br>
 * 
 * JFan 2015年1月4日 下午6:15:55
 */
public class ApiResource {

    /** 域信息 */
    private ApiDomain domain;
    /** 请求路径：PathInfo */
    private String pathInfo;
    /** 请求HTTP Method */
    private HCMethod method;
    /** 域、资源拦截器 */
    private ApiInterceptor interceptor;

    private ApiRequestStorage requestStorage;

    public ApiResource(ApiDomain domain, String pathInfo, HCMethod method) {
        this(domain, pathInfo, method, null, null);
    }

    public ApiResource(ApiDomain domain, String pathInfo, HCMethod method, ApiInterceptor interceptor) {
        this(domain, pathInfo, method, interceptor, null);
    }

    public ApiResource(ApiDomain domain, String pathInfo, HCMethod method, ApiRequestStorage requestStorage) {
        this(domain, pathInfo, method, null, requestStorage);
    }

    public ApiResource(ApiDomain domain, String pathInfo, HCMethod method, ApiInterceptor interceptor, ApiRequestStorage requestStorage) {
        Args.notNull(domain, "'domain'");
        Args.notNull(method, "'method'");
        String tmp = StringUtils.trimToNull(pathInfo);
        Args.notNull(tmp, "'pathInfo'");

        this.pathInfo = tmp;
        this.domain = domain;
        this.method = method;
        this.interceptor = interceptor;
        this.requestStorage = requestStorage;
    }

    /**
     * 清理已经存储的缓存
     */
    public void cleanCache(String storageKey) {
        ApiRequestStorage storage = getApiRequestStorage();
        if (null == storage)
            return;

        storage.cleanStorage(storageKey);
    }

    /**
     * 清理已经存储的缓存
     */
    public void cleanCache(SortedMap<String, String> params, Object... pathParams) {
        cleanCache(null, params, pathParams);
    }

    public void cleanCache(Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        ApiRequestStorage storage = getApiRequestStorage();
        if (null == storage)
            return;

        String requestUrl = getRequestURL(pathParams);
        storage.cleanStorage(requestUrl, headers, params);
    }

    /**
     * %s 字符串类型<br>
     * %c 字符类型<br>
     * %b 布尔类型<br>
     * %d 整数类型（十进制）<br>
     * %x 整数类型（十六进制）<br>
     * %o 整数类型（八进制）<br>
     * %f 浮点类型<br>
     * %a 十六进制浮点类型<br>
     * %e 指数类型<br>
     * %g 通用浮点类型（f和e类型中较短的）<br>
     * 
     * @return pathInfo
     */
    public String getRequestURL(Object... pathParams) {
        String path = pathInfo;
        if (ArrayUtils.isNotEmpty(pathParams))
            path = String.format(path, pathParams);
        return domain.getHostUrl() + path;
    }

    public ApiRequestStorage getApiRequestStorage() {
        if (null != requestStorage)
            return requestStorage;
        return domain.getRequestStorage();
    }

    public HCMethod getMethod() {
        return method;
    }

    public ApiInterceptor getApiInterceptor() {
        if (null != interceptor)
            return interceptor;
        return domain.getInterceptor();
    }

    // ####
    // ## set func
    // ####

    public void setResourceAccept(StorageAccept resourceAccept) {
        // 注意，这里只设置到 自己的storeage，没有设置到domain的stotage
        Args.notNull(requestStorage, "'requestStorage'");
        requestStorage.setResourceAccept(resourceAccept);
    }

    /**
     * 固定时限
     */
    public void setTimeLimit(final long timeLimit) {
        setTimeLimit(new TimeLimit() {
            public long timeLimit() {
                return timeLimit;
            }
        });
    }

    public void setTimeLimit(TimeLimit timeLimit) {
        // 注意，这里只设置到 自己的storeage，没有设置到domain的stotage
        Args.notNull(requestStorage, "'requestStorage'");
        requestStorage.setTimeLimit(timeLimit);
    }

    public void setRequestStorage(ApiRequestStorage requestStorage) {
        this.requestStorage = requestStorage;
    }

	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

}
