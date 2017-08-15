package com.yogu.commons.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yogu.commons.api.ApiResource;
import com.yogu.commons.api.ApiResult;
import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.interceptor.ApiInterceptor;
import com.yogu.commons.api.storage.ApiRequestStorage;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.HttpClientUtils.HCMethod;

public final class ApiUtils {

    private ApiUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);

    /**
     * 请求一个API资源<br>
     * <br>
     * 
     * @param apiResult 所需要得到的结果类型
     * @param apiResource 所请求的资源
     * @param pathParams 资源中PathInfo包含的参数（如果有）
     * @return
     */
    public static <T> T call(ApiResult<T> apiResult, ApiResource apiResource, Object... pathParams) {
        return execute(null, apiResult, apiResource, null, null, null, null, null, pathParams);
    }

    public static <T> T call(String storageKey, ApiResult<T> apiResult, ApiResource apiResource, Object... pathParams) {
        return execute(storageKey, apiResult, apiResource, null, null, null, null, null, pathParams);
    }

    /**
     * 请求一个API资源<br>
     * <br>
     * 
     * @param apiResult 所需要得到的结果类型
     * @param apiResource 所请求的资源
     * @param params Http Params
     * @param pathParams 资源中PathInfo包含的参数（如果有）
     * @return
     */
    public static <T> T call(ApiResult<T> apiResult, ApiResource apiResource, SortedMap<String, String> params, Object... pathParams) {
        return execute(null, apiResult, apiResource, null, null, null, null, params, pathParams);
    }

    public static <T> T call(String storageKey, ApiResult<T> apiResult, ApiResource apiResource, SortedMap<String, String> params, Object... pathParams) {
        return execute(storageKey, apiResult, apiResource, null, null, null, null, params, pathParams);
    }

    /**
     * 请求一个API资源<br>
     * <br>
     * 
     * @param apiResult 所需要得到的结果类型
     * @param apiResource 所请求的资源
     * @param headers Http Header
     * @param params Http Params
     * @param pathParams 资源中PathInfo包含的参数（如果有）
     * @return
     */
    public static <T> T call(ApiResult<T> apiResult, ApiResource apiResource, Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(null, apiResult, apiResource, null, null, null, headers, params, pathParams);
    }

    public static <T> T call(String storageKey, ApiResult<T> apiResult, ApiResource apiResource, Map<String, String> headers, SortedMap<String, String> params,
            Object... pathParams) {
        return execute(storageKey, apiResult, apiResource, null, null, null, headers, params, pathParams);
    }

    /**
     * 请求一个API资源<br>
     * <br>
     * 
     * @param apiResult 所需要得到的结果类型
     * @param apiResource 所请求的资源
     * @param timeOut 超时时间（毫秒）
     * @param headers Http Header
     * @param params Http Params
     * @param pathParams 资源中PathInfo包含的参数（如果有）
     * @return
     */
    public static <T> T call(ApiResult<T> apiResult, ApiResource apiResource, Integer timeOut//
            , Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(null, apiResult, apiResource, timeOut, null, null, headers, params, pathParams);
    }

    public static <T> T call(String storageKey, ApiResult<T> apiResult, ApiResource apiResource, Integer timeOut//
            , Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(storageKey, apiResult, apiResource, timeOut, null, null, headers, params, pathParams);
    }

    /**
     * 请求一个API资源<br>
     * <br>
     * 
     * @param apiResult 所需要得到的结果类型
     * @param apiResource 所请求的资源
     * @param timeOut 超时时间（毫秒）
     * @param requestEncode 请求参数编码
     * @param headers Http Header
     * @param params Http Params
     * @param pathParams 资源中PathInfo包含的参数（如果有）
     * @return
     */
    public static <T> T call(ApiResult<T> apiResult, ApiResource apiResource, Integer timeOut//
            , String requestEncode, Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(null, apiResult, apiResource, timeOut, requestEncode, null, headers, params, pathParams);
    }

    public static <T> T call(String storageKey, ApiResult<T> apiResult, ApiResource apiResource, Integer timeOut//
            , String requestEncode, Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(storageKey, apiResult, apiResource, timeOut, requestEncode, null, headers, params, pathParams);
    }

    /**
     * 请求一个API资源<br>
     * <br>
     * 
     * @param apiResult 所需要得到的结果类型
     * @param apiResource 所请求的资源
     * @param timeOut 超时时间（毫秒）
     * @param requestEncode 请求参数编码
     * @param responseEncode 响应内容编码
     * @param headers Http Header
     * @param params Http Params
     * @param pathParams 资源中PathInfo包含的参数（如果有）
     * @return
     */
    public static <T> T call(ApiResult<T> apiResult, ApiResource apiResource, Integer timeOut//
            , String requestEncode, String responseEncode, Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(null, apiResult, apiResource, timeOut, requestEncode, responseEncode, headers, params, pathParams);
    }

    public static <T> T call(String storageKey, ApiResult<T> apiResult, ApiResource apiResource, Integer timeOut//
            , String requestEncode, String responseEncode, Map<String, String> headers, SortedMap<String, String> params, Object... pathParams) {
        return execute(storageKey, apiResult, apiResource, timeOut, requestEncode, responseEncode, headers, params, pathParams);
    }

    // ####
    // ## static private func
    // ####

    /**
     * 请求资源<br>
     */
    private static <T> T execute(final String key, final ApiResult<T> result, final ApiResource resource, final Integer timeOut//
            , final String reqEncode, final String respEncode, final Map<String, String> headers, final SortedMap<String, String> params, Object... pathParams) {
        Args.notNull(resource, "'apiResource'");
        Args.notNull(result, "'apiResult'");

        final String url = resource.getRequestURL(pathParams);
        ApiRequestStorage reqStorage = resource.getApiRequestStorage();

        String response = null;
        // 没有要求存储，直接回源；没有防穿（以后想办法加上定制化防穿）
        if (null == reqStorage) {
            response = httpCall(resource.getApiInterceptor(), resource.getMethod(), url, timeOut, reqEncode, respEncode, headers, params);
        }
        // 资源存储
        else {
            // 选用key
            String storageKey = key;// 优先使用外部设定的
            if (null == storageKey) {
                // 注意：storageKey是使用原始参数计算得到的
                storageKey = reqStorage.storageKey(url, headers, params);
                // 你使用了 时限器，但是key却是默认计算的，这样从集中存储中就不容易看出key是干什么用的。所以提醒你使用自定的key（参考ApiUtils.call的第一个str参数）
                if (null != reqStorage.getTimeLimit())
                    logger.warn("Use TimeLimit, but storageKey is default. Please use custom key. Url: {}", url);
            }

            // 回源
            if (null == storageKey) {
                // storageKey还是null，说明 apiResource不希望存储这次请求 -- 直接回源
                response = httpCall(resource.getApiInterceptor(), resource.getMethod(), url, timeOut, reqEncode, respEncode, headers, params);
            }
            // storage
            else {
                ApiStorage storage;
                // request Before Storage
                logger.debug("Retrieval storage response. key: {}", storageKey);
                storage = reqStorage.requestStorage(storageKey);
                if (null != storage) {
                    logger.info("Request use Storage; Url: {}, Headers: {}, Params: {}.", url, headers, params);
                    logger.debug("Request use StorageValue; Url: {}, Response: {}", url, storage.getResponse());
                }
                // 存储 + 防穿
                else {
                    storage = reqStorage.callServer(storageKey, new Callable<String>() {
                        public String call() throws Exception {
                            return httpCall(resource.getApiInterceptor(), resource.getMethod(), url, timeOut, reqEncode, respEncode, headers, params);
                        }
                    });
                }

                return (null == storage ? null : storage.getResult(result));
            }
        }

        // ----
        // ---- 进入到这里，说明是直接调用http server
        // ----

        // 有可能没有正常响应，如果是4xx会返回null
        if (null == response)
            return null;

        // response Storage
        ApiStorage storage;
        try {
            // 防止相同的对象（一样的 response text）重复地反序列化
            // cache中的value是弱引用，具备防穿
            storage = responseCache.get(response);
        } catch (ExecutionException e) {
            logger.error("Response to Storage, ERROR; message: {}", e.getMessage(), e);
            storage = new ApiStorage(response);
        }
        return storage.getResult(result);
    }

    /**
     * 执行请求的方法<br>
     * 入参为签名前的数据<br>
     * 在执行call http请求之前，有可能headers、params中的内容会改变，因为需要封签
     */
    private static String httpCall(ApiInterceptor interceptor, HCMethod hcmethod, String requestUrl, Integer timeOut//
            , String requestEncode, String responseEncode, Map<String, String> headers, SortedMap<String, String> params) {
        logger.debug("Started to take data, on the server.");

        // request Before Interceptor
        if (null != headers && null != interceptor) {
            logger.debug("Request Header Before; Url: {}, headers: {}.", requestUrl, headers);
            headers = interceptor.requestBeforeHeader(requestUrl, headers, params);
            logger.debug("Headers Process After; Url: {}, headers: {}.", requestUrl, headers);
        }
        if (null != params && null != interceptor) {
            logger.debug("Request Params Before; Url: {}, params {}.", requestUrl, params);
            params = interceptor.requestBeforeParams(requestUrl, headers, params);
            logger.debug("Params Process After; Url: {}, params {}.", requestUrl, params);
        }

        // call http server
        HttpResult result = HttpClientUtils.callHttp(hcmethod, requestUrl, timeOut, headers, params, requestEncode, responseEncode, null, null, null);
        if (!result.isReqOK()) {
            int code = result.getStatusCode();
            logger.warn("Get a normal '{}' response. url: {}, headers: {}, params: {}", code, requestUrl, headers, params);
            if (HttpStatus.SC_BAD_REQUEST <= code && code <= HttpStatus.SC_FAILED_DEPENDENCY)// 400 ~ 424
                return null;
        }
        String response = result.getResponse();
        logger.debug("Response Text; url: {}, headers: {}, params: {}, response: {}", requestUrl, headers, params, response);
        return response;
    }

    // response Text 反序列化防穿 -- 值弱引用
    private static LoadingCache<String, ApiStorage> responseCache = CacheBuilder.newBuilder().weakValues().build(new CacheLoader<String, ApiStorage>() {
        public ApiStorage load(String response) throws Exception {
            logger.debug("Response to ApiStorage; responst: {}", response);
            return new ApiStorage(response);
        }
    });

    // ####
    // ## static public utilFunc
    // ##

    public static String toKey(SortedMap<String, String> params) {
        return toKey(params, '_', true);
    }

    public static String toKey(SortedMap<String, String> params, char sepa) {
        return toKey(params, sepa, true);
    }

    public static String toKey(SortedMap<String, String> params, char sepa, boolean ctnBlandVal) {
        if (null == params || params.isEmpty())
            return "null";
        boolean one = true;
        StringBuilder key = new StringBuilder();
        Set<Entry<String, String>> entrySet = params.entrySet();
        for (Entry<String, String> entry : entrySet) {
            if (ctnBlandVal && StringUtils.isBlank(entry.getValue()))
                continue;
            if (!one)
                key.append(sepa);
            key.append(entry.getValue());
            one = false;
        }
        return key.toString();
    }

}
