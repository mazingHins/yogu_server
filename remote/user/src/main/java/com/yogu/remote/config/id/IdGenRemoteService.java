package com.yogu.remote.config.id;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;

/**
 * 通过远程接口获取新的ID，一个JVM应只有一个实例。 这个类最好在 applicationContext.xml 初始化。 另外，mazing-config 模块不能调用这个类。
 *
 * 每次通过接口取一批ID，然后在本地分配，提高性能。
 *
 * @author linyi 2015/5/14.
 */
@Named
public class IdGenRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(IdGenRemoteService.class);

	private static final String host = CommonConstants.USER_DOMAIN;

	/** 缓存多个ID的信息 */
	private Map<IdGenName, IdCacheInfo> cacheMap = new ConcurrentHashMap<IdGenName, IdCacheInfo>();

	/**
	 * 获取下一个 公共ID
	 */
	public long getNextPublicId() {
		return nextId(IdGenName.PUBLIC);
	}

	/**
	 * 获取下一个 商家模块下的公共ID
	 */
	public long getNextStorePublicId() {
		return nextId(IdGenName.STORE_PUBLIC);
	}

	/**
	 * 获取下一个 门店 ID
	 */
	public long getNextStoreId() {
		return nextId(IdGenName.STORE);
	}

	/**
	 * 获取下一个 用户模块下的公共ID
	 */
	public long getNextUserPublicId() {
		return nextId(IdGenName.USER_PUBLIC);
	}

	/**
	 * 获取下一个 用户模块下的日志类ID
	 */
	public long getNextUserLogId() {
		return nextId(IdGenName.USER_LOG);
	}

	/**
	 * 获取下一个 用户ID
	 */
	public long getNextUserId() {
		return nextId(IdGenName.USER);
	}
	
	/**
	 * 获取下一个 订单模块下的评论ID
	 */
	public long getNextOrderCommentId() {
		return nextId(IdGenName.ORDER_PUBLIC);
	}
	
	/**
	 * 获取下一个 订单模块下的订单轨迹ID
	 */
	public long getNextOrderTrackId() {
		return nextId(IdGenName.ORDER_PUBLIC);
	}

	/**
	 * 获取下一个 订单ID
	 */
	public long getNextOrderId() {
		return nextId(IdGenName.ORDER);
	}
	
	/**
	 * 获取下一个 支付ID
	 */
	public long getNextPayId() {
		return nextId(IdGenName.PAY);
	}

	/**
	 * 获取下一个 支付系统公共ID
	 */
	public long getNextPayPublicId() {
		return nextId(IdGenName.PAY_PUBLIC);
	}

	/**
	 * 获取下一个 支付系统回调ID
	 */
	public long getNextPayCallbackId() {
		return nextId(IdGenName.PAY_CALLBACK);
	}

	/**
	 * 获取下一个 优惠券系统的公共ID
	 * @return
	 */
	public long getNextCouponPublicId(){
		return nextId(IdGenName.COUPON_PUBLIC);
	}
	
	/**
	 * 获取下一个配送id
	 */
	public long getNextExpressId(){
		return nextId(IdGenName.EXPRESS);
	}

	/**
	 * 获取下一个配送id
	 */
	public long getNextExpressPublicId(){
		return nextId(IdGenName.EXPRESS_PUBLIC);
	}

	/**
	 * 获取下一个 ticket 系统公共ID
	 * @return
	 */
	public long getNextTicketPublicId(){
		return nextId(IdGenName.TICKET_PUBLIC);
	}

	public long getNextTeamPayBuyId(){
		return nextId(IdGenName.TEAM_PAY_BUG_ID);
	}
	
	public long getNextTeamPayRecordId(){
		return nextId(IdGenName.TEAM_PAY_RECORD_ID);
	}
	
	/**
	 * 获取下一个ID
	 */
	private long nextId(IdGenName name) {
		ParameterUtil.assertNotNull(name, "idName不能为null");
		IdCacheInfo cache = cacheMap.get(name);

		// 保证一个key只有一个IdCacheInfo实例
		if (null == cache)
			synchronized (name) {
				cache = cacheMap.get(name);
				if (null == cache) {
					cache = new IdCacheInfo(name);// 一个IdGenName只创建一个cache对象
					cacheMap.put(name, cache);
				}
			}

		return cache.getId();
	}

	/**
	 * 读取下一个ID
	 * 
	 * @param idName - ID名
	 */
	private long[] read(IdGenName name) {// 枚举，JVM单例的，用来做同步主体
		ParameterUtil.assertNotNull(name, "idName不能为null");
		synchronized (name) {
			logger.debug("idGen#remote#readIds | read ids | name: {}", name.getName());

			Map<String, String> param = new HashMap<>(1);
			param.put("idName", name.getName());
//			 String json = HttpUtil.getUTF8(apiUrl, param);
			String json = HttpClientUtils.doGet(host + "/api/id/getNextId", param);
//			String json = HttpClientUtils.doGet("http://localhost:8080/api/id/getNextId", param);
			logger.debug("idGen#remote#readIds | read result | name: {}, result: '{}'", name.getName(), json);

			// JSONObject jsonObj = JSON.parseObject(json);
			JsonObject jsonObj = JsonUtils.toJsonObject(json);
			Integer code = jsonObj.getInt("code");

			if (code != null && ResultCode.SUCCESS == code) {
				// JSONArray range = jsonObj.getJSONArray("object");
				JsonArray range = jsonObj.getJsonArray("object");
				Integer start = range.getInt(0);
				Integer end = range.getInt(1);
				if (null != start && null != end) {
					logger.info("idGen#remote#readIds | success | name: {}, new start: {} - end: {}", name.getName(), start, end);
					return new long[] { start, end };
				}
			}

			logger.error("idGen#remote#readIds | read error | name: {}, result: '{}'", name.getName(), json);
			throw new IllegalStateException(name + "读取下一个ID失败");
		}
	}

	/**
	 * 缓存ID的信息
	 */
	class IdCacheInfo {

		private IdGenName name;
		// private int step = 1;
		private int safeValue = 5;

		/**
		 * 持有的一组一组的start、end值<br>
		 * 0: [100, 150]<br>
		 * 1: [200, 250] --- 有可能150~200被其他域个拿走了<br>
		 */
		private long[][] value = new long[0][0];

		public IdCacheInfo(IdGenName name) {
			this.name = name;
		}

		public IdGenName getName() {
			return name;
		}

		/**
		 * 获取可用ID
		 */
		public synchronized long getId() {// 锁当前对象
			stepCheck();// 此方法结束，value一定要有可用值

			long id = value[0][0];// 取用当前起始值
			value[0][0] = (id += 1);// 起始值+1
			return id;
		}

		// 保障value一定有值
		private void stepCheck() {
			must();

			long start = value[0][0];
			long end = value[0][1];

			if (start > end) {
				// 需要清理掉value[0]
				value = Arrays.copyOfRange(value, 1, value.length);
				must();
				start = value[0][0];
				end = value[0][1];
			}

			// 低于保险值，追加
			if (safeValue > (end - start) && 1 == value.length)
				ammunition();
		}

		// 保证value一定不是空数组（length != 0）
		private void must() {
			if (0 >= value.length)
				ammunition();
		}

		// 取值并追加到value
		private void ammunition() {
			long[] read = read(name);// Failure throw ex
			if (logger.isDebugEnabled())
				logger.debug("idGen#remote#readIds | ammunition | name: {}, old: {}, add: {}", name.getName(),
						JsonUtils.toJSONString(value), Arrays.toString(read));

			int lg = value.length;
			value = Arrays.copyOf(value, lg + 1);
			value[lg] = read;
		}

	}

}
