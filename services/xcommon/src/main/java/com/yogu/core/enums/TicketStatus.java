package com.yogu.core.enums;

/**
 * 活动票的状态枚举类
 * 
 * @author Sky 2017-02-27
 *
 */
public enum TicketStatus {

	/**
	 * 待购买
	 */
	TO_BUY((short) 0),
	
	/**
	 * 购买占用中
	 */
	PREBUY((short) 1),

	/**
	 * 已购买(未使用)
	 */
	UNUSE((short) 2),
	
	/**
	 * 退款中
	 */
	REFUNING((short) 3),

	/**
	 * 已使用(已核销)
	 */
	USED((short) 4),

	/**
	 * 已过期
	 */
	EXPIRED((short) 5),

	/**
	 * 已退款
	 */
	REFUNDED((short) 6);

	private short value;

	private TicketStatus(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}

	public static TicketStatus from(short status) {
		for (TicketStatus ts : TicketStatus.values()) {
			if (ts.getValue() == status) {
				return ts;
			}
		}
		return null;
	}
}
