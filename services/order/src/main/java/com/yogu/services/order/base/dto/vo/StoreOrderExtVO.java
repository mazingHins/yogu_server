package com.yogu.services.order.base.dto.vo;

/**
 * StoreOrderVO的拓展类, 适用于B端待收货的TAG, 增加了字段
 * 1, 配送员名字
 * 2, 配送员电话
 * @author felix
 * @date 2015-11-09
 */
public class StoreOrderExtVO extends StoreOrderVO {

	private static final long serialVersionUID = 1L;
	
	/** 配送员名 */
	private String deliveryName = "";
	
	/** 配送员电话 */
	private String deliveryPhone = "";
	
	/** 是否第三方配送. 1：是；其他否 */
	private short isThirdExpress;
	
	/** 第三方配送状态 */
	private short expressStatus;

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

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

	public short getExpressStatus() {
		return expressStatus;
	}

	public void setExpressStatus(short expressStatus) {
		this.expressStatus = expressStatus;
	}
	
}
