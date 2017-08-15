package com.yogu.core.enums.merchant;

/**
 * 门店星级 枚举
 * 
 * @author sky
 *
 */
public enum StoreStar {

	// 星级级，10表示1星，20为2星，如此类推 , 0 表示新店
	
	
	DEFAULT((short) 30),//默认3星
	/**
	 * 新店(没有星级)
	 */
	NEW((short) 0)
	/**
	 * 1星
	 */
	, ONE((short) 10)
	/**
	 * 2星
	 */
	, TWO((short) 20)
	/**
	 * 3星
	 */
	, THREE((short) 30)
	/**
	 * 4星
	 */
	, FOUR((short) 40)
	/**
	 * 5星
	 */
	, FIVE((short) 50);

	private short value;

	private StoreStar(short value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public short getValue() {
		return value;
	}

	public static short valueOf(short value) {

		// 1.5,2.5,3.5,4.5 星级 待定(产品还未确定是否需要)
		
		if (value < 10) {
			return 0;
		}
		if (value >= 10 && value < 15) {
			return 10;
		}
		if (value >= 15 && value < 20) {
			return 15;
		}
		if (value >= 20 && value < 25) {
			return 20;
		}
		if (value >= 25 && value < 30) {
			return 25;
		}
		if (value >= 30 && value < 35) {
			return 30;
		}
		if (value >= 35 && value < 40) {
			return 35;
		}
		if (value >= 40 && value < 45) {
			return 40;
		}

		if (value >= 45 && value < 50) {
			return 45;
		}
		if (value == 50) {
			return 50;
		}

		return 0;
	}
}
