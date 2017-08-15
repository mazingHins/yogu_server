package com.yogu.core.enums.order;

/**
 * 拼团购买记录的状态
 * 
 * @author east
 * @date 2017年5月17日 上午11:49:47
 */
public enum TeamRecordStatus {
	/**
	 * 未使用
	 */
	NOT_USED((short) 0),

	/**
	 * 拼团未成功取消的订单
	 */
	CANCEL((short) 1),

	/**
	 * 已核销
	 */
	USED((short) 2),

	/**
	 * 过期未使用的
	 */
	EXPIRE((short) 3);

	private short value;

	private TeamRecordStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

}
