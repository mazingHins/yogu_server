package com.yogu.core.web;

/**
 * 
 * @Description: 支付的错误代码
 * @author Hins
 * @date 2015年8月6日 下午5:58:24
 */
public class PayErrorCode {

	/**
	 * 支付记录不存在
	 */
	public static final int PAY_RECORD_NOT_EXIST = 4010;
	
	/**
	 * 支付记录已存在
	 */
	public static final int PAY_RECORD_IS_EXIST = 4011;
	
	/**
	 * 支付记录状态不是已支付
	 */
	public static final int PAY_RECORD_NOT_HAS_PAY = 4012;
	
	/**
	 * 退款金额大于交易金额
	 */
	public static final int REFUND_FEE_GT_TOTAL_FEE = 4030;
	
	/**
	 * 退款记录不存在
	 */
	public static final int REFUND_RECORD_NOT_EXIST = 4040;
	
	/**
	 * 退款状态不等于退款中
	 */
	public static final int REFUND_RECORD_NOT_EQUAL_REFUND = 4041;
	
	/**
	 * 余额记录不存在
	 */
	public static final int BALANCE_RECORD_NOT_FOUND = 4050;
	
	/**
	 * 提现记录不存在
	 */
	public static final int WITHDRAW_RECORD_NOT_FOUND = 4060;
	
	/**
	 * 余额不足
	 */
	public static final int WITHDRAW_BALANCE_NOT_ENOUGH = 4061;
	
	/**
	 * 账户状态不正常
	 */
	public static final int BALANCE_STATUS_INVALID = 4062;
	
	/**
	 * 账户余额不足
	 */
	public static final int BALANCE_NOT_ENOUGH = 4063;
	
	/**
	 * 提现金额太大，不满足最低提现金额条件
	 */
	public static final int WITHDRAW_LESS_THAN = 4064;
	
	/**
	 * 流水科目暂不支持
	 */
	public static final int SUBJECT_NOT_SUPPORT = 4065;
	
	/**
	 * 提现批次状态更新失败
	 */
	public static final int UPDATE_BATCH_STATUS_FAILED = 4070;
	
	/**
	 * 申请退款失败（订单已入账）
	 */
	public static final int REFUND_HAS_SETTLE = 4080;
}
