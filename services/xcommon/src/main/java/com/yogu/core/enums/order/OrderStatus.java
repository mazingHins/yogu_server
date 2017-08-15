package com.yogu.core.enums.order;

/**
 * 订单状态
 */
public enum OrderStatus {
    /**
     * 未付款
     */
    NON_PAYMENT((short)10),

    /**
     * 已付款   (选择线上支付的订单,该状态表示已支付; 选择现金支付的订单, 该状态表示待接单 )<br>
     * TODO [之后需要更改此订单状态名称,统一叫做 '待接单' pending_accept] 
     */
    PENDING_ACCEPT((short)15),

    /**
     * 已接单
     */
    ACCEPT_ORDER((short)20),

    /**
     * 完成制作
     */
    FINISH_COOKING((short)23),

    /**
     * 配送中
     */
    DELIVERY((short)25),

    /**
     * 买家确认收货
     */
    CONFIRM_RECEIPT_USER((short)35),

    /**
     * 已评论
     */
    HAS_COMMENT((short)40),

    /**
     * 申请退款
     */
    REFUND_APPLY((short)45),

    /**
     * 退款中
     */
    REFUND((short)50),

    /**
     * 拒绝退款
     */
    REFUND_REFUSE((short)55),

    /**
     * 已退款
     */
    REFUND_SUCCESS((short)60),

    /**
     * 订单已取消
     */
    CANCEL((short)65);

    private short value;

    private OrderStatus(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }
}
