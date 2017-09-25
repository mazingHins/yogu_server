package com.yogu.services.store.business.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.store.business.dao.GoodsDao;
import com.yogu.services.store.business.dto.Goods;
import com.yogu.services.store.business.dto.Store;
import com.yogu.services.store.business.entry.GoodsPO;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.business.service.StoreService;

@Named
public class GoodsServiceImpl implements GoodsService {

	@Inject
	private GoodsDao goodsDao;
	
	@Inject
	private UserRemoteService userRemoteService;
	
	@Inject
	private StoreService storeService;
	
	@Override
	public Goods getById(long goodsId) {
		GoodsPO po = goodsDao.getById(goodsId);
		if (null == po) {
			return null;
		}
		return VOUtil.from(po, Goods.class);
	}

	@Override
	public Goods getByKey(long goodsKey) {
		GoodsPO po = goodsDao.getByKey(goodsKey);
		if (null == po) {
			return null;
		}
		return VOUtil.from(po, Goods.class);
	}

	@Override
	public List<Goods> listByPage(Long uid, long lastTime, int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		
		List<GoodsPO> list = goodsDao.listByPage(lastTime < 1 ? null : lastTime, pageSize, BooleanConstants.TRUE);
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}
	
	private long getUserAgentStoreId(Long uid){
		if (uid == null || uid < 1) {
			return 0;
		}
		UserProfile profile = userRemoteService.getUserProfileByUid(uid);
		if (profile == null) {
			return 0;
		}
		
		Store store = storeService.getByUid(uid);
		if (store == null) {
			return 0;
		}
		
		return store.getStoreId();
		
	}

	@Override
	public List<Goods> listByName(String goodsName, Long uid, long lastTime, int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		if(StringUtils.isNotBlank(goodsName)){
			goodsName = goodsName.trim();
		}
		
		List<GoodsPO> list = null;
		if(StringUtils.isBlank(goodsName)){
			list = goodsDao.listByPage(lastTime < 1 ? null : lastTime, pageSize, BooleanConstants.TRUE);
		}else{
			list = goodsDao.listByName(goodsName, lastTime < 1 ? null : lastTime, pageSize, BooleanConstants.TRUE);
		}
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}

}
