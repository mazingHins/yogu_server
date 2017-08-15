package com.yogu.services.user.base.dao.impl;

import com.yogu.commons.datasource.mybatis.MyBatisReadonlyDao;
import com.yogu.services.user.base.dao.UserProfileReadonlyDao;

import javax.inject.Named;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取从库的 mz_user_profile。
 * @author ten 2016/2/1.
 */
@Named
public class UserProfileReadonlyDaoImpl extends MyBatisReadonlyDao implements UserProfileReadonlyDao {

    /**
     * 根据表名读取一页数据，不会返回null，仅用于后台管理推送
     * @param tableIndex 第几个表，0~UserProfileDaoImpl.TABLE_HOW，如果超出范围，直接返回空
     * @param offset 第几条记录开始
     * @param pageSize 返回多少条记录
     * @return 返回符合条件的数据。如果没有数据，返回 empty list
     * @author ten 2016/2/1
     */
    @Override
    public List<Long> listUidByPage(int tableIndex, int offset, int pageSize) {
        if (tableIndex < 0 || tableIndex >= UserProfileDaoImpl.TABLE_HOW) {
            return Collections.emptyList();
        }
        String tableName = UserProfileDaoImpl.TABLE_PREFIX + tableIndex;
        Map<String, Object> map = new HashMap<>(4);
        map.put("tableName", tableName);
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        return super.list("com.mazing.services.user.base.dao.UserProfileDao.listUidByPage", map);
    }

    @Override
    public List<Map<String, Object>> listPassportByPage(int tableIndex, int offset, int pageSize) {
        if (tableIndex < 0 || tableIndex >= UserProfileDaoImpl.TABLE_HOW) {
            return Collections.emptyList();
        }
        String tableName = UserProfileDaoImpl.TABLE_PREFIX + tableIndex;
        Map<String, Object> map = new HashMap<>(4);
        map.put("tableName", tableName);
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        return super.list("com.mazing.services.user.base.dao.UserProfileDao.listPassportByPage", map);
    }
}
