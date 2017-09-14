package com.yogu.services.store.business.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.store.business.dao.GoodsDao;
import com.yogu.services.store.business.dto.Goods;
import com.yogu.services.store.business.entry.GoodsPO;
import com.yogu.services.store.business.service.GoodsService;

@Named
public class GoodsServiceImpl implements GoodsService {

	@Inject
	private GoodsDao goodsDao;
	
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
	public List<Goods> listByPage(int pageNo, int pageSize) {
		return Collections.emptyList();
	}

}
