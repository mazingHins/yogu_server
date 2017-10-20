package com.yogu.services.store.business.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.store.Goods;
import com.yogu.services.store.business.dao.GoodsTrackDao;
import com.yogu.services.store.business.entry.GoodsTrackPO;
import com.yogu.services.store.business.service.GoodsTrackService;


@Named
public class GoodsTrackServiceImpl implements GoodsTrackService {
	
	@Inject
	private GoodsTrackDao goodsTrackDao;

	@Override
	public Goods getTrackById(long goodsId) {
		GoodsTrackPO po = goodsTrackDao.getById(goodsId);
		if (null == po) {
			return null;
		}
		return VOUtil.from(po, Goods.class);
	}

	@Override
	public List<Goods> listTrackByIds(List<Long> goodsIds) {
		if(goodsIds == null || goodsIds.isEmpty()){
			return Collections.emptyList();
		}
		List<GoodsTrackPO> list = goodsTrackDao.listById(goodsIds);
		return VOUtil.fromList(list, Goods.class);
	}

}
