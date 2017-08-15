package com.yogu.services.order.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.base.entry.OrderRefundDetailPO;

/**
 * mz_order_refund_detail 表的操作接口
 * 
 * @author Mazing 2017-03-07
 */
@TheDataDao
public interface OrderRefundDetailDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(OrderRefundDetailPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("refundId") long refundId, @Param("ticketId") long ticketId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(OrderRefundDetailPO po);

	/**
	 * 根据主键读取记录
	 */
	public OrderRefundDetailPO getById(@Param("refundId") long refundId, @Param("ticketId") long ticketId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderRefundDetailPO> listAll();
	
	/**
	 * 批量插入
	 * @param list    
	 * @author east
	 * @date 2017年3月7日 下午6:22:36
	 */
	public void saveAll(@Param("list") List<OrderRefundDetailPO> list);

}
