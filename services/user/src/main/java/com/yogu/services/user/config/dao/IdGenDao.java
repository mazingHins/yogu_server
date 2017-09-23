package com.yogu.services.user.config.dao;

import java.util.List;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.config.entry.IdGenPO;

/**
 * mz_id_gen 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface IdGenDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(IdGenPO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(String idName);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(IdGenPO po);

	/**
	 * 根据主键读取记录
	 */
	public IdGenPO getById(String idName);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<IdGenPO> listAll();

	/**
	 * 刷新起始位（加上步长）
	 */
	public int updateNextStart(String idName);

}
