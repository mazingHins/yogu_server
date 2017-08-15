package com.yogu.services.order.base.service.param;

import javax.ws.rs.FormParam;

public class CreateTeamBuyParam {
	/**
	 * 终端IP
	 */
	private String userIp;

	/**
	 * 支付方式，1-支付宝；2-微信
	 */
	@FormParam("payMode")
	private short payMode;

	/**
	 * 支付货币类型：1-人民币
	 */
	@FormParam("currencyType")
	private short currencyType;

	/**
	 * 参团类型，1=发起团购，2=参加团购
	 */
	@FormParam("teamType")
	private short teamType;

	/**
	 * 购买id，当teamType=1时，objectId=team_pay_id, 当teamType=2时，objectId=buy_id
	 */
	@FormParam("objectId")
	private long objectId;

	private short merchantType;

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public short getPayMode() {
		return payMode;
	}

	public void setPayMode(short payMode) {
		this.payMode = payMode;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getTeamType() {
		return teamType;
	}

	public void setTeamType(short teamType) {
		this.teamType = teamType;
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public short getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(short merchantType) {
		this.merchantType = merchantType;
	}

}
