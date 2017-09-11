package com.yogu.services.user.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.config.entry.ConfigPO;

/**
 * mz_config 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface ConfigDao {

	/**
	 * 保存数据，如果主键冲突，就更新数据
	 * 
	 * @param po 对象
	 */
	public void save(ConfigPO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(String configKey, String groupCode);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(ConfigPO po);

	/**
	 * 根据主键读取记录
	 */
	public ConfigPO getById(String configKey, String groupCode);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<ConfigPO> listAll();
	
	/**
	 * 根据分组读取记录
	 * 
	 * @return 返回分组的数据, 如果没有数据, 返回empty list
	 */
	public List<ConfigPO> getByGroupCode(@Param("groupCode") String groupCode);
}
