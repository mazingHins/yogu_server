package com.yogu.core.enums.merchant;

/**
 * 用于定义美食分组是否是默认分组 ('全部')
 * @author felix 2016-03-24
 */
public enum DishGroupIsDefault {
	/**
	 * 默认
	 */
	IS_DEFAULT((short) 1)
	/**
	 * 非默认
	 */
	, NON_DEFAULT((short) 2);

	private short value;

	private DishGroupIsDefault(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}
}
