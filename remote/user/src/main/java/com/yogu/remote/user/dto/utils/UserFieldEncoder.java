package com.yogu.remote.user.dto.utils;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.encrypt.DES3;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;

/**
 * 
 * 
 * 客户端传递至服务端的业务参数 加密、解密 工具, 加密和解密跟 appKey 相关,
 * 
 * 该工具类 的正常使用 , 应该是验证已登录之后
 * 
 * @author sky
 * @date 2015/08/21 17:58:00
 *
 */
public class UserFieldEncoder {

	private static final String APP_KEY = ConfigGroupConstants.APP_KEY;

	private static UserFieldEncoder instance;
	
	//D5EFAEE2D37B423AAA43AC67B5EDC3FB

	private UserFieldEncoder() {
	}

	public static synchronized UserFieldEncoder getInstance() {
		if (instance == null) {
			instance = new UserFieldEncoder();
		}
		return instance;

	}

	/**
	 * 加密 业务字段
	 * 
	 * @param field 需要加密的相关业务字段
	 * @param errorMsg 出错时的错误提醒语
	 * @return
	 */
	public String encryptField(String field, String errorMsg) {

		String result = null;

		if (!StringUtils.isBlank(field)) {
			try {
				result = DES3.encrypt(field, getKey());
			} catch (Exception e) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMsg);
			}
		}
		return result;
	}

	/**
	 * 解密 业务字段
	 * 
	 * @param field 需要解密的相关业务字段
	 * @param errorMsg 出错时的错误提醒语
	 * @return
	 */
	public String decryptField(String field, String errorMsg) {
		String result = null;

		if (!StringUtils.isBlank(field)) {
			try {
				result = DES3.decrypt(field, getKey());
			} catch (Exception e) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMsg);
			}
		}
		return result;
	}

	public String getKey() {

		String key = ConfigRemoteService.getConfig(APP_KEY, SecurityContext.getBaseParams().getAppKey());
		return key;
	}

}
