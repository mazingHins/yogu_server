package com.yogu.services.order.base.service;

import java.util.List;

import com.yogu.services.order.base.service.param.PurchaseDetail;
import com.yogu.services.order.resource.vo.OrderSettleVO;


public interface SettleService {
	
	/**
	 * 生成订单预支付信息（封装美食明细，配送费信息，优惠信息， 可选配送时间，用餐人数，订单特殊选择，用户收货地址列表）<br>
	 * v1.1.0-预订类餐厅设计方案（11-09调整）之后的版本调用该方法<br>
	 * 根据“优惠券提前使用”版本的要求，此方法在2.2.1版本后会返回可用和没满足条件的优惠券列表{@link http://w.mazing.com/www/index.php?m=story&f=view&storyID=156}<br>
	 * 
	 * @param params - 请求参数
	 * @return 下单提示信息，结算信息map
	 */
	public OrderSettleVO settleOrder(long uid, List<PurchaseDetail> params);
	

}
