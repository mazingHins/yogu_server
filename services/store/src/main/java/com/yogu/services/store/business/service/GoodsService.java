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
	 * 分页获取商品信息，按照创建时间倒序（从小到大）<br>
	 * 方法会区分用户对应的上级批发商装载不同的价格，如果不传用户id，默认为零售价
	 * 
	 * @param uid - 用户id（可以为空）
	 * @param lastId -上一条商品创建时间（首页传0）
	 * @param pageSize - 每页大小
	 * @author qiujun 
	 * @date 2017年9月13日 下午9:42:49 
	 * @return 商品列表，若无，返回empty list
	 */
	List<Goods> listByPage(Long uid, long lastTime, int pageSize);
	
	/**
	 * 通过商品名称分页获取商品信息，按照创建时间倒序（从小到大）<br>
	 * 方法会区分用户对应的上级批发商装载不同的价格，如果不传用户id，默认为零售价
	 * 
	 * @param goodsName - 商品名称（可以为空）
	 * @param uid - 用户id（可以为空）
	 * @param lastId -上一条商品创建时间（首页传0）
	 * @param pageSize - 每页大小
	 * @return 商品列表，若无，返回empty list
	 */
	List<Goods> listByName(String goodsName, Long uid, long lastTime, int pageSize);
	

}
