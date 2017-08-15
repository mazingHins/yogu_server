package com.yogu.services.backend.admin.service.impl;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.backend.admin.dao.AppDefineDao;
import com.yogu.services.backend.admin.dto.AppDefine;
import com.yogu.services.backend.admin.entry.AppDefinePO;
import com.yogu.services.backend.admin.service.AppDefineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * AppDefineService 的实现类
 * 
 * @author ten 2015-08-05
 */
@Service
public class AppDefineServiceImpl implements AppDefineService {

	@Autowired
	private AppDefineDao dao;

	@Override
	public void save(AppDefine dto) {
		AppDefinePO po = dto2po(dto);
		dao.save(po);
	}

	@Override
	public int update(AppDefine dto) {
		AppDefinePO po = dto2po(dto);
		return dao.update(po);
	}

	@Override
	public AppDefine getById(int appId) {
		AppDefinePO po = dao.getById(appId);
		if (null == po)
			return null;
		return po2dto(po);
	}

	@Override
	public List<AppDefine> listAll() {
		List<AppDefinePO> list = dao.listAll();
		List<AppDefine> result = new ArrayList<AppDefine>();
		if (null != list)
			for(AppDefinePO po : list)
				result.add(po2dto(po));
		return result;
	}

	// ####
	// ## private func
	// ####

	public AppDefine po2dto(AppDefinePO po) {
		return VOUtil.from(po, AppDefine.class);
	}

	public AppDefinePO dto2po(AppDefine dto) {
		return VOUtil.from(dto, AppDefinePO.class);
	}

}
