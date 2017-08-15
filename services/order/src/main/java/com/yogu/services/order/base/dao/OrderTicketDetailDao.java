package com.yogu.services.order.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.core.enums.TicketStatus;
import com.yogu.services.order.base.entry.OrderTicketDetailPO;

/**
 * mz_order_ticket_detail 表的操作接口
 * 
 * @author Mazing 2017-02-24
 */
@TheDataDao
public interface OrderTicketDetailDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(OrderTicketDetailPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("orderId") long orderId, @Param("ticketId") long ticketId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(OrderTicketDetailPO po);

	/**
	 * 根据主键读取记录
	 */
	public OrderTicketDetailPO getById(@Param("orderId") long orderId, @Param("ticketId") long ticketId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OrderTicketDetailPO> listAll();
	
	/**
	 * 批量插入
	 * @param list    
	 * @author east
	 * @date 2017年2月24日 下午3:45:20
	 */
	public void saveAll(@Param("list") List<OrderTicketDetailPO> list);

	/**
	 * 根据订单id获取所有的订单明细
	 * @param orderId
	 * @return    
	 * @author east
	 * @date 2017年3月2日 下午6:41:13
	 */
	public List<OrderTicketDetailPO> listAllByOrderId(@Param("orderId") long orderId);
	
	/**
	 * 通过uid & ticketRuleId 查询 用户已购买的该ticket记录(ticket状态值 > {@link TicketStatus.PREBUY})
	 * @param uid  购买者uid
	 * @param ticketRuleId 票规则id
	 * @return 
	 */
	public List<OrderTicketDetailPO> listAlreadyBuyByUidAndTicketRuleId(@Param("uid") long uid, @Param("ticketRuleId") long ticketRuleId);

	/**
	 * 根据用户和订单状态获取他的订单明细列表
	 * @param uid
	 * @param statusList -表示过滤某些状态，比如stauts=10，表示过滤未付款的数据
	 * @param pageIndex
	 * @param pageSize
	 * @return    
	 * @author east
	 * @date 2017年3月10日 上午11:36:08
	 */
	public List<OrderTicketDetailPO> listAllByUid(@Param("uid")long uid, @Param("statusList") List<Short> statusList, @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

	/**
	 * 更新订单明细的订单状态
	 * @param orderId
	 * @param ticketId
	 * @param status    
	 * @author east
	 * @date 2017年3月17日 下午7:03:08
	 */
	public void updateOrderStatus(@Param("orderId") long orderId, @Param("ticketId") long ticketId, @Param("oldStatus") short oldStatus, @Param("newStatus") short newStatus);

}

