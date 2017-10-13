package com.yogu.services.store.base.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.services.store.base.dao.GoodsRecommendDao;
import com.yogu.services.store.base.dto.GoodsRecommend;
import com.yogu.services.store.base.entry.GoodsRecommendPO;
import com.yogu.services.store.base.service.GoodsRecommendService;

@Named
public class GoodsRecommendServiceImpl implements GoodsRecommendService {
	
	@Inject
	private GoodsRecommendDao goodsRecommendDao;

	@Override
	public List<GoodsRecommend> listEffectivve(long lastTime, int size) {
		
		if(size <1){
			return Collections.emptyList();
		}
		
		List<GoodsRecommendPO> list = goodsRecommendDao.listByStatus(BooleanConstants.TRUE, size, lastTime);
		
		return VOUtil.fromList(list, GoodsRecommend.class);
	}

}
