package com.yogu.services.store.base.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.store.base.dao.GoodsTagMpDao;
import com.yogu.services.store.base.dto.GoodsTag;
import com.yogu.services.store.base.dto.GoodsTagMp;
import com.yogu.services.store.base.entry.GoodsTagMpPO;
import com.yogu.services.store.base.service.GoodsTagMpService;

/**
 * GoodsTagService的实现
 *
 */
@Named
public class GoodsTagMpServiceImpl implements GoodsTagMpService {

	@Inject
	private GoodsTagMpDao goodsTagMpDao;

	@Override
	public List<GoodsTagMp> listByGoods(long goodsKey) {
		List<GoodsTagMpPO> list = goodsTagMpDao.listByGoodsKey(goodsKey);
		return VOUtil.fromList(list, GoodsTagMp.class);
	}


	
}
