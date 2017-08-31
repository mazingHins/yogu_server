package com.yogu.services.user.base.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.services.user.base.dao.UserSettingDao;
import com.yogu.services.user.base.entry.UserSettingPO;

/** 
 * @author Hins 
 * 
 * @version createTime：2015年7月17日 上午10:40:56 
 * 
 * UserSettingDao实现 
 */
@Named
public class UserSettingDaoImpl extends MyBatisDao implements UserSettingDao{
	
	public static final String TABLE_PREFIX = "yg_user.yg_user_setting_";

	@Override
	public int save(UserSettingPO po) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("tableName", getTableName(po.getUid()));
		map.put("entity", po);
		return super.insert("com.yogu.services.user.base.dao.UserSettingDao.insert", map);
	}

	@Override
	public int update(UserSettingPO po) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("tableName", getTableName(po.getUid()));
		map.put("entity", po);
		return super.update("com.yogu.services.user.base.dao.UserSettingDao.update", map);
	}

	@Override
	public UserSettingPO getById(long uid) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("tableName", getTableName(uid));
		map.put("uid", uid);
		return super.get("com.yogu.services.user.base.dao.UserSettingDao.getById", map);
	}
	

	@Override
	public int updateIosPush(long uid, short isPush) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("tableName", getTableName(uid));
		map.put("isPush", isPush);
		map.put("uid", uid);
		
		return super.update("com.yogu.services.user.base.dao.UserSettingDao.updateIosPush", map);
	}
	
	
	 /**
     * 根据uid获取操作的用户设置表
     * @param uid - 用户id
     * @return 操作的表名
     */
    public static String getTableName(long uid) {
		// 分表128个
		int n = (int) (Math.abs(uid) % 16);
		return TABLE_PREFIX + n;
	}


}
