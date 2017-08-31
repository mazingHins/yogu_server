package com.yogu.services.order.base.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细表
 * 
 */
public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = 2240975482586702850L;

	/** 订单id */
	private long orderId;

	/** 商品id */
	private long goodsId;
	
	/** 商品key */
	private long goodsKey;

	/** 购买数量 */
	private int number;

	/** 商品单价（精确到分） */
	private long unitFee;

	/** 总价 */
	private long totalFee;

	/** 创建时间 */
	private Date createTime;
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

	public long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}

	public long getGoodsKey() {
		return goodsKey;
	}

	public void setGoodsKey(long goodsKey) {
		this.goodsKey = goodsKey;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getUnitFee() {
		return unitFee;
	}

	public void setUnitFee(long unitFee) {
		this.unitFee = unitFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
