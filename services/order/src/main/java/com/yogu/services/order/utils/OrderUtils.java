package com.yogu.services.order.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.StringUtils;
import com.yogu.core.enums.order.OrderConstants;
import com.yogu.core.enums.order.OrderStatus;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.store.StoreCreateOrderVO;

/**
 * 订单信息相关工具类
 * 
 * @author Hins
 * @date 2015年11月7日 下午3:10:12
 */
public class OrderUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderUtils.class);
	
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
	
	
	/**
	 * 装载订单其他数据<br>
	 * 包括：订单状态，创建时间，收货地址信息，订单金额，配送信息，门店信息
	 * 
	 * @author Hins
	 * @date 2015年8月22日 下午3:18:08
	 * 
	 * @param order - 订单对象
	 * @param ua - 用户、收货地址
	 * @param storeOrder - store域获取的下单信息
	 */
	public static void loadOrder(Order order, UserAndAddress ua, StoreCreateOrderVO storeOrder) {
		
		UserProfile user = ua.getUserProfile();
		UserAddress address = ua.getUserAddress();
		
		
		order.setUid(user.getUid());
		// felix 记录用户IM ID信息
		order.setGoodsFee(storeOrder.getGoodsFee());
		// 过滤掉 \n, \t, 2015/12/7 by ten
		order.setRemark(order.getRemark().replaceAll("\n", "").replaceAll("\t", ""));
		
		Date createTime = new Date();
		long orderNo = OrderNO.next(order.getUid(), order.getOrderId());
		order.setStatus(OrderStatus.NON_PAYMENT.getValue());
		order.setCreateTime(createTime);
		order.setUpdateTime(createTime);
		order.setOrderNo(orderNo);
		order.setFullAddress(StringUtils.isBlank(address.getFullAddress()) ? StringUtils.EMPTY : address.getFullAddress());
		order.setContacts(address.getContacts());
		order.setPhone(address.getPhone());
		order.setStoreId(storeOrder.getStoreId());
		order.setDiscountFee(0);
		order.setTotalFee(order.getGoodsFee());
		order.setActualFee(order.getTotalFee());
		
		validateFeeOverLimit(order.getTotalFee());
	}
	
	/**
	 * 获取订单可选支付方式<br>
	 * 在以下支付方式中选择1-支付宝；2-微信；3-现金
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午6:41:02
	 * 
	 * @param useCoupon - 订单是否使用优惠券（如果使用了优惠券，则不支持现金支付，取值范围{@link com.mazing.core.enums.BooleanConstants}）
	 * @param payType - 支付类型（米星付不支持出现现金支付）
	 * @param isThirdExpress - 是否第三方配送
	 * @return 可选支付方式，多个用逗号分隔
	 */
	public static String getOptionsPayMode(short useCoupon){
		return PayMode.ALIPAY.getValue() + "," + OrderConstants.PAY_MODE_CACH;
		
	}
	
	
}
