package com.yogu.services.order.coupon.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.coupon.entry.CouponPO;

/**
 * mz_coupon 表的操作接口
 * 
 * @author Mazing 2015-12-23
 */
@TheDataDao
public interface CouponDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(CouponPO po);

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
	public int update(CouponPO po);

	/**
	 * 根据主键读取记录
	 */
	public CouponPO getById(@Param("couponId") long couponId);

}
