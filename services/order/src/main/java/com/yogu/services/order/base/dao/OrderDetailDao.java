package com.yogu.services.order.base.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.base.entry.OrderDetailPO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * mz_order_detail 表的操作接口
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
	public OrderDetailPO getById(long objectId, long orderId);

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
	
	/**
	 * 根据下单id、美食key，分页对应的购买获取记录。结果无序<br>
	 * 该方法会跟订单表关联，暂时用于下单时，获取用户是否购买过此商品<br>
	 * 具体原因：2016-12-06 add by hins。 下午14:30，楼上同事打算在米星店指定一个美食，每个用户只能订购一份，并且下班前就要这个功能。所以直接写死在代码上，等活动完毕后，再关闭吧
	 * 
	 * @param uid - 用户id
	 * @param dishKey - 美食key
	 * @param offset - 起始记录
	 * @param pageSize - 结束记录
	 * @author hins
	 * @date 2016年12月6日 下午3:12:38
	 * @return 符合的记录，若无，返回empty list
	 */
	public List<OrderDetailPO> listByUidDishKey(@Param("uid") long uid, @Param("dishKey") long dishKey, @Param("status") List<Short> status);

}
