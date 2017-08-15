package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

public class AdminCancelOrderBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2603927133243861435L;

	private long orderNo;

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	
}
