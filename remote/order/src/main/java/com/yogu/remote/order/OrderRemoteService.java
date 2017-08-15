package com.yogu.remote.order;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.order.vo.OrderTrack;
import com.yogu.remote.order.vo.RemoteOrderVO;
import com.yogu.remote.order.vo.StoreReportVO;

/**
 * 通过远程接口获取订单的数据
 * 
 * @author ten 2015/9/1.
 */
@Named
public class OrderRemoteService {

	private static final Logger logger = LoggerFactory.getLogger(OrderRemoteService.class);

	// 访问接口 timeout 时间（ms）
	private static final int API_DEFAULT_TIMEOUT = 5000;

	/**
	 * 计算门店的评分星级(定时任务调度)
	 * 
	 * @param storeIds 门店id数组
	 * @return 返回 key=storeId, value=star 的 键值对容器, star为门店星级评分
	 * @author sky
	 */
	public Map<Long, Short> caculateStoreStar(long... storeIds) {

		try {
			String sids = StringUtils.join(storeIds, ',');
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/v1/order/caculateStoreStar?storeIds="
					+ sids);

			logger.debug("remote#order#caculateStoreStar | response | storeIds: {}, json: {}", sids, json);

			RestResult<Map<Long, Short>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Short>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#caculateStoreStar | Error | storeIds: {}, message: {}", storeIds, e.getMessage(), e);
		}

