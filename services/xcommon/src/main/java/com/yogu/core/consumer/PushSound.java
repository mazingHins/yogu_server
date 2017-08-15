package com.yogu.core.consumer;

/**
 * 推送声音定义
 * @author felix
 */
public class PushSound {
	/**
	 * 新单进入的声音 30秒版, 用于ios推送
	 */
	public static final String NEW_ORDER = "mazing_sound_new_order.caf";

	/**
	 * 新单进入的声音 4秒版, 用于ios推送
	 */
	public static final String NEW_ORDER_SHORT = "mazing_sound_new_order_short.caf";

	/**
	 * 新单进入的声音 无声版, 用于ios推送
	 */
	public static final String NEW_ORDER_NOT = "";

	/**
	 * 新单进入的声音 30秒版, 用于android推送
	 */
	public static final int NEW_ORDER_SOUND = 1;

	/**
	 * 新单进入的声音 4秒版, 用于android推送
	 */
	public static final int NEW_ORDER_SOUND_SHORT = 2;

	/**
	 * 新单进入的声音 无声版, 用于android推送
	 */
	public static final int NEW_ORDER_SOUND_NOT = 3;
	
	/**
	 * 新单进入的声音 无声版, 用于android推送
	 */
	public static final int QUICK_PAY_ORDER_SOUND = 3;
}
