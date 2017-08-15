/**
 * 
 */
package com.yogu.commons.api.storage;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.accept.StorageAccept;
import com.yogu.commons.api.timelimit.TimeLimit;

/**
 * 对API请求之前、之后 进行存储判断 <br>
 * 可以做：<br>
 * 1：对相同的请求参数进行缓存（responseText）<br>
 * 2：对相同的responseText反序列化后的对象进行缓存<br>
 * 
 * JFan 2014年12月23日 下午5:33:39
 */
public abstract class ApiRequestStorage {

    private int nullHashCode = "~Null~".hashCode();

    protected StorageAccept resourceAccept;
    protected TimeLimit timeLimit;

    /**
     * 返回一个KEY，用于后面的存储key<br>
     * 如果不想做存储，可返回null<br>
     * 这个是默认的算法，如果想自己指定，请在使用ApiUtils的时候指定<br>
     */
    public String storageKey(String requestUrl, Map<String, String> headers, SortedMap<String, String> params) {
        int urlhc = (null == requestUrl ? nullHashCode : requestUrl.hashCode());
        int headerhc = map2hc(null == headers ? null : new TreeMap<String, String>(headers));
        int paramhc = map2hc(params);
        return (urlhc + "_" + headerhc + "_" + paramhc);
    }

    /**
     * 根据请求条件（url、header、param）清理缓存
     */
    public void cleanStorage(String requestUrl, Map<String, String> headers, SortedMap<String, String> params) {
        String storageKey = storageKey(requestUrl, headers, params);
        cleanStorage(storageKey);
    }

    /**
     * 根据KEY，清理缓存
     */
    public abstract void cleanStorage(String storageKey);

    /**
     * 检查有没有：已经存储好的内容
     */
    public abstract ApiStorage requestStorage(String storageKey);

    /**
     * 请求服务任务
     * 
     * 该方法必须返回值，除非storageKey()返回null
     */
    public abstract ApiStorage callServer(String storageKey, Callable<String> httpCall);

    /**
     * @return timeLimit
     */
    public TimeLimit getTimeLimit() {
        return timeLimit;
    }

    /**
     * @param timeLimit 要设置的 timeLimit
     */
    public void setTimeLimit(TimeLimit timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * @return resourceAccept
     */
    public StorageAccept getResourceAccept() {
        return resourceAccept;
    }

    /**
     * @param resourceAccept 要设置的 resourceAccept
     */
    public void setResourceAccept(StorageAccept resourceAccept) {
        this.resourceAccept = resourceAccept;
    }

    // ####
    // ## private func
    // ####

    private int map2hc(SortedMap<String, String> map) {
        if (null == map)
            return nullHashCode;
        StringBuilder sb = new StringBuilder();
        for (Entry<String, String> entry : map.entrySet())
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        return sb.toString().hashCode();
    }

}
