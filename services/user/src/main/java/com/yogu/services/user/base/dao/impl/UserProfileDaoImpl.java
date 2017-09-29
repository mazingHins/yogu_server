package com.yogu.services.user.base.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.services.user.base.dao.UserProfileDao;
import com.yogu.services.user.base.entry.UserProfilePO;

/** 
 * @author Hins 
 * 
 * @version createTime：2015年7月17日 上午10:04:54 
 * 
 * UserProfileDao实现 
 */
@Named
public class UserProfileDaoImpl extends MyBatisDao implements UserProfileDao {

	public static final String TABLE_PREFIX = "yg_user.yg_user_profile_";
	public static final int TABLE_HOW = 8;

	@Override
	public int save(UserProfilePO po) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("tableName", getTableName(po.getUid()));
		map.put("entity", po);
		return super.insert("com.yogu.services.user.base.dao.UserProfileDao.insert", map);
	}

	@Override
	public int updateNickName(long uid, String nickName) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("tableName", getTableName(uid));
		map.put("uid", uid);
		map.put("nickname", nickName);
		map.put("updateTime", new Date());
		return super.update("com.yogu.services.user.base.dao.UserProfileDao.updateNickName", map);
	}

	@Override
	public UserProfilePO getById(long uid) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("tableName", getTableName(uid));
		map.put("uid", uid);
		return super.get("com.yogu.services.user.base.dao.UserProfileDao.getById", map);
	}

	@Override
	public int updateProfilePic(long uid, String profilePic) {
		Map<String, Object> map = new HashMap<>(4);
		map.put("tableName", getTableName(uid));
		map.put("uid", uid);
		map.put("profilePic", profilePic);
		map.put("lastUpdateTime", new Date());
		return super.update("com.yogu.services.user.base.dao.UserProfileDao.updateProfilePic", map);
	}

	/**
	 * 根据uid获取操作的用户信息表
	 * @param uid - 用户id
	 * @return 操作的表名
	 */
	public static String getTableName(long uid) {
		// 分表64个
		int n = (int) (Math.abs(uid) % TABLE_HOW);
		return TABLE_PREFIX + n;
	}

	public static void main(String[] args) {
		System.out.println(getTableName(168424));
	}


	//	public static void main(String[] args) {
	//
	//		String tableName = UserDaoImpl.getTableName("86", "18620076093");
	//		System.out.println(tableName);
	//		
	//		String profile = getTableName(10093);
	//		System.out.println(profile);
	//		
	//		
	//	}

}
