package com.yogu.services.store.resource.vo;

import java.io.Serializable;
import java.util.List;

/**
 * app首页信息VO
 * 
 * @author qiujun   
 * @date 2017年9月13日 下午9:20:26
 */
public class IndexVO implements Serializable {

	private static final long serialVersionUID = 6871819566613843924L;
	
	/**
	 * 广告配置
	 */
	private List<IndexAdvertisingVO> adList;
	
	/**
	 * 分类栏信息列表
	 */
	private List<GoodsCategoryVO> categoryList;
	
	/**
	 * 今日推荐商品列表
	 */
	private List<IndexRecommendVO> recommendList;

	public List<IndexAdvertisingVO> getAdList() {
		return adList;
	}

	public void setAdList(List<IndexAdvertisingVO> adList) {
		this.adList = adList;
	}

	public List<GoodsCategoryVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<GoodsCategoryVO> categoryList) {
		this.categoryList = categoryList;
	}

	public List<IndexRecommendVO> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<IndexRecommendVO> recommendList) {
		this.recommendList = recommendList;
	}
	
}
