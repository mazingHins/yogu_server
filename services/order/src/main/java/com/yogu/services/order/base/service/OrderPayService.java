package com.yogu.services.order.base.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.service.param.CreateOrderParam;
import com.yogu.services.order.resource.vo.pay.PayVO;

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
	
	/**
	 * 更换订单的支付方式。 若是货到付款订单，则修改订单类型，订单状态为待结单。<br>
	 * 若是在线支付订单，则请求pay域获取调用支付SDK所需信息，获取SDK信息成功后修改支付方式和支付编号
	 * 
	 * @author Hins
	 * @date 2015年8月15日 上午10:17:42
	 * 
	 * @param params - service方法请求参数
	 * @return 若payType是线上支付，返回调用支付平台SKD方法所需参数；其他返回null
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public PayVO changePayMode(long uid, long orderNo, short payMode, String userIp);

}
