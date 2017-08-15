package com.yogu.language;

/**
 * 域pay 下的 多语言提示相关key的常量类<br>
 * 
 * 用于组织pay域下的异常提示、注解提示相关的常量key
 * 
 * @author sky 2016-03-25
 *
 */
public class PayMessages {
	/**
	 * 餐厅ID不能为空
	 */
	public static final String BALANCEADMIN_STOREID_CAN_NOT_BE_EMPTY = "pay.balanceAdmin.storeId.empty";

	/**
	 * 餐厅ID不能为空
	 */
	public static String BALANCEADMIN_STOREID_CAN_NOT_BE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(BALANCEADMIN_STOREID_CAN_NOT_BE_EMPTY);
	}

	/**
	 * 开始时间不能大于结束时间
	 */
	public static final String BALANCEADMIN_TIME_ERROR = "pay.balanceAdmin.time.error";

	/**
	 * 开始时间不能大于结束时间
	 */
	public static String BALANCEADMIN_TIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BALANCEADMIN_TIME_ERROR);
	}

	/**
	 * 门店ID不合法
	 */

	public static final String LOCALBALANCEAPI_GETSTOREINCOME_STOREID_ILLEGAL = "pay.localBalanceApi.getStoreIncome.storeId.illegal";

	/**
	 * 门店ID不合法
	 */

	public static String LOCALBALANCEAPI_GETSTOREINCOME_STOREID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETSTOREINCOME_STOREID_ILLEGAL);
	}

	/**
	 * 用户ID不合法
	 */
	public static final String LOCALBALANCEAPI_GETSTOREINCOME_USERID_ILLEGAL = "pay.localBalanceApi.getStoreIncome.userId.illegal";

	/**
	 * 用户ID不合法
	 */
	public static String LOCALBALANCEAPI_GETSTOREINCOME_USERID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETSTOREINCOME_USERID_ILLEGAL);
	}

	/**
	 * 请求编号不能为空
	 */
	public static final String LOCALBALANCEAPI_LOGBALANCEFLOW_REQUESTID_EMPTY = "pay.localBalanceApi.logBalanceFlow.requestId.empty";

	/**
	 * 请求编号不能为空
	 */
	public static String LOCALBALANCEAPI_LOGBALANCEFLOW_REQUESTID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_LOGBALANCEFLOW_REQUESTID_EMPTY);
	}

	/**
	 * 餐厅ID错误, 必须大于0
	 */
	public static final String LOCALBALANCEAPI_LOGBALANCEFLOW_STOREID_ERROR = "pay.localBalanceApi.logBalanceFlow.storeId.error";

	/**
	 * 餐厅ID错误, 必须大于0
	 */
	public static String LOCALBALANCEAPI_LOGBALANCEFLOW_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_LOGBALANCEFLOW_STOREID_ERROR);
	}

	/**
	 * 相关ID错误, 必须大于0
	 */
	public static final String LOCALBALANCEAPI_LOGBALANCEFLOW_REFERID_ERROR = "pay.localBalanceApi.logBalanceFlow.referId.error";

	/**
	 * 相关ID错误, 必须大于0
	 */
	public static String LOCALBALANCEAPI_LOGBALANCEFLOW_REFERID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_LOGBALANCEFLOW_REFERID_ERROR);
	}

	/**
	 * 货币类型不合法
	 */
	public static final String LOCALBALANCEAPI_LOGBALANCEFLOW_CURRENCYTYPE_ILLEGAL = "pay.localBalanceApi.logBalanceFlow.currencyType.illegal";

	/**
	 * 货币类型不合法
	 */
	public static String LOCALBALANCEAPI_LOGBALANCEFLOW_CURRENCYTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_LOGBALANCEFLOW_CURRENCYTYPE_ILLEGAL);
	}

	/**
	 * 请求信息不能为空
	 */
	public static final String LOCALBALANCEAPI_GETPAY_PARAMS_EMPTY = "pay.localBalanceApi.getPay.params.empty";

	/**
	 * 请求信息不能为空
	 */
	public static String LOCALBALANCEAPI_GETPAY_PARAMS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPAY_PARAMS_EMPTY);
	}

	/**
	 * 支付方式不合法
	 */
	public static final String LOCALBALANCEAPI_GETPAY_PARAMS_PAYMODE_ILLEGAL = "pay.localBalanceApi.getPay.params.payMode.illegal";

	/**
	 * 支付方式不合法
	 */
	public static String LOCALBALANCEAPI_GETPAY_PARAMS_PAYMODE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPAY_PARAMS_PAYMODE_ILLEGAL);
	}

	/**
	 * 支付货币不合法
	 */
	public static final String LOCALBALANCEAPI_GETPAY_PARAMS_CURRENCYTYPE_ILLEGAL = "pay.localBalanceApi.getPay.params.currencyType.illegal";

	/**
	 * 支付货币不合法
	 */
	public static String LOCALBALANCEAPI_GETPAY_PARAMS_CURRENCYTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPAY_PARAMS_CURRENCYTYPE_ILLEGAL);
	}

	/**
	 * 支付请求来源不合法
	 */
	public static final String LOCALBALANCEAPI_GETPAY_PARAMS_SOURCETYPE_ILLEGAL = "pay.localBalanceApi.getPay.params.sourceType.illegal";

	/**
	 * 支付请求来源不合法
	 */
	public static String LOCALBALANCEAPI_GETPAY_PARAMS_SOURCETYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPAY_PARAMS_SOURCETYPE_ILLEGAL);
	}

	/**
	 * 订单号参数不合法
	 */
	public static final String LOCALBALANCEAPI_GETPAY_PARAMS_TRADENO_ILLEGAL = "pay.localBalanceApi.getPay.params.tradeNo.illegal";

	/**
	 * 订单号参数不合法
	 */
	public static String LOCALBALANCEAPI_GETPAY_PARAMS_TRADENO_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPAY_PARAMS_TRADENO_ILLEGAL);
	}

	/**
	 * 支付费用参数不合法
	 */
	public static final String LOCALBALANCEAPI_GETPAY_PARAMS_TOTALFEE_ILLEGAL = "pay.localBalanceApi.getPay.params.totalFee.illegal";

	/**
	 * 支付费用参数不合法
	 */
	public static String LOCALBALANCEAPI_GETPAY_PARAMS_TOTALFEE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPAY_PARAMS_TOTALFEE_ILLEGAL);
	}

	/**
	 * 购买用户参数不合法
	 */
	public static final String LOCALBALANCEAPI_GETPARAMS_BUYERID_ILLEGAL = "pay.localBalanceApi.getPay.params.buyerId.illegal";

	/**
	 * 购买用户参数不合法
	 */
	public static String LOCALBALANCEAPI_GETPARAMS_BUYERID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPARAMS_BUYERID_ILLEGAL);
	}

	/**
	 * 商家用户参数不合法
	 */
	public static final String LOCALBALANCEAPI_GETPARAMS_SELLERUID_ILLEGAL = "pay.localBalanceApi.getPay.params.sellerUid.illegal";

	/**
	 * 商家用户参数不合法
	 */
	public static String LOCALBALANCEAPI_GETPARAMS_SELLERUID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPARAMS_SELLERUID_ILLEGAL);
	}

	/**
	 * 优惠券费用参数不合法
	 */
	public static final String LOCALBALANCEAPI_GETPARAMS_COUPONFEE_ILLEGAL = "pay.localBalanceApi.getPay.params.couponFee.illegal";

	/**
	 * 优惠券费用参数不合法
	 */
	public static String LOCALBALANCEAPI_GETPARAMS_COUPONFEE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPARAMS_COUPONFEE_ILLEGAL);
	}

	/**
	 * 终端IP参数不能为空
	 */
	public static final String LOCALBALANCEAPI_GETPARAMS_USERIP_EMPTY = "pay.localBalanceApi.getPay.params.userIp.empty";

	/**
	 * 终端IP参数不能为空
	 */
	public static String LOCALBALANCEAPI_GETPARAMS_USERIP_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPARAMS_USERIP_EMPTY);
	}

	/**
	 * 终端IP参数长度超过限制
	 */
	public static final String LOCALBALANCEAPI_GETPARAMS_USERIP_LENGTH_MAX = "pay.localBalanceApi.getPay.params.userIp.length.max";

	/**
	 * 终端IP参数长度超过限制
	 */
	public static String LOCALBALANCEAPI_GETPARAMS_USERIP_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPARAMS_USERIP_LENGTH_MAX);
	}

	/**
	 * 回调地址参数长度超过限制
	 */
	public static final String LOCALBALANCEAPI_GETPARAMS_NOTIFYURL_LENGTH_MAX = "pay.localBalanceApi.getPay.params.notifyUrl.length.max";

	/**
	 * 回调地址参数长度超过限制
	 */
	public static String LOCALBALANCEAPI_GETPARAMS_NOTIFYURL_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(LOCALBALANCEAPI_GETPARAMS_NOTIFYURL_LENGTH_MAX);
	}

	/**
	 * 支付方式不合法
	 */
	public static final String LOCALREFUND_QUERYBYNO_PAYMODE_ILLEGAL = "pay.localRefund.queryByNo.payMode.illegal";

	/**
	 * 支付方式不合法
	 */
	public static String LOCALREFUND_QUERYBYNO_PAYMODE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_QUERYBYNO_PAYMODE_ILLEGAL);
	}

	/**
	 * 编号已过期，请重新刷新
	 */
	public static final String LOCALREFUND_QUERYBYNO_NO_EXPIRE = "pay.localRefund.queryByNo.no.expire";

	/**
	 * 编号已过期，请重新刷新
	 */
	public static String LOCALREFUND_QUERYBYNO_NO_EXPIRE() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_QUERYBYNO_NO_EXPIRE);
	}

	/**
	 * 退款不存在
	 */
	public static final String LOCALREFUND_DEALWITHREFUND_REFUNDNO_ERROR = "pay.localRefund.dealWithRefund.refundNo.error";

	/**
	 * 退款不存在
	 */
	public static String LOCALREFUND_DEALWITHREFUND_REFUNDNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_DEALWITHREFUND_REFUNDNO_ERROR);
	}

	/**
	 * 退款结果参数不合法
	 */
	public static final String LOCALREFUND_DEALWITHREFUND_REFUNDSTATUS_ILLEGAL = "pay.localRefund.dealWithRefund.refundStatus.illegal";

	/**
	 * 退款结果参数不合法
	 */
	public static String LOCALREFUND_DEALWITHREFUND_REFUNDSTATUS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_DEALWITHREFUND_REFUNDSTATUS_ILLEGAL);
	}

	/**
	 * 退款流水号不合法
	 */
	public static final String LOCALREFUND_CREATEALIPAYDATA_REFUNDNO_ILLEGAL = "pay.localRefund.createAlipayData.refundNo.illegal";

	/**
	 * 退款流水号不合法
	 */
	public static String LOCALREFUND_CREATEALIPAYDATA_REFUNDNO_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_CREATEALIPAYDATA_REFUNDNO_ILLEGAL);
	}

	/**
	 * 请求数据为空
	 */
	public static final String LOCALREFUND_APPLYREFUND_PARAMS_EMPTY = "pay.localRefund.applyRefund.params.empty";

	/**
	 * 请求数据为空
	 */
	public static String LOCALREFUND_APPLYREFUND_PARAMS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_APPLYREFUND_PARAMS_EMPTY);
	}

	/**
	 * 支付编号为空
	 */
	public static final String LOCALREFUND_APPLYREFUND_PARAMS_PAYNO_EMPTY = "pay.localRefund.applyRefund.params.payNo.empty";

	/**
	 * 支付编号为空
	 */
	public static String LOCALREFUND_APPLYREFUND_PARAMS_PAYNO_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_APPLYREFUND_PARAMS_PAYNO_EMPTY);
	}

	/**
	 * 内部平台退款申请编号为空
	 */
	public static final String LOCALREFUND_APPLYREFUND_PARAMS_INTERNALREFUNDNO_EMPTY = "pay.localRefund.applyRefund.params.internalRefundNo.empty";

	/**
	 * 内部平台退款申请编号为空
	 */
	public static String LOCALREFUND_APPLYREFUND_PARAMS_INTERNALREFUNDNO_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_APPLYREFUND_PARAMS_INTERNALREFUNDNO_EMPTY);
	}

	/**
	 * 货币参数不合法
	 */
	public static final String LOCALREFUND_APPLYREFUND_PARAMS_CURRENCYTYPE_ILLEGAL = "pay.localRefund.applyRefund.params.currencyType.illegal";

	/**
	 * 货币参数不合法
	 */
	public static String LOCALREFUND_APPLYREFUND_PARAMS_CURRENCYTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALREFUND_APPLYREFUND_PARAMS_CURRENCYTYPE_ILLEGAL);
	}

	/**
	 * 用户ID不能为空
	 */
	public static final String LOCALWITHDRAW_APPLYWITHDRAW_UID_EMPTY = "pay.localWithdraw.applyWithdraw.uid.empty";

	/**
	 * 用户ID不能为空
	 */
	public static String LOCALWITHDRAW_APPLYWITHDRAW_UID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALWITHDRAW_APPLYWITHDRAW_UID_EMPTY);
	}

	/**
	 * 门店ID不能为空
	 */
	public static final String LOCALWITHDRAW_APPLYWITHDRAW_STOREID_EMPTY = "pay.localWithdraw.applyWithdraw.storeId.empty";

	/**
	 * 门店ID不能为空
	 */
	public static String LOCALWITHDRAW_APPLYWITHDRAW_STOREID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALWITHDRAW_APPLYWITHDRAW_STOREID_EMPTY);
	}

	/**
	 * 提现金额不能为空或0
	 */
	public static final String LOCALWITHDRAW_APPLYWITHDRAW_WITHDRAWAMOUNT_EMPTY = "pay.localWithdraw.applyWithdraw.withdrawAmount.empty";

	/**
	 * 提现金额不能为空或0
	 */
	public static String LOCALWITHDRAW_APPLYWITHDRAW_WITHDRAWAMOUNT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(LOCALWITHDRAW_APPLYWITHDRAW_WITHDRAWAMOUNT_EMPTY);
	}

	/**
	 * 提现ID不合法
	 */
	public static final String LOCALWITHDRAW_APPLYWITHDRAW_WITHDRAWIDS_ILLEGAL = "pay.localWithdraw.applyWithdraw.withdrawIds.illegal";

	/**
	 * 提现ID不合法
	 */
	public static String LOCALWITHDRAW_APPLYWITHDRAW_WITHDRAWIDS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(LOCALWITHDRAW_APPLYWITHDRAW_WITHDRAWIDS_ILLEGAL);
	}

	/**
	 * 有正在处理的提现批次, 请稍后再试
	 */
	public static final String LOCALWITHDRAW_APPLYWITHDRAW_FAILURE = "pay.localWithdraw.applyWithdraw.failure";

	/**
	 * 有正在处理的提现批次, 请稍后再试
	 */
	public static String LOCALWITHDRAW_APPLYWITHDRAW_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(LOCALWITHDRAW_APPLYWITHDRAW_FAILURE);
	}

	/**
	 * 支付宝回调结果-交易状态不合法
	 */
	public static final String ALIPAYNOTIFY_SERVICE_ALIPAYTRADESTATUS_ILLEGAL = "pay.alipayNotify.service.AlipayTradeStatus.illegal";

	/**
	 * 支付宝回调结果-交易状态不合法
	 */
	public static String ALIPAYNOTIFY_SERVICE_ALIPAYTRADESTATUS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYNOTIFY_SERVICE_ALIPAYTRADESTATUS_ILLEGAL);
	}

	/**
	 * 支付宝回调结果-通知类型不合法
	 */
	public static final String ALIPAYNOTIFY_SERVICE_ALIPAYNOTIFYTYPE_ILLEGAL = "pay.alipayNotify.service.AlipayNotifyType.illegal";

	/**
	 * 支付宝回调结果-通知类型不合法
	 */
	public static String ALIPAYNOTIFY_SERVICE_ALIPAYNOTIFYTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYNOTIFY_SERVICE_ALIPAYNOTIFYTYPE_ILLEGAL);
	}

	/**
	 * 科目不正确
	 */
	public static final String BALANCELOG_SUBJECT_ERROR = "pay.balanceLog.subject.error";

	/**
	 * 科目不正确
	 */
	public static String BALANCELOG_SUBJECT_ERROR() {
		return MultiLanguageAdapter.fetchMessage(BALANCELOG_SUBJECT_ERROR);
	}

	/**
	 * 找不到该余额账户信息
	 */
	public static final String STOREBALANCE_PLUSBALANCE_BALANCE_NOTEXIST = "pay.storeBalance.plusBalance.balance.notexist";

	/**
	 * 找不到该余额账户信息
	 */
	public static String STOREBALANCE_PLUSBALANCE_BALANCE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_PLUSBALANCE_BALANCE_NOTEXIST);
	}

	/**
	 * 该余额账户已被冻结
	 */
	public static final String STOREBALANCE_PLUSBALANCE_BALANCE_FROZEN = "pay.storeBalance.plusBalance.balance.frozen";

	/**
	 * 该余额账户已被冻结
	 */
	public static String STOREBALANCE_PLUSBALANCE_BALANCE_FROZEN() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_PLUSBALANCE_BALANCE_FROZEN);
	}

	/**
	 * 修改餐厅账户余额失败
	 */
	public static final String STOREBALANCE_PLUSBALANCE_FAILURE = "pay.storeBalance.plusBalance.failure";

	/**
	 * 修改餐厅账户余额失败
	 */
	public static String STOREBALANCE_PLUSBALANCE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_PLUSBALANCE_FAILURE);
	}

	/**
	 * 该余额账户余额不足
	 */
	public static final String STOREBALANCE_SUBBALANCE_BALANCE_NOTENOUGH = "pay.storeBalance.subBalance.balance.notenough";

	/**
	 * 该余额账户余额不足
	 */
	public static String STOREBALANCE_SUBBALANCE_BALANCE_NOTENOUGH() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_SUBBALANCE_BALANCE_NOTENOUGH);
	}

	/**
	 * 不支持这个科目
	 */
	public static final String STOREBALANCE_LOGBALANCEDETAILS_SUBJECT_ILLEGAL = "pay.storeBalance.logBalanceDetails.subject.illegal";

	/**
	 * 不支持这个科目
	 */
	public static String STOREBALANCE_LOGBALANCEDETAILS_SUBJECT_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_LOGBALANCEDETAILS_SUBJECT_ILLEGAL);
	}

	/**
	 * 支付记录不存在
	 */
	public static final String STOREBALANCE_LOGBALANCEDETAILS_PAYRECORD_NOTEXIST = "pay.storeBalance.logBalanceDetails.payRecord.notexist";

	/**
	 * 支付记录不存在
	 */
	public static String STOREBALANCE_LOGBALANCEDETAILS_PAYRECORD_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_LOGBALANCEDETAILS_PAYRECORD_NOTEXIST);
	}

	/**
	 * 支付记录和优惠记录的实际支付金额不一致
	 */
	public static final String STOREBALANCE_LOGBALANCEDETAILS_FEE_ERROR = "pay.storeBalance.logBalanceDetails.fee.error";

	/**
	 * 支付记录和优惠记录的实际支付金额不一致
	 */
	public static String STOREBALANCE_LOGBALANCEDETAILS_FEE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_LOGBALANCEDETAILS_FEE_ERROR);
	}

	/**
	 * 交易中账户不存在
	 */
	public static final String STOREBALANCE_LOGBALANCEDETAILS_DEALBALANCE_NOTEXIST = "pay.storeBalance.logBalanceDetails.dealBalance.notexist";

	/**
	 * 交易中账户不存在
	 */
	public static String STOREBALANCE_LOGBALANCEDETAILS_DEALBALANCE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_LOGBALANCEDETAILS_DEALBALANCE_NOTEXIST);
	}

	/**
	 * 内部交易系统出错
	 */
	public static final String STOREBALANCE_LOGBALANCEDETAILS_STOREBALANCE_NOTEXIST = "pay.storeBalance.logBalanceDetails.storeBalance.notexist";

	/**
	 * 内部交易系统出错
	 */
	public static String STOREBALANCE_LOGBALANCEDETAILS_STOREBALANCE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREBALANCE_LOGBALANCEDETAILS_STOREBALANCE_NOTEXIST);
	}

	/**
	 * 支付回调记录已存在
	 */
	public static final String ALIPAYPAYNOTIFY_CREATENOTIFY_ALIPAYNOTIFY_EXIST = "pay.alipayPayNotify.createNotify.alipayNotify.exist";

	/**
	 * 支付回调记录已存在
	 */
	public static String ALIPAYPAYNOTIFY_CREATENOTIFY_ALIPAYNOTIFY_EXIST() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYPAYNOTIFY_CREATENOTIFY_ALIPAYNOTIFY_EXIST);
	}

	/**
	 * 优惠使用记录不存在
	 */
	public static final String PAYDISCOUNTRECORD_DELCOUPONRECORD_PAYDISCOUNTRECORD_NOTEXIST = "pay.payDiscountRecord.delCouponRecord.payDiscountRecord.notexist";

	/**
	 * 优惠使用记录不存在
	 */
	public static String PAYDISCOUNTRECORD_DELCOUPONRECORD_PAYDISCOUNTRECORD_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(PAYDISCOUNTRECORD_DELCOUPONRECORD_PAYDISCOUNTRECORD_NOTEXIST);
	}

	/**
	 * 支付记录不存在
	 */
	public static final String PAYRECORD_NOTIFYSUCCESS_PAYRECORD_NOTEXIST = "pay.payRecord.notifySuccess.payRecord.notexist";

	/**
	 * 支付记录不存在
	 */
	public static String PAYRECORD_NOTIFYSUCCESS_PAYRECORD_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(PAYRECORD_NOTIFYSUCCESS_PAYRECORD_NOTEXIST);
	}

	/**
	 * 回调记录错误，ID必须大于0
	 */
	public static final String PAYRECORD_PAYSUCCESS_NOTIFYID_ERROR = "pay.payRecord.paySuccess.notifyId.error";

	/**
	 * 回调记录错误，ID必须大于0
	 */
	public static String PAYRECORD_PAYSUCCESS_NOTIFYID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(PAYRECORD_PAYSUCCESS_NOTIFYID_ERROR);
	}

	/**
	 * 更新支付记录出错，请重试
	 */
	public static final String PAYRECORD_PAYSUCCESS_UPDATEPAYSTATUS_FAILURE = "pay.payRecord.paySuccess.updatePayStatus.failure";

	/**
	 * 更新支付记录出错，请重试
	 */
	public static String PAYRECORD_PAYSUCCESS_UPDATEPAYSTATUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(PAYRECORD_PAYSUCCESS_UPDATEPAYSTATUS_FAILURE);
	}

	/**
	 * 该订单已支付
	 */
	public static final String PAYRECORD_CHANGEHASDISCOUNTTOYES_PAYSTATUS_ERROR = "pay.payRecord.changeHasDiscountToYes.payStatus.error";

	/**
	 * 该订单已支付
	 */
	public static String PAYRECORD_CHANGEHASDISCOUNTTOYES_PAYSTATUS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(PAYRECORD_CHANGEHASDISCOUNTTOYES_PAYSTATUS_ERROR);
	}

	/**
	 * 更新数据失败，原因：未知
	 */
	public static final String PAYRECORD_CHANGEHASDISCOUNTTOYES_UPDATEHASDISCOUNT_FAILURE = "pay.payRecord.changeHasDiscountToYes.updateHasDiscount.failure";

	/**
	 * 更新数据失败，原因：未知
	 */
	public static String PAYRECORD_CHANGEHASDISCOUNTTOYES_UPDATEHASDISCOUNT_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(PAYRECORD_CHANGEHASDISCOUNTTOYES_UPDATEHASDISCOUNT_FAILURE);
	}

	/**
	 * 客户端版本错误
	 */
	public static final String PAY_CREATEPARAMS_TARGET_EMPTY = "pay.pay.createPay.params.target.empty";

	/**
	 * 客户端版本错误
	 */
	public static String PAY_CREATEPARAMS_TARGET_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(PAY_CREATEPARAMS_TARGET_EMPTY);
	}

	/**
	 * 优惠金额参数不能小于0
	 */
	public static final String PAY_CREATECOUPONFEE_ILLEGAL = "pay.pay.createPay.couponFee.illegal";

	/**
	 * 优惠金额参数不能小于0
	 */
	public static String PAY_CREATECOUPONFEE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(PAY_CREATECOUPONFEE_ILLEGAL);
	}

	/**
	 * 不允许更换优惠券
	 */
	public static final String PAY_CREATECOUPONFEE_ERROR = "pay.pay.createPay.couponFee.error";

	/**
	 * 不允许更换优惠券
	 */
	public static String PAY_CREATECOUPONFEE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(PAY_CREATECOUPONFEE_ERROR);
	}

	/**
	 * 请求微信支付失败，请重新尝试
	 */
	public static final String PAY_CREATEWECHATUNIFIEDORDERRESDATA_ERROR = "pay.pay.createPay.WechatUnifiedOrderResData.error";

	/**
	 * 请求微信支付失败，请重新尝试
	 */
	public static String PAY_CREATEWECHATUNIFIEDORDERRESDATA_ERROR() {
		return MultiLanguageAdapter.fetchMessage(PAY_CREATEWECHATUNIFIEDORDERRESDATA_ERROR);
	}

	/**
	 * 订单金额跟回调金额不一致
	 */
	public static final String PAY_ALIPAYNOTIFY_TOTALFEE_ERROR = "pay.pay.alipayNotify.totalFee.error";

	/**
	 * 订单金额跟回调金额不一致
	 */
	public static String PAY_ALIPAYNOTIFY_TOTALFEE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(PAY_ALIPAYNOTIFY_TOTALFEE_ERROR);
	}

	/**
	 * 操作系统类型不能为空
	 */
	public static final String PAY_CREATEPAY_SYSTYPE_EMPTY = "pay.pay.createPay.sysType.empty";

	/**
	 * 操作系统类型不能为空
	 */
	public static final String PAY_CREATEPAY_SYSTYPE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(PAY_CREATEPAY_SYSTYPE_EMPTY);
	}

	/**
	 * 交易状态不合法
	 */
	public static final String PAY_ALIPAYNOTIFY_ALIPAYTRADESTATUS_ILLEGAL = "pay.pay.alipayNotify.AlipayTradeStatus.illegal";

	/**
	 * 交易状态不合法
	 */
	public static String PAY_ALIPAYNOTIFY_ALIPAYTRADESTATUS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(PAY_ALIPAYNOTIFY_ALIPAYTRADESTATUS_ILLEGAL);
	}

	/**
	 * 请求退款数量跟数据库通知记录查询结果不一致
	 */
	public static final String ALIPAYREFUND_QUERYPAYNOTIFY_ERROR = "pay.alipayRefund.queryPayNotify.error";

	/**
	 * 请求退款数量跟数据库通知记录查询结果不一致
	 */
	public static String ALIPAYREFUND_QUERYPAYNOTIFY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_QUERYPAYNOTIFY_ERROR);
	}

	/**
	 * 支付宝交易ID不能为空
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_NOTIFYID_EMPTY = "pay.alipayRefund.alipayCallBack.notifyId.empty";

	/**
	 * 支付宝交易ID不能为空
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_NOTIFYID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_NOTIFYID_EMPTY);
	}

	/**
	 * 批次号不能为空
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_BATCHNO_EMPTY = "pay.alipayRefund.alipayCallBack.batchNo.empty";

	/**
	 * 批次号不能为空
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_BATCHNO_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_BATCHNO_EMPTY);
	}

	/**
	 * 退款结果明细不能为空
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_RESULTDETAILS_EMPTY = "pay.alipayRefund.alipayCallBack.resultDetails.empty";

	/**
	 * 退款结果明细不能为空
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_RESULTDETAILS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_RESULTDETAILS_EMPTY);
	}

	/**
	 * 支付宝批量退款信息记录不存在
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_ALIPAYREFUND_NOTEXIST = "pay.alipayRefund.alipayCallBack.alipayRefund.notexist";

	/**
	 * 支付宝批量退款信息记录不存在
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_ALIPAYREFUND_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_ALIPAYREFUND_NOTEXIST);
	}

	/**
	 * 批次明细不存在
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_BATCHNO_NOTEXIST = "pay.alipayRefund.alipayCallBack.batchNo.notexist";

	/**
	 * 批次明细不存在
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_BATCHNO_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_BATCHNO_NOTEXIST);
	}

	/**
	 * 请求退款批次单笔数和通知结果不一致
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_RESULTDETAILS_NOTEXIST = "pay.alipayRefund.alipayCallBack.resultDetails.notexist";

	/**
	 * 请求退款批次单笔数和通知结果不一致
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_RESULTDETAILS_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_RESULTDETAILS_NOTEXIST);
	}

	/**
	 * 退款批次明细记录的数据在回调通知里不存在
	 */
	public static final String ALIPAYREFUND_ALIPAYCALLBACK_TRADENO_NOTEXIST = "pay.alipayRefund.alipayCallBack.tradeNo.notexist";

	/**
	 * 退款批次明细记录的数据在回调通知里不存在
	 */
	public static String ALIPAYREFUND_ALIPAYCALLBACK_TRADENO_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYREFUND_ALIPAYCALLBACK_TRADENO_NOTEXIST);
	}

	/**
	 * 支付宝回调记录不存在
	 */
	public static final String REFUNDRECORD_APPLYREFUND_ALIPAYPAYNOTIFY_NOTEXIST = "pay.refundRecord.applyRefund.AlipayPayNotify.notexist";

	/**
	 * 支付宝回调记录不存在
	 */
	public static String REFUNDRECORD_APPLYREFUND_ALIPAYPAYNOTIFY_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(REFUNDRECORD_APPLYREFUND_ALIPAYPAYNOTIFY_NOTEXIST);
	}

	/**
	 * 微信支付回调记录不存在
	 */
	public static final String REFUNDRECORD_APPLYREFUND_WECHATPAYNOTIFY_NOTEXIST = "pay.refundRecord.applyRefund.WechatPayNotify.notexist";

	/**
	 * 微信支付回调记录不存在
	 */
	public static String REFUNDRECORD_APPLYREFUND_WECHATPAYNOTIFY_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(REFUNDRECORD_APPLYREFUND_WECHATPAYNOTIFY_NOTEXIST);
	}

	/**
	 * 退款记录不存在
	 */
	public static final String REFUNDRECORD_DEALWITHREFUND_REFUNDRECORD_NOTEXIST = "pay.refundRecord.dealWithRefund.refundRecord.notexist";

	/**
	 * 退款记录不存在
	 */
	public static String REFUNDRECORD_DEALWITHREFUND_REFUNDRECORD_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(REFUNDRECORD_DEALWITHREFUND_REFUNDRECORD_NOTEXIST);
	}

	/**
	 * 更新数据库失败
	 */
	public static final String REFUNDRECORD_DEALWITHREFUND_UPDATEREFUNDSTATUS_FAILURE = "pay.refundRecord.dealWithRefund.updateRefundStatus.failure";

	/**
	 * 更新数据库失败
	 */
	public static String REFUNDRECORD_DEALWITHREFUND_UPDATEREFUNDSTATUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(REFUNDRECORD_DEALWITHREFUND_UPDATEREFUNDSTATUS_FAILURE);
	}

	/**
	 * 更新提现批次状态失败
	 */
	public static final String ALIPAYWITHDRAWBATCH_SETBATCHSUCCESS_FAILURE = "pay.alipayWithdrawBatch.setBatchSuccess.failure";

	/**
	 * 更新提现批次状态失败
	 */
	public static String ALIPAYWITHDRAWBATCH_SETBATCHSUCCESS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYWITHDRAWBATCH_SETBATCHSUCCESS_FAILURE);
	}

	/**
	 * 该提现批次不存在, 请刷新再试!
	 */
	public static final String ALIPAYWITHDRAWBATCH_CLOSEEXCEPTIONBATCH_BATCHNO_NOTEXIST = "pay.alipayWithdrawBatch.closeExceptionBatch.batchNo.notexist";

	/**
	 * 该提现批次不存在, 请刷新再试!
	 */
	public static String ALIPAYWITHDRAWBATCH_CLOSEEXCEPTIONBATCH_BATCHNO_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYWITHDRAWBATCH_CLOSEEXCEPTIONBATCH_BATCHNO_NOTEXIST);
	}

	/**
	 * 该提现批次不能被关闭, 请刷新再试!
	 */
	public static final String ALIPAYWITHDRAWBATCH_CLOSEEXCEPTIONBATCH_STATUS_ERROR = "pay.alipayWithdrawBatch.closeExceptionBatch.status.error";

	/**
	 * 该提现批次不能被关闭, 请刷新再试!
	 */
	public static String ALIPAYWITHDRAWBATCH_CLOSEEXCEPTIONBATCH_STATUS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ALIPAYWITHDRAWBATCH_CLOSEEXCEPTIONBATCH_STATUS_ERROR);
	}

	/**
	 * 用户账户不合法
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_PARAMS_UID_ILLEGAL = "pay.storeWithdraw.applyWithdraw.params.uid.illegal";

	/**
	 * 用户账户不合法
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_PARAMS_UID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_PARAMS_UID_ILLEGAL);
	}

	/**
	 * 余额不足
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_PARAMS_BALANCE_NOTENOUGH = "pay.storeWithdraw.applyWithdraw.params.balance.notenough";

	/**
	 * 余额不足
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_PARAMS_BALANCE_NOTENOUGH() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_PARAMS_BALANCE_NOTENOUGH);
	}

	/**
	 * 餐厅账户被冻结, 请联系米星客服
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_PARAMS_BALANCESTATUS_FROZEN = "pay.storeWithdraw.applyWithdraw.params.balanceStatus.frozen";

	/**
	 * 餐厅账户被冻结, 请联系米星客服
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_PARAMS_BALANCESTATUS_FROZEN() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_PARAMS_BALANCESTATUS_FROZEN);
	}

	/**
	 * 您操作的餐厅不存在, 请联系米星客服
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_STORE_NOTEXIST = "pay.storeWithdraw.applyWithdraw.store.notexist";

	/**
	 * 您操作的餐厅不存在, 请联系米星客服
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_STORE_NOTEXIST);
	}

	/**
	 * 您的餐厅状态目前不可提现, 请联系米星客服
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_STORESTATUS_ILLEGAL = "pay.storeWithdraw.applyWithdraw.storeStatus.illegal";

	/**
	 * 您的餐厅状态目前不可提现, 请联系米星客服
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_STORESTATUS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_STORESTATUS_ILLEGAL);
	}

	/**
	 * 提现金额异常，请稍后再试
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_AMOUNT_ILLEGAL = "pay.storeWithdraw.applyWithdraw.amount.illegal";

	/**
	 * 提现金额异常，请稍后再试
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_AMOUNT_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_AMOUNT_ILLEGAL);
	}

	/**
	 * 提现金额不能低于{0}元
	 */
	public static final String STOREWITHDRAW_APPLYWITHDRAW_AMOUNT_MIN = "pay.storeWithdraw.applyWithdraw.amount.min";

	/**
	 * 提现金额不能低于{0}元
	 */
	public static String STOREWITHDRAW_APPLYWITHDRAW_AMOUNT_MIN(String amount) {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_APPLYWITHDRAW_AMOUNT_MIN, amount);
	}

	/**
	 * 批量请求总数不能超过1000笔
	 */
	public static final String STOREWITHDRAW_GETWITHDRAWREQDATA_WITHDRAWIDS_SIZE_MAX = "pay.storeWithdraw.getWithdrawReqData.withdrawIds.size.max";

	/**
	 * 批量请求总数不能超过1000笔
	 */
	public static String STOREWITHDRAW_GETWITHDRAWREQDATA_WITHDRAWIDS_SIZE_MAX() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_GETWITHDRAWREQDATA_WITHDRAWIDS_SIZE_MAX);
	}

	/**
	 * 请求提现数量跟数据库提现记录查询结果不一致
	 */
	public static final String STOREWITHDRAW_GETWITHDRAWREQDATA_RESULT_ERROR = "pay.storeWithdraw.getWithdrawReqData.result.error";

	/**
	 * 请求提现数量跟数据库提现记录查询结果不一致
	 */
	public static String STOREWITHDRAW_GETWITHDRAWREQDATA_RESULT_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_GETWITHDRAWREQDATA_RESULT_ERROR);
	}

	/**
	 * 当前有正在进行中的提现批次, 请完成/关闭后再进行此操作
	 */
	public static final String STOREWITHDRAW_GETWITHDRAWREQDATA_STATUS_INPROCESS = "pay.storeWithdraw.getWithdrawReqData.status.inprocess";

	/**
	 * 当前有正在进行中的提现批次, 请完成/关闭后再进行此操作
	 */
	public static String STOREWITHDRAW_GETWITHDRAWREQDATA_STATUS_INPROCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_GETWITHDRAWREQDATA_STATUS_INPROCESS);
	}

	/**
	 * 申请提现的ID中, 有正在被处理的, 为避免重复提现, 此次提现被终止
	 */
	public static final String STOREWITHDRAW_GETWITHDRAWREQDATA_WITHDRAWID_INPROCESS = "pay.storeWithdraw.getWithdrawReqData.withdrawId.inprocess";

	/**
	 * 申请提现的ID中, 有正在被处理的, 为避免重复提现, 此次提现被终止
	 */
	public static String STOREWITHDRAW_GETWITHDRAWREQDATA_WITHDRAWID_INPROCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_GETWITHDRAWREQDATA_WITHDRAWID_INPROCESS);
	}

	/**
	 * 找不到该提现批次
	 */
	public static final String STOREWITHDRAW_ALIPAYCALLBACK_ALIPAYWITHDRAWBATCH_NOTEXIST = "pay.storeWithdraw.alipayCallback.AlipayWithdrawBatch.notexist";

	/**
	 * 找不到该提现批次
	 */
	public static String STOREWITHDRAW_ALIPAYCALLBACK_ALIPAYWITHDRAWBATCH_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_ALIPAYCALLBACK_ALIPAYWITHDRAWBATCH_NOTEXIST);
	}

	/**
	 * 转换通知时间错误
	 */
	public static final String STOREWITHDRAW_ALIPAYCALLBACK_NOTIFYTIME_ERROR = "pay.storeWithdraw.alipayCallback.notifyTime.error";

	/**
	 * 转换通知时间错误
	 */
	public static String STOREWITHDRAW_ALIPAYCALLBACK_NOTIFYTIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_ALIPAYCALLBACK_NOTIFYTIME_ERROR);
	}

	/**
	 * 提现申请ID不能为空
	 */
	public static final String STOREWITHDRAW_GETAPPLYDETAILS_WITHDRAWIDS_EMPTY = "pay.storeWithdraw.getApplyDetails.withdrawIds.empty";

	/**
	 * 提现申请ID不能为空
	 */
	public static String STOREWITHDRAW_GETAPPLYDETAILS_WITHDRAWIDS_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_GETAPPLYDETAILS_WITHDRAWIDS_EMPTY);
	}

	/**
	 * 提现记录不存在
	 */
	public static final String STOREWITHDRAW_CLOSEWITHDRAW_STOREWITHDRAW_EMPTY = "pay.storeWithdraw.closeWithdraw.StoreWithdraw.empty";

	/**
	 * 提现记录不存在
	 */
	public static String STOREWITHDRAW_CLOSEWITHDRAW_STOREWITHDRAW_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_CLOSEWITHDRAW_STOREWITHDRAW_EMPTY);
	}

	/**
	 * 该提现正在被处理, 不能退回
	 */
	public static final String STOREWITHDRAW_CLOSEWITHDRAW_WITHDRAWID_INPROCESS = "pay.storeWithdraw.closeWithdraw.withdrawId.inprocess";

	/**
	 * 该提现正在被处理, 不能退回
	 */
	public static String STOREWITHDRAW_CLOSEWITHDRAW_WITHDRAWID_INPROCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_CLOSEWITHDRAW_WITHDRAWID_INPROCESS);
	}

	/**
	 * 更新提现状态失败
	 */
	public static final String STOREWITHDRAW_CLOSEWITHDRAW_UPDATESTATUS_FAILURE = "pay.storeWithdraw.closeWithdraw.updateStatus.failure";

	/**
	 * 更新提现状态失败
	 */
	public static String STOREWITHDRAW_CLOSEWITHDRAW_UPDATESTATUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_CLOSEWITHDRAW_UPDATESTATUS_FAILURE);
	}

	/**
	 * 门店的可提现账户不存在
	 */
	public static final String STOREWITHDRAW_CLOSEWITHDRAW_STOREBALANCE_NOTEXIST = "pay.storeWithdraw.closeWithdraw.StoreBalance.notexist";

	/**
	 * 门店的可提现账户不存在
	 */
	public static String STOREWITHDRAW_CLOSEWITHDRAW_STOREBALANCE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_CLOSEWITHDRAW_STOREBALANCE_NOTEXIST);
	}

	/**
	 * 该提现申请正在被其他请求处理中
	 */
	public static final String STOREWITHDRAW_CLOSEWITHDRAW_INPROCESS = "pay.storeWithdraw.closeWithdraw.inprocess";

	/**
	 * 该提现申请正在被其他请求处理中
	 */
	public static String STOREWITHDRAW_CLOSEWITHDRAW_INPROCESS() {
		return MultiLanguageAdapter.fetchMessage(STOREWITHDRAW_CLOSEWITHDRAW_INPROCESS);
	}
	
	/**
	 * 申请退款失败（订单已入账），请及时联系米星管理员
	 */
	public static final String REFUND_APPLY_HAS_SETTLE = "pay.refund.apply.has.settle";
	
	/**
	 * 申请退款失败（订单已入账），请及时联系米星管理员
	 */
	public static String REFUND_APPLY_HAS_SETTLE(){
		return MultiLanguageAdapter.fetchMessage(REFUND_APPLY_HAS_SETTLE);
	}
	
	/**
	 * 申请退款失败（账户金额异常），请及时联系米星管理员。
	 */
	public static final String REFUND_APPLY_FAIL_ACCOUNT_ERROR = "pay.refund.apply.fail.account.error";
	
	/**
	 * 申请退款失败（账户金额异常），请及时联系米星管理员。
	 */
	public static String REFUND_APPLY_FAIL_ACCOUNT_ERROR(){
		return MultiLanguageAdapter.fetchMessage(REFUND_APPLY_FAIL_ACCOUNT_ERROR);
	}
	
}
