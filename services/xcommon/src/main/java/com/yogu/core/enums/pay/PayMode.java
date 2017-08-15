package com.yogu.core.enums.pay;

/**
 * 支付平台
 */
public enum PayMode {
	
    /**
     * 支付宝
     */
    ALIPAY((short) 1),

    /**
     * 微信支付
     */
    WECHAT((short) 2);

    private short value;

    private PayMode(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static PayMode valueOf(int value) {
        switch (value) {
            case 1:
                return ALIPAY;
            case 2:
                return WECHAT;
            default:
                return null;
        }
    }
}