package com.yogu.core.enums;

/**
 * 定义请求的执行状态
 * 
 * @author felix
 */
public enum ChangeRequestStatus {
	/**
	 * 完成
	 */
	DONE((short)1),
	
	/**
	 * 未完成,需要执行
	 */
	NOT_DONE((short)2);
	
	private short value;

    private ChangeRequestStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
