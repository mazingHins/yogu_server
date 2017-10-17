package com.yogu.services.order.pay.service;

import com.yogu.services.order.pay.dto.AlipayPayNotify;
import com.yogu.services.order.pay.dto.WechatPayNotify;
import com.yogu.services.order.pay.service.params.PayReqParams;
import com.yogu.services.order.resource.vo.pay.PayVO;

/**
 * 支付请求相关的操作接口
 * 
 * @author Hins
 * @date 2015年8月6日 上午11:26:23
 */
public interface PayService {

	/**
	 * 生成支付请求
	 * 
	 * @param param
	 * @return 调用SDK所需参数
	 */
	PayVO createPay(PayReqParams param);
	
	/**
	 * 处理支付宝回调<br>
	 * 处理：若是支付成功回调，则新增支付回调记录，修改支付记录状态，并马上callback order域，若通知失败，新增定时通知任务。<br>
	 * 	        若是等待支付回调，则新增支付回调记录<br>
	 * 此方法不校验是否合法的支付宝回调，只提供给AlipayPayNotifyServlet!<br>
	 * 请求参数notify包括了支付宝回调(状态结果、买家、卖家、平台支付编号、交易时间、支付金额（分）等)
	 * 
	 * @param notify - 请求参数封装类
	 */
	@Deprecated
	void alipayNotify(AlipayPayNotify notify);
	
	/**
	 * 处理微信支付回调<br>
	 * 此方法不能兼容微信首次回调是“支付失败”，第二次回调“支付成功”（即多次回调的支付结果不同！），但是官方api文档不应该存在这个情况<br>
	 * 此方法不校验是否合法的微信回调，只提供给WechatPayNotifyServlet!
	 * 
	 * @author Hins
	 * @date 2016年2月1日 下午3:24:32
	 * 
	 * @param notify
	 */
	@Deprecated
	void wechatNotify(WechatPayNotify notify);

}
