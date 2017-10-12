package com.yogu.services.order.resource.param;

import javax.ws.rs.FormParam;

/**
 * 订单预支付API接收参数<br>
 * v1.1.0-预订类餐厅设计方案（11-09调整）之后的版本用此接收
 * 
 * @author Hins
 * @date 2015年11月13日 下午12:00:45
 */
public class NewSettleParam {

	/**
	 * 购买详情
	 * ten 2016/2/22 新增，美食规格开发
	 */
	@FormParam("purchaseDetail")
	private String purchaseDetail;
	
	public String getPurchaseDetail() {
		return purchaseDetail;
	}

	public void setPurchaseDetail(String purchaseDetail) {
		this.purchaseDetail = purchaseDetail;
	}

}
