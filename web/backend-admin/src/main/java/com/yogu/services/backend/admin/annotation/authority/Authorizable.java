package com.yogu.services.backend.admin.annotation.authority;

/**
 * 权限验证接口
 * @author ten 2015/10/17.
 */
public interface Authorizable {

    /**
     * 是否有授权
     * @param adminId 管理员ID
     * @param uri 当前访问的url
     * @return true=有授权
     */
    boolean hasAuthorization(long adminId, String uri);
}
