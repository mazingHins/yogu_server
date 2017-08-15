package com.yogu.core.remote.config;

/**
 * 配置分组（mz_config表group_code列） <br>
 * 
 * <br>
 * JFan - 2015年6月30日 上午11:15:36
 */
public class ConfigGroupConstants {

	/** 云服务相关配置 */
	public static final String CLOUD = "cloud";

	/** 颁发给APP的key - secret配置 */
	public static final String APP_KEY = "appKey";

	/** 图片服务器配置列表 */
	public static final String IMG_SERVER = "imgServer";

	/** 给APP的配置信息 */
	public static final String APP_CONFIG = "appConfig";

	/**
	 * 分享功能文案模版
	 */
	public static final String SHARE_INFO = "shareInfo";

	/** 给APP的开关配置 */
	public static final String APP_SWITCH = "appSwitch";

	/** 热门搜索配置 */
	public static final String HOT_SEARCH = "hotSearch";

	/** IM相关配置 */
	public static final String IM_CONFIG = "imConfig";

	/** 接口访问权限校验（部分接口受限） */
	public static final String DOMAIN_IP_WHITE = "permitValidate";

	/** 商家拒绝订单备注 */
	public static final String STORE_REJECT_REMARK = "storeRejectRemarks";
	
	/** 已完成的订单商家拒绝订单备注 */
	public static final String FINISH_REJECT_REMARK = "finishRejectRemarks";
	
	/** 值班人员的联系方式 */
	public static final String ALARM_WATCHER = "watcher";

	// /** 短信内容模板 */ 已在SmsConfig定义
	// public static final String SMS_TEMPLATE = "smsTemplate";

	/** 邮件标题模板 */
	public static final String EMAIL_TEMPLATE = "emailTemplate";

	/** 服务端相关配置模板 */
	public static final String SERVER_CONFIG = "serverConfig";

	/** 支付宝接口配置 */
	public static final String ALIPAY = "alipay";
	
	/** 支付宝接口配置 */
	public static final String ALIPAY2 = "alipay2";

	/** 门店相关的配置 */
	public static final String STORE = "store";
	/**
	 * 版本更新相关提示
	 */
	public static final String UPGRADE = "upgrade";

	/**
	 * 微信公众号 app 的资料 group 2016/1/7 ten
	 */
	public static final String WEIXIN_WP = "weixin_mp";

	/**
	 * 微信支付/退款的资料group 2016-02-01 hins
	 */
	public static final String WEIXIN_PAY = "weixin_pay";

	// ---new add by sky 2016-02-27
	/**
	 * 新注册用户派发 优惠券礼包 group_code 配置
	 */
	public static final String COUPON_GIFT = "couponGift";
	/**
	 * 新注册用户派发 优惠券礼包 config_key 配置（优惠券礼包类型）
	 */
	public static final String GIFT_OF_COUPON_BAG = "gift_of_coupon_bag";
	/**
	 * 新注册用户派发 优惠券礼包 config_key 配置（优惠券类型）
	 */
	public static final String GIFT_OF_COUPON = "gift_of_coupon";
	// --sky add end

	/**
	 * 用户下单成功后的礼券开关 group_code
	 */
	public static final String ORDER_SUCCESS_GIFT_CONTROL = "orderSuccessGift";
	/**
	 * 用户下单成功后的礼券开关 config_key 配置
	 */
	public static final String ORDER_SUCCESS_GIFT_CONTROL_VALUE = "order_success_gift_control_value";
	/**
	 * 下单成功返券，线上  订单金额限制值  config_key配置， 为0表示没有限制，单位元
	 */
	public static final String ORDER_SUCCESS_GIFT_ORDER_AMOUNT = "order_success_gift_order_amount";
	/**
	 * 下单成功返券，米星付订单 金额限制 config_key配置， 为0表示没有限制，单位元
	 */
	public static final String ORDER_SUCCESS_GIFT_PAY_ORDER_AMOUNT = "order_success_gift_pay_order_amount";

	/**
	 * 用户评论红包 group_code
	 */
	public static final String COMMENT_GIFT = "commentSuccessGift";
	/**
	 * 用户 评论 成功后的礼券id，可以是多个id的串 config_key 配置
	 */
	public static final String COMMENT_SUCCESS_GIFT_ID_VALUE = "comment_success_gift_id_value";
	
	
	/**
	 * 启动图控制  group_code
	 */
	public static final String START_IMG_CONTROL = "startImgControl";
	/**
	 * 启动图 config_key， (兼容中英文)
	 */
	public static final String START_IMG_ = "start_img_"; //add by Sky 2017-01-10
	
	
	
