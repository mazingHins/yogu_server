package com.yogu.core.remote.config.provider.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Priority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.remote.config.Area;
import com.yogu.core.remote.config.Config;
import com.yogu.core.remote.config.CustomizePoolVO;
import com.yogu.core.remote.config.FilterTagCategoryVO;
import com.yogu.core.remote.config.StoreCategoryVO;
import com.yogu.core.remote.config.WhiteList;
import com.yogu.core.remote.config.provider.ConfigProvider;
import com.yogu.core.web.RestResult;

/**
 * 通过HTTP形式获取配置数据 <br>
 * <br>
 * 优先级为20（config域有个10的优先级）
 *
 * @author JFan 2015年10月22日 下午3:58:23
 */
@Priority(20)
public class HttpConfigProviderImpl implements ConfigProvider {

	private static final Logger logger = LoggerFactory.getLogger(HttpConfigProviderImpl.class);

	private final List<Config> emptyConfig = new ArrayList<Config>();
	private final String host = CommonConstants.USER_DOMAIN;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Config> getAllConfig() throws Exception {
		String json = HttpClientUtils.doGet(host + "/api/base/allConfigs");
		logger.info("configProvider#allConfig | http response | result: {}", json);
		RestResult<List<Config>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Config>>>() {
		});

		if (!(result.isSuccess())) {
			logger.warn("httpConfigProvider#getAllConfig#Failure | Failure Request | result: {}", json);
			return emptyConfig;
		}

		List<Config> list = result.getObject();
		if (null != list)
			return list;
		else
			return emptyConfig;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Area getArea(String code) throws Exception {
		String json = HttpClientUtils.doGet(host + "/api/area/get?code=" + code);
		logger.info("configProvider#area | http response | code: {}, result: {}", code, json);
		RestResult<Area> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Area>>() {
		});

		if (!(result.isSuccess())) {
			logger.warn("configProvider#area#Failure | Failure Request | code: {}, result: {}", code, json);
			return null;
		}

		return result.getObject();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Area> listAllCity() {
		String json = HttpClientUtils.doGet(host + "/api/area/listAllCity?level=3");
		logger.info("configProvider#listAllCity | http response | result: {}", json);
		RestResult<List<Area>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Area>>>() {
		});

		if (result.isSuccess() && null != result.getObject())
			return result.getObject();
		return Collections.emptyList();
	}

	@Override
	public List<WhiteList> listAllWhiteList() {
		// 读取白名单 2016/1/14 by ten
		String json = HttpClientUtils.doGet(host + "/api/base/whitelist/listAll");
		logger.info("configProvider#listAllWhiteList | http response | result: {}", json);
		RestResult<List<WhiteList>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<WhiteList>>>() {
		});

		if (result.isSuccess() && result.getObject() != null) {
			return result.getObject();
		}
		return Collections.emptyList();
	}

	@Override
	public List<StoreCategoryVO> listAllCategoryTag() {
		// 读取门店标签信息 2016-01-19 by felix
		String json = HttpClientUtils.doGet(host + "/api/base/storeTag/listAllInitial");
		logger.info("configProvider#listAllCategoryTag | http response | result: {}", json);
		RestResult<List<StoreCategoryVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<StoreCategoryVO>>>() {
		});
		
		if (result.isSuccess() && result.getObject() != null) {
			return result.getObject();
		}
		
		return Collections.emptyList();
	}

	@Override
	public List<CustomizePoolVO> listCustomizeByCityCode(String cityCode, String lang) {
		String json = HttpClientUtils.doGet(host + "/api/v1/config/customize/list?cityCode=" + cityCode);
		logger.info("configProvider#listCustomizeByCityCode | http response | result: {}", json);
		RestResult<List<CustomizePoolVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<CustomizePoolVO>>>() {
		});

		if (result.isSuccess() && result.getObject() != null) {
			return result.getObject();
		}

		return Collections.emptyList();
	}

	@Override
	public List<FilterTagCategoryVO> listAllFilterTag() {
		return Collections.emptyList();
	}

	@Override
	public List<CustomizePoolVO> listAllEffective() {
		String json = HttpClientUtils.doGet(host + "/api/v1/config/customize/listAllEffective");
		logger.info("configProvider#listAllEffective | http response | result: {}", json);
		RestResult<List<CustomizePoolVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<CustomizePoolVO>>>() {
		});

		if (result.isSuccess() && result.getObject() != null) {
			return result.getObject();
		}

		return Collections.emptyList();
	}
}
