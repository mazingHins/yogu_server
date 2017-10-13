package com.yogu.services.order.base.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.remote.store.StoreRemoteService;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.order.base.service.SettleService;
import com.yogu.services.order.base.service.param.PurchaseDetail;
import com.yogu.services.order.coupon.service.OrderCouponService;
import com.yogu.services.order.resource.vo.order.OrderSettleVO;
import com.yogu.services.order.resource.vo.order.OrderSettleVO.SettleVO;
import com.yogu.services.order.utils.OrderUtils;
import com.yogu.services.store.StoreSettleOrderVO;

/**
 * SettleService实现类
 * 
 * @author Hins
 * @date 2015年8月6日 下午3:29:41
 */
@Named
public class SettleServiceImpl implements SettleService {

	private static final Logger logger = LoggerFactory.getLogger(SettleServiceImpl.class);

	@Inject
	private UserRemoteService userRemoteService;
	
	@Inject
	private StoreRemoteService storeRemoteService;

	@Inject
	private OrderCouponService orderCouponService;
	

	@Override
	public OrderSettleVO settleOrder(long uid, List<PurchaseDetail> params) {
		logger.info("order#service#settle | 进入预结算service流程 | uid: {}, params: {}", uid, JsonUtils.toJSONString(params));
		
		// 1. 查询购买的商品信息（包括销售/批发）
		StoreSettleOrderVO storeSettle = storeRemoteService.settleOrder(uid, JsonUtils.toJSONString(params));
		
		// 2. 计算总价格，优惠价格信息
		OrderSettleVO result = initBaseSettle(storeSettle);
		SettleVO settle = result.getSettle();
		
		// 3. 查询收货地址
		List<UserAddress> addressList = userRemoteService.listMyAddress(uid);
		settle.setAddress(addressList);
		
		// 4. 查询用户可用的优惠劵
		if (ResultCode.SUCCESS == result.getResultCode()) {// 5.
			// 装载优惠券
			settle.setCouponList(orderCouponService.listSettleCoupon(uid, settle.getTotalFee()));
		}
		
		return result;
		
	}
	
	/**
	 * 初始化结算接口返回信息<br>
	 * 信息包括餐厅信息，美食费用，配送费，订单特征
	 * 
	 * @param storeSettle - 从store域一次性获取的（餐厅信息，美食信息，配送费，库存，可选配送时间）
	 * @param result
	 * @author hins
	 * @date 2016年10月1日 下午1:09:42
	 * @return void
	 */
	private OrderSettleVO initBaseSettle(StoreSettleOrderVO storeSettle) {
		SettleVO settle = new SettleVO();
		OrderSettleVO result = new OrderSettleVO();
		result.setSettle(settle);
		// 装载费用，可选用餐人数
		settle.setTotalDiscountFee(0);
		settle.setGoodsFee(storeSettle.getGoodsFee());
		settle.setTotalFee(storeSettle.getGoodsFee());
		settle.setGoodsList(storeSettle.getList());
		
		// 判断金额是否超过限制，不在配送范围优先抛出异常                                                                                                                                                                                                                                                                                                                     
		try {
			OrderUtils.validateFeeOverLimit(settle.getTotalFee());
		} catch (ServiceException e) {
			result.setResultCode(e.getCode());
			result.setMessage(e.getMessage());
		}
		return result;
		
	}


}
