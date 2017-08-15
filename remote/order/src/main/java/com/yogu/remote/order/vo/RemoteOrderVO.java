package com.yogu.remote.order.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表 hins 2015/11/05。remote方法向外提供
 */
public class RemoteOrderVO implements Serializable {

	private static final long serialVersionUID = -3074457343540056274L;

	/** orderId */
	private long orderId;

	/** 订单编号 */
	private long orderNo;

	/** 支付系统流水号 */
	private long payNo;

	/** 下单人id */
	private long uid;

	/** 下单人imId */
	private long imId;

	/** 店家id */
	private long storeId;

	/** 配送员id */
	private long deliverId = 0;

	/** 支付类型 */
	private short payType = 0;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 应付金额（精确到分） */
	private long totalFee;

	/** 实付金额（精确到分） */
	private long actualFee = 0;

	/** 配送费（精确到分） */
	private long deliveryFee = 0;

	/** 优惠总价（精确到分） */
	private long discountFee = 0;

	/** 商品总价（精确到分） */
	private long goodsFee;

	/** 是否使用优惠券，1-是；其他否 */
	private short useCoupon = 0;

	/** 送货地址 */
	private String address;

	/** 送货地址全称 */
	private String fullAddress;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** 寄送时间 */
	private Date deliveryTime;

	/** 纬度 */
	private double lat;

	/** 经度 */
	private double lng;

	/** 用餐人数，为0则表示该订单不支持选择用餐人数 */
	private short mealNumber = 0;

	/** 订单界面选择商家信息（如口味） */
	private String featureContent;

	/**
	 * 订单状态：10-未付款；15-已付款；20-已接单；25-配送中；30-商家确认收货；35-买家确认收货；40-已评论；
	 * 45-申请退款；50-退款中；55-已退款；60-已退款；65-订单已取消
	 */
	private short status;

	/** 订单备注 */
	private String remark;

	/** 运费说明 */
	private String deliveryRemark;

	/** _id` bigint(20) NOT NULL COMMENT 订单评论id */
	private long commentId;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间 */
	private Date finishTime;

	/** 错误信息列表 */
	// private List<ErrorVO> errorList;

	/** 下单结果状态 */
	private short result;

	/** 用户是否自己提货 0:否 1:是 */
	private short selfPick;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 当天订单序列号 */
	private String serialNumber = "";

	/** 订单开始时间；现金支付：下单时间、在线支付：支付成功时间 */
	private Date orderBeginTime;

	/** 用户下单时-桌号的备注 */
	private String userTableNoRemark = "";
	
	/**
	 * 发票抬头
	 */
	private String invoiceTitle = "";

	public Date getOrderBeginTime() {
		return orderBeginTime;
	}

	public void setOrderBeginTime(Date orderBeginTime) {
		this.orderBeginTime = orderBeginTime;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setPayNo(long payNo) {
		this.payNo = payNo;
	}

	public long getPayNo() {
		return payNo;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getUid() {
		return uid;
	}

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getPayMode() {
		return payMode;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
	}

	public void setDeliveryFee(long deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public long getDeliveryFee() {
		return deliveryFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public long getDiscountFee() {
		return discountFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public short getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(short useCoupon) {
		this.useCoupon = useCoupon;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContacts() {
		return contacts;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLat() {
		return lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLng() {
		return lng;
	}

	public short getMealNumber() {
		return mealNumber;
	}

	public void setMealNumber(short mealNumber) {
		this.mealNumber = mealNumber;
	}

	public void setFeatureContent(String featureContent) {
		this.featureContent = featureContent;
	}

	public String getFeatureContent() {
		return featureContent;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public short getStatus() {
		return status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}

	public String getDeliveryRemark() {
		return deliveryRemark;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	// public List<ErrorVO> getErrorList() {
	// return errorList;
	// }

	// public void setErrorList(List<ErrorVO> errorList) {
	// this.errorList = errorList;
	// }

	public short getResult() {
		return result;
	}

	public void setResult(short result) {
		this.result = result;
	}

	public short getSelfPick() {
		return selfPick;
	}

	public void setSelfPick(short selfPick) {
		this.selfPick = selfPick;
	}

	public void setDeliverId(long deliverId) {
		this.deliverId = deliverId;
	}

	public long getDeliverId() {
		return deliverId;
	}

	public String getUserTableNoRemark() {
		return userTableNoRemark;
	}

	public void setUserTableNoRemark(String userTableNoRemark) {
		this.userTableNoRemark = userTableNoRemark;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
