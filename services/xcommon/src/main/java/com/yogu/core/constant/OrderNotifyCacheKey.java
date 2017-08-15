package com.yogu.core.constant;

import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;

/**
 * 订单通知的缓存key, 用于避免再次发送
 * @author felix
 */
public class OrderNotifyCacheKey {
	
	/** 到了预计送达时间的一半通知客户经理的前缀, 用于记录是否已经短信或推送通知过 */
	public static final String MID_DELIVERY_TIME_NOTIFY_PREFIX = "midDeliveryNotify::";
	
	/** 刚到预计送达时间的通知客户经理的前缀, 用于记录是否已经短信或推送通知过  */
	public static final String JUST_DELIVERY_TIME_NOTIFY_PREFIX = "justDeliveryTimeNotify";
	
	/** 到了该开始配送的时间还没有配送的前缀, 该开始配送的时间计算方式为: 如订单预计20160303 12:00送达，配送需要2小时，那么20160303 10:00 则为该配送时间 */
	public static final String SHOULD_HAS_DELIVERED_ORDER = "shouldHasDeliveredOrder";
	
	/**
	 * 团购成功控制并发的缓存
	 */
	public static final String TEAM_PAY_SUCCESS = "TeamPaySuccess";
	
	public static String teamPaySuccess(long buyId){
		return AnnoCacheExtendAspecter.toKey(TEAM_PAY_SUCCESS, buyId);
	}
}
