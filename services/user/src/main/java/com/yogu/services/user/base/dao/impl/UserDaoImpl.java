package com.yogu.services.user.base.dao.impl;

import com.yogu.commons.datasource.mybatis.MyBatisDao;
import com.yogu.services.user.base.dao.UserDao;
import com.yogu.services.user.base.entry.UserPO;

import javax.inject.Named;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hins
 * 
 * @version createTime：2015年7月17日 上午9:58:20
 * 
 *          UserDao实现
 */
@Named
public class UserDaoImpl extends MyBatisDao implements UserDao {

	public static final String TABLE_PREFIX = "yg_user.yg_user_";
	public static final int TABLE_HOW = 8;

	@Override
	public int save(UserPO po) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("tableName", getTableName(po.getCountryCode(), po.getPassport()));
		map.put("entity", po);
		return super.insert("com.yogu.services.user.base.dao.UserDao.insert", map);
	}

	@Override
	public UserPO getByPassport(String countryCode, String mobile) {
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("tableName", getTableName(countryCode, mobile));
		map.put("countryCode", countryCode);
		map.put("passport", mobile);
		return super.get("com.yogu.services.user.base.dao.UserDao.getByPassport", map);
	}

	/**
	 * 根据国家区号和通行证获取操作的表
	 * 
	 * @param countryCode 国家区号
	 * @param passport 通行证
	 * @return
	 */
	public static String getTableName(String countryCode, String passport) {
		String str = countryCode + "|" + passport;
		// 分表8个
		int n = Math.abs(str.hashCode()) % TABLE_HOW;
		return TABLE_PREFIX + n;
	}
	
	public static void main(String[] args) {
		System.out.println(getTableName("86", "13926426236"));
	}
	
	/**
	 * 通过表名获取所有user
	 * 
	 * @author sky
	 * @date 2015-08-31 10:48:20
	 * @param tableName
	 * @return
	 */
	public List<UserPO> getAll(String tableName){
		Map<String, Object> map = new HashMap<>(1);
		map.put("tableName",tableName);
		return super.list("com.yogu.services.user.base.dao.UserDao.getAll", map);
	}

	@Override
	public int updateUserStatus(long uid, String countryCode, String passport, short oldStatus, short newStatus) {
		// 更新帐号状态
		Map<String, Object> map = new HashMap<String, Object>(4);
		map.put("tableName", getTableName(countryCode, passport));
		map.put("uid", uid);
		map.put("oldStatus", Short.valueOf(oldStatus));
		map.put("newStatus", Short.valueOf(newStatus));
		return super.update("com.yogu.services.user.base.dao.UserDao.updateUserStatus", map);
	}

	/**
	 * 分表操作，临时使用的，其他地方请勿调用
	 */
	@Deprecated
	public List<UserPO> listAll() {
		List<UserPO> list = new ArrayList<>();
		for (int i = 0; i < 128; i++) {
			List<UserPO> all = getAll(TABLE_PREFIX + i);
			if (null != all && 0 < all.size())
				list.addAll(all);
		}
		return list;
	}

	@Override
	public int udpatePasswordByUid(long uid, String countryCode, String mobile, String newPassword) {
		Map<String, Object> map = new HashMap<>(3);
		map.put("tableName", getTableName(countryCode, mobile));
		map.put("uid", uid);
		map.put("password", newPassword);
		return super.update("com.yogu.services.user.base.dao.UserDao.udpatePasswordByUid", map);
	}

	@Override
	public short getUserStatusById(long uid, String countryCode, String passport) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("tableName", getTableName(countryCode, passport));
		map.put("uid", uid);
		return super.get("com.yogu.services.user.base.dao.UserDao.getUserStatusById", map);
	}

}
