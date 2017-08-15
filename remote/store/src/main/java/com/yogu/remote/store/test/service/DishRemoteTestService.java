package com.yogu.remote.store.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.remote.store.test.param.UpdateDishParam;
import com.yogu.services.store.dish.dto.Dish;

/**
 * 对菜品的远程操作服务类 <br>
 * 用于简易后台修改测试数据
 * 
 * @author Felix 2015年9月2日
 */
@Named
public class DishRemoteTestService {
	private static final Logger logger = LoggerFactory.getLogger(DishRemoteTestService.class);
	
	private static final String host = CommonConstants.STORE_DOMAIN;;


	/**
	 * 根据菜品ID获取门店内容
	 * 
	 * @param dishId 菜品ID
	 * @return 菜品信息
	 */
	public Dish getDishById(long dishId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/get?dishId=" + dishId);
			RestResult<Dish> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Dish>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishById | Error | dishId: {}, message: {}", dishId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据菜品dishKey获取菜品信息(可多个菜品ID)
	 * 
	 * @param dishKeys 菜品KEY列表
	 * @return 菜品信息，若无，返回null
	 */
	public List<Dish> getDishByIds(String dishKeys) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/list?dishKeys=" + dishKeys);
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			return result.getObject() == null || result.getObject().isEmpty() ? null : result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishByIds | Error | dishKeys: {}, message: {}", dishKeys, e.getMessage(), e);
		}
		return null;
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
			return result.getObject() == null || result.getObject().isEmpty() ? null : result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishByIds | Error | dishIds: {}, message: {}", dishIds, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 根据门店Id获取菜品快照信息
	 * 
	 * @param storeId 门店Id
	 * @return 菜品信息，若无，返回null
	 */
	public List<Dish> getDishesByStoreId(long storeId){
		try {

			String json = HttpClientUtils.doGet(host + "/api/dish/listAll?storeId=" + storeId);
			
			//String json2 = HttpClientUtils.dop
			RestResult<List<Dish>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Dish>>>() {
			});
			return result.getObject() == null || result.getObject().isEmpty() ? null : result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishByStoreId | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 用于简易后台
	 * 设置菜品上下架状态
	 * 
	 * @param storeId 门店Id
	 * @return 菜品信息，若无，返回null
	 */
	public void setDishStatus(long dishId, short status){
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("dishId", dishId+"");
			param.put("status", status+"");
			String json = HttpClientUtils.doPost(host + "/api/dish/setDishStatus.do",param);
			
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			
		} catch (Exception e) {
			logger.error("remote#dish#setDishStatus | Error | dishId: {}, status:{}, message: {}", dishId, status, e.getMessage(), e);
		}
		return;
	}
	
	/**
	 * 用于简易后台
	 * 设置菜品库存
	 * 
	 * @param storeId 门店Id
	 * @return 菜品信息，若无，返回null
	 */
	public void setDishSurplus(long dishId, int dailyNum){
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("dishId", dishId+"");
			param.put("dailyNum", dailyNum+"");
			String json = HttpClientUtils.doPost(host + "/api/dish/setSurplus.do",param);
			
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			
		} catch (Exception e) {
			logger.error("remote#dish#setDishSurplus | Error | dishId: {}, dailyNum:{}, message: {}", dishId, dailyNum, e.getMessage(), e);
		}
		return;
	}
	
	/**
	 * 用于简易后台
	 * 获取菜品信息
	 * 
	 * @param dishKey 菜品key
	 * @return 
	 */
	public UpdateDishParam getDishParam(long dishKey){
		try {

			String json = HttpClientUtils.doGet(host + "/api/dish/details?dishId=" + dishKey);
			
			//String json2 = HttpClientUtils.dop
			RestResult<UpdateDishParam> result = JsonUtils.parseObject(json, new TypeReference<RestResult<UpdateDishParam>>() {
			});
			return result.getObject() == null ? null : result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getDishParam | Error | dishKey: {}, message: {}", dishKey, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 用于简易后台
	 * 获取菜品信息
	 * 
	 * @param UpdateDishParam 菜品参数表
	 * @return 
	 */
	public RestResult<Object> updateDish(UpdateDishParam param){
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("dishId", param.getDishId()+"");
			// map.put("dishKey", param.getDishKey()+"");
			map.put("categoryId", param.getCategoryId()+"");
			map.put("content", param.getContent());
			map.put("currencyType", param.getCurrencyType()+"");
			map.put("dailyNum", param.getDailyNum()+"");
			map.put("delPicIds", param.getDelPicIds());
			map.put("dishName", param.getDishName());
			map.put("expiryTime", param.getExpiryTime());
			map.put("favImg", param.getFavImg());
			map.put("idPathEntry", param.getIdPathEntry());
			map.put("originalPrice", param.getOriginalPrice()+"");
			map.put("price", param.getPrice()+"");
			map.put("promoteTag", param.getPromoteTag()+"");
			map.put("saleTips", param.getSaleTips());
			map.put("spec", param.getSpec());
			map.put("specialContent", param.getSpecialContent());
			map.put("storeId", param.getStoreId()+"");
			map.put("topicImg", param.getTopicImg());
			String json = HttpClientUtils.doPost(host + "/api/dish/update.do",map);
			
			//String json2 = HttpClientUtils.dop
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#dish#updateDish | Error | dishId: {}, message: {}", param.getDishId(), e.getMessage(), e);
		}
		return null;
	}
}