	/**
	 * 评论有红包提示文案(兼容中英文)
	 */
	public static final String COMMENT_GIFT_TIP_ = "comment_gift_tip_";
	
	/**
	 * 评论是否有红包 ， 开关, 1 表示开;0 表示关
	 */
	public static final String COMMENT_GIFT_CONTROL = "comment_gift_control";
	
	
	/**
	 * 优惠券监控group_code
	 */
	public static final String COUPON_ALARM = "couponAlarm";
	
	/**
	 * 优惠券卡包中优惠券剩余数量监控config_key
	 */
	public static final String COUPON_BAG_ALARM_REMAIN_COUNT = "coupon_bag_remain_count";
	
	/**
	 * 优惠券卡包中优惠券剩余数量监控 ， email 接收人 config_key
	 */
	public static final String COUPON_BAG_ALARM_RECEIVER = "coupon_bag_alarm_receiver";
	
	/**
	 * 优惠券卡包中优惠券剩余数量监控 ，领取率 config_key
	 */
	public static final String COUPON_BAG_ALARM_RATE = "coupon_bag_alarm_rate";
	
	//优惠券过期提醒start 2016-10-27 sky
	/**
	 * 优惠券过期提醒groupCode
	 */
	public static final String COUPON_EXPIRE_NOTIFY = "couponExpireNotify";
	/**
	 * 优惠券过期提醒 周期,如3天提醒一次相同用户config_key
	 */
	public static final String COUPON_EXPIRE_NOTIFY_DAY_STEP = "coupon_expire_notify_day_step";
	/**
	 * 优惠券提醒 开始时间,比如3天后将过期config_key
	 */
	public static final String COUPON_EXPIRE_DAY_TO = "coupon_expire_day_to";
	
	/**
	 * 优惠券提醒，push方式通知 config_key
	 */
	public static final String COUPON_EXPIRE_NOTIFY_WAY_PUSH = "coupon_expire_notify_way_push";
	
	/**
	 * 其他配送方 group_code
	 */
	public static final String  OTHER_DELIVERY = "other_delivery";
	/**
	 * 其他配送方 开关控制字段 config_key， 1表示开启，0表示关闭
	 */
	public static final String OTHER_DELIVERY_SF_CONTROL = "other_delivery_sf_control";
	/**
	 * 优惠券提醒，短信方式通知 config_key
	 */
	public static final String COUPON_EXPIRE_NOTIFY_WAY_SMS = "coupon_expire_notify_way_sms";
	
	//优惠券过期提醒end

	/**
	 * 我的优惠券提示 groupCode
	 */
	public static final String COUPON_TIP = "couponTip";

	/**
	 * 我的优惠券提示 config_key（分语言，中文、英文）
	 */
	public static final String MY_COUPON_TIP_ = "my_coupon_tip_";

	// 邮件通知管理者 去处理 退款、提现 start
	/**
	 * 有退款、提现 的邮件通知
	 */
	public static final String REFUND_WITHDRAW_DEAL_NOTIFY = "rewiDealNotify";
	/**
	 * 有退款、提现 通知处理的管理者,可多个,用英文逗号‘,’分隔
	 */
	public static final String REFUND_WITHDRAW_DEAL_NOTIFY_RECEIVER = "receiver";
	/**
	 * 有退款、提现 通知处理的时间段, 格式：8-12,14-23
	 */
	public static final String REFUND_WITHDRAW_DEAL_NOTIFY_PERIOD = "notifyPeriod";

	/**
	 * 优惠券创建时的邮件通知 group code
	 */
	public static final String COUPON_CREATE_NOTIFY = "couponCreateNotify";// group code
	/**
	 * 优惠券创建时的邮件通知 config key
	 */
	public static final String COUPON_CREATE_NOTIFY_RECEIVER = "notifyReceiver";// config_key

	// 邮件通知管理者 去处理 退款、提现 end

	/**
	 * IOS push 证书秘钥
	 */
	public static final String IOS_PUSH_CERTSECRET = "iosPush";

	/** configp配置表-conffig_key的值：定时任务-自动取消新单超时未送达的订单，超过时间段(秒) */
	public static final String CONFIG_KEY_CANCEL_PENDING_ORDER_TIME = "autoCancelPendingTime";

	/** configp配置表-conffig_key的值：定时任务-自动取接单后超时未送达的订单，超过时间段(秒) */
	public static final String CONFIG_KEY_CANCEL_ACCEPT_ORDER_TIME = "autoCancelAcceptTime";

