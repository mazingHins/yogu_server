package com.yogu.services.store.base.dto;

/**
 * 直接命中那个‘服务范围’配置 <br>
 *
 * @author JFan 2015年8月5日 下午4:05:57
 */
public class ServiceRangeVO {

	/** 配送范围ID */
	private long rangeId;

	/** 配送服务名 */
	private String name;

	/** 配送服务备注 */
	private String remark;

	// 目前只有一种：多边形
	// /** 设定类型；1：半径、2：多边形 */
	// private short cutType;
	//
	// /** cut_type为1：配送半径（米）、cut_type为2时：[ [lat lng] ...] 坐标点数组（至少3个）确定一个面 */
	// private String cutValue;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 起送金额（分） */
	private int minimumMoney;

	/** 费用类型：1-免费；2-付费；3-的士费*1；4-的士费*2 */
	private short moneyType = 1;

	/** 配送费用（分） */
	private int money;

	/** 免运费金额（分）（小于等于0表示不做"满免"） */
	private int fullFreeFreight;

	/** 配送时间（分钟） */
	private int minute;

	/** 是否启用守时配送，也就是在minute时间内送达；1：启用、其他：不启用 */
	private short punctuality = 0;

	/**
	 * @return rangeId
	 */
	public long getRangeId() {
		return rangeId;
	}

	/**
	 * @param rangeId 要设置的 rangeId
	 */
	public void setRangeId(long rangeId) {
		this.rangeId = rangeId;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark 要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return currencyType
	 */
	public short getCurrencyType() {
		return currencyType;
	}

	/**
	 * @param currencyType 要设置的 currencyType
	 */
	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * @return minimumMoney
	 */
	public int getMinimumMoney() {
		return minimumMoney;
	}

	/**
	 * @param minimumMoney 要设置的 minimumMoney
	 */
	public void setMinimumMoney(int minimumMoney) {
		this.minimumMoney = minimumMoney;
	}

	/**
	 * @return moneyType
	 */
	public short getMoneyType() {
		return moneyType;
	}

	/**
	 * @param moneyType 要设置的 moneyType
	 */
	public void setMoneyType(short moneyType) {
		this.moneyType = moneyType;
	}

	/**
	 * @return money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money 要设置的 money
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return fullFreeFreight
	 */
	public int getFullFreeFreight() {
		return fullFreeFreight;
	}

	/**
	 * @param fullFreeFreight 要设置的 fullFreeFreight
	 */
	public void setFullFreeFreight(int fullFreeFreight) {
		this.fullFreeFreight = fullFreeFreight;
	}

	/**
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @param minute 要设置的 minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * @return punctuality
	 */
	public short getPunctuality() {
		return punctuality;
	}

	/**
	 * @param punctuality 要设置的 punctuality
	 */
	public void setPunctuality(short punctuality) {
		this.punctuality = punctuality;
	}

}
