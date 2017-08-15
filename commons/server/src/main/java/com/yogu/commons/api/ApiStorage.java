/**
 * 
 */
package com.yogu.commons.api;

import java.util.HashMap;
import java.util.Map;

import com.yogu.commons.utils.Args;

/**
 * 贮存对象 <br>
 * ApiStorage实例需要考虑时效、回收问题 <br>
 * 
 * JFan 2014年12月24日 上午11:31:03
 */
public class ApiStorage {

    private String response;
    private Map<Integer, Object> resultMap;// 跟随ApiStorage实例一起存活

    public ApiStorage(String response) {
        Args.notNull(response, "'response'");

        this.response = response;
        resultMap = new HashMap<Integer, Object>();// 里面的数量一般为个位数
    }

    @SuppressWarnings("unchecked")
    public <T> T getResult(ApiResult<T> apiResult) {
        int hashCode = apiResult.hashCode();
        Object obj = resultMap.get(hashCode);
        if (null != obj)
            return (T) obj;

        T value = apiResult.getResult(response);
        resultMap.put(hashCode, value);
        return value;
    }

    /**
     * @return response
     */
    public String getResponse() {
        return response;
    }

}
