package com.yogu.web;
//package com.mazing.web;
//
//import com.mazing.commons.cache.redis.Redis;
//import com.mazing.commons.server.context.MainframeBeanFactory;
//import com.mazing.commons.utils.CollectionUtils;
//import com.mazing.commons.utils.JsonUtils;
//import com.mazing.core.web.LoginInfoService;
//import com.mazing.core.web.ParameterUtil;
//import com.mazing.core.web.UserErrorCode;
//import com.mazing.core.web.exception.ServiceException;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.*;
//
///**
// * 保存登录信息 对于同一个帐号（mobile）： 1. 同类型的设备，只允许一个登录。比如在iphone手机登录后，如果在其他手机登录，iphone手机的登录token将被清除； 2. 目前只支持phone/pad/pc三种设备登录 token内容，根据token查找： {
// * token: "登录token", uid: 用户ID, long login_time: 登录时间, update_time: 刷新时间, }
// * 
// * login用户内容，根据 uid 查找 { 'phone' : {}, 'pad' : {}, 'pc' : {} }
// * 
// * @author linyi 2015/5/21.
// */
//public class LoginInfoStore {
//
//	private static final Logger logger = LoggerFactory.getLogger(LoginInfoService.LOG_NAME);
//
//	/**
//	 * 用户ID
//	 */
//	private long uid;
//
//	/**
//	 * token
//	 */
//	private String token;
//
//	/**
//	 * 密钥
//	 */
//	private String secret;
//
//	/**
//	 * 已经登录的设备
//	 */
//	private List<Map<String, Object>> devices = null;
//
//	private Date loginTime;
//
//	private Redis redis;
//
//	private static final String PHONE = "phone";
//
//	private static final String PAD = "pad";
//
//	private static final String PC = "pc";
//
//	public static final String WEB = "web";
//
//	public LoginInfoStore() {
//	}
//
//	public LoginInfoStore(long uid, String token, String secret, Date loginTime) {
//		this.uid = uid;
//		this.token = token;
//		this.secret = secret;
//		this.loginTime = loginTime;
//	}
//
//	// public LoginInfoStore setUid(long uid) {
//	// this.uid = uid;
//	// return this;
//	// }
//	//
//	// public LoginInfoStore setToken(String token) {
//	// this.token = token;
//	// return this;
//	// }
//
//	/**
//	 * 增加登录设备
//	 * 
//	 * @param d - 设备取一个唯一的值，作用户的标识
//	 * @param s - 来源，比如iPhone/Android/iPad/androidPad/PC
//	 * @param sysName 系统名  ios/android
//	 * @param target ios版本区分线上、测试版本参数
//	 * @return this
//	 */
//	public LoginInfoStore addDevice(String d, String s, String sysName, String target) {
//		Map<String, Object> map = new HashMap<>(4);
//		String device = getSimpleDevice(s);
//		ParameterUtil.assertNotNull(device, "Unknown device source: " + s);
//		map.put("device", device);
//		map.put("loginTime", loginTime);
//		map.put("token", token);
//		map.put("s", s);
//		map.put("d", d);
//		// new add by sky 2015-11-17
//		map.put("sysName", sysName);
//		map.put("target", target);
//		
//		getDevices().add(map);
//		return this;
//	}
//
//	private List<Map<String, Object>> getDevices() {
//		if (devices == null) {
//			devices = new LinkedList<>();
//		}
//		return devices;
//	}
//
//	/**
//	 * 把设备简化为 3 种，转换为 phone(手机)、pad(包括ipad, androidPad)、pc 三种
//	 * 
//	 * @param s - 来源，比如iPhone/Android/iPad/androidPad/PC
//	 * @return 转换失败返回null
//	 */
//	public String getSimpleDevice(String s) {
//		String tmp = s.toLowerCase();
//		String device = null;
//		// 只需转换为 phone(手机)、pad(包括ipad, androidPad)、pc 三种
//		if (tmp.indexOf("iphone") >= 0 || tmp.indexOf("android") >= 0 || tmp.indexOf("wm") >= 0) {
//			device = PHONE;
//		} else if (tmp.indexOf("pad") >= 0) {
//			device = PAD;
//		} else if (tmp.indexOf("pc") >= 0) {
//			device = PC;
//		} else if (tmp.indexOf("web") >= 0) {
//			device = WEB;
//		}
//		return device;
//	}
//
//	/**
//	 * 返回 token 在 redis 保存的 key
//	 * 
//	 * @param token
//	 * @return
//	 */
//	public static String getKeyByToken(String token) {
//		return "LoginInfoStore_token_" + token;
//	}
//
//	/**
//	 * 返回 uid 在 redis 保存的 key
//	 * 
//	 * @param uid
//	 * @return
//	 */
//	public static String getKeyByUid(long uid) {
//		return "LoginInfoStore_uid_" + uid;
//	}
//
//	/**
//	 * 通过 token 读取UID，如果UID不存在，抛出异常
//	 * 
//	 * @param token
//	 * @return
//	 */
//	public long getUidByToken(String token) {
//		long uid = getUidByToken(token, 0L);
//		if (uid <= 0) {
//			throw new ServiceException(UserErrorCode.USER_NOT_LOGIN, "用户没有登录");
//		}
//		return uid;
//	}
//
//	/**
//	 * 通过 token 读取UID，如果UID不存在，返回default值
//	 * 
//	 * @param token
//	 * @param defaultValue - 默认值
//	 * @return 如果用户已经登录，返回uid，否则返回 defaultValue
//	 */
//	public long getUidByToken(String token, long defaultValue) {
//		String tokenKey = getKeyByToken(token);
//		String strUid = redis().hget(tokenKey, "uid");
//		logger.debug("login#store | getUidByToken | token: {}, uid: {}, def: {}", token, strUid, defaultValue);
//		return NumberUtils.toLong(strUid, defaultValue);
//	}
//
//	/**
//	 * 读取用户登录颁发的密钥
//	 * 
//	 * @param token - User Token
//	 * @return 返回密钥，如果没有，返回null
//	 */
//	public String getSecretByToken(String token) {
//		String tokenKey = getKeyByToken(token);
//		String tmp = redis().hget(tokenKey, "secret");
//		if (StringUtils.isEmpty(tmp)) {
//			logger.error("login#store | Cannot find token | token: {}", token);
//		}
//		return tmp;
//	}
//
//	/**
//	 * 读取已经登录的设备列表，返回里包含了每个设备登录的 token
//	 * 
//	 * @param uid - 用户ID
//	 * @return 如果没有登录，不会返回null，返回empty list
//	 */
//	public List<Map<String, Object>> getDevicesByUid(long uid) {
//		String uidKey = getKeyByUid(uid);
//		List<Map<String, Object>> tmpList = new LinkedList<>();
//		String[] names = new String[] { PHONE, PAD, PC };
//		for (String name : names) {
//			String current = redis().hget(uidKey, name);
//			if (StringUtils.isNotBlank(current)) {
//				// Map<String, Object> device = JSON.parseObject(current, new TypeReference<Map<String, Object>>() {
//				// });
//				Map<String, Object> device = JsonUtils.parseMap(current, Object.class);
//				tmpList.add(device);
//			}
//		}
//		return tmpList;
//	}
//
//	/**
//	 * 保存 token 和 用户信息
//	 */
//	public void save() {
//		// save token
//		if (StringUtils.isNotBlank(token)) {
//			String tokenKey = getKeyByToken(token);
//
//			redis().hset(tokenKey, "uid", uid + "");
//			redis().hset(tokenKey, "secret", secret);
//			redis().hset(tokenKey, "loginTime", loginTime.getTime() + "");
//			redis().hset(tokenKey, "updateTime", loginTime.getTime() + "");
//			logger.info("login#store | store token | key: {}, uid: {}", tokenKey, uid);
//		}
//		// save user devices
//		if (uid > 0 && getDevices().size() > 0) {
//			String uidKey = getKeyByUid(uid);
//			// logger.info("saving devices|uidKey=" + uidKey + "|size=" + getDevices().size());
//			for (Map<String, Object> map : getDevices()) {
//				String newDeviceStr = JsonUtils.toJSONString(map);
//				String device = (String) map.get("device");
//				String current = redis().hget(uidKey, device);
//
//				if (StringUtils.isNotBlank(current)) {
//					logger.info("login#store | kick old login | uid: {}, device: {}, current: {}, new: {}", uid, device, current,
//							newDeviceStr);
//					// 已经有这个设备登录，替代它的登录，并删除它的登录状态
//					// Map<String, Object> old = JSON.parseObject(current, new TypeReference<Map<String, Object>>() {
//					// });
//					Map<String, Object> old = JsonUtils.parseMap(current, Object.class);
//					String oldToken = (String) old.get("token");
//					if (StringUtils.isNotBlank(oldToken)) {
//						String tokenKey = getKeyByToken(oldToken);
//						redis().del(tokenKey);
//					}
//				}
//
//				logger.info("login#store | store device | device: {}", newDeviceStr);
//				redis().hset(uidKey, device, newDeviceStr);
//			} // end for
//		} // end if
//	}
//
//	/**
//	 * 
//	 * 
//	 * 清除用户 指定 userToken 所对应的缓存数据
//	 * 
//	 * @param uid 用户id
//	 * @param userToken 用户token
//	 * @author sky
//	 */
//	public void clearTokenCache(long uid, String userToken) {
//		logger.info("login#store#clearTokenCache | before clear token cache | uid: {}, userToken: {}", uid, userToken);
//
//		String cacheKey = getKeyByToken(userToken);
//		// 清除用户缓存数据
//		redis().del(cacheKey);
//	}
//
//	/**
//	 * 清除设备的登录
//	 * @param uid 用户ID
//	 * @param device 设备
//	 */
//	public void clearDeviceLoginSession(long uid, String device) {
//		logger.info("login#store#clearDeviceLoginCache | 清除设备登录start | uid: {}, device: {}", uid, device);
//		if (uid > 0 && StringUtils.isNotBlank(device)) {
//			String simpleDevice = getSimpleDevice(device);
//			if (StringUtils.isNotEmpty(simpleDevice)) {
//				String uidKey = getKeyByUid(uid);
//				String old = redis().hget(uidKey, simpleDevice);
//				if (StringUtils.isNotEmpty(old)) {
//					redis().hdel(uidKey, simpleDevice);
//					logger.info("login#store#clearDeviceLoginCache | 清除设备登录end | old token: {}", old);
//				}
//				else {
//					logger.error("login#store#clearDeviceLoginCache | 清除设备登录end，设备没有登录 | device: {}", device);
//				}
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * <b>前提:清除的是当前请求的用户</b><br>
//	 * 清除 当前登录设备用户的 device 缓存数据,不清除其他设备该用户数据
//	 * 
//	 * @param uid 用户uid
//	 * @author sky
//	 */
//	public void clearCurrLoginUserDeviceCache(long uid) {
//		// 得到当前设备用户 的 设备名
//		String deviceName = DeviceUtil.getRequestDevice();
//		// simple name
//		String currDevice = getSimpleDevice(deviceName);
//
//		logger.info("login#store#clearUserDeviceCache | before clear current user device cache | uid: {}, currDevice: {}", uid, currDevice);
//
//		// 清除该用户设备 缓存数据
//		String uidCachekey = getKeyByUid(uid);
//
//		redis().hdel(uidCachekey, currDevice);
//	}
//
//	/**
//	 * 清空用户所有登录缓存信息
//	 * 
//	 * @param uid 用户uid
//	 * @author sky
//	 */
//	public void clearUserAllLoginCache(long operator, long uid) {
//
//		logger.info("login#store#clearUserAllLoginCache | before clear user login cache | operator: {}, uid: {}", operator, uid);
//
//		List<Map<String, Object>> deviceList = getDevicesByUid(uid);
//		if (CollectionUtils.isNotEmpty(deviceList)) {
//			for (Map<String, Object> map : deviceList) {
//
//				String mDevice = (String) map.get("device");
//
//				String mToken = (String) map.get("token");
//
//				String needClearedTokenCachekey = getKeyByToken(mToken);
//
//				String needCleardUidCachekey = getKeyByUid(uid);
//
//				redis().del(needClearedTokenCachekey);
//				redis().hdel(needCleardUidCachekey, mDevice);
//
//			}
//		}
//
//	}
//
//	/**
//	 * 强制下线其他登录设备
//	 * 
//	 * @param uid 当前用户id
//	 * @param currDevice 当前设备
//	 * @param oldToken 当前设备用户的 验证信息user token
//	 */
//	public void forcedOthersOffLine(long uid, String currDevice, String oldToken) {
//
//		logger.info("login#store#forcedOthersOffLine | before clear others devices off line | uid: {}, currDevice: {}, oldToken: {}", uid,
//				currDevice, oldToken);
//
//		try {
//			List<Map<String, Object>> devicesList = getDevicesByUid(uid);
//
//			// 清除设备列表中 除当前登录设备外的 其他设备 登录信息 (login invalid)
//			if (CollectionUtils.isNotEmpty(devicesList)) {
//
//				for (Map<String, Object> map : devicesList) {
//
//					String mDevice = (String) map.get("device");
//
//					if (!currDevice.equals(mDevice)) {// 不是当前登录设备,进行清除
//
//						String mToken = (String) map.get("token");
//
//						if (!mToken.equals(oldToken)) { // 不是当前登录用户的token,进行清除
//
//							String needClearedTokenCachekey = getKeyByToken(mToken);
//
//							String needCleardUidCachekey = getKeyByUid(uid);
//
//							redis().del(needClearedTokenCachekey);
//							redis().hdel(needCleardUidCachekey, mDevice);
//
//						} else {
//							continue;// 不可能发生，外层已做过滤
//						}
//					} else {
//						continue;
//					}
//				}
//			} else {
//				logger.warn("login#store#forcedOthersOffLine | get logged in device error | uid: {}", uid);
//			}
//		} catch (Exception e) {
//			logger.error("login#store#forcedOthersOffLine | clear others device error | msg: {}", e.getMessage(), e);
//		}
//
//	}
//
//	private Redis redis() {
//		if (null == redis)
//			redis = (Redis) MainframeBeanFactory.getBean("redis");
//		return redis;
//	}
//
//}
