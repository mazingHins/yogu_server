package com.yogu.core.enums.merchant;

/**
 * 餐厅收藏类型的枚举
 * @author felix
 */
public enum StoreFavType {
	/** 全部 */ 
	ALL((short) 0),
	
	/** 想吃 */
	WANT((short) 1),
	
	/** 喜欢(收藏) */
	FAV((short) 2),
	
	/** 吃过 */
	EAT((short) 3);
	
	private short value;

	private StoreFavType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}
	
	public static StoreFavType getStoreFavType(short value) {
		switch (value) {
		case 0:
			return ALL;
		case 1:
			return WANT;
		case 2:
			return FAV;
		case 3:
			return EAT;
		default:
			return null;
		}
	}
}
