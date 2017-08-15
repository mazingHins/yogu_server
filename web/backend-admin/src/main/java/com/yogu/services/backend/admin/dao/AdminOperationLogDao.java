package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.AdminOperationLogPO;

/**
 * mz_admin_operation_log 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface AdminOperationLogDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(AdminOperationLogPO po);

}
