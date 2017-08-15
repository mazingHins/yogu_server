package com.yogu.core.enums.order;

import java.util.Arrays;
import java.util.List;

/**
 * 第三方配送状态定义
 *
 * @date 2016年10月9日 下午6:15:30
 * @author hins
 */
public enum ExpressStatus {

	/**
	 * 未付款
	 */
	NON_PAYMENT((short) 10),

	/**
	 * 已付款 ，等待配送<br>
	 */
	HAS_PAYMENT((short) 20),

	/**
	 * 跟第三方配送下单成功
	 */
	CREATE_THIRD_ORDER((short) 30),

	/**
	 * 第三方接单
	 */
	THIRD_ACCEPT_ORDER((short) 40),

	/**
	 * 第三方已取货
	 */
	THIRD_PICK_UP((short) 50),

	/**
	 * 配送中
	 */
	DELIVERY((short) 60),

	/**
	 * 第三方确认送达
	 */
	THIRD_CONFIRM_RECEIPT((short) 70),

	/**
	 * 取消配送
	 */
	CANCEL_EXPRESS((short) 80);
	
	private short value;

	private ExpressStatus(short value) {
		this.value = value;
	}

	public short getValue() {
		return value;
	}
	
	/**
	 * 有效的配送状态集合
	 */
	public static List<Short> getEffective() {
		return Arrays.asList(NON_PAYMENT.getValue(), HAS_PAYMENT.getValue(), CREATE_THIRD_ORDER.getValue(), THIRD_ACCEPT_ORDER.getValue(),
				THIRD_PICK_UP.getValue(), DELIVERY.getValue(), THIRD_CONFIRM_RECEIPT.getValue());
	}
	
	/**
	 * 顺丰进行中的状态集合
	 */
	public static List<Short> getSfGoing(){
		return Arrays.asList(CREATE_THIRD_ORDER.getValue(), THIRD_ACCEPT_ORDER.getValue(),THIRD_PICK_UP.getValue(), DELIVERY.getValue());
	}
	
	public static List<Short> getSfEffective(){
		return Arrays.asList(CREATE_THIRD_ORDER.getValue(), THIRD_ACCEPT_ORDER.getValue(),THIRD_PICK_UP.getValue(), DELIVERY.getValue(), THIRD_CONFIRM_RECEIPT.getValue());
	}
	
	/**
	 * 支付后有效的配送状态集合
	 */
	public static List<Short> getHasPayEffective(){
		return Arrays.asList(HAS_PAYMENT.getValue(), CREATE_THIRD_ORDER.getValue(), THIRD_ACCEPT_ORDER.getValue(),
				THIRD_PICK_UP.getValue(), DELIVERY.getValue(), THIRD_CONFIRM_RECEIPT.getValue());
	}
	
	public static List<Short> getHasPay(){
		return Arrays.asList(HAS_PAYMENT.getValue(), CREATE_THIRD_ORDER.getValue(), THIRD_ACCEPT_ORDER.getValue(),
				THIRD_PICK_UP.getValue(), DELIVERY.getValue(), THIRD_CONFIRM_RECEIPT.getValue(), CANCEL_EXPRESS.getValue());
	}
	
	public static List<Short> getAll(){
		return Arrays.asList(NON_PAYMENT.getValue(), HAS_PAYMENT.getValue(), CREATE_THIRD_ORDER.getValue(), THIRD_ACCEPT_ORDER.getValue(),
				THIRD_PICK_UP.getValue(), DELIVERY.getValue(), THIRD_CONFIRM_RECEIPT.getValue(), CANCEL_EXPRESS.getValue());
	}
	
}
