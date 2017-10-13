package com.yogu.services.order.base.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.store.StoreRemoteService;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.dto.OrderDetail;
import com.yogu.services.order.base.service.OrderDetailService;
import com.yogu.services.order.base.service.OrderPayService;
import com.yogu.services.order.base.service.param.CreateOrderParam;
import com.yogu.services.store.StoreCreateOrderVO;


@Named
public class OrderPayServiceImpl implements OrderPayService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderPayServiceImpl.class);
	
	@Inject
	private UserRemoteService userRemoteService;
	
	@Inject
	private StoreRemoteService storeRemoteService;
	
	@Inject
	private IdGenRemoteService idGenRemoteService;
	
	@Inject
	private OrderDetailService orderDetailService;

	@Override
	public Order createOrder(CreateOrderParam params, long uid) {
		logger.info("order#service#createOrder | 创建订单start | uid: {}", uid);
		
		// 1. 验证参数，获取收货地址、用户信息
		UserAndAddress ua = validateAddress(params, uid);
		
		
		// 2. 调用store接口，获取餐厅、美食、配送费（包括顺丰）、最新配送时间、服务日期。并锁住库存... 如果已经存在异常信息（库存不足，美食下架），直接返回
		StoreCreateOrderVO storeOrder = storeRemoteService.createOrder(uid, JsonUtils.toJSONString(params.getPurchaseDetails()));
		
		// 3. 初始化order对象
		Order order = VOUtil.from(params, Order.class);
		
		// 4. 尝试使用优惠卷
		long orderId = idGenRemoteService.getNextOrderId(); // 订单id，防止接下来操作出现异常，释放优惠券
		List<OrderDetail> detailList = orderDetailService.initOrderDetailByCreateOrder(params.getPurchaseDetails(), storeOrder.getGoodsMap(), orderId);
		
		
		return null;
	}
	

	/**
	 * 验证创建订单方法参数是否合法（餐厅、美食、库存，配送时间等信息不验证） 验证用户收货地址，判断是否存在，是否本人收货地址
	 * 
	 * @param params - 创建订单方法参数
	 * @param uid - 用户id
	 * @return 验证通过后的用户和地址信息
	 */
	private UserAndAddress validateAddress(CreateOrderParam params, long uid) {
		if (params.getPayMode() > 0) {
			PayMode mode = PayMode.valueOf(params.getPayMode());
			ParameterUtil.assertNotNull(mode, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL());
		}
		ParameterUtil.assertNotNull(params.getPurchaseDetails(), OrderMessages.ORDER_ORDER_SPILTBUY_DISH_EMPTY());
		ParameterUtil.assertMaxLength(params.getUserIp(), 16, OrderMessages.ORDER_UTILS_VALIDATE_USERIP_ERROR());
		if (StringUtils.isNotBlank(params.getRemark()))
			ParameterUtil.assertMaxLength(params.getRemark(), 100, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_REMARK_LENGTH_OVERLIMIT());

		UserAndAddress ua = userRemoteService.getUserAndAddress(uid, params.getAddressId());

		if (ua == null || ua.getUserAddress() == null || ua.getUserProfile() == null) {
			logger.error("order#service#createOrder | user address not exist | uid: {}, addressId: {}", uid, params.getAddressId());
			throw new ServiceException(OrderErrorCode.USER_ADDRESS_NOT_EXIST, OrderMessages.ORDER_ORDER_VALIDATEADDRESS_ADDRESS_NOTEXIST());
		}
		return ua;
	}

}
