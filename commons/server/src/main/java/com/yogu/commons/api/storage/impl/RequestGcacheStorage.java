/**
 * 
 */
package com.yogu.commons.api.storage.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.storage.ApiRequestStorage;
import com.yogu.commons.concurrent.ExecutorFactory;

/**
 * 请求HTTP SERVER防穿 <br>
 * 在指定的秒数内，可选是否value弱引用
 * 
 * JFan 2015年1月15日 下午3:45:02
 */
public class RequestGcacheStorage extends ApiRequestStorage {

    private static final Logger logger = LoggerFactory.getLogger(RequestGcacheStorage.class);

    private ApiStorage nullStorage = new ApiStorage("");

    private Cache<String, ApiStorage> reqCache;
    private static LoadingCache<String, ApiStorage> respCache;// response Text 反序列化防穿 -- 值弱引用

    /**
     * 默认缓存60秒（防穿），弱引用
     */
    public RequestGcacheStorage() {
        this(60, true);
    }

    /**
     * 指定秒数内缓存（防穿），弱引用
     */
    public RequestGcacheStorage(int requestCacheSeconds) {
        this(requestCacheSeconds, true);
    }

    /**
     * 指定秒数内缓存（防穿），指定是否弱引用
     */
    public RequestGcacheStorage(int requestCacheSeconds, boolean weakValues) {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
        if (weakValues)
            cacheBuilder.weakValues();
        cacheBuilder.expireAfterWrite(requestCacheSeconds, TimeUnit.SECONDS);
        reqCache = cacheBuilder.build();

        respCache = CacheBuilder.newBuilder().weakValues().build(new CacheLoader<String, ApiStorage>() {
            public ApiStorage load(String response) throws Exception {
                logger.debug("Response 2 ApiStorage; responst: {}", response);
                return new ApiStorage(response);
            }
        });
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#cleanStorage(java.lang.String)
     */
    @Override
    public void cleanStorage(String storageKey) {
        logger.debug("Clean storageKey: {}", storageKey);
        reqCache.invalidate(storageKey);
        if (null != timeLimit)
            timeLimit.delCache(storageKey);
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#requestStorage(java.lang.String)
     */
    @Override
    public ApiStorage requestStorage(String storageKey) {
        return reqCache.getIfPresent(storageKey);
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#callServer(java.lang.String, java.util.concurrent.Callable)
     */
    @Override
    public ApiStorage callServer(final String storageKey, final Callable<String> httpCall) {
        try {
            // 包装， string 2 storage
            Callable<ApiStorage> callStorage;

            callStorage = new Callable<ApiStorage>() {
                public ApiStorage call() throws Exception {
                    // gcache -- 直接call
                    if (null == timeLimit) {
                        String response = httpCall.call();
                        // Storage Accept
                        return accept(response);

                        // time limit + gcache : 并行
                    } else {
                        Future<String> future = ExecutorFactory.submit(httpCall);
                        return readResponse(storageKey, future);
                    }
                }
            };

            // 最终经过这里防穿 + apiUtils本地存储
            ApiStorage storage = reqCache.get(storageKey, callStorage);

            // gcache load 的时候为null，则抛异常
            // 没有找到方法，使gcache不抛异常--查看了源代码
            // 》》》》临时做法，accept中判定不合法，则默认返回nullStorage这个实例，这里再做一次判定
            // 》》》》问题就是，这里是‘离线’地调用invalidate来清除cache，如果并发进来，有一定量会得到一个错误的结果
            // 》》》》还有一个问题，gcache存储的对象 是否 equal nullStorage ？？
            if (nullStorage.equals(storage)) {
                logger.info("Bad data, clear localStorage; storageKey: {}", storageKey);
                reqCache.invalidate(storageKey);
                return null;
            }

            return storage;
        } catch (Exception e) {
            logger.error("RequestStorage CallServer Error: {}", e.getMessage(), e);
            return null;
        }
    }

    // #### 时限策略

    /**
     * 写入‘备用存储’<br>
     * 默认是集中式缓存中，如果需要改成其他存储，请覆盖此方法
     */
    protected void writeCache(String storageKey, String response) {
        try {
            int expSeconds = timeLimit.expSeconds();
            timeLimit.setCache(storageKey, response, expSeconds);
        } catch (Exception e) {
            logger.error("Spare async storage, ERROR; message: {}", e.getMessage(), e);
        }
    }

    /**
     * 从‘备用存储’中拿数据<br>
     * 默认是集中式缓存中，如果需要改成其他存储，请覆盖此方法
     */
    protected String readCache(String storageKey) {
        return timeLimit.getCache(storageKey);
    }

    // 拿结果
    // 限时内：拿http-server的响应值
    // 超时了：尝试mc中取：有-返回、没有-等待http响应
    // --最后，拿到值了都要做存储
    private ApiStorage readResponse(String storageKey, Future<String> future) {
        long time = timeLimit.timeLimit();
        try {
            // 在限时内拿到了数据
            String response = future.get(time, TimeUnit.MILLISECONDS);
            logger.debug("Smooth data in {} ms. response: {}", time, response);
            ApiStorage storage = acceptStorage(storageKey, response, true);
            return storage;
        } catch (TimeoutException e) {
            logger.info("Data can not be too long, {}ms; storageKey: {}.", time, storageKey);
            return storageOrWait(storageKey, true, future, time);// 看看情况吧
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Failed get data from server; storageKey: {}, timeLimit: {}.", storageKey, time, e);
            return storageOrWait(storageKey, false, future, time);// 直接从mc获取，有就有，没有就没有
        }

    }

    // wait >> true: 尝试从mc获取，如果没有结果，则一直等待future；但是mc拿到值的同时htt也执行完了，则优先使用http的结果
    // wait >> false: 直接从mc拿数据，有就有，没就没
    private ApiStorage storageOrWait(String storageKey, boolean wait, Future<String> future, long timeLimit) {
        // >>> 考虑使用自旋的形式，使取mc和等待http call能够同时进行
        // >>> 因为有可能在刚刚发起mc查询，http call就已经结束了，但是mc又耗时
        // >>> 自旋的话，就回在不同的线程，call_ok需要使用volatile修饰符来同步标志位
        String response = readCache(storageKey);
        logger.debug("Values from Storage is '{}'", response);

        // 异步拿到了数据，当然是用新的数据了
        // 存储中没有，但是说明了要求等待
        // 拿到了结果，但是要检测一下http是否也结束了 -- 用最新http call的
        if (future.isDone() || (null == response && wait)) {
            logger.info("Need from the Source; storageKey: {}, timeLimit: {}.", storageKey, timeLimit);
            try {
                response = future.get();
                ApiStorage storage = acceptStorage(storageKey, response, true);// 备份性存储--异步的
                return storage;
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Once the request strategy fails; storageKey: {}, timeLimit: {}.", storageKey, timeLimit, e);
            }
        }

        // 异步，不影响原线程--等待拿到数据，然后存储值
        else
            theStorage(storageKey, future);

        // 没得选了
        return new ApiStorage(response);
    }

    private void theStorage(final String storageKey, final Future<String> future) {
        ExecutorFactory.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String response = future.get();
                    acceptStorage(storageKey, response, false);
                } catch (Exception e) {
                    logger.error("Async give data, Spare storage, ERROR; message: {}", e.getMessage(), e);
                }
            }
        });
    }

    private ApiStorage acceptStorage(final String storageKey, final String response, boolean async) {
        ApiStorage storage = accept(response);

        if (null != storage && nullStorage != storage) {// 合格的 -- 存储起来
            if (async)
                ExecutorFactory.submit(new Runnable() {
                    @Override
                    public void run() {
                        writeCache(storageKey, response);
                    }
                });
            else
                writeCache(storageKey, response);
        }

        return storage;
    }

    /**
     * 将response text转换成ApiStorage -- 防穿<br>
     * 转换之后，并不是马上返回，而是经过‘过滤’之后<br>
     * 不合格的将返回null
     */
    private ApiStorage accept(String response) {
        if (null == response) {
            logger.debug("How can provide an empty to me."); // yes; use debug, not warn|error.
            return null;
        }
        // 2 result
        ApiStorage storage;
        try {
            storage = respCache.get(response);
        } catch (ExecutionException e) {
            logger.error("Response 2 Storage, ERROR; message: {}", e.getMessage(), e);
            storage = new ApiStorage(response);
        }
        // accept
        if (null != storage && null != resourceAccept)
            storage = resourceAccept.accept(storage);
        return (null == storage ? nullStorage : storage);
    }

}
