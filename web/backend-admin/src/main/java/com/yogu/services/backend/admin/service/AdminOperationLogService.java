package com.yogu.services.backend.admin.service;

import com.yogu.services.backend.admin.dto.AdminOperationLog;

/**
 * 管理员操作日志 <br>
 * 的操作接口
 * 
 * @author ten 2015-08-05
 */
public interface AdminOperationLogService {

	/**
	 * 保存数据
	 * 
	 * @param dto 对象
	 */
	public void save(AdminOperationLog dto);

}
