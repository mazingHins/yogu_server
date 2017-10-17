package com.yogu.services.order.pay.dao;

import com.yogu.services.order.pay.entry.PayRecordPO;
import com.yogu.services.order.pay.service.params.UpdataPayRecordMode;

/**
 * yg_pay_record 表的操作接口
 * 
 * @author Mazing 2015-12-23
 */
public interface PayRecordDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(PayRecordPO po);

	// /**
	// * 根据主键删除数据
	// */
	// public int deleteById(@Param("couponId") long couponId);

	/**
	 * 根据主键读取记录
	 */
	public PayRecordPO getById(long payId);
	
	/**
	 * 根据支付编号，获取支付信息
	 * 
	 * @param payNo - 支付编号
	 * @return 符合的记录，若无，返回null
	 */
	public PayRecordPO getByPayNo(long payNo);
	
	
	public PayRecordPO getByOrderNo(long orderNo);
	
	/**
	 * 更换支付方式,是否使用优惠券，实际支付金额，用户支付第三方配送费<br>
	 * 请注意，参数里面的值要全部不能为空（或者默认值0），请谨慎使用 2016-10-10 modify by hins
	 * 
	 * @param params - 请求参数封装
	 * @return 更新行数，为1表示成功
	 */
	public int updatePayMode(UpdataPayRecordMode params);
	
	/**
	 * 更新支付状态
	 * 
	 * @param payId - 支付ID
	 * @param oldStatus - 支付结果状态（原值，查询条件）
	 * @param newStatus - 支付结果状态（修改值）
	 * @param notifyId - 支付平台回调记录ID
	 * @param updateTime - 修改时间
	 * @return 更新行数
	 */
	public int updatePayStatus(long payId, short oldStatus, short newStatus, long notifyId);
	

}
