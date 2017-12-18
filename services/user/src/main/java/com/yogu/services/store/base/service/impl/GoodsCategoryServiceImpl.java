package com.yogu.services.store.base.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.store.base.dao.GoodsCategoryDao;
import com.yogu.services.store.base.dto.GoodsCategory;
import com.yogu.services.store.base.entry.GoodsCategoryPO;
import com.yogu.services.store.base.service.GoodsCategoryService;

/**
 * GoodsCategoryService的实现
 *
 */
@Named
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	
	@Inject
	private GoodsCategoryDao goodsCategoryDao;

	@Override
	public List<GoodsCategory> listAll() {
		List<GoodsCategoryPO> list = goodsCategoryDao.listAllBySequence();
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		return VOUtil.fromList(list, GoodsCategory.class);
	}

	@Override
	public GoodsCategory getById(long categoryId) {
		GoodsCategoryPO po = goodsCategoryDao.getById(categoryId);
		if(null == po){
			return null;
		}
		return VOUtil.from(po, GoodsCategory.class);
	}

}
