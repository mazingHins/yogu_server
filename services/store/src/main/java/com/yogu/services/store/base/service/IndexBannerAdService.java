package com.yogu.services.store.base.service;

import java.util.List;

import com.yogu.services.store.base.dto.IndexBannerAd;

/**
 * 商品标签相关接口
 * 
 */
public interface IndexBannerAdService {
	
	/**
	 * 获取有效的广告列表，结果按照创建时间倒序排序
	 * 
	 * @param size - 广告数量长度
	 * @return 符合的记录，若无，返回empty list
	 */
	List<IndexBannerAd> listEffectivve(int size);
	

}
