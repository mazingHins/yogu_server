/**
 * 
 */
package com.yogu.commons.cache;

import com.yogu.commons.cache.memcached.MemcachedServiceImpl;
import com.yogu.commons.utils.Args;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * <br>
 * 
 * JFan 2015年2月5日 下午6:15:49
 */
//@Named
public final class CacheFactory {

    private CacheFactory() {
    }
//
//    // @Inject写在set方法上，静态属性无法注入
//    private static EhCacheServiceImpl localCache;

    // @Inject写在set方法上，静态属性无法注入
    private static MemcachedServiceImpl amassCache;

    @PostConstruct
    public void initialCheck() {
//        Args.notNull(localCache, "'localCache'");
        Args.notNull(amassCache, "'amassCache'");
    }

//    /**
//     * @param localCache 要设置的 localCache
//     */
//    @Inject
//    public void setLocalCache(EhCacheServiceImpl localCache) {
//        CacheFactory.localCache = localCache;
//    }

//    /**
//     * @return localCache
//     */
//    public static EhCacheServiceImpl getLocalCache() {
//        return localCache;
//    }

    /**
     * @param amassCache 要设置的 amassCache
     */
    @Inject
    public void setAmassCache(MemcachedServiceImpl amassCache) {
        CacheFactory.amassCache = amassCache;
    }

    /**
     * @return amassCache
     */
    public static MemcachedServiceImpl getAmassCache() {
        return amassCache;
    }

}
