package com.yogu.services.backend.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogu.services.backend.admin.annotation.authority.Authorizable;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.dto.UrlResource;
import com.yogu.services.backend.admin.service.AdminAccountService;
import com.yogu.services.backend.admin.service.MenuService;
import com.yogu.services.backend.admin.util.AppConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 权限验证 service
 * @author ten 2015/10/19.
 */
@Service
public class AuthorizationServiceImpl implements Authorizable {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @Autowired
    private AdminAccountService adminAccountService;

    @Autowired
    private MenuService menuService;

    @Override
    public boolean hasAuthorization(long adminId, String uri) {
        logger.debug("admin#account#hasAuthorization | 权限判断 | adminId: {}, uri: {}", adminId, uri);
        // 注：javax.ws.rs.Path 里可能有正则表达式
        AdminAccount adminAccount = adminAccountService.getById(adminId);
        boolean pass = true;
        if (adminAccount == null) {
            logger.error("admin#account#hasAuthorization | 找不到管理员 | adminId: {}", adminId);
            pass = false;
        }
        else if (adminAccountService.isSuperAdmin(adminId)) {
            logger.debug("admin#account#hasAuthorization | 超级管理员 | adminId: {}", adminId);
        }
        else {
            pass = matchUri(adminId, AppConstants.APP_ID_DEFAULT, uri);
        }
        logger.info("admin#account#hasAuthorization | 权限判断结果 | adminId: {}, uri:{}, pass: {}", adminId, uri, pass);
        return pass;
    }

    /**
     * 返回所有的用户有权限的链接列表
     * @param adminId
     * @param appId
     * @param regxSet 使用了正则匹配的url
     * @return
     */
    private Set<String> getUrlResourceSet(long adminId, int appId, Set<String> regxSet) {
        List<UrlResource> list = menuService.listMenuResourceByAccount(adminId, appId);
        Set<String> set = new HashSet<>(list.size() * 4 / 3);
        for (UrlResource urlResource : list) {
            set.add(urlResource.getUri());
            if (urlResource.getUri().indexOf("{") >= 0) {
                regxSet.add(urlResource.getUri());
            }
        }
        return set;
    }

    /**
     * 判断是否有权限访问 uri
     * @param adminId
     * @param appId
     * @param uri
     * @return
     */
    private boolean matchUri(long adminId, int appId, String uri) {
        Set<String> regxSet = new HashSet<>();
        Set<String> urlSet = getUrlResourceSet(adminId, appId, regxSet);
        if (urlSet.contains(uri)) {
            return true;
        }
        if (regxSet.size() > 0) {
            // 判断正则
            for (String s : regxSet) {
                if (Pattern.matches(s, uri)) {
                    return true;
                }
            }
        }
        return false;
    }
}
