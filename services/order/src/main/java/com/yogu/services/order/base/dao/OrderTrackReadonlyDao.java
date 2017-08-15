package com.yogu.services.order.base.dao;

import java.util.List;

import com.yogu.services.order.base.entry.OrderPO;
import com.yogu.services.order.base.entry.OrderTrackPO;

/**
 * mz_order_track 表的只读接口
 * 
 * @author Hins
 * @date 2015年12月8日 上午10:54:54
 */
public interface OrderTrackReadonlyDao {
	
	/**
	 * 根据主键读取记录
	 */
	public OrderTrackPO getById(long trackId);
	
	/**
	 * 根据订单ID和订单状态查询最后一条订单轨迹(退回可能导致多条)
	 * @param orderId 订单ID
	 * @param status 订单状态列表
	 * @return
	 */
	public OrderTrackPO getTrackByOrderIdAndStatus(long orderId, List<Short> status);

	/**
	 * 根据订单ID 获取最新的订单跟踪信息
	 * @param orderId - 订单ID
	 * @return
	 */
	public OrderTrackPO getLastTrack(long orderId);
	
	/**
	 * 根据订单ID，查询正在进行中的订单轨迹数据，按照创建时间倒序排序
	 * 
	 * @param orderId - 订单ID
	 * @param status - 订单状态列表(不能为空)
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderTrackPO> listInByOrderId(long orderId, List<Short> status);
	
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
	public List<OrderTrackPO> listByOrderIdAndPastStatus(long orderId, List<Short> status);
	
	/**
	 * 用于微信h5页面查看订单列表
	 * @param uid
	 * @return 符合的订单列表
	 * @author jack 2016/4/14
	 */
	public List<OrderPO> listOrderByUid(long uid);
}
