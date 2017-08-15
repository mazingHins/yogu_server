package com.yogu.core.constant;

/**
 * 邮件相关的常量定义
 * 
 * @author felix
 * @date 2016-03-09
 */
public class EmailConstant {
	/**
	 * 当订单的总价大于等于此值时, 发送给BD的邮件标题带上订单金额
	 * (单位分)
	 */
	public static final int SUBJECT_SHOW_AMOUNT = 100000;
	
	/**
	 * 米星进度通知邮件的主题
	 */
	public static final String NOTIFY_BD_SUBJECT = "米星订单进度通知!";
}
