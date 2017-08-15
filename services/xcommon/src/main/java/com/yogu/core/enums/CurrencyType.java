package com.yogu.core.enums;

/**
 * 支付货币
 */
public enum CurrencyType {
    /**
     * 人民币
     */
    CNY((short) 1);

    private short value;

    private CurrencyType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static CurrencyType valueOf(int value) {
        switch (value) {
            case 1:
                return CNY;
            default:
                return null;
        }
    }
}