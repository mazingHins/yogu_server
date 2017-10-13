/**
 * 
 */
package com.yogu.services.store;

import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;

/**
 * 整个商家模块的KEY定义 <br>
 * 这里提供方法直接获取key
 *
 * @author JFan 2015年8月18日 下午5:13:12
 */
public final class MerchantCacheKey {

	/** 永久有效 */
	public static final int TIME_FOREVER = -1;

	/** 5秒 */
	public static final int TIME_5S = 5;
	/** 1小时 */
	public static final int TIME_1H = 60 * 60;
	/** 6小时 */
	public static final int TIME_6H = 6 * TIME_1H;

	/** 门店缓存前缀 */
	public static final String STORE_PREFIX = "Store";

	/** 门店图片列表（仅启用）缓存前缀 */
	public static final String STORE_PICS_PREFIX = "StorePicS";

	/** 门店菜品列表缓存前缀（只有上架的菜品） */
	public static final String STORE_DISHS_PREFIX = "StoreDishS";
	
	/**add by kimmy 2017-01-10 门店菜品英文列表缓存前缀（只有上架的菜品） */
	public static final String STORE_DISHENS_PREFIX = "StoreDishEnS";

	/** 门店详情缓存前缀 */
	public static final String STORE_DETAILS_PREFIX = "StoreDetails";

	/** 门店详情图片列表（仅启用）缓存前缀 */
	public static final String STORE_DETAILS_PICS_PREFIX = "StoreDetailsPicS";

	/** 门店配送范围缓存前缀 */
	public static final String SERVICE_RANGE_PREFIX = "StoreServiceRangeS";

	/** 菜品缓存前缀 */
	public static final String DISH_PREFIX = "Dish";

	/** 菜品图片列表缓存前缀 */
	public static final String DISHS_PICS_PREFIX = "DishPicS";

	/** 用户收藏门店 */
	public static final String FAV_STORE = "FavStore";

	/** 用户收藏菜品 */
	public static final String FAV_DISH = "FavDish";

	/** 美食规格列表（根据dishKey） */
	public static final String DISH_SPEC_DISHKEY_PREFIX = "DishSpecDishKeyS";

	/** 美食规格列表（根据storeId） */
	public static final String DISH_SPEC_STOREID_PREFIX = "DishSpecStoreIdS";

	/** 美食品种 */
	public static final String DISH_CATEGORY_PREFIX = "DishCategory";

	/** 美食TAG列表 */
	public static final String DISH_TAG_LIST_PREFIX = "DishTagList";

	/** 美食绑定了的TAG列表 */
	public static final String DISH_TAGS_PREFIX = "DishTags";

	/** 美食品种列表 */
	public static final String DISH_CATEGORY_LIST_PREFIX = "DishCategoryList";

	/** 菜品快照前缀 */
	public static final String DISH_TRACK_PREFIX = "DishTrack";

	/** 门店员工角色前缀 */
	public static final String STORE_STAFF_ROLE_PREFIX = "StoreStaffRole";

	/** 门店是否被封的缓存前缀 */
	public static final String IS_STORE_BANNED_PREFIX = "IsStoreBanned";

	/** 美食规格备注map (根据dishKey) */
	public static final String DISH_SPEC_SUPPLEMENT_DISHKEY_PREFIX = "DishSpecSupplementDishKeyPrefix";

	/** 美食规格备注map (根据storeId) */
	public static final String DISH_SPEC_SUPPLEMENT_STOREID_PREFIX = "DishSpecSupplementStoreIdPrefix";

	/** 门店下所有的美食分组缓存前缀 2016-03-24 add by hins */
	public static final String STORE_DISH_GROUP_PREFIX = "StoreDishGroup";

	/** 门店下所有的美食分组关联缓存前缀 2016-03-24 add by hins */
	public static final String STORE_DISH_GROUP_MP_PREFIX = "StoreDishGroupMp";

	/** 更新美食排序缓存前缀 2016-04-07 add by hins */
	public static final String UPDATE_DISH_SEQUENCE = "UpdateDishSequence";
	
