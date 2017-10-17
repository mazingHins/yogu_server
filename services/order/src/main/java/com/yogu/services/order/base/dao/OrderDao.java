package com.yogu.services.order.base.dao;

import com.yogu.services.order.base.dao.params.UpdateOrderPayPOJO;
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
	
	/**
	 * 修改订单支付流水，订单状态，支付方式，支付类型，优惠金额，实际支付金额，修改时间<br>
	 * 执行：实际支付金额不执行(totalFee-discountFee)!!因为可能涉及到更换优惠券，已经计算的实付金额很难再用sql语句计算；<br>
	 * 该方法现在主要用于：线上支付订单，且使用优惠金额>订单金额，不需要支付
	 * 
	 * @author Hins
	 * @date 2015年12月31日 下午4:57:08
	 * 
	 * @param pojo - 修改对象
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updatePayNoAndSuccess(UpdateOrderPayPOJO pojo);

	/**
	 * 修改订单支付流水号，支付类型，支付方式，修改时间<br>
	 * 执行：实际支付金额=订单金额-discountFee；优惠金额=discountFee；modify by hins 先这样简单的修改实际支付金额和优惠金额吧！如果以后加上其他优惠再说
	 * 该方法不验证(订单金额-discountFee是否小于0！！！！)
	 * @author Hins
	 * @date 2015年8月18日 下午4:42:32
	 * 
	 * @param pojo - 修改对象
	 * @return 返回操作成功的行数，如果没有修改，返回0
	 */
	public int updatePayNo(UpdateOrderPayPOJO pojo);
	
}
