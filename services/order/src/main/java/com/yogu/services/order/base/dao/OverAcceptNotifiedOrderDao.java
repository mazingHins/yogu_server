package com.yogu.services.order.base.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.order.base.entry.OverAcceptNotifiedOrderPO;

/**
 * mz_over_accept_notified_order 表的操作接口
 * 
 * @author Mazing 2015-10-19
 */
@TheDataDao
public interface OverAcceptNotifiedOrderDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(OverAcceptNotifiedOrderPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("orderNo") long orderNo);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(OverAcceptNotifiedOrderPO po);

	/**
	 * 根据主键读取记录
	 */
	public OverAcceptNotifiedOrderPO getById(@Param("orderNo") long orderNo);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<OverAcceptNotifiedOrderPO> listAll();

}
