package com.yogu.core.enums.merchant;

public enum DishGroupDefaultShow {
	/**
	 * 进入餐厅miniblog默认展示分组
	 */
	SHOW((short) 1)
	/**
	 * 进入餐厅miniblog非默认展示分组
	 */
	, NOT_SHOW((short) 2);

	private short value;

	private DishGroupDefaultShow(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}
}
