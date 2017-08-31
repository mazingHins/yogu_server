package com.yogu.services.order.pay.service;

import java.util.List;

import com.yogu.services.order.pay.dto.AlipayPayNotify;

/**
 * 支付宝支付回调通知记录表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-08-06
 */
public interface AlipayPayNotifyService {

	/**
	 * 根据主键读取记录
	 */
	public AlipayPayNotify getById(long notifyId);
	
	/**
	 * 根据多条支付记录id，获取支付宝通知列表<br>
	 * 该方法支持批量查询，不验证支付记录是否合法并存在
	 * 
	 * @author Hins
	 * @date 2015年9月14日 下午4:47:56
	 * 
	 * @param payIds - 支付记录idList
	 * @return 返回支付记录数据，如果没有数据，返回emtpy list
	 */
	public List<AlipayPayNotify> listByPayIds(List<Long> payIds);
	
	/**
	 * 新增/修改支付宝成功回调记录
	 * @param notify
	 */
	public long successNotify(AlipayPayNotify notify);
	
	/**
	 * 创建待支付的支付宝回调
	 * @param notify
	 * @return 回调记录ID
	 */
	public long createNotify(AlipayPayNotify notify);
	
	/**
	 * 根据支付宝交易号读取记录
	 */
	public AlipayPayNotify getByTradeNo(String tradeNo);

}
