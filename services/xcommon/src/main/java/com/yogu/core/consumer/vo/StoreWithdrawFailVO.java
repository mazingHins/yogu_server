package com.yogu.core.consumer.vo;

/**
 * 商家提现失败对账结果vo
 *
 * @date 2016年6月21日 上午10:06:12
 * @author hins
 */
public class StoreWithdrawFailVO {
	
	/**
	 * 提现申请id
	 */
	private long withDrawId;
	
	/**
	 * 门店id
	 */
	private long storeId;
	
	/**
	 * 申请提现金额
	 */
	private long applyAmount;
	
	/**
	 * 实际到账金额
	 */
	private long amount;
	
	/**
	 * 提现手续费
	 */
	private long withDrawChargesFee;
	
	/**
	 * 提现补贴手续费
	 */
	private long withDrawSubsidy;
	
	/**
	 * 提现手续费流水-入账
	 */
	private long chargesPushFee;
	
	/**
	 * 提现补贴手续费-出账
	 */
	private long subsidySubtractFee;
	
	/**
	 * 正常流水-入账
	 */
	private long pushFee;
	
	public long getWithDrawId() {
		return withDrawId;
	}

	public void setWithDrawId(long withDrawId) {
		this.withDrawId = withDrawId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(long applyAmount) {
		this.applyAmount = applyAmount;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getWithDrawChargesFee() {
		return withDrawChargesFee;
	}

	public void setWithDrawChargesFee(long withDrawChargesFee) {
		this.withDrawChargesFee = withDrawChargesFee;
	}

	public long getWithDrawSubsidy() {
		return withDrawSubsidy;
	}

	public void setWithDrawSubsidy(long withDrawSubsidy) {
		this.withDrawSubsidy = withDrawSubsidy;
	}

	public long getChargesPushFee() {
		return chargesPushFee;
	}

	public void setChargesPushFee(long chargesPushFee) {
		this.chargesPushFee = chargesPushFee;
	}

	public long getSubsidySubtractFee() {
		return subsidySubtractFee;
	}

	public void setSubsidySubtractFee(long subsidySubtractFee) {
		this.subsidySubtractFee = subsidySubtractFee;
	}

	public long getPushFee() {
		return pushFee;
	}

	public void setPushFee(long pushFee) {
		this.pushFee = pushFee;
	}

}
