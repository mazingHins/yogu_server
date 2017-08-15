package com.yogu.services.store.dish.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 菜品TAG（收纳形式，即所有的菜品TAG都聚集于此）
 * 
 */
public class DishTag implements Serializable {

	private static final long serialVersionUID = -3074457346044739729L;

	/** TAG ID */
	private long tagId;

	/** TAG名称 */
	private String name;

	/** 创建时间 */
	private Date createTime;

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
