/**
 * 
 */
package com.yogu.commons.api.timelimit;

import com.yogu.commons.cache.BaseCacheService;
import com.yogu.commons.cache.CacheFactory;

/**
 * 时限获取接口 <br>
 * 定义成接口是因为可能有不同的实现，例如：固定时间、动态运算，更多的是配置中心配置 <br>
 * 
 * JFan 2015年2月9日 上午10:51:32
 */
public abstract class TimeLimit {

    /**
     * 时限（毫秒）
     */
    public abstract long timeLimit();

    /**
     * ‘超时存储’中存储多久（秒）<br>
     * 有需要修改的话，请自行覆盖方法
     */
    public int expSeconds() {
        return BaseCacheService.EXP_7_D;
    }

    /**
     * 设置‘超时存储’<br>
     * 有需要修改的话，请自行覆盖方法
     */
    public void setCache(String storageKey, String response, int expSeconds) {
        CacheFactory.getAmassCache().set(storageKey, response, expSeconds);
    }

    /**
     * 从‘超时存储’中获取内容<br>
     * 有需要修改的话，请自行覆盖方法
     */
    public String getCache(String storageKey) {
        return CacheFactory.getAmassCache().get(storageKey);
    }

    /**
     * 从‘超时存储’中删除已经存储的内容<br>
     * 有需要修改的话，请自行覆盖方法
     */
    public String delCache(String storageKey) {
        return CacheFactory.getAmassCache().get(storageKey);
    }

}
