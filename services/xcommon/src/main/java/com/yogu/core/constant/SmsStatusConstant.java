package com.yogu.core.constant;

/**
 * 短信状态的相关常量定义
 * @author felix
 */
public class SmsStatusConstant {
	/** 短信发送到接收时间N<=10的计数器的key */
	public static final String NORMAL = "sms_normal_counter_key";
	
	/** 短信发送到接收时间10秒<N<=30秒的计数器的key */
	public static final String LOW = "sms_low_counter_key";
	
	/** 短信发送到接收时间30秒<N<=60秒的计数器的key */
	public static final String MEDIUM = "sms_medium_counter_key";
	
	/** 短信发送到接收时间60秒<N的计数器的key */
	public static final String SERIOUS = "sms_serious_counter_key";
	
	public static final int NORMAL_LIMIT = 10;
	
	public static final int LOW_LIMIT = 30;
	
	public static final int MEDIUM_LIMIT = 60;
	
	public static final int ALARM_LIMIT = 20;
}
