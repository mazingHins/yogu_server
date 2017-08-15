package com.yogu.core.remote.store.provider.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Priority;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.cache.annotation.Cacher;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.enums.Role;
import com.yogu.core.remote.store.provider.StoreProvider;
import com.yogu.core.web.RestResult;

/**
 * 读取store域相关信息的适配器 的HTTP实现<br>
 * HTTP优先级为20<br>
 * store域有个DB实现，优先级为10（高于HTTP）
 *
 * @author JFan 2015年11月9日 下午5:01:55
 */
@Named
@Priority(20)
public class HttpStoreProviderImpl implements StoreProvider {

	private static final Logger logger = LoggerFactory.getLogger(HttpStoreProviderImpl.class);

	private final String host = CommonConstants.STORE_DOMAIN;

	/**
	 * 获取用户在指定门店下的角色
	 * 
	 * @param storeId 门店ID
	 * @param uid userId
	 */
	@Cacher(STORE_USER_STAFF_PREFIX)
	public Boolean whetherStoreStaff(long storeId, long uid) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/staff/roles?storeId=" + storeId + "&uid=" + uid);
			logger.debug("remote#auth#store | getStaff | storeId: {}, uid: {}, json: {}", storeId, uid, json);
			RestResult<List<Role>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Role>>>() {
			});
			List<?> list = result.getObject();
			return CollectionUtils.isNotEmpty(list);
		} catch (Exception e) {
			logger.error("remote#auth#store | getStaff Error | storeId: {}, uid: {}, message: {}", storeId, uid, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 读取菜品所属的门店ID
	 * 
	 * @param dishId 菜品ID
	 */
	@Cacher(STORE_DISH_PREFIX)
	public Long getDishStoreId(long dishId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/get?dishId=" + dishId);
			logger.debug("remote#auth#store | getDish | dishId: {}, json: {}", dishId, json);
			RestResult<Map<?, ?>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<?, ?>>>() {
			});
			Map<?, ?> map = result.getObject();
			if (null != map) {
				Integer sid = (Integer) map.get("storeId");
				if (null != sid)
					return sid.longValue();
			}
		} catch (Exception e) {
			logger.error("remote#auth#store | getDish Error | dishId: {}, message: {}", dishId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 读取服务范围所属的门店ID
	 * 
	 * @param rangeId 配送ID
	 */
	@Cacher(value = STORE_RANGE_PREFIX, time = 360)
	public Long getRangeStoreId(long rangeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/service/get?rangeId=" + rangeId);
			logger.debug("remote#auth#store | getRange | rangeId: {}, json: {}", rangeId, json);
			RestResult<Map<?, ?>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<?, ?>>>() {
			});
			Map<?, ?> map = result.getObject();
			if (null != map) {
				Integer sid = (Integer) map.get("storeId");
				if (null != sid)
					return sid.longValue();
			}
		} catch (Exception e) {
			logger.error("remote#auth#store | getRange Error | rangeId: {}, message: {}", rangeId, e.getMessage(), e);
		}
		return null;
	}

}
