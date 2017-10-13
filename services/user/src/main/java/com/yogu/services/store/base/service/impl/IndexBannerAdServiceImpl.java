package com.yogu.services.store.base.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.services.store.base.dao.IndexBannerAdDao;
import com.yogu.services.store.base.dto.IndexBannerAd;
import com.yogu.services.store.base.entry.IndexBannerAdPO;
import com.yogu.services.store.base.service.IndexBannerAdService;

@Named
public class IndexBannerAdServiceImpl implements IndexBannerAdService {

	@Inject
	private IndexBannerAdDao indexBannerAdDao;
	
	@Override
	public List<IndexBannerAd> listEffectivve(int size) {
		if (size < 1) {
			return Collections.emptyList();
		}

		List<IndexBannerAdPO> list = indexBannerAdDao.listByStatus(BooleanConstants.TRUE, size, 0);
		if (list.isEmpty()) {
			return Collections.emptyList();
		}

		return VOUtil.fromList(list, IndexBannerAd.class);
	}
	
	

}
