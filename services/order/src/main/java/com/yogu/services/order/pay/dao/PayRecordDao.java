package com.yogu.services.order.pay.dao;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.pay.entry.PayRecordPO;

/**
 * yg_pay_record 表的操作接口
 * 
 * @author Mazing 2015-12-23
 */
@TheDataDao
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
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(PayRecordPO po);

	/**
	 * 根据主键读取记录
	 */
	public PayRecordPO getById(@Param("payId") long payId);
	
	/**
	 * 根据支付编号，获取支付信息
	 * 
	 * @param payNo - 支付编号
	 * @return 符合的记录，若无，返回null
	 */
	public PayRecordPO getByPayNo(@Param("payNo") long payNo);
	

}
