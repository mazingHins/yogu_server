/**
 * 
 */
package com.yogu.remote.store;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.base.Point;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.base.dto.StoreServiceRange;

/**
 * 门店服务配置信息 远程服务类 <br>
 * 
 * @author JFan 2015年7月20日 下午4:29:22
 */
@Named
public class StoreServiceRangeRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(StoreRemoteService.class);

	private static final String host = CommonConstants.STORE_DOMAIN;

	/**
	 * 获取门店下全部的‘服务配置’信息
	 */
	public List<StoreServiceRange> getAllServiceRange(long storeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/service/allRange?storeId=" + storeId);
			logger.debug("remote#storeService#getAll | response | storeId: {}, json: {}", storeId, json);
			RestResult<List<StoreServiceRange>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<StoreServiceRange>>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeService#getAll | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取最适合用户“配送目的地”的“服务配置”信息<br>
	 * 获取不到时，表示用户不在服务区<br>
	 * 方法不推荐使用（因为没有返回顺丰配送的信息）
	 * 
	 * @param storeId 门店ID
	 * @param lng 目的地的坐标
	 * @param lat 目的地的坐标
	 * @param distance 地点离门店的距离（米）
	 */
	@Deprecated
	public StoreServiceRange proximate(long storeId, double lng, double lat, Integer distance) {
		try {
			StringBuffer url = new StringBuffer(host);
			url.append("/api/store/service/effective?storeId=").append(storeId).append("&lng=").append(lng).append("&lat=").append(lat);
			if (null != distance)
				url.append("&distance=").append(distance);
			String json = HttpClientUtils.doGet(url.toString());

			logger.debug("remote#storeService#proximate | response | storeId: {}, lng: {}, lat: {}, distance: {}, json: {}", storeId, lng, lat, distance, json);
			RestResult<StoreServiceRange> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreServiceRange>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeService#proximate | Error | storeId: {}, lng: {}, lat: {}, distance: {}, message: {}", storeId, lng, lat, distance,
					e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据ID读取配送信息对象
	 * 
	 * @param rangeId 配送ID
	 */
	public StoreServiceRange getRange(long rangeId) {
		try {
			// HttpStoreProviderImpl.getRangeStoreId() 也有call此URL
			String json = HttpClientUtils.doGet(host + "/api/store/service/get?rangeId=" + rangeId);
			logger.debug("remote#storeService#getRange | response | rangeId: {}, json: {}", rangeId, json);
			RestResult<StoreServiceRange> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreServiceRange>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeService#getRange | Error | rangeId: {}, message: {}", rangeId, e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 修改配送信息
	 * 
	 * @param range 配送对象
	 */
	public StoreServiceRange update(StoreServiceRange range) {
		try {
			String data = JsonUtils.toJSONString(range);
			Map<String, String> params = new HashMap<>();
			params.put("data", data);
			String json = HttpClientUtils.doPost(host + "/api/store/service/update", params);
			logger.debug("remote#storeService#update | response | data: {}, json: {}", data, json);
			RestResult<StoreServiceRange> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreServiceRange>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeService#getRange | Error | data: {}, message: {}", JsonUtils.toJSONString(range), e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 根据ID读取rangeTrack对象(订单中的range信息 存在于 range_track表中,主表信息会被更新删除)
	 * 
	 * @param rangeId 配送ID
	 */
	public StoreServiceRange getRangeTrack(long rangeId) {
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/service/range/get?rangeId=" + rangeId);
			logger.debug("remote#storeService#getRange | response | rangeId: {}, json: {}", rangeId, json);
			RestResult<StoreServiceRange> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreServiceRange>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeService#getRangeTrack | Error | rangeId: {}, message: {}", rangeId, e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 验证指定坐标集合是否在门店的配送范围内。
	 * 若在，返回配送范围对象。
	 * 
	 * @author Hins
	 * @date 2015年11月20日 下午3:37:57
	 * 
	 * @param form - 坐标集合，key-下标（用户确定某一组坐标），value-坐标经纬度
	 * @param storeId - 门店ID
	 * @return 若获取失败，返回null；成功，key-请求的下标；value-配送范围对象
	 */
	public Map<Long, StoreServiceRange> verifyRange(Map<Long, Point> form, long storeId) {
		Args.notEmpty(form, "'form'");

		try {
			String url = host + "/api/store/service/range/verify.do";
			String jsonForm = JsonUtils.toJSONString(form);

			Map<String, String> reqParams = new HashMap<String, String>();
			reqParams.put("storeId", String.valueOf(storeId));
			reqParams.put("form", jsonForm);
			logger.info("remote#dishSurplus#verifyRange | request | url: '{}'", url.toString());

			String json = HttpClientUtils.doPost(url, reqParams);
			RestResult<Map<Long, StoreServiceRange>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<Map<Long, StoreServiceRange>>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#dishSurplus#verifyRange | Error | storeId: {}, form: {}, message: {}", storeId,
					JsonUtils.toJSONString(form), e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * 判断餐厅在指定的时间，是否可以进行顺丰配送<br>
	 * 如果请求失败等异常，会抛出。其余会返回boolean<br>
	 * 方法只判断指定时间是否在一天内的时间点（即没有超过10:00-21:00 初步是这个时间），不判断这个时间是否已经过了（比如前天，这一般是不合法的）
	 * 
	 * @param storeId - 餐厅id
	 * @param time - 指定时间，不能为空
	 * @author hins
	 * @date 2016年10月11日 上午10:02:23
	 * @return boolean
	 */
	public boolean verifySfExpress(long storeId, Date time){
		ParameterUtil.assertNotNull(time, "配送时间不能为空");
		try {
			String json = HttpClientUtils.doGet(host + "/api/store/service/range/verifySfExpress?storeId=" + storeId + "&time=" + time.getTime());
			logger.info("remote#storeService#verifySfExpress | response | storeId: {}, json: {}", storeId, json);
			RestResult<Boolean> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Boolean>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#storeService#verifySfExpress | Error | storeId: {}, message: {}", storeId, e.getMessage(), e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, "请求失败，请重新提交");
		}
		
	}

}
