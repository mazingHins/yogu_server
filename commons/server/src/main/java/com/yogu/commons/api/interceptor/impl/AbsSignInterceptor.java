/**
 * 
 */
package com.yogu.commons.api.interceptor.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import com.yogu.commons.api.interceptor.ApiInterceptor;
import com.yogu.commons.api.interceptor.OutboundApiKey;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.UrlUtils;

/**
 * 基本封签类 <br>
 * 主要支持：<br>
 * 1：计算签名前，可以移除不需要的参数，removeParams<br>
 * 2：计算签名前移除，算完之后再填回来，notSginNotRemove<br>
 * <br>
 * <b>如果有参数不参与验签，或者需要删除，那么不能放在requestUrl上</b><br>
 * 
 * JFan 2014年12月25日 下午6:46:17
 */
public abstract class AbsSignInterceptor implements ApiInterceptor {

    private String keyParamName;
    private String signParamName;
    private String configKeyName;

    private String[] removeParams;
    private String[] notSginNotRemove;

    public AbsSignInterceptor(String configKeyName) {
        this(configKeyName, "SecurityConstant.API_KEY", "SecurityConstant.API_SIGN", null, null);
    }

    public AbsSignInterceptor(String configKeyName, String[] removeParams, String[] notSginNotRemove) {
        this(configKeyName, "SecurityConstant.API_KEY", "SecurityConstant.API_SIGN", removeParams, notSginNotRemove);
    }

    public AbsSignInterceptor(String configKeyName, String keyParamName, String signParamName, String[] removeParams, String[] notSginNotRemove) {
        Args.notNull(configKeyName, "'configKeyName'");
        Args.notNull(signParamName, "'signParamName'");
        Args.notNull(keyParamName, "'keyParamName'");

        this.notSginNotRemove = notSginNotRemove;
        this.configKeyName = configKeyName;
        this.signParamName = signParamName;
        this.keyParamName = keyParamName;
        this.removeParams = removeParams;
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.interceptor.ApiInterceptor#requestBeforeHeader(java.lang.String, java.util.Map,
     * java.util.SortedMap)
     */
    @Override
    public Map<String, String> requestBeforeHeader(String requestUrl, Map<String, String> headers, SortedMap<String, String> requestParams) {
        return headers;
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.interceptor.ApiInterceptor#requestBeforeParams(java.lang.String, java.util.Map,
     * java.util.SortedMap)
     */
    @Override
    public SortedMap<String, String> requestBeforeParams(String requestUrl, Map<String, String> headers, SortedMap<String, String> requestParams) {
        Args.notNull(configKeyName, "'configKeyName'");
        OutboundApiKey apiKey = key();// TODO OutboundApiKeyConfig.getApiKey(configKeyName);
        Args.notNull(apiKey, "'OutboundApiKey'");

        // 不需要的参数，不参与验签
        if (null != requestParams && ArrayUtils.isNotEmpty(removeParams))
            for (String removeParam : removeParams)
                requestParams.remove(removeParam);

        // 不参与验签的参数，但是又不删除
        Map<String, String> nnMap = null;
        if (ArrayUtils.isNotEmpty(notSginNotRemove)) {
            nnMap = new HashMap<String, String>();
            for (String key : notSginNotRemove) {
                String value = requestParams.get(key);
                if (null == value)
                    continue;
                nnMap.put(key, value);
                requestParams.remove(key);
            }
        }

        // sign
        requestParams.put(keyParamName, apiKey.getApiKey());

        requestParams = privateProcessing(requestParams);

        // 所有参数用作签名（包括URL上的参数）
        SortedMap<String, String> params = UrlUtils.mergeQuery2Map(requestUrl, requestParams);
        String hash = makeSign(params, apiKey);

        requestParams.put(signParamName, hash);

        // notSginNotRemove back
        if (null != nnMap)
            for (Entry<String, String> entry : nnMap.entrySet())
                requestParams.put(entry.getKey(), entry.getValue());

        return requestParams;
    }

	// 为了不让代码 出现警告
	private OutboundApiKey key() {
		return null;
	}

    /**
     * 在计算签名之前再做一次处理，如果有需要，请覆盖此方法 <br>
     * 操作的值（+-）会参与验签
     */
    protected SortedMap<String, String> privateProcessing(SortedMap<String, String> params) {
        return params;
    }

    /**
     * 计算签名<br>
     * params 中包括 URL上的参数
     */
    protected abstract String makeSign(SortedMap<String, String> params, OutboundApiKey apiKey);

}
