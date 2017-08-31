package com.yogu.services.user.base.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.yogu.commons.utils.VOUtil;
import com.yogu.services.user.base.dao.UserInviteDao;
import com.yogu.services.user.base.dto.UserInvite;
import com.yogu.services.user.base.entry.UserInvitePO;
import com.yogu.services.user.base.service.UserInviteService;

@Named
public class UserInviteServiceImpl implements UserInviteService {
	
	@Inject
	private UserInviteDao userInviteDao;

	@Override
	public UserInvite getById(long uid) {
		UserInvitePO po = userInviteDao.getByUid(uid);
		if(null == po){
			return null;
		}
		
		return VOUtil.from(po, UserInvite.class);
	}

}
