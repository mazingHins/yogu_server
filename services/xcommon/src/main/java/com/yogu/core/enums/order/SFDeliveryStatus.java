package com.yogu.core.enums.order;

/**
 * 顺丰配送 回调状态枚举(该值对应顺丰配送的订单状态值,非米星订单状态值)
 * 
 * @author sky 2016-10-11
 *
 */
public enum SFDeliveryStatus {
	/**
	 * 已调度
	 */
	DISTRIBUTED((short) 1),

	/**
	 * 到店<br>
	 */
	ARRIVED((short) 19),

	/**
	 * 已取货
	 */
	PICKED_UP((short) 2),

	/**
	 * 已妥投
	 */
	DELIVERED((short) 3),
	
	/**
	 * 异常调度
	 */
	DISTRIBUTED_EXCEPTION((short)4),
	
	/**
	 * 异常取货
	 */
	PICKED_UP_EXCEPTION((short)5),
	
	/**
	 * 异常妥投
	 */
	DELIVERED_EXCEPTION((short)6);

	private short value;

	private SFDeliveryStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public static SFDeliveryStatus valueOf(short value) {
		switch (value) {
		case 1:
			return DISTRIBUTED;
		case 2:
			return PICKED_UP;
		case 3:
			return DELIVERED;
		case 19:
			return ARRIVED;
		default:
			return null;
		}
	}
}
