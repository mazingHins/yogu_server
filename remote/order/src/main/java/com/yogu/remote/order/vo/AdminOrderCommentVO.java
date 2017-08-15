package com.yogu.remote.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理系统查看订单评论信息VO
 *
 * @date 2016年7月12日 下午4:41:55
 * @author hins
 */
public class AdminOrderCommentVO implements Serializable {

	private static final long serialVersionUID = -7707824721861607466L;
	
	/** 订单id */
	private long orderId;
	
	/** 评论id */
	private long commentId;
	
	/** 订单编号 */
	private long orderNo;
	
	/** 餐厅id */
	private long storeId;
	
	/** 餐厅名称 */
	private String storeName;
	
	/** 当天订单序列号 */
	private String serialNumber = "";
	
	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** 送货地址 */
	private String address;
	
	/** 下单人用户id */
	private long uid;
	
	/** 下单人通信证 */
	private String userNickname;
	
	/** 评论星级 */
	private short star;

	/** 反馈问题编码 */
	private short feedbackCode = 0;

	/** 评论文字内容 */
	private String content;
	
	/** 评论创建时间 */
	private Date createTime;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public short getStar() {
		return star;
	}

	public void setStar(short star) {
		this.star = star;
	}

	public short getFeedbackCode() {
		return feedbackCode;
	}

	public void setFeedbackCode(short feedbackCode) {
		this.feedbackCode = feedbackCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
