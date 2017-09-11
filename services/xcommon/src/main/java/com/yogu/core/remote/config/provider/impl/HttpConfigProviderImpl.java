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



}
