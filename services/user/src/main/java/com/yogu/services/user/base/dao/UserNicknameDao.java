package com.yogu.services.user.base.dao;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.base.entry.UserNicknamePO;

/**
 * mz_user_nickname 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface UserNicknameDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(UserNicknamePO po);

	/**
	 * 修改昵称（只有昵称需要同步）
	 */
	public int updateNickname(@Param("uid") long uid, @Param("nickname") String nickname);

	/**
	 * 根据主键读取记录
	 */
	public UserNicknamePO getById(@Param("uid") long uid);

	/**
	 * 根据IMID读取记录
	 */
	public UserNicknamePO getByImid(@Param("imId") long imId);

	/**
	 * 检查昵称是否已经存在了<br>
	 * 外部请确认内容的正确性，比如：not null、trim
	 * 
	 * @return 不存在则返回null
	 */
	public UserNicknamePO actuallyExists(@Param("nickname") String nickname);

}
