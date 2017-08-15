package com.yogu;

/**
 * 定时任务 常量定义
 * 
 * @author sky
 *
 */
public class TasksConstants {

	/**
	 * 一次处理大小(是指一次性从数据库拿取的记录数大小) 暂定 30条
	 */
	public static final int PROCESS_CAPACITY = 30;

	/**
	 * 未支付(线上支付)的订单 超时时间(单位 分钟) 30分钟
	 */
	public static final int ORDER_NOT_PAY_TIMEOUT = 30;

	/**
	 * 用户退款申请 自动 同意退款 的 计数时间间隔 (单位分钟) 60分钟
	 */
	public static final int REFUND_AUTO_AGREE_APPLY = 60;
	
	/**
	 * 接受订单的超时时间 (3分钟)
	 */
	public static final int ACCEPT_OVER_TIME = 3;
	
	/**
	 * 获取需要发送短信通知的订单的时间范围
	 */
	public static final int SMS_RANGE_TIME = 3 * 60;
	
	/**
	 * 定时任务中, 允许重发短信和推送消息的时间间隔 (单位:秒)
	 */
	public static final int MSG_RESEND_TIME = 60 * 60 * 24 * 7; // 7天
	

}
