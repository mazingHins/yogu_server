package com.yogu.core.enums.pay;

/**
 * 支付结果状态
 * 用户内部系统，管理各域之间通知结果的参数
 */
public enum PayStatus{
    /**
     * 支付成功
     */
    TRADE_SUCCESS((short)10),

    /**
     * 支付失败
     */
    TRADE_FAIL((short)20),

    /**
     * 支付超时
     */
    TRADE_CLOSED((short)30);


    private short value;

    private PayStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

}
