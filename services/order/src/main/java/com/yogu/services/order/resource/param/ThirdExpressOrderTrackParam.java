package com.yogu.services.order.resource.param;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 通用的第三方配送 订单配送状态回调参数接收对象
 * 
 * @author sky 2016-10-11
 *
 */
public class ThirdExpressOrderTrackParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9161437893349700179L;

	/** 第三方配送方code */
	private long expressCode;

	/** 米星订单id */
	private long orderId;

	/** 米星订单orderNo */
	private long orderNo;

	/** 第三方配送订单orderNo */
	private long thirdOrderNo;

	/** 配送员name */
	private String deliverName = "";

	/** 配送员电话号码 */
	private String deliverPhone = "";

	/** 配送操作时间 */
	private Date operateTime;

	/** 配送状态 */
	private short expressStatus = 0;

	/** 配送状态描述 */
	private String expressStatusDesc = "";
	
	/** 异常原因说明 **/
	private String exceptionReason;

	/** 配送员当前位置，纬度 */
	private double lat;

	/** 配送员当前位置，经度 */
	private double lng;

	public long getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(long expressCode) {
		this.expressCode = expressCode;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getThirdOrderNo() {
		return thirdOrderNo;
	}

	public void setThirdOrderNo(long thirdOrderNo) {
		this.thirdOrderNo = thirdOrderNo;
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getDeliverPhone() {
		return deliverPhone;
	}

	public void setDeliverPhone(String deliverPhone) {
		this.deliverPhone = deliverPhone;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public short getExpressStatus() {
		return expressStatus;
	}

	public void setExpressStatus(short expressStatus) {
		this.expressStatus = expressStatus;
	}

	public String getExpressStatusDesc() {
		return expressStatusDesc;
	}

	public void setExpressStatusDesc(String expressStatusDesc) {
		this.expressStatusDesc = expressStatusDesc;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
