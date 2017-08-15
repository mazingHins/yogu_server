package com.yogu.services.order.base.dto.vo;

import com.yogu.services.order.base.dto.Order;

/**
 * 内部管理订单VO
 *
 * @date 2016年8月16日 下午4:43:18
 * @author hins
 */
public class AdminOrderVO extends Order{

	private static final long serialVersionUID = 1090532315950744935L;
	/**
	 * 是否允许退款：1-是；其他-否
	 */
	private short allowRefund;
	
	/**
	 * 第三方配送状态
	 */
	private short expressStatus;
	
	/** 配送员名 */
	private String deliveryName = "";
	
	/** 配送员电话 */
	private String deliveryPhone = ""; 
	
	public short getAllowRefund() {
		return allowRefund;
	}

	public void setAllowRefund(short allowRefund) {
		this.allowRefund = allowRefund;
	}

	public short getExpressStatus() {
		return expressStatus;
	}

	public void setExpressStatus(short expressStatus) {
		this.expressStatus = expressStatus;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}
	
}
