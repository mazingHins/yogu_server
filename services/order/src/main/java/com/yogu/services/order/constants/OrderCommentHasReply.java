package com.yogu.services.order.constants;

public enum OrderCommentHasReply {
	/**
     * 有回复
     */
    HAS_REPLY((short)1),

    /**
     * 没有回复
     */
    HAS_NOT_REPLY((short)0);
    
    private short value;

    private OrderCommentHasReply(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
