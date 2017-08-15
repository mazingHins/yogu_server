package com.yogu.remote.store;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.services.store.activity.vo.EventVO;

/**
 * 购票活动的远程操作服务类
 * @author east
 * @date 2017年3月3日 下午2:57:45
 */
@Named
public class EventRemoteService {
	private static final Logger logger = LoggerFactory.getLogger(EventRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;
	
	public EventVO getEventId(long eventId, String lang){
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("eventId", String.valueOf(eventId));
			params.put("lang", lang);
			String json = HttpClientUtils.doGet(host + "/api/event/get", API_DEFAULT_TIMEOUT, params);
			logger.debug("remote#ticket#getTicketRuleById | response | eventId: {}, json: {}", eventId, json);
			RestResult<EventVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<EventVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticket#getTicketRuleById | Error | eventId: {}, message: {}", eventId, e.getMessage(), e);
		}
		return null;
	}
}
