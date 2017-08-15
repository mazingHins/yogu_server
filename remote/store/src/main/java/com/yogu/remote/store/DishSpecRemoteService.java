package com.yogu.remote.store;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.dish.dto.DishSpecSnapshot;
import com.yogu.services.store.dish.dto.DishSpecVO;

/**
 * 菜品规格的远程操作类
 * 
 * @author felix
 * @date 2016-02-23
 */
@Named
public class DishSpecRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(DishSpecRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	/**
	 * 根据规格key获取规格信息
	 * 
	 * @param specKeys 规格key, 多个用英文逗号分隔
	 * @return
	 * @author felix
	 */
	public List<DishSpecVO> getSpecByKeys(String specKeys) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/spec/getByKeys?specKeys=" + specKeys);
			RestResult<List<DishSpecVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<DishSpecVO>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSpec#getSpecByKey | Error | specKeys: {}, message: {}", specKeys, e.getMessage(), e);
		}
		return Collections.<DishSpecVO> emptyList();
	}

	/**
	 * 根据菜品规格specId 获取菜品规格快照信息
	 * 
	 * @param specId
	 * @return
	 * @author sky 2016-02-25
	 */
	public DishSpecSnapshot getSpecSnapshotById(long specId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/dish/spec/snapshot/getById?specId=" + specId);
			RestResult<DishSpecSnapshot> result = JsonUtils.parseObject(json, new TypeReference<RestResult<DishSpecSnapshot>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSpec#getSpecSnapshotById | Error | specId: {}, message: {}", specId, e.getMessage(), e);
		}
		return null;
	}
}
