package com.yogu.services.store.business.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.commons.cache.CacheExtendService;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.PageUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.StoreMessages;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.store.Goods;
import com.yogu.services.store.MerchantCacheKey;
import com.yogu.services.store.business.dao.GoodsDao;
import com.yogu.services.store.business.dao.GoodsTrackDao;
import com.yogu.services.store.business.dto.Store;
import com.yogu.services.store.business.entry.GoodsPO;
import com.yogu.services.store.business.entry.GoodsTrackPO;
import com.yogu.services.store.business.service.GoodsService;
import com.yogu.services.store.business.service.StoreService;
import com.yogu.services.store.resource.api.GoodsAdminResource;

@Named
public class GoodsServiceImpl implements GoodsService {
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsAdminResource.class);

	@Inject
	private GoodsDao goodsDao;
	
	@Inject
	private GoodsTrackDao goodsTrackDao;
	
	@Inject
	private UserRemoteService userRemoteService;
	
	@Inject
	private StoreService storeService;
	
	@Inject
	private IdGenRemoteService idGenRemoteService;
	
	@Inject
	private CacheExtendService cacheExtendService;
	
	@Override
	public Goods getById(long goodsId) {
		GoodsPO po = goodsDao.getById(goodsId);
		if (null == po) {
			return null;
		}
		return VOUtil.from(po, Goods.class);
	}

	@Override
	public Goods getByKey(long goodsKey) {
		GoodsPO po = goodsDao.getByKey(goodsKey);
		if (null == po) {
			return null;
		}
		return VOUtil.from(po, Goods.class);
	}

	@Override
	public List<Goods> listByPage(Long uid, long lastTime, int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		
		List<GoodsPO> list = goodsDao.listByPage(lastTime < 1 ? null : lastTime, pageSize, BooleanConstants.TRUE);
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}
	
	private long getUserAgentStoreId(Long uid){
		if (uid == null || uid < 1) {
			return 0;
		}
		UserProfile profile = userRemoteService.getUserProfileByUid(uid);
		if (profile == null) {
			return 0;
		}
		
		Store store = storeService.getByUid(uid);
		if (store == null) {
			return 0;
		}
		
		return store.getStoreId();
		
	}

	@Override
	public List<Goods> listByName(String goodsName, Long uid, long lastTime, int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		if(StringUtils.isNotBlank(goodsName)){
			goodsName = goodsName.trim();
		}
		
		List<GoodsPO> list = null;
		if(StringUtils.isBlank(goodsName)){
			list = goodsDao.listByPage(lastTime < 1 ? null : lastTime, pageSize, BooleanConstants.TRUE);
		}else{
			list = goodsDao.listByName(goodsName, lastTime < 1 ? null : lastTime, pageSize, BooleanConstants.TRUE);
		}
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}

	@Override
	public List<Goods> listByTagIdOrderByPriceDesc(long tagId, Long uid, int pageNo, int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageNo, pageSize);
		List<GoodsPO> list = goodsDao.listByTagId(tagId, pageSize, offset, 2, BooleanConstants.TRUE);
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}
	
	@Override
	public List<Goods> listByTagIdOrderByPriceAsc(long tagId, Long uid, int pageNo, int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageNo, pageSize);
		List<GoodsPO> list = goodsDao.listByTagId(tagId, pageSize, offset, 3, BooleanConstants.TRUE);
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}
	
	@Override
	public List<Goods> listByTagIdOrderByPriceDesc(List<Long> tagIds, Long uid, int pageNo, int pageSize) {
		if (CollectionUtils.isEmpty(tagIds)) {
			return Collections.emptyList();
		}
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageNo, pageSize);
		List<GoodsPO> list = goodsDao.listByTagIds(tagIds, pageSize, offset, 2, BooleanConstants.TRUE);
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}
	
	@Override
	public List<Goods> listByTagIdOrderByPriceAsc(List<Long> tagIds, Long uid, int pageNo, int pageSize) {
		if (CollectionUtils.isEmpty(tagIds)) {
			return Collections.emptyList();
		}
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageNo, pageSize);
		List<GoodsPO> list = goodsDao.listByTagIds(tagIds, pageSize, offset, 3, BooleanConstants.TRUE);
		
		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}

	@Override
	public List<Goods> listByTagId(long tagId, Long uid, int pageNo,
			int pageSize) {
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageNo, pageSize);
		List<GoodsPO> list = goodsDao.listByTagId(tagId, pageSize, offset, 0, BooleanConstants.TRUE);

		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}
	
	@Override
	public List<Goods> listByTagId(List<Long> tagIds, Long uid, int pageNo,
			int pageSize) {
		if (CollectionUtils.isEmpty(tagIds)) {
			return Collections.emptyList();
		}
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		pageSize = PageUtils.limitSize(pageSize, 1, 100);
		int offset = PageUtils.offset(pageNo, pageSize);
		List<GoodsPO> list = goodsDao.listByTagIds(tagIds, pageSize, offset, 0, BooleanConstants.TRUE);

		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}

	@Override
	public List<Goods> listBykeys(Long uid, List<Long> goodsKeys) {
		if(goodsKeys.isEmpty()){
			return Collections.emptyList();
		}
		
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		List<GoodsPO> list = goodsDao.listByKey(goodsKeys);;

		for (GoodsPO goods : list) {
			if (goods.getStoreId() == storeId) {
				goods.setRetailPrice(goods.getTradePrice());
			}
		}

		return VOUtil.fromList(list, Goods.class);
	}

	@Override
	public List<Goods> listByName(String goodsName, int pageIndex, int pageSize) {
		// 搜索数据
		int rows = PageUtils.limitSize(pageSize, 5, 30);// [5~30]
		int offset = PageUtils.offset(pageIndex, rows);
		
		List<GoodsPO> list = goodsDao.listByLikeName(StringUtils.isBlank(goodsName)?null:goodsName, offset, rows);
		
		// 装在美食数据
		return VOUtil.fromList(list, Goods.class);
	}

	@Override
	public void addGoods(Goods goods) {
		// 1. 先获取goodsId
		
		long goodsId = goods.getGoodsId();
		if (goodsId > 0) {// 执行修改
			logger.info("service#addGoods | 新增商品 ");
			processUpdateGoods(goods);
			removeDishCache(goods.getGoodsKey());
			logger.info("service#addGoods | 新增商品 success");
		} else {
			logger.info("service#addGoods | 修改商品 | goodsId: {}", goods.getGoodsId());
			processSaveDishPO(goods);
			logger.info("service#addGoods | 修改商品success | goodsId: {}", goods.getGoodsId());
		}
		
	}
	
	private void processUpdateGoods(Goods goods){
		
		GoodsPO po = VOUtil.from(goods, GoodsPO.class);
		GoodsPO old = validateDishAndPermissionById(goods.getGoodsId());
		long goodsKey = old.getGoodsKey();
		po.setGoodsKey(goodsKey);
		
		unifyDealWithDish(old, po);
	}
	
	/**
	 * 初始化并保存新增菜品时候，涉及修改db的逻辑<br>
	 * 并返回美食信息
	 * @param uid - 操作者
	 * @param params - 新增美食方法的参数
	 * @param dishId - 美食id
	 * @param store - 餐厅信息
	 * @author hins
	 * @date 2016年9月9日 下午6:16:02
	 * @return DishPO
	 */
	private void processSaveDishPO(Goods goods) {
		// 1. 先获取dishId, 因为规格需要用到
		long goodsId = idGenRemoteService.getNextStoreId();
		GoodsPO po = VOUtil.from(goods, GoodsPO.class);
		po.setGoodsId(goodsId);
		po.setGoodsKey(goodsId);
		po.setCreateTime(DateUtils.getUniformCurrentTimeForThread());
		po.setUpdateTime(DateUtils.getUniformCurrentTimeForThread());
		goodsDao.save(po);

		// 新增快照
		backupDishTrack(po, goodsId);
	}
	
	/**
	 * 判断菜品是否存在，并且用户是否有权限修改这个菜品
	 *
	 * @param goodsId 菜品KEY
	 * @return 返回DishPO，不会为null
	 */
	private GoodsPO validateDishAndPermissionById(long goodsId) {
		GoodsPO goods = goodsDao.getById(goodsId);
		ParameterUtil.assertNotNull(goods, StoreMessages.DISH_VALIDATEDISHANDPERMISSIONBYID_DISH_NOT_EXIST(goodsId));
		return goods;
	}
	
	/**
	 * 统一处理菜品信息修改操作： a. 需要修改的信息符合新增快照条件，则删除旧的菜品信息，插入新的菜品信息，并新增快照记录 b. 若修改的信息不符合新增快照条件，则直接修改菜品信息和对应的快照记录 必须保证update（非修改的信息）和原old信息一致
	 * 以下情况需要增加快照： 1. 新增菜品; 2. 修改菜品价格（原价、现价）； 3. 修改菜品主图； 4. 修改菜品规格（比如400克、1kg）； 5. 修改菜品名称；
	 * 
	 * @author Hins
	 * @date 2015年8月28日 下午3:21:55
	 * 
	 * @Editer Felix
	 * @date 2015年8月29日 下午3:58:55
	 * 
	 * @param old - 旧的菜品对象
	 * @param po - 更新的菜品信息对象
	 * @return 返回是否有保存快照。
	 */
	private void unifyDealWithDish(GoodsPO old, GoodsPO update) {
		ParameterUtil.assertNotNull(old, StoreMessages.DISH_UNIFYDEALWITHDISH_DISH_NOT_EXIST());
		ParameterUtil.assertNotNull(update, StoreMessages.DISH_UNIFYDEALWITHDISH_DISH_NOT_EXIST());
		if (old.getGoodsId() != update.getGoodsId()) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR,
					StoreMessages.DISH_UNIFYDEALWITHDISH_DISH_DIFFERENT());
		}
		long newDishId = idGenRemoteService.getNextStoreId();
		update.setGoodsId(newDishId);
		update.setCreateTime(DateUtils.getUniformCurrentTimeForThread());
		update.setUpdateTime(DateUtils.getUniformCurrentTimeForThread());
		goodsDao.deleteById(old.getGoodsId());
		goodsDao.save(update);
		// 新增菜品快照
		backupDishTrack(update, old.getGoodsId());

	}
	
	/**
	 * 新增菜品快照
	 * 
	 * 新增菜品快照，以下情况需要增加快照： 1. 新增菜品; 2. 修改菜品价格（原价、现价）； 3. 修改菜品主图； 4. 修改菜品规格（比如400克、1kg）； 5. 修改菜品名称；
	 * 
	 * @param dish 菜品
	 * @param parentDishId 父菜品ID
	 */
	private void backupDishTrack(GoodsPO goods, long parentDishId) {
		// 新增菜品快照
		GoodsTrackPO track = VOUtil.from(goods, GoodsTrackPO.class);
		track.setParentGoodsId(parentDishId);
		track.setCreateTime(DateUtils.getUniformCurrentTimeForThread());
		track.setUpdateTime(DateUtils.getUniformCurrentTimeForThread());
		goodsTrackDao.save(track);
	}

	@Override
	public void setDishStatus(long goodsId, short status) {
		
		// 1. 验证参数
		GoodsPO goods = validateSetDishStatus(goodsId, status);

		// 2. 只调整修改过的状态（防止错误提交）
		if (goods.getStatus() != status) {
			goodsDao.updateStatus(goodsId, status);
		}
		
		removeDishCache(goods.getGoodsKey());
		
	}
				
	/**
	 * 验证调整美食状态（上架/下架）方法
	 * 
	 * @param uid
	 *            - 操作人
	 * @param dishId
	 *            - 美食id
	 * @param status
	 *            美食状态{@link com.mazing.CommonConstants}
	 * @author hins
	 * @date 2016年9月9日 下午5:54:08
	 * @return DishPO
	 */
	private GoodsPO validateSetDishStatus(long goodsId, short status) {
		if (status != CommonConstants.DISH_STATUS_NORMAL && status != CommonConstants.DISH_STATUS_OFF_SHELF) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, StoreMessages.DISHSERVICE_SETDISHSTATUS_STATUS_ERROR());
		}

		GoodsPO dish = goodsDao.getById(goodsId);
		ParameterUtil.assertNotNull(dish, StoreMessages.STORESERVICE_UPDATETOPICIMG_DISH_NOT_EXIST());

		Store store = storeService.getById(dish.getStoreId());
		ParameterUtil.assertNotNull(store, StoreMessages.STORE_NOT_EXIST());

		return dish;
	}
	
	/**
	 * 删除Dish缓存
	 * 
	 * @param dishId
	 */
	private void removeDishCache(long dishKey) {
		cacheExtendService.delete(MerchantCacheKey.dishKey(dishKey));// 菜品自身cache
		reloadDishToCache(dishKey);
	}
	
	/**
	 * 重新加载 菜品数据Dish 数据到缓存
	 *
	 * @param dishKey 菜品key
	 * @author ten 2015/8/22
	 */
	private void reloadDishToCache(long dishKey) {
		// 重载单个菜品数据
		String key = MerchantCacheKey.dishKey(dishKey);
		GoodsPO po = goodsDao.getByKey(dishKey);
		if (po.getStatus() != BooleanConstants.TRUE) {
			return;
		}
		Goods dto = VOUtil.from(po, Goods.class);
		cacheExtendService.set(key, dto, MerchantCacheKey.TIME_FOREVER);
	}

	@Override
	public Goods getByKey(long goodsKey, Long uid) {
		GoodsPO po = goodsDao.getByKey(goodsKey);
		if (null == po) {
			return null;
		}
		
		long storeId = getUserAgentStoreId(uid); // 查询用户所属的商家id，为0标示不属于任何商家
		if (po.getStoreId() == storeId) {
			po.setRetailPrice(po.getTradePrice());
		}
		
		return VOUtil.from(po, Goods.class);
	}

}
