package com.yogu.services.store.base.service;

import java.util.List;

import com.yogu.services.store.base.dto.GoodsRecommend;

/**
 * 商品标签相关接口
 * 
 */
public interface GoodsRecommendService {
	
	/**
	 * 获取有效的推荐列表，结果按照创建时间倒序排序
	 * 
	 * @param lastTime - 上一页最后一条时间，首页传0
	 * @param size - 数量长度
	 * @return 符合的记录，若无，返回empty list
	 */
	List<GoodsRecommend> listEffectivve(long lastTime, int size);
	

}
