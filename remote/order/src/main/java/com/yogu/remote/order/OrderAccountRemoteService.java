package com.yogu.remote.order;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.consumer.vo.ConfirmedOrderVO;
import com.yogu.core.consumer.vo.RefundOrderVO;
import com.yogu.core.consumer.vo.StoreAcceptOrderVO;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

/**
 * 通过远程接口获取订单对账相关的数据
 *
 * @date 2016年6月22日 上午10:24:05
 * @author hins
 */
@Named
public class OrderAccountRemoteService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderAccountRemoteService.class);

	/**
	 * 获取指定订单编号-商家接单后的订单相关金额（订单金额，应付金额，优惠券信息）
	 * @param orderNo - 订单编号
	 * @author hins
	 * @date 2016年6月22日 上午10:26:25
	 * @return 金额相关数据，若无/请求失败，返回null
	 */
	public StoreAcceptOrderVO acceptOrder(long orderNo) {
		try {
			logger.info("remote#orderAccountDetail#acceptOrder | acceptOrder start | orderNo: {}, message: {}", orderNo);
			
			String json = HttpClientUtils
					.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/account/acceptOrder/orderNo/" + orderNo);
			RestResult<StoreAcceptOrderVO> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<StoreAcceptOrderVO>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#orderAccountDetail#acceptOrder | Error | orderNo: {}, message: {}", orderNo,
					e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * 获取指定订单编号-确认收货后的订单相关金额（订单金额，应付金额，优惠券信息）
	 * @param orderNo - 订单编号
	 * @author hins
	 * @date 2016年6月22日 上午10:28:18
	 * @return 金额相关数据，若无/请求失败，返回null
	 */
	public ConfirmedOrderVO confirmedOrder(long orderNo) {
		try {
			logger.info("remote#orderAccountDetail#confirmedOrder | confirmedOrder start | orderNo: {}, message: {}", orderNo);
			
			String json = HttpClientUtils
					.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/account/confirmedOrder/orderNo/" + orderNo);
			RestResult<ConfirmedOrderVO> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<ConfirmedOrderVO>>() {
					});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#orderAccountDetail#confirmedOrder | Error | orderNo: {}, message: {}", orderNo,
					e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * 获取指定订单编号-米星付成功后的订单相关金额（订单金额，应付金额，优惠券信息）
	 * @param orderNo - 订单编号
	 * @author hins
	 * @date 2016年7月8日 下午2:29:12
	 * @return 金额相关数据，若无/请求失败，返回null
	 */
	public ConfirmedOrderVO mazingPaySuccessOrder(long orderNo) {
		try {
			logger.info("remote#orderAccountDetail#mazingPaySuccessOrder | mazingPaySuccessOrder start | orderNo: {}, message: {}", orderNo);
			
			String json = HttpClientUtils
					.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/account/mazingPaySuccessOrder/orderNo/" + orderNo);
			RestResult<ConfirmedOrderVO> result = JsonUtils.parseObject(json, new TypeReference<RestResult<ConfirmedOrderVO>>() {});
			if (result.getCode() != ResultCode.SUCCESS) {
				throw new ServiceException(result.getCode(), result.getMessage());
			}
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#orderAccountDetail#mazingPaySuccessOrder | Error | orderNo: {}, message: {}", orderNo,
					e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * 获取指定订单编号-申请退款后的订单相关金额（订单金额，退款金额，优惠券信息）<br>
	 * 该方法适用于交易中的订单退款的对账
	 * 
	 * @param orderNo 订单编号
	 * @author hins
	 * @date 2016年6月23日 下午3:00:48
	 * @return 金额相关数据，若无/请求失败，返回null
	 */
	public RefundOrderVO applyRefund(long orderNo) {
		try {
			logger.info("remote#orderAccountDetail#applyRefund | applyRefund start | orderNo: {}, message: {}", orderNo);
			
			String json = HttpClientUtils
					.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/account/applyRefund/orderNo/" + orderNo);
			RestResult<RefundOrderVO> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<RefundOrderVO>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#orderAccountDetail#applyRefund | Error | orderNo: {}, message: {}", orderNo,
					e.getMessage(), e);
			throw e;
		}

//		return null;
	}
	
	/**
	 * 获取指定订单编号-申请退款后的订单相关金额（订单金额，退款金额，优惠券信息）<br>
	 * 该方法适用于已完成（但还没进入可提现）的订单退款的对账
	 * 
	 * @param orderNo - 订单编号
	 * @author hins
	 * @date 2016年8月17日 下午2:36:52
	 * @return 金额相关数据，若无/请求失败，返回null
	 */
	public RefundOrderVO applyRefundByFinish(long orderNo){
		try {
			logger.info("remote#orderAccountDetail#applyRefundByFinish | applyRefundByFinish start | orderNo: {}, message: {}", orderNo);
			
			String json = HttpClientUtils
					.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/account/applyRefundByFinish/orderNo/" + orderNo);
			RestResult<RefundOrderVO> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<RefundOrderVO>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#orderAccountDetail#applyRefundByFinish | Error | orderNo: {}, message: {}", orderNo,
					e.getMessage(), e);
		}

		return null;
	}
	
	/**
	 * 获取指定米星付订单编号-申请退款后的订单相关金额（订单金额，退款金额，优惠券信息）
	 * 
	 * @param orderNo - 订单编号
	 * @author hins
	 * @date 2016年8月17日 下午2:38:30
	 * @return 金额相关数据，若无/请求失败，返回null
	 */
	public RefundOrderVO applyRefundByMazingPay(long orderNo){
		try {
			logger.info("remote#orderAccountDetail#applyRefundByMazingPay | applyRefundByMazingPay start | orderNo: {}, message: {}", orderNo);
			
			String json = HttpClientUtils
					.doGet(CommonConstants.ORDER_DOMAIN + "/api/order/account/applyRefundByMazingPay/orderNo/" + orderNo);
			RestResult<RefundOrderVO> result = JsonUtils.parseObject(json,
					new TypeReference<RestResult<RefundOrderVO>>() {});
			return result.getObject();
		} catch (Exception e) {
			logger.error("remote#orderAccountDetail#applyRefundByMazingPay | Error | orderNo: {}, message: {}", orderNo,
					e.getMessage(), e);
		}

		return null;
	}

}
