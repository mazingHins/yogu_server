package com.yogu.services.store.business.service;

import com.yogu.services.store.Goods;

/**
 * 商品快照信息相关接口
 *
 */
public interface GoodsTrackService {
	
	public Goods getTrackById(long goodsId);

}
