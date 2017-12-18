package com.yogu.services.order.base.service;

import java.util.List;

import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.resource.vo.order.AdminOrderVO;
import com.yogu.services.order.resource.vo.order.UserOrderVO;

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
	
	
	public List<Order> listOrderByUid(long uid, int pageNo, int pageSize);
	
	/**
	 * 用户查看订单详情信息
	 * 返回订单信息，订单美食列表，配送费，商家基础信息
	 * 
	 * @param uid - 用户ID
	 * @param orderNo - 订单编号
	 * @return
	 */
	public UserOrderVO userOrdeDetail(long uid, long orderNo);
	
	/**
	 * 分页获取所有订单，admin使用<br>
	 * 该方法只查询非米星付的订单，米星付和其他的订单分开查询和展示 2016/7/13 add by hins
	 *
	 * @author ben
	 * @date 2015年11月26日 下午5:29:24
	 * @param uid 下单的用户ID，可以为 0
	 * @param storeId 餐厅的ID，可以为0，和uid可以同时生效
	 * @param page
	 * @param pageSize
	 * @return
	 * @mofified by ten 2015/12/17
	 */
	List<AdminOrderVO> listAllOrders(long uid, long storeId, int page, int pageSize);

}
