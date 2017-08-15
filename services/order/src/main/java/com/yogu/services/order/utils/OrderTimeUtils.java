package com.yogu.services.order.utils;

import java.util.Calendar;
import java.util.Date;

import com.yogu.commons.utils.DateUtils;
import com.yogu.core.enums.order.OrderIsSysConfirm;
import com.yogu.core.enums.order.OrderStatus;
import com.yogu.core.enums.order.OrderStatusUtils;
import com.yogu.core.enums.pay.PayType;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.entry.OrderPO;

/**
 * 订单时间相关计算的工具类
 * @author felix
 * @date 2016-01-29
 */
public class OrderTimeUtils {
	/**
	 * 统计订单总用时。<br>
	 * 计算方式：若订单状态=配送中且商家已签收，则用时=商家签收时间-下单时间
	 * 		     若订单状态等于商家已完成收货状态条件，则用时=订单完成时间-下单时间
	 * 
	 * @author Hins
	 * @date 2015年10月22日 上午11:30:40
	 * 
	 * @param createTime - 下单时间
	 * @param orderStatus - 订单当前状态
	 * @param finishTime - 订单完成时间
	 * @param storeConfirmTime - 商家点击送达时间
	 * @return 返回总用时的秒数，若userConfirmTime，storeConfirmTime，都为空，则返回0；若计算的总用时小于0，也返回0。
	 */
	public static int countDuration(Date createTime, short orderStatus, Date finishTime, Date storeConfirmTime) {
		int duration = 0;
		
		if (orderStatus == OrderStatus.DELIVERY.getValue() && storeConfirmTime != null) {
			duration = DateUtils.countSecond(createTime, storeConfirmTime);
		}

		if (OrderStatusUtils.INSTANCE.getStoreFinishSet().contains(orderStatus) && finishTime != null) {
			duration = DateUtils.countSecond(createTime, finishTime);
		}

		return duration < 0 ? 0 : duration;
	}
	
	
	/**
	 * 统计订单总用时。<br>
	 * 1, 当人为签收时,订单总用时=用户签收或商家点击送达时间（取先发生的）-下单时间。<br>
	 * 2, 当系统自动签收时, 订单总用时=预下单时间 - 下单时间
	 * 
	 * @param order
	 * @return
	 * 
	 * @author felix
	 */
	public static int countDuration(OrderPO order) {
		int duration = 0;
		if (order.getSysConfirm() == OrderIsSysConfirm.YES.getValue()) {
			duration = DateUtils.countSecond(order.getCreateTime(), order.getDeliveryTime());
		} else {
			duration = countDuration(order.getCreateTime(), order.getStatus(), order.getFinishTime(), order.getStoreConfirmTime());
		}

		return duration;
	}
	
	/**
	 * 计算订单的预计送达时间展示方式
	 * 
	 * @param order 订单信息
	 * @return 1 - 尽快送达; 2 - 预订
	 * @author east 2017-02-24
	 */
	public static short getDeliveryTimeMode(OrderPO order) {
		//如果是米星付，直接返回1
		if(PayType.MAZING_PAY.getValue() == order.getPayType())
			return 1;
		
		if (null == order.getOrderBeginTime() || null == order.getDeliveryTime()) {
			if(order.getStatus() == OrderStatus.NON_PAYMENT.getValue() || order.getStatus() == OrderStatus.CANCEL.getValue())
				return 1;
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDERDETAIL_STARTTIME_DELIVERYTIME_EMPTY());
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(order.getOrderBeginTime());
		cal.add(Calendar.MINUTE, order.getServiceTime());
		
		return (short) (cal.getTime().equals(order.getDeliveryTime()) ? 1 : 2);
	}
}
