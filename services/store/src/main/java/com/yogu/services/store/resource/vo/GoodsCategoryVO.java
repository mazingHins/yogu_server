package com.yogu.services.store.resource.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品分类VO
 *
 */
public class GoodsCategoryVO implements Serializable {
	
	private static final long serialVersionUID = -8598969701047963241L;

	/** 分类ID */
	private long categoryId;

	/** 分类名称 */
	private String categoryName;
	
	/** 分类icon图标地址 */
	private String iconUrl;
	
	/** 分类下的标签列表 */
	private List<GoodsTagVO> tagList;
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public List<GoodsTagVO> getTagList() {
		return tagList;
	}

	public void setTagList(List<GoodsTagVO> tagList) {
		this.tagList = tagList;
	}

	

}
