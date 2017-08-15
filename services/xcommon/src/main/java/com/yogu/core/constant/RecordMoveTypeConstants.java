package com.yogu.core.constant;

/**
 * 记录移动方向,主要用于管理后台
 * 
 * @author sky
 *
 */
public class RecordMoveTypeConstants {

	/**
	 * 上移
	 */
	public static final int UP = 1;
	/**
	 * 下移
	 */
	public static final int DOWN = 2;
	/**
	 * 置顶
	 */
	public static final int TOP = 3;
	/**
	 * 置底
	 */
	public static final int BOTTOM = 4;

	/**
	 * 获取移动操作的名称
	 * 
	 * @param moveType 移动类型
	 * @return
	 * @author sky 2016-01-16
	 */
	public static String getMoveName(int moveType) {
		String name = "";
		switch (moveType) {
		case UP:
			name = "上移";
			break;
		case DOWN:
			name = "下移";
			break;
		case TOP:
			name = "置顶";
			break;
		case BOTTOM:
			name = "置底";
			break;
		default:
			name = "上移";
			break;
		}
		return name;
	}
}
