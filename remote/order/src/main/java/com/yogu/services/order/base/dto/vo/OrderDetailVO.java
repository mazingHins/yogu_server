package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yogu.services.store.dish.dto.DishDetailVO;

/**
 * @Description: 用户端订单详情接口VO
 * @author Hins
 * @date 2015年8月19日 上午10:13:13
 */
public class OrderDetailVO implements Serializable {

	private static final long serialVersionUID = -9055355293991770682L;

	/** 店家id */
	private long storeId;

	/** cblog餐厅id */
	private long sinfoId;

	/** 店家名称 */
	private String storeName;

	/**
	 * 店家英文名称
	 */
	private String storeNameEn;

	/** 店家电话，可能多个，英文逗号隔开 */
	private String storePhone;

	/** 店家电话，可能多个，英文逗号隔开 */
	private String storePhones;// add by sky,2016-01-30 真正的支持多个客服电话的字段

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

	// 新增orderNo topicImg
	/** 订单编号 */
	private Long orderNo;

	/** 瀑布流图片路径，门店收藏列表中也使用此图。 */
	private String topicImg;

	/** 支付类型；1：线上支付、2：现金支付 */
	private short payType;

	/** 支付方式，1-支付宝；2-微信 */
	private short payMode = 0;

	/** 服务日期（年月日的数值形式，月日各为两位数；例如：20150709） */
	private int serviceDay = 0;

	/** 送餐地址全称 */
	private String fullAddress;

	/** 规格id */
	private long specId;

	/** 规格名称 */
	private String specName;

	/** 规格备注, 多个用英文逗号分隔 */
	private String supplements;

	/** 餐厅logo */
	private String logoPath;

	/** 订单预计送达时间的展示模式 1 - 尽快送达; 2 - 预订 */
	private short deliveryTimeMode;

	/**
	 * 配送人电话
	 */
	private String deliveryPhone;
	
	/**
	 * 配送人姓名
	 */
	private String deliveryUserName;
	
	public long getSpecId() {
		return specId;
	}

	public void setSpecId(long specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSupplements() {
		return supplements;
	}

	public void setSupplements(String supplements) {
		this.supplements = supplements;
	}

	public String getStorePhones() {
		return storePhones;
	}

	public void setStorePhones(String storePhones) {
		this.storePhones = storePhones;
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

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
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

	public String getTopicImg() {
		return topicImg;
	}

	public void setTopicImg(String topicImg) {
		this.topicImg = topicImg;
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


	public long getSinfoId() {
		return sinfoId;
	}

	public void setSinfoId(long sinfoId) {
		this.sinfoId = sinfoId;
	}

	public String getStoreNameEn() {
		return storeNameEn;
	}

	public void setStoreNameEn(String storeNameEn) {
		this.storeNameEn = storeNameEn;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public short getDeliveryTimeMode() {
		return deliveryTimeMode;
	}

	public void setDeliveryTimeMode(short deliveryTimeMode) {
		this.deliveryTimeMode = deliveryTimeMode;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public String getDeliveryUserName() {
		return deliveryUserName;
	}

	public void setDeliveryUserName(String deliveryUserName) {
		this.deliveryUserName = deliveryUserName;
	}

}
