package com.yogu.services.store.business.service;

import com.yogu.services.store.business.dto.Goods;

/**
 * 商品信息相关接口
 *
 */
public interface GoodsService {
	
	/**
	 * 通过商品id获取店铺信息
	 * 
	 * @param goodsId - 商品id
	 * @return 商品信息，如果无，返回null
	 */
	Goods getById(long goodsId);
	
	/**
	 * 通过商品key获取店铺信息
	 * 
	 * @param goodsKey - 商品key
	 * @return 商品信息，如果无，返回null
	 */
	Goods getByKey(long goodsKey);
	
	

}