	public static final String UPDATE_DISH_ALL_SEQUENCE = "UpdateDishAllSequence";
	
	public static final String UPDATE_GROUP_ALL_SEQUENCE = "UpdateGroupAllSequence";
	
	/** 资讯首页块 */
	public static final String NEWS_BLOCK = "NewsBlock";
	
	/** 用户收藏资讯首页块 */
	public static final String FAV_NEWS_BLOCK = "FavNewsBlock";
	/** 餐厅营业前准备得缓存key 2016-05-26 by felix */
	public static final String STORE_START_SERVICE_PRE_PREFIX = "StoreStartServicePre";
	
	
	public static final String STORE_BUSSINESS_LICENSE = "StoreBussinessLicense";
	
	public static final String STORE_CATERING_CERTIFICATION = "StoreCateringCertification";
	/**
	 * 米星餐厅-顺丰配送 关联配置实体缓存key
	 */
	public static final String STORE_SF_RELATION = "StoreSfRelation";
	
	/** 用户收藏资讯资讯餐厅(cblog) */
	public static final String FAV_NEWS_STORE = "FavNewsStore";
	
	/** miniblog餐厅跟cblog餐厅关系表缓存key **/
	public static final String STORE_NEWS_STORE_MP = "storeNewsStoreMP";
	
	/**
	 * 商家直连信息缓存
	 */
	public static final String STORE_PAY_ACCOUNT = "storePayAccount";
	
	
	
