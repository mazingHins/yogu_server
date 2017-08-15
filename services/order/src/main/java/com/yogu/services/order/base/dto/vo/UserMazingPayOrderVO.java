package com.yogu.services.order.base.dto.vo;

import java.util.Date;

/**
 * 用户米星付的订单信息VO
 *
 * @date 2016年6月15日 下午6:00:45
 * @author hins
 */
public class UserMazingPayOrderVO {

	/** 订单编号 */
	private String orderNo;

	/** 店家id */
	private long storeId;

	/** 店家名称 */
	private String storeName;

	/** 瀑布流图片路径，门店收藏列表中也使用此图。 */
	private String topicImg;

	/** 店家电话，可能多个，英文逗号隔开 */
	private String storePhones;

	/** 门店LOGO路径 */
	private String logoImg;

	/** 门店地址 */
	private String storeAddress;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间、买单时间 */
	private Date finishTime;

	/** 评论星级 */
	private short star;

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

	/** 可选支付方式 1-支付宝；2-微信 */
	private String optionalPayMode;

	/** cblog餐厅id */
	private long sinfoId;

	/** 评论数量 */
	private int commentCount;

	/** 评价图标展示 */
	private Integer[] quotas;

	/**
	 * 发票抬头
	 */
	private String invoiceTitle;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTopicImg() {
		return topicImg;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
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

	public short getStar() {
		return star;
	}

	public void setStar(short star) {
		this.star = star;
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

	public String getStorePhones() {
		return storePhones;
	}

	public void setStorePhones(String storePhones) {
		this.storePhones = storePhones;
	}

	public String getOptionalPayMode() {
		return optionalPayMode;
	}

	public void setOptionalPayMode(String optionalPayMode) {
		this.optionalPayMode = optionalPayMode;
	}

	public long getSinfoId() {
		return sinfoId;
	}

	public void setSinfoId(long sinfoId) {
		this.sinfoId = sinfoId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Integer[] getQuotas() {
		return quotas;
	}

	public void setQuotas(Integer[] quotas) {
		this.quotas = quotas;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

}
