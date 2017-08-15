package com.yogu.services.order.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.base.entry.OrderTrackPO;

/**
 * mz_order_track 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface OrderTrackDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(OrderTrackPO po);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(OrderTrackPO po);

	/**
	 * 根据主键读取记录
	 */
	public OrderTrackPO getById(long trackId);

	/**
	 * 根据订单ID，查询正在进行中的订单轨迹数据<br>
	 * 此方法查询主库
	 * 
	 * modify by hins 内容：增加注释，按照创建时间顺序排序
	 * 
	 * @param orderId - 订单ID
	 * @param status - 订单状态列表(不能为空)
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	@Deprecated
	public List<OrderTrackPO> listInByOrderId(@Param("orderId")long orderId, @Param("status")List<Short> status);
	
	/**
	 * 根据订单ID，查询所有订单轨迹数据 
	 * @author ben
	 * @date 2015年11月18日 下午4:48:02 
	 * @param orderId － 订单ID
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderTrackPO> listAllByOrderId(@Param("orderId")long orderId);
	
	/**
	 * 根据订单ID，上一个订单状态查询订单轨迹数据，按照创建时间顺序排序
	 * 
	 * @author Hins
	 * @date 2015年12月4日 下午5:14:27
	 * 
	 * @param orderId - 订单ID
	 * @param status - 上一订单状态列表
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderTrackPO> listByOrderIdAndPastStatus(@Param("orderId")long orderId, @Param("status")List<Short> status);
	
	/**
	 * 根据订单ID 获取最新的订单跟踪信息<br>
	 * 此方法查询主库
	 * @param orderId - 订单ID
	 * @return
	 */
	@Deprecated
	public OrderTrackPO getLastTrack(long orderId);
	
	
	/**
	 * 根据订单ID和订单状态查询最后一条订单轨迹(退回可能导致多条)<br>
	 * 此方法查询主库
	 * 
	 * @param orderId 订单ID
	 * @param status 订单状态列表
	 * @return
	 */
	@Deprecated
	public OrderTrackPO getTrackByOrderIdAndStatus(@Param("orderId")long orderId, @Param("status")List<Short> status);

}
