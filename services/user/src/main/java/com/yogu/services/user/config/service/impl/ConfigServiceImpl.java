package com.yogu.services.user.config.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.remote.config.Config;
import com.yogu.core.web.ParameterUtil;
import com.yogu.services.user.config.dao.ConfigDao;
import com.yogu.services.user.config.entry.ConfigPO;
import com.yogu.services.user.config.service.ConfigService;

/**
 * ConfigService 的实现类
 * 
 * @author JFan 2015-07-13
 */
@Named
public class ConfigServiceImpl implements ConfigService {

	@Inject
	private ConfigDao dao;

	@Override
	public void save(Config dto) {
		ParameterUtil.assertNotNull(dto, "参数不能为null");
		ParameterUtil.assertNotBlank(dto.getGroupCode(), "groupCode不能为空");
		ParameterUtil.assertNotBlank(dto.getConfigKey(), "configKey不能为空");
		ParameterUtil.assertNotBlank(dto.getConfigValue(), "configValue不能为空");
		ParameterUtil.assertNotBlank(dto.getRemarks(), "备注不能为空");

		ConfigPO po = dto2po(dto);
		if (po.getCreateTime() == null) {
			po.setCreateTime(new Date());
		}
		dao.save(po);
	}

	@Override
	public int update(Config dto) {
		ConfigPO po = dto2po(dto);
		return dao.update(po);
	}

	@Override
	public Config getById(String configKey, String groupCode) {
		ConfigPO po = dao.getById(configKey, groupCode);
		if (null == po)
			return null;
		return po2dto(po);
	}

	@Override
	public List<Config> listAll() {
		List<ConfigPO> list = dao.listAll();
		List<Config> result = new LinkedList<>();
		if (null != list)
			for (ConfigPO po : list)
				result.add(po2dto(po));
		return result;
	}

	// ####
	// ## private func
	// ####

	public Config po2dto(ConfigPO po) {
		return VOUtil.from(po, Config.class);
	}

	public ConfigPO dto2po(Config dto) {
		return VOUtil.from(dto, ConfigPO.class);
	}

	@Override
	public Map<String, String> getByGroupCode(String groupCode) {
		List<ConfigPO> list = dao.getByGroupCode(groupCode);
		Map<String, String> result = new HashMap<String, String>();
		if (null != list) {
			for (ConfigPO po : list) {
				result.put(po.getConfigKey(), po.getConfigValue());
			}
		}
		return result;
	}

}
