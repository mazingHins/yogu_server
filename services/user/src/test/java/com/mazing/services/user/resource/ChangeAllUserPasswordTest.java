package com.mazing.services.user.resource;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.yogu.core.test.BaseServiceTest;
import com.yogu.core.web.encrypt.PasswordHelper;
import com.yogu.services.user.base.dao.UserDao;
import com.yogu.services.user.base.entry.UserPO;

public class ChangeAllUserPasswordTest extends BaseServiceTest {
	@Inject
	UserDao userDao;

	public void doChange() {

		String all_password = "12345678";
		for (int i = 0; i < 64; i++) {
			String tableName = "mz_user_" + i;
			System.out.println("table=" + tableName);

			List<UserPO> list = userDao.getAll(tableName);
			for (UserPO userPO : list) {

				// String mobile = userPO.getPassport();
				// String encrypted_old_password = userPO.getPassword();
				String salt = PasswordHelper.salt();
				String new_password = PasswordHelper.encrypt(all_password, salt);

				System.out.println("salt=" + salt + ", new password =" + new_password);
				
				userPO.setPassword(new_password);
				
				//userDao.updatePassword(userPO);
			}
		}

	}

	

}
