package com.yogu.services.store.business.dao;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.business.entry.StorePO;

/**
 * yg_store 表的操作接口
 *
 */
@TheDataDao
public interface StoreDao {
	
	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(StorePO po);

	/**
	 * 根据主键读取记录
	 */
	public StorePO getById(@Param("storeId") long storeId);
	
	
	/**
	 * 根据店主读取记录<br>
	 * 如果有多个，获取首个
	 */
	public StorePO getByUid(@Param("uid") long uid);
	
}
