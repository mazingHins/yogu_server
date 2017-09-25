package com.yogu.services.store.business.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.store.business.dao.StoreDao;
import com.yogu.services.store.business.dto.Store;
import com.yogu.services.store.business.entry.StorePO;
import com.yogu.services.store.business.service.StoreService;

/**
 * StoreService的实现
 *
 */
@Named
public class StoreServiceImpl implements StoreService {

	@Inject
	private StoreDao storeDao;
	
	@Override
	public Store getById(long storeId) {
		if(storeId<1){
			return null;
		}
		
		StorePO store = storeDao.getById(storeId);
		if(null == store){
			return null;
		}
		
		return VOUtil.from(store, Store.class);
	}

	@Override
	public Store getByUid(long uid) {
		if(uid<1){
			return null;
		}
		
		StorePO store = storeDao.getByUid(uid);
		if(null == store){
			return null;
		}
		
		return VOUtil.from(store, Store.class);
	}

}
