package com.yogu.core.enums;

public enum MoveDirection {
	/** 上移 */
	UP(-1),

	/** 下移 */
	DOWN(1);
	
	private int value;
	
	private MoveDirection(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static MoveDirection valueOf(int value) {
		switch (value) {
		case -1:
			return UP;
		case 1:
			return DOWN;
		default:
			return null;
		}
	}
}
