/**
 * 
 */
package com.yogu.core.web.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.DeviceType;
import com.yogu.core.base.BaseParams;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.mq.ServerMsgNotice;
import com.yogu.mq.ServerMsgService;

/**
 * 登录信息操作接口实现<br>
 * 基于两层缓存
 *
 * @author JFan 2016年3月31日 下午4:32:53
 */
@Named
public class LoginInfoServiceImpl implements LoginInfoService, ServerMsgNotice {

	private static final Logger logger = LoggerFactory.getLogger(LOG_NAME);

	// message：为需要移除的key列表，多个key由'|||'分隔（不含''）
	private static final String MSG_ID = "LoginInfo__DeleteAction";
	private static final String MSG_SEPARATOR = "|||";

	@Inject
	@Named("redis")
	private Redis redis;

	@Inject
	private ServerMsgService serverMsgService;

	// 有一个小风险：不像redis一样，可以单独设置某个field
	// 这里需要把整个value(Map)先从local get出来，value.put数据之后再local.set进去
	// 那么在get-set这个时间内，cache的内容不排除有变化的可能
	private Cache<String, Map<String, String>> local;

	@PostConstruct
	public void initial() {
		// 本地缓存：2分钟失效、key为弱引用、value为弱引用
		local = CacheBuilder.newBuilder().weakKeys().weakValues().expireAfterWrite(120, TimeUnit.SECONDS).build();

		// 注册需要监听的消息
		serverMsgService.regNotice(MSG_ID, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveLoginInfo(long uid, String passport, String token, String secret) {
		BaseParams bp = ThreadLocalContext.getThreadValue(SecurityContext.BASE_PARAMS);

		if (0 < uid && StringUtils.isNotBlank(token)) {
			String appName = (null != bp ? bp.getAppName() : "");
			appName = StringUtils.trimToEmpty(appName);
			String sysName = (null != bp ? bp.getSysName() : "");
			sysName = StringUtils.trimToEmpty(sysName);
			String target = (null != bp ? bp.getTarget() : "");
			target = StringUtils.trimToEmpty(target);
			Date loginTime = new Date();
			
			String version = (null !=bp ? bp.getAppVersion() : "");//add by Sky 2016-12-19

			// 反查s，属于什么设备
			DeviceType type = DeviceType.valueOfAppName(appName);
			String device = (null != type ? type.getName() : "");

			// 拿到这个device已经登录的信息
			String uidKey = getKeyByUid(uid);
			String uidOldCache = redis.hget(uidKey, device);

			// ================================================================
			// 存储到cache（两层）：key：'xx'+uid 、 value：'json string'
			// 'json string' 的结构参考下面这个map
			Map<String, Object> uidNewCacheMap = new HashMap<>();
			uidNewCacheMap.put("loginTime", loginTime);// 登录时间
			uidNewCacheMap.put("sysName", sysName);// 系统名称
			uidNewCacheMap.put("appName", appName);// APP名称
			uidNewCacheMap.put("device", device);// 登录的设备类型
			uidNewCacheMap.put("target", target);// ios才有的 target（push要用到）
			uidNewCacheMap.put("token", token);// 登录时颁发的令牌
			uidNewCacheMap.put("d", passport);// 登录账号
			
			uidNewCacheMap.put("version", version);// 客户端app版本号 add by Sky 2016-12-19
			String uidNewCache = JsonUtils.toJSONString(uidNewCacheMap);

			// set to redis
			logger.info("login#store | 添加用户登录设备信息 to redis | key(uid): {}, cache: {}", uidKey, uidNewCache);
			redis.hset(uidKey, device, uidNewCache);// 更新单个field

			// ================================================================
			// key：'xx'+token
			// value：hash类型（hash的kv，参考cache对象）
			Map<String, String> tokenNewCache = new HashMap<>();
			tokenNewCache.put("uid", uid + "");// 用户id
			tokenNewCache.put("secret", secret);// 登录成功后颁发的令牌密匙
			tokenNewCache.put("loginTime", loginTime.getTime() + "");// 登录时间
			tokenNewCache.put("updateTime", loginTime.getTime() + "");// 修改时间

			// 隐藏secret
			Map<String, String> tokenNewCacheTmp = new HashMap<>(tokenNewCache);
			tokenNewCacheTmp.put("secret", "****");
			String tmpStr = JsonUtils.toJSONString(tokenNewCacheTmp);

			// set to redis
			String tokenKey = getKeyByToken(token);
			logger.info("login#store | 保存用户登录信息 to redis | key(token): {}, cache: {}", tokenKey, tmpStr);
			redis.hmset(tokenKey, tokenNewCache);

			// ================================================================
			// 读取并移除旧的token
			String oldTokenKey = null;
			if (StringUtils.isNotBlank(uidOldCache)) {
				Map<String, Object> uidOldDeviceCache = JsonUtils.parseMap(uidOldCache, Object.class);
				oldTokenKey = (String) uidOldDeviceCache.get("token");
				oldTokenKey = getKeyByToken(oldTokenKey);
				redis.del(oldTokenKey);
			}

			// ================================================================
			// 发送移除local 旧缓存的消息
			// 本地移除后，get不到数据，会重新从redis中读取
			List<String> keys = new ArrayList<String>();
			if (StringUtils.isNotBlank(oldTokenKey))
				keys.add(oldTokenKey);
			keys.add(uidKey);
			// keys.add(tokenKey);//顺带
			sendMsg(keys);
		}
		// 输出错误信息
		else {
			Map<String, Object> data = new HashMap<>();
			data.put("baseParams", bp);
			data.put("token", token);
			data.put("d", passport);
			data.put("uid", uid);
			if (null != secret)
				data.put("secret", (0 < secret.length() ? "****" : ""));
			logger.error("login#store | 需要保存的用户信息不合法 | data: '{}'", JsonUtils.toJSONString(data));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Map<String, Object>> getDevicesByUid(long uid) {
		String uidKey = getKeyByUid(uid);
		Map<String, String> values = redis.hgetAll(uidKey);

		List<Map<String, Object>> result = new ArrayList<>();
		if (MapUtils.isNotEmpty(values))
			for (String value : values.values())
				result.add(JsonUtils.parseMap(value, Object.class));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTokenByUid(long uid, DeviceType device) {
		String field = device.getName();
		String uidKey = getKeyByUid(uid);
		String value = hget(uidKey, field);
		if (StringUtils.isBlank(value))
			return null;

		Map<String, Object> map = JsonUtils.parseMap(value, Object.class);
		return (String) map.get("token");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getUidByToken(String token, long defaultValue) {
		String key = getKeyByToken(token);
		String value = hget(key, "uid");
		logger.debug("login#store | getUidByToken | token: {}, uid: {}, def: {}", token, value, defaultValue);
		return NumberUtils.toLong(value, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSecretByToken(String token) {
		String key = getKeyByToken(token);
		String tmp = hget(key, "secret");
		if (StringUtils.isEmpty(tmp))
			logger.error("login#store | Cannot find token | token: {}", token);
		return tmp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearCurrDeviceToken(long uid) {
		// 清除当前正在使用的token数据
		String userToken = SecurityContext.getUserToken();
		if (null != userToken) {
			String tokenKey = getKeyByToken(userToken);

			// 只移除redis的，local的会在 收到消息时移除
			logger.info("login#store#del | 移除redis缓存数据 | key(token): {}", tokenKey);
			redis.del(tokenKey);

			// 清除当前用户设备的数据
			DeviceType device = null;
			BaseParams bp = SecurityContext.getBaseParams();
			if (null != bp)
				device = DeviceType.valueOfAppName(bp.getAppName());

			// 清除内容
			clearDeviceCache(uid, device, tokenKey);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearDeviceSession(long uid, DeviceType device) {
		clearDeviceCache(uid, device);
	}

	/**
	 * 清除指定用户（uid）的指定设备（device）的登录信息<br>
	 * 当uid不合法（<=0）或者device为空时，只会发送msg
	 * 
	 * @param uid 用户id
	 * @param device 用户的设备类型
	 * @param keys 当清理完数据之后，需要而外清理的key（发送消息）
	 */
	private void clearDeviceCache(long uid, DeviceType device, String... keys) {
		List<String> clearKeys = new ArrayList<>();
		if (ArrayUtils.isNotEmpty(keys))
			for (String key : keys)
				clearKeys.add(key);

		if (0 > uid && null != device) {
			String name = device.getName();
			String uidKey = getKeyByUid(uid);
			String cache = hget(uidKey, name);// value 是一个json格式的str
			if (StringUtils.isNotEmpty(cache)) {
				// 清除基于uid的对应设备的cache
				logger.info("login#store#clearDeviceSession | 清除已登录的设备 | uid: {}, device: {}, cache: {}", uid, name, cache);
				redis.hdel(uidKey, name);
				clearKeys.add(uidKey);

				// 清除对应的token缓存
				try {
					Map<String, Object> oldValue = JsonUtils.parseMap(cache, Object.class);
					String token = (String) oldValue.get("token");
					if (StringUtils.isNotBlank(token)) {
						logger.info("login#store#clearDeviceSession | 清除已登录设备对应的token | uid: {}, device: {}, token: {}", uid, name, token);
						String tokenKey = getKeyByToken(token);
						redis.del(tokenKey);
						clearKeys.add(tokenKey);
					}
				} catch (Exception e) {
					logger.info("login#store#clearDeviceSession | 清除已登录设备对应的token ERROR | uid: {}, device: {}, cache: {}", uid, name, cache);
				}
			}
		}

		// 发送移除local缓存的消息
		if (CollectionUtils.isNotEmpty(clearKeys))
			sendMsg(clearKeys);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearUserAllLoginCache(long operatorId, long uid) {
		logger.info("login#store#clearUserAllLoginCache | 清理用户的所有登录信息 | 操作者ID: {}, uid: {}", operatorId, uid);
		String uidKey = getKeyByUid(uid);
		Map<String, String> cache = redis.hgetAll(uidKey);// 以集中式cache的为准

		List<String> keys = new ArrayList<>();

		// 移除基于uid的cache
		logger.info("login#store#clearUserAllLoginCache | 准备删除用户uid的登录信息 | key: {}", uidKey);
		redis.del(uidKey);
		keys.add(uidKey);

		if (MapUtils.isNotEmpty(cache)) {
			for (String value : cache.values()) {
				Map<String, Object> map = JsonUtils.parseMap(value, Object.class);
				String token = (String) map.get("token");
				// 移除基于token的cache
				String tokenKey = getKeyByToken(token);
				logger.info("login#store#clearUserAllLoginCache | 准备删除用户token的登录信息 | key: {}", tokenKey);
				redis.del(tokenKey);
				keys.add(tokenKey);
			}
		}

		// 发送消息，移除local的缓存
		sendMsg(keys);
	}

	// ####

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notice(String messageId, String message) {
		logger.info("login#store | 接收到消息 | messageId: '{}', message: '{}'", messageId, message);
		if (StringUtils.isBlank(message))
			return;

		// 只清理local缓存，redis缓存由消息发送者清除
		String[] keys = StringUtils.split(message, MSG_SEPARATOR);
		for (String key : keys) {
			logger.info("login#store | 移除local缓存数据 | key: '{}'", key);
			local.invalidate(key);
		}
	}

	// ####
	// ## private func
	// ####

	/**
	 * 发送一个移除local缓存的消息
	 * 
	 * @param keys 需要移除的key列表
	 */
	private void sendMsg(List<String> keys) {
		if (CollectionUtils.isNotEmpty(keys)) {
			String message = StringUtils.join(keys, MSG_SEPARATOR);
			logger.info("login#store | 发送一个移除local缓存的消息 | message: '{}'", message);
			serverMsgService.send(MSG_ID, message);
		}
	}

	/**
	 * 返回一个基于token的key
	 */
	private String getKeyByToken(String token) {
		return "LoginInfoStore_token_" + token;
	}

	/**
	 * 返回一个基于uid的key
	 */
	private String getKeyByUid(long uid) {
		return "LoginInfoStore_uid_" + uid;
	}

	/**
	 * 从两层cache中（hash），读取field字段的内容<br>
	 * 根据key先从本地缓存中读取，没有的话才去redis中加载（并存储到本地）
	 */
	private String hget(final String key, String field) {
		Map<String, String> cache = null;
		try {
			// 本地没有数据，会去redis加载，并存储在本地中
			// 具备防穿透的功能
			cache = local.get(key, new Callable<Map<String, String>>() {

				public Map<String, String> call() throws Exception {
					return redis.hgetAll(key);// 因为要将hash内容缓存在本地，所以是一次性读取全部的field
				}

			});
		} catch (ExecutionException e) {
			logger.error("loginInfo#getCache | ERROR | key: {}, msg: {}", key, e.getMessage(), e);
		}

		if (null == cache)
			return null;
		return cache.get(field);
	}

}
