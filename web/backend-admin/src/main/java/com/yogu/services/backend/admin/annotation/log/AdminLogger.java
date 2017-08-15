package com.yogu.services.backend.admin.annotation.log;

import com.yogu.services.backend.admin.dto.AdminOperationLog;

/**
 * 管理员日志记录
 * @author ten 2015/10/17.
 */
public interface AdminLogger {

    /**
     * 保存管理员日志
     * @param log 日志内容
     */
    void saveAdminLog(AdminOperationLog log);
}
