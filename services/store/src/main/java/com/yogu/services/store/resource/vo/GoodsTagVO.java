package com.yogu.services.store.resource.vo;

/**
 * 商品标签VO
 *
 */
public class GoodsTagVO {
	
	/** 标签ID */
	private long tagId;

	/** 分类ID */
	private long categoryId;

	/** 分类名称 */
	private String tagName;
	
	/** 分类icon图标地址 */
	private String iconUrl;
	
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}
