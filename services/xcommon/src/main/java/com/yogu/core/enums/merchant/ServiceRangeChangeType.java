package com.yogu.core.enums.merchant;

/**
 * 配送范围的修改类型
 * @author felix
 */
public enum ServiceRangeChangeType {
	ADD("新增"),
	
	UPDATE("修改"),
	
	DELETE("删除");
	
	private String value;

	private ServiceRangeChangeType(String value) {
		this.value = value;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}
}
