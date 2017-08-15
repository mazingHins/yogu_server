package com.yogu.services.user.business.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.UserFeedbackPO;

/**
 * mz_user_feedback 表的操作接口
 * 
 * @author JFan 2015-07-24
 */
@TheDataDao
public interface UserFeedbackDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(UserFeedbackPO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(long feedbackId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(UserFeedbackPO po);

	/**
	 * 根据主键读取记录
	 */
	public UserFeedbackPO getById(long feedbackId);

}
