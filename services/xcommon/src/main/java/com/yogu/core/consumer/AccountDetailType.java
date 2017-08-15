package com.yogu.core.consumer;

/**
 * 每一笔金额变动涉及对账请求的类型枚举定义
 *
 * @date 2016年6月20日 下午3:23:37
 * @author hins
 */
public enum AccountDetailType {

	/**
	 * 商家接单
	 */
	STORE_ACCEPT_ORDER(10),

	/**
	 * 用户订单（退款）
	 */
	USER_REFUND(20),

	/**
	 * 商家/管理员/定时任务取消订单（退款）
	 */
	REFUND_ORDER(30),
	
	/**
	 * 商家/管理员取消已完成订单（退款）
	 */
	REFUNND_FINISH_ORDER(35),

	/**
	 * 用户确认收货
	 */
	USER_CONFIRMED(40),
	
	/**
	 * 米星付成功
	 */
	MAZING_PAY_SUCCESS(50),
	
	/**
	 * 米星付退款
	 */
	MAZING_PAY_REFUND(55),

	/**
	 * T+N结算入账
	 */
	SETTLE(60),
	
	/**
	 * 米星付T+N结算入账
	 */
	MAZING_PAY_SETTLE(70),

	/**
	 * 申请提现
	 */
	APPLY_WITHDRAW(80),

	/**
	 * 提现失败
	 */
	WITHDRAW_FAIL(90),

	/**
	 * 提现成功
	 */
	WITHDRAW_SUCCESS(100),
	
	/**
	 * 团购T+N结算入账
	 */
	TEAM_PAY_SETTLE(110);

	private int value;

	private AccountDetailType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static AccountDetailType valueOf(int value) {
		switch (value) {
		case 10:
			return STORE_ACCEPT_ORDER;
		case 20:
			return USER_REFUND;
		case 30:
			return REFUND_ORDER;
		case 35:
			return REFUNND_FINISH_ORDER;
		case 40:
			return USER_CONFIRMED;
		case 50:
			return MAZING_PAY_SUCCESS;
		case 55:
			return MAZING_PAY_REFUND;
		case 60:
			return SETTLE;
		case 70:
			return MAZING_PAY_SETTLE;
		case 80:
			return APPLY_WITHDRAW;
		case 90:
			return WITHDRAW_FAIL;
		case 100:
			return WITHDRAW_SUCCESS;
		default:
			return null;
		}
	}

}
