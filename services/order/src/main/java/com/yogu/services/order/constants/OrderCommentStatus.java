package com.yogu.services.order.constants;

public enum OrderCommentStatus {
	/**
     * 正常
     */
    NORMAL((short)1),

    /**
     * 屏蔽
     */
    BLOCK((short)2);
    
    private short value;

    private OrderCommentStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
