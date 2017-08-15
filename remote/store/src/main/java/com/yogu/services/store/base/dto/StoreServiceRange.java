package com.yogu.services.store.base.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店的配送范围界定，允许配置多个；如果用户命中多个配送费，应该取配送费用最低的。
 * 
 */
public class StoreServiceRange implements Serializable {

	private static final long serialVersionUID = -3074457345703615409L;

	/** 配送范围ID */
	private long rangeId;

	/** 门店ID */
	private long storeId;

	/** 配送服务名 */
	private String name;

	/** 配送服务备注 */
	private String remark;

	/** 设定类型；1：半径、2：多边形 */
	private short cutType;

	/** cut_type为1：配送半径（米）、cut_type为2时：[ [lat lng] ...] 坐标点数组（至少3个）确定一个面 */
	private String cutValue;

	/** 货币类型：1-人民币 */
	private short currencyType;

	/** 起送金额（分） */
	private int minimumMoney;

	/** 费用类型：1-免费；2-付费；3-的士费*1；4-的士费*2 */
	private short moneyType = 1;

	/** 配送费用（分）,用户支付的部分 */
	private int money;

	/** 免运费金额（分）（小于等于0表示不做"满免"） */
	private int fullFreeFreight;

	/** 配送时间（分钟） */
	private int minute;

	/** 是否启用守时配送，也就是在minute时间内送达；1：启用、其他：不启用 */
	private short punctuality = 0;

	/** 创建时间 */
	private Date createTime;
	
	/**
	 * 是否满足顺丰配送，1：是，其他否
	 */
	private short isSf;
	
	// ------------------- 或者直接返回顺丰配送规则对象 ----------------------

	public void setRangeId(long rangeId) {
		this.rangeId = rangeId;
	}

	public long getRangeId() {
		return rangeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public void setCutType(short cutType) {
		this.cutType = cutType;
	}

	public short getCutType() {
		return cutType;
	}

	public void setCutValue(String cutValue) {
		this.cutValue = cutValue;
	}

	public String getCutValue() {
		return cutValue;
	}

	public void setCurrencyType(short currencyType) {
		this.currencyType = currencyType;
	}

	public short getCurrencyType() {
		return currencyType;
	}

	public void setMinimumMoney(int minimumMoney) {
		this.minimumMoney = minimumMoney;
	}

	public int getMinimumMoney() {
		return minimumMoney;
	}

	public void setMoneyType(short moneyType) {
		this.moneyType = moneyType;
	}

	public short getMoneyType() {
		return moneyType;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setFullFreeFreight(int fullFreeFreight) {
		this.fullFreeFreight = fullFreeFreight;
	}

	public int getFullFreeFreight() {
		return fullFreeFreight;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getMinute() {
		return minute;
	}

	public void setPunctuality(short punctuality) {
		this.punctuality = punctuality;
	}

	public short getPunctuality() {
		return punctuality;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	public short getIsSf() {
		return isSf;
	}

	public void setIsSf(short isSf) {
		this.isSf = isSf;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
