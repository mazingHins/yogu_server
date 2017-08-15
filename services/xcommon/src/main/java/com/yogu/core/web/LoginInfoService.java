/**
 * 
 */
package com.yogu.core.web;

import java.util.List;
import java.util.Map;

import com.yogu.core.DeviceType;

/**
 * 登录信息操作接口<br>
 *
 * @author JFan 2016年3月31日 下午4:32:24
 */
public interface LoginInfoService {

	// 日志存储的名称
	public static final String LOG_NAME = "LoginTokenInfo";

	/**
	 * 增加登录设备，如果同一个设备(DeviceType.valueOfAppName)存在，那么会覆盖<br>
	 * 前提是SecurityContext中包含BaseParams对象
	 * 
	 * @param uid 用户id
	 * @param passport 登录账号
	 * @param token 登录成功后，新分配的令牌
	 * @param secret 登录成功后，新分配的令牌密匙
	 */
	public void saveLoginInfo(long uid, String passport, String token, String secret);

	/**
	 * 读取已经登录的设备列表，返回里包含了每个设备登录的 token
	 * 
	 * @param uid - 用户ID
	 */
	public List<Map<String, Object>> getDevicesByUid(long uid);

	/**
	 * 获取用户在指定设备上登录之后颁发的userToken
	 */
	public String getTokenByUid(long uid, DeviceType device);

	/**
	 * 通过 token 读取UID，如果UID不存在，返回default值
	 */
	public long getUidByToken(String token, long defaultValue);

	/**
	 * 根据token读取用户登录颁发的密钥<br>
	 * 如果没有，返回null
	 */
	public String getSecretByToken(String token);

	/**
	 * 清除 当前登录设备用户的 device 缓存数据,不清除其他设备该用户数据<br>
	 * 同时清除用户当前使用的token 缓存数据<br>
	 * <b>前提：清除的是当前请求的用户</b>
	 */
	public void clearCurrDeviceToken(long uid);

	/**
	 * 清除指定设备上的登录信息
	 */
	public void clearDeviceSession(long uid, DeviceType device);

	/**
	 * 清空用户所有登录缓存信息
	 * 
	 * @param operatorId 操作者ID，如果是用户自己（比如修改密码）那就是用户自己的id
	 * @param uid 被清除的用户ID
	 */
	public void clearUserAllLoginCache(long operatorId, long uid);

}
