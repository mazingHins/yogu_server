package com.yogu.services.user.resource.base;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.Validator;
import com.yogu.commons.utils.WordCountUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.services.user.business.service.UserAddressService;
import com.yogu.services.user.resource.param.AddressParam;

/**
 * 收货地址相关接口
 * 
 * @author Hins
 * @version createTime：2015年7月23日 下午4:46:56
 */
@Named
@Path("a")
@Singleton
@Produces("application/json;charset=UTF-8")
public class AddressResource {

	private static final Logger logger = LoggerFactory.getLogger(AddressResource.class);

	@Inject
	private UserAddressService userAddressService;

	/**
	 * 新增/修改收货地址
	 * 
	 * @param params
	 * @return
	 */
	@POST
	@Path("v1/user/address/save.do")
	public RestResult<Long> save(@Valid @BeanParam AddressParam params) {
		long uid = SecurityContext.getUid();
		logger.info("user#address#save |insert or update address | uid: {}, addressId: {}, fullAddres: {}, contacts: {}, "
				+ "phone: {}", uid, params.getAddressId(), params.getFullAddress(), params.getContacts(), params.getPhone());
		validateSave(params);
		
		UserAddress entity = VOUtil.from(params, UserAddress.class);
		entity.setUid(uid);
		return new RestResult<Long>(userAddressService.saveOrUpdate(entity));
	}

	/**
	 * 验证操作收货地址接口参数
	 * 
	 * @author Hins
	 * @date 2015年10月20日 下午5:55:53
	 * 
	 * @param params
	 */
	private static void validateSave(AddressParam params) {
		ParameterUtil.assertNotNull(params, UserMessages.USER_ADDRESS_SAVE_ADDRESS_PARAMS_ILLEGAL());
		if (!Validator.isMobileNo(params.getPhone())) {
			logger.info("userAddress#save | mobile error | mobile: {}", params.getPhone());
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_ADDRESS_SAVE_ADDRESS_PHONE_INVALID());
		}

		if (Validator.containsEmoji(params.getFullAddress()))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_ADDRESS_FULL_ADDRESS_EMOJI_ERROR());

		if (Validator.containsEmoji(params.getContacts()))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_ADDRESS_CONTACTS_EMOJI_ERROR());

		if (WordCountUtils.getWordCount(params.getContacts().trim()) > 12) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_ADDRESS_CONTACTS_LENGTH_ILLEGAL());
		}
	}

	/**
	 * 获取我的收货地址列表
	 * 
	 * @return
	 */
	@GET
	@Path("v1/user/address/list")
	public RestResult<List<UserAddress>> listPage() {
		long uid = SecurityContext.getUid();
		logger.info("user#address#list |get user address list | uid: {}", uid);

		List<UserAddress> list = userAddressService.listMyAddress(uid);
		return new RestResult<List<UserAddress>>(list);
	}

	/**
	 * 删除用户单条收货地址
	 * 
	 * @param addressId 收货地址主键id
	 * @return 成功时返回1;失败是返回0
	 * @author sky 2015-12-08
	 */
	@POST
	@Path("v1/user/address/delete.do")
	public RestResult<Integer> delete(@FormParam("addressId") long addressId) {
		long uid = SecurityContext.getUid();
		logger.info("user#address#delete | 删除用户收货地址 | uid: {}, addressId: {}", uid, addressId);
		int result = userAddressService.delete(uid, addressId);

		logger.info("user#address#delete | 删除用户的收货地址结束 | uid: {}, addressId: {}, result: {}", uid, addressId, (result == 1 ? "success"
				: "failed"));
		return new RestResult<Integer>(result);
	}
}
