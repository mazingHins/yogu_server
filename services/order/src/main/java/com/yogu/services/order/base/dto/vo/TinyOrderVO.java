package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 轻量化的Order对象
 * 
 * @author felix
 * @date 2015-10-19
 */
public class TinyOrderVO implements Serializable{
	
	private static final long serialVersionUID = 1457848857626756804L;

	/**
	 * 订单编号
	 */
	private long orderNo;
	
	/**
	 * 用户ID
	 */
	private long uid;
	
	/**
	 * 门店ID
	 */
	private long storeId;

	/**
	 * 订单开始时间
	 */
	private Date orderBeginTime;
	
	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Date getOrderBeginTime() {
		return orderBeginTime;
	}

	public void setOrderBeginTime(Date orderBeginTime) {
		this.orderBeginTime = orderBeginTime;
	}
}
