package com.yogu.services.order.coupon.service;

import java.util.List;

import com.yogu.services.order.coupon.dto.OrderCouponRecord;

/**
 * 订单优惠券使用记录表 <br>
 * 的操作接口
 * 
 * @author Mazing 2015-12-23
 */
public interface OrderCouponRecordService {

	/**
	 * 保存数据
	 * 
	 * @param dto 对象
	 */
	public void save(OrderCouponRecord dto);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param dto - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(OrderCouponRecord dto);

	/**
	 * 根据主键读取记录
	 */
	public OrderCouponRecord getById(long recordId);

	/**
	 * 获取订单锁定中的优惠券使用记录<br>
	 * 若该订单有多条锁定的记录，发送报警邮件，不报异常，并返回首条符合的使用记录
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午7:20:46
	 * 
	 * @param orderId - 订单ID
	 * @return 优惠券使用记录，若无，返回null
	 */
	public OrderCouponRecord getOrderLockRecord(long orderId);
	
	/**
	 * 获取订单使用的优惠券使用记录<br>
	 * 若该订单有多条锁定的记录，发送报警邮件，不报异常，并返回首条符合的使用记录
	 * 
	 * @author Hins
	 * @date 2015年12月24日 下午6:48:58
	 * 
	 * @param orderId
	 * @return 返回已使用优惠记录，若无，返回null
	 */
	public OrderCouponRecord getOrderUseRecord(long orderId);
	
	/**
	 * 将正在锁定的优惠记录修改成使用完成。<br>
	 * 此方法还要加上一个调用coupon域，将优惠券的payNo修复，调用者帮忙查看下是否有加入，如果没加入请反馈，如果加入了，请删除此注释
	 * 
	 * @author Hins
	 * @date 2016年1月4日 下午4:02:21
	 * 
	 * @param orderId - 订单ID
	 * @return 被修改的优惠券ID，若有多条，返回最近
	 */
	public long recordUseSuccess(long orderId);
	
	/**
	 * 当订单取消时, 优惠券需要回滚, 当前记录会被标识为使用失效
	 * 
	 * @param dto
	 * @return
	 */
	public void recordUseFail(OrderCouponRecord dto);
	
	/**
	 * 获取订单的最新优惠券使用记录<br>
	 * 
	 * @author Hins
	 * @date 2016年1月6日 下午6:43:57
	 * 
	 * @param orderId - 订单ID
	 * @return 使用记录，若无，返回null
	 */
	public OrderCouponRecord getNewestRecord(long orderId);
	
	/**
	 * @Description:根据日期获取订单使用优惠券的记录
	 * @param date
	 * @return     
	 * @author east
	 * @date 2016年10月12日
	 */
	public List<OrderCouponRecord> listByDate(String date);

}
