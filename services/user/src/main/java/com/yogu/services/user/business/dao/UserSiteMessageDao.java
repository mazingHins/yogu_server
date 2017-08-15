package com.yogu.services.user.business.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.UserSiteMessagePO;

/**
 * mz_user_site_message 表的操作接口
 * 
 * @author Mazing 2015-12-16
 */
@TheDataDao
public interface UserSiteMessageDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(UserSiteMessagePO po);

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
	public int update(UserSiteMessagePO po);

	/**
	 * 根据主键读取记录
	 */
	public UserSiteMessagePO getById(@Param("messageId") long messageId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<UserSiteMessagePO> listAll();
	
	/**
	 * 查询用户消息, 根据用户ID,类型和创建时间
	 * 
	 * @param uid 用户ID
	 * @param type 消息类型
	 * @param createTime 创建时间
	 * @return
	 * 
	 * @OrderBy create_time desc,
	 * 			message_id  desc
	 */
	public List<UserSiteMessagePO> listAllByUidAndType(@Param("uid") long uid, @Param("type") List<Short> type, @Param("createTime") Date createTime, @Param("pageSize") int pageSize);
	
	/**
	 * 查询用户消息, 根据用户ID,类型
	 * 
	 * @param uid 用户ID
	 * @param type 消息类型
	 * @param pageSize 每页大小
	 * @return
	 * @orderBy message_id desc
	 */
	public List<UserSiteMessagePO> listAllByUidAndTypeOrderById (@Param("uid") long uid, @Param("type") List<Short> type, @Param("pageSize") int pageSize);
	
	/**
	 * 获取用户未读站内信数量
	 * 
	 * @param uid 用户ID
	 * @param type 消息类型
	 * @param createTime 创建时间
	 * @param status 是否已读
	 * @return
	 */
	public int getUnReadCount(@Param("uid") long uid, @Param("type") List<Short> type, @Param("createTime") Date createTime, @Param("status") short status);
	
	/**
	 * 批量更新用户站内消息状态 
	 * 
	 * @param uid 用户ID
	 * @param type 消息类型
	 * @param createTime 创建时间
	 * @param status 更新后状态
	 * @param prevStatus 更新前状态
	 * @return
	 */
	public int updateMsgStatus(@Param("uid") long uid, @Param("type") List<Short> type, @Param("createTime") Date createTime, @Param("status") short status, @Param("prevStatus") short prevStatus); 
}
