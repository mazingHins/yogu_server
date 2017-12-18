package com.yogu.services.order.resource.app;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.service.OrderService;
import com.yogu.services.order.resource.vo.order.UserOrderVO;

/**
 * 
 * @Description: 订单列表相关接口
 * @author Hins
 * @date 2015年8月3日 上午11:37:55
 */
@Named
@Path("a")
@Singleton
@Produces("application/json;charset=UTF-8")
public class OrderListResource {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderListResource.class);
	
	@Inject
	private OrderService orderService;

	/**
	 * 查看进行中的订单列表
	 * 
	 * @return 订单列表，订单轨迹数据
	 */
	@GET
	@Path("v1/order/list")
	public RestResult<List<UserOrderVO>> listOrder(@QueryParam("pageIndex") int pageIndex// 第几页（1开始），小于1则默认第一页
			, @QueryParam("pageSize") int pageSize) {
		long uid = SecurityContext.getUid();
		logger.debug("order#listInOrder | 进行中的订单列表 | uid: {}", uid);
		
		if (pageIndex < 1) {
			pageIndex = 1;
		}

		if (pageSize < 1 || pageSize > 50) {
			pageIndex = 10;
		}

		List<Order> list = orderService.listOrderByUid(uid, pageIndex, pageSize);
		
		return new RestResult<List<UserOrderVO>>(orderToVo(uid, list));
	}
	
	private List<UserOrderVO> orderToVo(long uid, List<Order> list){
		
		List<UserOrderVO> result = new ArrayList<>(list.size());
		for(Order order : list){
			UserOrderVO vo = orderService.userOrdeDetail(uid, order.getOrderNo());
			result.add(vo);
		}
		
		return result;
		
	}
	
	/**
	 * 用户查看订单详情
	 * 
	 * @param orderNo - 订单编号
	 * @return 商家信息，订单信息（配送费，优惠费，下单填写信息等），订单明细
	 */
	@GET
	@Path("v1/order/detail")
	public RestResult<UserOrderVO> orderDetail(@QueryParam("orderNo") Long orderNo) {
		long uid = SecurityContext.getUid();
		logger.debug("order#orderDetail | 订单详情 | uid: {}, orderNo: {}", uid, orderNo);
		ParameterUtil.assertNotNull(orderNo, OrderMessages.ORDER_ORDERLIST_ORDERDETAIL_ORDERNO_EMPTY());
		return new RestResult<UserOrderVO>(orderService.userOrdeDetail(uid, orderNo));
	}
	
}
