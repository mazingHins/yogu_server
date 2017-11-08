package com.yogu.services.backend.admin.resources.account;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.service.AdminAccountService;

/**
 * 新增/修改管理员帐号
 * @author linyi 2015/6/7.
 */
@Controller
@RequestMapping("admin/account")
@Menu(name="增加/修改管理员", parent = "帐号管理", sequence = 1400000)
public class EditAccountResource {

    private static final Logger logger = LoggerFactory.getLogger(EditAccountResource.class);

    @Autowired
    private AdminAccountService adminAccountService;

    @Autowired
    private UserRemoteService userRemoteService;

    /**
     * 增加管理员主页，xhtml 仅用于展示页面，
     * @return
     */
    @RequestMapping("editAccount.xhtm")
    @MenuResource("增加/修改管理员主页")
    public String index() {
        return ("/account/edit_account");
    }

    /**
     * 增加、修改管理员信息
     *
     * @param account 帐号数据，包括uid、mobileNo、roleId
     * @return result.success=true表示成功
     */
    @RequestMapping(value = "updateAccount.do", method = RequestMethod.POST)
    @MenuResource("增加/修改管理员信息")
    @ResponseBody
    @AdminLog
    public RestResult<Object> updateAccount(@NotNull(message="account不能为空")
                                                @Valid @ModelAttribute AdminAccount account) {
        logger.info("account: " + JSON.toJSONString(account));
//        logger.info("bingdingResult.hasErrors: " + bindingResult.hasErrors());
        RestResult<Object> result = null;

//        if (bindingResult.hasErrors()) {
//            for (ObjectError obj : bindingResult.getAllErrors()) {
//                result.setResult(ResultCode.PARAMETER_ERROR, obj.getDefaultMessage());
//                break;
//            }
//        }
//        else {
            Set<Integer> roles = new HashSet<>();
            roles.add(account.getRoleId());
            adminAccountService.addOrUpdate(account, roles);

            result = new RestResult<>(ResultCode.SUCCESS, "操作成功.");

//        }
        return result;
    }

    /**
     * 读取管理员信息
     * @param uid 管理员ID
     * @return 返回管理员信息
     */
    @RequestMapping("getAdmin")
    @MenuResource("读取管理员信息")
    @ResponseBody
    public RestResult<AdminAccount> getAdmin(long uid) {
        AdminAccount account = adminAccountService.getById(uid);
        RestResult<AdminAccount> result = null;
        if (account == null) {
            result = new RestResult<>(ResultCode.RECORD_NOT_EXIST, "管理员不存在：uid=" + uid);
        }
        else {
            result = new RestResult<>(account);
        }
        return result;
    }

    /**
     * 读取用户信息，比如 uid, nickname
     * @param countryCode 国家代码
     * @param phone 手机帐号
     * @return result.object={uid:...,nickname:...}
     */
    @RequestMapping("queryByPhone")
    @MenuResource("读取帐号信息")
    @ResponseBody
    public RestResult<Map<String, Object>> queryByPhone(String countryCode,
                                                       String phone) {
        Map<String, Object> map = userRemoteService.getUidByPassport(countryCode, phone);
        RestResult<Map<String, Object>> result = null;
        if (map == null) {
            logger.warn("用户ID不存在：" + phone);
            result = new RestResult<>(ResultCode.RECORD_NOT_EXIST, "帐号不存在：" + countryCode + "-" + phone);
        }
        else {
            logger.info("uid=" + map.get("uid"));
            result = new RestResult<>(map);
        }
        return result;
    }
}
