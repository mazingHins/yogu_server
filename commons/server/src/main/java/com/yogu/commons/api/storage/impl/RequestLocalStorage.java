/**
 * 
 */
package com.yogu.commons.api.storage.impl;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.api.ApiStorage;
import com.yogu.commons.api.storage.ApiRequestStorage;

/**
 * 请求Http Server的执行器 <br>
 * 具备防穿功能<br>
 * 以及本地缓存<br>
 * 
 * JFan 2014年12月23日 下午5:35:37
 * @deprecated 此类还没有完善，请使用RequestGcacheStorage
 */
public class RequestLocalStorage extends ApiRequestStorage {

    private static final Logger logger = LoggerFactory.getLogger(RequestLocalStorage.class);

    private static ReferenceQueue<ApiStorage> referenceQueue = new CleanReferenceQueue();// static，多个实例共用同一个，因为做的事情很单一

    private ConcurrentMap<String, Future<String>> stast;// >>>>>>>>> 需要设置清理
    private Map<String, WeakReference<ApiStorage>> cache;

    // 暂时不开放使用此类，这两个构造方法私有
    private RequestLocalStorage() {
        this(256);
    }

    private RequestLocalStorage(int initialCapacity) {
        stast = new ConcurrentHashMap<String, Future<String>>();
        cache = new ConcurrentHashMap<>(256);// 使用 ReferenceQueue 清理 Key
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#cleanStorage(java.lang.String)
     */
    @Override
    public void cleanStorage(String storageKey) {
        cache.remove(storageKey);
        stast.remove(storageKey);
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#requestStorage(java.lang.String)
     */
    @Override
    public ApiStorage requestStorage(String storageKey) {
        WeakReference<ApiStorage> reference = cache.get(storageKey);
        if (null == reference)
            return null;
        return reference.get();
    }

    /*
     * （非 Javadoc）
     * 
     * @see com.vip.commons.api.storage.ApiRequestStorage#callServer(java.lang.String, java.util.concurrent.Callable)
     */
    @Override
    public ApiStorage callServer(String storageKey, Callable<String> httpCall) {
        String response = null;

        FutureTask<String> future = new FutureTask<String>(httpCall);
        Future<String> oldFuture = stast.putIfAbsent(storageKey, future);

        try {
            if (null == oldFuture) {
                future.run();
                response = future.get();
            } else {
                future = null;
                response = oldFuture.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApiStorage storage = new ApiStorage(response);
        if (null != response)
            cache.put(storageKey, new CleanWeakReference(storage, referenceQueue));
        return storage;
    }

    // ####
    // ##
    // ####

    /**
     * 扩展弱引用，多持有response <br>
     * 当ApiStorage被回收时，引用队列通过response字段，清理cache中过期的内容
     * 
     * JFan 2015年1月15日 上午11:43:41
     */
    class CleanWeakReference extends WeakReference<ApiStorage> {

        private ApiStorage response;

        public CleanWeakReference(ApiStorage response, ReferenceQueue<ApiStorage> q) {
            super(response, q);
            this.response = response;
        }

        public void cleanKey() {
            logger.info("Clean Response Storage.");
            cache.remove(response);
        }

    }

    /**
     * 引用队列 <br>
     * 扩展：构建时，同时创建一个守护线程，用于自己队列中的数据，并执行清理
     * 
     * JFan 2015年1月15日 下午12:16:01
     */
    static class CleanReferenceQueue extends ReferenceQueue<ApiStorage> {

        public CleanReferenceQueue() {
            Thread thread = new Thread(new CleanRunnable(this), "AU_clean_storage");
            thread.setDaemon(true);
            thread.start();
        }

    }

    static class CleanRunnable implements Runnable {

        private CleanReferenceQueue queue;

        public CleanRunnable(CleanReferenceQueue queue) {
            this.queue = queue;
        }

        /*
         * （非 Javadoc）
         * 
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
            while (true) {
                Reference<? extends ApiStorage> refe = null;
                // refe = queue.poll();
                try {
                    refe = queue.remove(30000);
                } catch (Exception e) {
                    // ignore
                }

                if (refe instanceof CleanWeakReference)
                    ((CleanWeakReference) refe).cleanKey();
            }
        }

    }

}
