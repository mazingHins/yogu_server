/**
 * 
 */
package com.yogu.services.store;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.base.Point;

/**
 * store域，记录‘埋点’的统一日志对象 <br>
 * 不算是埋点，只是便于统计的一种日志形式
 *
 * @author JFan 2016年1月12日 下午2:25:47
 */
public final class StoreAccessLog {

	private static final String LOG_NAME = "StoreAccess";
	private static final Logger accessLog = LoggerFactory.getLogger(LOG_NAME);

	/**
	 * 获得Logger对象，自行打印日志
	 */
	public static Logger getLogger() {
		return accessLog;
	}

	// ########
	// #### 首页

	/**
	 * 查看首页布局
	 */
	public static void indexLayout(String whiteStr, Long uid, Point point, long time) {
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);// 此ip在RestForwardFilter进行赋值
		accessLog.info("index#layout{} | uid: {}, point: {}, ip: {}, time: {}", whiteStr, uid, point, ip, time);
	}

	// ########
	// #### 餐厅

	/**
	 * 查看门店介绍（点logo的页面）
	 * 
	 * @param storeId 餐厅ID
	 * @param uid 用户ID，为null表示没有登录
	 */
	public static void storeDetailsShow(long storeId, Long uid) {
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);// 此ip在RestForwardFilter进行赋值
		accessLog.info("store#detailsShow | storeId: {}, uid: {}, ip: {}", storeId, uid, ip);
	}

	/**
	 * 查看门店Mini Blog
	 * 
	 * @param storeId 餐厅ID
	 * @param uid 用户ID，为null表示没有登录
	 * @param time 访问耗时 (ms), 2016/3/8 by ten
	 */
	public static void storeMiniBlog(long storeId, Long uid, long time) {
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);// 此ip在RestForwardFilter进行赋值
		accessLog.info("store#miniBlog | storeId: {}, uid: {}, ip: {}, time: {}", storeId, uid, ip, time);
	}

	/**
	 * 用户收藏餐厅
	 * 
	 * @param uid 用户ID
	 * @param fav 收藏还是取消收藏；true：收藏、false：取消收藏
	 * @param storeIds 餐厅id列表
	 */
	public static void storeFav(long uid, boolean fav, long[] storeIds) {
		if (ArrayUtils.isNotEmpty(storeIds)) {
			String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);// 此ip在RestForwardFilter进行赋值
			for (long storeId : storeIds)
				if (fav)
					accessLog.info("store#favStore | storeId: {}, uid: {}, ip: {}", storeId, uid, ip);
				else
					accessLog.info("store#cancelFavStore | storeId: {}, uid: {}, ip: {}", storeId, uid, ip);
		}
	}

	// ########
	// #### 美食

	/**
	 * 查看美食详情
	 * 
	 * @param dishKey 美食key
	 * @param uid 用户ID，为null表示没有登录
	 */
	public static void dishDetails(long dishKey, Long uid) {
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);// 此ip在RestForwardFilter进行赋值
		accessLog.info("dish#details | dishKey: {}, uid: {}, ip: {}", dishKey, uid, ip);
	}

	/**
	 * 用户收藏美食
	 * 
	 * @param uid 用户ID
	 * @param fav 收藏还是取消收藏；true：收藏、false：取消收藏
	 * @param dishKeys 美食key列表
	 */
	public static void dishFav(long uid, boolean fav, long[] dishKeys) {
		if (ArrayUtils.isNotEmpty(dishKeys)) {
			String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);// 此ip在RestForwardFilter进行赋值
			for (long dishKey : dishKeys)
				if (fav)
					accessLog.info("dish#favDish | dishKey: {}, uid: {}, ip: {}", dishKey, uid, ip);
				else
					accessLog.info("dish#cancelFavDish | dishKey: {}, uid: {}, ip: {}", dishKey, uid, ip);
		}
	}

}