		return Collections.emptyMap();
	}

	/**
	 * 计算门店的评论总数(定时任务调度)
	 * 
	 * @param storeIds 门店id数组
	 * @return 返回 key=storeId, value=count 的 键值对容器, count为门店评论总数
	 * @author sky
	 * @date 2015-11-04
	 */
	public Map<Long, Integer> caculateStoreCommentCount(long... storeIds) {

		try {
			String sids = StringUtils.join(storeIds, ',');
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/v1/order/caculateStoreCommentCount?storeIds="
					+ sids);

			logger.debug("remote#order#caculateStoreCommentCount | response | storeIds: {}, json: {}", sids, json);

			RestResult<Map<Long, Integer>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<Long, Integer>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#caculateStoreCommentCount | Error | storeIds: {}, message: {}", storeIds, e.getMessage(), e);
		}

		return Collections.emptyMap();
	}

	/**
	 * 新单列表，对应 [商家后台] 的新单列表，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pickType 配送方式
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/26
	 */
	public RestResult<List<Map<String, Object>>> adminGetNewOrders(long storeId, int pickType, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pickType", pickType + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/newOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetNewOrders | 管理员获取新单列表错误 | storeId: {}, pickType: {}", storeId, pickType, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 制作(已接单状态)中的订单，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pickType 配送方式
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/26
	 */
	public RestResult<List<Map<String, Object>>> adminGetOnCookingOrders(long storeId, int pickType, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pickType", pickType + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/onCookingOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetOnCookingOrders | 管理员获取制作中的订单列表错误 | storeId: {}, pickType: {}", storeId, pickType, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 制作完成（待配送）的订单，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pickType 配送方式
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/28
	 */
	public RestResult<List<Map<String, Object>>> adminGetFinishCookingOrders(long storeId, int pickType, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pickType", pickType + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/finishCookingOrders", API_DEFAULT_TIMEOUT,
					params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetFinishCookingOrders | 管理员获取制作完成（待配送）的订单列表错误 | storeId: {}, pickType: {}", storeId, pickType,
					e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 配送中的订单，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pickType 配送方式
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/28
	 */
	public RestResult<List<Map<String, Object>>> adminGetOnDeliveryOrders(long storeId, int pickType, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pickType", pickType + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/onDeliveryOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetOnDeliveryOrders | 管理员获取配送中的订单列表错误 | storeId: {}, pickType: {}", storeId, pickType, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 退款中的订单，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/28
	 */
	public RestResult<List<Map<String, Object>>> adminGetRefundOrders(long storeId, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/refundOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetRefundOrders | 管理员获取退款中的订单列表错误 | storeId: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 读取已完成的订单，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/28
	 */
	public RestResult<List<Map<String, Object>>> adminGetFinishedOrders(long storeId, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/finishedOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetFinishedOrders | 管理员获取已完成的订单列表错误 | storeId: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 读取订单统计数据, 后台使用
	 * @param type 数据时间段 1 - 7天(周)数据, 2 - 30天(月)数据
	 * @return
	 */
	public RestResult<List<Map<String, Object>>> adminGetOrderStatistics(short type) {
		Map<String, String> params = new HashMap<>(2);
		params.put("type", type + "");
		
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/statistics/general", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetFinishedOrders | 管理员获取订单统计错误 | type: {}", type, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 账款已入商家账户的订单，即状态为［买家确认收货］和［已评论］的订单，后台管理使用
	 *
	 * @author ben
	 * @date 2015年12月7日 下午7:10:04 
	 * @param storeId 门店ID
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回已完成的订单数据，不为null，如果没有数据，返回size=0的数据
	 */
	public RestResult<List<Map<String, Object>>> adminGetPaidOrders(long storeId, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/paidOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetPaidOrders | 管理员获取已入账的订单列表错误 | storeId: {}", storeId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 查询符合条件的所有订单。后台管理使用<br>
	 * 
	 * 该方法只查询非米星付的订单，米星付和其他的订单分开查询和展示 2016/7/13 add by hins
	 * 
	 * @param uid 查询哪个用户的订单，可以为 0
	 * @param orderNo 订单编号，可以为0，优先级最高
	 * @param storeId 餐厅ID，和uid可以同时起作用
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回订单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ben 2015-11-25
	 */
	public RestResult<List<Map<String, Object>>> adminQueryOrders(long uid,
																  long orderNo,
																  long storeId,
																  int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("orderNo", orderNo + "");
		params.put("uid", uid + "");
		params.put("storeId", storeId + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/queryOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminQueryOrders | 管理员获取所有订单数据错误 | orderNo: {}", orderNo, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 查询符合条件的所有订单。后台管理使用<br>
	 * 该方法只查询米星付的订单，米星付和其他的订单分开查询和展示 2016/7/13 add by hins
	 * 
	 * @param uid 查询哪个用户的订单，可以为 0
	 * @param orderNo 订单编号，可以为0，优先级最高
	 * @param storeId 餐厅ID，和uid可以同时起作用
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回订单数据，不为null，如果没有数据，返回size=0的数据
	 * @author hins
	 * @date 2016年7月13日 下午6:49:55
	 * @return RestResult<List<Map<String,Object>>>
	 */
	public RestResult<List<Map<String, Object>>> adminQueryMazingPayOrders(long uid, long orderNo, long storeId, int pageIndex, int pageSize) {
		logger.info("remote#order#adminQueryMazingPayOrders | 管理员获取所有米星付订单start | uid: {}, orderNo: {}, storeId: {}, pageIndex: {}, pageSize: {}", uid, orderNo, storeId, pageIndex, pageSize);
		Map<String, String> params = new HashMap<>(4);
		params.put("orderNo", orderNo + "");
		params.put("uid", uid + "");
		params.put("storeId", storeId + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/queryMazingPayOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminQueryMazingPayOrders | 管理员获取所有米星付订单数据错误 | orderNo: {}", orderNo, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 
	 * 说明 
	 *
	 * @author ben
	 * @date 2015年11月23日 下午2:49:38 
	 * @param orderNo 订单编号
	 * @param pageIndex  第几页
	 * @param pageSize  每页大小
	 * @return 返回订单轨迹数据，不为null，如果没有数据，返回size=0的数据
	 */
	public List<OrderTrack> adminGetOrderTrack(String orderNo, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("orderNo", orderNo);
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/allOrderTrack", API_DEFAULT_TIMEOUT, params);

			RestResult<List<OrderTrack>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<OrderTrack>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#adminGetOrderTrack | 管理员获取订单轨迹列表错误 | orderNo: {}", orderNo, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
		
	}
	

	/**
	 * 返回订单的详细信息，包括：基本信息、订单轨迹。<br/>
	 * 成功返回相应的详细信息，失败抛出 ServiceException
	 * <strong>注：后台管理系统使用</strong>，用于展示订单的信息。
	 * <pre>
	 * {
	 *     order: 订单基本信息,
	 *     orderTrackList: 员工列表,
	 * }
	 * </pre>
	 * @param orderNo 订单号
	 * @return 成功返回相应的详细信息，失败抛出 ServiceException
	 */
	public Map<String, Object> adminGetOrderDetail(long orderNo) {
		Map<String, String> params = new HashMap<>(2);
		String strOrderNo = String.valueOf(orderNo);
//		if (strOrderNo.length() < 16) {
//			// TODO 临时处理一下，兼容两个版本，在优惠券版本可以修改回来
//			params.put("orderId", strOrderNo);
//		}
//		else {
////			params.put("orderNo", strOrderNo);
//			RestResult<List<Map<String, Object>>> tmpResult = adminQueryOrders(0L, orderNo, 0L, 1, 20);
//			if (tmpResult.isSuccess() && tmpResult.getObject().size() > 0) {
//				params.put("orderId", String.valueOf(tmpResult.getObject().get(0).get("orderId")));
//			}
//			else {
//				throw new ServiceException(tmpResult.getCode(), tmpResult.getMessage());
//			}
//		}
		try {
			params.put("orderNo", strOrderNo);
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/detail", API_DEFAULT_TIMEOUT, params);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});
			if (result.isSuccess())
				return result.getObject();
			throw new ServiceException(result.getCode(), result.getMessage());
		} catch (Exception e) {
			logger.error("remote#store#adminGetOrderDetail | 查询订单详细数据错误 | orderNo: {}", orderNo, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}

	/**
	 * 管理员取消用户的订单
	 * @param adminId 管理员ID
	 * @param orderNo 订单号
	 * @param remark 取消原因
	 * @return result.success=true为成功
	 * @author ten 2015/12/16
	 */
	public RestResult<Object> adminCancelOrder(long adminId, long orderNo, String remark) {
		Map<String, String> params = new HashMap<>(4);
		params.put("uid", adminId + "");
		params.put("orderNo", orderNo + "");
		params.put("remark", remark);
		try {
			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/order/cancel.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminCancelOrder | 管理员取消订单错误 | adminId: {}, orderNo: {}, remark: {}",
					adminId, orderNo, remark, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 管理员取消订单的第三方配送
	 * 
	 * @param adminId - 管理员ID
	 * @param orderNo - 订单号
	 * @param remark - 取消原因
	 * @author hins
	 * @date 2016年10月16日 下午2:22:18
	 * @return RestResult<Object>
	 */
	public RestResult<Object> adminCancelSfExpress(long adminId, long orderNo, String remark) {
		Map<String, String> params = new HashMap<>(4);
		params.put("uid", adminId + "");
		params.put("orderNo", orderNo + "");
		params.put("remark", remark);
		try {
			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/order/cancelExpress.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#cancelExpress | 管理员取消第三方配送错误 | adminId: {}, orderNo: {}, remark: {}", adminId, orderNo, remark, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 根据订单编号获取订单信息 
	 * @param orderNo 订单编号
	 * @return
	 */
	public RemoteOrderVO getByOrderNo(long orderNo) {
		Map<String, String> params = new HashMap<>(2);
		params.put("orderNo", orderNo + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/v1/order/getOrderByNo", API_DEFAULT_TIMEOUT, params);
			RestResult<RemoteOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<RemoteOrderVO>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#getByOrderNo | 获取订单信息错误 | orderNo: {}", orderNo, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 根据订单ID获取订单评论信息
	 * @param orderId 订单ID
	 * @return
	 */
	public Map<String, Object> getCommentByOrderId(long orderId) {
		Map<String, String> params = new HashMap<>(2);
		params.put("orderId", orderId + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/v1/order/comment/getByOrderId", API_DEFAULT_TIMEOUT, params);
			RestResult<Map<String, Object>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Map<String, Object>>>() {
			});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#order#comment#getCommentByOrderId | 获取订单评论信息错误 | orderId: {}", orderId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 导出餐厅订单报表, 访问storeapi返回excel的封装类StoreReportVO
	 * @param storeId
	 * @param startTime
	 * @param endTime
	 * @param payTypes
	 * @param isShowPhone是否显示用户帐号,后台管理导出报表显示，B端不显示
	 * @return StoreReportVO
	 * @author east
	 * @date 2016年11月24日
	 */
	public StoreReportVO queryOrderExcel(long storeId, String startTime, String endTime, String payTypes, boolean isShowPhone){
		Map<String, String> params = new HashMap<String, String>(6);
		params.put("storeId", storeId + "");
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		params.put("payTypes", payTypes);
		params.put("isShowPhone", String.valueOf(isShowPhone));
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/queryOrderExcel", 5000, params);
			RestResult<StoreReportVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<StoreReportVO>>() {
			});
			return result.getObject();
		} catch (RuntimeException e) {
			logger.error("remote#order#queryOrderExcel | 导出报表连接超时 | storeId: {}, startTime: {}, endTime: {}", storeId, startTime, endTime, e);
			throw new ServiceException(ResultCode.FAILURE, "报表数据量过大，请缩短日期区间后再试！");
		} catch (Exception e) {
			logger.error("remote#order#queryOrderExcel | 导出报表错误 | storeId: {}, startTime: {}, endTime: {}", storeId, startTime, endTime, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, "导出报表失败，请重试！");
		}
	}
	
	/**
	 * 根据餐厅ID获取每日餐厅营业前需要处理的订单
	 * 
	 * @param storeIds 门店ID列表, 多个用英文逗号分隔
	 * @return
	 */
	public RestResult<List<Map<String, Object>>> storeOrderDailyReport(String storeIds) {
		Map<String, String> params = new HashMap<>(2);
		params.put("storeIds", storeIds);
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/statistics/dailyReport", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#storeOrderDailyReport | 获取餐厅每日运营前信息失败 | storeIds: {}", storeIds, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 管理员查询订单的评论列表结果<br>
	 * 方法支持分页
	 * 
	 * @param uid - 下单人id
	 * @param orderNo - 订单编号，可以为0，优先级最高
	 * @param star 评论星级，可以为0（为0表示无星级查询条件），否则只查询星级=star的
	 * @param beginTime - 评论创建时间（查询的开始时间）
	 * @param endTime - 评论创建时间（查询的结束时间）
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @author hins
	 * @date 2016年7月12日 下午6:37:50
	 * @return 评论列表结果
	 */
	public RestResult<List<Map<String, Object>>> adminQueryComments(long uid, long orderNo, short star, long beginTime, long endTime, int pageIndex,
			int pageSize) {
		
		logger.info("order#remote#adminQueryComments | star | orderNo: {}, star: {}, beginTime: {}, endTime: {}, pageIndex: {}, pageSize: {}", orderNo, star,
				beginTime, endTime, pageIndex, pageSize);
		
		Map<String, String> params = new HashMap<>(8);
		params.put("uid", String.valueOf(uid));
		params.put("orderNo", String.valueOf(orderNo));
		params.put("star", String.valueOf(star));
		params.put("beginTime", String.valueOf(beginTime));
		params.put("endTime", String.valueOf(endTime));
		params.put("pageIndex", String.valueOf(pageIndex));
		params.put("pageSize", String.valueOf(pageSize));

		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/comment/query", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, Object>>>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("order#remote#adminQueryComments | 查询失败 | orderNo: {}, star: {}, beginTime: {}, endTime: {}, pageIndex: {}, pageSize: {}", orderNo, star,
					beginTime, endTime, pageIndex, pageSize, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}

	}
	
	/**
	 * 管理员删除订单评论<br>
	 * 软删除
	 * 
	 * @param adminId - 操作人id
	 * @param commentId - 评论id
	 * @author hins
	 * @date 2016年7月13日 上午10:34:26
	 * @return RestResult<Object>
	 */
	public RestResult<Object> adminDeleteComment(long adminId, long orderNo, long commentId) {
		logger.info("remote#store#adminDeleteComment | 管理员删除订单评论start | adminId: {}, commentId: {}", adminId, commentId);
		Map<String, String> params = new HashMap<>(4);
		params.put("uid", adminId + "");
		params.put("orderNo", orderNo + "");
		params.put("commentId", commentId + "");
		try {
			String json = HttpClientUtils.doPost(CommonConstants.ORDER_DOMAIN + "/api/order/comment/delete.do", API_DEFAULT_TIMEOUT, params);
			RestResult<Object> result = JsonUtils.parseObject(json, new TypeReference<RestResult<Object>>() {
			});
			return result;
		} catch (Exception e) {
			logger.error("remote#store#adminDeleteComment | 管理员删除订单评论错误 | adminId: {}, commentId: {}", adminId, commentId, e);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, e.getMessage());
		}
	}
	
	
}
