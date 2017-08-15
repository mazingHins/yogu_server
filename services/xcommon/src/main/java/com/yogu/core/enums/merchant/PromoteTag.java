package com.yogu.core.enums.merchant;

/**
 * 推广标签
 */
public enum PromoteTag {
	/** 无推广 */
	NON_PROMOTE((short) 0),

	/** 新菜上市 */
	NEW_DISH((short) 1),

	/** 厨师推荐 */
	COOK_BEST((short) 2),

	/** 拿手菜 */
	ADEPT((short) 3),

	/** 特价 */
	SPECIAL((short) 4);

	private short value;

	private PromoteTag(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public static PromoteTag valueOf(int value) {
		switch (value) {
		case 0:
			return NON_PROMOTE;
		case 1:
			return NEW_DISH;
		case 2:
			return COOK_BEST;
		case 3:
			return ADEPT;
		case 4:
			return SPECIAL;
		default:
			return null;
		}
	}

}