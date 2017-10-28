package com.yogu.remote.order;

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
	 * 新单列表，对应 [商家后台] 的新单列表，后台管理使用
	 * 
	 * @param storeId 门店ID
	 * @param pickType 配送方式
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回新单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ten 2015/9/26
	 */
	public RestResult<List<Map<String, Object>>> adminGetNewOrders(long storeId, int pageIndex, int pageSize) {
		Map<String, String> params = new HashMap<>(4);
		params.put("storeId", storeId + "");
		params.put("pageIndex", pageIndex + "");
		params.put("pageSize", pageSize + "");
		try {
			String json = HttpClientUtils.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/newOrders", API_DEFAULT_TIMEOUT, params);
			RestResult<List<Map<String, Object>>> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<List<Map<String, Object>>>>() {
					});
			return result;
		} catch (Exception e) {
			logger.error("remote#order#adminGetNewOrders | 管理员获取新单列表错误 | storeId: {}", storeId, e);
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
