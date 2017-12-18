package com.yogu.services.store.base.service;

import java.util.List;

import com.yogu.services.store.base.dto.GoodsCategory;

/**
 * 商品分类相关接口
 * 
 */
public interface GoodsCategoryService {
	
	/**
	 * 获取所有商品分类信息列表，结果按照sequence从小到大排序
	 * 
	 * @return 符合的记录，若无，返回empty list
	 */
	List<GoodsCategory> listAll();
	
	GoodsCategory getById(long categoryId);

}
