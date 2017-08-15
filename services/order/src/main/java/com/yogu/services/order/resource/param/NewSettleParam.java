package com.yogu.services.order.resource.param;

import javax.ws.rs.FormParam;

/**
 * 订单预支付API接收参数<br>
 * v1.1.0-预订类餐厅设计方案（11-09调整）之后的版本用此接收
 * 
 * @author Hins
 * @date 2015年11月13日 下午12:00:45
 */
public class NewSettleParam {

	/**
	 * 寄送地址ID
	 */
	// jfan 3-30 补坑，ios可能会传递错误的值；这里改成不自动填充，外部需要手动填充
	// FillingPitUtils.findAddressId
	// @FormParam("addressId")
	private long addressId;

	/**
	 * 寄送时间（毫秒数）
	 */
	@FormParam("deliveryTime")
	private long deliveryTime;

	/**
	 * 购买商品key，用英文逗号分隔
	 */
	@FormParam("dishKey")
//	@NotEmpty(message = "订单商品不能为空") // 2016/2/23 去掉必填属性
	private String dishKey;

	/**
	 * 购买商品数量，用英文逗号分隔，跟dishKey对应
	 */
	@FormParam("num")
//	@NotEmpty(message = "订单商品数量不能为空") // 2016/2/23 去掉必填属性
	private String num;

	/**
	 * 地址到商家的直线距离(单位米)
	 */
	@FormParam("distance")
	private int distance;

	/**
	 * 购买详情
	 * ten 2016/2/22 新增，美食规格开发
	 */
	@FormParam("purchaseDetail")
	private String purchaseDetail;
	
	/**
	 * 是否只检测库存信息
	 * 
	 * 1 - 表示只检测库存
	 * 其他或不传 - 表示检测所有项
	 * 
	 * felix 2016/3/15 新增
	 * 
	 */
	@FormParam("checkSurplusOnly")
	private short checkSurplusOnly;

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

	public String getDishKey() {
		return dishKey;
	}

	public void setDishKey(String dishKey) {
		this.dishKey = dishKey;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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
}