	// ####
	// ## 当你需要缓存某个东西的时候，例如：门店信息
	// ## 应当先查看现有的key能否满足，没有合适的再添加
	// ####
	/** 门店 营业证照bl的缓存 */
	public static String storeBussinessLicenseKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_BUSSINESS_LICENSE, storeId);
	}
	
	/** 门店 营业证照cc的缓存 */
	public static String storeCateringCertificationKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_CATERING_CERTIFICATION, storeId);
	}



	/** 门店 Store对象的缓存 */
	public static String storeKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_PREFIX, storeId);
	}

	/** 门店 StorePic List 对象的缓存 */
	public static String storePicsKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_PICS_PREFIX, storeId);
	}

	/** 门店 Dish List 对象的缓存 */
	public static String dishsKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_DISHS_PREFIX, storeId);
	}

	/** 门店详情 StoreDetails 对象的缓存 */
	public static String storeDetailsKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_DETAILS_PREFIX, storeId);
	}

	/** 门店详情图片 StoreDetailsPic List 对象的缓存 */
	public static String storeDetailsPicKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_DETAILS_PICS_PREFIX, storeId);
	}

	/** 门店 StoreServiceRange List 对象的缓存 */
	public static String serviceRangeKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(SERVICE_RANGE_PREFIX, storeId);
	}

	/** 菜品 Dish 对象的缓存 */
	public static String dishKey(long dishKey) {
		return AnnoCacheExtendAspecter.toKey(DISH_PREFIX, dishKey);
	}

	/** 菜品图片 DishPic List 对象的缓存 */
	public static String dishPicsKey(long dishKey) {
		return AnnoCacheExtendAspecter.toKey(DISHS_PICS_PREFIX, dishKey);
	}

	/** 用户收藏门店 boolean 的缓存 */
	public static String favStoreKey(long uid, long storeId) {
		return AnnoCacheExtendAspecter.toKey(FAV_STORE, uid, storeId);
	}

	/** 用户收藏菜品 boolean 的缓存 */
	public static String favDishKey(long uid, long dishKey) {
		return AnnoCacheExtendAspecter.toKey(FAV_DISH, uid, dishKey);
	}

	/** 美食规格列表（根据dishKey） */
	public static String dishSpecKeyByDishKey(long dishKey) {
		return AnnoCacheExtendAspecter.toKey(DISH_SPEC_DISHKEY_PREFIX, dishKey);
	}

	/** 美食规格列表（根据storeId） */
	public static String dishSpecKeyByStoreId(long storeId) {
		return AnnoCacheExtendAspecter.toKey(DISH_SPEC_STOREID_PREFIX, storeId);
	}

	/** 美食规格备注map (根据dishKey) */
	public static String dishSpecSupplementByDishKey(long dishKey) {
		return AnnoCacheExtendAspecter.toKey(DISH_SPEC_SUPPLEMENT_DISHKEY_PREFIX, dishKey);
	}

	/** 美食规格备注map (根据storeId) */
	public static String dishSpecSupplementByStoreId(long storeId) {
		return AnnoCacheExtendAspecter.toKey(DISH_SPEC_SUPPLEMENT_STOREID_PREFIX, storeId);
	}

	/** 美食品种 的缓存 */
	public static String dishCategoryKey(long categoryId) {
		return AnnoCacheExtendAspecter.toKey(DISH_CATEGORY_PREFIX, categoryId);
	}

	/** 美食TAG列表 的缓存 */
	public static String dishTagListKey() {
		return AnnoCacheExtendAspecter.toKey(DISH_TAG_LIST_PREFIX);
	}

	/** 美食绑定了的TAG列表 的缓存 */
	public static String dishTagsKey(long dishKey) {
		return AnnoCacheExtendAspecter.toKey(DISH_TAGS_PREFIX, dishKey);
	}

	/** 美食品种list 的缓存 */
	public static String dishCategorysKey() {
		return AnnoCacheExtendAspecter.toKey(DISH_CATEGORY_LIST_PREFIX);
	}

	/** 菜品快照 对象的缓存 */
	public static String dishTrackKey(long dishId) {
		return AnnoCacheExtendAspecter.toKey(DISH_TRACK_PREFIX, dishId);
	}

	/** 门店员工角色的缓存 */
	public static String storeStaffRoleKey(long storeId, long uid) {
		return AnnoCacheExtendAspecter.toKey(STORE_STAFF_ROLE_PREFIX, storeId, uid);
	}

	/** 门店是否被封的缓存 */
	public static String isStoreBannedKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(IS_STORE_BANNED_PREFIX, storeId);
	}

	/** 门店所有的美食分组缓存 */
	public static String storeDishGroupKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_DISH_GROUP_PREFIX, storeId);
	}

	/** 门店所有的美食分组关联缓存 */
	public static String storeDishGroupMpKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_DISH_GROUP_MP_PREFIX, storeId);
	}
	/** 餐厅-顺丰配送 配置信息 关联缓存 */
	public static String storeSfConfigKey(long storeId) {
		return AnnoCacheExtendAspecter.toKey(STORE_SF_RELATION, storeId);
	}
	/** 更新美食排序缓存 */
	public static String updateDishSequence(long storeId, long dishId) {
		return AnnoCacheExtendAspecter.toKey(UPDATE_DISH_SEQUENCE, storeId, dishId);
	}
	
	/** 更新所有美食排序缓存 */
	public static String updateAllDishSequence(long storeId){
		return AnnoCacheExtendAspecter.toKey(UPDATE_DISH_ALL_SEQUENCE, storeId);
	}

	/** 更新所有分组排序缓存 */
	public static String updateAllGroupSequence(long storeId){
		return AnnoCacheExtendAspecter.toKey(UPDATE_GROUP_ALL_SEQUENCE, storeId);
	}
	
	/** 用户收藏资讯餐厅 boolean 的缓存 */
	public static String favNewsStoreKey(long uid, long storeId) {
		return AnnoCacheExtendAspecter.toKey(FAV_NEWS_STORE, uid, storeId);
	}
	
	/** miniblog餐厅跟cblog餐厅关系表缓存key **/
	public static String storeNewsStoreMpKey(long storeId){
		return AnnoCacheExtendAspecter.toKey(STORE_NEWS_STORE_MP, storeId);
	}
	
	public static String storePayAccountKey(long uid, short payMode){
		return AnnoCacheExtendAspecter.toKey(STORE_PAY_ACCOUNT, uid, payMode);
	}
}
