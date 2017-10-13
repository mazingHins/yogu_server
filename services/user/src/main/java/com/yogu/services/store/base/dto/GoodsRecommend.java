package com.yogu.services.store.base.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品标签
 */
public class GoodsRecommend implements Serializable {

	private static final long serialVersionUID = -5877992949648887767L;

	/** 推荐ID */
	private long recommend_id;

	/** 商品key*/
	private long goodsKey;

	/** 排序，从小到大 */
	private int sequence;
	
	/** 状态；1-正常；其他-下架 */
	private short status;
	
	/** 创建时间 */
	private Date createTime;

	public long getRecommend_id() {
		return recommend_id;
	}

	public void setRecommend_id(long recommend_id) {
		this.recommend_id = recommend_id;
	}

	public long getGoodsKey() {
		return goodsKey;
	}

	public void setGoodsKey(long goodsKey) {
		this.goodsKey = goodsKey;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
