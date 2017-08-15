package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家米星付订单详情VO
 *
 * @date 2016年7月26日 上午9:42:44
 * @author hins
 */
public class StoreMazingPayOrderDetailVO implements Serializable {
	
	private static final long serialVersionUID = 2803178072684758665L;
	
	/** 餐厅名称 */
	private String storeName;
	
	/** 用餐人数，为0则表示该订单不支持选择用餐人数 */
	private short mealNumber = 0;

	/** 寄送时间 */
	private Date deliveryTime;

	/** 送货地址 */
	private String address;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** 订单备注 */
	private String remark;

	/** 订单状态：1-未付款；2-已付款；3-已接单；4-制作中；5-配送中；6-商家确认收货；7-买家确认收货 */
	private short status;

	/** 订单总价格 */
	private int totalFee;

	/** 优惠总价格 */
	private int totalDiscountFee;

	/** 商品总价格 */
	private int goodsFee;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单编号 */
	private Long orderNo;

	/** 下单人IMID */
	private long imId;

	/** 订单用时 （秒） */
	private int duration;

	/** 当天订单序列号 */
	private String serialNumber;

	/** 打印次数 */
	private short printNumber;

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 送餐地址全称 */
	private String fullAddress;
	
	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间、买单时间 */
	private Date finishTime;
	
	/** 商家对用户的评分 */
	private short storeStar;

	/** 支付货币类型：1-人民币 */
	private short currencyType;
	
	/** 实付金额（精确到分） */
	private long actualFee = 0;
	
	/** 优惠总价（精确到分） */
	private int discountFee = 0;
	
	/** 拒单/取消订单备注 */
	private String rejectRemark;
	
	/** 当天订单排序号 */
	private long sequence;
	
	/**
	 * 是否允许退款：1-是；其他-否
	 */
	private short allowRefund;
	
	/** 用户下单时-桌号的备注 */
	private String userTableNoRemark = "";

	/** 商家输入的订单备注 */
	private String storeRemark = "";

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public short getMealNumber() {
		return mealNumber;
	}

	public void setMealNumber(short mealNumber) {
		this.mealNumber = mealNumber;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}

	public int getTotalDiscountFee() {
		return totalDiscountFee;
	}

	public void setTotalDiscountFee(int totalDiscountFee) {
		this.totalDiscountFee = totalDiscountFee;
	}

	public int getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(int goodsFee) {
		this.goodsFee = goodsFee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public short getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(short printNumber) {
		this.printNumber = printNumber;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public short getStoreStar() {
		return storeStar;
	}

	public void setStoreStar(short storeStar) {
		this.storeStar = storeStar;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
	}

	public int getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(int discountFee) {
		this.discountFee = discountFee;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public short getAllowRefund() {
		return allowRefund;
	}

	public void setAllowRefund(short allowRefund) {
		this.allowRefund = allowRefund;
	}

	public String getUserTableNoRemark() {
		return userTableNoRemark;
	}

	public void setUserTableNoRemark(String userTableNoRemark) {
		this.userTableNoRemark = userTableNoRemark;
	}

	public String getStoreRemark() {
		return storeRemark;
	}

	public void setStoreRemark(String storeRemark) {
		this.storeRemark = storeRemark;
	}
	
}
