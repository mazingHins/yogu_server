package com.yogu.services.order.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.enums.order.OrderConstants;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;

/**
 * 订单信息相关工具类
 * 
 * @author Hins
 * @date 2015年11月7日 下午3:10:12
 */
public class OrderUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderUtils.class);
	
	private static final String OPEN_ALIPAY_PAY = "1";	// 平台启用支付宝支付的开关值
	
	private static final String OPEN_WECHAT_PAY = "1";	// 平台启用微信支付的开关值

	/**
	 * 判断费用金额是否超限
	 * 
	 * @author Hins
	 * @date 2015年12月3日 上午10:06:25
	 * 
	 * @param fee - 金额（单位分）
	 */
	public static void validateFeeOverLimit(long fee) {
		// 金额小于0和大于阀值，分开不同的提示语
		if (fee <= 0) {
			logger.error("order#service#validateFeeOverLimit | 订单金额小于等于0 | totalFee: {}", fee);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDERDETAIL_PAY_FEE_TOO_SMALL());
		}
		// 2015-12-02 modify by hins 内容：判断下单的最大金额是否超限
		if (fee > OrderConstants.ORDER_FEE_THRESHOLD) {
			logger.error("order#service#validateFeeOverLimit | 订单金额超过阀值 | totalFee: {}", fee);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_SETTLE_SETTLEORDER2_TOTALFEE_MAX());
		}
	}
	
	
}
