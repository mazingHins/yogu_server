package com.yogu.services.order.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.pay.entry.PayDiscountRecordPO;

/**
 * yg_pay_discount_record 表的操作接口
 * 
 * @author Mazing 2015-12-28
 */
@TheDataDao
public interface PayDiscountRecordDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(PayDiscountRecordPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("recordId") long recordId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(PayDiscountRecordPO po);

	/**
	 * 根据主键读取记录
	 */
	public PayDiscountRecordPO getById(@Param("recordId") long recordId);

	/**
	 * 查询指定payId和优惠类型的记录，不排序
	 * 
	 * @author Hins
	 * @date 2015年12月28日 下午4:01:38
	 * 
	 * @param payId - 支付记录ID
	 * @param discountType - 优惠类型 1-优惠券；2-活动，参考枚举PayDiscountType
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<PayDiscountRecordPO> listByPayAndType(@Param("payId") long payId, @Param("discountType") short discountType);

	/**
	 * 删除支付优惠记录
	 * 
	 * @author Hins
	 * @date 2015年12月28日 下午5:55:05
	 * 
	 * @param recordId - 记录id
	 * @return
	 */
	public int deleteById(@Param("recordId") long recordId);
	
	/**
	 * 
	 * 
	 * @author Hins
	 * @date 2015年12月29日 上午10:57:46
	 * 
	 * @param payId
	 * @param discountType
	 * @return
	 */
	public List<PayDiscountRecordPO> listByPayIdAndType(@Param("payId") long payId, @Param("discountType") short discountType);
	
}
