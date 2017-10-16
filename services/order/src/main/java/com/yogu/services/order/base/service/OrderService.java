package com.yogu.services.order.base.service;

import com.yogu.services.order.base.dto.Order;

/**
 * 订单表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-17
 */
public interface OrderService {

	/**
	 * 根据购买人和订单编号获取订单信息 <br>
	 * 结果：若订单不存在或者订单编号所属的购买人不是uid，返回null
	 * 
	 * @author Hins
	 * @date 2015年8月15日 上午10:12:25
	 * 
	 * @param uid - 购买人
	 * @param orderNo - 订单编号
	 * @return 订单记录，若不存在，返回null
	 */
	public Order getByOrderNo(long uid, long orderNo);

	/**
	 * 根据订单编号获取订单信息<br>
	 * 结果：若订单不存在，返回null
	 * 
	 * @author Hins
	 * @date 2015年8月15日 上午10:13:00
	 * 
	 * @param orderNo - 订单编号
	 * @return 订单记录，若不存在，返回null
	 */
	public Order getByOrderNo(long orderNo);

	/**
	 * 根据订单ID 获取订单信息<br>
	 * 结果：若订单不存在，返回null
	 * 
	 * @author Hins
	 * @date 2015年8月15日 上午10:14:37
	 * 
	 * @param orderId - 订单ID
	 * @return 订单信息，若不存在，返回null
	 */
	public Order getById(long orderId);


	/**
	 * 收货确认 , 用户确认收货后执行
	 * 
	 * @param uid 用户id
	 * @param orderNo 订单号
	 */
	public void userReceiveConfirm(long uid, long orderNo);

}
