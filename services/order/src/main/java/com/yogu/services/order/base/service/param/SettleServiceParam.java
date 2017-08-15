package com.yogu.services.order.base.service.param;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 计算订单结算信息接口SettleService的请求参数
 * @author Hins
 * @date 2015年8月20日 上午10:55:00
 */
public class SettleServiceParam implements Serializable {
	
	private static final long serialVersionUID = -663392282631846611L;

	/**
	 * 用户ID
	 */
	private long uid;
	
	/**
	 * 寄送地址ID
	 */
	private long addressId;
	
	/**
	 * 寄送时间（毫秒数）
	 */
	private long deliveryTime;
	
	/**
	 * 地址到商家的直线距离(单位米)
	 */
	private int distance;
	
	/**
	 * 纬度 
	 */
	private double lat;
	
	/**
	 * 经度 
	 */
	private double lng;

	/**
	 * 购买信息，增加规格相关的内容
	 * ten 2016/2/23
	 */
	private List<PurchaseDetail> purchaseDetails;
	
	/**
	 * 是否只检测库存信息
	 * 
	 * 1 - 表示只检测库存
	 * 其他或不传 - 表示检测所有项
	 * felix 2016-03-15
	 */
	private short checkSurplusOnly;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

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

	public List<PurchaseDetail> getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

	public short getCheckSurplusOnly() {
		return checkSurplusOnly;
	}

	public void setCheckSurplusOnly(short checkSurplusOnly) {
		this.checkSurplusOnly = checkSurplusOnly;
	}
}
