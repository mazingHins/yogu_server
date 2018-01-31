package com.yogu.services.store.base.service;

import java.util.List;

import com.yogu.services.store.base.dto.GoodsTagMp;

/**
 * 商品标签相关接口
 * 
 */
public interface GoodsTagMpService {
	
	
	List<GoodsTagMp> listByGoods(long goodsKey);
	

}
