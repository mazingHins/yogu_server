package com.yogu.core.enums.pay;

/**
 * 申请退款结果状态
 */
public enum ApplyRefundStatus{
    /**
     * 退款成功
     */
    REFUND_SUCCESS((short)10),

    /**
     * 退款失败
     */
    REFUND_FAIL((short)20);

    private short value;

    private ApplyRefundStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

}