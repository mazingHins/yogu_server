package com.yogu.core.web;

import com.yogu.core.web.exception.ServiceException;

/**
 * 商家的错误代码
 * 
 * @author linyi 2015/6/4.
 */
public class StoreErrorCode {

	// 2000~2999 是商家模块的错误代码

	// 基本：20

	/** 不属于任何门店 */
	public static final int NOT_BELONG = 2001;

	/** 不是门店内的员工 */
	public static final int NOT_BELONG_STAFF = 2002;
	/** 门店 角色员工已存在 **/
	public static final int STORE_STAFF_ALREADY_EXIST = 2003;
	
	/**
	 * 权限不够
	 */
	public static final int NO_AUTHORITY = 2004;
	
	

	/** 图片文件格式不支持 */
	public static final int FILE_FORMAT_NOT_SUPPORT = 2010;

	/** 图片文件数据错误 */
	public static final int FILE_DATA_ERROR = 2020;

	/** 图片文件太大 */
	public static final int FILE_TOO_LARGE = 2030;

	/** 图片文件读取失败 */
	public static final int FILE_READ_ERROR = 2031;

	/** 图片和排序长度不一致 */
	public static final int PIC_SEQUENCE_NOT_EQUAL = 2060;

	// 门店：21

	/** 门店已存在 */
	public static final int STORE_ALREADY_EXIST = 2101;

	/** 门店已被冻结 **/
	public static final int STORE_HAS_BEEN_LOCKED = 2102;

	/** 门店不存在 */
	public static final int STORE_NOT_EXIST = 2104;

	/** 门店名称已经存在 **/
	public static final int STORE_NAME_ALREADY_EXIST = 2105;
	
	/**add by kimmy 2017-01-05  门店英文名称已经存在 **/
	public static final int STORE_NAME_EN_ALREADY_EXIST = 2140;

	/** 门店主图未设置 **/
	public static final int STORE_TOPIC_IMAGE_NOT_SET = 2106;

	/** 门店还未添加任何菜品 **/
	public static final int STORE_HAS_NO_DISH = 2107;

	/** 门店没有任何菜品上架, 没有任何可卖的菜品 **/
	public static final int STORE_HAS_NO_DISH_ON_SHELF = 2108;

	/** 门店营业时间未设置 **/
	public static final int STORE_OPEN_HOURS_NOT_SET = 2109;

	/** 注册门店时的 用户验证信息参数错误 **/
	public static final int REG_PARAMS_ERROR = 2110;

	/** 注册门店时的 门店信息参数错误 **/
	public static final int AUDIT_PARAMS_ERROR = 2120;

	/** 修改门店信息失败 **/
	public static final int UPDATE_STORE_FAILED = 2130; // TODO wiki要更新说明

	/** 修改门店详情信息失败 **/
	public static final int UPDATE_STORE_DETAILS_FAILED = 2131; // TODO wiki要更新说明
	// 菜品：22

	/** 美食不存在 */
	public static final int DISH_NOT_EXIST = 204;

	/** 新的 dailyNum 小于用户预定的数量 */
	public static final int DISH_DAILY_NUM_SMALLER_THAN_CONSUMED = 2210;// TODO wiki要更新说明
	
	/**
	 * 菜品不支持删除
	 */
	public static final int DISH_CANNOT_BE_DELETED = 2220;

	// 菜品分类：23

	/** 美食品种不存在 */
	public static final int DISH_CATEGORY_NOT_EXIST = 2304;

	// 配送范围：24

	/** 配送范围不存在 */
	public static final int SERVICE_RANGE_NOT_EXIST = 2404;

	// 认证模块 : 25

	/** 用户不是店主,门店注册者 */
	public static final int USER_IS_NOT_MASTER = 2501;

	// 已完成通过
	/** 用户认证信息已经存在 且已通过认证 **/
	public static final int USER_AUDIT_ALREADY_PASSED = 2502;
	/** 场所认证信息已经存在且已通过认证 **/
	public static final int FACILITY_AUDIT_ALREADY_PASSED = 2503;
	/** 健康认证信息已经存在且已通过认证 **/
	public static final int HEALTH_AUDIT_ALREADY_PASSED = 2504;
	/** 营业执照信息已经存在且已通过认证 **/
	public static final int BUSINESS_LICENSE_AUDIT_ALREADY_PASSED = 2510;
	/** 资质证件信息已经存在且已通过认证 **/
	public static final int CATERING_CERTIFICATION_AUDIT_ALREADY_PASSED = 2511;
	

	// 未完成 通过
	/** 实名认证 信息需要待完善 **/
	public static final int USER_AUDIT_NEED_COMPLETED = 2506;
	/** 场所设施认证 信息需要待完善 **/
	public static final int FACILITY_AUDIT_NEED_COMPLETED = 2507;
	/** 健康认证 信息需要待完善 **/
	public static final int HEALTH_AUDIT_NEED_COMPLETED = 2508;
	/** 食品安全承诺 信息需要待完善 **/
	public static final int SAFE_PROMISE_AUDIT_NEED_COMPLETED = 2509;
	/** 营业执照信息正在审核中 **/
	public static final int BUSINESS_LICENSE_AUDIT_NEED_COMPLETED = 2512;
	/** 资质证件信息正在审核中 **/
	public static final int CATERING_CERTIFICATION_AUDIT_NEED_COMPLETED = 2513;

	// 员工模块 26

	/** 禁止员工管理模块 添加 店主角色 **/
	public static final int FORBID_TO_ADD_A_ROLE_OF_BOSS = 2601;

	/**
	 * 用户非店主且为其他门店下的员工,不能开餐厅
	 */
	public static final int USER_IS_A_STAFF_CANNOT_OPEN_STORE = 2602;

	/**
	 * 禁止设置餐厅A的店主 为餐厅B下的员工
	 */
	public static final int CANNOT_ADD_A_BOSS_TO_BE_OTHER_STORES_ROLE = 2603;

	/**
	 * 禁止添加A餐厅的员工为 餐厅B下的员工
	 */
	public static final int CANNOT_ADD_A_STAFF_TO_BE_OTHER_STORES_STAFF = 2603;

	// 门店状态 27

	/** 门店已被冻结 不可用 **/
	public static final int STORE_HAS_BEEN_FROZEN = 2701;

	/** 门店没有被冻结，不能解冻 ten 2015/9/23 */
	public static final int STORE_NOT_BEEN_FROZEN = 2702;

	// other: 29

	/** 服务器内部错误 */
	public static final int SERVER_ERROR = 2900;

	/**
	 * 邀请码错误
	 */
	public static final int STORE_INVITE_CODE_ERROR = 2901;
	/**
	 * 无效的邀请码
	 */
	public static final int STORE_INVITE_CODE_INVALID = 2902;
	
	
	/**
	 * 餐厅没有可选的配送时间
	 */
	public static final int STORE_NOT_OPTIONAL_SERVICE_TIME = 2910;
	
	/**
	 * 餐厅营业状态已经更新
	 */
	public static final int STORE_STATUS_HAS_NEWEST = 2911;

	/**
	 * 餐厅美食/分组排序操作号过期
	 */
	public static final int STORE_SEQUENCE_OPERATION_EXPIRED = 2920;
	
	/**
	 * 得到一个门店不存在的ServiceException
	 */
	public static ServiceException storeNotExist(long storeId) {
		return new ServiceException(STORE_NOT_EXIST, String.format("门店(ID:%d)不存在！", storeId));
	}

}
