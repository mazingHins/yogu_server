package com.yogu.services.order.base.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.store.StoreRemoteService;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.dto.OrderDetail;
import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.service.OrderDetailService;
import com.yogu.services.order.base.service.OrderPayService;
import com.yogu.services.order.base.service.param.CreateOrderParam;
import com.yogu.services.order.constants.OrderCouponRecordStatus;
import com.yogu.services.order.coupon.dto.Coupon;
import com.yogu.services.order.coupon.dto.OrderCouponRecord;
import com.yogu.services.order.coupon.service.CouponService;
import com.yogu.services.order.coupon.service.OrderCouponRecordService;
import com.yogu.services.order.utils.OrderUtils;
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
	
	@Inject
	private OrderCouponRecordService orderCouponRecordService;
	
	@Inject
	private CouponService couponService;
	
	@Inject
	private OrderDao orderDao;

	@Override
	public Order createOrder(CreateOrderParam params, long uid) {
		logger.info("order#service#createOrder | 创建订单start | uid: {}", uid);
		
		// 1. 验证参数，获取收货地址、用户信息
		UserAndAddress ua = validateAddress(params, uid);
		
		
		// 2. 调用store接口，获取餐厅、美食、配送费（包括顺丰）、最新配送时间、服务日期。并锁住库存... 如果已经存在异常信息（库存不足，美食下架），直接返回
		StoreCreateOrderVO storeOrder = storeRemoteService.createOrder(uid, JsonUtils.toJSONString(params.getPurchaseDetails()));
		
		// 3. 初始化order对象
		Order order = VOUtil.from(params, Order.class);
		
		// 4. 补全订单信息
		OrderUtils.loadOrder(order, ua, storeOrder);
		
		// 5. 尝试使用优惠卷
		long orderId = idGenRemoteService.getNextOrderId(); // 订单id，防止接下来操作出现异常，释放优惠券
		OrderCouponRecord couponRecord = useCoupon(VOUtil.from(order, OrderPO.class), params.getCouponId());
		order.setDiscountFee(couponRecord.getCouponFee());
		order.setActualFee(order.getTotalFee() - couponRecord.getCouponFee());
		order.setUseCoupon(couponRecord == null ? BooleanConstants.FALSE : BooleanConstants.TRUE);
		
		// 6. 保存数据
		List<OrderDetail> detailList = orderDetailService.initOrderDetailByCreateOrder(params.getPurchaseDetails(), storeOrder.getGoodsMap(), orderId);
		saveOrder(VOUtil.from(order, OrderPO.class), detailList);
		return order;
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
	
	private OrderCouponRecord useCoupon(OrderPO order,  long couponId) {
		ParameterUtil.assertNotNull(order, "订单不能为空");
		long orderId = order.getOrderId();

		OrderCouponRecord couponRecord = orderCouponRecordService.getOrderLockRecord(orderId);

		// 1. 新的请求不使用优惠券，且此订单没有“锁定”旧优惠券，返回null
		if (couponId < 1 && couponRecord == null) {
			logger.info("order#service#useCoupon | 此订单没有使用优惠券 | orderId: {}", orderId);
			return null;
		}

		// 2. 新的请求不使用优惠券，但此订单已“锁定”了旧优惠券
		if (couponId < 1 && couponRecord != null) {
			// 不允许取消使用优惠券！！因为微信同一张单不允许多次“统一下单”！！
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_CAN_NOT_USE_COUPON());
		}

		// 3. 新的请求使用优惠券，但没有已“锁定”的旧优惠券，新增锁定
		if (couponRecord == null) {
			if (order.getPayNo() > 0) { // 已经调用过一次pay域获取支付信息，所以此情况不允许使用优惠券了！！因为微信同一张单不允许多次“统一下单”！！
				throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_CAN_NOT_USE_COUPON());
			}

			logger.info("order#service#useCoupon | 此订单使用优惠券，但没有已“锁定”的旧优惠券 | orderId: {}, couponId: {}", order.getOrderId(), couponId);
			return saveCouponRecord(order, couponId);
		}

		// 4. 新的请求改变了优惠券，抛出异常，不允许更换优惠券
		if (couponId != couponRecord.getCouponId()) {
			logger.error("order#service#useCoupon | 此订单使用新的优惠券，且有旧的优惠券，不允许更换 | orderId: {}, newCouponId: {}, oldCouponId", orderId,
					couponId, couponRecord.getCouponId());
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_USECOUPON_COUPON_CHANGE());
		}
		
		return couponRecord;
	}
	
	/**
	 * 当前订单“锁定”使用优惠券
	 * 
	 * @author Hins
	 * @date 2016年1月7日 下午8:21:27
	 * 
	 * @param order - 订单对象
	 * @param detailList - 订单明细（因为创建订单时可以使用优惠券，所以不是先查询数据库（从数据库查询订单明细）），订单明细用于装载调用券域的remote方法（dishIds参数）
	 * @param couponId - 要使用的优惠券ID
	 * @return 优惠使用记录
	 */
	public OrderCouponRecord saveCouponRecord(OrderPO order, long couponId) {
		UserProfile user = userRemoteService
				.getUserProfileByUid(order.getUid());
		ParameterUtil.assertNotNull(user,
				OrderMessages.ORDER_ORDER_USECOUPON_GETUSER_FAILURE());

		// 使用优惠券
		Coupon coupon = couponService.useCoupon(couponId, order.getUid(), order.getOrderNo(), order.getTotalFee(), order.getGoodsFee());

		// “锁定”成功后，新增订单优惠券使用记录
		long couponFee = coupon.getCouponFee();
		if (couponFee > order.getTotalFee()) { // 防止优惠金额>订单应付金额
			couponFee = order.getTotalFee();
		}
		OrderCouponRecord record = new OrderCouponRecord();
		long recordId = idGenRemoteService.getNextOrderCommentId();
		record.setRecordId(recordId);
		record.setCreateTime(DateUtils.getUniformCurrentTimeForThread());
		record.setCouponFee(couponFee);
		record.setCouponName(coupon.getCouponName());
		record.setCouponType(coupon.getCouponType());
		record.setCouponId(couponId);
		record.setOrderId(order.getOrderId());
		record.setUseStatus(OrderCouponRecordStatus.LOCK);
		orderCouponRecordService.save(record);
		return record;

	}
	

	/**
	 * 保存订单和订单明细<br>
	 * 将bigTotalFee和bigGoodsFee转成totalFee,goodsFee<br>
	 * long转int，注意越界问题
	 * 
	 * @param order - 订单
	 * @param detailList - 订单明细列表
	 */
	private void saveOrder(OrderPO order, List<OrderDetail> detailList) {
		// save order
		orderDao.save(order);
		// save order details
		if (detailList != null && !detailList.isEmpty()) {
			// 合并相同的记录，然后保存 2016/2/23
			for (OrderDetail od : detailList) {
				orderDetailService.save(od);
			}
		}
	}
	
	


}
