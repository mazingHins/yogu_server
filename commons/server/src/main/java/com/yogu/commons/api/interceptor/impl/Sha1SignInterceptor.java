/**
 * 
 */
package com.yogu.commons.api.interceptor.impl;

import java.util.SortedMap;

import com.yogu.commons.api.interceptor.OutboundApiKey;
import com.yogu.commons.utils.UrlUtils;

/**
 * Sha1算法封签 <br>
 * 
 * JFan 2014年12月25日 下午6:46:27
 */
public class Sha1SignInterceptor extends AbsSignInterceptor {

    public Sha1SignInterceptor(String configKeyName) {
        super(configKeyName);
    }

    public Sha1SignInterceptor(String configKeyName, String[] removeParams, String[] notSginNotRemove) {
        super(configKeyName, removeParams, notSginNotRemove);
    }

    public Sha1SignInterceptor(String configKeyName, String keyParamName, String signParamName, String[] removeParams, String[] notSginNotRemove) {
        super(configKeyName, keyParamName, signParamName, removeParams, notSginNotRemove);
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.interceptor.impl.AbsSignInterceptor#makeSign(java.util.SortedMap,
     * com.vip.commons.api.interceptor.OutboundApiKey)
     */
    @Override
    protected String makeSign(SortedMap<String, String> params, OutboundApiKey apiKey) {
        String hash = UrlUtils.makeSha1Hash(params, apiKey.getApiSecret());
        return hash;
    }

}
