package com.yogu.services.user.base.dao;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.base.entry.UserInvitePO;

/**
 * mz_user_invite 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface UserInviteDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 * 
	 * @return 更新的行数
	 */
	public void save(UserInvitePO po);

	/**
	 * 根据通行证读取记录
	 * 
	 * @param countryCode - 国家代码，比如+86表示中国
	 * @param passport - 手机号码或email
	 * @return 如果有记录返回UserPO，否则返回null
	 */
	UserInvitePO getByInvite(@Param("inviteCode")String inviteCode);
	
	UserInvitePO getByUid(@Param("uid")long uid);


}
