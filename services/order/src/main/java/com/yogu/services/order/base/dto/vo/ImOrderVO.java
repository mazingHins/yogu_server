package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yogu.services.store.dish.dto.DishDetailVO;

/**
 * @Description: IM聊天界面订单信息VO
 * @author Hins
 * @date 2015年8月19日 上午10:07:11
 */
public class ImOrderVO implements Serializable {

	private static final long serialVersionUID = -2088449193934101133L;

	/** 订单编号 */
	long orderNo;

	/** 用餐人数，为0则表示该订单不支持选择用餐人数 */
	private short mealNumber = 0;

	/** 订单备注 */
	private String remark;

	/** 订单状态：1-未付款；2-已付款；3-已接单；4-制作中；5-配送中；6-商家确认收货；7-买家确认收货 */
	private short status;

	/** 订单总价格 */
	private long totalFee;

	/** 商品总价格 */
	private long goodsFee;

	/** 支付货币类型：1-人民币 */
	private short currencyType;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单修改时间默认值为创建时间 */
	private Date updateTime;

	/** 送货地址 */
	private String address;

	/** 收货联系人 */
	private String contacts;

	/** 收货联系电话 */
	private String phone;

	/** 送餐地址全称 */
	private String fullAddress;
	
	/** 订单的所有菜品名, 服务端拼接输出 */
	private String dishName;
	
	/** 订单明细 */
	private List<DishDetailVO> detail;
	
	/** 用户确认收货时间 */
	private Date userConfirmTime;

	/** 商家确认收货时间 */
	private Date storeConfirmTime;
	
	private long remainTime;
	
	/** 订单总用时 */
	private long duration;
	
	/** 寄送时间 */
	private Date deliveryTime;
	
	/** 拒单备注 */
	private String rejectRemark = "";

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

	public long getDuration() {
		return duration;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public short getMealNumber() {
		return mealNumber;
	}

	public void setMealNumber(short mealNumber) {
		this.mealNumber = mealNumber;
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

	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
	}

	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
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

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public List<DishDetailVO> getDetail() {
		return detail;
	}

	public void setDetail(List<DishDetailVO> detail) {
		this.detail = detail;
	}

	public long getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(long remainTime) {
		this.remainTime = remainTime;
	}
	

}
