package com.yogu.services.store.business.service;

import com.yogu.services.store.business.dto.Store;

/**
 * 店铺信息相关接口
 *
 */
public interface StoreService {
	
	/**
	 * 通过店铺id获取店铺信息
	 * 
	 * @param storeId - 店铺id
	 * @return 店铺信息，如果无，返回null
	 */
	Store getById(long storeId);

}
