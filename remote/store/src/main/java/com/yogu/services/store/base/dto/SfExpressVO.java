package com.yogu.services.store.base.dto;

/**
 * 顺丰配送对象
 *
 * @date 2016年10月8日 上午9:50:03
 * @author hins
 */
public class SfExpressVO {
	
	/**
	 * 配送规则id
	 */
	private long vid;
	
	/**
	 * 餐厅id
	 */
	private long storeId;
	
	/**
	 * 配送方需要的店铺编码
	 */
	private String storeCode;
	
	/**
	 * 用户应该承担运费，单位分
	 */
	private long userBearFee;
	
	/**
	 * 商家应该承担运费，单位分
	 */
	private long merchantBearFee;
	
	/**
	 * 平台应该承担运费，单位分
	 */
	private long mzBearFee;
	
	/**
	 * 订单科目
	 */
	private short orderCategory;
	
	/** 
	 * 店铺联系人姓名
	 */
	private String storeContacts = "";

	/** 
	 * 店铺联系人电话 
	 */
	private String storePhone = "";
	
	/**
	 * 备注，如满免，用户承担的费用转移到商家
	 */
	private String remark = "";

	public long getVid() {
		return vid;
	}

	public void setVid(long vid) {
		this.vid = vid;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public long getUserBearFee() {
		return userBearFee;
	}

	public void setUserBearFee(long userBearFee) {
		this.userBearFee = userBearFee;
	}

	public long getMerchantBearFee() {
		return merchantBearFee;
	}

	public void setMerchantBearFee(long merchantBearFee) {
		this.merchantBearFee = merchantBearFee;
	}

	public long getMzBearFee() {
		return mzBearFee;
	}

	public void setMzBearFee(long mzBearFee) {
		this.mzBearFee = mzBearFee;
	}

	public short getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(short orderCategory) {
		this.orderCategory = orderCategory;
	}

	public String getStoreContacts() {
		return storeContacts;
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
