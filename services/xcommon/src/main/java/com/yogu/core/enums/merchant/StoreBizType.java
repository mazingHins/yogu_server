package com.yogu.core.enums.merchant;


/**
 * 餐厅营业类型枚举   1：常规配送餐厅  2：预定类餐厅
 * @author sky
 *
 */
public enum StoreBizType {

	/**
	 * 常规配送餐厅
	 */
	NORMAL((short) 1)
	/**
	 * 预定类餐厅
	 */
	, ADVBOOK((short) 2);

	private short value;

	private StoreBizType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}
	
	public static StoreBizType getBizType(short value) {
		switch (value) {
		case 1:
			return NORMAL;
		case 2:
			return ADVBOOK;
		default:
			return null;
		}
	}

	
}
