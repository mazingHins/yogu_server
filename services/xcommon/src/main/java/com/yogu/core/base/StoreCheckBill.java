package com.yogu.core.base;

import java.io.Serializable;
import java.util.List;

/**
 * 商家对账信息VO，此对象适用于pay域获得流水相关数据
 * 
 * @author Hins
 * @date 2015年11月27日 下午9:09:34
 */
public class StoreCheckBill implements Serializable {

	private static final long serialVersionUID = 543946089261314761L;

	/**
	 * 商家ID
	 */
	private long storeId;

	/** 余额类型：1-账户余额；2-交易中金额; 3-待入账余额; 4-积分余额; */
	private short balanceType;

	/**
	 * 账户余额
	 */
	private long balance;
	
	/**
	 * 资金（在交易中/待入账除开优惠券）流水流入总金额
	 */
	private long plusBalance;
	
	/**
	 * 资金（在交易中/待入账除开优惠券）流水流出总金额
	 */
	private long subtractBalance;
	
	/**
	 * 用户配送费入账总额
	 */
	private long userDeliveryPushBalance;
	
	/**
	 * 用户配送费出账总额
	 */
	private long userDeliverySubtractBalance;
	
	/**
	 * 商家配送费出账总额
	 */
	private long storeDeliverySubtractBalance;
	
	/**
	 * 优惠券流水流入总金额
	 */
	private long couponPlusBalance;
	
	/**
	 * 优惠券流水流出总金额
	 */
	private long couponSubtractBalance;

	/**
	 * 对账结果
	 */
	private int code;
	
	/**
	 * 对冲金额（用于验证：余额=开始时间最后一条流水的余额-流出+流入），所以对冲金额=开始时间最后一条流水的余额
	 * 此金额不包括优惠券部分
	 */
	private long normalHedge;
	
	/**
	 * 优惠券对冲金额
	 */
	private long couponHedge;
	
	/**
	 * 配送费的对冲金额
	 */
	private long deliveryHedge;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public short getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(short balanceType) {
		this.balanceType = balanceType;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getPlusBalance() {
		return plusBalance;
	}
	
	public long getCouponPlusBalance() {
		return couponPlusBalance;
	}

	public void setCouponPlusBalance(long couponPlusBalance) {
		this.couponPlusBalance = couponPlusBalance;
	}

	public long getCouponSubtractBalance() {
		return couponSubtractBalance;
	}

	public void setCouponSubtractBalance(long couponSubtractBalance) {
		this.couponSubtractBalance = couponSubtractBalance;
	}

	public void setPlusBalance(long plusBalance) {
		this.plusBalance = plusBalance;
	}

	public long getSubtractBalance() {
		return subtractBalance;
	}

	public void setSubtractBalance(long subtractBalance) {
		this.subtractBalance = subtractBalance;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getNormalHedge() {
		return normalHedge;
	}

	public void setNormalHedge(long normalHedge) {
		this.normalHedge = normalHedge;
	}

	public long getCouponHedge() {
		return couponHedge;
	}

	public void setCouponHedge(long couponHedge) {
		this.couponHedge = couponHedge;
	}

	public long getUserDeliveryPushBalance() {
		return userDeliveryPushBalance;
	}

	public void setUserDeliveryPushBalance(long userDeliveryPushBalance) {
		this.userDeliveryPushBalance = userDeliveryPushBalance;
	}

	public long getUserDeliverySubtractBalance() {
		return userDeliverySubtractBalance;
	}

	public void setUserDeliverySubtractBalance(long userDeliverySubtractBalance) {
		this.userDeliverySubtractBalance = userDeliverySubtractBalance;
	}

	public long getDeliveryHedge() {
		return deliveryHedge;
	}

	public void setDeliveryHedge(long deliveryHedge) {
		this.deliveryHedge = deliveryHedge;
	}

	public long getStoreDeliverySubtractBalance() {
		return storeDeliverySubtractBalance;
	}

	public void setStoreDeliverySubtractBalance(long storeDeliverySubtractBalance) {
		this.storeDeliverySubtractBalance = storeDeliverySubtractBalance;
	}
	
}
