package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 菜品类别，用于简易后台
 * 
 */
public class DishCategory implements Serializable {

	private static final long serialVersionUID = -3074457346643879762L;

	/** 菜品分类ID */
	private long categoryId;

	/** 菜品分类名称 */
	private String categoryName;

	/** 创建时间 */
	private Date createTime;

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
