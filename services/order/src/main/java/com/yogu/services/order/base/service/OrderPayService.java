package com.yogu.services.order.base.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.service.param.CreateOrderParam;

public interface OrderPayService {
	
	/**
	 * 生成订单 1. 验证收货地址 2. 验证订单明细信息，并计算美食费用 3. 验证配送时间是否在门店配送段范围内 4. 验证配送范围信息 5. 锁住库存 6. 保存订单，订单明细，订单轨迹记录。 若出现异常，则释放库存
	 * 
	 * @author Hins
	 * @date 2015年8月17日 上午12:22:05
	 * 
	 * @param params - 订单内容
	 * @param uid - 下单人
	 * @return 订单dto
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Order createOrder(CreateOrderParam params, long uid);

}
