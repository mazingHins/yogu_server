package com.yogu.services.user.resource.api;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.user.business.service.UserAddressService;

/**
 * 用户收货地址相关的 本地API，提供给集群内部使用的
 * @author Hins
 * @date 2015年7月25日 下午2:27:18
 */
@Named
@Path("api")
@Singleton
@Produces("application/json;charset=UTF-8")
public class LocalAddressApiResource {

	private static final Logger logger = LoggerFactory.getLogger(LocalAddressApiResource.class);

	@Inject
	private UserAddressService userAddressService;
	
	@Inject
	private UserService userService;
	
	@GET
	@Path("v1/user/address/list/{uid}")
	public RestResult<List<UserAddress>> listMyAddress(@PathParam("uid") long uid) {
		logger.info("api#user#listMyAddress |get user address list  | uid: {}", uid);

		List<UserAddress> list = userAddressService.listMyAddress(uid);
		return new RestResult<>(list);
	}

	@GET
	@Path("v1/user/address/id/{addressId:[0-9]+}")
	public RestResult<UserAddress> getUserAddress(@PathParam("addressId") long addressId) {
		logger.info("api#user#getUserAddress |get user address information  | addressId: {}", addressId);

		UserAddress address = userAddressService.getById(addressId);
		return new RestResult<>(address);
	}
	
	/**
	 * 根据用户id、地址id获取UserAddress信息<br>
	 * 方法会校验用户是否存在
	 * 
	 * @param addressId
	 * @param uid
	 * @author hins
	 * @date 2016年10月1日 上午10:29:04
	 * @return RestResult<UserAddress>
	 */
	@GET
	@Path("v1/user/address/addressId/{addressId:[0-9]+}/uid/{uid:[0-9]+}")
	public RestResult<UserAddress> getUserAddress(@PathParam("addressId") long addressId, @PathParam("uid") long uid) {
		logger.info("api#user#getUserAddress |get user address information  | addressId: {}, uid: {}", addressId, uid);

		UserProfile user = userService.getUserProfile(uid);
		if (user == null) {
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "not found.");
		}
		
		UserAddress address = userAddressService.getById(addressId);
		if (address == null || address.getUid() != uid) {
			logger.info("api#user#getUserAddress | 地址不存在/该地址不属于用户 | addressId: {}, uid: {}", addressId, uid);
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "not found.");
		}
		return new RestResult<>(address);
	}


	/**
	 * 根据用户id、地址id，获取用户信息和地址信息<br>
	 * 此方法暂时只用于创建订单时，一次性调用user域获取用户信息和地址信息
	 * 
	 * @param addressId - 地址id
	 * @param uid - 用户id
	 * @author hins
	 * @date 2016年10月2日 下午2:33:23
	 * @return RestResult<UserAndAddress>
	 */
	@GET
	@Path("v1/user/address/uid/{uid:[0-9]+}/addressId/{addressId:[0-9]+}")
	public RestResult<UserAndAddress> getUserAndAddress(@PathParam("addressId") long addressId, @PathParam("uid") long uid) {
		logger.info("api#user#getUserAddress |get user address information  | addressId: {}, uid: {}", addressId, uid);

		UserProfile user = userService.getUserProfile(uid);
		if (user == null) {
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "not found.");
		}
		
		UserAddress address = userAddressService.getById(addressId);
		if (address == null || address.getUid() != uid) {
			logger.info("api#user#getUserAddress | 地址不存在/该地址不属于用户 | addressId: {}, uid: {}", addressId, uid);
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "not found.");
		}
		
		UserAndAddress result = new UserAndAddress();
		result.setUserAddress(address);
		result.setUserProfile(user);
		return new RestResult<>(result);
	}

}
