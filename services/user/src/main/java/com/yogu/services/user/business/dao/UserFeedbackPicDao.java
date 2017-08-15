package com.yogu.services.user.business.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.UserFeedbackPicPO;

/**
 * mz_user_feedback_pic 表的操作接口
 * 
 * @author JFan 2015-07-24
 */
@TheDataDao
public interface UserFeedbackPicDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(UserFeedbackPicPO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(long pid);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(UserFeedbackPicPO po);

	/**
	 * 根据主键读取记录
	 */
	public UserFeedbackPicPO getById(long pid);

}
