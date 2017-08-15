package com.yogu.services.user.business.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.CacheExtendService;
import com.yogu.commons.cache.annotation.CacheClean;
import com.yogu.commons.cache.annotation.CacheCleanExpr;
import com.yogu.commons.cache.annotation.Cacher;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.services.user.UserCacheKey;
import com.yogu.services.user.business.dao.UserAddressDao;
import com.yogu.services.user.business.entry.UserAddressPO;
import com.yogu.services.user.business.service.UserAddressService;
import com.yogu.services.utils.LoginInfoUtil;

/**
 * UserAddressService 的实现类
 * 
 * @author JFan 2015-07-13
 */
@Named
public class UserAddressServiceImpl implements UserAddressService {

	private static final Logger logger = LoggerFactory.getLogger(UserAddressServiceImpl.class);

	@Inject
	private UserAddressDao dao;

	@Inject
	private IdGenRemoteService idGenRemoteService;

	@Inject
	private CacheExtendService cacheExtendService;

	@Override
	@Cacher(value = UserCacheKey.ADDRESS_PREFIX, time = UserCacheKey.TIME_FOREVER)
	public UserAddress getById(long addressId) {
		UserAddressPO po = dao.getById(addressId);
		if (null == po)
			return null;
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public UserAddress po2dto(UserAddressPO po) {
		return VOUtil.from(po, UserAddress.class);
	}

	public UserAddressPO dto2po(UserAddress dto) {
		return VOUtil.from(dto, UserAddressPO.class);
	}

	@Override
	@CacheClean(@CacheCleanExpr(value = UserCacheKey.ADDRESS_LIST_PREFIX, expr = "0.uid"))
	public long saveOrUpdate(UserAddress dto) {
		validateSave(dto);
		if (dto.getAddressId() == 0) {// 执行新增
			// 获取地址id
			long addressId = idGenRemoteService.getNextUserPublicId();
			dto.setAddressId(addressId);
			dto.setCreateTime(new Date());
			dao.save(dto2po(dto));
		} else {
			UserAddressPO po = dao.getById(dto.getAddressId());
			if (po == null || po.getUid() != dto.getUid()) {// 非本人
				logger.error("address#service#saveOrUpdate | address not exist | uid: {}, addressId: {}", dto.getUid(), dto.getAddressId());
				throw new ServiceException(OrderErrorCode.USER_ADDRESS_NOT_EXIST, UserMessages.USER_SERVICE_SAVE_ADDRESS_ADDRESS_NOT_EXIST());
			}
			update(dto);
		}
		return dto.getAddressId();
	}

	/**
	 * 验证修改/新增收货地址方法参数
	 * 
	 * @author Hins
	 * @date 2015年10月20日 下午5:55:53
	 * 
	 * @param params
	 */
	private static void validateSave(UserAddress address) {
		ParameterUtil.assertNotNull(address, UserMessages.USER_ADDRESS_SAVE_ADDRESS_PARAMS_ILLEGAL());
		ParameterUtil.assertNotBlank(address.getContacts(), UserMessages.USER_SAVE_ADDRESS_CONTACTS_CAN_NOT_BE_EMPTY());
		ParameterUtil.assertNotBlank(address.getPhone(), UserMessages.USER_SAVE_ADDRESS_PHONE_CAN_NOT_BE_EMPTY());
		ParameterUtil.assertMaxLength(address.getContacts(), 24, UserMessages.USER_SAVE_ADDRESS_CONTACTS_TOO_LONG());
		ParameterUtil.assertMaxLength(address.getPhone(), 24, UserMessages.USER_SAVE_ADDRESS_PHONE_TOO_LONG());
		ParameterUtil.assertMaxLength(address.getFullAddress(), 128, UserMessages.USER_ADDRESS_FULL_ADDRESS_TOO_LONG());
		LoginInfoUtil.mobileCheck(address.getPhone());

	}

	private void update(UserAddress dto) {
		dao.update(dto2po(dto));
		removeAddressCache(dto.getAddressId());
	}

	@Override
	@Cacher(value = UserCacheKey.ADDRESS_LIST_PREFIX, time = UserCacheKey.TIME_FOREVER)
	public List<UserAddress> listMyAddress(long uid) {
		List<UserAddressPO> list = dao.queryByFilter(uid);
		return VOUtil.fromList(list, UserAddress.class);
	}

	/**
	 * 手动清除单个地址缓存
	 * 
	 * @param addressId 地址ID
	 * 
	 * @author felix
	 * @date 2015-10-23
	 */
	private void removeAddressCache(long addressId) {
		String key = UserCacheKey.addressKey(addressId);
		cacheExtendService.delete(key);
	}

	@Override
	@CacheClean({ @CacheCleanExpr(value = UserCacheKey.ADDRESS_LIST_PREFIX, expr = "0"),
			@CacheCleanExpr(value = UserCacheKey.ADDRESS_PREFIX, expr = "1") })
	public int delete(long uid, long addressId) {
		// 删除用户收货地址 sky 2015/12/08

		logger.info("address#service#delete | 删除用户的收货地址 | uid: {}, addressId: {}", uid, addressId);

		UserAddress address = getById(addressId);

		if (address == null || uid != address.getUid())
			throw new ServiceException(OrderErrorCode.USER_ADDRESS_NOT_EXIST, UserMessages.USER_SERVICE_SAVE_ADDRESS_ADDRESS_NOT_EXIST());

		int result = dao.deleteById(addressId);

		logger.info("address#service#delete | 删除用户的收货地址结束 | uid: {}, addressId: {}, result: {}, address: {}", uid, addressId,
				(result == 1 ? "success" : "failed"), address);

		return result;
	}
}
