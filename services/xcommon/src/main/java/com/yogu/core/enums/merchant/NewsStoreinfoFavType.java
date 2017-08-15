package com.yogu.core.enums.merchant;

/**
 * 资讯餐厅 喜欢的类型枚举
 * 
 * @author sky
 *
 */
public enum NewsStoreinfoFavType {
	/** 想吃 */
	WANT((short) 1),

	/** 吃过 */
	EATEN((short) 2);

	private short value;

	private NewsStoreinfoFavType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}

	public static NewsStoreinfoFavType getNewsStoreinfoFavType(short value) {
		switch (value) {
		case 1:
			return WANT;
		case 2:
			return EATEN;
		default:
			return null;
		}
	}
}
