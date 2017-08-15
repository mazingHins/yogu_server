package com.yogu.services.order.resource.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * 生成订单API接收参数
 * @author Hins
 * @date 2015年7月31日 下午3:29:03
 */
public class CreateParam {
	
	/**
	 * 支付方式，1-支付宝；2-微信
	 */
	@FormParam("payMode")
	@DefaultValue("0")
	private short payMode;

	/**
	 * 支付货币类型：1-人民币
	 */
	@FormParam("currencyType")
	@DefaultValue("0")
	private short currencyType;

	/**
	 * 寄送地址
	 */
	@FormParam("addressId")
	private long addressId;

	/**
	 * 寄送时间
	 */
	@FormParam("deliveryTime")
	private long deliveryTime;

	/**
	 * 购买商品id，用英文逗号分隔
	 */
	@FormParam("dishId")
//	@NotEmpty(message = "订单商品不能为空") // 2016/2/23 by ten 改为非必填
	private String dishId;

	/**
	 * 购买商品数量，用英文逗号分隔，跟dishId对应
	 */
	@FormParam("num")
//	@NotEmpty(message = "订单商品数量不能为空")
	private String num;

	/**
	 * 订单备注
	 */
	@FormParam("remark")
	@DefaultValue("")
	private String remark;

	/**
	 * 用户距离商家的直线距离(单位米)
	 */
	@FormParam("distance")
	private int distance;

	/**
	 * 用餐人数
	 */
	@FormParam("mealNumber")
	private int mealNumber;

	/**
	 * 订单界面选择商家信息（如口味）
	 */
	@FormParam("featureContent")
	@DefaultValue("")
	private String featureContent;

	// add by sky 2015-09-02 15:39:00 新增用户‘自提货物’选项字段
	/** 配送方式 1:商家配送 2:自提 3:快递 **/
	@FormParam("pickType")
	@DefaultValue("1")
	private short pickType;

	/**
	 * 购买详情，包含规格的内容
	 * 2016/2/23 by ten
	 */
	@FormParam("purchaseDetail")
	private String purchaseDetail;
	
	/**
	 * 优惠券id，0表示没使用
	 */
	@FormParam("couponId")
	private long couponId;

	// end
	
	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public Short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Short currencyType) {
		this.currencyType = currencyType;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDishId() {
		return dishId;
	}

	public void setDishId(String dishId) {
		this.dishId = dishId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public int getMealNumber() {
		return mealNumber;
	}

	public void setMealNumber(int mealNumber) {
		this.mealNumber = mealNumber;
	}

	public String getFeatureContent() {
		return featureContent;
	}

	public void setFeatureContent(String featureContent) {
		this.featureContent = featureContent;
	}

	public short getPickType() {
		return pickType;
	}

	public void setPickType(short pickType) {
		this.pickType = pickType;
	}

	public String getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(String purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
	
}
