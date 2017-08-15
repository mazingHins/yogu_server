package com.yogu.remote.store;

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
import com.yogu.services.store.dish.dto.Dish;
import com.yogu.services.store.dish.dto.DishVO;

/**
 * 对菜品的远程操作服务类 <br>
 * 
 * @author JFan 2015年7月20日 下午12:32:55
 */
@Named
public class DishRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(DishRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	/**
	 * 搜索美食，分页形式返回
	 * 
	 * @param query 关键字
	 * @param pageIndex 第几页（1开始）
	 * @param pageSize 每页多少条数据
	 */
	public List<Dish> query(String query, int pageIndex, int pageSize) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("query", query);
			params.put("pageSize", pageSize + "");
			params.put("pageIndex", pageIndex + "");
			String json = HttpClientUtils.doGet(host + "/api/dish/query", 3000, params);
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#query | Error | query: {}, pageIndex: {}, pageSize: {}, message: {}"//
					, query, pageIndex, pageSize, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 搜索美食以及美食下的所有规格信息，分页形式返回
	 * 
	 * @param query 关键字
	 * @param pageIndex 第几页（1开始）
	 * @param pageSize 每页多少条数据
	 * @author sky 2016-02-27
	 */
	public List<DishVO> queryDishWithSpecList(String query, int pageIndex, int pageSize) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("query", query);
			params.put("pageSize", pageSize + "");
			params.put("pageIndex", pageIndex + "");
			String json = HttpClientUtils.doGet(host + "/api/dish/queryDishWithSpecList", 3000, params);
			RestResult<List<DishVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<DishVO>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#queryDishWithSpecList | Error | query: {}, pageIndex: {}, pageSize: {}, message: {}"//
					, query, pageIndex, pageSize, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 查询指定餐厅的全部在售美食信息
	 * 
	 * @param storeId 餐厅ID
	 */
	public List<Dish> queryOnShelfByStoreId(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/queryOnShelfByStoreId?storeId=" + storeId);
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#queryOnShelfByStoreId | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 查询指定美食在售的列表，若无，返回empty
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午9:24:00
	 * 
	 * @param dishKeys - 美食key，多个用英文逗号分隔
	 * @return 美食列表，若无，返回empty
	 */
	public List<Dish> queryOnShelfByDishKeys(String dishKeys) {
		logger.info("remote#dish#queryOnShelfByDishKeys | 查询指定美食在售的列表start | dishKeys: {}", dishKeys);
		if (StringUtils.isBlank(dishKeys)) {
			return Collections.emptyList();
		}
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/queryOnShelfByDishKeys?dishKeys=" + dishKeys);
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#queryOnShelfByDishKeys | 查询指定美食在售的列表 Error | dishKeys: {}, message: {}", dishKeys, e.getMessage(), e);
		}

		return Collections.emptyList();
	}

	/**
	 * 根据菜品ID获取内容
	 * 
	 * @param dishId 菜品ID
	 */
	public Dish getDish(long dishId) {
		try {
			// HttpStoreProviderImpl.getDishStoreId() 也有call此URL
			String json = HttpClientUtils.doGet(host + "/api/dish/get?dishId=" + dishId);
			RestResult<Dish> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Dish>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDish | Error | dishId: {}, message: {}", dishId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据菜品ID获取快照内容
	 * 
	 * @param dishId 菜品ID
	 */
	public Dish getDishTrack(long dishId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dishTrack/get?dishId=" + dishId);
			RestResult<Dish> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Dish>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishTrack | Error | dishId: {}, message: {}", dishId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据菜品dishKey获取菜品信息(可多个菜品ID)
	 * 
	 * @param dishKeys 菜品KEY列表
	 * @return 菜品信息，若无，返回null
	 */
	public List<Dish> getDishByKeys(String dishKeys) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/list?dishKeys=" + dishKeys);
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishByIds | Error | dishKeys: {}, message: {}", dishKeys, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 根据菜品Id获取菜品快照信息(可多个菜品ID)
	 * 
	 * @param dishIds 菜品Id
	 * @return 菜品信息，若无，返回null
	 */
	public List<Dish> getDishTrackByIds(String dishIds) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dishTrack/list?dishIds=" + dishIds);
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			if (result.isSuccess() && null != result.getObject())
				return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishByIds | Error | dishIds: {}, message: {}", dishIds, e.getMessage(), e);
		}
		return Collections.emptyList();
	}

}
