package com.yogu.core.base;

import java.io.Serializable;
import java.util.Date;

public class StoreBalanceStatementBaseVO implements Serializable {

	private static final long serialVersionUID = 6906909004517226709L;

	/** 交易后余额(分) */
	private long curBalance;

	/** 1: 加 2: 减 */
	private short tradeType;

	/** 余额变化值(分)，大于0 */
	private int value;

	/** 是否为冲正记录 0 - 否; 1 - 是 */
	private short reverse = 0;

	/** 创建时间 */
	private Date createTime;

	public long getCurBalance() {
		return curBalance;
	}

	public void setCurBalance(long curBalance) {
		this.curBalance = curBalance;
	}

	public short getTradeType() {
		return tradeType;
	}

	public void setTradeType(short tradeType) {
		this.tradeType = tradeType;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public short getReverse() {
		return reverse;
	}

	public void setReverse(short reverse) {
		this.reverse = reverse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
