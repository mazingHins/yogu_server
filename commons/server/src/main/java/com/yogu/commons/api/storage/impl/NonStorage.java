/**
 * 
 */
package com.yogu.commons.api.storage.impl;

import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.Callable;

import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.storage.ApiRequestStorage;

/**
 * 没有任何存储 <br>
 * 
 * JFan 2015年1月22日 下午12:54:31
 */
public class NonStorage extends ApiRequestStorage {

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#storageKey(java.lang.String, java.util.Map,
     * java.util.SortedMap)
     */
    @Override
    public String storageKey(String requestUrl, Map<String, String> headers, SortedMap<String, String> params) {
        return null;
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#requestStorage(java.lang.String)
     */
    @Override
    public ApiStorage requestStorage(String storageKey) {
        return null;
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#cleanStorage(java.lang.String)
     */
    @Override
    public void cleanStorage(String storageKey) {
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#callServer(java.lang.String, java.util.concurrent.Callable)
     */
    @Override
    public ApiStorage callServer(String storageKey, Callable<String> httpCall) {
        return null;
    }

}
