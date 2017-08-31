package com.yogu.services.order.base.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.base.entry.OrderDetailPO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * yg_order_detail 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface OrderDetailDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(OrderDetailPO po);

	// /**
	// * 修改数据，以主键更新
	// *
	// * @param po - 要更新的数据
	// * @return 更新的行数
	// */
	// public int update(OrderDetailPO po);

	/**
	 * 根据主键读取记录
	 */
	public OrderDetailPO getById(@Param("goodsId") long goodsId, @Param("orderId")long orderId);

	/**
	 * 根据订单ID 查询全部记录
	 * 
	 * @param orderId - 订单ID
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderDetailPO> listByOrderId(long orderId);

	/**
	 * 分页获取所有的订单详情
	 * 
	 * @param offset
	 * @param pageSize
	 * @return
	 * @author sky 2016-02-25
	 */
	public List<OrderDetailPO> listByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

	/**
	 * 获取订单orderId所购买的菜品总数
	 * 
	 * @param orderId
	 * @return
	 */
	public int countOrderDishs(@Param("orderId") long orderId);
	
}
