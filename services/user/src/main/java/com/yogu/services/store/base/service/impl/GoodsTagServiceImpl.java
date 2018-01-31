package com.yogu.services.store.base.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.store.base.dao.GoodsTagDao;
import com.yogu.services.store.base.dto.GoodsTag;
import com.yogu.services.store.base.entry.GoodsTagPO;
import com.yogu.services.store.base.service.GoodsTagService;

/**
 * GoodsTagService的实现
 *
 */
@Named
public class GoodsTagServiceImpl implements GoodsTagService {

	@Inject
	private GoodsTagDao goodsTagDao;
	
	@Override
	public List<GoodsTag> listByCategoryId(long categoryId) {
		if(categoryId<1){
			return Collections.emptyList();
		}
		List<GoodsTagPO> list = goodsTagDao.listByCategoryId(categoryId);
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		
		return VOUtil.fromList(list, GoodsTag.class);
	}

	@Override
	public GoodsTag getById(long tagId) {
		GoodsTagPO po = goodsTagDao.getById(tagId);
		if (null != po)
			return VOUtil.from(po, GoodsTag.class);
		return null;
	}

	@Override
	public List<GoodsTag> listAll() {
		List<GoodsTagPO> list = goodsTagDao.listAll();
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		
		return VOUtil.fromList(list, GoodsTag.class);
	}

}