	/** configp配置表-conffig_key的值：定时任务-自动取消制作完成后超时未送达的订单，超过时间段(秒) */
	public static final String CONFIG_KEY_CANCEL_COOKING_ORDER_TIME = "autoCancelCookingTime";

	/** configp配置表-conffig_key的值：开店时，资质证件是否必传 */
	public static final String CONFIG_KEY_NEED_AUDIT_CERTIFICATE = "need_audit_certificate";

	/**
	 * 纯预定餐厅 门店详情页展示的 预定信息
	 */
	public static final String CONFIG_KEY_ADVANCE_BOOK_STORE_BOOK_TIP = "bookTip";

	/**
	 * 版本升级推送提示
	 */
	public static final String CONFIG_KEY_VERSION_UPGRADE_PUSH_TIP = "upgradePushTip";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未接单后, 通知商家接单 */
	public static final String OVER_DELIVERY_NOT_ACCEPT_NOTIFY_TIME = "overDeliveryNotAcceptNotifyTime";

	/** configp配置表-conffig_key的值: 起始时间, 用于超过预计送达一定时间(秒)还未接单后, 通知商家接单 */
	public static final String OVER_DELIVERY_NOT_ACCEPT_NOTIFY_RANGE = "overDeliveryNotAcceptNotifyRange";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未配送后, 通知商家配送 */
	public static final String OVER_DELIVERY_NOT_DELIVER_NOTIFY_STORE_TIME = "overDeliveryNotDeliverNotifyStoreTime";

	/** configp配置表-conffig_key的值: 起始时间, 用于超过预计送达一定时间(秒)还未配送后, 通知商家配送 */
	public static final String OVER_DELIVERY_NOT_DELIVER_NOTIFY_STORE_RANGE = "overDeliveryNotDeliverNotifyStoreRange";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未配送后, 通知顾问联系商家 */
	public static final String OVER_DELIVERY_NOT_DELIVER_NOTIFY_CONSULTANT_TIME = "overDeliveryNotDeliverNotifyConsultantTime";

	/** configp配置表-conffig_key的值: 起始时间, 用于超过预计送达一定时间(秒)还未配送后, 通知顾问联系商家 */
	public static final String OVER_DELIVERY_NOT_DELIVER_NOTIFY_CONSULTANT_RANGE = "overDeliveryNotDeliverNotifyConsultantRange";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未配送后, 再次通知商家配送 */
	public static final String OVER_DELIVERY_NOT_DELIVER_NOTIFY_STORE_AGAIN_TIME = "overDeliveryNotDeliverNotifyStoreAgainTime";

	/** configp配置表-conffig_key的值: 起始时间, 用于超时时间, 用于超过预计送达一定时间(秒)还未配送后, 再次通知商家配送 */
	public static final String OVER_DELIVERY_NOT_DELIVER_NOTIFY_STORE_AGAIN_RANGE = "overDeliveryNotDeliverNotifyStoreAgainRange";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未签收后, 再次通知用户签收或联系商家 */
	public static final String OVER_DELIVERY_NOT_CONFIRM_NOTIFY_USER_TIME = "overDeliveryNotConfirmNotifyUserTime";

	/** configp配置表-conffig_key的值: 起始时间, 用于超时时间, 用于超过预计送达一定时间(秒)还未签收后, 再次通知用户签收或联系商家 */
	public static final String OVER_DELIVERY_NOT_CONFIRM_NOTIFY_USER_RANGE = "overDeliveryNotConfirmNotifyUserRange";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未接单后, 通知客户经理 */
	public static final String OVER_DELIVERY_NOT_ACCEPT_NOTIFY_CONL_TIME = "overDeliveryNotAcceptNotifyConsultantTime";

	/** configp配置表-conffig_key的值: 起始时间, 用于超过预计送达一定时间(秒)还未接单后, 通知客户经理 */
	public static final String OVER_DELIVERY_NOT_ACCEPT_NOTIFY_CONL_RANGE = "overDeliveryNotAcceptNotifyConsultantRange";

	/** configp配置表-conffig_key的值: 超时时间, 用于超过预计送达一定时间(秒),自动配送 */
	public static final String AUTO_DELIVER_ORDER_TIME = "autoDeliverOrderTime";
	
	/** configp配置表-conffig_key的值: 顺丰最大配送距离，单位米 */
	public static final String SF_DISTANCE = "sfDistance";

	/** configp配置表-conffig_key的值: 起始时间, 用于超过预计送达一定时间(秒),自动配送 */
	public static final String AUTO_DELIVER_ORDER_RANGE = "autoDeliverOrderRange";
	
