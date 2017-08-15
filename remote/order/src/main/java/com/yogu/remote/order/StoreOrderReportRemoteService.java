package com.yogu.remote.order;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.cache.annotation.Cacher;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.vo.StoreMazingPayReportVO;
import com.yogu.remote.order.vo.StoreOrderStatisticsVO;
import com.yogu.remote.order.vo.StoreOrderVO;
import com.yogu.remote.order.vo.StoreReportDetailVO;
import com.yogu.remote.order.vo.StoreSaleVO;

/**
 * 通过远程接口获取餐厅订单报表的数据
 * 
 * @author Hins
 * @date 2015年12月16日 下午4:10:47
 */
@Named
public class StoreOrderReportRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(StoreOrderReportRemoteService.class);

	/**
	 * 查询餐厅指定日期的统计报表数据<br>
	 * 返回：返回：若当天没数据，全部返回默认值=0的结果；否则返回进行订单/取消/已完成的订单数和金额。<br>
	 * 返回的数据可能存在0；返回的异常：ServiceException
	 * 
	 * @author Hins
	 * @date 2015年12月16日 下午4:16:11
	 * 
	 * @param storeId - 餐厅ID
	 * @param day - 指定日期，格式yyyyMMdd
	 * @return 报表数据，请求失败，抛出异常。不会返回null
	 */
	public StoreReportDetailVO getStoreReportDetail(String storeId, String day) {
		logger.info("remote#order#getStoreReportDetail | 查询餐厅指定日期的统计报表数据 start | storeId: {}, day: {}", storeId, day);
		Map<String, String> params = new HashMap<>(2);
		params.put("storeId", storeId);
		params.put("time", day);
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/order/report/detail", params);
			RestResult<StoreReportDetailVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreReportDetailVO>>() {
			});
			return result.getObject() == null ? new StoreReportDetailVO() : result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getStoreReportDetail | 查询餐厅指定日期的统计报表数据异常 | storeId: {}, day: {}", storeId, day, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	
	/**
	 * 查询餐厅指定日期的米星付统计报表数据<br>
	 * 返回：返回：若当天没数据，全部返回默认值=0的结果；否则返回退款/已完成的订单数和金额。<br>
	 * 返回的数据可能存在0；返回的异常：ServiceException
	 * 
	 * @param storeId - 餐厅ID
	 * @param day - 指定日期，格式yyyyMMdd
	 * @author hins
	 * @date 2016年8月22日 下午4:35:15
	 * @return 报表数据，请求失败，抛出异常。不会返回null
	 */
	public StoreMazingPayReportVO getStoreMazingPayReportDetail(String storeId, String day) {
		logger.info("remote#order#getStoreMazingPayReportDetail | 查询餐厅指定日期的米星付统计报表数据 start | storeId: {}, day: {}", storeId, day);
		Map<String, String> params = new HashMap<>(2);
		params.put("storeId", storeId);
		params.put("time", day);
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/mazingPay/report/detail", params);
			RestResult<StoreMazingPayReportVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreMazingPayReportVO>>() {
			});
			return result.getObject() == null ? new StoreMazingPayReportVO() : result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getStoreMazingPayReportDetail | 查询餐厅指定日期的米星付统计报表数据异常 | storeId: {}, day: {}", storeId, day, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取餐厅订单报表数据<br>
	 * 返回：7天内的图表报表（x、y轴所需数据），7天内的总订单，日均订单，单日最高，2个月内的（汇总和明细）
	 * 
	 * @author Hins
	 * @date 2015年12月18日 上午10:13:53
	 * 
	 * @param storeId - 餐厅ID
	 * @param start - 统计开始时间
	 * @param end - 统计结束时间
	 * @return 报表数据，获取失败，抛出异常
	 */
	public StoreOrderVO getStoreOrderFlow(String storeId, int start, int end) {
		logger.info("remote#order#getStoreOrderFlow | 获取餐厅订单报表数据 start | storeId: {}", storeId);
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId);
		params.put("start", String.valueOf(start));
		params.put("end", String.valueOf(end));
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/order/flow", params);
			RestResult<StoreOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreOrderVO>>() {
			});
			return result.getObject() == null ? new StoreOrderVO() : result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getStoreOrderFlow | 获取餐厅订单报表数据异常 | storeId: {}, day: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 获取餐厅米星付订单报表数据<br>
	 * 返回：7天内的图表报表（x、y轴所需数据），7天内的总订单，日均订单，单日最高，2个月内的（汇总和明细）
	 * 
	 * @param storeId - 餐厅id
	 * @param start - 统计开始时间
	 * @param end - 统计结束时间
	 * @author hins
	 * @date 2016年8月9日 下午4:07:44
	 * @return 报表数据，获取失败，抛出异常
	 */
	public StoreOrderVO getStoreMazingPayOrderFlow(String storeId, int start, int end) {
		logger.info("remote#order#getStoreMazingPayOrderFlow | 获取餐厅米星付订单报表数据 start | storeId: {}", storeId);
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId);
		params.put("start", String.valueOf(start));
		params.put("end", String.valueOf(end));
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/order/mazingPayFlow", params);
			RestResult<StoreOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreOrderVO>>() {
			});
			return result.getObject() == null ? new StoreOrderVO() : result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getStoreMazingPayOrderFlow| 获取餐厅米星付订单报表数据异常 | storeId: {}, day: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	

	/**
	 * 获取餐厅订单营业数据<br>
	 * 返回：7天内的图表报表（x、y轴所需数据），7天内的营业额，日均营业额，单日最高营业额，2个月内的（汇总和明细）
	 * 
	 * @author Hins
	 * @date 2015年12月18日 上午10:16:46
	 * 
	 * @param storeId - 餐厅ID
	 * @param start - 统计开始时间
	 * @param end - 统计结束时间
	 * @return 报表数据，获取失败，抛出异常
	 */
	public StoreSaleVO getStoreSaleFlow(String storeId, int start, int end) {
		logger.info("remote#order#getStoreSaleFlow | 获取餐厅订单营业数据 start | storeId: {}", storeId);
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId);
		params.put("start", String.valueOf(start));
		params.put("end", String.valueOf(end));
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/sale/flow", params);
			RestResult<StoreSaleVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreSaleVO>>() {
			});
			return result.getObject() == null ? new StoreSaleVO() : result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getStoreSaleFlow | 获取餐厅订单营业数据异常 | storeId: {}, day: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 获取餐厅米星付订单营业数据<br>
	 * 返回：7天内的图表报表（x、y轴所需数据），7天内的营业额，日均营业额，单日最高营业额，2个月内的（汇总和明细）
	 * 
	 * @param storeId - 餐厅ID
	 * @param start - 统计开始时间
	 * @param end - 统计结束时间
	 * @author hins
	 * @date 2016年8月9日 下午4:11:34
	 * @return 报表数据，获取失败，抛出异常
	 */
	public StoreSaleVO getStoreMazingPaySaleFlow(String storeId, int start, int end) {
		logger.info("remote#order#getStoreMazingPaySaleFlow | 获取餐厅米星付订单营业数据 start | storeId: {}", storeId);
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId);
		params.put("start", String.valueOf(start));
		params.put("end", String.valueOf(end));
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/sale/mazingPayFlow", params);
			RestResult<StoreSaleVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreSaleVO>>() {
			});
			return result.getObject() == null ? new StoreSaleVO() : result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getStoreMazingPaySaleFlow | 获取餐厅米星付订单营业数据异常 | storeId: {}, day: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 获取商家在指定天数内的米星付订单列表
	 * 
	 * @param storeId - 订单id
	 * @param day - 日期，格式yyyyMMdd
	 * @author hins
	 * @date 2016年9月27日 下午6:15:39
	 * @return 订单列表，若无，返回empty list
	 */
