/**
 * 
 */
package com.yogu.remote.cache;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Store;

import com.yogu.remote.store.StoreRemoteService;

/**
 * 对Store对象的一次性局部缓存
 * 
 * 不可以放在成员变量中，应为这个对象使用完之后是要抛弃等待回收的
 * 
 * @author jfan 2016年5月25日 上午9:56:41
 */
public class StoreOnceCache {

	private StoreRemoteService storeRemoteService;
	private Map<Long, Store> cache;

	public StoreOnceCache(StoreRemoteService storeRemoteService) {
		this.storeRemoteService = storeRemoteService;
		cache = new HashMap<>();
	}

	public Store get(Long key) {
		Store value = cache.get(key);
		if (null == value) {
			value = storeRemoteService.getStoreBySid(key);
			if (null != value)
				cache.put(key, value);
		}
		return value;
	}

}
