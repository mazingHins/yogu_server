package com.yogu.core.enums.user;

/**
 * 用户标签现在的使用状态定义
 *
 * @date 2016年12月22日 下午4:36:24
 * @author hins
 */
public enum TagUseStatus {

	/**
	 * 有效
	 */
	EFFECTIVE((short) 1),

	/**
	 * 系统取消
	 */
	SYSTEM_DELETE((short) 3);

	private short value;

	private TagUseStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

}
