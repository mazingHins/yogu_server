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
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.order.StoreCreateOrderVO;
import com.yogu.services.store.order.StoreSettleOrderVO;

/**
 * 对门店操作的远程服务类-该类只用于下单流程
 *
 * @date 2016年10月1日 下午2:33:22
 * @author hins
 */
@Named
public class StoreOrderRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(StoreOrderRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;
	
	/**
	 * 调用store域的预下单接口，获取餐厅信息，美食信息，库存信息，配送费信息
	 * 
	 * @param deliveryTime - 预计送达时间
	 * @param purchaseDetail - 购买信息
	 * @param distance - 收货地址到商家的直径距离
	 * @param lat - 收货地址经度
	 * @param lng - 收货地址纬度
	 * @author hins
	 * @date 2016年10月1日 下午2:35:36
	 * @return void
	 */
	@Deprecated
	public StoreSettleOrderVO settleOrder(long deliveryTime, String purchaseDetail, int distance, double lat, double lng) {
		
		try {

			Map<String, String> params = new HashMap<>(8);
			params.put("deliveryTime", String.valueOf(deliveryTime));
			params.put("purchaseDetail", purchaseDetail);
			params.put("distance", String.valueOf(distance));
			params.put("lat", String.valueOf(lat));
			params.put("lng", String.valueOf(lng));

			String json = HttpClientUtils.doPost(host + "/api/v1/store/order/settle.do", params);
			logger.info("remote#store#settleOrder | response |  deliveryTime: {}, purchaseDetail: {}, distance: {}, lat: {}, lng: {}", deliveryTime, purchaseDetail, distance, lat, lng);
			RestResult<StoreSettleOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreSettleOrderVO>>() {});
			if (result.getCode() != ResultCode.SUCCESS){
				logger.error("remote#store#settleOrder | 不允许正常结算 |  deliveryTime: {}, purchaseDetail: {}, distance: {}, lat: {}, lng: {}", deliveryTime, purchaseDetail, distance, lat, lng);
				throw new ServiceException(result.getCode(), result.getMessage());
			}
			return result.getObject();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 调用store域的下单接口，获取餐厅、美食、配送费（包括顺丰送）、锁住库存<br>
	 * 只有service>0，才扣除库存。如果出现异常直接抛出
	 * 
	 * @param deliveryTime - 预计送达时间
	 * @param purchaseDetail - 购买信息
	 * @param distance - 收货地址到商家的直径距离
	 * @param lat - 收货地址经度
	 * @param lng - 收货地址纬度
	 * @author hins
	 * @date 2016年10月2日 下午12:47:38
	 * @return StoreCreateOrderVO
	 */
	@Deprecated
	public StoreCreateOrderVO createOrder(long deliveryTime, String purchaseDetail, int distance, double lat, double lng) {
		
		try {

			Map<String, String> params = new HashMap<>(8);
			params.put("deliveryTime", String.valueOf(deliveryTime));
			params.put("purchaseDetail", purchaseDetail);
			params.put("distance", String.valueOf(distance));
			params.put("lat", String.valueOf(lat));
			params.put("lng", String.valueOf(lng));

			String json = HttpClientUtils.doPost(host + "/api/v1/store/order/create.do", params);
			logger.debug("remote#store#createOrder | response |  deliveryTime: {}, purchaseDetail: {}, distance: {}, lat: {}, lng: {}", deliveryTime, purchaseDetail, distance, lat, lng);
			RestResult<StoreCreateOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreCreateOrderVO>>() {
			});
			if (result.getCode() != ResultCode.SUCCESS){
				logger.error("remote#store#createOrder | 不允许正常下单 |  deliveryTime: {}, purchaseDetail: {}, distance: {}, lat: {}, lng: {}", deliveryTime, purchaseDetail, distance, lat, lng);
				throw new ServiceException(result.getCode(), result.getMessage());
			}
			return result.getObject();
		} catch (Exception e) {
			throw e;
		}
	}
	
}
