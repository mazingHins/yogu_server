package com.yogu.services.order.resource.param;

import javax.ws.rs.FormParam;

/**
 * 订单预支付API接收参数<br>
 * @author felix
 */
public class H5SettleParam {
	/**
	 * 用户ID
	 */
	@FormParam("uid")
	private long uid;
	
	/**
	 * 寄送地址ID
	 */
	@FormParam("addressId")
	private long addressId;
	
	/**
	 * 寄送时间（毫秒数）
	 */
	@FormParam("deliveryTime")
	private long deliveryTime;
	
	/**
	 * 地址到商家的直线距离(单位米)
	 */
	@FormParam("distance")
	private int distance;
	
	/**
	 * 购买详情
	 */
	@FormParam("purchaseDetail")
	private String purchaseDetail;
	
	/**
	 * 是否只检测库存信息
	 * 
	 * 1 - 表示只检测库存
	 * 其他或不传 - 表示检测所有项
	 * 
	 */
	@FormParam("checkSurplusOnly")
	private short checkSurplusOnly;
	
	/**
	 * 纬度 
	 */
	@FormParam("lat")
	private double lat;
	
	/**
	 * 经度 
	 */
	@FormParam("lng")
	private double lng;
	
	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(String purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}

	public short getCheckSurplusOnly() {
		return checkSurplusOnly;
	}

	public void setCheckSurplusOnly(short checkSurplusOnly) {
		this.checkSurplusOnly = checkSurplusOnly;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
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

	
}
