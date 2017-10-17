package com.yogu.services.order.base.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.constant.PayResultCode;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.enums.order.OrderStatus;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.store.GoodsRemoteService;
import com.yogu.remote.store.StoreRemoteService;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.dao.params.UpdateOrderPayPOJO;
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
import com.yogu.services.order.pay.service.PayService;
import com.yogu.services.order.pay.service.params.PayReqParams;
import com.yogu.services.order.resource.vo.pay.PayVO;
import com.yogu.services.order.utils.OrderUtils;
import com.yogu.services.store.Goods;
import com.yogu.services.store.StoreCreateOrderVO;


@Named
public class OrderPayServiceImpl implements OrderPayService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderPayServiceImpl.class);
	
	@Inject
	private UserRemoteService userRemoteService;
	
	@Inject
	private StoreRemoteService storeRemoteService;
	
	@Inject
	private GoodsRemoteService goodsRemoteService;
	
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
	
	@Inject
	private PayService payService;

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
		long couponFee = couponRecord == null ? 0 : couponRecord.getCouponFee();
		order.setDiscountFee(couponFee);
		order.setActualFee(order.getTotalFee() - couponFee);
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


	@Override
	public PayVO changePayMode(long uid, long orderNo, short payMode, String userIp) {
		logger.info("order#service#changePayMode | 更改支付方式start | uid: {}, orderNo: {}, payMode: {}", uid, orderNo, payMode);

		// 1. 验证订单是否存在，状态是否可以修改
		OrderPO order = validateChangePayModeOrder(uid, orderNo, payMode);
		order.setPayMode(payMode);

		return updatePayModeToOnline(order, uid, payMode, userIp);
	}
	
	/**
	 * 验证更换支付方式的方法，订单是否存在，订单状态是否正确
	 * 
	 * @param uid - 用户id
	 * @param orderNo - 订单编号
	 * @param payType - 支付类型，取值参考枚举PayType
	 * @author hins
	 * @date 2016年7月4日 下午4:48:30
	 * @return OrderPO
	 */
	private OrderPO validateChangePayModeOrder(long uid, long orderNo, short payMode) {
		PayMode mode = PayMode.valueOf(payMode);
		ParameterUtil.assertNotNull(mode, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL());
		
		OrderPO order = orderDao.getByOrderNoUid(uid, orderNo);
		if (order == null) {
			logger.error("order#service#changePayMode | order not exist  | orderNo: {}", orderNo);
			throw new ServiceException(OrderErrorCode.ORDER_NOT_EXIST, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
		}

		// 2016-01-15 modify by hins 内容：针对超时被取消的订单，返回提示语
		if (order.getStatus() == OrderStatus.CANCEL.getValue()) {
			logger.error("order#service#changePayMode | 订单超时被取消 | orderNo: {}, status:{}", order.getOrderNo(), order.getStatus());
			throw new ServiceException(OrderErrorCode.ORDER_STATUS_NOT_NON_PAYMENT,
					OrderMessages.ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_TIMEOUT());
		}

		if (order.getStatus() != OrderStatus.NON_PAYMENT.getValue()) {// 只有为待支付的订单才可以更换支付方式
			logger.error("order#service#changePayMode | 订单状态不正常，需为待付款才能更换支付方式 | orderNo: {}, status:{}", order.getOrderNo(),
					order.getStatus());
			throw new ServiceException(OrderErrorCode.ORDER_STATUS_NOT_NON_PAYMENT,
					OrderMessages.ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_CHANGED());
		}

		return order;
	}
	
	/**
	 * 将支付方式修改成货到付款<br>
	 * 若使用的优惠券金额>订单金额，则订单修改：支付方式=线上，支付平台，订单状态，serviceDay，sequence都要修改
	 * 
	 * @author Hins
	 * @date 2016年1月6日 下午1:20:07
	 * 
	 * @param order - 订单对象
	 * @param couponFee - 优惠金额
	 * @param couponBearType - 优惠承担方
	 * @param params - 请求更换支付方式接口参数
	 */
	private PayVO updatePayModeToOnline(OrderPO order, long uid, short payMode, String userIp) {
		long orderNo = order.getOrderNo(); // 订单编号

		PayVO pay = getPayment(uid, VOUtil.from(order, Order.class), userIp, order.getDiscountFee());
		
		// 2016-10-09 add by hins end
		int rows = 0; // 数据库更新行数
		
		UpdateOrderPayPOJO pojo = initOrderPayPOJO(uid, order.getOrderId(), payMode, pay.getPayNo());
		// 不需要支付，更新支付编号，serialNumber，sequence，是否使用优惠券，优惠金额，实付金额，订单状态，支付方式，支付平台，将优惠券记录变成“已使用”
		if (pay.getPayCode() == PayResultCode.PAY_SUCCESS) {
			pojo.setOrderBeginTime(pojo.getUpdateTime());
			// 计算下单时候的预计送达时间到现在经过的秒数
			// 因为可能存在：用户下单后，经过一段时间（如10分钟），再有支付成功回到，则要更新预计送达时间。
			pojo.setNewStatus(OrderStatus.PENDING_ACCEPT.getValue());
			rows = orderDao.updatePayNoAndSuccess(pojo);
			orderCouponRecordService.recordUseSuccess(order.getOrderId());
		} else {
			// 需要支付，更新是支付编号，否使用优惠券，优惠金额，实付金额，支付方式，支付平台
			rows = orderDao.updatePayNo(pojo);
		}
		pay.setTotalFee(order.getActualFee());

		if (rows <= 0) {
			logger.error("order#service#changePayMode | 更换支付方式 | uid: {}, orderNo: {}, payMode: {}", uid, orderNo, payMode);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, OrderMessages.ORDER_ORDER_UPDATEPAYNO_FAILURE());
		}
		logger.info("order#service#changePayMode | 更改支付方式成功 | uid: {}, orderNo: {},  payMode: {}", uid, orderNo, payMode);
		return pay;
	}
	
	/**
	 * 初始化要修改订单状态，支付编号等字段的POJO对象
	 * 
	 * @author Hins
	 * @date 2016年1月6日 下午1:52:59
	 * 
	 * @param params - 更换支付方式接口请求参数
	 * @param order - 订单对象
	 * @param payNo - 支付编号
	 * @return POJO对象
	 */
	private UpdateOrderPayPOJO initOrderPayPOJO(long uid, long orderId, short payMode, long payNo) {
		UpdateOrderPayPOJO pojo = new UpdateOrderPayPOJO();
		pojo.setOrderId(orderId);
		pojo.setOldStatus(OrderStatus.NON_PAYMENT.getValue());
		pojo.setPayMode(payMode);
		pojo.setPayNo(payNo);
		pojo.setUpdateTime(new Date());
		return pojo;
	}
	
	
	/**
	 * 请求pay域获取调用支付SDK所需信息。
	 * 
	 * @author Hins
	 * @date 2015年9月12日 下午6:28:46
	 * 
	 * @param uid
	 * @param order
	 * @param userIp
	 * @return
	 */
	private PayVO getPayment(long uid, Order order, String userIp, long couponFee) {
		ParameterUtil.assertNotBlank(userIp, OrderMessages.ORDER_UTILS_VALIDATE_USERIP_ERROR());
		if (order == null) {
			logger.error("order#service#payNotify | order not exist");
			throw new ServiceException(OrderErrorCode.ORDER_NOT_EXIST, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
		}

		// 封装请求参数
		PayReqParams req = new PayReqParams();
		req.setOrderNo(order.getOrderNo());
		req.setTotalFee(order.getActualFee());// 2015-12-26 modify by hins 内容：请求支付宝金额为实际应付金额
		req.setPayMode(order.getPayMode());
		req.setBuyerUid(order.getUid());
		req.setSellerUid(order.getStoreId());
		req.setUserIp(userIp);
		// 2016-01-09 modify by hins 内容：根据优惠券版本的需求，新增2个传递参数使用优惠券费用，优惠券承担方
		req.setCouponFee(couponFee);

		req.setSubject("");
		req.setBody(getBody(order.getOrderId()));
		// 2016-10-10 add by hins 内容：请求参数加上美食价格，配送费，订单价格。作为冗余字段
		req.setGoodsFee(order.getGoodsFee());
		req.setOrderFee(order.getTotalFee());

		PayVO pay = payService.createPay(req);
		if (pay == null) {
			logger.error("order#service#getPayment | 获取支付信息失败 | orderNo: {}", order.getOrderNo());
			throw new ServiceException(OrderErrorCode.PAYAPI_GETPAY_ERROR, OrderMessages.ORDER_ORDER_GETPAYMENT_PAYMODE_ERROR());
		}

		pay.setOrderNo(order.getOrderNo());
		return pay;
	}
	
	/**
	 * 根据订单ID，查询美食名称详情。<br>
	 * 返回：美食名称，多个美食用英文逗号分隔，返回的名称长度不超过500<br>
	 * modify by hins：因为接入米星付，没有订单明细。所以只有非米星付的订单，才验证订单明细
	 * 
	 * @author Hins
	 * @date 2015年11月13日 下午3:16:25
	 * 
	 * @param orderId - 订单ID
	 * @return 美食名称，若无，返回“”
	 */
	private String getBody(long orderId) {
				
		// 1. 根据订单ID，查询订单明细
		List<OrderDetail> detailList = orderDetailService.listByOrderId(orderId);
		if (detailList.isEmpty()) {
			logger.error("order#service#getBody | 订单不存在购买明细 | orderId : {}", orderId);
			throw new ServiceException(OrderErrorCode.ORDER_NOT_EXIST, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
		}

		StringBuffer goodsIds = new StringBuffer();
		// 2. 遍历明细，装载美食ID，多个用英文逗号分隔，用于批量查询菜品
		for (OrderDetail detail : detailList) {
			goodsIds.append(",").append(detail.getGoodsId());
		}

		// 3. 批量查询商品
		List<Goods> goodsList = goodsRemoteService.getGoodsTrackByIds(goodsIds.toString());
		if (goodsList == null) {
			return "";
		}

		StringBuilder sf = new StringBuilder();
		for (Goods dish : goodsList) {
			sf.append(",").append(dish.getGoodsName());
		}
		String body = sf.substring(1);
		return body.length() < 500 ? body : body.substring(0, 500);
	}
	


}
