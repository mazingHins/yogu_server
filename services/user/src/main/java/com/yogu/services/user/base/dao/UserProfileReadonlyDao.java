package com.yogu.services.user.base.dao;

import java.util.List;
import java.util.Map;

/**
 * 读取从库的 mz_user_profile。
 * @author ten 2016/2/1.
 */
public interface UserProfileReadonlyDao {

    /**
     * 根据表名读取一页uid数据，不会返回null，仅用于后台管理推送
     * @param tableIndex 第几个表，0~UserProfileDaoImpl.TABLE_HOW，如果超出范围，直接返回空
     * @param offset 第几条记录开始
     * @param pageSize 返回多少条记录
     * @return 返回符合条件的uid列表。如果没有数据，返回 empty list
     * @author ten 2016/2/1
     */
    List<Long> listUidByPage(int tableIndex, int offset, int pageSize);

    /**
     * 根据表名读取一页 uid,country_code, passport 数据，不会返回null，仅用于后台管理推送
     * @param tableIndex 第几个表，0~UserProfileDaoImpl.TABLE_HOW，如果超出范围，直接返回空
     * @param offset 第几条记录开始
     * @param pageSize 返回多少条记录
     * @return 返回符合条件的数据。如果没有数据，返回 empty list
     * @author ten 2016/2/2
     */
    List<Map<String, Object>> listPassportByPage(int tableIndex, int offset, int pageSize);
}
