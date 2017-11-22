package com.yogu.services.order.resource.api;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.dto.OrderDetail;
import com.yogu.services.order.base.service.OrderDetailService;
import com.yogu.services.order.base.service.OrderService;
import com.yogu.services.order.coupon.dto.OrderCouponRecord;
import com.yogu.services.order.coupon.service.OrderCouponRecordService;
import com.yogu.services.order.resource.vo.order.AdminOrderVO;

@Named
@Path("api")
@Singleton
@Produces("application/json;charset=UTF-8")
public class LocalOrderApiResource {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalOrderApiResource.class);

	@Inject
	private OrderService orderService;
	
	@Inject
	private OrderDetailService orderDetailService;
	
	@Inject
	private OrderCouponRecordService orderCouponRecordService;
	
	
	/**
	 * 查询符合条件的所有订单。后台管理使用<br>
	 * 该方法只查询非米星付的订单，米星付和其他的订单分开查询和展示 2016/7/13 add by hins
	 * 
	 * @param uid 查询哪个用户的订单，可以为 0
	 * @param orderNo 订单编号，可以为0，优先级最高
	 * @param storeId 餐厅ID，可以为0，优先级和 uid 一样，和uid可以同时起作用
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 返回订单数据，不为null，如果没有数据，返回size=0的数据
	 * @author ben 2015-11-25
	 * @mofified by ten 2015/12/17
	 */
	@GET
	@Path("/order/queryOrders")
	public RestResult<List<AdminOrderVO>> queryOrders(@QueryParam("uid") long uid,
											   @QueryParam("orderNo") long orderNo,
											   @QueryParam("storeId") long storeId,
											   @QueryParam("pageIndex") int pageIndex,
			@QueryParam("pageSize") int pageSize) {
		logger.info("api#order#queryOrders | 准备读取数据 | orderNo: {}, pageIndex: {}, pageSize: {}", orderNo, pageIndex, pageSize);
		
		if (pageIndex < 0 || pageSize < 0)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDERADMINAPI_VALIDATE_PAGEINDEX_ERROR());
		

		List<AdminOrderVO> list = null;
		if (orderNo == 0) {
			list = orderService.listAllOrders(uid, storeId, pageIndex, pageSize);
		} else {
			Order order = orderService.getByOrderNo(orderNo);
			list = new LinkedList<>();
			AdminOrderVO vo = VOUtil.from(order, AdminOrderVO.class);
			list.add(vo);
		}
		logger.info("api#order#queryOrders | 读取数据成功 | data size: {}", list.size());
		return new RestResult<>(list);
	}
	
	/**
	 * 获取订单信息
	 * 
	 * @param orderNo
	 *            订单编号
	 * @return
	 */
	@GET
	@Path("v1/order/getOrderByNo")
	public RestResult<AdminOrderVO> getOrderByNo(@QueryParam("orderNo") long orderNo) {
		logger.info("api#order#getOrderByNo | 准备读取数据 | orderNo: {}", orderNo);
		AdminOrderVO order = VOUtil.from(orderService.getByOrderNo(orderNo), AdminOrderVO.class);
		return new RestResult<AdminOrderVO>(order);
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
	 * @author ben
	 */
    @GET
    @Path("order/detail")
    public RestResult<Map<String, Object>> orderDetail(@QueryParam("orderNo") long orderNo,
                                                       @Context HttpServletRequest request) {

        if (orderNo <= 0) {
            return new RestResult<>(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDERNO_ERROR());
        }
        // 查询商家信息
        Order order = orderService.getByOrderNo(orderNo);
        if (order == null) {
            return new RestResult<>(ResultCode.FAILURE, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
        }
        else {
			long orderId = order.getOrderId();
            // 返回结果
            Map<String, Object> map = new HashMap<>(4);
            map.put("order", order);
            // 2016/7/12 add by hins 内容：米星付的订单不查询美食详情
			List<OrderDetail> orderDetails = orderDetailService.listByOrderId(orderId);
			map.put("orderDetails", orderDetails);
			if (order.getDiscountFee() > 0) {
				// 读取优惠券相关信息
				OrderCouponRecord coupon = orderCouponRecordService.getNewestRecord(orderId);
				if (coupon != null) {
					map.put("coupon", coupon);
				}
			}

            logger.info("api#order#orderDetail | 订单详情 | orderNo: {}, IP: {}", orderNo,
                    IpAddressUtils.getClientIpAddr(request));
            return new RestResult<>(map);
        }
    }

	
}
