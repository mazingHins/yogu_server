package com.yogu.remote.store;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.activity.vo.TicketCreateOrderVO;
import com.yogu.services.store.ticket.vo.AssignTicketBO;
import com.yogu.services.store.ticket.vo.TicketRuleVO;
import com.yogu.services.store.ticket.vo.TicketVO;

/**
 * Ticket 远程服务类
 * 
 * @author sky 2017-02-27
 *
 */
@Named
public class TicketRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(TicketRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;

	/**
	 * 批量获取 订单相关的票信息
	 * 
	 * @param orderNos 订单orderNo串,英文逗号分隔
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Long, List<TicketVO>> batchOrderTickets(String orderNos) {
		if(StringUtils.isBlank(orderNos))
			return Collections.emptyMap();
		Map<String, String> params = new HashMap<>(3);
		params.put("orderNos", orderNos);

		try {
			String json = HttpClientUtils.doGet(host + "/api/ticket/batchOrderTickets", API_DEFAULT_TIMEOUT, params);
			RestResult<Map<Long, List<TicketVO>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<Map<Long, List<TicketVO>>>>() {
					});
			if(result.getObject() == null)
				return Collections.emptyMap();
			
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticket#orderTickets | 远程获取订单下的票信息失败 | orderNos: {}", orderNos, e);

			return Collections.EMPTY_MAP;
		}
	}

	/**
	 * 远程获取订单相关的票信息
	 * 
	 * @param orderNo 订单编号
	 * @return
	 * @author sky 2017-03-02
	 */
	@SuppressWarnings("unchecked")
	public List<TicketVO> orderTickets(long orderNo) {
		if(orderNo == 0)
			return Collections.emptyList();
		
		Map<String, String> params = new HashMap<>(3);
		params.put("orderNo", orderNo + "");

		try {
			String json = HttpClientUtils.doGet(host + "/api/ticket/orderTickets", API_DEFAULT_TIMEOUT, params);
			RestResult<List<TicketVO>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<TicketVO>>>() {
			});
			if(result.getObject() == null)
				return Collections.emptyList();
			
			return result.getObject();

		} catch (Exception e) {
			logger.error("remote#ticket#orderTickets | 远程获取订单下的票信息失败 | orderNo: {}", orderNo, e);

			return Collections.EMPTY_LIST;
		}
	}

	/**
	 * 消费库存操作<br>
	 * <b>下单时调用</b> 此处定义： 父库存是指 ticketRule 下的 surplus数据， 是数量库存,表现级别的<br>
	 * 子库存是指未被购买的ticket，放在redis中，是cache,真实级别的
	 * 
	 * @param buyInfo 购买信息
	 * @return
	 * @author sky 2017-03-02
	 */
	public TicketCreateOrderVO consumeTicket(AssignTicketBO buyInfo) {
		Args.notNull(buyInfo, "'buyInfo'");

		try {
			Map<String, String> params = new HashMap<>(2);
			params.put("buyInfo", JsonUtils.toJSONString(buyInfo));

			String json = HttpClientUtils.doPost(host + "/api/ticket/surplus/consumeTicket.do", params);
			RestResult<TicketCreateOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TicketCreateOrderVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticketService#consumeSurplus | 消费ticket库存错误 | bo: {}", JsonUtils.toJSONString(buyInfo), e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 释放库存操作<br>
	 * 该接口供取消未成功支付的订单/下单失败时使用, 注意： 支付成功的退款不释放库存; 扣库存失败时不能调用释放库存(扣库存逻辑自身已完成库存回滚)<br>
	 * <b>该操作将会 1：释放ticketRule父库存(数量上库存) 2:释放ticket子库存（redis数据回设）以及 回滚DB(更新用户预购买状态为 未购买) </b>
	 * 
	 * @param buyInfo
	 * @return
	 * @author sky 2017-03-02
	 */
	public boolean releaseTicket(TicketCreateOrderVO buyInfo, long orderNo) {
		Args.notNull(buyInfo, "'buyInfo'");

		try {

			Map<String, String> params = new HashMap<>(2);
			params.put("buyInfo", JsonUtils.toJSONString(buyInfo));
			params.put("orderNo", String.valueOf(orderNo));

			String json = HttpClientUtils.doPost(host + "/api/ticket/surplus/releaseTicket.do", params);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			if(result.getObject() == null)
				return false;
			
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticketService#releaseTicket | 释放ticket库存错误 | bo: {}", JsonUtils.toJSONString(buyInfo), e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 下单时的扣库存操作<br>
	 * 
	 * @param orderNo 订单编号
	 * @param storeId 餐厅id
	 * @param actId 活动id
	 * @param buyerId 购买者用户uid
	 * @param form ticket来源 , key：票ticketRuleId、value：这个ticket的数量, 如：13213<=>100,表示购买13213的ticket100张
	 * @return map, 操作成功, map.empty = true；2：操作失败, 记录（key：ticketRuleId，value：剩余数量）
	 * @author sky 2017-02-27
	 */
	@Deprecated
	public Map<Long, Integer> consumeSurplus(long orderNo, long storeId, long actId, long buyerId, Map<Long, Integer> form) {

		Args.notEmpty(form, "'form'");

		try {

			StringBuilder url = new StringBuilder(host);

			url.append("/api/ticket/surplus/consume?" + "orderNo=" + orderNo + "&storeId=" + storeId + "&uid=" + buyerId + "&actId="
					+ actId);

			for (Entry<Long, Integer> entry : form.entrySet()) {

				for (int index = 0; index < entry.getValue(); index++) {
					url.append("&ticketRuleId=").append(entry.getKey());
				}
			}

			logger.info("remote#ticketService#consumeSurplus | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {

			logger.error("remote#ticketService#consumeSurplus | Error |  form: {}, message: {}", JsonUtils.toJSONString(form),
					e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 取消订单/支付失败时的 释放库存操作<br>
	 * 
	 * 
	 * @param form ticket来源 , key：票ticketRuleId、value：这个ticket的数量, 如：13213<=>100,表示购买13213的ticket100张
	 * @return true/false=成功/失败; 数据异常时将抛出异常Exception
	 * @author sky 2017-02-27
	 */
	@Deprecated
	public Map<Long, Integer> releaseSurplus(Map<Long, Integer> form) {

		Args.notEmpty(form, "'form'");

		try {

			StringBuilder url = new StringBuilder(host);

			url.append("/api/ticket/surplus/release");

			for (Entry<Long, Integer> entry : form.entrySet()) {

				for (int index = 0; index < entry.getValue(); index++) {

					if (index == 0)
						url.append("?ticketRuleId=").append(entry.getKey());
					else
						url.append("&ticketRuleId=").append(entry.getKey());
				}
			}

			logger.info("remote#ticketService#releaseSurplus | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doGet(url.toString());
			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {

			logger.error("remote#ticketService#releaseSurplus | Error |  form: {}, message: {}", JsonUtils.toJSONString(form),
					e.getMessage(), e);
		}

		return null;
	}

	public TicketRuleVO getTicketRuleById(long ticketRuleId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/ticket/ticketRule/get?ticketRuleId=" + ticketRuleId, API_DEFAULT_TIMEOUT);
			logger.debug("remote#ticket#getTicketRuleById | response | ticketRuleId: {}, json: {}", ticketRuleId, json);
			RestResult<TicketRuleVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TicketRuleVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticket#getTicketRuleById | Error | ticketRuleId: {}, message: {}", ticketRuleId, e.getMessage(), e);
		}
		return null;
	}

	private static Map<String, String> toMapString(Object object) throws Exception {
		Map<String, String> map = new HashMap<>();

		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_mm_ss);
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = (getter != null ? getter.invoke(object) : null);
			if (value != null) {
				if (value instanceof Date) {
					map.put(key, sdf.format((Date) value));
				} else {
					map.put(key, String.valueOf(value));
				}
			}
		}

		return map;
	}

	/**
	 * 根据id集合，获取TicketRuleVO的map类型，key=ticketRuleId
	 * 
	 * @param ticketRuleIds
	 * @return
	 * @author east
	 * @date 2017年3月13日 下午5:48:45
	 */
	public Map<Long, TicketRuleVO> getTicketRuleByIds(String ticketRuleIds) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/ticket/ticketRule/getTicketRuleByIds?ticketRuleIds=" + ticketRuleIds,
					API_DEFAULT_TIMEOUT);
			logger.debug("remote#ticket#getTicketRuleById | response | ticketRuleIds: {}, json: {}", JsonUtils.toJSONString(ticketRuleIds),
					json);
			RestResult<Map<Long, TicketRuleVO>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<Map<Long, TicketRuleVO>>>() {
					});
			if (result.getObject() == null)
				return Collections.emptyMap();
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticket#getTicketRuleById | Error | ticketRuleIds: {}, message: {}", JsonUtils.toJSONString(ticketRuleIds),
					e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 根据ticketNo获取ticket
	 * @param ticketNo
	 * @return    
	 * @author east
	 * @date 2017年3月20日 下午12:56:44
	 */
	public TicketVO getTicketById(long ticketId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/ticket/get?ticketId=" + ticketId, API_DEFAULT_TIMEOUT);
			logger.debug("remote#ticket#getTicketRuleById | response | ticketId: {}, json: {}", ticketId, json);
			RestResult<TicketVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<TicketVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#ticket#getTicketRuleById | Error | ticketId: {}, message: {}", ticketId, e.getMessage(), e);
		}
		return null;
	}
}