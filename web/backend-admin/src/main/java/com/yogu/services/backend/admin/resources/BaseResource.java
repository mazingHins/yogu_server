package com.yogu.services.backend.admin.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.service.AdminAccountService;

/**
 * 共有的基本接口
 * 
 * @author jfan 2016年7月15日 下午1:06:23
 */
@Controller
@RequestMapping("/admin/base/")
public class BaseResource {

	@Autowired
	private AdminAccountService adminAccountService;

	/**
	 * 根据uid查询用户信息
	 */
	@ResponseBody
	@RequestMapping("findAdminByIds")
	public RestResult<Map<Long, AdminAccount>> findAdminByIds(String uids) {
		// uids --> Set<long>
		long[] tmp = ParameterUtil.str2longs(uids);
		Set<Long> ids = new HashSet<>();
		if (ArrayUtils.isNotEmpty(tmp))
			for (long l : tmp)
				ids.add(l);

		Map<Long, AdminAccount> result = new HashMap<>();

		// load
		if (CollectionUtils.isNotEmpty(ids))
			for (Long uid : ids) {
				AdminAccount account = adminAccountService.getById(uid);
				result.put(uid, account);// account 有可能为null
			}

		return new RestResult<>(result);
	}

}
