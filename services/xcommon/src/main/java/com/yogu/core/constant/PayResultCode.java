package com.yogu.core.constant;

/**
 * 支付结果定义<br>
 * 用于服务端通知客户端订单更换支付方式的结果
 * 
 * @author Hins
 * @date 2015年12月28日 下午7:53:40
 */
public class PayResultCode {
	
	/**
	 * 支付成功
	 */
	public static final short PAY_SUCCESS = 1;
	
	/**
	 * 待跳转到支付宝支付
	 */
	public static final short PAY_WAITING_BY_ALIPAY = 20;
	
	/**
	 * 待跳转到微信支付
	 */
	public static final short PAY_WAITING_BY_WECHAT = 30;
	
}
