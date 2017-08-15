package com.yogu.core.enums.pay;

/**
 * 申请退款状态
 * 用户内部系统，管理各域之间通知结果的参数
 * 
 * @author Hins
 * @date 2015年9月11日 上午10:42:17
 */
public enum RefundStatus {

	/**
	 * 退款申请中
	 */
	APPLY((short) 1),

	/**
	 * 退款中(即同意退款)
	 */
	REFUNDING((short) 2),
	
	/**
	 * 拒绝退款（后台人员拒绝退款）
	 */
	REFUND_REFUSE((short) 3),

	/**
	 * 退款成功
	 */
	SUCCESS((short) 4),

	/**
	 * 拒绝退款（支付平台拒绝退款）
	 */
	FAIL((short) 5);

	private short value;

	private RefundStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
	
	public static RefundStatus valueOf(short value) {
        switch (value) {
            case 1:
                return APPLY;
            case 2:
                return REFUNDING;
            case 3:
                return REFUND_REFUSE;
            case 4:
                return SUCCESS;
            case 5:
                return FAIL;
            default:
                return null;
        }
    }

}
