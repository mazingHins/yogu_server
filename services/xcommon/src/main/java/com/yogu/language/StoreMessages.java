package com.yogu.language;

import com.yogu.core.enums.merchant.StoreStatus;

/**
 * 域store 下的 多语言提示相关key的常量类<br>
 * 
 * 用于组织store域下的异常提示、注解提示相关的常量key
 * 
 * @author sky 2016-03-25
 *
 */
public class StoreMessages {

	/**
	 * 餐厅不存在
	 */
	public static final String STORE_NOT_EXIST = "common.store.notexist";

	/**
	 * 餐厅不存在
	 * 
	 * @return
	 */
	public static String STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STORE_NOT_EXIST);
	}

	/**
	 * 您的餐厅已经被封号
	 */
	public static final String STORESTATUS_FROST = "common.StoreStatus_FROST";

	/**
	 * 您的餐厅已经被封号
	 * 
	 * @return
	 */
	public static String STORESTATUS_FROST() {
		return MultiLanguageAdapter.fetchMessage(STORESTATUS_FROST);
	}

	/**
	 * 菜品ID不能为空
	 */
	public static final String DISHAPI_DISHIDS_CAN_NOT_BE_EMPTY = "store.dishApi.dishIds.empty";

	/**
	 * 菜品ID不能为空
	 * 
	 * @return
	 */
	public static String DISHAPI_DISHIDS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISHAPI_DISHIDS_CAN_NOT_BE_EMPTY);
	}

	/**
	 * storeId错误
	 */
	public static final String STOREADMINAPI_STOREID_ERROR = "store.storeadminApi.storeId.error";

	/**
	 * storeId错误
	 * 
	 * @return
	 */
	public static String STOREADMINAPI_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_STOREID_ERROR);
	}

	/**
	 * 请求错误，请重试
	 */
	public static final String FAV_REQUEST_ERROR = "store.fav.request.error";

	/**
	 * 请求错误，请重试
	 * 
	 * @return
	 */
	public static String FAV_REQUEST_ERROR() {
		return MultiLanguageAdapter.fetchMessage(FAV_REQUEST_ERROR);
	}

	/**
	 * 不存在
	 */
	public static final String INDEX_FILLINGITEM_STRORE_NOT_EXIST = "store.index.fillingItem.str.notexist";

	/**
	 * 不存在
	 * 
	 * @return
	 */
	public static String INDEX_FILLINGITEM_STRORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(INDEX_FILLINGITEM_STRORE_NOT_EXIST);
	}

	/**
	 * 此数据不可分享！
	 */
	public static final String SHARE_SHAREINFO_CAN_NOT_SHARE = "store.share.shareInfo.cannotshare";

	/**
	 * 此数据不可分享！
	 * 
	 * @return
	 */
	public static String SHARE_SHAREINFO_CAN_NOT_SHARE() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_CAN_NOT_SHARE);
	}

	/**
	 * 规格不存在
	 */
	public static final String DISHSPECAPI_GETBYKEY_SPEC_NOT_EXIST = "store.dishSpecApi.getByKey.spec.empty";

	/**
	 * 规格不存在
	 * 
	 * @return
	 */
	public static String DISHSPECAPI_GETBYKEY_SPEC_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISHSPECAPI_GETBYKEY_SPEC_NOT_EXIST);
	}

	/**
	 * 菜品没有设置默认规格{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_DISH_KEY_NOT_SET = "store.dishSpecValidationApi.validateDefaultSpec.dish.key.notset";

	/**
	 * 菜品没有设置默认规格{0}
	 * 
	 * @return
	 */
	public static String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_DISH_KEY_NOT_SET(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_DISH_KEY_NOT_SET, dishKey);
	}

	/**
	 * 菜品设置的默认规格不正确{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_DEFAULTSPEC_ERROR = "store.dishSpecValidationApi.validateDefaultSpec.defaultSpec.error";

	/**
	 * 菜品设置的默认规格不正确{0}
	 * 
	 * @return
	 */
	public static String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_DEFAULTSPEC_ERROR(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_DEFAULTSPEC_ERROR, dishKey);
	}

	/**
	 * 菜品与规格的规格名称不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_NAME_DIFFERENT = "store.dishSpecValidationApi.validateDefaultSpec.name.different";

	/**
	 * 菜品与规格的规格名称不一致{0}
	 * 
	 * @param o
	 * @return
	 */
	public static String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_NAME_DIFFERENT(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_NAME_DIFFERENT, dishKey);
	}

	/**
	 * 菜品与规格的每日供应数不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_NUM_DIFFERENT = "store.dishSpecValidationApi.validateDefaultSpec.num.different";

	/**
	 * 菜品与规格的每日供应数不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_NUM_DIFFERENT(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_NUM_DIFFERENT, dishKey);
	}

	/**
	 * 菜品与规格的价格不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_PRICE_DIFFERENT = "store.dishSpecValidationApi.validateDefaultSpec.price.different";

	/**
	 * 菜品与规格的价格不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_PRICE_DIFFERENT(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_PRICE_DIFFERENT, dishKey);
	}

	/**
	 * 菜品与规格的原价不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_PRICE_NOTSAME = "store.dishSpecValidationApi.validateDefaultSpec.price.notsame";

	/**
	 * 菜品与规格的原价不一致{0}
	 */
	public static final String DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_PRICE_NOTSAME(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_VALIDATEDEFAULTSPEC_PRICE_NOTSAME, dishKey);
	}

	/**
	 * 规格{0}在快照表中不存在
	 */
	public static final String DISHSPECVALIDATIONAPI_STARTCHECK_SNAPSHOTLIST_NOT_EXIST = "store.dishSpecValidationApi.startCheck.snapshotList.empty";

	/**
	 * 规格{0}在快照表中不存在
	 */
	public static final String DISHSPECVALIDATIONAPI_STARTCHECK_SNAPSHOTLIST_NOT_EXIST(long specKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECVALIDATIONAPI_STARTCHECK_SNAPSHOTLIST_NOT_EXIST, specKey);
	}

	/**
	 * 规格信息不存在
	 */
	public static final String DISHSURPLUSAPI_RELEASESURPLUSBYSPECID_SPEC_NOT_EXIST = "store.dishSurplusApi.releaseSurplusBySpecId.spec.empty";

	/**
	 * 规格信息不存在
	 */
	public static final String DISHSURPLUSAPI_RELEASESURPLUSBYSPECID_SPEC_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISHSURPLUSAPI_RELEASESURPLUSBYSPECID_SPEC_NOT_EXIST);
	}

	/**
	 * 请设置块的标题
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_TITLE_CAN_NOT_BE_EMPTY = "store.recommendApi.giveBlock.title.empty";

	/**
	 * 请设置块的标题
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_TITLE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_TITLE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 请设置块的类型
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_TYPE_CAN_NOT_BE_EMPTY = "store.recommendApi.giveBlock.type.empty";

	/**
	 * 请设置块的类型
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_TYPE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_TYPE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 起始分钟数不合法，应该在00:00~24:00之间
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_ACTIVESTARTMINUTE_ERROR = "store.recommendApi.giveBlock.activeStartMinute.error";

	/**
	 * 起始分钟数不合法，应该在00:00~24:00之间
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_ACTIVESTARTMINUTE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_ACTIVESTARTMINUTE_ERROR);
	}

	/**
	 * 截止分钟数不合法，应该在00:00~24:00之间
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_ACTIVEENDMINUTE_ERROR = "store.recommendApi.giveBlock.activeEndMinute.error";

	/**
	 * 截止分钟数不合法，应该在00:00~24:00之间
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_ACTIVEENDMINUTE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_ACTIVEENDMINUTE_ERROR);
	}

	/**
	 * 起始时间应该小于截止时间
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_TIME_ERROR = "store.recommendApi.giveBlock.time.error";

	/**
	 * 起始时间应该小于截止时间
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_TIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_TIME_ERROR);
	}

	/**
	 * 未知的标题点击事件类型
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_RBA_UNKNOWN = "store.recommendApi.giveBlock.rba.empty";

	/**
	 * 未知的标题点击事件类型
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_RBA_UNKNOWN() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_RBA_UNKNOWN);
	}

	/**
	 * 请设置块点击跳转的URL地址
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_URL_CAN_NOT_BE_EMPTY = "store.recommendApi.giveBlock.url.empty";

	/**
	 * 请设置块点击跳转的URL地址
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_URL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_URL_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 请设置分享的图片
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_SHAREIMG_CAN_NOT_BE_EMPTY = "store.recommendApi.giveBlock.shareImg.empty";

	/**
	 * 请设置分享的图片
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_SHAREIMG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_SHAREIMG_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 请设置分享的标题
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_SHARETITLE_CAN_NOT_BE_EMPTY = "store.recommendApi.giveBlock.shareTitle.empty";

	/**
	 * 请设置分享的标题
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_SHARETITLE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_SHARETITLE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 请设置分享的内容简介
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_SHARECONTENT_CAN_NOT_BE_EMPTY = "store.recommendApi.giveBlock.shareContent.empty";

	/**
	 * 请设置分享的内容简介
	 */
	public static final String RECOMMENDAPI_GIVEBLOCK_SHARECONTENT_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_GIVEBLOCK_SHARECONTENT_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 需要调整排序的块的参数不正确
	 */
	public static final String RECOMMENDAPI_UPDATEBLOCKSEQUENCE_PARAM_ERROR = "store.recommendApi.updateBlockSequence.param.error";

	/**
	 * 需要调整排序的块的参数不正确
	 */
	public static final String RECOMMENDAPI_UPDATEBLOCKSEQUENCE_PARAM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_UPDATEBLOCKSEQUENCE_PARAM_ERROR);
	}

	/**
	 * 新增的item不能为空
	 */
	public static final String RECOMMENDAPI_ADDITEM_ITEM_CAN_NOT_BE_EMPTY = "store.recommendApi.addItem.item.empty";

	/**
	 * 新增的item不能为空
	 */
	public static final String RECOMMENDAPI_ADDITEM_ITEM_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_ADDITEM_ITEM_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 需要更新的item不能为空
	 */
	public static final String RECOMMENDAPI_UPDATEITEM_ITEM_CAN_NOT_BE_EMPTY = "store.recommendApi.updateItem.item.empty";

	/**
	 * 需要更新的item不能为空
	 */
	public static final String RECOMMENDAPI_UPDATEITEM_ITEM_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDAPI_UPDATEITEM_ITEM_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 商店不存在
	 */
	public static final String STOREADMINAPI_STOREDETAIL_STORE_NOT_EXIST = "store.storeAdminApi.storeDetail.store.empty";

	/**
	 * 商店不存在
	 */
	public static final String STOREADMINAPI_STOREDETAIL_STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_STOREDETAIL_STORE_NOT_EXIST);
	}

	/**
	 * 认证类型不能为空
	 */
	public static final String STOREADMINAPI_UPDATEAUDITSTATUS_TYPE_CAN_NOT_BE_EMPTY = "store.storeAdminApi.updateAuditStatus.type.empty";

	/**
	 * 认证类型不能为空
	 */
	public static final String STOREADMINAPI_UPDATEAUDITSTATUS_TYPE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_UPDATEAUDITSTATUS_TYPE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 门店不存在，storeId={0}
	 */
	public static final String STOREADMINAPI_MODIFYSTATUS_STORE_NOT_EXIST = "store.storeAdminApi.modifyStatus.store.empty";

	/**
	 * 门店不存在，storeId={0}
	 */
	public static final String STOREADMINAPI_MODIFYSTATUS_STORE_NOT_EXIST(long storeId) {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_MODIFYSTATUS_STORE_NOT_EXIST, storeId);
	}

	/**
	 * 暂不支持操作：+{0}
	 */
	public static final String STOREADMINAPI_MODIFYSTATUS_CAN_NOT_SUPPORT = "store.storeAdminApi.modifyStatus.newStatus.error";

	/**
	 * 暂不支持操作：+{0}
	 */
	public static final String STOREADMINAPI_MODIFYSTATUS_CAN_NOT_SUPPORT(StoreStatus newStatus) {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_MODIFYSTATUS_CAN_NOT_SUPPORT, newStatus);
	}

	/**
	 * 修改门店状态成功
	 */
	public static final String STOREADMINAPI_MODIFYSTATUS_SUCCESS = "store.storeAdminApi.modifyStatus.success";

	/**
	 * 修改门店状态成功
	 */
	public static final String STOREADMINAPI_MODIFYSTATUS_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_MODIFYSTATUS_SUCCESS);
	}
	
	/**
	 * 清除缓存成功
	 */
	public static final String STOREADMINAPI_CLEANSTORECACHE_SUCCESS = "store.storeAdminApi.cleanStoreCache.success";

	/**
	 * 清除缓存成功
	 */
	public static final String STOREADMINAPI_CLEANSTORECACHE_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_CLEANSTORECACHE_SUCCESS);
	}

	/**
	 * 清除所有餐厅、美食的缓存成功
	 */
	public static final String STOREADMINAPI_CLEANALLSTORECACHE_SUCCESS = "store.storeAdminApi.cleanAllStoreCache.success";

	/**
	 * 清除所有餐厅、美食的缓存成功
	 */
	public static final String STOREADMINAPI_CLEANALLSTORECACHE_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_CLEANALLSTORECACHE_SUCCESS);
	}

	/**
	 * 找不到要转让的用户信息，请重试
	 */
	public static final String STOREADMINAPI_STORETRANSFER_USERPROFILE_CAN_NOT_FIND = "store.storeAdminApi.storeTransfer.userProfile.empty";

	/**
	 * 找不到要转让的用户信息，请重试
	 */
	public static final String STOREADMINAPI_STORETRANSFER_USERPROFILE_CAN_NOT_FIND() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_STORETRANSFER_USERPROFILE_CAN_NOT_FIND);
	}

	/**
	 * 门店转让成功
	 */

	public static final String STOREADMINAPI_STORETRANSFER_SUCCESS = "store.storeAdminApi.storeTransfer.success";

	/**
	 * 门店转让成功
	 */
	public static final String STOREADMINAPI_STORETRANSFER_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_STORETRANSFER_SUCCESS);
	}

	/**
	 * 修改美食分类成功
	 */
	public static final String STOREADMINAPI_MODIFYCATEGORYNAME_SUCCESS = "store.storeAdminApi.modifyCategoryName.success";

	/**
	 * 修改美食分类成功
	 */
	public static final String STOREADMINAPI_MODIFYCATEGORYNAME_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_MODIFYCATEGORYNAME_SUCCESS);
	}

	/**
	 * 餐厅名称不允许为空
	 */
	public static final String STOREADMINAPI_UPDATENAME_NEWNAME_CAN_NOT_BE_EMPTY = "store.storeAdminApi.updateName.newName.empty";

	/**
	 * 餐厅名称不允许为空
	 */
	public static final String STOREADMINAPI_UPDATENAME_NEWNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_UPDATENAME_NEWNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 门店名称长度不合法
	 */
	public static final String STOREADMINAPI_UPDATENAME_NEWNAME_LENGTH_ERROR = "store.storeAdminApi.updateName.newName.length.error";

	/**
	 * 门店名称长度不合法
	 */
	public static final String STOREADMINAPI_UPDATENAME_NEWNAME_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_UPDATENAME_NEWNAME_LENGTH_ERROR);
	}

	/**
	 * 修改门店名称成功
	 */
	public static final String STOREADMINAPI_UPDATENAME_SUCCESS = "store.storeAdminApi.updateName.success";

	/**
	 * 修改门店名称成功
	 */
	public static final String STOREADMINAPI_UPDATENAME_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_UPDATENAME_SUCCESS);
	}

	/**
	 * 修改门店客户经理信息成功
	 */
	public static final String STOREADMINAPI_UPDATECSM_SUCCESS = "store.storeAdminApi.updateCsm.success";

	/**
	 * 修改门店客户经理信息成功
	 */
	public static final String STOREADMINAPI_UPDATECSM_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_UPDATECSM_SUCCESS);
	}

	/**
	 * 管理员更新门店瀑布流首图失败
	 */
	public static final String STOREADMINAPI_UPLOADTOPICIMG_FAILURE = "store.storeAdminApi.uploadTopicImg.failure";

	/**
	 * 管理员更新门店瀑布流首图失败
	 */
	public static final String STOREADMINAPI_UPLOADTOPICIMG_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_UPLOADTOPICIMG_FAILURE);
	}

	/**
	 * 找不到dishKey={0}的美食
	 */
	public static final String STOREADMINAPI_GETOFFSHELFDISHBYKEY_DISHLIST_CAN_NOT_FIND = "store.storeAdminApi.getOffShelfDishByKey.dishList.empty";

	/**
	 * 找不到dishKey={0}的美食
	 */
	public static final String STOREADMINAPI_GETOFFSHELFDISHBYKEY_DISHLIST_CAN_NOT_FIND(long dishKey) {
		return MultiLanguageAdapter.fetchMessage(STOREADMINAPI_GETOFFSHELFDISHBYKEY_DISHLIST_CAN_NOT_FIND, dishKey);
	}

	/**
	 * 没有找到餐厅: {0}
	 */
	public static final String STOREAPI_ALLINFO_STORE_NOT_FIND = "store.storeApi.allInfo.store.empty";

	/**
	 * 没有找到餐厅: {0}
	 */
	public static final String STOREAPI_ALLINFO_STORE_NOT_FIND(long storeId) {
		return MultiLanguageAdapter.fetchMessage(STOREAPI_ALLINFO_STORE_NOT_FIND, storeId);
	}

	/**
	 * 非法调用
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_SIGNNOTEQUALSMD5_ILLEGAL_CALLS = "store.storeApi.tipMerchant2Update.signNotEqualsmd5";

	/**
	 * 非法调用
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_SIGNNOTEQUALSMD5_ILLEGAL_CALLS() {
		return MultiLanguageAdapter.fetchMessage(STOREAPI_TIPMERCHANT2UPDATE_SIGNNOTEQUALSMD5_ILLEGAL_CALLS);
	}

	/**
	 * 版本类型不能为空
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_VERSIONTYPE_CAN_NOT_BE_EMPTY = "store.storeApi.tipMerchant2Update.versionType.empty";

	/**
	 * 版本类型不能为空
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_VERSIONTYPE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREAPI_TIPMERCHANT2UPDATE_VERSIONTYPE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 通知提示‘标题’不能为空
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_TITLE_CAN_NOT_BE_EMPTY = "store.storeApi.tipMerchant2Update.title.empty";

	/**
	 * 通知提示‘标题’不能为空
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_TITLE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREAPI_TIPMERCHANT2UPDATE_TITLE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 通知提示‘内容’不能为空
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_MSG_CAN_NOT_BE_EMPTY = "store.storeApi.tipMerchant2Update.msg.empty";

	/**
	 * 通知提示‘内容’不能为空
	 */
	public static final String STOREAPI_TIPMERCHANT2UPDATE_MSG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREAPI_TIPMERCHANT2UPDATE_MSG_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 门店Id不合法
	 */
	public static final String STORESTAFFROLEAPI_GETSTAFFROLES_STOREID_ERROR = "store.storeStaffroleApi.getStaffRoles.storeId.error";

	/**
	 * 门店Id不合法
	 */
	public static final String STORESTAFFROLEAPI_GETSTAFFROLES_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESTAFFROLEAPI_GETSTAFFROLES_STOREID_ERROR);
	}

	/**
	 * 用户Id不合法
	 */
	public static final String STORESTAFFROLEAPI_GETSTAFFROLES_UID_ERROR = "store.storeStaffroleApi.getStaffRoles.uid.error";

	/**
	 * 用户Id不合法
	 */
	public static final String STORESTAFFROLEAPI_GETSTAFFROLES_UID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESTAFFROLEAPI_GETSTAFFROLES_UID_ERROR);
	}

	/**
	 * store|dish|all|ALL 四选一
	 */
	public static final String SYNCOPENSEARCHAPI_SYNCALL_TYPE_ERROR = "store.syncOpenSearchApi.syncAll.type.error";

	/**
	 * store|dish|all|ALL 四选一
	 */
	public static final String SYNCOPENSEARCHAPI_SYNCALL_TYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SYNCOPENSEARCHAPI_SYNCALL_TYPE_ERROR);
	}

	/**
	 * 购物车数据不能为空
	 */
	public static final String DISH_DISHSPECCHECK_SPECLIST_CAN_NOT_BE_EMPTY = "store.dish.dishSpecCheck.specList.empty";

	/**
	 * 购物车数据不能为空
	 */
	public static final String DISH_DISHSPECCHECK_SPECLIST_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_DISHSPECCHECK_SPECLIST_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 受检购物车数据格式错误
	 */
	public static final String DISH_DISHSPECCHECK_SPECLIST_ERROR = "store.dish.dishSpecCheck.specList.error";

	/**
	 * 受检购物车数据格式错误
	 */
	public static final String DISH_DISHSPECCHECK_SPECLIST_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_DISHSPECCHECK_SPECLIST_ERROR);
	}

	/**
	 * storeId参数不能为空
	 */
	public static final String FAV_FAVSTORE_STOREID_CAN_NOT_BE_EMPTY = "store.fav.favStore.storeId.empty";

	/**
	 * storeId参数不能为空
	 */
	public static final String FAV_FAVSTORE_STOREID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(FAV_FAVSTORE_STOREID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * dishId参数不能为空
	 */
	public static final String FAV_FAVDISH_DISHKEY_CAN_NOT_BE_EMPTY = "store.fav.favDish.dishKey.empty";

	/**
	 * dishId参数不能为空
	 */
	public static final String FAV_FAVDISH_DISHKEY_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(FAV_FAVDISH_DISHKEY_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 不可展示
	 */
	public static final String INDEX_FILLINGITEM_STR_STORE_CAN_NOT_SHOW = "store.index.fillingItem.str.store.notshow";

	/**
	 * 不可展示
	 */
	public static final String INDEX_FILLINGITEM_STR_STORE_CAN_NOT_SHOW() {
		return MultiLanguageAdapter.fetchMessage(INDEX_FILLINGITEM_STR_STORE_CAN_NOT_SHOW);
	}

	/**
	 * 已下架
	 */
	public static final String INDEX_FILLINGITEM_STR_DISH_OFFSHELF = "store.index.fillingItem.str.dish.offshelf";

	/**
	 * 已下架
	 */
	public static final String INDEX_FILLINGITEM_STR_DISH_OFFSHELF() {
		return MultiLanguageAdapter.fetchMessage(INDEX_FILLINGITEM_STR_DISH_OFFSHELF);
	}

	/**
	 * type不能为空
	 */
	public static final String SHARE_SHAREINFO_TYPE_CAN_NOT_BE_EMPTY = "store.share.shareInfo.type.empty";

	/**
	 * type不能为空
	 */
	public static final String SHARE_SHAREINFO_TYPE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_TYPE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 分享类型错误：{0}
	 */
	public static final String SHARE_SHAREINFO_TYPE_ERROR = "store.share.shareInfo.type.error";

	/**
	 * 分享类型错误：{0}
	 */
	public static final String SHARE_SHAREINFO_TYPE_ERROR(String type) {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_TYPE_ERROR, type);
	}

	/**
	 * 分享的餐厅不存在
	 */
	public static final String SHARE_SHAREINFO_STORE_NOT_EXIST = "store.share.shareInfo.store.empty";

	/**
	 * 分享的餐厅不存在
	 */
	public static final String SHARE_SHAREINFO_STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_STORE_NOT_EXIST);
	}

	/**
	 * 分享的美食不存在
	 */
	public static final String SHARE_SHAREINFO_DISH_NOT_EXIST = "store.share.shareInfo.dish.empty";

	/**
	 * 分享的美食不存在
	 */
	public static final String SHARE_SHAREINFO_DISH_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_DISH_NOT_EXIST);
	}

	/**
	 * 分享的广告不存在
	 */
	public static final String SHARE_SHAREINFO_ACTIVITY_NOT_EXIST = "store.share.shareInfo.activity.empty";

	/**
	 * 分享的广告不存在
	 */
	public static final String SHARE_SHAREINFO_ACTIVITY_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_ACTIVITY_NOT_EXIST);
	}

	/**
	 * 分享的栏位不存在
	 */
	public static final String SHARE_SHAREINFO_BAR_NOT_EXIST = "store.share.shareInfo.bar.empty";

	/**
	 * 分享的栏位不存在
	 */
	public static final String SHARE_SHAREINFO_BAR_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_BAR_NOT_EXIST);
	}

	/**
	 * 这个优惠券活动不可分享
	 */
	public static final String SHARE_SHAREINFO_RULE_CAN_NOT_SHARE = "store.share.shareInfo.rule.cannotshare";

	/**
	 * 这个优惠券活动不可分享
	 */
	public static final String SHARE_SHAREINFO_RULE_CAN_NOT_SHARE() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_RULE_CAN_NOT_SHARE);
	}

	/**
	 * 分享的优惠券卡包不存在
	 */
	public static final String SHARE_SHAREINFO_BAG_NOT_EXIST = "store.share.shareInfo.bag.empty";

	/**
	 * 分享的优惠券卡包不存在
	 */
	public static final String SHARE_SHAREINFO_BAG_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_BAG_NOT_EXIST);
	}

	/**
	 * 这个优惠券卡包活动不可分享
	 */
	public static final String SHARE_SHAREINFO_BAG_CAN_NOT_SHARE = "store.share.shareInfo.bag.cannotshare";

	/**
	 * 这个优惠券卡包活动不可分享
	 */
	public static final String SHARE_SHAREINFO_BAG_CAN_NOT_SHARE() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_BAG_CAN_NOT_SHARE);
	}

	/**
	 * 这个活动已终止
	 */
	public static final String SHARE_SHAREINFO_BAG_IS_STOP = "store.share.shareInfo.bag.isstop";

	/**
	 * 这个活动已终止
	 */
	public static final String SHARE_SHAREINFO_BAG_IS_STOP() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_BAG_IS_STOP);
	}

	/**
	 * 请先登录，再来分享把！
	 */
	public static final String SHARE_SHAREINFO_IMID_EMPTY_LOGIN_FIRST = "store.share.shareInfo.imid.empty";

	/**
	 * 请先登录，再来分享把！
	 */
	public static final String SHARE_SHAREINFO_IMID_EMPTY_LOGIN_FIRST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_IMID_EMPTY_LOGIN_FIRST);
	}

	/**
	 * 分享的优惠券不存在
	 */
	public static final String SHARE_SHAREINFO_C_NOT_EXIST = "store.share.shareInfo.c.empty";

	/**
	 * 分享的优惠券不存在
	 */
	public static final String SHARE_SHAREINFO_C_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_C_NOT_EXIST);
	}

	/**
	 * 要分享的数据不存在！
	 */
	public static final String SHARE_SHAREINFO_SHAREDATA_NOT_EXIST = "store.share.shareInfo.sharedata.notexist";

	/**
	 * 要分享的数据不存在！
	 */
	public static final String SHARE_SHAREINFO_SHAREDATA_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_SHAREINFO_SHAREDATA_NOT_EXIST);
	}

	/**
	 * 系统错误，请重试
	 */
	public static final String SHARE_ENCRYPTCOUPONID_ID_ERROR = "store.share.encryptCouponId.id.error";

	/**
	 * 系统错误，请重试
	 */
	public static final String SHARE_ENCRYPTCOUPONID_ID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SHARE_ENCRYPTCOUPONID_ID_ERROR);
	}

	/**
	 * 分享的优惠券活动不存在
	 */
	public static final String SHARE_GETCOUPONRULE_RULE_NOT_EXIST = "store.share.getCouponRule.rule.empty";

	/**
	 * 分享的优惠券活动不存在
	 */
	public static final String SHARE_GETCOUPONRULE_RULE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARE_GETCOUPONRULE_RULE_NOT_EXIST);
	}

	/**
	 * 这个优惠券不可转让
	 */
	public static final String SHARE_CHECK_CR_CAN_NOT_TRANSFER = "store.share.check.cr.cannottransfer";

	/**
	 * 这个优惠券不可转让
	 */
	public static final String SHARE_CHECK_CR_CAN_NOT_TRANSFER() {
		return MultiLanguageAdapter.fetchMessage(SHARE_CHECK_CR_CAN_NOT_TRANSFER);
	}

	/**
	 * 系统繁忙，待会再来分享吧！
	 */
	public static final String SHARE_CHECK_USER_EMPTY_SYSTEM_BUSY = "store.share.check.user.empty";

	/**
	 * 系统繁忙，待会再来分享吧！
	 */
	public static final String SHARE_CHECK_USER_EMPTY_SYSTEM_BUSY() {
		return MultiLanguageAdapter.fetchMessage(SHARE_CHECK_USER_EMPTY_SYSTEM_BUSY);
	}

	/**
	 * 这个优惠券你不可以转让
	 */
	public static final String SHARE_CHECK_COUPONUIDNOTEQUALUSERUID_CAN_NOT_TRANSFER = "store.share.check.couponUidNotEqualUseruid";

	/**
	 * 这个优惠券你不可以转让
	 */
	public static final String SHARE_CHECK_COUPONUIDNOTEQUALUSERUID_CAN_NOT_TRANSFER() {
		return MultiLanguageAdapter.fetchMessage(SHARE_CHECK_COUPONUIDNOTEQUALUSERUID_CAN_NOT_TRANSFER);
	}

	/**
	 * 请输入邀请码
	 */
	public static final String STOREAUTH_APPLYINVITECODE_INVITECODE_CAN_NOT_BE_EMPTY = "store.storeAuth.applyInviteCode.inviteCode.empty";

	/**
	 * 请输入邀请码
	 */
	public static final String STOREAUTH_APPLYINVITECODE_INVITECODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREAUTH_APPLYINVITECODE_INVITECODE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 邀请码错误
	 */
	public static final String STOREAUTH_APPLYINVITECODE_INVITECODE_ERROR = "store.storeAuth.applyInviteCode.inviteCode.length";

	/**
	 * 邀请码错误
	 */
	public static final String STOREAUTH_APPLYINVITECODE_INVITECODE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREAUTH_APPLYINVITECODE_INVITECODE_ERROR);
	}

	/**
	 * 餐厅名称已经存在
	 */
	public static final String STOREPUBLIC_VALIDATE_STORENAME_EXIST = "store.storePublic.validate.storeName.exist";

	/**
	 * 餐厅名称已经存在
	 */
	public static final String STOREPUBLIC_VALIDATE_STORENAME_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_STORENAME_EXIST);
	}
	
	/**
	 * add by kimmy 2017-01-05  餐厅英文名称已经存在
	 */
	public static final String STOREPUBLIC_VALIDATE_STORENAMEEN_EXIST = "store.storePublic.validate.storeNameEn.exist";
	
	/**
	 * add by kimmy 2017-01-05  餐厅英文名称已经存在
	 */
	public static final String STOREPUBLIC_VALIDATE_STORENAMEEN_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_STORENAMEEN_EXIST);
	}

	/**
	 * 餐厅名中不能有特殊字符
	 */
	public static final String STOREPUBLIC_VALIDATE_STORENAME_CAN_NOT_HAS_SPECIALCHARACTER = "store.storePublic.validate.storeName.specialCharacter";

	/**
	 * 餐厅名中不能有特殊字符
	 */
	public static final String STOREPUBLIC_VALIDATE_STORENAME_CAN_NOT_HAS_SPECIALCHARACTER() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_STORENAME_CAN_NOT_HAS_SPECIALCHARACTER);
	}

	/**
	 * 餐厅名字最多 24 个字符，目前是 {0} 个字符（1中文=2字符）
	 */
	public static final String STOREPUBLIC_VALIDATE_WORDCOUNT_LENGTH_TOO_LONG = "store.storePublic.validate.wordCount.length.max";

	/**
	 * 餐厅名字最多 24 个字符，目前是 {0} 个字符（1中文=2字符）
	 */
	public static final String STOREPUBLIC_VALIDATE_WORDCOUNT_LENGTH_TOO_LONG(int wordCount) {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_WORDCOUNT_LENGTH_TOO_LONG, wordCount);
	}

	/**
	 * 您的开店请求已被处理，请不要重复提交
	 */
	public static final String STOREPUBLIC_VALIDATE_STROE_REQUEST_BE_PROCESSED = "store.storePublic.validate.stroeAlreadyExist";

	/**
	 * 您的开店请求已被处理，请不要重复提交
	 */
	public static final String STOREPUBLIC_VALIDATE_STROE_REQUEST_BE_PROCESSED() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_STROE_REQUEST_BE_PROCESSED);
	}

	/**
	 * 当前城市还未开通服务；敬请期待！
	 */
	public static final String STOREPUBLIC_VALIDATE_AREA_SERVICE_NOT_OPEN = "store.storePublic.validate.area.empty";

	/**
	 * 当前城市还未开通服务；敬请期待！
	 */
	public static final String STOREPUBLIC_VALIDATE_AREA_SERVICE_NOT_OPEN() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_AREA_SERVICE_NOT_OPEN);
	}

	/**
	 * 经营者姓名中不能有特殊字符
	 */
	public static final String STOREPUBLIC_VALIDATE_REALNAME_CAN_NOT_HAS_SPECIALCHARACTER = "store.storePublic.validate.realname.specialCharacter";

	/**
	 * 经营者姓名中不能有特殊字符
	 */
	public static final String STOREPUBLIC_VALIDATE_REALNAME_CAN_NOT_HAS_SPECIALCHARACTER() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_REALNAME_CAN_NOT_HAS_SPECIALCHARACTER);
	}

	/**
	 * 坐标参数错误
	 */
	public static final String STOREPUBLIC_VALIDATE_LATANDLNG_ERROR = "store.storePublic.validate.latAndlng.error";

	/**
	 * 坐标参数错误
	 */
	public static final String STOREPUBLIC_VALIDATE_LATANDLNG_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_LATANDLNG_ERROR);
	}

	/**
	 * 经营者姓名过长
	 */
	public static final String STOREPUBLIC_VALIDATE_REALNAME_TOO_LONG = "store.storePublic.validate.realname.length.max";

	/**
	 * 经营者姓名过长
	 */
	public static final String STOREPUBLIC_VALIDATE_REALNAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_REALNAME_TOO_LONG);
	}

	/**
	 * 经营者手机号格式不正确,请重新输入
	 */
	public static final String STOREPUBLIC_VALIDATE_PHONE_ERROR = "store.storePublic.validate.phone.error";

	/**
	 * 经营者手机号格式不正确,请重新输入
	 */
	public static final String STOREPUBLIC_VALIDATE_PHONE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_PHONE_ERROR);
	}

	/**
	 * 经营者证件号格式不正确,请重新输入
	 */
	public static final String STOREPUBLIC_VALIDATE_IDENTITY_ERROR = "store.storePublic.validate.identity.error";

	/**
	 * 经营者证件号格式不正确,请重新输入
	 */
	public static final String STOREPUBLIC_VALIDATE_IDENTITY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREPUBLIC_VALIDATE_IDENTITY_ERROR);
	}

	/**
	 * {0}前下单,次日配送; {1}后下单,隔日配送(非营业时间除外)
	 */
	public static final String STORE_DETAILS_TIPMSG_EMPTY = "store.store.details.tipMsg.empty";

	/**
	 * {0}前下单,次日配送; {1}后下单,隔日配送(非营业时间除外)
	 */
	public static final String STORE_DETAILS_TIPMSG_EMPTY(String deadline1, String deadline2) {
		return MultiLanguageAdapter.fetchMessage(STORE_DETAILS_TIPMSG_EMPTY, deadline1, deadline2);
	}

	/**
	 * 配送时间: {0}
	 */
	public static final String STORE_LOADBUSINESS_DELIVERY_TIME = "store.store.loadBusiness.delivery.time";

	/**
	 * 配送时间: {0}
	 */
	public static final String STORE_LOADBUSINESS_DELIVERY_TIME(String serviceHoursMsg) {
		return MultiLanguageAdapter.fetchMessage(STORE_LOADBUSINESS_DELIVERY_TIME, serviceHoursMsg);
	}

	/**
	 * {0}前下单明天配送/之后下单后天配送
	 */
	public static final String STORE_LOADBUSINESS_STORE_BOTTOMMSG = "store.store.loadBusiness.store.bottomMsg";

	/**
	 * {0}前下单明天配送/之后下单后天配送
	 */
	public static final String STORE_LOADBUSINESS_STORE_BOTTOMMSG(String time) {
		return MultiLanguageAdapter.fetchMessage(STORE_LOADBUSINESS_STORE_BOTTOMMSG, time);
	}

	/**
	 * 餐厅休息中，欢迎{0}再来选购
	 */
	public static final String STORE_LOADBUSINESS_DAY_EMPTY = "store.store.loadBusiness.day.empty";

	/**
	 * 餐厅休息中，欢迎{0}再来选购
	 */
	public static final String STORE_LOADBUSINESS_DAY_EMPTY(String day) {
		return MultiLanguageAdapter.fetchMessage(STORE_LOADBUSINESS_DAY_EMPTY, day);
	}

	/**
	 * 营业时间: {0}({1})
	 */
	public static final String STORE_LOADBUSINESS_DAY_NOT_EMPTY_BUSINESSMSG = "store.store.loadBusiness.dayNotempty.businessMsg";

	/**
	 * 营业时间: {0}({1})
	 */
	public static final String STORE_LOADBUSINESS_DAY_NOT_EMPTY_BUSINESSMSG(String serviceHoursMsg, String day) {
		return MultiLanguageAdapter.fetchMessage(STORE_LOADBUSINESS_DAY_NOT_EMPTY_BUSINESSMSG, serviceHoursMsg, day);
	}

	/**
	 * 现在下单，{0}开始配送
	 */
	public static final String STORE_LOADBUSINESS_DAY_NOT_EMPTY_BOTTOMMSG = "store.store.loadBusiness.dayNotempty.bottomMsg";

	/**
	 * 现在下单，{0}开始配送
	 */
	public static final String STORE_LOADBUSINESS_DAY_NOT_EMPTY_BOTTOMMSG(String time) {
		return MultiLanguageAdapter.fetchMessage(STORE_LOADBUSINESS_DAY_NOT_EMPTY_BOTTOMMSG, time);
	}

	/**
	 * 营业时间: {0}
	 */
	public static final String STORE_LOADBUSINESS_DAY_EMPTY_BUSINESSMSG = "store.store.loadBusiness.dayempty.businessMsg";

	/**
	 * 营业时间: {0}
	 */
	public static final String STORE_LOADBUSINESS_DAY_EMPTY_BUSINESSMSG(String serviceHoursMsg) {
		return MultiLanguageAdapter.fetchMessage(STORE_LOADBUSINESS_DAY_EMPTY_BUSINESSMSG, serviceHoursMsg);
	}

	/**
	 * {0}-次日{1}
	 */
	public static final String STORE_FORMATSERVICETIME_START_GREATERTHAN_END = "store.store.formatServiceTime.startGreaterthanEnd";

	/**
	 * {0}-次日{1}
	 */
	public static final String STORE_FORMATSERVICETIME_START_GREATERTHAN_END(String start, String end) {
		return MultiLanguageAdapter.fetchMessage(STORE_FORMATSERVICETIME_START_GREATERTHAN_END, start, end);
	}

	/**
	 * 美食名称不允许为空
	 */
	public static final String ADDDISHPARAM_DISHNAME_CAN_NOT_BE_EMPTY = "store.addDishParam.dishName.empty";

	/**
	 * 美食名称不允许为空
	 */
	public static final String ADDDISHPARAM_DISHNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_DISHNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 美食名称建议在12个中文字或24个英文字以内哦
	 */
	public static final String ADDDISHPARAM_DISHNAME_LENGTH_ERROR = "store.addDishParam.dishName.length.error";

	/**
	 * 美食名称建议在12个中文字或24个英文字以内哦
	 */
	public static final String ADDDISHPARAM_DISHNAME_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_DISHNAME_LENGTH_ERROR);
	}

	/**
	 * 保质期不允许为空
	 */
	public static final String ADDDISHPARAM_EXPIRYTIME_CAN_NOT_BE_EMPTY = "store.addDishParam.expiryTime.empty";

	/**
	 * 保质期不允许为空
	 */
	public static final String ADDDISHPARAM_EXPIRYTIME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_EXPIRYTIME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 保质期内容过长
	 */
	public static final String ADDDISHPARAM_EXPIRYTIME_TOO_LONG = "store.addDishParam.expiryTime.length.max";

	/**
	 * 保质期内容过长
	 */
	public static final String ADDDISHPARAM_EXPIRYTIME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_EXPIRYTIME_TOO_LONG);
	}

	/**
	 * 卖点内容过长，不能超过18个字符
	 */
	public static final String ADDDISHPARAM_SPECIALCONTENT_TOO_LONG = "store.addDishParam.specialContent.length.max";

	/**
	 * 卖点内容过长，不能超过18个字符
	 */
	public static final String ADDDISHPARAM_SPECIALCONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_SPECIALCONTENT_TOO_LONG);
	}

	/**
	 * 美食介绍中不能含有表情符号
	 */
	public static final String ADDDISHPARAM_CONTENT_EMOJI = "store.addDishParam.content.emoji";

	/**
	 * 美食介绍中不能含有表情符号
	 */
	public static final String ADDDISHPARAM_CONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_CONTENT_EMOJI);
	}

	/**
	 * 美食介绍内容过长
	 */
	public static final String ADDDISHPARAM_CONTENT_TOO_LONG = "store.addDishParam.content.length.max";

	/**
	 * 美食介绍内容过长
	 */
	public static final String ADDDISHPARAM_CONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_CONTENT_TOO_LONG);
	}

	/**
	 * 美食主图不允许为空
	 */
	public static final String ADDDISHPARAM_TOPICIMG_CAN_NOT_BE_EMPTY = "store.addDishParam.topicImg.empty";

	/**
	 * 美食主图不允许为空
	 */
	public static final String ADDDISHPARAM_TOPICIMG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_TOPICIMG_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 美食详情主图不允许为空
	 */
	public static final String ADDDISHPARAM_CARDIMG_CAN_NOT_BE_EMPTY = "store.addDishParam.cardImg.empty";

	/**
	 * 美食详情主图不允许为空
	 */
	public static final String ADDDISHPARAM_CARDIMG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_CARDIMG_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 美食的收藏图片不允许为空
	 */
	public static final String ADDDISHPARAM_FAVIMG_CAN_NOT_BE_EMPTY = "store.addDishParam.favImg.empty";

	/**
	 * 美食的收藏图片不允许为空
	 */
	public static final String ADDDISHPARAM_FAVIMG_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_FAVIMG_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 提前下单时间不能小于0
	 */
	public static final String ADDDISHPARAM_ADVANCEMINUTE_MININRANGE = "store.addDishParam.advanceMinute.minInRange";

	/**
	 * 提前下单时间不能小于0
	 */
	public static final String ADDDISHPARAM_ADVANCEMINUTE_MININRANGE() {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_ADVANCEMINUTE_MININRANGE);
	}

	/**
	 * 提前下单时间太大，不能大于{0}天
	 */
	public static final String ADDDISHPARAM_ADVANCEMINUTE_INRANGE = "store.addDishParam.advanceMinute.inRange";

	/**
	 * 提前下单时间太大，不能大于{0}天
	 */
	public static final String ADDDISHPARAM_ADVANCEMINUTE_INRANGE(int day) {
		return MultiLanguageAdapter.fetchMessage(ADDDISHPARAM_ADVANCEMINUTE_INRANGE, day);
	}

	/**
	 * 厨房地址不能为空
	 */
	public static final String AUDITFACILITYPARAM_KITCHENADDRESS_CAN_NOT_BE_EMPTY = "store.auditFacilityParam.kitchenAddress.empty";

	/**
	 * 厨房地址不能为空
	 */
	public static final String AUDITFACILITYPARAM_KITCHENADDRESS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITFACILITYPARAM_KITCHENADDRESS_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者真实姓名不能为空
	 */
	public static final String AUDITHEALTHPARAM_REALNAME_CAN_NOT_BE_EMPTY = "store.auditHealthParam.realname.empty";

	/**
	 * 经营者真实姓名不能为空
	 */
	public static final String AUDITHEALTHPARAM_REALNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITHEALTHPARAM_REALNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 健康证 照片不能为空
	 */
	public static final String AUDITHEALTHPARAM_HPIC1_CAN_NOT_BE_EMPTY = "store.auditHealthParam.hPic1.empty";

	/**
	 * 健康证 照片不能为空
	 */
	public static final String AUDITHEALTHPARAM_HPIC1_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITHEALTHPARAM_HPIC1_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者姓名不能为空
	 */
	public static final String AUDITUSERPARAM_REALNAME_CAN_NOT_BE_EMPTY = "store.auditUserParam.realname.empty";

	/**
	 * 经营者姓名不能为空
	 */
	public static final String auditUserParam_realname_empty() {
		return MultiLanguageAdapter.fetchMessage(AUDITUSERPARAM_REALNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者电话不能为空
	 */
	public static final String AUDITUSERPARAM_PHONE_CAN_NOT_BE_EMPTY = "store.auditUserParam.phone.empty";

	/**
	 * 经营者电话不能为空
	 */
	public static final String AUDITUSERPARAM_REALNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITUSERPARAM_REALNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者 证件号不能为空
	 */
	public static final String AUDITUSERPARAM_IDENTITY_CAN_NOT_BE_EMPTY = "store.auditUserParam.identity.empty";

	/**
	 * 经营者 证件号不能为空
	 */
	public static final String AUDITUSERPARAM_IDENTITY_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITUSERPARAM_IDENTITY_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者证件正面照不能为空
	 */
	public static final String AUDITUSERPARAM_IFACE_CAN_NOT_BE_EMPTY = "store.auditUserParam.iFace.empty";

	/**
	 * 经营者证件正面照不能为空
	 */
	public static final String AUDITUSERPARAM_IFACE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITUSERPARAM_IFACE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者证件背面照不能为空
	 */
	public static final String AUDITUSERPARAM_IBACK_CAN_NOT_BE_EMPTY = "store.auditUserParam.iBack.empty";

	/**
	 * 经营者证件背面照不能为空
	 */
	public static final String AUDITUSERPARAM_IBACK_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITUSERPARAM_IBACK_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者手持 证件照 不能为空
	 */
	public static final String AUDITUSERPARAM_IHOLD_CAN_NOT_BE_EMPTY = "store.auditUserParam.iHold.empty";

	/**
	 * 经营者手持 证件照 不能为空
	 */
	public static final String AUDITUSERPARAM_IHOLD_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(AUDITUSERPARAM_IHOLD_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 餐厅名不能为空
	 */
	public static final String STOREPARAM_STORENAME_CAN_NOT_BE_EMPTY = "store.storeParam.storeName.empty";

	/**
	 * 餐厅名不能为空
	 */
	public static final String STOREPARAM_STORENAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREPARAM_STORENAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 餐厅地址不能为空
	 */
	public static final String STOREPARAM_ADDRESS_CAN_NOT_BE_EMPTY = "store.storeParam.address.empty";

	/**
	 * 餐厅地址不能为空
	 */
	public static final String STOREPARAM_ADDRESS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREPARAM_ADDRESS_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者姓名不能为空
	 */
	public static final String STOREPARAM_MANAGERNAME_CAN_NOT_BE_EMPTY = "store.storeParam.managerName.empty";

	/**
	 * 经营者姓名不能为空
	 */
	public static final String STOREPARAM_MANAGERNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREPARAM_MANAGERNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者手机号不能为空
	 */
	public static final String STOREPARAM_MANAGERPHONE_CAN_NOT_BE_EMPTY = "store.storeParam.managerPhone.empty";

	/**
	 * 经营者手机号不能为空
	 */
	public static final String STOREPARAM_MANAGERPHONE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREPARAM_MANAGERPHONE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 经营者证件号不能为空
	 */
	public static final String STOREPARAM_MANEGERID_CAN_NOT_BE_EMPTY = "store.storeParam.manegerID.empty";

	/**
	 * 经营者证件号不能为空
	 */
	public static final String STOREPARAM_MANEGERID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREPARAM_MANEGERID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 餐厅介绍中不能含有表情符号
	 */
	public static final String UPDATESTOREPARAM_CONTENT_EMOJI = "store.updateStoreParam.content.emoji";

	/**
	 * 餐厅介绍中不能含有表情符号
	 */
	public static final String UPDATESTOREPARAM_CONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(UPDATESTOREPARAM_CONTENT_EMOJI);
	}

	/**
	 * 您的版本过低，请前往http://www.mazing.com/down.html下载新版本
	 */
	public static final String DISHSERVICE_NEEDTIPFORCEUPDATE = "store.dishService.needTipForceUpdate";

	/**
	 * 您的版本过低，请前往http://www.mazing.com/down.html下载新版本
	 */
	public static final String DISHSERVICE_NEEDTIPFORCEUPDATE() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_NEEDTIPFORCEUPDATE);
	}

	/**
	 * 门店不存在
	 */
	public static final String DISHSERVICE_STORE_NOT_EXIST = "store.dishService.store.empty";

	/**
	 * 门店不存在
	 */
	public static final String DISHSERVICE_STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_STORE_NOT_EXIST);
	}
	
	/**
	 * 餐厅信息错误，请先登录
	 */
	public static final String DISHSERVICE_DISHESLIST_STORE_ERROR = "store.dishService.dishesList.store.error";
	
	/**
	 * 餐厅信息错误，请先登录
	 */
	public static final String DISHSERVICE_DISHESLIST_STORE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_DISHESLIST_STORE_ERROR);
	}

	/**
	 * 美食ID错误，必须大于0
	 */
	public static final String DISHSERVICE_DISHID_ERROR = "store.dishService.dishId.error";

	/**
	 * 美食ID错误，必须大于0
	 */
	public static final String DISHSERVICE_DISHID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_DISHID_ERROR);
	}


	/**
	 * 给美食补库存错误，数量不能小于1或超过{0}
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM_NUM_ERROR = "store.dishService.incSupplyNum.num.error";

	/**
	 * 给美食补库存错误，数量不能小于1或超过{0}
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM_NUM_ERROR(int num) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_INCSUPPLYNUM_NUM_ERROR, num);
	}

	/**
	 * 美食的默认规格不存在：{0}
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM_SPEC_NOT_EXIST = "store.dishService.incSupplyNum.spec.empty";

	/**
	 * 美食的默认规格不存在：{0}
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM_SPEC_NOT_EXIST(long defaultSpecKey) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_INCSUPPLYNUM_SPEC_NOT_EXIST, defaultSpecKey);
	}

	/**
	 * 待补库存的菜品数据错误
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_INCSURPLUSLIST_DATA_ERROR = "store.dishService.incSupplyNum2.incSurplusList.empty";

	/**
	 * 待补库存的菜品数据错误
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_INCSURPLUSLIST_DATA_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_INCSUPPLYNUM2_INCSURPLUSLIST_DATA_ERROR);
	}

	/**
	 * 待补库存的菜品数据格式错误
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_INCSURPLUSLIST_DATAFORMAT_ERROR = "store.dishService.incSupplyNum2.incSurplusList.error";

	/**
	 * 待补库存的菜品数据格式错误
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_INCSURPLUSLIST_DATAFORMAT_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_INCSUPPLYNUM2_INCSURPLUSLIST_DATAFORMAT_ERROR);
	}

	/**
	 * 给美食补库存错误，数量不能小于0或超过{0}个
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_NUM_ERROR = "store.dishService.incSupplyNum2.num.error";

	/**
	 * 给美食补库存错误，数量不能小于0或超过{0}个
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_NUM_ERROR(int num) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_INCSUPPLYNUM2_NUM_ERROR, num);
	}

	/**
	 * 待补库存的菜品不存在
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_DISH_NOT_EXIST = "store.dishService.incSupplyNum2.dish.empty";

	/**
	 * 待补库存的菜品不存在
	 */
	public static final String DISHSERVICE_INCSUPPLYNUM2_DISH_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_INCSUPPLYNUM2_DISH_NOT_EXIST);
	}

	/**
	 * 设置美食每日的供应数量错误，数量不能小于1或超过{0}个
	 */
	public static final String DISHSERVICE_SETSUPPLYNUM_NUM_ERROR = "store.dishService.setSupplyNum.num.error";

	/**
	 * 设置美食每日的供应数量错误，数量不能小于1或超过{0}个
	 */
	public static final String DISHSERVICE_SETSUPPLYNUM_NUM_ERROR(int num) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_SETSUPPLYNUM_NUM_ERROR, num);
	}

	/**
	 * 参数不能为空
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_PARAMS_CAN_NOT_BE_EMPTY = "store.dishService.validateAddDish.params.empty";

	/**
	 * 参数不能为空
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_PARAMS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_PARAMS_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 美食名称不能使用特殊字符
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_DISHNAME_CAN_NOT_HAS_SPECIALCHARACTER = "store.dishService.validateAddDish.dishName.specialCharacter";

	/**
	 * 美食名称不能使用特殊字符
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_DISHNAME_CAN_NOT_HAS_SPECIALCHARACTER() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_DISHNAME_CAN_NOT_HAS_SPECIALCHARACTER);
	}

	/**
	 * 保质期不能为空
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_EXPIRYTIME_CAN_NOT_BE_EMPTY = "store.dishService.validateAddDish.expiryTime.empty";

	/**
	 * 保质期不能为空
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_EXPIRYTIME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_EXPIRYTIME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 卖点内容不能含有特殊字符
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_SPECIALCONTENT_EMOJI = "store.dishService.validateAddDish.specialContent.emoji";

	/**
	 * 卖点内容不能含有特殊字符
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_SPECIALCONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_SPECIALCONTENT_EMOJI);
	}

	/**
	 * 美食介绍内容中不能含有特殊字符
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_CONTENT_EMOJI = "store.dishService.validateAddDish.content.emoji";

	/**
	 * 美食介绍内容中不能含有特殊字符
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_CONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_CONTENT_EMOJI);
	}

	/**
	 * 规格信息不能为空
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_SPECDETAIL_CAN_NOT_BE_EMPTY = "store.dishService.validateAddDish.specDetail.empty";

	/**
	 * 规格信息不能为空
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_SPECDETAIL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_SPECDETAIL_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 美食主图长度超过限制
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_TOPICIMG_TOO_LONG = "store.dishService.validateAddDish.topicImg.length.max";

	/**
	 * 美食主图长度超过限制
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_TOPICIMG_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_TOPICIMG_TOO_LONG);
	}

	/**
	 * 美食详情图片长度超过限制
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_CARDIMG_TOO_LONG= "store.dishService.validateAddDish.cardImg.length.max";

	/**
	 * 美食详情图片长度超过限制
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_CARDIMG_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_CARDIMG_TOO_LONG);
	}

	/**
	 * 美食收藏页图片长度超过限制
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_FAVIMG_TOO_LONG = "store.dishService.validateAddDish.favImg.length.max";

	/**
	 * 美食收藏页图片长度超过限制
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_FAVIMG_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_FAVIMG_TOO_LONG);
	}

	/**
	 * 支付货币不合法
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_TYPE_ERROR = "store.dishService.validateAddDish.type.empty";

	/**
	 * 支付货币不合法
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_TYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_TYPE_ERROR);
	}

	/**
	 * 推广标签不合法
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_TAG_ERROR = "store.dishService.validateAddDish.tag.empty";

	/**
	 * 推广标签不合法
	 */
	public static final String DISHSERVICE_VALIDATEADDDISH_TAG_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEADDDISH_TAG_ERROR);
	}

	/**
	 * 菜品dishId错误，必须大于0
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_DISHID_ERROR = "store.dishService.validateUpdateDish.dishId.error";

	/**
	 * 菜品dishId错误，必须大于0
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_DISHID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEUPDATEDISH_DISHID_ERROR);
	}

	/**
	 * {0}介绍信息，需要您填写标题
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_TITLE_CAN_NOT_BE_EMPTY = "store.dishService.validateUpdateDish.title.empty";

	/**
	 * {0}介绍信息，需要您填写标题
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_TITLE_CAN_NOT_BE_EMPTY(String indexStr) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEUPDATEDISH_TITLE_CAN_NOT_BE_EMPTY, indexStr);
	}

	/**
	 * {0}标题最多输入15个汉字或30个英文字母
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_TITLE_TOO_LONG = "store.dishService.validateUpdateDish.title.length.max";

	/**
	 * {0}标题最多输入15个汉字或30个英文字母
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_TITLE_TOO_LONG(String indexStr) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEUPDATEDISH_TITLE_TOO_LONG, indexStr);
	}

	/**
	 * {0}介绍内容最多输入500个字
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_CONTENT_TOO_LONG = "store.dishService.validateUpdateDish.content.length.max";

	/**
	 * {0}介绍内容最多输入500个字
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_CONTENT_TOO_LONG(String indexStr) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEUPDATEDISH_CONTENT_TOO_LONG, indexStr);
	}

	/**
	 * {0}最多只能上传3张图片
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_PICS_SIZE_MAX = "store.dishService.validateUpdateDish.pics.size.max";

	/**
	 * {0}最多只能上传3张图片
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_PICS_SIZE_MAX(String indexStr) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEUPDATEDISH_PICS_SIZE_MAX, indexStr);
	}

	/**
	 * {0}请添加文字或图片
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_THISCONTENTYES_LENGTH_0 = "store.dishService.validateUpdateDish.thisContentYes.length.0";

	/**
	 * {0}请添加文字或图片
	 */
	public static final String DISHSERVICE_VALIDATEUPDATEDISH_THISCONTENTYES_LENGTH_0(String indexStr) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_VALIDATEUPDATEDISH_THISCONTENTYES_LENGTH_0, indexStr);
	}

	/**
	 * 设置美食上下架状态错误，状态只能为 1 或者 2
	 */
	public static final String DISHSERVICE_SETDISHSTATUS_STATUS_ERROR = "store.dishService.setDishStatus.status.error";

	/**
	 * 设置美食上下架状态错误，状态只能为 1 或者 2
	 */
	public static final String DISHSERVICE_SETDISHSTATUS_STATUS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_SETDISHSTATUS_STATUS_ERROR);
	}

	/**
	 * 参数缺少
	 */
	public static final String DISHSERVICE_UPDATESEQUENCE_PARAM_LACK = "store.dishService.updateSequence.param.lack";

	/**
	 * 参数缺少
	 */
	public static final String DISHSERVICE_UPDATESEQUENCE_PARAM_LACK() {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_UPDATESEQUENCE_PARAM_LACK);
	}

	/**
	 * 设置美食提前下单时间错误，不能小于0分钟或超过{0}天
	 */
	public static final String DISHSERVICE_MINUTE_ERROR = "store.dishService.minute.error";

	/**
	 * 设置美食提前下单时间错误，不能小于0分钟或超过{0}天
	 */
	public static final String DISHSERVICE_MINUTE_ERROR(int day) {
		return MultiLanguageAdapter.fetchMessage(DISHSERVICE_MINUTE_ERROR, day);
	}

	/**
	 * 动态内容不能为空
	 */
	public static final String NEWSSERVICE_ADD_CONTENT_CAN_NOT_BE_EMPTY = "store.newsService.add.content.empty";

	/**
	 * 动态内容不能为空
	 */
	public static final String NEWSSERVICE_ADD_CONTENT_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(NEWSSERVICE_ADD_CONTENT_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 动态内容长度不合法
	 */
	public static final String NEWSSERVICE_ADD_CONTENT_LENGTH_ERROR = "store.newsService.add.content.length.error";

	/**
	 * 动态内容长度不合法
	 */
	public static final String NEWSSERVICE_ADD_CONTENT_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(NEWSSERVICE_ADD_CONTENT_LENGTH_ERROR);
	}

	/**
	 * 动态内容中不能含有特殊字符
	 */
	public static final String NEWSSERVICE_ADD_BODY_EMOJI = "store.newsService.add.body.emoji";

	/**
	 * 动态内容中不能含有特殊字符
	 */
	public static final String NEWSSERVICE_ADD_BODY_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(NEWSSERVICE_ADD_BODY_EMOJI);
	}

	/**
	 * 记录不存在：{0}
	 */
	public static final String SERVICERANGE_DELETE_RESULT_LESS_THAN_ZERO = "store.serviceRange.delete.result.lessThanZero";

	/**
	 * 记录不存在：{0}
	 */
	public static final String SERVICERANGE_DELETE_RESULT_LESS_THAN_ZERO(long rangeId) {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_DELETE_RESULT_LESS_THAN_ZERO, rangeId);
	}

	/**
	 * 为确保您的正常营业，请至少保留一个配送区域哦
	 */
	public static final String SERVICERANGE_DELETE_NOT_ALLOWED() {
		return MultiLanguageAdapter.fetchMessage("store.serviceRange.delete.not.allowed");
	}

	/**
	 * 备注字数超长
	 */
	public static final String SERVICERANGE_REMARK_TOO_LONG = "store.serviceRange.remark.length.max";

	/**
	 * 备注字数超长
	 */
	public static final String SERVICERANGE_REMARK_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_REMARK_TOO_LONG);
	}

	/**
	 * 配送费用不正确
	 */
	public static final String SERVICERANGE_MONEY_ERROR = "store.serviceRange.money.error";

	/**
	 * 配送费用不正确
	 */
	public static final String SERVICERANGE_MONEY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_MONEY_ERROR);
	}

	/**
	 * 起送金额不正确
	 */
	public static final String SERVICERANGE_MINIMUMMONEY_ERROR = "store.serviceRange.minimumMoney.error";

	/**
	 * 起送金额不正确
	 */
	public static final String SERVICERANGE_MINIMUMMONEY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_MINIMUMMONEY_ERROR);
	}

	/**
	 * 配送设定类型错误
	 */
	public static final String SERVICERANGE_CUTTYPE_ERROR = "store.serviceRange.cutType.error";

	/**
	 * 配送设定类型错误
	 */
	public static final String SERVICERANGE_CUTTYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_CUTTYPE_ERROR);
	}

	/**
	 * 配送时间不在限定范围内
	 */
	public static final String SERVICERANGE_MINUTE_ERROR = "store.serviceRange.minute.error";

	/**
	 * 配送时间不在限定范围内
	 */
	public static final String SERVICERANGE_MINUTE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_MINUTE_ERROR);
	}

	/**
	 * 免运费金额不正确
	 */
	public static final String SERVICERANGE_FULLFREEFREIGHT_ERROR = "store.serviceRange.fullFreeFreight.error";

	/**
	 * 免运费金额不正确
	 */
	public static final String SERVICERANGE_FULLFREEFREIGHT_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_FULLFREEFREIGHT_ERROR);
	}

	/**
	 * 配送范围不能为空
	 */
	public static final String SERVICERANGE_CUTVALUE_CAN_NOT_BE_EMPTY = "store.serviceRange.cutValue.empty";

	/**
	 * 配送范围不能为空
	 */
	public static final String SERVICERANGE_CUTVALUE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_CUTVALUE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 数据不存在：{0}
	 */
	public static final String SERVICERANGE_UPDATE_RANGE_NOT_EXIST = "store.serviceRange.update.range.empty";

	/**
	 * 数据不存在：{0}
	 */
	public static final String SERVICERANGE_UPDATE_RANGE_NOT_EXIST(long rangeId) {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_UPDATE_RANGE_NOT_EXIST, rangeId);
	}

	/**
	 * cutValue不是一个数值
	 */
	public static final String SERVICERANGE_READCUTVALUE_CUTVALUE_NOTNUM = "store.serviceRange.readCutValue.cutValue.notNum";

	/**
	 * cutValue不是一个数值
	 */
	public static final String SERVICERANGE_READCUTVALUE_CUTVALUE_NOTNUM() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_READCUTVALUE_CUTVALUE_NOTNUM);
	}

	/**
	 * cutValue半径距离不合法
	 */
	public static final String SERVICERANGE_READCUTVALUE_RADIUS_LESSTHANZERO = "store.serviceRange.readCutValue.radius.lessThanZero";

	/**
	 * cutValue半径距离不合法
	 */
	public static final String SERVICERANGE_READCUTVALUE_RADIUS_LESSTHANZERO() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_READCUTVALUE_RADIUS_LESSTHANZERO);
	}

	/**
	 * cutValue没有传递坐标内容
	 */
	public static final String SERVICERANGE_READCUTVALUE_XYARRAY_EMPTY = "store.serviceRange.readCutValue.xyArray.empty";

	/**
	 * cutValue没有传递坐标内容
	 */
	public static final String SERVICERANGE_READCUTVALUE_XYARRAY_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_READCUTVALUE_XYARRAY_EMPTY);
	}

	/**
	 * 最多只能设置35个坐标点，请重新设置
	 */
	public static final String SERVICERANGE_READCUTVALUE_LENGTH_ERROR = "store.serviceRange.readCutValue.length.error";

	/**
	 * 最多只能设置35个坐标点，请重新设置
	 */
	public static final String SERVICERANGE_READCUTVALUE_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_READCUTVALUE_LENGTH_ERROR);
	}

	/**
	 * {0}不是一个正确的坐标值
	 */
	public static final String SERVICERANGE_READCUTVALUE_XYVALUE_ERROR = "store.serviceRange.readCutValue.xyValue.error";

	/**
	 * {0}不是一个正确的坐标值
	 */
	public static final String SERVICERANGE_READCUTVALUE_XYVALUE_ERROR(String xy) {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_READCUTVALUE_XYVALUE_ERROR, xy);
	}

	/**
	 * 配送费值非法
	 */
	public static final String SERVICERANGE_SWITCHMONEYTYPE_MONEY_ERROR = "store.serviceRange.switchMoneyType.money.error";

	/**
	 * 配送费值非法
	 */
	public static final String SERVICERANGE_SWITCHMONEYTYPE_MONEY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_SWITCHMONEYTYPE_MONEY_ERROR);
	}

	/**
	 * 的士费x1
	 */
	public static final String SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_ONE = "store.serviceRange.switchRemark.moneyType.TAXI_FEE_ONE";

	/**
	 * 的士费x1
	 */
	public static final String SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_ONE() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_ONE);
	}

	/**
	 * 的士费x2
	 */
	public static final String SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_DOUBLE = "store.serviceRange.switchRemark.moneyType.TAXI_FEE_DOUBLE";

	/**
	 * 的士费x2
	 */
	public static final String SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_DOUBLE() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_DOUBLE);
	}

	/**
	 * 免运费
	 */
	public static final String SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_FREE = "store.serviceRange.switchRemark.moneyType.TAXI_FEE_FREE";

	/**
	 * 免运费
	 */
	public static final String SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_FREE() {
		return MultiLanguageAdapter.fetchMessage(SERVICERANGE_SWITCHREMARK_MONEYTYPE_TAXI_FEE_FREE);
	}

	/**
	 * 餐厅员工数据错误
	 */
	public static final String STAFFSERVICE_DSLIST_ROLE_DATA_ERROR = "store.staffService.dsList.role.data.error";

	/**
	 * 餐厅员工数据错误
	 */
	public static final String STAFFSERVICE_DSLIST_ROLE_DATA_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_DSLIST_ROLE_DATA_ERROR);
	}

	/**
	 * 请输入员工姓名
	 */
	public static final String STAFFSERVICE_EDITSTAFF_NAME_CAN_NOT_BE_EMPTY = "store.staffService.editStaff.name.empty";

	/**
	 * 请输入员工姓名
	 */
	public static final String STAFFSERVICE_EDITSTAFF_NAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_EDITSTAFF_NAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 员工姓名过长，请检查您的输入
	 */
	public static final String STAFFSERVICE_EDITSTAFF_NAME_TOO_LONG = "store.staffService.editStaff.name.length.max";

	/**
	 * 员工姓名过长，请检查您的输入
	 */
	public static final String STAFFSERVICE_EDITSTAFF_NAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_EDITSTAFF_NAME_TOO_LONG);
	}

	/**
	 * 员工姓名不能含有特殊字符
	 */
	public static final String STAFFSERVICE_EDITSTAFF_NAME_EMOJI = "store.staffService.editStaff.name.emoji";

	/**
	 * 员工姓名不能含有特殊字符
	 */
	public static final String STAFFSERVICE_EDITSTAFF_NAME_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_EDITSTAFF_NAME_EMOJI);
	}

	/**
	 * 用户不存在，uid: {0}
	 */
	public static final String STAFFSERVICE_EDITSTAFF_PROFILE_NOT_EXIST = "store.staffService.editStaff.profile.empty";

	/**
	 * 用户不存在，uid: {0}
	 */
	public static final String STAFFSERVICE_EDITSTAFF_PROFILE_NOT_EXIST(long uid) {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_EDITSTAFF_PROFILE_NOT_EXIST, uid);
	}

	/**
	 * 电话号码格式不正确
	 */
	public static final String STAFFSERVICE_SEARCHUSER_PHONE_ERROR = "store.staffService.searchUser.phone.error";

	/**
	 * 电话号码格式不正确
	 */
	public static final String STAFFSERVICE_SEARCHUSER_PHONE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_SEARCHUSER_PHONE_ERROR);
	}

	/**
	 * 系统错误,找不到该用户
	 */
	public static final String STAFFSERVICE_SEARCHUSER_USER_EMPTY = "store.staffService.searchUser.user.empty";

	/**
	 * 系统错误,找不到该用户
	 */
	public static final String STAFFSERVICE_SEARCHUSER_USER_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STAFFSERVICE_SEARCHUSER_USER_EMPTY);
	}

	/**
	 * 图片链接过长
	 */
	public static final String STOREAUDIT_PIC_TOO_LONG = "store.storeAudit.pic.length.max";

	/**
	 * 图片链接过长
	 */
	public static final String STOREAUDIT_PIC_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_PIC_TOO_LONG);
	}

	/**
	 * 无效的图片链接
	 */
	public static final String STOREAUDIT_PIC_ERROR = "store.storeAudit.pic.error";

	/**
	 * 无效的图片链接
	 */
	public static final String STOREAUDIT_PIC_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_PIC_ERROR);
	}

	/**
	 * 名字长度过长
	 */
	public static final String STOREAUDIT_VALIDTEUSER_REALNAME_TOO_LONG = "store.storeAudit.validteUser.realname.length.max";

	/**
	 * 名字长度过长
	 */
	public static final String STOREAUDIT_VALIDTEUSER_REALNAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDTEUSER_REALNAME_TOO_LONG);
	}

	/**
	 * 真实姓名中不能有特殊字符
	 */
	public static final String STOREAUDIT_VALIDTEUSER_REALNAME_CAN_NOT_HAS_SPECIALCHARACTER = "store.storeAudit.validteUser.realname.specialCharacter";

	/**
	 * 真实姓名中不能有特殊字符
	 */
	public static final String STOREAUDIT_VALIDTEUSER_REALNAME_CAN_NOT_HAS_SPECIALCHARACTER() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDTEUSER_REALNAME_CAN_NOT_HAS_SPECIALCHARACTER);
	}

	/**
	 * 电话号码格式不正确,请重新输入
	 */
	public static final String STOREAUDIT_VALIDTEUSER_PHONE_ERROR = "store.storeAudit.validteUser.phone.error";

	/**
	 * 电话号码格式不正确,请重新输入
	 */
	public static final String STOREAUDIT_VALIDTEUSER_PHONE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDTEUSER_PHONE_ERROR);
	}

	/**
	 * 证件号格式不正确,请重新输入
	 */
	public static final String STOREAUDIT_VALIDTEUSER_IDENTITY_ERROR = "store.storeAudit.validteUser.identity.error";

	/**
	 * 证件号格式不正确,请重新输入
	 */
	public static final String STOREAUDIT_VALIDTEUSER_IDENTITY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDTEUSER_IDENTITY_ERROR);
	}

	/**
	 * 地址长度过长
	 */
	public static final String STOREAUDIT_VALIDATEFACILITY_ADDRESS_TOO_LONG = "store.storeAudit.validateFacility.address.length.max";

	/**
	 * 地址长度过长
	 */
	public static final String STOREAUDIT_VALIDATEFACILITY_ADDRESS_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEFACILITY_ADDRESS_TOO_LONG);
	}

	/**
	 * 地址中不能有特殊字符
	 */
	public static final String STOREAUDIT_VALIDATEFACILITY_ADDRESS_EMOJI = "store.storeAudit.validateFacility.address.emoji";

	/**
	 * 地址中不能有特殊字符
	 */
	public static final String STOREAUDIT_VALIDATEFACILITY_ADDRESS_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEFACILITY_ADDRESS_EMOJI);
	}

	/**
	 * 图片超过9张
	 */
	public static final String STOREAUDIT_VALIDATEFACILITY_PICARR_TOO_LONG = "store.storeAudit.validateFacility.picArr.length.max";

	/**
	 * 图片超过9张
	 */
	public static final String STOREAUDIT_VALIDATEFACILITY_PICARR_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEFACILITY_PICARR_TOO_LONG);
	}

	/**
	 * 图片超过{0}张
	 */
	public static final String STOREAUDIT_VALIDATEPICTURE_TOTAL_OVERLIMIT = "store.storeAudit.validatePicture.total.overlimit";

	/**
	 * 图片超过{0}张
	 */
	public static final String STOREAUDIT_VALIDATEPICTURE_TOTAL_OVERLIMIT(int quantity) {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEPICTURE_TOTAL_OVERLIMIT, quantity);
	}

	/**
	 * 名称过长
	 */
	public static final String STOREAUDIT_VALIDATEHEALTH_NAME_TOO_LONG = "store.storeAudit.validateHealth.name.length.max";

	/**
	 * 名称过长
	 */
	public static final String STOREAUDIT_VALIDATEHEALTH_NAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEHEALTH_NAME_TOO_LONG);
	}

	/**
	 * 员工姓名不能含有特殊字符
	 */
	public static final String STOREAUDIT_VALIDATEHEALTH_NAME_EMOJI = "store.storeAudit.validateHealth.name.emoji";

	/**
	 * 员工姓名不能含有特殊字符
	 */
	public static final String STOREAUDIT_VALIDATEHEALTH_NAME_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEHEALTH_NAME_EMOJI);
	}

	/**
	 * 角色名称错误
	 */
	public static final String STOREAUDIT_VALIDATEHEALTH_ROLENAME_ERROR = "store.storeAudit.validateHealth.rolename.error";

	/**
	 * 角色名称错误
	 */
	public static final String STOREAUDIT_VALIDATEHEALTH_ROLENAME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_VALIDATEHEALTH_ROLENAME_ERROR);
	}
	
	/**
	 * 参数错误
	 */
	public static final String STOREAUDIT_AUDITSAFE_SAFEPROMISE_ERROR =  "store.storeAudit.auditSafe.safePromise.error";
	
	/**
	 * 参数错误
	 */
	public static final String STOREAUDIT_AUDITSAFE_SAFEPROMISE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_AUDITSAFE_SAFEPROMISE_ERROR);
	}

	/**
	 * 提现记录不存在
	 */
	public static final String STOREINCOME_WITHDRAWSDETAIL_WITHDRAW_NOT_EXIST = "store.storeIncome.withdrawsDetail.withdraw.empty";

	/**
	 * 提现记录不存在
	 */
	public static final String STOREINCOME_WITHDRAWSDETAIL_WITHDRAW_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREINCOME_WITHDRAWSDETAIL_WITHDRAW_NOT_EXIST);
	}	

	/**
	 * 时间设定不能为空
	 */
	public static final String STOREOPENING_HOURS_CAN_NOT_EMPTY = "store.storeOpening.hours.empty";

	/**
	 * 时间设定不能为空
	 */
	public static final String STOREOPENING_HOURS_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREOPENING_HOURS_CAN_NOT_EMPTY);
	}

	/**
	 * 时间字符串长度超过限制
	 */
	public static final String STOREOPENING_HOURS_TOO_LONG = "store.storeOpening.hours.length.max";

	/**
	 * 时间字符串长度超过限制
	 */
	public static final String STOREOPENING_HOURS_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STOREOPENING_HOURS_TOO_LONG);
	}

	/**
	 * 设置餐厅可以接受预定的天数错误，不能小于0或超过{0}天
	 */
	public static final String STOREOPENING_UPDATEOPENHOURSANDADVANCEDAY_DAYS_ERROR = "store.storeOpening.updateOpenHoursAndAdvanceDay.days.error";

	/**
	 * 设置餐厅可以接受预定的天数错误，不能小于0或超过{0}天
	 */
	public static final String STOREOPENING_UPDATEOPENHOURSANDADVANCEDAY_DAYS_ERROR(int day) {
		return MultiLanguageAdapter.fetchMessage(STOREOPENING_UPDATEOPENHOURSANDADVANCEDAY_DAYS_ERROR, day);
	}

	/**
	 * hours不是一个合法的JSON数据
	 */
	public static final String STOREOPENING_CHECKANDPARSERHOURS_HOURS_NOTJSON = "store.storeOpening.checkAndParserHours.hours.notJSON";

	/**
	 * hours不是一个合法的JSON数据
	 */
	public static final String STOREOPENING_CHECKANDPARSERHOURS_HOURS_NOTJSON() {
		return MultiLanguageAdapter.fetchMessage(STOREOPENING_CHECKANDPARSERHOURS_HOURS_NOTJSON);
	}

	/**
	 * hours没有按照要求传递数据
	 */
	public static final String STOREOPENING_CHECKANDPARSERHOURS_HOURS_ERROR = "store.storeOpening.checkAndParserHours.hours.error";

	/**
	 * hours没有按照要求传递数据
	 */
	public static final String STOREOPENING_CHECKANDPARSERHOURS_HOURS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREOPENING_CHECKANDPARSERHOURS_HOURS_ERROR);
	}

	/**
	 * 店铺ID错误，必须大于0
	 */
	public static final String STORESERVICE_SETADVANCERESERVEDAYS_STOREID_ERROR = "store.storeService.setAdvanceReserveDays.storeId.error";

	/**
	 * 店铺ID错误，必须大于0
	 */
	public static final String STORESERVICE_SETADVANCERESERVEDAYS_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_SETADVANCERESERVEDAYS_STOREID_ERROR);
	}

	/**
	 * 设置餐厅可以接受预定的天数错误，不能小于0或超过{0}天
	 */
	public static final String STORESERVICE_SETADVANCERESERVEDAYS_DAYS_ERROR = "store.storeService.setAdvanceReserveDays.days.error";

	/**
	 * 设置餐厅可以接受预定的天数错误，不能小于0或超过{0}天
	 */
	public static final String STORESERVICE_SETADVANCERESERVEDAYS_DAYS_ERROR(int day) {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_SETADVANCERESERVEDAYS_DAYS_ERROR, day);
	}

	/**
	 * 餐厅ID错误，必须大于0
	 */
	public static final String STORESERVICE_UPDATEFEATURE_STOREID_ERROR = "store.storeService.updateFeature.storeId.error";

	/**
	 * 餐厅ID错误，必须大于0
	 */
	public static final String STORESERVICE_UPDATEFEATURE_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATEFEATURE_STOREID_ERROR);
	}

	/**
	 * 餐厅名称不允许为空
	 */
	public static final String STORESERVICE_UPDATENAME_NEWNAME_CAN_NOT_BE_EMPTY = "store.storeService.updateName.newName.empty";

	/**
	 * 餐厅名称不允许为空
	 */
	public static final String STORESERVICE_UPDATENAME_NEWNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATENAME_NEWNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 餐厅名称中不能有特殊字符
	 */
	public static final String STORESERVICE_UPDATENAME_NAME_CAN_HAS_BE_SPECIALCHARACTER = "store.storeService.updateName.name.specialCharacter";

	/**
	 * 餐厅名称中不能有特殊字符
	 */
	public static final String STORESERVICE_UPDATENAME_NAME_CAN_HAS_BE_SPECIALCHARACTER() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATENAME_NAME_CAN_HAS_BE_SPECIALCHARACTER);
	}

	/**
	 * 餐厅名字最多 24 个字符，目前是 {0} 个字符（1中文=2字符）
	 */
	public static final String STORESERVICE_UPDATENAME_WORDCOUNT_TOO_LONG = "store.storeService.updateName.wordCount.length.max";

	/**
	 * 餐厅名字最多 24 个字符，目前是 {0} 个字符（1中文=2字符）
	 */
	public static final String STORESERVICE_UPDATENAME_WORDCOUNT_TOO_LONG(int wordCount) {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATENAME_WORDCOUNT_TOO_LONG, wordCount);
	}

	/**
	 * 餐厅不存在，请重新登录帐号
	 */
	public static final String STORESERVICE_UPDATESTORESTATUS_STORE_NOT_EXIST = "store.storeService.updateStoreStatus.store.empty";

	/**
	 * 餐厅不存在，请重新登录帐号
	 */
	public static final String STORESERVICE_UPDATESTORESTATUS_STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATESTORESTATUS_STORE_NOT_EXIST);
	}

	/**
	 * 餐厅状态错误
	 */
	public static final String STORESERVICE_UPDATESTORESTATUS_STATUSERROR = "store.storeService.updateStoreStatus.statuserror";

	/**
	 * 餐厅状态错误
	 */
	public static final String STORESERVICE_UPDATESTORESTATUS_STATUSERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATESTORESTATUS_STATUSERROR);
	}

	/**
	 * 美食ID不存在
	 */
	public static final String STORESERVICE_UPDATETOPICIMG_DISHID_NOTEXIST = "store.storeService.updateTopicImg.dishId.notexist";

	/**
	 * 美食ID不存在
	 */
	public static final String STORESERVICE_UPDATETOPICIMG_DISHID_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATETOPICIMG_DISHID_NOTEXIST);
	}

	/**
	 * 美食不存在
	 */
	public static final String STORESERVICE_UPDATETOPICIMG_DISH_NOT_EXIST = "store.storeService.updateTopicImg.dish.empty";

	/**
	 * 美食不存在
	 */
	public static final String STORESERVICE_UPDATETOPICIMG_DISH_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_UPDATETOPICIMG_DISH_NOT_EXIST);
	}

	/**
	 * 提交的数据全部为空
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_PARAMS_CAN_NOT_BE_EMPTY = "store.storeService.validateUpdateStore.params.empty";

	/**
	 * 提交的数据全部为空
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_PARAMS_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_PARAMS_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 您输入的手机号码须为11位
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_PHONE_ERROR = "store.storeService.validateUpdateStore.phone.error";

	/**
	 * 您输入的手机号码须为11位
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_PHONE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_PHONE_ERROR);
	}

	/**
	 * 客服电话设置过长，最多128个字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_FINALPHONE_TOO_LONG = "store.storeService.validateUpdateStore.finalPhone.length.max";

	/**
	 * 客服电话设置过长，最多128个字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_FINALPHONE_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_FINALPHONE_TOO_LONG);
	}

	/**
	 * 餐厅介绍不能超过22个字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_CONTENT_TOO_LONG = "store.storeService.validateUpdateStore.content.length.max";

	/**
	 * 餐厅介绍不能超过22个字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_CONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_CONTENT_TOO_LONG);
	}

	/**
	 * 餐厅介绍中不能含有特殊字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_CONTENT_EMOJI = "store.storeService.validateUpdateStore.content.emoji";

	/**
	 * 餐厅介绍中不能含有特殊字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_CONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_CONTENT_EMOJI);
	}

	/**
	 * 餐厅详情的长度不能超过512个字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_DETAIL_TOO_LONG = "store.storeService.validateUpdateStore.detail.length.max";

	/**
	 * 餐厅详情的长度不能超过512个字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_DETAIL_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_DETAIL_TOO_LONG);
	}

	/**
	 * 餐厅详情不能含有特殊字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_DETAIL_EMOJI = "store.storeService.validateUpdateStore.detail.emoji";

	/**
	 * 餐厅详情不能含有特殊字符
	 */
	public static final String STORESERVICE_VALIDATEUPDATESTORE_DETAIL_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_VALIDATEUPDATESTORE_DETAIL_EMOJI);
	}

	/**
	 * 邮箱不能为空
	 */
	public static final String STORESERVICE_BINDEMAIL_EMAIL_CAN_NOT_BE_EMPTY = "store.storeService.bindEmail.email.empty";

	/**
	 * 邮箱不能为空
	 */
	public static final String STORESERVICE_BINDEMAIL_EMAIL_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDEMAIL_EMAIL_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 验证码不能为空
	 */
	public static final String STORESERVICE_BINDEMAIL_IDCODE_CAN_NOT_BE_EMPTY = "store.storeService.bindEmail.idcode.empty";

	/**
	 * 验证码不能为空
	 */
	public static final String STORESERVICE_BINDEMAIL_IDCODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDEMAIL_IDCODE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 邮箱错误
	 */
	public static final String STORESERVICE_BINDEMAIL_EMAIL_ERROR = "store.storeService.bindEmail.email.error";

	/**
	 * 邮箱错误
	 */
	public static final String STORESERVICE_BINDEMAIL_EMAIL_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDEMAIL_EMAIL_ERROR);
	}

	/**
	 * 非店主, 没有权限设置
	 */
	public static final String STORESERVICE_BINDEMAIL_STAFF_EMPTY = "store.storeService.bindEmail.staff.empty";

	/**
	 * 非店主, 没有权限设置
	 */
	public static final String STORESERVICE_BINDEMAIL_STAFF_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDEMAIL_STAFF_EMPTY);
	}

	/**
	 * 收款账户不能为空
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNO_CAN_NOT_BE_EMPTY = "store.storeService.bindAccount.accountNo.empty";

	/**
	 * 收款账户不能为空
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNO_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_ACCOUNTNO_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 账户所有者姓名不能为空
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNAME_CAN_NOT_BE_EMPTY = "store.storeService.bindAccount.accountName.empty";

	/**
	 * 账户所有者姓名不能为空
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNAME_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_ACCOUNTNAME_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 验证码不能为空
	 */
	public static final String STORESERVICE_BINDACCOUNT_IDCODE_CAN_NOT_BE_EMPTY = "store.storeService.bindAccount.idcode.empty";

	/**
	 * 验证码不能为空
	 */
	public static final String STORESERVICE_BINDACCOUNT_IDCODE_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_IDCODE_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 提现账户错误
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNO_ERROR = "store.storeService.bindAccount.accountNo.error";

	/**
	 * 提现账户错误
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_ACCOUNTNO_ERROR);
	}

	/**
	 * 用户不存在
	 */
	public static final String STORESERVICE_USER_NOT_EXIST = "store.storeService.user.empty";

	/**
	 * 用户不存在
	 */
	public static final String STORESERVICE_USER_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_USER_NOT_EXIST);
	}

	/**
	 * 收款账户请使用邮箱或手机号码
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNO_NOT_EMAIL_OR_MOBILE = "store.storeService.bindAccount.accountNo.notEmailOrMobile";

	/**
	 * 收款账户请使用邮箱或手机号码
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNO_NOT_EMAIL_OR_MOBILE() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_ACCOUNTNO_NOT_EMAIL_OR_MOBILE);
	}

	/**
	 * 验证码错误
	 */
	public static final String STORESERVICE_IDCODE_ERROR = "store.storeService.idcode.error";

	/**
	 * 验证码错误
	 */
	public static final String STORESERVICE_IDCODE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_IDCODE_ERROR);
	}

	/**
	 * 您不是店主 , 没有权限设置
	 */
	public static final String STORESERVICE_BINDACCOUNT_STAFF_EMPTY = "store.storeService.bindAccount.staff.empty";

	/**
	 * 您不是店主 , 没有权限设置
	 */
	public static final String STORESERVICE_BINDACCOUNT_STAFF_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_STAFF_EMPTY);
	}

	/**
	 * 账户所有者姓名过长
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNAME_TOO_LONG = "store.storeService.bindAccount.accountName.length";

	/**
	 * 账户所有者姓名过长
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_ACCOUNTNAME_TOO_LONG);
	}

	/**
	 * 账户所有者姓名中不能有特殊字符
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNAME_CAN_NOT_HAS_SPECIALCHARACTER = "store.storeService.bindAccount.accountName.specialCharacter";

	/**
	 * 账户所有者姓名中不能有特殊字符
	 */
	public static final String STORESERVICE_BINDACCOUNT_ACCOUNTNAME_CAN_NOT_HAS_SPECIALCHARACTER() {
		return MultiLanguageAdapter.fetchMessage(STORESERVICE_BINDACCOUNT_ACCOUNTNAME_CAN_NOT_HAS_SPECIALCHARACTER);
	}

	/**
	 * 未知的图片类别！
	 */
	public static final String UPLOADPIC_PUBLICPIC_TYPE_ERROR = "store.uploadPic.publicPic.type.error";

	/**
	 * 未知的图片类别！
	 */
	public static final String UPLOADPIC_PUBLICPIC_TYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_PUBLICPIC_TYPE_ERROR);
	}

	/**
	 * 读取图片数据失败：{0}
	 */
	public static final String UPLOADPIC_PUBLICPIC_PIC_FAILURE = "store.uploadPic.publicPic.pic.failure";

	/**
	 * 读取图片数据失败：{0}
	 */
	public static final String UPLOADPIC_PUBLICPIC_PIC_FAILURE(String msg) {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_PUBLICPIC_PIC_FAILURE, msg);
	}

	/**
	 * 请上传正确的图片.
	 */
	public static final String UPLOADPIC_PUBLICPIC_PIC_ERROR = "store.uploadPic.publicPic.pic.error";

	/**
	 * 请上传正确的图片.
	 */
	public static final String UPLOADPIC_PUBLICPIC_PIC_ERROR() {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_PUBLICPIC_PIC_ERROR);
	}

	/**
	 * 处理图片失败，可能图片数据不正确
	 */
	public static final String UPLOADPIC_PUBLICPIC_CARDIMG_FAILURE = "store.uploadPic.publicPic.cardImg.failure";

	/**
	 * 处理图片失败，可能图片数据不正确
	 */
	public static final String UPLOADPIC_PUBLICPIC_CARDIMG_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_PUBLICPIC_CARDIMG_FAILURE);
	}

	/**
	 * 修改了美食的相关图片
	 */

	public static final String UPLOADPIC_PUBLICPIC_TEMPLATE_DISHIDNOTEMPTY = "store.uploadPic.publicPic.template.dishIdNotempty";

	/**
	 * 修改了美食的相关图片
	 */

	public static final String UPLOADPIC_PUBLICPIC_TEMPLATE_DISHIDNOTEMPTY() {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_PUBLICPIC_TEMPLATE_DISHIDNOTEMPTY);
	}

	/**
	 * 修改了餐厅的相关图片
	 */
	public static final String UPLOADPIC_PUBLICPIC_TEMPLATE_DISHIDISEMPTY = "store.uploadPic.publicPic.template.dishIdIsempty";

	/**
	 * 修改了餐厅的相关图片
	 */
	public static final String UPLOADPIC_PUBLICPIC_TEMPLATE_DISHIDISEMPTY() {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_PUBLICPIC_TEMPLATE_DISHIDISEMPTY);
	}

	/**
	 * 截取图片失败({0})
	 */
	public static final String UPLOADPIC_CUTIMG_FAILURE = "store.uploadPic.cutImg.failure";

	/**
	 * 截取图片失败({0})
	 */
	public static final String UPLOADPIC_CUTIMG_FAILURE(String type) {
		return MultiLanguageAdapter.fetchMessage(UPLOADPIC_CUTIMG_FAILURE, type);
	}

	/**
	 * 提现方式不正确
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_PAYMODE_ERROR = "store.withdrawService.applyWithdraw.payMode.error";

	/**
	 * 提现方式不正确
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_PAYMODE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(WITHDRAWSERVICE_APPLYWITHDRAW_PAYMODE_ERROR);
	}

	/**
	 * 您操作的餐厅不存在, 请联系米星客服
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_STORE_NOT_EXIST = "store.withdrawService.applyWithdraw.store.empty";

	/**
	 * 您操作的餐厅不存在, 请联系米星客服
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(WITHDRAWSERVICE_APPLYWITHDRAW_STORE_NOT_EXIST);
	}

	/**
	 * 您的餐厅状态目前不可提现, 请联系米星客服
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_STORESTATUSERROR = "store.withdrawService.applyWithdraw.storeStatusError";

	/**
	 * 您的餐厅状态目前不可提现, 请联系米星客服
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_STORESTATUSERROR() {
		return MultiLanguageAdapter.fetchMessage(WITHDRAWSERVICE_APPLYWITHDRAW_STORESTATUSERROR);
	}

	/**
	 * 提现申请失败! 请联系米星客服
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_WITHDRAW_FAILURE = "store.withdrawService.applyWithdraw.withdraw.empty";

	/**
	 * 提现申请失败! 请联系米星客服
	 */
	public static final String WITHDRAWSERVICE_APPLYWITHDRAW_WITHDRAW_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(WITHDRAWSERVICE_APPLYWITHDRAW_WITHDRAW_FAILURE);
	}
	
	/**
	 * (请选择{0}项)
	 */
	public static final String DISHSPECUTILS_GETSUPPLEMENTCOMMENT_REMARK_MSG1 = "store.dishSpecUtils.getSupplementComment.remark.msg1";
	
	/**
	 * (请选择{0}项)
	 */
	public static final String DISHSPECUTILS_GETSUPPLEMENTCOMMENT_REMARK_MSG1(int min) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECUTILS_GETSUPPLEMENTCOMMENT_REMARK_MSG1, min);
	}
	
	/**
	 * (请选择{0}-{1}项)
	 */
	public static final String DISHSPECUTILS_GETSUPPLEMENTCOMMENT_REMARK_MSG2 = "store.dishSpecUtils.getSupplementComment.remark.msg2";
	
	/**
	 * (请选择{0}-{1}项)
	 */
	public static final String DISHSPECUTILS_GETSUPPLEMENTCOMMENT_REMARK_MSG2(int min, int max) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECUTILS_GETSUPPLEMENTCOMMENT_REMARK_MSG2, min, max);
	}
	
	/**
	 * 起
	 */
	public static final String DISHSPECUTILS_RANGEFORMAT = "store.dishSpecUtils.rangeFormat";
	
	/**
	 * 起
	 */
	public static final String DISHSPECUTILS_RANGEFORMAT(String price) {
		return MultiLanguageAdapter.fetchMessage(DISHSPECUTILS_RANGEFORMAT, price);
	}
	
	/**
	 * 20米内
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE1 = "store.distanceUtils.distance2string.distance1";
	
	/**
	 * 20米内
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE1() {
		return MultiLanguageAdapter.fetchMessage(DISTANCEUTILS_DISTANCE2STRING_DISTANCE1);
	}
	
	/**
	 * 50米内
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE2 = "store.distanceUtils.distance2string.distance2";
	
	/**
	 * 50米内
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE2() {
		return MultiLanguageAdapter.fetchMessage(DISTANCEUTILS_DISTANCE2STRING_DISTANCE2);
	}
	
	/**
	 * {0}米
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE3 = "store.distanceUtils.distance2string.distance3";
	
	/**
	 * {0}米
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE3(int distance) {
		return MultiLanguageAdapter.fetchMessage(DISTANCEUTILS_DISTANCE2STRING_DISTANCE3, distance);
	}
	
	/**
	 * 0.{0}公里
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE4 = "store.distanceUtils.distance2string.distance4";
	
	/**
	 * 0.{0}公里
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE4(int distance) {
		return MultiLanguageAdapter.fetchMessage(DISTANCEUTILS_DISTANCE2STRING_DISTANCE4, distance);
	}
	
	/**
	 * {0}.{1}公里
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE5 = "store.distanceUtils.distance2string.distance5";
	
	/**
	 * {0}.{1}公里
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE5(int x, int y) {
		return MultiLanguageAdapter.fetchMessage(DISTANCEUTILS_DISTANCE2STRING_DISTANCE5, x, y);
	}
	
	/**
	 * {0}公里
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE6 = "store.distanceUtils.distance2string.distance6";
	
	/**
	 * {0}公里
	 */
	public static final String DISTANCEUTILS_DISTANCE2STRING_DISTANCE6(int x) {
		return MultiLanguageAdapter.fetchMessage(DISTANCEUTILS_DISTANCE2STRING_DISTANCE6, x);
	}
	
	/**
	 * 规格
	 */
	public static final String DISHCONSTANTS_DEFAULT_DISH_SPEC_LABE = "store.dishConstants.DEFAULT_DISH_SPEC_LABEL";
	
	/**
	 * 规格
	 */
	public static final String DISHCONSTANTS_DEFAULT_DISH_SPEC_LABE() {
		return MultiLanguageAdapter.fetchMessage(DISHCONSTANTS_DEFAULT_DISH_SPEC_LABE);
	}
	
	/**
	 * 备注
	 */
	public static final String DISHCONSTANTS_DEFAULT_DISH_SPEC_SUPPLEMENT_LABEL = "store.dishConstants.DEFAULT_DISH_SPEC_SUPPLEMENT_LABEL";
	
	/**
	 * 备注
	 */
	public static final String DISHCONSTANTS_DEFAULT_DISH_SPEC_SUPPLEMENT_LABEL() {
		return MultiLanguageAdapter.fetchMessage(DISHCONSTANTS_DEFAULT_DISH_SPEC_SUPPLEMENT_LABEL);
	}
	
	/**
	 * 用户门店不存在
	 */
	public static final String COMMOM_STORE_EMPTY = "commom.store.empty";
	
	/**
	 * 用户门店不存在
	 */
	public static final String COMMOM_STORE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(COMMOM_STORE_EMPTY);
	}
	
	/**
	 * 审核认证中，无法修改认证信息
	 */
	public static final String COMMOM_SERVICE_INAUDIT = "commom.service.inAudit";
	
	/**
	 * 审核认证中，无法修改认证信息
	 */
	public static final String COMMOM_SERVICE_INAUDIT() {
		return MultiLanguageAdapter.fetchMessage(COMMOM_SERVICE_INAUDIT);
	}
	
	/**
	 * 场所设施已认证过 且已通过,无需再次认证
	 */
	public static final String COMMOM_SERVICE_FINISHAUDIT = "commom.service.finishAudit";
	
	/**
	 * 场所设施已认证过 且已通过,无需再次认证
	 */
	public static final String COMMOM_SERVICE_FINISHAUDIT() {
		return MultiLanguageAdapter.fetchMessage(COMMOM_SERVICE_FINISHAUDIT);
	}
	
	/**
	 * 资质证明已认证过 且已通过,无需再次认证
	 */
	public static final String STOREAUDITCATERINGCERTIFICATION_APTITUDE_PASSAUDIT = "store.storeAuditCateringCertification.aptitude.passAudit";
	
	/**
	 * 资质证明已认证过 且已通过,无需再次认证
	 */
	public static final String STOREAUDITCATERINGCERTIFICATION_APTITUDE_PASSAUDIT() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDITCATERINGCERTIFICATION_APTITUDE_PASSAUDIT);
	}
	
	/**
	 * 用户餐厅不存在
	 */
	public static final String STOREAUDITHEALTH_SAVEAUDITHEALTHINFO_STORE_NOT_EXIST = "store.storeAuditHealth.saveAuditHealthInfo.store.empty";
	
	/**
	 * 用户餐厅不存在
	 */
	public static final String STOREAUDITHEALTH_SAVEAUDITHEALTHINFO_STORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREAUDITHEALTH_SAVEAUDITHEALTHINFO_STORE_NOT_EXIST);
	}
	/**
	 * 健康认证已存在且已通过,无需再次认证
	 */
	public static final String STOREAUDITHEALTH_SAVEAUDITHEALTHINFO_HEALTH_PASSAUDIT = "store.storeAuditHealth.saveAuditHealthInfo.health.passAudit";
	/**
	 * 健康认证已存在且已通过,无需再次认证
	 */
	public static final String STOREAUDITHEALTH_SAVEAUDITHEALTHINFO_HEALTH_PASSAUDIT () {
		return MultiLanguageAdapter.fetchMessage(STOREAUDITHEALTH_SAVEAUDITHEALTHINFO_HEALTH_PASSAUDIT);
	}
	
	/**
	 * 系统出错
	 */
	public static final String STOREAUDIT_PRECHECK_FROMDB_SYSTEM_ERROR = "store.storeAudit.preCheck.fromDB.empty";
	
	/**
	 * 系统出错
	 */
	public static final String STOREAUDIT_PRECHECK_FROMDB_SYSTEM_ERROR () {
		return MultiLanguageAdapter.fetchMessage(STOREAUDIT_PRECHECK_FROMDB_SYSTEM_ERROR);
	}
	
	/**
	 * 用户不是门店店主
	 */
	public static final String STOREAUDITUSER_SAVEAUDITUSERINFO_NOT_STOREOWNER = "store.storeAuditUser.saveAuditUserInfo.storeGetUid.notEquals.uid";
	
	/**
	 * 用户不是门店店主
	 */
	public static final String STOREAUDITUSER_SAVEAUDITUSERINFO_NOT_STOREOWNER () {
		return MultiLanguageAdapter.fetchMessage(STOREAUDITUSER_SAVEAUDITUSERINFO_NOT_STOREOWNER);
	}
	
	/**
	 * 店主已认证过 且已通过,无需再次认证
	 */
	public static final String STOREAUDITUSER_SAVEAUDITUSERINFO_USER_PASSAUDIT = "store.storeAuditUser.saveAuditUserInfo.user.passAudit";
	
	/**
	 * 店主已认证过 且已通过,无需再次认证
	 */
	public static final String STOREAUDITUSER_SAVEAUDITUSERINFO_USER_PASSAUDIT () {
		return MultiLanguageAdapter.fetchMessage(STOREAUDITUSER_SAVEAUDITUSERINFO_USER_PASSAUDIT);
	}
	
	/**
	 * 收藏美食失败，请重试
	 */
	public static final String FAV_FAVFAV_ERROR = "store.fav.favStore.fav.error";
	
	/**
	 * 收藏美食失败，请重试
	 */
	public static final String FAV_FAVFAV_ERROR() {
		return MultiLanguageAdapter.fetchMessage(FAV_FAVFAV_ERROR);
	}
	
	/**
	 * 收藏美食失败
	 */
	public static final String FAV_FAVDISH_FAV_ERROR = "store.fav.favDish.fav.error";
	
	/**
	 * 收藏美食失败
	 */
	public static final String FAV_FAVDISH_FAV_ERROR() {
		return MultiLanguageAdapter.fetchMessage(FAV_FAVDISH_FAV_ERROR);
	}
	
	/**
	 * 名称不能为空
	 */
	public static final String DISHCATEGORY_MODIFYNAME_NAME_CAN_NOT_EMPTY = "store.dishCategory.modifyName.name.empty";
	
	/**
	 * 名称不能为空
	 */
	public static final String DISHCATEGORY_MODIFYNAME_NAME_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISHCATEGORY_MODIFYNAME_NAME_CAN_NOT_EMPTY);
	}
	
	/**
	 * 美食分类不存在
	 */
	public static final String DISHCATEGORY_MODIFYNAME_PO_NOT_EXIST = "store.dishCategory.modifyName.po.empty";
	
	/**
	 * 美食分类不存在
	 */
	public static final String DISHCATEGORY_MODIFYNAME_PO_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISHCATEGORY_MODIFYNAME_PO_NOT_EXIST);
	}
	
	/**
	 * 请选择美食分类
	 */
	public static final String DISH_ADDDISH_CATEGORY_CAN_NOT_EMPTY = "store.dish.addDish.category.empty";
	
	/**
	 * 请选择美食分类
	 */
	public static final String DISH_ADDDISH_CATEGORY_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_ADDDISH_CATEGORY_CAN_NOT_EMPTY);
	}
	
	/**
	 * 上传的规格信息格式不正确
	 */
	public static final String DISH_ADDDISH_SPECDETAIL_ERROR = "store.dish.addDish.specDetail.error";
	
	/**
	 * 上传的规格信息格式不正确
	 */
	public static final String DISH_ADDDISH_SPECDETAIL_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_ADDDISH_SPECDETAIL_ERROR);
	}
	
	/**
	 * 获取默认规格信息失败
	 */
	public static final String DISH_ADDDISH_DEFAULTDISHSPEC_ERROR = "store.dish.addDish.defaultDishSpec.empty";
	
	/**
	 * 获取默认规格信息失败
	 */
	public static final String DISH_ADDDISH_DEFAULTDISHSPEC_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_ADDDISH_DEFAULTDISHSPEC_ERROR);
	}
	
	/**
	 * 规格信息不能为空
	 */
	public static final String DISH_CHECKSPEC_LIST_CAN_NOT_EMPTY = "store.dish.checkSpec.list.empty";
	
	/**
	 * 规格信息不能为空
	 */
	public static final String DISH_CHECKSPEC_LIST_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPEC_LIST_CAN_NOT_EMPTY);
	}
	
	/**
	 * 不能设置超过{0}规格
	 */
	public static final String DISH_CHECKSPEC_LISTSIZE_ERROR = "store.dish.checkSpec.listSize.error";
	
	/**
	 * 不能设置超过{0}规格
	 */
	public static final String DISH_CHECKSPEC_LISTSIZE_ERROR(int num) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPEC_LISTSIZE_ERROR, num);
	}
	
	/**
	 * 规格名称{0}已重复
	 */
	public static final String DISH_CHECKSPEC_SPECNAME_REPEAT = "store.dish.checkSpec.specName.repeat";
	
	/**
	 * 规格名称{0}已重复
	 */
	public static final String DISH_CHECKSPEC_SPECNAME_REPEAT(String specName) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPEC_SPECNAME_REPEAT, specName);
	}
	
	/**
	 * 规格ID不合法
	 */
	public static final String DISH_CHECKSPECINFO_SPECID_ERROR = "store.dish.checkSpecInfo.specId.error";
	
	/**
	 * 规格ID不合法
	 */
	public static final String DISH_CHECKSPECINFO_SPECID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SPECID_ERROR);
	}
	
	/**
	 * 规格名称不能为空
	 */
	public static final String DISH_CHECKSPECINFO_SPECNAME_CAN_NOT_EMPTY = "store.dish.checkSpecInfo.specName.empty";
	
	/**
	 * 规格名称不能为空
	 */
	public static final String DISH_CHECKSPECINFO_SPECNAME_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SPECNAME_CAN_NOT_EMPTY);
	}
	
	/**
	 * 规格描述中不能含有特殊字符
	 */
	public static final String DISH_CHECKSPECINFO_SPECNAME_EMOJI = "store.dish.checkSpecInfo.specName.emoji";
	
	/**
	 * 规格描述中不能含有特殊字符
	 */
	public static final String DISH_CHECKSPECINFO_SPECNAME_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SPECNAME_EMOJI);
	}
	
	/**
	 * 子规格名称不能含有特殊字符
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENTLABEL_EMOJI = "store.dish.checkSpecInfo.supplementLabel.emoji";
	
	/**
	 * 子规格名称不能含有特殊字符
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENTLABEL_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPLEMENTLABEL_EMOJI);
	}
	
	/**
	 * 子规格名称不能大于{0}个字符或{1}个中文汉字
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENTLABEL_TOO_LONG = "store.dish.checkSpecInfo.supplementLabel.length.max";
	
	/**
	 * 子规格名称不能大于{0}个字符或{1}个中文汉字
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENTLABEL_TOO_LONG(int num1, int num2) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPLEMENTLABEL_TOO_LONG, num1, num2);
	}
	
	/**
	 * 规格名称不能大于{0}个字符或{1}个中文汉字
	 */
	public static final String DISH_CHECKSPECINFO_SPECNAME_TOO_LONG = "store.dish.checkSpecInfo.specName.length.max";
	
	/**
	 * 规格名称不能大于{0}个字符或{1}个中文汉字
	 */
	public static final String DISH_CHECKSPECINFO_SPECNAME_TOO_LONG(int num1, int num2) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SPECNAME_TOO_LONG, num1, num2);
	}
	
	/**
	 * 规格价格不能大于原价
	 */
	public static final String DISH_CHECKSPECINFO_SPECPRICE_GREATERTHAN_ORIGINALPRICE = "store.dish.checkSpecInfo.specPrice.greaterThan.originalPrice";
	
	/**
	 * 规格价格不能大于原价
	 */
	public static final String DISH_CHECKSPECINFO_SPECPRICE_GREATERTHAN_ORIGINALPRICE() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SPECPRICE_GREATERTHAN_ORIGINALPRICE);
	}
	
	/**
	 * 价格不能小于等于0元或大于{0}元
	 */
	public static final String DISH_CHECKSPECINFO_PRICE_ERROR = "store.dish.checkSpecInfo.price.error";
	
	/**
	 * 价格不能小于等于0元或大于{0}元
	 */
	public static final String DISH_CHECKSPECINFO_PRICE_ERROR(String maxPrice) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_PRICE_ERROR, maxPrice);
	}
	
	/**
	 * 原价不能小于0元或大于{0}元
	 */
	public static final String DISH_CHECKSPECINFO_ORIGINALPRICE_ERROR = "store.dish.checkSpecInfo.originalPrice.error";
	
	/**
	 * 原价不能小于0元或大于{0}元
	 */
	public static final String DISH_CHECKSPECINFO_ORIGINALPRICE_ERROR(String maxPrice) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_ORIGINALPRICE_ERROR, maxPrice);
	}
	
	/**
	 * 不能设置超过{0}个子规格
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_NUM_ERROR = "store.dish.checkSpecInfo.supplement.num.error";
	
	/**
	 * 不能设置超过{0}个子规格
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_NUM_ERROR(int num) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPLEMENT_NUM_ERROR, num);
	}
	
	/**
	 * 子规格的标题不能为空
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_TITLE_CAN_NOT_EMPTY = "store.dish.checkSpecInfo.supplement.title.empty";
	
	/**
	 * 子规格的标题不能为空
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_TITLE_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPLEMENT_TITLE_CAN_NOT_EMPTY);
	}
	
	/**
	 * 最小子规格可选数量不能小于1
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_SIZE_MIN_ERROR = "store.dish.checkSpecInfo.supplement.size.min.error";
	
	/**
	 * 最小子规格可选数量不能小于1
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_SIZE_MIN_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPLEMENT_SIZE_MIN_ERROR);
	}
	
	/**
	 * 最大子规格可选数量不能小于1
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_SIZE_MAX_ERROR = "store.dish.checkSpecInfo.supplement.size.max.error";
	
	/**
	 * 最大子规格可选数量不能小于1
	 */
	public static final String DISH_CHECKSPECINFO_SUPPLEMENT_SIZE_MAX_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPLEMENT_SIZE_MAX_ERROR);
	}
	
	/**
	 * 规格备注名不能为空
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_CAN_NOT_EMPTY = "store.dish.checkSpecInfo.suppGetName.empty";
	
	/**
	 * 规格备注名不能为空
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPGETNAME_CAN_NOT_EMPTY);
	}
	
	/**
	 * 子规格选项名中不能含有特殊字符
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_EMOJI = "store.dish.checkSpecInfo.suppGetName.emoji";
	
	/**
	 * 子规格选项名中不能含有特殊字符
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPGETNAME_EMOJI);
	}
	
	/**
	 * 子规格选项名不能大于{0}个字符或{1}个中文汉字
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_TOO_LONG = "store.dish.checkSpecInfo.suppGetName.length.max";
	
	/**
	 * 子规格选项名不能大于{0}个字符或{1}个中文汉字
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_TOO_LONG(int num1, int num2) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPGETNAME_TOO_LONG, num1, num2);
	}
	
	/**
	 * 子规格选项名{0}已重复
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_REPEAT = "store.dish.checkSpecInfo.suppGetName.repeat";
	
	/**
	 * 子规格选项名{0}已重复
	 */
	public static final String DISH_CHECKSPECINFO_SUPPGETNAME_REPEAT(String name) {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKSPECINFO_SUPPGETNAME_REPEAT, name);
	}
	
	/**
	 * 截取图片失败：{0}
	 */
	public static final String DISH_CREATEORDERIMG_ERROR = "store.dish.createOrderImg.error";
	
	/**
	 * 截取图片失败：{0}
	 */
	public static final String DISH_CREATEORDERIMG_ERROR(String msg) {
		return MultiLanguageAdapter.fetchMessage(DISH_CREATEORDERIMG_ERROR, msg);
	}
	
	/**
	 * 用户ID错误，请先登录
	 */
	public static final String DISH_UID_ERROR = "store.dish.uid.error";
	
	/**
	 * 用户ID错误，请先登录
	 */
	public static final String DISH_UID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_UID_ERROR);
	}
	
	/**
	 * 设置美食提前下单时间失败，未知错误
	 */
	public static final String DISH_SETADVANCEMINUTE_ADVANCEMINUTE_ERROR = "store.dish.setAdvanceMinute.advanceMinute.error";
	
	/**
	 * 设置美食提前下单时间失败，未知错误
	 */
	public static final String DISH_SETADVANCEMINUTE_ADVANCEMINUTE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_SETADVANCEMINUTE_ADVANCEMINUTE_ERROR);
	}
	
	/**
	 * 菜品不存在，dishId={0}
	 */
	public static final String DISH_VALIDATEDISHANDPERMISSIONBYID_DISH_NOT_EXIST = "store.dish.validateDishAndPermissionById.dish.empty";
	
	/**
	 * 菜品不存在，dishId={0}
	 */
	public static final String DISH_VALIDATEDISHANDPERMISSIONBYID_DISH_NOT_EXIST(long dishId) {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEDISHANDPERMISSIONBYID_DISH_NOT_EXIST, dishId);
	}
	
	/**
	 * 设置美食每日的供应数量失败，未知错误
	 */
	public static final String DISH_SETDISHSUPPLYNUM_DAILYNUM_ERROR = "store.dish.setDishSupplyNum.dailyNum.error";
	
	/**
	 * 设置美食每日的供应数量失败，未知错误
	 */
	public static final String DISH_SETDISHSUPPLYNUM_DAILYNUM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_SETDISHSUPPLYNUM_DAILYNUM_ERROR);
	}
	
	/**
	 * 美食每日供应的数量不能小于已经被用户下单预定的数量
	 */
	public static final String DISH_CHECKDISHFUTURE_CONSUMEDNUM = "store.dish.checkDishFutureConsumedNum";
	
	/**
	 * 美食每日供应的数量不能小于已经被用户下单预定的数量
	 */
	public static final String DISH_CHECKDISHFUTURE_CONSUMEDNUM() {
		return MultiLanguageAdapter.fetchMessage(DISH_CHECKDISHFUTURE_CONSUMEDNUM);
	}
	
	/**
	 * 设置美食每日的供应数量失败，无法更新数据
	 */
	public static final String DISH_SETDISHFUTURESUPPLYNUM_ERROR = "store.dish.setDishFutureSupplyNum.error";
	
	/**
	 * 设置美食每日的供应数量失败，无法更新数据
	 */
	public static final String DISH_SETDISHFUTURESUPPLYNUM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_SETDISHFUTURESUPPLYNUM_ERROR);
	}
	
	/**
	 * 需要扣减的库存数量不正确.
	 */
	public static final String DISH_CONSUMESPECSURPLUS_ERROR = "store.dish.consumeSpecSurplus.error";
	
	/**
	 * 需要扣减的库存数量不正确.
	 */
	public static final String DISH_CONSUMESPECSURPLUS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_CONSUMESPECSURPLUS_ERROR);
	}
	
	/**
	 * 美食库存不足.
	 */
	public static final String DISH_CONSUMESPECSURPLUS_FAILUR = "store.dish.consumeSpecSurplus.failure";
	
	/**
	 * 美食库存不足.
	 */
	public static final String DISH_CONSUMESPECSURPLUS_FAILUR() {
		return MultiLanguageAdapter.fetchMessage(DISH_CONSUMESPECSURPLUS_FAILUR);
	}
	
	/**
	 * 释放美食库存失败
	 */
	public static final String DISH_RELEASESPECSURPLUS_FAILURE = "store.dish.releaseSpecSurplus.failure";
	
	/**
	 * 释放美食库存失败
	 */
	public static final String DISH_RELEASESPECSURPLUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(DISH_RELEASESPECSURPLUS_FAILURE);
	}
	
	/**
	 * 规格ID错误，必须大于0
	 */
	public static final String DISH_INCDISHSUPPLYNUM_SPECID_ERROR = "store.dish.incDishSupplyNum.specId.error";
	
	/**
	 * 规格ID错误，必须大于0
	 */
	public static final String DISH_INCDISHSUPPLYNUM_SPECID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_INCDISHSUPPLYNUM_SPECID_ERROR);
	}
	
	/**
	 * 库存日期不正确
	 */
	public static final String DISH_INCDISHSUPPLYNUM_DAY_ERROR = "store.dish.incDishSupplyNum.day.error";
	
	/**
	 * 库存日期不正确
	 */
	public static final String DISH_INCDISHSUPPLYNUM_DAY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_INCDISHSUPPLYNUM_DAY_ERROR);
	}
	
	/**
	 * 给指定的美食补当天的库存失败，未知错误
	 */
	public static final String DISH_INCDISHSUPPLYNUM_ROWS_ERROR = "store.dish.incDishSupplyNum.rows.error";
	
	/**
	 * 给指定的美食补当天的库存失败，未知错误
	 */
	public static final String DISH_INCDISHSUPPLYNUM_ROWS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_INCDISHSUPPLYNUM_ROWS_ERROR);
	}
	
	/**
	 * 不能补过去日期的库存 
	 */
	public static final String DISH_VALIDATESURPLUSDAY_GAP_ERROR = "store.dish.validateSurplusDay.gap.error";
	
	/**
	 * 不能补过去日期的库存 
	 */
	public static final String DISH_VALIDATESURPLUSDAY_GAP_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATESURPLUSDAY_GAP_ERROR);
	}
	
	/**
	 * 待补库存日期最大不能超过{0}
	 */
	public static final String DISH_VALIDATESURPLUSDAY_DAY_GREATERTHAN_MAXDAYINT = "store.dish.validateSurplusDay.day.greaterThan.maxDayInt";
	
	/**
	 * 待补库存日期最大不能超过{0}
	 */
	public static final String DISH_VALIDATESURPLUSDAY_DAY_GREATERTHAN_MAXDAYINT(int maxDayInt) {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATESURPLUSDAY_DAY_GREATERTHAN_MAXDAYINT, maxDayInt);
	}
	
	/**
	 * 美食名称不能为空 
	 */
	public static final String DISH_VALIDATEADDDISH_DISHNAME_CAN_NOT_EMPTY = "store.dish.validateAddDish.dishName.empty";
	
	/**
	 * 美食名称不能为空 
	 */
	public static final String DISH_VALIDATEADDDISH_DISHNAME_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_DISHNAME_CAN_NOT_EMPTY);
	}
	
	/**
	 * 美食名称长度超过限制 
	 */
	public static final String DISH_VALIDATEADDDISH_DISHNAME_TOO_LONG = "store.dish.validateAddDish.dishName.length.max";
	
	/**
	 * 美食名称长度超过限制 
	 */
	public static final String DISH_VALIDATEADDDISH_DISHNAME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_DISHNAME_TOO_LONG);
	}
	
	/**
	 * 保质期长度超过限制
	 */
	public static final String DISH_VALIDATEADDDISH_EXPIRYTIME_TOO_LONG= "store.dish.validateAddDish.expiryTime.length.max";
	
	/**
	 * 保质期长度超过限制
	 */
	public static final String DISH_VALIDATEADDDISH_EXPIRYTIME_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_EXPIRYTIME_TOO_LONG);
	}
	
	/**
	 * 卖点长度超过限制
	 */
	public static final String DISH_VALIDATEADDDISH_SPECIALCONTENT_TOO_LONG = "store.dish.validateAddDish.specialContent.length.max";
	
	/**
	 * 卖点长度超过限制
	 */
	public static final String DISH_VALIDATEADDDISH_SPECIALCONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_SPECIALCONTENT_TOO_LONG);
	}
	
	/**
	 * 美食封面图片不能为空
	 */
	public static final String DISH_VALIDATEADDDISH_TOPICIMG_CAN_NOT_EMPTY = "store.dish.validateAddDish.topicImg.empty";
	
	/**
	 * 美食封面图片不能为空
	 */
	public static final String DISH_VALIDATEADDDISH_TOPICIMG_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_TOPICIMG_CAN_NOT_EMPTY);
	}
	
	/**
	 * 美食封面图片地址长度超过限制
	 */
	public static final String  DISH_VALIDATEADDDISH_TOPICIMG_TOO_LONG = "store.dish.validateAddDish.topicImg.length.max";
	
	/**
	 * 美食封面图片地址长度超过限制
	 */
	public static final String  DISH_VALIDATEADDDISH_TOPICIMG_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_TOPICIMG_TOO_LONG);
	}
	
	/**
	 * 美食收藏页图片不能为空
	 */
	public static final String DISH_VALIDATEADDDISH_DAVIMG_CAN_NOT_EMPTY = "store.dish.validateAddDish.davImg.empty";
	
	/**
	 * 美食收藏页图片不能为空
	 */
	public static final String DISH_VALIDATEADDDISH_DAVIMG_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_DAVIMG_CAN_NOT_EMPTY);
	}
	
	/**
	 * 美食收藏页图片地址长度超过限制
	 */
	public static final String DISH_VALIDATEADDDISH_DAVIMG_TOO_LONG = "store.dish.validateAddDish.davImg.length.max";
	
	/**
	 * 美食收藏页图片地址长度超过限制
	 */
	public static final String DISH_VALIDATEADDDISH_DAVIMG_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(DISH_VALIDATEADDDISH_DAVIMG_TOO_LONG);
	}
	
	/**
	 * 用户ID错误
	 */
	public static final String DISH_SETDISHSTATUS_UID_ERROR = "store.dish.setDishStatus.uid.error";
	
	/**
	 * 用户ID错误
	 */
	public static final String DISH_SETDISHSTATUS_UID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_SETDISHSTATUS_UID_ERROR);
	}
	
	/**
	 * 美食ID错误
	 */
	public static final String DISH_DELETEDISH_DISHID_ERROR = "store.dish.deleteDish.dishId.error";
	
	/**
	 * 美食ID错误
	 */
	public static final String DISH_DELETEDISH_DISHID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(DISH_DELETEDISH_DISHID_ERROR);
	}
	
	/**
	 * 菜品不存在
	 */
	public static final String DISH_UNIFYDEALWITHDISH_DISH_NOT_EXIST = "store.dish.unifyDealWithDish.dish.empty";
	
	/**
	 * 菜品不存在
	 */
	public static final String DISH_UNIFYDEALWITHDISH_DISH_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISH_UNIFYDEALWITHDISH_DISH_NOT_EXIST);
	}
	
	/**
	 * 美食不相同
	 */
	public static final String DISH_UNIFYDEALWITHDISH_DISH_DIFFERENT = "store.dish.unifyDealWithDish.dish.different";
	
	/**
	 * 美食不相同
	 */
	public static final String DISH_UNIFYDEALWITHDISH_DISH_DIFFERENT() {
		return MultiLanguageAdapter.fetchMessage(DISH_UNIFYDEALWITHDISH_DISH_DIFFERENT);
	}
	
	/**
	 * 修改美食对象失败
	 */
	public static final String DISH_UNIFYDEALWITHDISH_FAILURE = "store.dish.unifyDealWithDish.failure";
	
	/**
	 * 修改美食对象失败
	 */
	public static final String DISH_UNIFYDEALWITHDISH_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(DISH_UNIFYDEALWITHDISH_FAILURE);
	}
	
	/**
	 * 美食标签不存在
	 */
	public static final String DISH_UPDATEDISH_CATEGORY_NOT_EXIST = "store.dish.updateDish.category.empty";
	
	/**
	 * 美食标签不存在
	 */
	public static final String DISH_UPDATEDISH_CATEGORY_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISH_UPDATEDISH_CATEGORY_NOT_EXIST);
	}
	
	/**
	 * 不能删除全部规格
	 */
	public static final String DISH_UPDATEDISH_EXISTLISTSIZE_CANNOT_DELETE = "store.dish.updateDish.existListSize.cannot.delete";
	
	/**
	 * 不能删除全部规格
	 */
	public static final String DISH_UPDATEDISH_EXISTLISTSIZE_CANNOT_DELETE() {
		return MultiLanguageAdapter.fetchMessage(DISH_UPDATEDISH_EXISTLISTSIZE_CANNOT_DELETE);
	}
	
	/**
	 * 需要更新的规格不存在
	 */
	public static final String DISH_UPDATESPEC_SPEC_NOT_EXIST = "store.dish.updateSpec.spec.empty";
	
	/**
	 * 需要更新的规格不存在
	 */
	public static final String DISH_UPDATESPEC_SPEC_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISH_UPDATESPEC_SPEC_NOT_EXIST);
	}
	
	/**
	 * 规格备注不存在
	 */
	public static final String DISH_UPDATESPEC_SUPPLEMENT_NOT_EXIST = "store.dish.updateSpec.supplement.empty";
	
	/**
	 * 规格备注不存在
	 */
	public static final String DISH_UPDATESPEC_SUPPLEMENT_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(DISH_UPDATESPEC_SUPPLEMENT_NOT_EXIST);
	}
	
	/**
	 * 未知的服务类型：{0}
	 */
	public static final String EXTINRANGE_ERROR = "store.extStore.inRange.error";
	
	/**
	 * 未知的服务类型：{0}
	 */
	public static final String EXTINRANGE_ERROR(short cutType) {
		return MultiLanguageAdapter.fetchMessage(EXTINRANGE_ERROR, cutType);
	}
	
	/**
	 * 无效的邀请码
	 */
	public static final String TOREINVITECODE_APPLY_INVALID_CODE = "store.storeInviteCode.apply.invalid.code";
	
	/**
	 * 无效的邀请码
	 */
	public static final String TOREINVITECODE_APPLY_INVALID_CODE() {
		return MultiLanguageAdapter.fetchMessage(TOREINVITECODE_APPLY_INVALID_CODE);
	}
	
	/**
	 * 服务器繁忙,请重试 
	 */
	public static final String TOREINVITECODE_APPLY_ERROR = "store.storeInviteCode.apply.error";
	
	/**
	 * 服务器繁忙,请重试 
	 */
	public static final String TOREINVITECODE_APPLY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(TOREINVITECODE_APPLY_ERROR);
	}
	
	/**
	 * 获取门店余额相关信息失败
	 */
	public static final String STOREBALANCEINFO_GETSTOREBALANCEINFO_FAILURE = "store.storeBalanceInfo.getStoreBalanceInfo.failure";
	
	/**
	 * 获取门店余额相关信息失败
	 */
	public static final String STOREBALANCEINFO_GETSTOREBALANCEINFO_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCEINFO_GETSTOREBALANCEINFO_FAILURE);
	}
	
	/**
	 * 新增加的block为空
	 */
	public static final String RECOMMENDBLOCK_NEWADD_DTO_CAN_NOT_EMPTY = "store.recommendBlock.newAdd.dto.empty";
	
	/**
	 * 新增加的block为空
	 */
	public static final String RECOMMENDBLOCK_NEWADD_DTO_CAN_NOT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_NEWADD_DTO_CAN_NOT_EMPTY);
	}
	
	/**
	 * 需要启用的块不存在
	 */
	public static final String RECOMMENDBLOCK_DISABLEBLOCK_BLOCK_NOT_EXIST = "store.recommendBlock.enableBlock.block.empty";
	
	/**
	 * 需要启用的块不存在
	 */
	public static final String RECOMMENDBLOCK_DISABLEBLOCK_BLOCK_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_DISABLEBLOCK_BLOCK_NOT_EXIST);
	}
	
	/**
	 * 要修改的块数据不存在
	 */
	public static final String RECOMMENDBLOCK_UPDATEBLOCK_DATA_NOT_EXIST = "store.recommendBlock.updateBlock.data.empty";
	
	/**
	 * 要修改的块数据不存在
	 */
	public static final String RECOMMENDBLOCK_UPDATEBLOCK_DATA_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_UPDATEBLOCK_DATA_NOT_EXIST);
	}
	
	/**
	 * 没有任何需要调整的块
	 */
	public static final String RECOMMENDBLOCK_UPDATEBLOCKSEQUENCE_MAP_EMPTY = "store.recommendBlock.updateBlockSequence.map.empty";
	
	/**
	 * 没有任何需要调整的块
	 */
	public static final String RECOMMENDBLOCK_UPDATEBLOCKSEQUENCE_MAP_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_UPDATEBLOCKSEQUENCE_MAP_EMPTY);
	}
	
	/**
	 * 移动的块不存在,请刷新页面再操作
	 */
	public static final String RECOMMENDBLOCK_MVBLOCK_BLOCK_EMPTY = "store.recommendBlock.mvBlock.block.empty";
	
	/**
	 * 移动的块不存在,请刷新页面再操作
	 */
	public static final String RECOMMENDBLOCK_MVBLOCK_BLOCK_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_MVBLOCK_BLOCK_EMPTY);
	}
	
	/**
	 * 前面
	 */
	public static final String RECOMMENDBLOCK_JUMP2TOPEND_DIRECTION1 = "store.recommendBlock.jump2TopEnd.direction1";
	
	/**
	 * 前面
	 */
	public static final String RECOMMENDBLOCK_JUMP2TOPEND_DIRECTION1() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_JUMP2TOPEND_DIRECTION1);
	}
	
	/**
	 * 后面
	 */
	public static final String RECOMMENDBLOCK_JUMP2TOPEND_DIRECTION2 = "store.recommendBlock.jump2TopEnd.direction2";
	
	/**
	 * 后面
	 */
	public static final String RECOMMENDBLOCK_JUMP2TOPEND_DIRECTION2() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_JUMP2TOPEND_DIRECTION2);
	}
	
	/**
	 * 移动失败,请刷新页面确认后再试
	 */
	public static final String RECOMMENDBLOCK_MVBLOCK_FAILURE = "store.recommendBlock.mvBlock.failure";
	
	/**
	 * 移动失败,请刷新页面确认后再试
	 */
	public static final String RECOMMENDBLOCK_MVBLOCK_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_MVBLOCK_FAILURE);
	}
	
	/**
	 * 将cityCode{0}下block置为不可用时更新数据库失败
	 */
	public static final String RECOMMENDBLOCK_DISABLEALL_ERROR = "store.recommendBlock.disableAll.error";
	
	/**
	 * 将cityCode{0}下block置为不可用时更新数据库失败
	 */
	public static final String RECOMMENDBLOCK_DISABLEALL_ERROR(String cityCode) {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_DISABLEALL_ERROR, cityCode);
	}
	
	/**
	 * 新增加的item为空
	 */
	public static final String RECOMMENDITEM_NEWADD_DTO_EMPTY = "store.recommendItem.newAdd.dto.empty";
	
	/**
	 * 新增加的item为空
	 */
	public static final String RECOMMENDITEM_NEWADD_DTO_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NEWADD_DTO_EMPTY);
	}
	
	/**
	 * 数据错误, 没有指定数据项的所属类型
	 */
	public static final String RECOMMENDITEM_NEWADD_TARGET_ERROR = "store.recommendItem.newAdd.target.error";
	
	/**
	 * 数据错误, 没有指定数据项的所属类型
	 */
	public static final String RECOMMENDITEM_NEWADD_TARGET_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NEWADD_TARGET_ERROR);
	}
	
	/**
	 * 请输入数据项的名称
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_NAME_EMPTY = "store.recommendItem.necessaryCheck.name.empty";
	
	/**
	 * 请输入数据项的名称
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_NAME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_NAME_EMPTY);
	}
	
	/**
	 * 请上传数据项展示的图片
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG_EMPTY = "store.recommendItem.necessaryCheck.img.empty";
	
	/**
	 * 请上传数据项展示的图片
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_IMG_EMPTY);
	}
	
	/**
	 * 请设置调整的链接URL
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_URL_EMPTY = "store.recommendItem.necessaryCheck.url.empty";
	
	/**
	 * 请设置调整的链接URL
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_URL_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_URL_EMPTY);
	}
	
	/**
	 * 请设置分享出去时的展示缩略图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_SHAREIMG_EMPTY = "store.recommendItem.necessaryCheck.shareImg.empty";
	
	/**
	 * 请设置分享出去时的展示缩略图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_SHAREIMG_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_SHAREIMG_EMPTY);
	}
	
	/**
	 * 请设置分享出去时的展示标题
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_SHARETITLE_EMPTY = "store.recommendItem.necessaryCheck.shareTitle.empty";
	
	/**
	 * 请设置分享出去时的展示标题
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_SHARETITLE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_SHARETITLE_EMPTY);
	}
	
	/**
	 * 请设置分享出去时的展示摘要
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_SHARECONTENT_EMPTY = "store.recommendItem.necessaryCheck.shareContent.empty";
	
	/**
	 * 请设置分享出去时的展示摘要
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_SHARECONTENT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_SHARECONTENT_EMPTY);
	}
	
	/**
	 * 请选择需要展示的餐厅
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_TARGETID_ERROR = "store.recommendItem.necessaryCheck.targetId.error";
	
	/**
	 * 请选择需要展示的餐厅
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_TARGETID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_TARGETID_ERROR);
	}
	
	/**
	 * 请输入餐厅的名称
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_NAME2_EMPTY = "store.recommendItem.necessaryCheck.name2.empty";
	
	/**
	 * 请输入餐厅的名称
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_NAME2_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_NAME2_EMPTY);
	}
	
	/**
	 * 请上传餐厅的展示图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG2_EMPTY = "store.recommendItem.necessaryCheck.img2.empty";
	
	/**
	 * 请上传餐厅的展示图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG2_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_IMG2_EMPTY);
	}
	
	/**
	 * 请选择需要展示的美食
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_TARGETID2_ERROR = "store.recommendItem.necessaryCheck.targetId2.error";
	
	/**
	 * 请选择需要展示的美食
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_TARGETID2_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_TARGETID2_ERROR);
	}
	
	/**
	 * 请输入美食的名称
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_NAME3_EMPTY = "store.recommendItem.necessaryCheck.name3.empty";
	
	/**
	 * 请输入美食的名称
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_NAME3_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_NAME3_EMPTY);
	}
	
	/**
	 * 请上传美食的展示图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG3_EMPTY = "store.recommendItem.necessaryCheck.img3.empty";
	
	/**
	 * 请上传美食的展示图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG3_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_IMG3_EMPTY);
	}
	
	/**
	 * 请上传瀑布流的展示图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG4_EMPTY = "store.recommendItem.necessaryCheck.img4.empty";
	
	/**
	 * 请上传瀑布流的展示图
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_IMG4_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_IMG4_EMPTY);
	}
	
	/**
	 * 不支持的数据项类型,请重新选择
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_ERROR = "store.recommendItem.necessaryCheck.error";
	
	/**
	 * 不支持的数据项类型,请重新选择
	 */
	public static final String RECOMMENDITEM_NECESSARYCHECK_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_NECESSARYCHECK_ERROR);
	}
	
	/**
	 * 待删除的item不存在,请刷新页面再试
	 */
	public static final String RECOMMENDITEM_DELETEITEM_ITEM_EMPTY = "store.recommendItem.deleteItem.item.empty";
	
	/**
	 * 待删除的item不存在,请刷新页面再试
	 */
	public static final String RECOMMENDITEM_DELETEITEM_ITEM_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_DELETEITEM_ITEM_EMPTY);
	}
	
	/**
	 * 删除失败,请刷新页面再试
	 */
	public static final String RECOMMENDITEM_DELETEITEM_ERROR = "store.recommendItem.deleteItem.error";
	
	/**
	 * 删除失败,请刷新页面再试
	 */
	public static final String RECOMMENDITEM_DELETEITEM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_DELETEITEM_ERROR);
	}
	
	/**
	 * 移动的item记录不存在,请刷新页面再操作
	 */
	public static final String RECOMMENDITEM_MVITEM_ITEM_EMPTY = "store.recommendItem.mvItem.item.empty";
	
	/**
	 * 移动的item记录不存在,请刷新页面再操作
	 */
	public static final String RECOMMENDITEM_MVITEM_ITEM_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_MVITEM_ITEM_EMPTY);
	}
	
	/**
	 * 移动失败,请刷新页面确认后再试
	 */
	public static final String RECOMMENDITEM_MVITEM_ERROR = "store.recommendItem.mvItem.error";
	
	/**
	 * 移动失败,请刷新页面确认后再试
	 */
	public static final String RECOMMENDITEM_MVITEM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_MVITEM_ERROR);
	}
	
	/**
	 * 更新失败, 被更新的item项不存在, 请刷新页面确认后再试
	 */
	public static final String RECOMMENDITEM_UPDATEITEM_ERROR = "store.recommendItem.updateItem.error";
	
	/**
	 * 更新失败, 被更新的item项不存在, 请刷新页面确认后再试
	 */
	public static final String RECOMMENDITEM_UPDATEITEM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDITEM_UPDATEITEM_ERROR);
	}
	
	/**
	 * 动态内容超长
	 */
	public static final String STORENEWS_ADD_CONTENT_TOO_LONG = "store.storeNews.add.content.length.max";
	
	/**
	 * 动态内容超长
	 */
	public static final String STORENEWS_ADD_CONTENT_TOO_LONG() {
		return MultiLanguageAdapter.fetchMessage(STORENEWS_ADD_CONTENT_TOO_LONG);
	}
	
	/**
	 * 餐厅名称格式不合法！
	 */
	public static final String STORE_UPDATENAME_NEWNAME_ERROR = "store.store.updateName.newName.error";
	
	/**
	 * 餐厅名称格式不合法！
	 */
	public static final String STORE_UPDATENAME_NEWNAME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATENAME_NEWNAME_ERROR);
	}
	
	/**
	 * 同名的餐厅已经存在！
	 */
	public static final String STORE_UPDATENAME_NEWNAME_REPEAT = "store.store.updateName.newName.repeat";
	
	/**
	 * 同名的餐厅已经存在！
	 */
	public static final String STORE_UPDATENAME_NEWNAME_REPEAT() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATENAME_NEWNAME_REPEAT);
	}
	/**
	 * 同英文名的餐厅已经存在！
	 */
	public static final String STORE_UPDATENAME_NEWNAMEEN_REPEAT = "store.store.updateName.newNameEn.repeat";
	
	/**
	 * 同英文名的餐厅已经存在！
	 */
	public static final String STORE_UPDATENAME_NEWNAMEEN_REPEAT() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATENAME_NEWNAMEEN_REPEAT);
	}
	
	/**
	 * 已经修改过餐厅名称，不允许再修改了！
	 */
	public static final String STORE_UPDATENAME_UPDATEDATE_ERROR = "store.store.updateName.updateDate.error";
	
	/**
	 * 已经修改过餐厅名称，不允许再修改了！
	 */
	public static final String STORE_UPDATENAME_UPDATEDATE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATENAME_UPDATEDATE_ERROR);
	}
	
	/**
	 * 客户经理姓名不能为空
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_NAME_EMPTY = "store.store.updateClientServiceManager.name.empty";
	
	/**
	 * 客户经理姓名不能为空
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_NAME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATECLIENTSERVICEMANAGER_NAME_EMPTY);
	}
	
	/**
	 * 客户经理姓名过长
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_LENGTH_MAX = "store.store.updateClientServiceManager.length.max";
	
	/**
	 * 客户经理姓名过长
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATECLIENTSERVICEMANAGER_LENGTH_MAX);
	}
	
	/**
	 * 客户经理电话不能为空
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_PHONE_EMPTY = "store.store.updateClientServiceManager.phone.empty";
	
	/**
	 * 客户经理电话不能为空
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_PHONE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATECLIENTSERVICEMANAGER_PHONE_EMPTY);
	}
	
	/**
	 * 客户经理电话不正确
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_PHONE_ERROR = "store.store.updateClientServiceManager.phone.error";
	
	/**
	 * 客户经理电话不正确
	 */
	public static final String STORE_UPDATECLIENTSERVICEMANAGER_PHONE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATECLIENTSERVICEMANAGER_PHONE_ERROR);
	}
	
	/**
	 * 经度不能大于180.0
	 */
	public static final String STORE_UPDATEADDRESS_NEWLNG_ERROR = "store.store.updateAddress.newLng.error";
	
	/**
	 * 经度不能大于180.0
	 */
	public static final String STORE_UPDATEADDRESS_NEWLNG_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEADDRESS_NEWLNG_ERROR);
	}
	
	/**
	 * 纬度不能大于90.0
	 */
	public static final String STORE_UPDATEADDRESS_NEWLAT_ERROR = "store.store.updateAddress.newLat.error";
	
	/**
	 * 纬度不能大于90.0
	 */
	public static final String STORE_UPDATEADDRESS_NEWLAT_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEADDRESS_NEWLAT_ERROR);
	}
	
	/**
	 * 餐厅地址不能为空或不能超过200个字符
	 */
	public static final String STORE_UPDATEADDRESS_NEWADDRESS_LENGTH_ERROR = "store.store.updateAddress.newAddress.length.error";
	
	/**
	 * 餐厅地址不能为空或不能超过200个字符
	 */
	public static final String STORE_UPDATEADDRESS_NEWADDRESS_LENGTH_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEADDRESS_NEWADDRESS_LENGTH_ERROR);
	}
	
	/**
	 * 修改餐厅地址失败，餐厅可能不存在或地址没有任何改变
	 */
	public static final String STORE_UPDATEADDRESS_ERROR = "store.store.updateAddress.error";
	
	/**
	 * 修改餐厅地址失败，餐厅可能不存在或地址没有任何改变
	 */
	public static final String STORE_UPDATEADDRESS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEADDRESS_ERROR);
	}
	
	/**
	 * 营业时间不允许为null
	 */
	public static final String STORE_UPDATEOPENHOURS_HOURS_EMPTY = "store.store.updateOpenHours.hours.empty";
	
	/**
	 * 营业时间不允许为null
	 */
	public static final String STORE_UPDATEOPENHOURS_HOURS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEOPENHOURS_HOURS_EMPTY);
	}
	
	/**
	 * 修改营业时间失败，请重试
	 */
	public static final String STORE_UPDATEOPENHOURS_ERROR = "store.store.updateOpenHours.error";
	
	/**
	 * 修改营业时间失败，请重试
	 */
	public static final String STORE_UPDATEOPENHOURS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEOPENHOURS_ERROR);
	}
	
	/**
	 * 配送时间不允许为null
	 */
	public static final String STORE_UPDATESERVICEHOURS_HOURS_EMPTY = "store.store.updateServiceHours.hours.empty";
	
	/**
	 * 配送时间不允许为null
	 */
	public static final String STORE_UPDATESERVICEHOURS_HOURS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESERVICEHOURS_HOURS_EMPTY);
	}
	
	/**
	 * 截单时间不合法
	 */
	public static final String STORE_UPDATESERVICEHOURS_TIME_ERROR = "store.store.updateServiceHours.time.error";
	
	/**
	 * 截单时间不合法
	 */
	public static final String STORE_UPDATESERVICEHOURS_TIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESERVICEHOURS_TIME_ERROR);
	}
	
	/**
	 * 修改配送时间失败，请重试
	 */
	public static final String STORE_UPDATESERVICEHOURS_ERROR = "store.store.updateServiceHours.error";
	
	/**
	 * 修改配送时间失败，请重试
	 */
	public static final String STORE_UPDATESERVICEHOURS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESERVICEHOURS_ERROR);
	}
	
	/**
	 * 修改营业类型失败，请重试
	 */
	public static final String STORE_UPDATESERVICEHOURS_TYPE_ERROR = "store.store.updateServiceHours.type.error";
	
	/**
	 * 修改营业类型失败，请重试
	 */
	public static final String STORE_UPDATESERVICEHOURS_TYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESERVICEHOURS_TYPE_ERROR);
	}
	
	/**
	 * 修改餐厅截单时间失败
	 */
	public static final String STORE_UPDATESERVICEHOURS_TIME_FAILURE = "store.store.updateServiceHours.time.failure";
	
	/**
	 * 修改餐厅截单时间失败
	 */
	public static final String STORE_UPDATESERVICEHOURS_TIME_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESERVICEHOURS_TIME_FAILURE);
	}
	
	/**
	 * 店铺不存在，storeId={0}
	 */
	public static final String STORE_SETADVANCERESERVEDAYS_STORE_EMPTY = "store.store.setAdvanceReserveDays.store.empty";
	
	/**
	 * 店铺不存在，storeId={0}
	 */
	public static final String STORE_SETADVANCERESERVEDAYS_STORE_EMPTY(long storeId) {
		return MultiLanguageAdapter.fetchMessage(STORE_SETADVANCERESERVEDAYS_STORE_EMPTY, storeId);
	}
	
	/**
	 * 设置餐厅可以接受预定的天数失败，请重试
	 */
	public static final String STORE_SETADVANCERESERVEDAYS_FAILURE = "store.store.setAdvanceReserveDays.failure";
	
	/**
	 * 设置餐厅可以接受预定的天数失败，请重试
	 */
	public static final String STORE_SETADVANCERESERVEDAYS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STORE_SETADVANCERESERVEDAYS_FAILURE);
	}
	
	/**
	 * hours不允许为null
	 */
	public static final String STORE_CHECKHOURS_HOURS_EMPTY = "store.store.checkHours.hours.empty";
	
	/**
	 * hours不允许为null
	 */
	public static final String STORE_CHECKHOURS_HOURS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_HOURS_EMPTY);
	}
	
	/**
	 * 未知的数据列(%s)，应该是一个数字
	 */
	public static final String STORE_CHECKHOURS_KEY_ERROR = "store.store.checkHours.key.error";
	
	/**
	 * 未知的数据列(%s)，应该是一个数字
	 */
	public static final String STORE_CHECKHOURS_KEY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_KEY_ERROR);
	}
	
	/**
	 * 未知的数据列(%d)
	 */
	public static final String STORE_CHECKHOURS_DAYOFWEEK_ERROR = "store.store.checkHours.dayOfWeek.error";
	
	/**
	 * 未知的数据列(%d)
	 */
	public static final String STORE_CHECKHOURS_DAYOFWEEK_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_DAYOFWEEK_ERROR);
	}
	
	/**
	 * 数据列(%d)的内容为空
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_EMPTY = "store.store.checkHours.staEndTime.empty";
	
	/**
	 * 数据列(%d)的内容为空
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_STAENDTIME_EMPTY);
	}
	
	/**
	 * 数据列(%d)的内容不正确
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_CONTENT_ERROR = "store.store.checkHours.staEndTime.content.error";
	
	/**
	 * 数据列(%d)的内容不正确
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_CONTENT_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_STAENDTIME_CONTENT_ERROR);
	}
	
	/**
	 * 数据列(%d)的内容不在范围内
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_NOT_IN = "store.store.checkHours.staEndTime.not.in";
	
	/**
	 * 数据列(%d)的内容不在范围内
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_NOT_IN() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_STAENDTIME_NOT_IN);
	}
	
	/**
	 * 预订类餐厅的配送时间不允许跨天
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_ERROR = "store.store.checkHours.staEndTime.error";
	
	/**
	 * 预订类餐厅的配送时间不允许跨天
	 */
	public static final String STORE_CHECKHOURS_STAENDTIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKHOURS_STAENDTIME_ERROR);
	}
	
	/**
	 * 设置自提失败,请重试
	 */
	public static final String STORE_SELFPICKSET_ERROR = "store.store.selfpickSet.error";
	
	/**
	 * 设置自提失败,请重试
	 */
	public static final String STORE_SELFPICKSET_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_SELFPICKSET_ERROR);
	}
	
	/**
	 * 修改餐厅营业状态失败,请重试
	 */
	public static final String STORE_UPDATESTORESTATUSANDSEARCHVISIBILITY_FAILURE = "store.store.updateStoreStatusAndSearchVisibility.failure";
	
	/**
	 * 修改餐厅营业状态失败,请重试
	 */
	public static final String STORE_UPDATESTORESTATUSANDSEARCHVISIBILITY_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESTORESTATUSANDSEARCHVISIBILITY_FAILURE);
	}
	
	/**
	 * 餐厅名称长度不合法！
	 */
	public static final String STORE_ISSTORENAMEEXIST_STORENAME_ERROR = "store.store.isStoreNameExist.storeName.error";
	
	/**
	 * 餐厅名称长度不合法！
	 */
	public static final String STORE_ISSTORENAMEEXIST_STORENAME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_ISSTORENAMEEXIST_STORENAME_ERROR);
	}
	/**
	 * add by kimmy 2017-01-05  餐厅英文名称长度不合法！
	 */
	public static final String STORE_ISSTORENAMEEXIST_STORENAMEEN_ERROR = "store.store.isStoreNameExist.storeNameEn.error";
	
	/**
	 * add by kimmy 2017-01-05  餐厅英文名称长度不合法！
	 */
	public static final String STORE_ISSTORENAMEEXIST_STORENAMEEN_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_ISSTORENAMEEXIST_STORENAMEEN_ERROR);
	}
	
	/**
	 * 读取餐厅数据失败，请重试
	 */
	public static final String STORE_UPDATESTORE_DETAILS_EMPTY = "store.store.updateStore.details.empty";
	
	/**
	 * 读取餐厅数据失败，请重试
	 */
	public static final String STORE_UPDATESTORE_DETAILS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESTORE_DETAILS_EMPTY);
	}
	
	/**
	 * 餐厅数据更新失败，请重试
	 */
	public static final String STORE_UPDATESTORE_ERROR = "store.store.updateStore.error";
	
	/**
	 * 餐厅数据更新失败，请重试
	 */
	public static final String STORE_UPDATESTORE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESTORE_ERROR);
	}
	
	/**
	 * 餐厅封面图片更新失败
	 */
	public static final String STORE_UPDATESTORE_PIC_ERROR = "store.store.updateStore.pic.error";
	
	/**
	 * 餐厅封面图片更新失败
	 */
	public static final String STORE_UPDATESTORE_PIC_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESTORE_PIC_ERROR);
	}
	
	/**
	 * 美食主图片地址不能为空
	 */
	public static final String STORE_UPDATETOPICIMG_PIC_EMPTY = "store.store.updateTopicImg.pic.empty";
	
	/**
	 * 美食主图片地址不能为空
	 */
	public static final String STORE_UPDATETOPICIMG_PIC_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATETOPICIMG_PIC_EMPTY);
	}
	
	/**
	 * 修改封面图片出错，请重试
	 */
	public static final String STORE_UPDATETOPICIMG_PIC_ERROR = "store.store.updateTopicImg.pic.error";
	
	/**
	 * 修改封面图片出错，请重试
	 */
	public static final String STORE_UPDATETOPICIMG_PIC_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATETOPICIMG_PIC_ERROR);
	}
	
	/**
	 * 店主不存在
	 */
	public static final String STORE_GETSTOREINFORMATION_STOREOWNER_NOTEXIST = "store.store.getStoreInformation.storeowner.notexist";
	
	/**
	 * 店主不存在
	 */
	public static final String STORE_GETSTOREINFORMATION_STOREOWNER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STORE_GETSTOREINFORMATION_STOREOWNER_NOTEXIST);
	}
	
	/**
	 * 餐厅详情获取失败
	 */
	public static final String STORE_GETSTOREINFORMATION_ERROR = "store.store.getStoreInformation.error";
	
	/**
	 * 餐厅详情获取失败
	 */
	public static final String STORE_GETSTOREINFORMATION_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_GETSTOREINFORMATION_ERROR);
	}
	
	/**
	 * 餐厅已被封号，不能操作
	 */
	public static final String STORE_VALIDATESTOREAVAILABILITY_ERROR = "store.store.validateStoreAvailability.error";
	
	/**
	 * 餐厅已被封号，不能操作
	 */
	public static final String STORE_VALIDATESTOREAVAILABILITY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_VALIDATESTOREAVAILABILITY_ERROR);
	}
	
	/**
	 * 餐厅营业状态已改变，请重新确认
	 */
	public static final String STORE_VALIDATESTOREAVAILABILITY_SUCCESS = "store.store.validateStoreAvailability.success";
	
	/**
	 * 餐厅营业状态已改变，请重新确认
	 */
	public static final String STORE_VALIDATESTOREAVAILABILITY_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(STORE_VALIDATESTOREAVAILABILITY_SUCCESS);
	}
	
	/**
	 * 餐厅不存在，请重试
	 */
	public static final String STORE_STARTBUSINESS_STORE_EMPTY = "store.store.startBusiness.store.empty";
	
	/**
	 * 餐厅不存在，请重试
	 */
	public static final String STORE_STARTBUSINESS_STORE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STARTBUSINESS_STORE_EMPTY);
	}
	
	/**
	 * 餐厅认证信息为空
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_AUDIT_EMPTY = "store.store.checkAuditModuleBeforeStart.audit.empty";
	
	/**
	 * 餐厅认证信息为空
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_AUDIT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_AUDIT_EMPTY);
	}
	
	/**
	 * 实名认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_NAME_UNFINISHED = "store.store.checkAuditModuleBeforeStart.name.unfinished";
	
	/**
	 * 实名认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_NAME_UNFINISHED() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_NAME_UNFINISHED);
	}
	
	/**
	 * 食品安全承诺认证还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_SAFE_UNFINISHED = "store.store.checkAuditModuleBeforeStart.safe.unfinished";
	
	/**
	 * 食品安全承诺认证还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_SAFE_UNFINISHED() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_SAFE_UNFINISHED);
	}
	
	/**
	 * 健康认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_HEALTH_UNFINISHED = "store.store.checkAuditModuleBeforeStart.place.unfinished";
	
	/**
	 * 健康认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_HEALTH_UNFINISHED() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_HEALTH_UNFINISHED);
	}
	
	/**
	 * 营业执照认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_LICENSE_UNFINISHED = "store.store.checkAuditModuleBeforeStart.license.unfinished";
	
	/**
	 * 营业执照认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_LICENSE_UNFINISHED() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_LICENSE_UNFINISHED);
	}
	
	/**
	 * 资质证明认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_APTITUDE_UNFINISHED = "store.store.checkAuditModuleBeforeStart.aptitude.unfinished";
	
	/**
	 * 资质证明认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_APTITUDE_UNFINISHED() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_APTITUDE_UNFINISHED);
	}
	
	/**
	 * 您的餐厅已被冻结，无法营业，详细请咨询平台管理员
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_STATUS_ERROR = "store.store.checkStoreBeforeStart.status.error";
	
	/**
	 * 您的餐厅已被冻结，无法营业，详细请咨询平台管理员
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_STATUS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKSTOREBEFORESTART_STATUS_ERROR);
	}
	
	/**
	 * 封面图片还未设置，请到「营业」里把一个美食设置为封面
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_TOPICIMG_EMPTY = "store.store.checkStoreBeforeStart.topicImg.empty";
	
	/**
	 * 封面图片还未设置，请到「营业」里把一个美食设置为封面
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_TOPICIMG_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKSTOREBEFORESTART_TOPICIMG_EMPTY);
	}
	
	/**
	 * 营业时间还未设置，请到「我的-经营模式」中设置
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_OPENHOURS_ERROR = "store.store.checkStoreBeforeStart.openHours.error";
	
	/**
	 * 营业时间还未设置，请到「我的-经营模式」中设置
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_OPENHOURS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKSTOREBEFORESTART_OPENHOURS_ERROR);
	}
	
	/**
	 * 配送时间还未设置，请到「我的-经营模式」中设置
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_SERVICEHOURS_ERROR = "store.store.checkStoreBeforeStart.serviceHours.error";
	
	/**
	 * 配送时间还未设置，请到「我的-经营模式」中设置
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_SERVICEHOURS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKSTOREBEFORESTART_SERVICEHOURS_ERROR);
	}
	
	/**
	 * 餐厅营业类型不正确，请到「我的-经营模式」中设置
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_TYPE_ERROR = "store.store.checkStoreBeforeStart.type.error";
	
	/**
	 * 餐厅营业类型不正确，请到「我的-经营模式」中设置
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_TYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKSTOREBEFORESTART_TYPE_ERROR);
	}
	
	/**
	 * 配送范围还未设置，请到「我的-发货设置」里设置配送范围
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_SCOPE_NOTSET = "store.store.checkStoreBeforeStart.scope.notset";
	
	/**
	 * 配送范围还未设置，请到「我的-发货设置」里设置配送范围
	 */
	public static final String STORE_CHECKSTOREBEFORESTART_SCOPE_NOTSET() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKSTOREBEFORESTART_SCOPE_NOTSET);
	}
	
	/**
	 * 您还未添加美食，请到「营业」里添加
	 */
	public static final String STORE_CHECKDISHBEFORESTART_FOOD_NOTSET = "store.store.checkDishBeforeStart.food.notset";
	
	/**
	 * 您还未添加美食，请到「营业」里添加
	 */
	public static final String STORE_CHECKDISHBEFORESTART_FOOD_NOTSET() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKDISHBEFORESTART_FOOD_NOTSET);
	}
	
	/**
	 * 您还未上架任何可卖的美食，请到「营业」里操作
	 */
	public static final String STORE_CHECKDISHBEFORESTART_ON_SHELF_ERROR = "store.store.checkDishBeforeStart.on_shelf.error";
	
	/**
	 * 您还未上架任何可卖的美食，请到「营业」里操作
	 */
	public static final String STORE_CHECKDISHBEFORESTART_ON_SHELF_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKDISHBEFORESTART_ON_SHELF_ERROR);
	}
	
	/**
	 * 封号原因不能为空。
	 */
	public static final String STORE_BAN_REASON_EMPTY = "store.store.ban.reason.empty";
	
	/**
	 * 封号原因不能为空。
	 */
	public static final String STORE_BAN_REASON_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_BAN_REASON_EMPTY);
	}
	
	/**
	 * 餐厅没有被封，不能解封
	 */
	public static final String STORE_UNBAN_STORE_NOBAN = "store.store.unban.store.noban";
	
	/**
	 * 餐厅没有被封，不能解封
	 */
	public static final String STORE_UNBAN_STORE_NOBAN() {
		return MultiLanguageAdapter.fetchMessage(STORE_UNBAN_STORE_NOBAN);
	}
	
	/**
	 * 没有找到省份信息（cityCode：{0}）
	 */
	public static final String STORE_VALIDATECITYANDSET_AREA2_EMPTY = "store.store.validateCityAndSet.area2.empty";
	
	/**
	 * 没有找到省份信息（cityCode：{0}）
	 */
	public static final String STORE_VALIDATECITYANDSET_AREA2_EMPTY(String code) {
		return MultiLanguageAdapter.fetchMessage(STORE_VALIDATECITYANDSET_AREA2_EMPTY, code);
	}
	
	/**
	 * 没有找到国家信息（provinceCode：{0}）
	 */
	public static final String STORE_VALIDATECITYANDSET_AREA3_EMPTY = "store.store.validateCityAndSet.area3.empty";
	
	/**
	 * 没有找到国家信息（provinceCode：{0}）
	 */
	public static final String STORE_VALIDATECITYANDSET_AREA3_EMPTY(String code) {
		return MultiLanguageAdapter.fetchMessage(STORE_VALIDATECITYANDSET_AREA3_EMPTY, code);
	}
	
	/**
	 * 找不到该用户
	 */
	public static final String STORE_SETTERSTORESTAFFROLE_USER_EMPTY = "store.store.setterStoreStaffRole.user.empty";
	
	/**
	 * 找不到该用户
	 */
	public static final String STORE_SETTERSTORESTAFFROLE_USER_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_SETTERSTORESTAFFROLE_USER_EMPTY);
	}
	
	/**
	 * 您已经是餐厅员工，不能再开餐厅
	 */
	public static final String STORE_VALIDATE_ISSTAFF = "store.store.validate.isStaff";
	
	/**
	 * 您已经是餐厅员工，不能再开餐厅
	 */
	public static final String STORE_VALIDATE_ISSTAFF() {
		return MultiLanguageAdapter.fetchMessage(STORE_VALIDATE_ISSTAFF);
	}
	
	/**
	 * 经营者身份证号不能为空
	 */
	public static final String STORE_VALIDATE_IDENTITY_EMPTY = "store.store.validate.identity.empty";
	
	/**
	 * 经营者身份证号不能为空
	 */
	public static final String STORE_VALIDATE_IDENTITY_EMPTY(){
		return MultiLanguageAdapter.fetchMessage(STORE_VALIDATE_IDENTITY_EMPTY);
	}
	
	/**
	 * 昵称不能为空
	 */
	public static final String STORE_STORETRANSFER_NICKNAME_EMPTY = "store.store.storeTransfer.nickname.empty";
	
	/**
	 * 昵称不能为空
	 */
	public static final String STORE_STORETRANSFER_NICKNAME_EMPTY(){
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_NICKNAME_EMPTY);
	}
	
	/**
	 * 真实姓名不能为空
	 */
	public static final String STORE_STORETRANSFER_NAME_EMPTY = "store.store.storeTransfer.name.empty";
	
	/**
	 * 真实姓名不能为空
	 */
	public static final String STORE_STORETRANSFER_NAME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_NAME_EMPTY);
	}
	
	/**
	 * 证件号不能为空
	 */
	public static final String STORE_STORETRANSFER_IDENTITY_EMPTY = "store.store.storeTransfer.identity.empty";
	
	/**
	 * 证件号不能为空
	 */
	public static final String STORE_STORETRANSFER_IDENTITY_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_IDENTITY_EMPTY);
	}
	
	/**
	 * 找不到要转让的用户信息，请重试
	 */
	public static final String STORE_STORETRANSFER_USERPROFILE_EMPTY = "store.store.storeTransfer.userProfile.empty";
	
	/**
	 * 找不到要转让的用户信息，请重试
	 */
	public static final String STORE_STORETRANSFER_USERPROFILE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_USERPROFILE_EMPTY);
	}
	
	/**
	 * 餐厅转让失败：餐厅不存在
	 */
	public static final String STORE_STORETRANSFER_STOREPO_EMPTY = "store.store.storeTransfer.storePO.empty";
	
	/**
	 * 餐厅转让失败：餐厅不存在
	 */
	public static final String STORE_STORETRANSFER_STOREPO_EMPTY(){
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_STOREPO_EMPTY);
	}
	
	/**
	 * 用户已绑定其他餐厅, 不能转让
	 */
	public static final String STORE_STORETRANSFER_ROLELIST_NOTEMPTY = "store.store.storeTransfer.roleList.notempty";
	
	/**
	 * 用户已绑定其他餐厅, 不能转让
	 */
	public static final String STORE_STORETRANSFER_ROLELIST_NOTEMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_ROLELIST_NOTEMPTY);
	}
	
	/**
	 * 餐厅转让失败，无法更改用户，请重试
	 */
	public static final String STORE_STORETRANSFER_FAILUER = "store.store.storeTransfer.failuer";
	
	/**
	 * 餐厅转让失败，无法更改用户，请重试
	 */
	public static final String STORE_STORETRANSFER_FAILUER() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORETRANSFER_FAILUER);
	}
	
	/**
	 * 标签列表不允许为空
	 */
	public static final String STORE_UPDATESTORETAG_TAGIDS_EMPTY = "store.store.updateStoreTag.tagIds.empty";
	
	/**
	 * 标签列表不允许为空
	 */
	public static final String STORE_UPDATESTORETAG_TAGIDS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATESTORETAG_TAGIDS_EMPTY);
	}
	
	/**
	 * 角色为空
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_ROLEIDS_EMPTY = "store.storeStaffRole.editStaff.roleIds.empty";
	
	/**
	 * 角色为空
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_ROLEIDS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_ROLEIDS_EMPTY);
	}
	
	/**
	 * 员工姓名为空
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_NAME_EMPTY = "store.storeStaffRole.editStaff.name.empty";
	
	/**
	 * 员工姓名为空
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_NAME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_NAME_EMPTY);
	}
	
	/**
	 * 不存在的用户id
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_STAFFUID_NOT_EXIST = "store.storeStaffRole.editStaff.staffUid.not.exist";
	
	/**
	 * 不存在的用户id
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_STAFFUID_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_STAFFUID_NOT_EXIST);
	}
	
	/**
	 * 员工姓名过长，请检查您的输入
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_NAME_LENGTH_MAX = "store.storeStaffRole.editStaff.name.length.max";
	
	/**
	 * 员工姓名过长，请检查您的输入
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_NAME_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_NAME_LENGTH_MAX);
	}
	
	/**
	 * 被操作的用户不存在
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_USER_EMPTY = "store.storeStaffRole.editStaff.user.empty";
	
	/**
	 * 被操作的用户不存在
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_USER_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_USER_EMPTY);
	}
	
	/**
	 * 该餐厅已经不存在
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_STORE_EMPTY = "store.storeStaffRole.editStaff.store.empty";
	
	/**
	 * 该餐厅已经不存在
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_STORE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_STORE_EMPTY);
	}
	
	/**
	 * {0}已经是其他餐厅的员工，每个人只能加入一家餐厅
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_ALREADY_ISSTAFF = "store.storeStaffRole.editStaff.already.isStaff";
	
	/**
	 * {0}已经是其他餐厅的员工，每个人只能加入一家餐厅
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_ALREADY_ISSTAFF(String name) {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_ALREADY_ISSTAFF, name);
	}
	
	/**
	 * 添加了不存在的角色 
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_STAFF_NOT_EXIST = "store.storeStaffRole.editStaff.staff.not.exist";
	
	/**
	 * 添加了不存在的角色 
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_STAFF_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_STAFF_NOT_EXIST);
	}
	
	/**
	 * 不能操作店主这个角色
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_CANNOT_OPERATION = "store.storeStaffRole.editStaff.cannot.operation";
	
	/**
	 * 不能操作店主这个角色
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_CANNOT_OPERATION() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_CANNOT_OPERATION);
	}
	
	/**
	 * 目前最多只允许设置一名客服
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_CUSTOMERSERVICE_ERROR = "store.storeStaffRole.editStaff.customerService.error";
	
	/**
	 * 目前最多只允许设置一名客服
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_CUSTOMERSERVICE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_CUSTOMERSERVICE_ERROR);
	}
	
	/**
	 * 您没有权限操作，权限请咨询餐厅负责人
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_NO_PERMISSIONS = "store.storeStaffRole.editStaff.no.permissions";
	
	/**
	 * 您没有权限操作，权限请咨询餐厅负责人
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_NO_PERMISSIONS() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_NO_PERMISSIONS);
	}
	
	/**
	 * 您不能修改比自己权限高的员工的角色
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_CANNOT_EDIT = "store.storeStaffRole.editStaff.cannot.edit";
	
	/**
	 * 您不能修改比自己权限高的员工的角色
	 */
	public static final String STORE_STORESTAFFROLE_EDITSTAFF_CANNOT_EDIT() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_EDITSTAFF_CANNOT_EDIT);
	}
	
	/**
	 * 无权操作
	 */
	public static final String STORE_STORESTAFFROLE_DELETESTAFF_NO_RIGHT = "store.storeStaffRole.deleteStaff.no.right";
	
	/**
	 * 无权操作
	 */
	public static final String STORE_STORESTAFFROLE_DELETESTAFF_NO_RIGHT() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_DELETESTAFF_NO_RIGHT);
	}
	
	/**
	 * 找不到该员工信息
	 */
	public static final String STORE_STORESTAFFROLE_DELETESTAFF_STAFFUSERBIGROLE_EMPTY = "store.storeStaffRole.deleteStaff.staffUserBigRole.empty";
	
	/**
	 * 找不到该员工信息
	 */
	public static final String STORE_STORESTAFFROLE_DELETESTAFF_STAFFUSERBIGROLE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_DELETESTAFF_STAFFUSERBIGROLE_EMPTY);
	}
	
	/**
	 * 转让门店店主失败，请重试
	 */
	public static final String STORE_STORESTAFFROLE_TRANSFERSTOREOWNER_FAILURE = "store.storeStaffRole.transferStoreOwner.failure";
	
	/**
	 * 转让门店店主失败，请重试
	 */
	public static final String STORE_STORESTAFFROLE_TRANSFERSTOREOWNER_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STORE_STORESTAFFROLE_TRANSFERSTOREOWNER_FAILURE);
	}
	
	/**
	 * 餐厅下单附属信息不是合法json格式
	 */
	public static final String STORE_UPDATEFEATURE_FEATURE_NOJSON = "store.store.updateFeature.feature.nojson";
	
	/**
	 * 餐厅下单附属信息不是合法json格式
	 */
	public static final String STORE_UPDATEFEATURE_FEATURE_NOJSON() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEFEATURE_FEATURE_NOJSON);
	}
	
	/**
	 * 场所设施认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_PLACE_AUDIT_UNFINISHED = "store.store.checkAuditModuleBeforeStart.place.audit.unfinished";
	
	/**
	 * 场所设施认证信息还未完成
	 */
	public static final String STORE_CHECKAUDITMODULEBEFORESTART_PLACE_AUDIT_UNFINISHED() {
		return MultiLanguageAdapter.fetchMessage(STORE_CHECKAUDITMODULEBEFORESTART_PLACE_AUDIT_UNFINISHED);
	}
	
	/**
	 * 角色id错误
	 */
	public static final String STORESTAFFROLE_SAVEORUPDATE_ERROR = "store.storeStaffRole.saveOrUpdate.error";
	/**
	 * 角色id错误
	 */
	public static final String STORESTAFFROLE_SAVEORUPDATE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESTAFFROLE_SAVEORUPDATE_ERROR);
	}
	
	/**
	 * 您不能删除比自己权限高的员工
	 */
	public static final String STORESTAFFROLE_DELETESTAFF_ERROR = "store.storeStaffRole.deleteStaff.error";
	
	/**
	 * 您不能删除比自己权限高的员工
	 */
	public static final String STORESTAFFROLE_DELETESTAFF_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORESTAFFROLE_DELETESTAFF_ERROR);
	}
	
	/**
	 * 修改餐厅地址失败，餐厅不存在
	 */
	public static final String STORE_UPDATEADDRESS_STORE_EMPTY ="store.store.updateAddress.store.empty";
	
	/**
	 * 修改餐厅地址失败，餐厅不存在
	 */
	public static final String STORE_UPDATEADDRESS_STORE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORE_UPDATEADDRESS_STORE_EMPTY);
	}
	
	/**
	 * 需要停用的块不存在
	 */
	public static final String RECOMMENDBLOCK_DISABLEBLOCK_BLOCK_EMPTY = "store.recommendBlock.disableBlock.block.empty";
	
	/**
	 * 需要停用的块不存在
	 */
	public static final String RECOMMENDBLOCK_DISABLEBLOCK_BLOCK_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(RECOMMENDBLOCK_DISABLEBLOCK_BLOCK_EMPTY);
	}
	
	/**
	 * 餐厅未开张
	 */
	public static final String CHECKING_BUSINESS_MSG = "store.checking_business_msg";
	
	/**
	 * 餐厅未开张
	 */
	public static final String CHECKING_BUSINESS_MSG() {
		return MultiLanguageAdapter.fetchMessage(CHECKING_BUSINESS_MSG);
	}
	
	/**
	 * 餐厅即将开业
	 */
	public static final String COMING_SOON_BUSINESS_MSG = "store.coming_soon_business_msg";
	
	/**
	 * 餐厅即将开业
	 */
	public static final String COMING_SOON_BUSINESS_MSG() {
		return MultiLanguageAdapter.fetchMessage(COMING_SOON_BUSINESS_MSG);
	}
	
	/**
	 * 餐厅休假中
	 */
	public static final String IN_REST_BUSINESS_MSG = "store.in_rest_business_msg";
	
	/**
	 * 餐厅休假中
	 */
	public static final String IN_REST_BUSINESS_MSG() {
		return MultiLanguageAdapter.fetchMessage(IN_REST_BUSINESS_MSG);
	}
	
	/**
	 * 餐厅停业中
	 */
	public static final String CLOSED_BUSINESS_MSG = "store.closed_business_msg";
	
	/**
	 * 餐厅停业中
	 */
	public static final String CLOSED_BUSINESS_MSG() {
		return MultiLanguageAdapter.fetchMessage(CLOSED_BUSINESS_MSG);
	}
	
	/**
	 * 餐厅已结业
	 */
	public static final String FROST_BUSINESS_MSG = "store.frost_business_msg";
	
	/**
	 * 餐厅已结业
	 */
	public static final String FROST_BUSINESS_MSG() {
		return MultiLanguageAdapter.fetchMessage(FROST_BUSINESS_MSG);
	}
	
	/**
	 * 餐厅休息中
	 */
	public static final String OUT_BUSINESS_MSG = "store.out_business_msg";
	
	/**
	 * 餐厅休息中
	 */
	public static final String OUT_BUSINESS_MSG() {
		return MultiLanguageAdapter.fetchMessage(OUT_BUSINESS_MSG);
	}
	
	/**
	 * 今天
	 */
	public static final String STORE_TIME_TODAY = "store.time.today";

	/**
	 * 今天
	 */
	public static final String STORE_TIME_TODAY() {
		return MultiLanguageAdapter.fetchMessage(STORE_TIME_TODAY);
	}

	/**
	 * 明天
	 */
	public static final String STORE_TIME_TOMORROW = "store.time.tomorrow";

	/**
	 * 明天
	 */
	public static final String STORE_TIME_TOMORROW() {
		return MultiLanguageAdapter.fetchMessage(STORE_TIME_TOMORROW);
	}

	/**
	 * -次日
	 */
	public static final String STORE_TIME_NEXTDAY = "store.time.nextDay";

	/**
	 * -次日
	 */
	public static final String STORE_TIME_NEXTDAY() {
		return MultiLanguageAdapter.fetchMessage(STORE_TIME_NEXTDAY);
	}
	
	/**
	 * 分享的资讯专题不存在
	 */
	public static final String SHARERESOURCE_SHAREINFO_INFOSUBJECT_NOT_EXIST = "store.shareResource.shareinfo.infoSubject.notExist";
	
	/**
	 * 分享的资讯专题不存在
	 */
	public static final String SHARERESOURCE_SHAREINFO_INFOSUBJECT_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARERESOURCE_SHAREINFO_INFOSUBJECT_NOT_EXIST);
	}
	
	/**
	 * 此资讯专题不可分享
	 */
	public static final String SHARERESOURCE_SHAREINFO_INFOSUBJECT_CAN_NOT_SHARE = "store.shareResource.shareinfo.infoSubject.cannotShare";
	
	/**
	 *  此资讯专题不可分享
	 */
	public static final String SHARERESOURCE_SHAREINFO_INFOSUBJECT_CAN_NOT_SHARE() {
		return MultiLanguageAdapter.fetchMessage(SHARERESOURCE_SHAREINFO_INFOSUBJECT_CAN_NOT_SHARE);
	}
	
	/**
	 * 分享的资讯餐厅不存在
	 */
	public static final String SHARERESOURCE_SHAREINFO_INFOSTORE_NOT_EXIST = "store.shareResource.shareinfo.infoStore.notExist";
	
	/**
	 * 分享的资讯餐厅不存在
	 */
	public static final String SHARERESOURCE_SHAREINFO_INFOSTORE_NOT_EXIST() {
		return MultiLanguageAdapter.fetchMessage(SHARERESOURCE_SHAREINFO_INFOSTORE_NOT_EXIST);
	}
	
	
	/**
	 * 关注类型不合法
	 */
	public static final String STORENEWSFAVRESOURCE_FAVTYPE_ILLEGAL = "store.storeNewsFavResource.favType.illegal";
	
	/**
	 * 关注类型不合法
	 */
	public static final String STORENEWSFAVRESOURCE_FAVTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(STORENEWSFAVRESOURCE_FAVTYPE_ILLEGAL);
	}
	
	/**
	 * type参数不正确
	 */
	public static final String STORENEWSFAVRESOURCE_TYPE_ERROR = "store.storeNewsFavResource.type.error";
	
	/**
	 * type参数不正确
	 */
	public static final String STORENEWSFAVRESOURCE_TYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STORENEWSFAVRESOURCE_TYPE_ERROR);
	}
	
	/**
	 * 餐厅资讯详情ID不合法
	 */
	public static final String STORENEWSRESOURCE_SINFOID_ILLEGAL = "store.storeNewsResource.sinfoId.illegal";
	
	/**
	 * 餐厅资讯详情ID不合法
	 */
	public static final String STORENEWSRESOURCE_SINFOID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(STORENEWSRESOURCE_SINFOID_ILLEGAL);
	}
	
	/**
	 * 请输入需要搜索的内容
	 */
	public static final String STORENEWSRESOURCE_QUERY_EMPTY = "store.storeNewsResource.query.empty";
	
	/**
	 * 请输入需要搜索的内容
	 */
	public static final String STORENEWSRESOURCE_QUERY_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STORENEWSRESOURCE_QUERY_EMPTY);
	}
	
	/**
	 * 资讯餐厅不存在
	 */
	public static final String STORENEWSRESOURCE_STOREINFO_NOTEXIST = "store.storeNewsResource.storeinfo.notexist";
	/**
	 * 资讯餐厅不存在
	 */
	public static final String STORENEWSRESOURCE_STOREINFO_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STORENEWSRESOURCE_STOREINFO_NOTEXIST);
	}
	
	/**
	 * 操作无效，分组列表有变更，请重新排序
	 */
	public static final String GROUP_SEQUENCE_HAS_UPDATE = "store.group.sequence.has.update";
	
	/**
	 * 操作无效，分组列表有变更，请重新排序
	 */
	public static final String GROUP_SEQUENCE_HAS_UPDATE() {
		return MultiLanguageAdapter.fetchMessage(GROUP_SEQUENCE_HAS_UPDATE);
	}
	
	/**
	 * 操作无效，美食列表有变更，请重新排序
	 */
	public static final String DISH_SEQUENCE_HAS_UPDATE = "store.dish.sequence.has.update";
	
	/**
	 * 操作无效，美食列表有变更，请重新排序
	 */
	public static final String DISH_SEQUENCE_HAS_UPDATE() {
		return MultiLanguageAdapter.fetchMessage(DISH_SEQUENCE_HAS_UPDATE);
	}
	
	/**
	 * 美食分组名不能含有表情符号
	 */
	public static final String DISH_GROUP_NAME_CONTENT_EMOJI = "store.dish.group.name.content.emoji";

	/**
	 * 美食分组名不能含有表情符号
	 */
	public static final String DISH_GROUP_NAME_CONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(DISH_GROUP_NAME_CONTENT_EMOJI);
	}
	
	/**
	 * 当餐厅是即将开放状态时，美食列表/详情页面‘+’代替为以下文字
	 */
	public static final String STORE_STATUS_COMING_SOON = "store.status.coming.soon";

	/**
	 * 当餐厅是即将开放状态时，美食列表/详情页面‘+’代替为以下文字
	 */
	public static final String STORE_STATUS_COMING_SOON() {
		return MultiLanguageAdapter.fetchMessage(STORE_STATUS_COMING_SOON);
	}
	
	/**
	 * 当餐厅是餐厅休假状态时，美食列表/详情页面‘+’代替为以下文字
	 */
	public static final String STORE_STATUS_CLOSED_NOW = "store.status.closed.now";

	/**
	 * 当餐厅是餐厅休假状态时，美食列表/详情页面‘+’代替为以下文字
	 */
	public static final String STORE_STATUS_CLOSED_NOW() {
		return MultiLanguageAdapter.fetchMessage(STORE_STATUS_CLOSED_NOW);
	}
	
	/**
	 * 当餐厅是结业、封店状态时，美食列表/详情页面‘+’代替为以下文字
	 */
	public static final String STORE_STATUS_CLOSED = "store.status.closed";

	/**
	 * 当餐厅是结业、封店状态时，美食列表/详情页面‘+’代替为以下文字
	 */
	public static final String STORE_STATUS_CLOSED() {
		return MultiLanguageAdapter.fetchMessage(STORE_STATUS_CLOSED);
	}
	
	/**
	 * 当美食是已下架状态时，‘+’代替为以下文字
	 */
	public static final String STORE_DISH_STATUS_NO_STOCK = "store.dish.status.no.stock";

	/**
	 * 当美食是已下架状态时，‘+’代替为以下文字
	 */
	public static final String STORE_DISH_STATUS_NO_STOCK() {
		return MultiLanguageAdapter.fetchMessage(STORE_DISH_STATUS_NO_STOCK);
	}
}
