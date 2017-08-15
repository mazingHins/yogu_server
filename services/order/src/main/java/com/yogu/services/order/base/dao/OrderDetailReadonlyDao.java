package com.yogu.services.order.base.dao;

import java.util.List;

import com.yogu.services.order.base.entry.OrderDetailPO;

/**
 * mz_order_detail 表的操作
 * 操作从库
 * 
 * @author Hins
 * @date 2015年12月17日 上午10:58:50
 */
public interface OrderDetailReadonlyDao {
	
	/**
	 * 根据主键读取记录
	 */
	public OrderDetailPO getById(long objectId, long orderId);

	/**
	 * 根据订单ID 查询全部记录
	 * @param orderId - 订单ID
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderDetailPO> listByOrderId(long orderId);

}
