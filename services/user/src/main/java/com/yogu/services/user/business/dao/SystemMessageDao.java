package com.yogu.services.user.business.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.SystemMessagePO;

/**
 * mz_system_message 表的操作接口
 * 
 * @author Mazing 2015-12-16
 */
@TheDataDao
public interface SystemMessageDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(SystemMessagePO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("messageId") long messageId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(SystemMessagePO po);

	/**
	 * 根据主键读取记录
	 */
	public SystemMessagePO getById(@Param("messageId") long messageId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<SystemMessagePO> listAll();
	
	/**
	 * 根据主键读取数据, 获取大于主键的记录
	 * 
	 * @param messageId 消息ID
	 * @return
	 */
	public List<SystemMessagePO> listById(@Param("messageId") long messageId, @Param("expireTime") Date expireTime, @Param("type") Short type);

}
