package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商家订单列表 数据展示实体封装
 * 
 * @author sky
 * 
 */
public class StoreOrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 订单编号 */
	private long orderNo;

	/** 送货地址 */
	private String address;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** im id **/
	private long imId;

	/** 订单状态：10-未付款；15-已付款；20-已接单；25-配送中；30-商家确认收货；35-买家确认收货；40-已评论； 45-申请退款；50-退款中；55-拒绝退款；60-已退款；65-订单已取消； */
	private short status;

	/** 应付金额（精确到分） */
	private long totalFee;

	/** 订单创建时间 */
	private Date createTime;

	/** 剩余时间 **/
	private long remainTime;

	/** 用时 **/
	private long useTime;

	/** 商家内部回退标识；1：是、其他：否 */
	private short isBack = 0;

	/** 退回时间间隔 */
	private long backTimeGap;

	/** 退回次数 */
	private short backNumber = 0;

	/** 订单退回来源状态 */
	private short backSourceStatus;

	/** 当天订单序列号 */
	private int serialNumber;

	/** 当天订单排序号 */
	private long sequence;

	/** 用户所处的商家配送范围名称 */
	private String serviceRangeName;

	/** 订单界面选择商家信息（如口味） */
	private String featureContent;

	/** 订单备注 */
	private String remark;

	/** 打印次数 */
	private short printNumber;

	/** 菜品 **/
	private String dishNames = "";

	/** 菜品总数 **/
	private int dishTotal;

	/**退款状态：1-退款申请中；2-退款中；3-拒绝退款 ；4-退款成功；**/
	private short refundStatus;

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 配送费（精确到分） */
	private long deliveryFee = 0;

	/** 优惠总价（精确到分） */
	private long discountFee = 0;

	/** 商品总价（精确到分） */
	private long goodsFee;

	/** 配送方式；1：商家配送、2：自提、3：快递 */
	private short pickType;

	/** 寄送时间 */
	private Date deliveryTime;

	/** 纬度 */
	private double lat;

	/** 经度 */
	private double lng;

	/** 运费说明 */
	private String deliveryRemark;

	/** 订单开始时间；现金支付：下单时间、在线支付：支付成功时间 */
	private Date orderBeginTime;

	/** 用户确认收货时间 */
	private Date userConfirmTime;

	/** 商家确认收货时间 */
	private Date storeConfirmTime;

	/** 商家准备菜品所需的时间（分钟） */
	private int makingTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 商家对用户的评分 */
	private int star = 0;

	/** 订单创建到结束总用时（秒） */
	private int duration;

	/** 订单退款创建时间 */
	private Date refundCreateTime;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 送餐地址全称 */
	private String fullAddress;

	/** 拒单备注 */
	private String rejectRemark;

	/**
	 * 是否允许退款：1-是；其他-否
	 */
	private short allowRefund;

	/**
	 * 是否是第三方配送，1-是；其他-否
	 */
	private short isThirdExpress;

	/** 订单预计送达时间的展示模式  1 - 尽快送达; 2 - 预订*/
	private short deliveryTimeMode;

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public Date getRefundCreateTime() {
		return refundCreateTime;
	}

	public void setRefundCreateTime(Date refundCreateTime) {
		this.refundCreateTime = refundCreateTime;
	}

	public short getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(short refundStatus) {
		this.refundStatus = refundStatus;
	}

	public short getPickType() {
		return pickType;
	}

	public void setPickType(short pickType) {
		this.pickType = pickType;
	}

	public long getImId() {
		return imId;
	}

	public void setImId(long imId) {
		this.imId = imId;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getPayMode() {
		return payMode;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
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

	public String getDishNames() {
		return dishNames;
	}

	public void setDishNames(String dishNames) {
		this.dishNames = dishNames;
	}

	public int getDishTotal() {
		return dishTotal;
	}

	public void setDishTotal(int dishTotal) {
		this.dishTotal = dishTotal;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public Date getOrderBeginTime() {
		return orderBeginTime;
	}

	public void setOrderBeginTime(Date orderBeginTime) {
		this.orderBeginTime = orderBeginTime;
	}

	public Date getUserConfirmTime() {
		return userConfirmTime;
	}

	public void setUserConfirmTime(Date userConfirmTime) {
		this.userConfirmTime = userConfirmTime;
	}

	public Date getStoreConfirmTime() {
		return storeConfirmTime;
	}

	public void setStoreConfirmTime(Date storeConfirmTime) {
		this.storeConfirmTime = storeConfirmTime;
	}

	public short getIsBack() {
		return isBack;
	}

	public void setIsBack(short isBack) {
		this.isBack = isBack;
	}

	public String getServiceRangeName() {
		return serviceRangeName;
	}

	public void setServiceRangeName(String serviceRangeName) {
		this.serviceRangeName = serviceRangeName;
	}

	public int getMakingTime() {
		return makingTime;
	}

	public void setMakingTime(int makingTime) {
		this.makingTime = makingTime;
	}

	public long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(long remainTime) {
		this.remainTime = remainTime;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	public long getBackTimeGap() {
		return backTimeGap;
	}

	public void setBackTimeGap(long backTimeGap) {
		this.backTimeGap = backTimeGap;
	}

	public short getBackNumber() {
		return backNumber;
	}

	public void setBackNumber(short backNumber) {
		this.backNumber = backNumber;
	}

	public short getBackSourceStatus() {
		return backSourceStatus;
	}

	public void setBackSourceStatus(short backSourceStatus) {
		this.backSourceStatus = backSourceStatus;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public short getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(short printNumber) {
		this.printNumber = printNumber;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public short getAllowRefund() {
		return allowRefund;
	}

	public void setAllowRefund(short allowRefund) {
		this.allowRefund = allowRefund;
	}

	public short getIsThirdExpress() {
		return isThirdExpress;
	}

	public void setIsThirdExpress(short isThirdExpress) {
		this.isThirdExpress = isThirdExpress;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public short getDeliveryTimeMode() {
		return deliveryTimeMode;
	}

	public void setDeliveryTimeMode(short deliveryTimeMode) {
		this.deliveryTimeMode = deliveryTimeMode;
	}

}
