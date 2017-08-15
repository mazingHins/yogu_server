/**
 * 
 */
package com.yogu.commons.api.interceptor;

import java.util.Map;
import java.util.SortedMap;

/**
 * 请求API时的拦截器， 作用在真正call http请求的时候：<br>
 * 之前：处理header、params<br>
 * 之后：处理responseText<br>
 * 
 * JFan 2014年12月23日 上午9:58:01
 */
public interface ApiInterceptor {

    public Map<String, String> requestBeforeHeader(String requestUrl, Map<String, String> headers, SortedMap<String, String> requestParams);

    public SortedMap<String, String> requestBeforeParams(String requestUrl, Map<String, String> headers, SortedMap<String, String> requestParams);

}