	/** configp配置表-conffig_key的值: 第三方超时没接单，就取消第三方配送。超时时间，单位秒 */
	public static final String OVER_EXPRESS_TIMEOUT_CANCEL_TIME = "overExpressTimeOutCancelTime";
	
	/** configp配置表-conffig_key的值: 商家还没开始对顺丰订单点击配送的超时时间，单位秒 */
	public static final String OVER_NOT_BEGIN_DELIVERY_EXPRESS_TIME = "overNotBeginDeliveryExpressTime";

	/**
	 * config配置表里，用来加密优惠券相关ID的key
	 * 
	 * @author ten 2016/1/21
	 */
	public static final String CONFIG_KEY_COUPON_ACCESSKEY = "couponIdAccesskey";

	/** configp配置表-config_key的值: 超时时间, 用于超过订单开始时间指定时间后(秒),发送通知商家 */
	public static final String OVER_ACCEPT_TIME_NOTIFY_STORE_TIME = "overAcceptTimeNotifyStoreTime";

	/** configp配置表-config_key的值: 起始时间, 用于超过订单开始时间指定时间后(秒),发送通知商家 */
	public static final String OVER_ACCEPT_TIME_NOTIFY_STORE_RANGE = "overAcceptTimeNotifyStoreRange";
	
	/** configp配置表-config_key的值: 取消超时没支付订单的-结束时间*/
	public static final String ORDER_NOT_PAY_TIMEOUT = "orderNotPayTimeOut";
	
	/** configp配置表-config_key的值: 取消超时没支付订单的-起始时间 */
	public static final String ORDER_NOT_PAY_TIMEOUT_RANGE = "orderNotPayTimeOutRange";

	// ---------------

	/** configp配置表-config_key的值: 超时时间, 用于超过订单开始时间指定时间后(秒),发送通知用户 */
	public static final String OVER_ACCEPT_TIME_NOTIFY_USER_TIME = "overAcceptTimeNotifyUserTime";

	/** configp配置表-config_key的值: 起始时间, 用于超过订单开始时间指定时间后(秒),发送通知用户 */
	public static final String OVER_ACCEPT_TIME_NOTIFY_USER_RANGE = "overAcceptTimeNotifyUserRange";

	/** configp配置表-config_key的值: 超时时间, 用于超过预计送达一定时间(秒)还未完成订单后,通知BD和客户经理 */
	public static final String OVER_DELIVERY_TIME_NOT_FINISH_TIME = "overDeliveryNotFinishNotifyTime";

	/** configp配置表-config_key的值: 起始时间, 用于超过预计送达一定时间(秒)还未完成订单后, 通知BD和客户经理 */
	public static final String OVER_DELIVERY_TIME_NOT_FINISH_RANGE = "overDeliveryNotFinishNotifyRange";

	/** configp配置表-config_key的值: 订单预计送达时间的提前时间, 单位秒, 用于订单进行到一半通知BD和客户经理 */
	public static final String AHEAD_OF_DELIVERY_TIME = "aheadOfDeliveryTime";

	/**
	 * BD部门的邮件组
	 */
	public static final String BD_EMAIL_GROUP = "BDEmailGroup";

	/**
	 * 商家每日运营前报告的提前通知时间(单位:分钟)
	 */
	public static final String STORE_DAILY_REPORT_ADV_NOTIFY_TIME = "storeDailyReportAdvNotifyTime";
	
	/** 顺丰接口配置 */
	public static final String SF_EXPRESS = "sfExpress";
	
	/** 返回给用户的最大可选定制标签池长度*/
	public static final String USER_CUSTOMIZE_POOL_SIZE = "userCustomizePoolSize";

	/** 限购美食配置 */
	public static final String LIMITED_PURCHASEING_DISH = "limitedPurchaseing";
	
	/** 平台是否启用支付宝支付的开关，1-启用；其他不启用 */
	public static final String ALIPAY_PAY_SWITCH = "alipayPaySwitch";
	
	/** 平台是否启用微信支付的开关，1-启用；其他不启用 */
	public static final String WECHAT_PAY_SWITCH = "wechatPaySwitch";
	
	/** 4.0全局搜索配置 */
	public static final String GLOBAL_SEARCH = "globalSearch";
	
	/** cblog指定菜系 */
	public static final String CBLOG_CUISINE = "cblogCuisine";
	
	/** cblog指定菜系 */
	public static final String CUISINE_CATEGORYID = "cuisineCategoryId";
	
	/**
	 * 是否实时到帐
	 */
	public static final String WITHDRAW_REAL_TIME = "withdraw_real_time";
	
	public static final String SWITCH = "switch";
	
}
