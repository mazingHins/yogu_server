/**
 * 
 */
package com.yogu.commons.api;

import javax.json.JsonArray;
import javax.json.JsonObject;

import com.yogu.commons.utils.JsonUtils;

/**
 * Api返回的结果信息定义 <br>
 * 建议是尽量使用 JsonObject、JsonArray
 * 
 * JFan 2014年12月22日 下午4:37:45
 */
public abstract class ApiResult<T> {

    public abstract T getResult(String response);

    //

    public static class ApiResultBuild {

        /**
         * @deprecated 不建议直接使用原始数据，请选择其他结果对象
         */
        public static ApiResult<String> STRING = new ApiResult<String>() {
            public String getResult(String response) {
                return response;
            }
        };
        public static ApiResult<JsonArray> JSON_ARRAY = new ApiResult<JsonArray>() {
            public JsonArray getResult(String response) {
                return JsonUtils.toJsonArray(response);
            }
        };
        public static ApiResult<JsonObject> JSON_OBJECT = new ApiResult<JsonObject>() {
            public JsonObject getResult(String response) {
                return JsonUtils.toJsonObject(response);
            }
        };

    }

}
