package com.yogu.core.consumer.vo;

/**
 * 商家申请提现对账结果vo
 *
 * @date 2016年6月21日 上午9:43:46
 * @author hins
 */
public class StoreApplyWithdrawVO {
	
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
	 * 提现手续费流水-出账
	 */
	private long chargesSubtractFee;
	
	/**
	 * 提现补贴手续费-入账
	 */
	private long subsidyPushFee;
	
	/**
	 * 提现补贴手续费-出账
	 */
	private long subsidySubtractFee;
	
	/**
	 * 正常流水-入账
	 */
	private long pushFee;
	
	/**
	 * 正常流水-出账
	 */
	private long subtractFee;

	public long getWithDrawId() {
		return withDrawId;
	}

	public void setWithDrawId(long withDrawId) {
		this.withDrawId = withDrawId;
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

	public long getChargesSubtractFee() {
		return chargesSubtractFee;
	}

	public void setChargesSubtractFee(long chargesSubtractFee) {
		this.chargesSubtractFee = chargesSubtractFee;
	}

	public long getSubsidyPushFee() {
		return subsidyPushFee;
	}

	public void setSubsidyPushFee(long subsidyPushFee) {
		this.subsidyPushFee = subsidyPushFee;
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

	public long getSubtractFee() {
		return subtractFee;
	}

	public void setSubtractFee(long subtractFee) {
		this.subtractFee = subtractFee;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	
}
