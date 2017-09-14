package com.yogu.services.store.business.service;

import java.util.List;

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
	
	/**
	 * 分页获取商品信息，根据创建时间倒序（从小到大）
	 * 
	 * @param pageNo - 页码，首页传1
	 * @param pageSize - 每页大小
	 * @author qiujun 
	 * @date 2017年9月13日 下午9:42:49 
	 * @return 商品列表，若无，返回empty list
	 */
	List<Goods> listByPage(int pageNo, int pageSize);

}
