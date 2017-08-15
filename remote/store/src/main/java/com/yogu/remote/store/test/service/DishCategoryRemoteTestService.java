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
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.store.DishRemoteService;
import com.yogu.services.store.dish.dto.Dish;
import com.yogu.services.store.dish.dto.DishCategory;

/**
 * 对菜品类别操作服务类 <br>
 * 用于简易后台修改测试数据
 * 
 * @author Felix 2015年9月3日
 */
@Named
public class DishCategoryRemoteTestService {
	private static final Logger logger = LoggerFactory.getLogger(DishCategoryRemoteTestService.class);
	
	private static final String host = CommonConstants.STORE_DOMAIN;
	
	/**
	 * 获取所有菜品类别
	 * 
	 * 
	 * @return 菜品类别列表
	 */
	public List<DishCategory> getAll(){
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/allCategories");
			RestResult<List<DishCategory>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<DishCategory>>>() {
			});
			return result.getObject() == null || result.getObject().isEmpty() ? null : result.getObject();
		} catch (Exception e) {
			logger.error("remote#dish#getAllDishCategories | Error | message: {}", e.getMessage(), e);
		}
		return null;
	}
	
	public void modifyName(long adminId, long categoryId, String name){
		try {
			Map<String, String> param = new HashMap<String, String>();
			param.put("adminId", String.valueOf(adminId));
			param.put("categoryId", String.valueOf(categoryId));
			param.put("name", name);
			
			String json = HttpClientUtils.doPost(host + "/api/store/category/modify.do", param);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			if(result.getCode() != ResultCode.SUCCESS){
				logger.error("remote#dish#modifyName | 修改失败 | code: {}, message:{}", result.getCode(), result.getMessage());
				throw new ServiceException(ResultCode.FAILURE, "修改失败");
			}
		} catch (Exception e) {
			logger.error("remote#dish#modifyName | Error | message: {}", e.getMessage(), e);
		}
	}
	
	
	
}
