package com.yogu.services.store.tag;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资讯专题下的推荐餐厅标签
 * 
 */
public class NewsStoreinfoTag implements Serializable {

	private static final long serialVersionUID = -3074457345783695131L;

	/** 餐厅详情id */
	private long sinfoId;

	/** 标签id */
	private long tagId;

	/** 打标签时间 */
	private Date createTime;

	public void setSinfoId(long sinfoId) {
		this.sinfoId = sinfoId;
	}

	public long getSinfoId() {
		return sinfoId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getTagId() {
		return tagId;
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
