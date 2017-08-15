package com.yogu.core.enums;

/**
 * 用于 bool 值的表示
 * @author ten 2016/1/12.
 */
public class BooleanConstants {

    /**
     * 是
     */
    public static final short TRUE = 1;

    /**
     * 否
     */
    public static final short FALSE = 0;

	public static short conver(String str) {
		return (("1".equals(str)) ? TRUE : FALSE);
	}

}
