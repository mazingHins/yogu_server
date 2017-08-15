package com.yogu.core.enums.user;

/**
 * 用户标签权重记录变化类型, 1-加, 2-减
 *
 * @date 2016年12月27日 下午6:21:34
 * @author hins
 */
public enum TagRaiseTradeType {
	
	/**
	 * 加
	 */
	PLUS((short)1),
	
	/**
	 * 减
	 */
	SUBTRACT((short)2);
	
	private short value;
	
	private TagRaiseTradeType(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
}
