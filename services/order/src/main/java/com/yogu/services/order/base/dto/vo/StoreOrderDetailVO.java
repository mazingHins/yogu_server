package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yogu.services.store.dish.dto.DishDetailVO;

/**
 * 商家端订单详情接口VO
 * 
 * @author Hins
 * @date 2015年9月8日 下午3:24:40
 */
public class StoreOrderDetailVO implements Serializable {

	private static final long serialVersionUID = -7159928650079807380L;

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

	/** 订单明细 */
	private List<DishDetailVO> detail;

	/** 订单总价格 */
	private long totalFee;

	/** 优惠总价格 */
	private long totalDiscountFee;

	/** 商品总价格 */
	private long goodsFee;

	/** 订单创建时间 */
	private Date createTime;

	/** 配送费信息 */
	private SendInfoVO send;

	/** 下单可选特殊项 */
	private String featureContent;

	/** 订单编号 */
	private Long orderNo;

	/** 下单人IMID */
	private long imId;

	/** 订单用时 （秒） */
	private long duration;

	/** 当天订单序列号 */
	private String serialNumber;

	/** 打印次数 */
	private short printNumber;

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 商家内部回退标识；1：是、其他：否 */
	private short isBack = 0;

	/** 退回时间间隔 */
	private long backTimeGap;

	/** 退回次数 */
	private short backNumber = 0;

	/** 订单退回来源状态 */
	private short backSourceStatus;

	/** 剩余时间 **/
	private long remainTime;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 送餐地址全称 */
	private String fullAddress;
	
	/** 订单预计送达时间的展示模式  1 - 尽快送达; 2 - 预订*/
	private short deliveryTimeMode;
	
	/**
	 * 是否允许退款：1-是；其他-否
	 */
	private short allowRefund;
	
	/** 商家对用户的评分..因为旧的订单列表中商家对用户评分的字段是star，所以详情也用这个了... */
	private short star;
	
	/** 是否第三方配送. 1：是；其他否 */
	private short isThirdExpress;
	
	/** 第三方配送状态 */
	private short expressStatus;
	
	/** 商家确认收货时间 */
	private Date storeConfirmTime;
	
	/** 拒单备注 */
	private String rejectRemark;
	
	public short getDeliveryTimeMode() {
		return deliveryTimeMode;
	}

	public void setDeliveryTimeMode(short deliveryTimeMode) {
		this.deliveryTimeMode = deliveryTimeMode;
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

	public List<DishDetailVO> getDetail() {
		return detail;
	}

	public void setDetail(List<DishDetailVO> detail) {
		this.detail = detail;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getTotalDiscountFee() {
		return totalDiscountFee;
	}

	public void setTotalDiscountFee(long totalDiscountFee) {
		this.totalDiscountFee = totalDiscountFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SendInfoVO getSend() {
		return send;
	}

	public void setSend(SendInfoVO send) {
		this.send = send;
	}

	public String getFeatureContent() {
		return featureContent;
	}

	public void setFeatureContent(String featureContent) {
		this.featureContent = featureContent;
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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
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

	public short getIsBack() {
		return isBack;
	}

	public void setIsBack(short isBack) {
		this.isBack = isBack;
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

	public long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(long remainTime) {
		this.remainTime = remainTime;
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
	
	public short getStar() {
		return star;
	}

	public void setStar(short star) {
		this.star = star;
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

	public short getExpressStatus() {
		return expressStatus;
	}

	public void setExpressStatus(short expressStatus) {
		this.expressStatus = expressStatus;
	}

	public Date getStoreConfirmTime() {
		return storeConfirmTime;
	}

	public void setStoreConfirmTime(Date storeConfirmTime) {
		this.storeConfirmTime = storeConfirmTime;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}
	
}
