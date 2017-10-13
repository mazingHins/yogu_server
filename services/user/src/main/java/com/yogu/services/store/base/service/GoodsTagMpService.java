package com.yogu.services.store.base.service;

import java.util.List;

import com.yogu.services.store.base.dto.GoodsTag;

/**
 * 商品标签相关接口
 * 
 */
public interface GoodsTagMpService {
	
	/**
	 * 获取指定分类下的商品标签列表，结果按照sequence从小到大排序<br>
	 * 方法不会校验分类是否存在
	 * 
	 * @param categoryId - 分类id
	 * @return 符合的记录，若无，返回empty list
	 */
	List<GoodsTag> listByCategoryId(long categoryId);
	
	/**
	 * 通过id获取商品表情内容
	 * @author qiujun
	 * @date 2017年10月9日 上午11:24:21 
	 * 
	 * @param tagId - 标签id
	 * @return
	 */
	GoodsTag getById(long tagId);

}
