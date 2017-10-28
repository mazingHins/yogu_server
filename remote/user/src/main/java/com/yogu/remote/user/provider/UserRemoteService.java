package com.yogu.remote.user.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.dto.UserProfile;


/**
 * user域，user有关的操作 <br>
 * 
 * @author Hins
 * @version createTime：2015年7月17日 上午10:55:36
 */
@Named
public class UserRemoteService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRemoteService.class);

	private static final String host = CommonConstants.USER_DOMAIN;
	
	/**
	 * 根据userId获取UserProfile信息
	 */
	public UserProfile getUserProfileByUid(long uid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/v1/user/id/" + uid);

			RestResult<UserProfile> result = JsonUtils.parseObject(json, new TypeReference<RestResult<UserProfile>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("user#remote#getUserPsofile | Error | uid: {}, message: {}", uid, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 根据地址id获取UserAddress信息
	 * 
	 * @param id
	 *            - 地址id
	 * @return UserAddress
	 */
	public UserAddress getUserAddressById(long id) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/v1/user/address/id/" + id);

			RestResult<UserAddress> result = JsonUtils.parseObject(json, new TypeReference<RestResult<UserAddress>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("user#remote#getUserAddressById | Error | addressId: {}, message: {}", id, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 根据用户id、地址id获取UserAddress信息<br>
	 * 该方法暂时只用于下单流程，因为不想2次查询地址和用户（查询用户只是用于校验用户id）
	 * 
	 * @param uid - 用户id
	 * @param addressId - 地址id
	 * @author hins
	 * @date 2016年10月1日 上午10:26:21
	 * @return UserAddress
	 */
	public UserAddress getUserAddressByIdAndUid(long uid, long addressId) {
		logger.info("user#remote#getUserAddressByIdAndUid | getUserAddressByIdAndUid开始 | addressId: {}, uid: {}", addressId, uid);
		try {
			String json = HttpClientUtils.doGet(host + "/api/v1/user/address/addressId/" + addressId + "/uid/" + uid);

			RestResult<UserAddress> result = JsonUtils.parseObject(json, new TypeReference<RestResult<UserAddress>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("user#remote#getUserAddressByIdAndUid | Error | addressId: {}, uid: {}, message: {}", addressId, uid, e.getMessage(), e);
		}
		return null;

	}
	
	/**
	 * 获取我的收货地址列表
	 * 
	 * @param uid
	 *            - 用户ID
	 * @return 地址列表，若无，返回empty list
	 */
	public List<UserAddress> listMyAddress(long uid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/v1/user/address/list/" + uid);

			RestResult<List<UserAddress>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<UserAddress>>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("user#remote#listMyAddress | Error | uid: {}, message: {}", uid, e.getMessage(), e);
		}
		return new ArrayList<UserAddress>();
	}
	
	/**
	 * 根据用户id、地址id，获取用户和地址信息<br>
	 * 返回的结果可能地址、用户其中一个为null
	 * @param uid - 用户id
	 * @param addressId - 地址id
	 * @author hins
	 * @date 2016年10月2日 下午12:40:53
	 * @return UserAndAddress
	 */
	public UserAndAddress getUserAndAddress(long uid, long addressId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/v1/user/address/uid/" + uid + "/addressId/" + addressId);

			RestResult<UserAndAddress> result = JsonUtils.parseObject(json, new TypeReference<RestResult<UserAndAddress>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("user#remote#getUserAndAddress | Error | uid: {}, message: {}", uid, e.getMessage(), e);
		}
		return null;
	}

	
	/**
	 * 根据帐号读取 uid 等信息，不会返回null
	 * 
	 * @param countryCode
	 *            国家代码
	 * @param passport
	 *            帐号
	 * @return 返回json信息{uid: ..., nickname: ...}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getUidByPassport(String countryCode, String passport) {
		Map<String, String> map = new HashMap<>(4);
		map.put("countryCode", StringUtils.trimToEmpty(countryCode));
		map.put("passport", StringUtils.trimToEmpty(passport));
		try {
			String json = HttpClientUtils.doGet(host + "/api/v1/user/get", map);

			RestResult<HashMap> result = JsonUtils.parseObject(json, new TypeReference<RestResult<HashMap>>() {
			});
			if (result.isSuccess() && result.getObject() != null)
				return result.getObject();
		} catch (Exception e) {
			logger.error("user#remote#getUidByPassport | Error | countryCode: {}, passport: {}", countryCode, "***", e);
		}
		return Collections.emptyMap();
	}
	
	/**
	 * 根据用户ID（多个）批量查询用户信息 根据查询结果，装载到map返回，key-用户ID，value-用户信息
	 * 
	 * @author Hins
	 * @date 2015年8月17日 上午11:45:57
	 * 
	 * @param uid
	 * @return UserProfile信息集合，key-userId，value-UserProfile，若无返回empty map
	 */
	public Map<Long, UserProfile> getUserProfileByUids(long... uid) {
		Map<Long, UserProfile> map = new HashMap<Long, UserProfile>(uid.length * 4 / 3 + 1);
		try {
			String params = "";
			if (uid != null) {
				params = StringUtils.join(uid, ',');
				// for (long id : uid) {
				// params = params + "," + id;
				// }
				// params = params.substring(1, params.length());
			}
			String json = HttpClientUtils.doGet(host + "/api/v1/user/list?uids=" + params);

			RestResult<List<UserProfile>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<UserProfile>>>() {
					});
			List<UserProfile> userList = result.getObject();
			if (userList == null || userList.isEmpty())
				return map;
			for (UserProfile user : userList)
				map.put(user.getUid(), user);
			return map;
		} catch (Exception e) {
			logger.error("user#remote#getUserProfileByUids | Error | uid: {}, message: {}", uid, e.getMessage(), e);
		}
		return map;
	}
	

}
