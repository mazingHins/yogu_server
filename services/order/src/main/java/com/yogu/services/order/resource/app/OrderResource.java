package com.yogu.services.order.resource.app;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.base.Point;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.service.SettleService;
import com.yogu.services.order.base.service.param.PurchaseDetail;
import com.yogu.services.order.resource.param.NewSettleParam;
import com.yogu.services.order.resource.vo.OrderSettleVO;

public class OrderResource {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);
	
	@Inject
	private SettleService settleService;
	
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
	@Path("v1/order/settle2.do")
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


}
