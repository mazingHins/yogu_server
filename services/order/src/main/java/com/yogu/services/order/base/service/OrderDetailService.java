package com.yogu.services.order.base.service;

import java.util.List;
import java.util.Map;

import com.yogu.services.order.base.dto.OrderDetail;
import com.yogu.services.order.base.service.param.PurchaseDetail;
import com.yogu.services.store.GoodsOrderVO;

/**
 * 订单明细表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-17
 */
public interface OrderDetailService {

	/**
	 * 保存数据
	 * 
	 * @param dto对象
	 */
	public void save(OrderDetail dto);

	/**
	 * 根据订单编号获取明细列表
	 * 
	 * @author Hins
	 * @date 2015年8月18日 上午11:02:47
	 * 
	 * @param orderNo
	 * @return 明细列表，若无，返回empty
	 */
	public List<OrderDetail> listByOrderNo(long orderNo);

	/**
	 * 根据订单ID获取明细列表<br>
	 * 此方法不验证订单是否存在
	 * 
	 * @author Hins
	 * @date 2015年11月7日 下午2:52:25
	 * 
	 * @param orderId - 订单ID
	 * @return 明细列表，若无，返回empty
	 */
	public List<OrderDetail> listByOrderId(long orderId);
	
	/**
	 * 创建订单时候，初始化订单详情列表
	 * @param list - 购买列表
	 * @param goodsMap - 美食信息，key：美食key，value：美食对象
	 * @param orderId - 订单id
	 * @author hins
	 * @date 2016年10月2日 下午1:45:54
	 * @return List<OrderDetail>
	 */
	public List<OrderDetail> initOrderDetailByCreateOrder(List<PurchaseDetail> list, Map<Long, GoodsOrderVO> goodsMap, long orderId);

}
