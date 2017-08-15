package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户历史订单VO
 * 
 * @author Hins
 * @date 2015年8月19日 上午10:14:08
 */
public class UserOrderHistoryVO implements Serializable {

	private static final long serialVersionUID = 1457213573255143080L;

	/** 订单编号 */
	private String orderNo;

	/** 下单人id */
	private long uid;

	/** 店家id */
	private long storeId;

	/** 店家名称 */
	private String storeName;

	/** 瀑布流图片路径，门店收藏列表中也使用此图。 */
	private String topicImg;

	/**
	 * 门店LOGO路径
	 */
	private String logoImg;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间 */
	private Date finishTime;

	/** 评论星级 */
	private short star;

	/** 应付金额（精确到分） */
	private long totalFee;

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 订单状态：1-未付款；2-已付款；3-已接单；4-制作中；5-配送中；6-商家确认收货；7-买家确认收货 */
	private short status;

	/**
	 * 预计送达时间
	 */
	private Date deliveryTime;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 拒单备注 */
	private String rejectRemark;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/**
	 * 是否第三方配送，1：是；其他-否
	 */
	private short isThirdExpress;

	/**
	 * 是否商家自行配送，1：是；其他-否
	 */
	private short isStoreExpress;

	/** 评论数量 **/
	private int commentCount;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
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

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

	public short getIsStoreExpress() {
		return isStoreExpress;
	}

	public void setIsStoreExpress(short isStoreExpress) {
		this.isStoreExpress = isStoreExpress;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

}
