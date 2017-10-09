package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品标签
 */
public class GoodsTagMp implements Serializable {

	private static final long serialVersionUID = -7498407660134703574L;

	/** 标签ID */
	private long tagId;

	/** 分类ID */
	private long categoryId;

	/** 分类名称 */
	private String tagName;
	
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
