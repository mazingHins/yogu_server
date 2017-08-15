package com.yogu.services.order.base.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.yogu.services.order.base.dto.vo.OrderErrorVO;

/**
 * 订单表
 * 
 */
public class Order implements Serializable {

	private static final long serialVersionUID = -3074457346803597409L;

	/** 订单ID */
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

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 下单的渠道, 暂时有iphone, android和H5(mobile) add by felix 2016-04-28 */
	private String orderChannel = "";

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 订单金额（应付金额，精确到分） */
	private long totalFee = 0;

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

	/** 配送方式；1：商家配送、2：自提、3：快递 */
	private short pickType;

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
	 * 45-申请退款；50-退款中；55-拒绝退款；60-已退款；65-订单已取消；
	 */
	private short status;

	/** 取消的操作来源：1-用户取消；2-商家取消；3-米星管理员取消；4-定时任务取消.取值范围参考枚举：CancelOrderSource */
	private short cancelSource;

	/** 订单备注 */
	private String remark;

	/** 运费说明 */
	private String deliveryRemark;

	/** 订单开始时间；现金支付：下单时间、在线支付：支付成功时间 */
	private Date orderBeginTime;

	/** 接单时间 */
	private Date acceptTime;

	/** 用户确认收货时间 */
	private Date userConfirmTime;

	/** 商家确认收货时间 */
	private Date storeConfirmTime;

	/** 系统自动确认签收标识; 1-否, 2-是 */
	private short sysConfirm = 1;

	/** 商家内部回退标识；1：是、其他：否 */
	private short isBack = 0;

	/** 退回时间-当内部退回标识为1时，才不为空 */
	private Date backTime;

	/** 退回次数 */
	private short backNumber = 0;

	/** 订单退回来源状态 */
	private short backSourceStatus = 0;

	/** 拒单备注 */
	private String rejectRemark;

	/** 用户下单时-桌号的备注 */
	private String userTableNoRemark = "";

	/** 商家输入的订单备注 */
	private String storeRemark = "";

	/** 当天订单序列号 */
	private String serialNumber = "";

	/** 打印次数 */
	private short printNumber = 0;

	/** 用户所处的商家配送范围ID */
	private long serviceRangeId;

	/** 用户所处的商家配送范围名称 */
	private String serviceRangeName = "";

	/** 用户所处的商家配送范围所需的时间（分钟） */
	private int serviceTime;

	/** 是否指定了第三方配送（这个值在下单时候指定后，就不改变，即使在配送时商家自行配送），1：是，其他否 */
	private short isThirdExpress = 0;

	/** 是否商家自行配送(在商家选择配送时候确定)，1：是，其他否 */
	private short isStoreExpress = 1;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 商家准备菜品所需的时间（分钟） */
	private int makingTime;

	/** _id` bigint(20) NOT NULL COMMENT 订单评论id */
	private long commentId;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 订单完成时间 */
	private Date finishTime;

	/** 错误信息列表 */
	private List<OrderErrorVO> errorList;

	/** 下单结果状态 */
	private short result;

	/** 是否支持现金支付, 1支持, 其他不支持（在创建订单装载可选支付方式时候需要） */
	private short supportCash;

	/** 优惠券承担方类型,1:平台型. 2:商家型 （在创建订单获取pay域支付信息时候需要） */
	private Short couponBearType;

	/** 关联活动id */
	private String objectId;
	
	/**
	 * 发票抬头
	 */
	private String invoiceTitle = "";

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

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setPayType(short payType) {
		this.payType = payType;
	}

	public short getPayType() {
		return payType;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getPayMode() {
		return payMode;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
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

	public void setPickType(short pickType) {
		this.pickType = pickType;
	}

	public short getPickType() {
		return pickType;
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

	public void setMealNumber(short mealNumber) {
		this.mealNumber = mealNumber;
	}

	public short getMealNumber() {
		return mealNumber;
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

	public short getCancelSource() {
		return cancelSource;
	}

	public void setCancelSource(short cancelSource) {
		this.cancelSource = cancelSource;
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

	public void setOrderBeginTime(Date orderBeginTime) {
		this.orderBeginTime = orderBeginTime;
	}

	public Date getOrderBeginTime() {
		return orderBeginTime;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public void setUserConfirmTime(Date userConfirmTime) {
		this.userConfirmTime = userConfirmTime;
	}

	public Date getUserConfirmTime() {
		return userConfirmTime;
	}

	public void setStoreConfirmTime(Date storeConfirmTime) {
		this.storeConfirmTime = storeConfirmTime;
	}

	public Date getStoreConfirmTime() {
		return storeConfirmTime;
	}

	public short getSysConfirm() {
		return sysConfirm;
	}

	public void setSysConfirm(short sysConfirm) {
		this.sysConfirm = sysConfirm;
	}

	// 2016-01-13 modify by hins 内容：没有了商家退回订单流程，此方法不推荐使用
	@Deprecated
	public void setIsBack(short isBack) {
		this.isBack = isBack;
	}

	// 2016-01-13 modify by hins 内容：没有了商家退回订单流程，此方法不推荐使用
	@Deprecated
	public short getIsBack() {
		return isBack;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackNumber(short backNumber) {
		this.backNumber = backNumber;
	}

	public short getBackNumber() {
		return backNumber;
	}

	public void setBackSourceStatus(short backSourceStatus) {
		this.backSourceStatus = backSourceStatus;
	}

	public short getBackSourceStatus() {
		return backSourceStatus;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public String getRejectRemark() {
		return rejectRemark;
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

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setPrintNumber(short printNumber) {
		this.printNumber = printNumber;
	}

	public short getPrintNumber() {
		return printNumber;
	}

	public void setServiceRangeId(long serviceRangeId) {
		this.serviceRangeId = serviceRangeId;
	}

	public long getServiceRangeId() {
		return serviceRangeId;
	}

	public void setServiceRangeName(String serviceRangeName) {
		this.serviceRangeName = serviceRangeName;
	}

	public String getServiceRangeName() {
		return serviceRangeName;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public void setMakingTime(int makingTime) {
		this.makingTime = makingTime;
	}

	public int getMakingTime() {
		return makingTime;
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

	public List<OrderErrorVO> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<OrderErrorVO> errorList) {
		this.errorList = errorList;
	}

	public short getResult() {
		return result;
	}

	public void setResult(short result) {
		this.result = result;
	}

	public void setDeliverId(long deliverId) {
		this.deliverId = deliverId;
	}

	public long getDeliverId() {
		return deliverId;
	}

	public short getSupportCash() {
		return supportCash;
	}

	public void setSupportCash(short supportCash) {
		this.supportCash = supportCash;
	}

	public Short getCouponBearType() {
		return couponBearType;
	}

	public void setCouponBearType(Short couponBearType) {
		this.couponBearType = couponBearType;
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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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
