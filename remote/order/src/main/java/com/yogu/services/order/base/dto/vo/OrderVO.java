package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description: 进行中订单接口VO
 * @author Hins
 * @date 2015年8月19日 上午10:14:45
 */
public class OrderVO implements Serializable {

	private static final long serialVersionUID = -5212897221356754536L;

	/** 订单编号 */
	private String orderNo;

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 店家id */
	private long storeId;

	/** 店家名称 */
	private String storeName;

	/** 门店LOGO。 */
	private String logoImg;

	/** 是否支持现金支付, 1支持, 其他不支持 */
	private short supportCash;

	/** 门店联系电话。 */
	private String storePhone;

	/** 店家电话，可能多个，英文逗号隔开 */
	private String storePhones;// add by sky,2016-01-30 真正的支持多个客服电话的字段

	/** 寄送时间 */
	private Date deliveryTime;

	/** 当前状态消耗时长 */
	private long duration;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间 */
	private Date finishTime;

	/** 订单跟踪明细 */
	private List<OrderTrackVO> trackList;

	/** 配送方式 1:商家配送 2:自提 3:快递 **/
	private short pickType;

	/** 应付金额（精确到分） */
	private long totalFee;

	/** 送货地址 */
	private String address;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 送餐地址全称 */
	private String fullAddress;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 评论数量 */
	private int commentCount;

	/** 订单预计送达时间的展示模式  1 - 尽快送达; 2 - 预订*/
	private short deliveryTimeMode;
	
	public String getStorePhones() {
		return storePhones;
	}

	public void setStorePhones(String storePhones) {
		this.storePhones = storePhones;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
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

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<OrderTrackVO> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<OrderTrackVO> trackList) {
		this.trackList = trackList;
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

	public short getPickType() {
		return pickType;
	}

	public void setPickType(short pickType) {
		this.pickType = pickType;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
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

	public short getSupportCash() {
		return supportCash;
	}

	public void setSupportCash(short supportCash) {
		this.supportCash = supportCash;
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

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public short getDeliveryTimeMode() {
		return deliveryTimeMode;
	}

	public void setDeliveryTimeMode(short deliveryTimeMode) {
		this.deliveryTimeMode = deliveryTimeMode;
	}

}