//	@Cacher(value = "STORE_ORDER_REPORT_", time = 60 * 60)
	public StoreOrderStatisticsVO listOnlineOrder(long storeId, int day) {
		logger.info("store#order#listOnlineOrder | 获取指定商家在一天内的线上订单报表start  | storeId: {}, day: {}", storeId, day);

		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", String.valueOf(storeId));
		params.put("day", String.valueOf(day));

		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/order/onlineList", 5000, params);
			RestResult<StoreOrderStatisticsVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreOrderStatisticsVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#listOnlineOrder | 获取指定商家在一天内的线上订单报表数据异常 | storeId: {}, day: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}

	}

	/**
	 * 获取商家在指定天数内的线上订单列表
	 * 
	 * @param storeId - 订单id
	 * @param day - 日期，格式yyyyMMdd
	 * @author hins
	 * @date 2016年9月27日 下午6:16:20
	 * @return 订单列表，若无，返回empty list
	 */
//	@Cacher(value = "STORE_MAZING_REPORT_", time = 60 * 60)
	public StoreOrderStatisticsVO listMazingOrder(long storeId, int day) {
		logger.info("store#order#listMazingOrder | 获取指定商家在一天内的米星付订单报表start  | storeId: {}, day: {}", storeId, day);
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", String.valueOf(storeId));
		params.put("day", String.valueOf(day));

		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/statistics/order/mazingPayList",5000, params);
			RestResult<StoreOrderStatisticsVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreOrderStatisticsVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#listMazingOrder | 获取指定商家在一天内的米星付订单报表数据异常 | storeId: {}, day: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

}
