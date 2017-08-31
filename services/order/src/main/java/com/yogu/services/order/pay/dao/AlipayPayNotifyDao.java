package com.yogu.services.order.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.pay.entry.AlipayPayNotifyPO;

/**
 * yg_alipay_pay_notify 表的操作接口
 * 
 * @author JFan 2015-08-06
 */
@TheDataDao
public interface AlipayPayNotifyDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(AlipayPayNotifyPO po);

	/**
	 * 根据主键删除数据
	public int deleteById(long notifyId);
	 */

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(AlipayPayNotifyPO po);

	/**
	 * 根据主键读取记录
	 */
	public AlipayPayNotifyPO getById(long notifyId);

	/**
	 * 根据支付流水号读取记录
	 * @param payId - 支付记录ID
	 * @return 记录，若不存在，返回null
	 */
	public AlipayPayNotifyPO getByPayId(long payId);
	
	/**
	 * 根据支付记录ID，批量获取通知结果，该方法不排序
	 * @author Hins
	 * @date 2015年9月16日 下午12:11:21
	 * 
	 * @param payIds - 支付记录ID集
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<AlipayPayNotifyPO> listByPayIds(@Param("list") List<Long> payIds);
	
	/**
	 * 根据支付宝交易号，获取记录。
	 * 前置条件：支付宝交易号是唯一的
	 * @author Hins
	 * @date 2015年9月25日 上午10:35:29
	 * 
	 * @param tradeNo - 支付宝交易号
	 * @return 记录，若不存在，返回null
	 */
	public AlipayPayNotifyPO getByTradeNo(@Param("tradeNo") String tradeNo);

}
