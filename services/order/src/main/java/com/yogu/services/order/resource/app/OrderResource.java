package com.yogu.services.order.resource.app;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.Validator;
import com.yogu.core.enums.order.OrderConstants;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.service.OrderPayService;
import com.yogu.services.order.base.service.OrderService;
import com.yogu.services.order.base.service.SettleService;
import com.yogu.services.order.base.service.param.CreateOrderParam;
import com.yogu.services.order.base.service.param.PurchaseDetail;
import com.yogu.services.order.resource.param.CreateParam;
import com.yogu.services.order.resource.param.NewSettleParam;
import com.yogu.services.order.resource.vo.OrderPayVO;
import com.yogu.services.order.resource.vo.order.OrderSettleVO;
import com.yogu.services.order.resource.vo.pay.PayVO;
import com.yogu.services.order.utils.OrderUtils;

@Named
@Path("a")
@Singleton
@Produces("application/json;charset=UTF-8")
public class OrderResource {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);
	
	@Inject
	private SettleService settleService;
	
	@Inject
	private OrderPayService orderPayService;
	
	@Inject
	private OrderService orderService;
	
	/**
	 * 生成订单预支付信息，返回美食明细，配送费信息，优惠信息，<br>
	 * 可选配送时间，用餐人数，订单特殊选择，用户收货地址列表结果<br>
	 * v1.1.0-预订类餐厅设计方案（11-09调整）之后的版本调用该接口
	 * 
	 * @author Hins
	 * @date 2015年8月24日 上午12:12:10
	 * 
	 * @param reqParams - 请求参数
	 * @return 预支付信息
	 */
	@POST
	@Path("v1/order/settle.do")
	public RestResult<OrderSettleVO> settle(@Valid @BeanParam NewSettleParam reqParams, @Context HttpServletRequest request) {
		long uid = SecurityContext.getUid();
		logger.info("order#settle | 订单预支付信息接口  | uid: {}, reqParams: {}", uid, JsonUtils.toJSONString(reqParams));

		// 重新装载service方法的请求参数
		List<PurchaseDetail> params = convertToPurchaseDetail(reqParams.getPurchaseDetail());

		OrderSettleVO result = settleService.settleOrder(uid, params);
		logger.info("order#settle | 订单预支付信息接口  | uid: {}, result: {}", uid, JsonUtils.toJSONString(result));
		return new RestResult<>(result);
	}
	
	
	/**
	 * 把json转换成 PurchaseDetail 列表
	 * @param json JSON 数据，参考格式；http://10.0.0.10:8090/pages/viewpage.action?pageId=5701834
	 * @return 返回 PurchaseDetail 列表
	 */
	private List<PurchaseDetail> convertToPurchaseDetail(String json) {
		String purchaseDetail = StringUtils.trimToEmpty(json);
		List<PurchaseDetail> purchaseDetailList = JsonUtils.parseObject(purchaseDetail, new TypeReference<List<PurchaseDetail>>() {
		});
		if (CollectionUtils.isEmpty(purchaseDetailList)) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_CONVERTTOPURCHASEDETAIL_PURCHASEDETAILLIST_EMPTY());
		}
		validatePurchaseDetails(purchaseDetailList);
		return purchaseDetailList;
	}
	

	/**
	 * 校验美食参数
	 *
	 * @param list 购买的美食详情
	 * @author ten 2016/2/23
	 */
	private void validatePurchaseDetails(List<PurchaseDetail> list) {
		ParameterUtil.assertNotNull(list, OrderMessages.ORDER_ORDER_SPILTBUY_DISH_EMPTY());
		for (PurchaseDetail detail : list) {
			if (detail.getPurchaseNum() < 1) {
				logger.info("order#validatePurchaseDetails | 校验美食参数，购买数量为空 | detail: {}", JsonUtils.toJSONString(detail));
				throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_VALIDATEPURCHASEDETAILS_NUM_ERROR());
			}
		}
	}
	
	/**
	 * 生成订单，若不出现异常： 1. 若选择了支付方式，则调用pay域，生成支付请求，获得调用SDK所需的信息，封装并返回 2. 若没选择支付方式，则只封装订单编号和总费用基本信息. 若库存不足，或存在菜品下线，则返回具体的错误信息列表
	 *
	 * @author Hins
	 * @date 2015年8月24日 上午12:15:15
	 *
	 * @param reqParams - 生成订单接收参数类
	 * @return 若选择了支付方式，则返回调用支付SDK所需的信息，若无，则返回订单编号和总费用基本信息
	 * @modified by ten 2016/2/23 增加规格的处理
	 */
	@POST
	@Path("v1/order/create.do")
	public RestResult<OrderPayVO> create(@Valid @BeanParam CreateParam reqParams) {
		long uid = SecurityContext.getUid();
		String userIp = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
		logger.info("order#create | 下单接口 | uid: {}, ip: {}, reqParams: {}", uid, userIp, JsonUtils.toJSONString(reqParams));

		// 1. 验证参数
		validateCreateOrder(reqParams);
		
		
		// 2. 重新装载service方法的请求参数
		List<PurchaseDetail> purchaseDetails = convertToPurchaseDetail(reqParams.getPurchaseDetail());
		
		// 3. 创建订单
		CreateOrderParam params = VOUtil.from(reqParams, CreateOrderParam.class);
		params.setPurchaseDetails(purchaseDetails); // 使用规格 2016/2/23
		Order order = orderPayService.createOrder(params, uid);
		
		// 4. 装载返回结果
		OrderPayVO result = new OrderPayVO();
		result.setOrderNo(order.getOrderNo());
		result.setTotalFee(Long.valueOf(order.getTotalFee()).intValue());
		result.setCreateTime(order.getCreateTime());
		result.setResult(OrderConstants.RESULT_CODE_SUCCESS);

		// 5. 装载可选支付方式 1-支付宝；2-微信；3-现金
		String optionsPayMode = OrderUtils.getOptionsPayMode(order.getUseCoupon());
		result.setOptionalPayMode(optionsPayMode);
		result.setDefaultPayMode(PayMode.ALIPAY.getValue());
		
		return new RestResult<OrderPayVO>(result);
	}
	
	/**
	 * 检查下单方法参数是否正确
	 * 
	 * @param params - 购买物品的详情
	 */
	private static void validateCreateOrder(CreateParam params) {

		if (StringUtils.isBlank(params.getPurchaseDetail())) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_SPILTBUY_DISH_EMPTY());
		}
		
		if (params.getPayMode() > 0) {
			PayMode mode = PayMode.valueOf(params.getPayMode());
			ParameterUtil.assertNotNull(mode, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL());
		}
		String remark = params.getRemark();
		if (StringUtils.isNotBlank(params.getRemark()))
			ParameterUtil.assertMaxLength(params.getRemark(), 100, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_REMARK_LENGTH_OVERLIMIT());
		if (Validator.containsEmoji(remark))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_REMARK_EMOJI());
	}
	
	/**
	 * 更换支付方式，重新调用pay域，生成支付请求，成功后修改订单 封装并返回调用SDK所需的信息
	 * 
	 * @author Hins
	 * @date 2015年8月24日 上午12:22:20
	 * 
	 * @param payMode - 支付方式  1- 支付宝；2-微信
	 * @param orderNo - 订单编号
	 * @return 调用SDK所需的信息
	 */
	@POST
	@Path("v1/order/change.do")
	public RestResult<PayVO> change( @FormParam("payMode") @DefaultValue("1") short payMode,
			@FormParam("orderNo") long orderNo) {
		long uid = SecurityContext.getUid();
		String userIp = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);
		logger.info("order#change | change pay mode  | uid: {}, ip: {}, payMode: {}, orderNo: {}", uid, userIp, payMode, orderNo);

		// 验证参数
		validateChange(uid, orderNo, payMode);

		// 修改订单的支付方式
		PayVO result = orderPayService.changePayMode(uid, orderNo, payMode, userIp);
		
		return new RestResult<PayVO>(result);
	}
	
	/**
	 * 验证更换支付方式方法的参数<br>
	 * 返回验证通过后的service方法请求参数<br>
	 * 参数不包括下单ip和优惠券id
	 *
	 * @param uid - 用户id
	 * @param orderNo - 订单编号
	 * @param payMode - 支付方式
	 * @author hins
	 * @date 2016年7月4日 下午5:07:13
	 */
	private void validateChange(long uid, long orderNo, short payMode){
		ParameterUtil.assertNotNull(orderNo, OrderMessages.ORDER_COMMENT_VALIDATEADDCOMMENT_ORDERNO_EMPTY());
		Order order = orderService.getByOrderNo(uid, orderNo);
		ParameterUtil.assertNotNull(order, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());
		PayMode mode = PayMode.valueOf(payMode);
		ParameterUtil.assertNotNull(mode, OrderMessages.ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL());
		
	}

	@POST
	@Path("v1/order/confirm.do")
	public RestResult<Integer> confirm(@FormParam("orderNo") long orderNo) {
		// 登录验证
		long uid = SecurityContext.getUid();

		logger.info("#order#confirmReceive | confirm has received | userId: {}, orderNo: {}", uid, orderNo);

		if (orderNo <= 0)
			throw new ServiceException(OrderErrorCode.ORDER_NOT_EXIST, OrderMessages.ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST());

		//确认收货
		orderService.userReceiveConfirm(uid, orderNo);

		return new RestResult<Integer>(1);
	}
	

}
