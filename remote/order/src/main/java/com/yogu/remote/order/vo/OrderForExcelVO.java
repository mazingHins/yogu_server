package com.yogu.remote.order.vo;

import java.io.Serializable;

/**
 * 导出到Excel的VO
 * 
 * @author jack
 *
 */
public class OrderForExcelVO implements Serializable {

	private static final long serialVersionUID = -1449216166466049355L;

	/**餐厅名称，区分分店显示*/
	private String storeName;
	
	private long storeId;
	
	private long uid;
	
	/**餐厅名称，区分分店显示*/
	private String userName;
	
	/** 订单编号 */
	private String orderNo;

	/** 订单编号 */
	private long orderId;
	
	/** 订单创建时间 */
	private String createTime;

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 订单金额（应付金额，精确到分） */
	private long totalFee = 0;

	/** 优惠券金额（精确到分） */
	private long discountFee = 0;

	/** 实付金额（精确到分） */
	private long actualFee = 0;

	/**顺丰配送费：用户承担金额*/
	private long userDeliveryFee;
	
	/**顺丰配送费：商家承担金额*/
	private long storeDeliveryFee;
	
	/**顺丰配送费：米星补贴金额*/
	private long mzDeliveryFee;
	
	/**顺丰配送费：总金额(sfCustomerFee + sfStoreFee + sfMazingFee)*/
	private long expressNeedFee;
	
	/**商家实收金额*/
	private long receivedFee;
	
	/** 优惠券承担方类型 1：平台型 2：商家型 */
	private short couponBearType;

	/** 订单完成时间 */
	private String finishTime;
	
	/**是否顺丰配送1是0否*/
	private short isThirdExpress;
	
	/**备注 **/
	private String remark;

	/**
	 * 用户帐号，这个字段只显示在管理后台报表导出，b端报表不显示
	 */
	private String passport;
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
	}

	public long getUserDeliveryFee() {
		return userDeliveryFee;
	}

	public void setUserDeliveryFee(long userDeliveryFee) {
		this.userDeliveryFee = userDeliveryFee;
	}

	public long getStoreDeliveryFee() {
		return storeDeliveryFee;
	}

	public void setStoreDeliveryFee(long storeDeliveryFee) {
		this.storeDeliveryFee = storeDeliveryFee;
	}

	public long getMzDeliveryFee() {
		return mzDeliveryFee;
	}

	public void setMzDeliveryFee(long mzDeliveryFee) {
		this.mzDeliveryFee = mzDeliveryFee;
	}

	public long getExpressNeedFee() {
		return expressNeedFee;
	}

	public void setExpressNeedFee(long expressNeedFee) {
		this.expressNeedFee = expressNeedFee;
	}

	public long getReceivedFee() {
		return receivedFee;
	}

	public void setReceivedFee(long receivedFee) {
		this.receivedFee = receivedFee;
	}

	public short getCouponBearType() {
		return couponBearType;
	}

	public void setCouponBearType(short couponBearType) {
		this.couponBearType = couponBearType;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "OrderForExcelVO [storeName=" + storeName + ", storeId=" + storeId + ", uid=" + uid + ", userName="
				+ userName + ", orderNo=" + orderNo + ", orderId=" + orderId + ", createTime=" + createTime
				+ ", payType=" + payType + ", totalFee=" + totalFee + ", discountFee=" + discountFee + ", actualFee="
				+ actualFee + ", userDeliveryFee=" + userDeliveryFee + ", storeDeliveryFee=" + storeDeliveryFee
				+ ", mzDeliveryFee=" + mzDeliveryFee + ", expressNeedFee=" + expressNeedFee + ", receivedFee="
				+ receivedFee + ", couponBearType=" + couponBearType + ", finishTime=" + finishTime
				+ ", isThirdExpress=" + isThirdExpress + ", remark=" + remark + ", passport=" + passport + "]";
	}
}
