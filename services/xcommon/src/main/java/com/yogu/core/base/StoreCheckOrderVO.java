package com.yogu.core.base;

import java.io.Serializable;

/**
 * 总对账时，订单域返回的VO
 *
 * @date 2016年8月18日 上午10:24:37
 * @author hins
 */
public class StoreCheckOrderVO implements Serializable {

	private static final long serialVersionUID = 5269929460621920503L;
	
	/**
	 * 餐厅id
	 */
	private long storeId;
	
	/**
	 * 总金额，单位分
	 */
	private long totalFee;
	
	/**
	 * 实付金额，单位分
	 */
	private long actualFee;
	
	/**
	 * 优惠金额，单位分
	 */
	private long discountFee;
	
	/**
	 * 用户承担配送费
	 */
	private long userDeliveryFee;
	
	/**
	 * 接单时候收取了用户配送费，但是
	 */
	private long cancelUserDeliveryFee;
	
	/**
	 * 商家承担配送费
	 */
	private long storeDeliveryFee;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	
	public long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(long totalFee) {
		this.totalFee = totalFee;
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

	public long getUserDeliveryFee() {
		return userDeliveryFee;
	}

	public void setUserDeliveryFee(long userDeliveryFee) {
		this.userDeliveryFee = userDeliveryFee;
	}

	public long getCancelUserDeliveryFee() {
		return cancelUserDeliveryFee;
	}

	public void setCancelUserDeliveryFee(long cancelUserDeliveryFee) {
		this.cancelUserDeliveryFee = cancelUserDeliveryFee;
	}

	public long getStoreDeliveryFee() {
		return storeDeliveryFee;
	}

	public void setStoreDeliveryFee(long storeDeliveryFee) {
		this.storeDeliveryFee = storeDeliveryFee;
	}
	
}
