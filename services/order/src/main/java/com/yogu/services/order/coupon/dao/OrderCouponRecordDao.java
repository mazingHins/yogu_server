package com.yogu.services.order.coupon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.coupon.entry.OrderCouponRecordPO;

/**
 * yg_order_coupon_record 表的操作接口
 * 
 * @author Mazing 2015-12-23
 */
@TheDataDao
public interface OrderCouponRecordDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(OrderCouponRecordPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("recordId") long recordId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(OrderCouponRecordPO po);

	/**
	 * 根据主键读取记录
	 */
	public OrderCouponRecordPO getById(@Param("recordId") long recordId);

	/**
	 * 查询订单指定使用状态的优惠券使用记录，不排序
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午7:29:00
	 * 
	 * @param orderId - 订单ID
	 * @param useStatus - 使用状态 参考OrderCouponRecordStatus
	 * @return 返回符合条件的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderCouponRecordPO> listByOrderAndStatus(@Param("orderId") long orderId, @Param("useStatus") short useStatus);

	/**
	 * 修改使用记录的使用状态
	 * 
	 * @author Hins
	 * @date 2016年1月4日 下午4:05:32
	 * 
	 * @param orderId - 订单ID
	 * @param oldStatus - 旧的状态（查询条件）
	 * @param newStatus - 新的状态
	 * @return 更新行数
	 */
	public int updateUseStatus(@Param("orderId") long orderId, @Param("oldStatus") short oldStatus, @Param("newStatus") short newStatus);
	

	/**
	 * 查询订单的优惠券使用记录，按照创建时间倒序排序
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午7:29:00
	 * 
	 * @param orderId - 订单ID
	 * @return 返回符合条件的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderCouponRecordPO> listByOrder(@Param("orderId") long orderId);


	/**
	 * @Description:根据日期获取订单使用优惠券的记录
	 * @param date
	 * @return     
	 * @author east
	 * @date 2016年10月12日
	 */
	public List<OrderCouponRecordPO> listByDate(@Param("date") String date);
}
