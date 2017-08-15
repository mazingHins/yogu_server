package com.yogu.services.store;

import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;

/**
 * ticket 相关的 redis 缓存key 管理
 * 
 * @author sky 2017-03-14
 *
 */
public class TicketCacheKey {

	/**
	 * 未出售的ticket库存 缓存key
	 * 
	 * @param ticketRuleId ticket规则id
	 * @return
	 */
	public static String ticketSurplusCacheKey(long ticketRuleId) {
		String key = AnnoCacheExtendAspecter.toKey("ticketSurplusCacheKey", ticketRuleId);
		return key;
	}

	/**
	 * 库存为0时，retry一次的缓存控制key
	 * 
	 * @param ticketRuleId
	 * @return
	 */
	public static String justOneMoreRetryCacheKey(long ticketRuleId) {

		String key = AnnoCacheExtendAspecter.toKey("ticketLoadDBRetryKey", ticketRuleId);
		return key;
	}

	/**
	 * 防止重复load all的 缓存控制key
	 * 
	 * @return
	 */
	public static String stopRepeatLoadAvailableTicketCacheKey() {

		String key = AnnoCacheExtendAspecter.toKey("stopLoadAllAvailableTicket");
		return key;
	}

	/**
	 * 防止重复创建新批次的 缓存控制key
	 * 
	 * @param ticketRuleId
	 * @return
	 */
	public static String stopRepeatCreateNewBatchCacheKey(long ticketRuleId) {

		String key = AnnoCacheExtendAspecter.toKey("stopReCreateNewBatch", ticketRuleId);
		return key;
	}

	/**
	 * 防止订单重复扣库存 缓存控制key
	 * 
	 * @param orderNo
	 * @return
	 */
	public static String stopOrderRepeatAssignTicketCacheKey(long orderNo) {

		String key = AnnoCacheExtendAspecter.toKey("stopOrderRepeatAssignTicket", orderNo);
		return key;
	}
}
