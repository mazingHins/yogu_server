package com.yogu.services.order;

import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;

/**
 * 整个订单模块的KEY定义 <br>
 * 这里提供方法直接获取key
 * @author Hins
 * @date 2015年9月6日 下午4:06:55
 */
public class OrderCacheKey {
	
	/** 10秒 */
	public static final int TIME_10S = 10;
	
	/** 1小时 */
	public static final int TIME_1H = 60 * 60;
	
	/** 6小时 */
	public static final int TIME_6H = 6 * TIME_1H;
	
	/** 3小时 */
	public static final int TIME_3H = 3 * 60 * 60;
	
	/** 24小时 */
	public static final int TIME_24H = 24 * 60 * 60;
	
	/** 门店当天订单序列缓存 */
	public static final String ORDER_SERIAL_PREFIX = "OrderSerial";
	
	/** 门店当天报表缓存 */
	public static final String ORDER_REPORT_PREFIX = "OrderReport";
	
	/** 创建订单resource缓存key */
	public static final String CREATE_ORDER_PREFIX = "CreateOrder";
	
	/** 创建订单并获取支付sdk信息resource缓存key */
	public static final String CREATE_ORDER_AND_GET_PAY_PREFIX = "CreateOrderAndGetPay";
	
	/** 更换订单支付方式resource缓存key */
	public static final String CHANGE_ORDER_PAYMODE_PREFIX = "ChangeOrderPayMode";
	
	/** 新订单提醒客户端刷新红点的KEY */
	public static final String HAS_NEW_ORDER = "HasNewOrder";
	
	/** 用户统计数据缓存key */
	public static final String STORE_USER_STATISTICS = "StoreUserStatistics";
	
	/**
	 * 用户统计数据缓存key
	 * 
	 * @param uid
	 * @return
	 */
	public static String userStatisticsKey(long uid) {
		return AnnoCacheExtendAspecter.toKey(STORE_USER_STATISTICS, uid);
	}
	
	/** 门店当天订单序列对象的缓存 */
	public static String serialKey(long storeId, String time) {
		return AnnoCacheExtendAspecter.toKey(ORDER_SERIAL_PREFIX, storeId, time);
	}
	
	/** 门店当天报表的缓存 */
	public static String reportKey(long storeId, int time) {
		return AnnoCacheExtendAspecter.toKey(ORDER_REPORT_PREFIX, storeId, time);
	}
	
	/** 创建订单resource缓存 */
	public static String createOrderKey(long uid){
		return AnnoCacheExtendAspecter.toKey(CREATE_ORDER_PREFIX, uid);
	}
	
	/** 更换订单支付方式resource缓存 */
	public static String changeOrderPayModeKey(long orderNo, short payMode){
		return AnnoCacheExtendAspecter.toKey(CHANGE_ORDER_PAYMODE_PREFIX, orderNo, payMode);
	}
	
	public static String createOrderAndGetPayKey(long uid) {
		return AnnoCacheExtendAspecter.toKey(CREATE_ORDER_AND_GET_PAY_PREFIX, uid);
	}
}
