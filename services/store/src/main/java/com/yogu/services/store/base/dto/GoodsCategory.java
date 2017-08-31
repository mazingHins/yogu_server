package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类
 */
public class GoodsCategory implements Serializable {
	
	private static final long serialVersionUID = -3602464074554519700L;

	/** 分类ID */
	private long categoryId;

	/** 分类名称 */
	private String categoryName;
	
	/** 分类icon图标地址 */
	private String iconUrl;
	
	/** 排序，从小到大 */
	private int sequence;
	
	/** 创建时间 */
	private Date createTime;

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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
