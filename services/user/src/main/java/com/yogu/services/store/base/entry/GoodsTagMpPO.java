package com.yogu.services.store.base.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品标签
 */
public class GoodsTagMpPO implements Serializable {

	private static final long serialVersionUID = -7860106753378542977L;

	/** 标签ID */
	private long tagId;

	/** 商品ID */
	private long goodsId;
	/** 创建时间 */
	private Date createTime;
	

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
