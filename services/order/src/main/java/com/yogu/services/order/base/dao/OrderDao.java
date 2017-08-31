package com.yogu.services.order.base.dao;

import com.yogu.services.order.base.entry.OrderPO;

/**
 * yg_order 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface OrderDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(OrderPO po);

	// --------------------------- 获取单个订单对象查询方法 start --------------------
	/**
	 * 根据主键读取记录<br>
	 * 此方法查询主库
	 * 
	 */
	public OrderPO getById(long orderId);

	/**
	 * 根据订单编号和下单人获取记录<br>
	 * 此方法查询主库
	 * 
	 * @param uid - 下单人
	 * @param orderNo - 订单编号
	 * @return
	 */
	public OrderPO getByOrderNoUid(long uid, long orderNo);

	/**
	 * 根据订单编号获取记录<br>
	 * 此方法查询主库
	 * 
	 * @param orderNo - 订单编号
	 * @return
	 */
	public OrderPO getByOrderNo(long orderNo);

}
