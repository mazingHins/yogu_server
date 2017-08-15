package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商家米星付订单列表 数据展示实体封装
 *
 * @date 2016年6月28日 下午6:13:31
 * @author hins
 */
public class StoreMazingPayOrderVO implements Serializable {

	private static final long serialVersionUID = 8853621599717454256L;

	/** 订单编号 */
	private String orderNo;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间、买单时间 */
	private Date finishTime;

	/** 商家对用户的评分 */
	private short storeStar;

	/** 订单金额、应付金额（精确到分） */
	private long totalFee;

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 订单状态：1-未付款；2-已付款；3-已接单；4-制作中；5-配送中；6-商家确认收货；7-买家确认收货 */
	private short status;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 实付金额（精确到分） */
	private long actualFee = 0;

	/** 优惠总价（精确到分） */
	private long discountFee = 0;

	/** 拒单/取消订单备注 */
	private String rejectRemark;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** im id **/
	private long imId;

	/** 当天订单序列号 */
	private String serialNumber;

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

	/** 发票抬头 */
	private String invoiceTitle = "";

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public long getActualFee() {
		return actualFee;
	}

	public void setActualFee(long actualFee) {
		this.actualFee = actualFee;
	}

	public long getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(long discountFee) {
		this.discountFee = discountFee;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
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

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	
}
