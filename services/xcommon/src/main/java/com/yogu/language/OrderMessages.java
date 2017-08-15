package com.yogu.language;

/**
 * 域order 下的 多语言提示相关key的常量类<br>
 * 
 * 用于组织order域下的异常提示、注解提示相关的常量key
 * 
 * @author sky 2016-03-25
 *
 */
public class OrderMessages {
	//活动ticket相关的描述文案--------------start--------------------------
	
	
	/**
	 * 获取票详情失败
	 */
	public static final String ORDER_TICKET_EVENTDETAIL_ERROR = "order.ticket.eventdetail.error";
	
	/**
	 * 获取票详情失败
	 */
	public static final String ORDER_TICKET_EVENTDETAIL_ERROR(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_EVENTDETAIL_ERROR);
	}
	
	
	/**
	 * 支付类型不匹配，不能退款
	 */
	public static final String ORDER_TICKET_REFUND_PAYTYPE_ERROR = "order.ticket.refund.paytype.error";
	
	/**
	 * 支付类型不匹配，不能退款
	 */
	public static final String ORDER_TICKET_REFUND_PAYTYPE_ERROR(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_REFUND_REPEAT);
	}
	
	
	/**
	 * 重复的退款请求
	 */
	public static final String ORDER_TICKET_REFUND_REPEAT = "order.ticket.refund.repeat";
	
	/**
	 * 重复的退款请求
	 */
	public static final String ORDER_TICKET_REFUND_REPEAT(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_REFUND_REPEAT);
	}
	
	
	/**
	 * 订单已取消
	 */
	public static final String ORDER_TICKET_ORDER_CANCLED = "order.ticket.order.cancled";
	
	/**
	 * 订单已取消
	 */
	public static final String ORDER_TICKET_ORDER_CANCLED(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_ORDER_CANCLED);
	}
	
	/**
	 * 活动不存在
	 */
	public static final String ORDER_EVENT_NOT_EXIST = "order.ticket.event.notExist";
	
	/**
	 * 活动不存在
	 */
	public static final String ORDER_EVENT_NOT_EXIST(){
		return MultiLanguageAdapter.fetchMessage(ORDER_EVENT_NOT_EXIST);
	}
	/**
	 * 该活动已暂停
	 */
	public static final String ORDER_EVENT_CANNOT_BUY = "order.ticket.event.cannotBuy";
	
	/**
	 * 该活动已暂停
	 */
	public static final String ORDER_EVENT_CANNOT_BUY(){
		return MultiLanguageAdapter.fetchMessage(ORDER_EVENT_CANNOT_BUY);
	}
	
	
	
	/**
	 * 该活动不能购买
	 */
	public static final String ORDER_EVENT_NOT_IN_TIME = "order.ticket.event.not.inTime";
	
	/**
	 * 该活动不能购买
	 */
	public static final String ORDER_EVENT_NOT_IN_TIME(){
		return MultiLanguageAdapter.fetchMessage(ORDER_EVENT_NOT_IN_TIME);
	}
	
	
	
	/**
	 * 门票已售完
	 */
	public static final String ORDER_TICKET_NOT_ENOUGH = "order.ticket.notEnough";
	
	/**
	 * 门票已售完
	 */
	public static final String ORDER_TICKET_NOT_ENOUGH(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_NOT_ENOUGH);
	}
	
	
	/**
	 * 票库存扣除异常
	 */
	public static final String ORDER_TICKET_CONSUME_ERROR = "order.ticket.consume.error";
	
	/**
	 * 票库存扣除异常
	 */
	public static final String ORDER_TICKET_CONSUME_ERROR(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_NOT_ENOUGH);
	}
	
	/**
	 * 参数错误
	 */
	public static final String ORDER_TICKET_PARAM_ERROR = "order.ticket.param.error";
	
	/**
	 * 参数错误
	 */
	public static final String ORDER_TICKET_PARAM_ERROR(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_PARAM_ERROR);
	}
	
	
	
	/**
	 * 门票不存在
	 */
	public static final String ORDER_TICKET_NOT_EXIST = "order.ticket.notExist";
	
	/**
	 * 门票不存在
	 */
	public static final String ORDER_TICKET_NOT_EXIST(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_NOT_EXIST);
	}
	
	/**
	 * 不能超过限购数量
	 */
	public static final String ORDER_TICKET_BUY_REACH_LIMIT = "order.ticket.buy.reachLimit";
	
	/**
	 * 不能超过限购数量
	 */
	public static final String ORDER_TICKET_BUY_REACH_LIMIT(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_BUY_REACH_LIMIT);
	}
	
	/**
	 * 门票暂停售卖
	 */
	public static final String ORDER_TICKET_IS_STOPPED = "order.ticket.stopped";
	
	/**
	 * 门票暂停售卖
	 */
	public static final String ORDER_TICKET_IS_STOPPED(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_IS_STOPPED);
	}
	
	
	
	/**
	 * 门票已过期
	 */
	public static final String ORDER_TICKET_IS_EXPIRED = "order.ticket.expired";
	
	/**
	 * 门票已过期
	 */
	public static final String ORDER_TICKET_IS_EXPIRED(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_IS_EXPIRED);
	}
	
	
	/**
	 * 该门票不支持退款
	 */
	public static final String ORDER_TICKET_DONOT_SUPPORT_REFUND = "order.ticket.support.refund.not";
	
	/**
	 * 该门票不支持退款
	 */
	public static final String ORDER_TICKET_DONOT_SUPPORT_REFUND(){
		return MultiLanguageAdapter.fetchMessage(ORDER_TICKET_DONOT_SUPPORT_REFUND);
	}
	
	
	
	
	
	//优惠券文案描述start---------------
	
	/**
	 * 仅「{0}」使用
	 */
	public static final String ORDER_COUPON_DESC_ONLYSTOREUSE = "order.coupon.desc.onlyStoreUse";
	/**
	 * 仅「{0}」使用
	 * @param storeName
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_ONLYSTOREUSE(String storeName) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_ONLYSTOREUSE, storeName);
	}

	/**
	 * 以下{0}家餐厅可以使用
	 */
	public static final String ORDER_COUPON_DESC_SOMESTOREUSE = "order.coupon.desc.someStoreUse";
	/**
	 * 以下{0}家餐厅可以使用
	 * @param size
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_SOMESTOREUSE(int size) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_SOMESTOREUSE, String.valueOf(size));
	}

	/**
	 * 以下{0}菜品可以
	 */
	public static final String ORDER_COUPON_DESC_SOMEDISHUSE = "order.coupon.desc.someDishUse";
	/**
	 * 以下{0}菜品可以
	 */
	public static final String ORDER_COUPON_DESC_SOMEDISHUSE(int size) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_SOMEDISHUSE, String.valueOf(size));
	}
	
	/**
	 * 仅「{0}」使用
	 */
	public static final String ORDER_COUPON_DESC_ONLYDISHUSE = "order.coupon.desc.onlyDishUse";
	
	/**
	 * 仅「{0}」使用
	 * @param storeName
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_ONLYDISHUSE(String storeName) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_ONLYDISHUSE, storeName);
	}

	/**
	 * 限尾号{0}的手机号码使用
	 */
	public static final String ORDER_COUPON_DESC_ONLYPHONEUSE = "order.coupon.desc.onlyPhoneUse";
	/**
	 * 限尾号{0}的手机号码使用
	 * @param suffix 手机尾号
	 */
	public static final String ORDER_COUPON_DESC_ONLYPHONEUSE(String suffix) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_ONLYPHONEUSE,suffix);
	}
	
	
	
	/**
	 * 全平台商家通用
	 */
	public static final String ORDER_COUPON_DESC_PLATFORM_ALL = "order.coupon.desc.platform.all";
	/**
	 * 全平台商家通用
	 */
	public static final String ORDER_COUPON_DESC_PLATFORM_ALL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_PLATFORM_ALL);
	}
	/**
	 * 限在线订餐,
	 */
	public static final String ORDER_COUPON_DESC_CHANNEL_ONLINEORDER = "order.coupon.desc.channel.onlineOrder";
	/**
	 * 限在线订餐,
	 */
	public static final String ORDER_COUPON_DESC_CHANNEL_ONLINEORDER() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_CHANNEL_ONLINEORDER);
	}
	
	/**
	 * 限米星Pay,
	 */
	public static final String ORDER_COUPON_DESC_CHANNEL_MAZINGPAY = "order.coupon.desc.channel.MazingPay";
	/**
	 * 限米星Pay,
	 */
	public static final String ORDER_COUPON_DESC_CHANNEL_MAZINGPAY(){
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_CHANNEL_MAZINGPAY);
	}
	
	/**
	 * {0} 当日有效
	 */
	public static final String ORDER_COUPON_DESC_VALID_ONEDAY_ONLY = "order.coupon.desc.valid.oneday.only";
	/**
	 * {0} 当日有效
	 * @param size
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_VALID_ONEDAY_ONLY(String date) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_VALID_ONEDAY_ONLY, date);
	}
	
	/**
	 * 有效期
	 */
	public static final String ORDER_COUPON_DESC_VALID_PERIOD = "order.coupon.desc.valid.period";
	/**
	 * 有效期
	 */
	public static final String ORDER_COUPON_DESC_VALID_PERIOD() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_VALID_PERIOD);
	}
	
	/**
	 * 有效期{0}-永久
	 */
	public static final String ORDER_COUPON_DESC_VALID_PERIOD_TO_FOREVER = "order.coupon.desc.valid.period.to.forever";
	/**
	 * 有效期{0}-永久
	 * @param date
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_VALID_PERIOD_TO_FOREVER(String date) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_VALID_PERIOD_TO_FOREVER, date);
	}
	
	/**
	 * 有效期现在-{0}
	 */
	public static final String ORDER_COUPON_DESC_VALID_NOW_TO_PERIOD = "order.coupon.desc.valid.now.to.period";
	/**
	 * 有效期现在-{0}
	 * @param date
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_VALID_NOW_TO_PERIOD(String date) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_VALID_NOW_TO_PERIOD, date);
	}
	
	/**
	 * 消费满{0}元可用
	 */
	public static final String ORDER_COUPON_DESC_ORDER_MINIMUM = "order.coupon.desc.order.minimum";
	/**
	 * 消费满{0}元可用
	 * @param minimum 最小金额
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_ORDER_MINIMUM(String minimum) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_ORDER_MINIMUM, minimum);
	}
	
	/**
	 * 最高优惠{0}元
	 */
	public static final String ORDER_COUPON_DESC_ORDER_DISCOUNT_MAXIMUM = "order.coupon.desc.order.discount.maximum";
	/**
	 * 最高优惠{0}元
	 * @param maximum 最高优惠金额
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_ORDER_DISCOUNT_MAXIMUM(String maximum) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_ORDER_DISCOUNT_MAXIMUM, maximum);
	}
	
	/**
	 * {0}元
	 */
	public static final String ORDER_COUPON_DESC_TYPE_CASH_FACEVALUE = "order.coupon.desc.type.cash.facevalue";
	/**
	 * {0}元
	 * @param faceValue 现金券面值
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_TYPE_CASH_FACEVALUE(String faceValue) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_TYPE_CASH_FACEVALUE, faceValue);
	}
	
	/**
	 * 满{0}减{0}
	 */
	public static final String ORDER_COUPON_DESC_TYPE_FULLCUT_FACEVALUE = "order.coupon.desc.type.fullcut.facevalue";
	/**
	 * 满{0}减{0}
	 * @param full 满 金额
	 * @param cut 免 金额
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_TYPE_FULLCUT_FACEVALUE(String full, String cut) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_TYPE_FULLCUT_FACEVALUE, full, cut);
	}
	
	/**
	 * {0}折
	 */
	public static final String ORDER_COUPON_DESC_FACEVALUE_TITLE = "order.coupon.desc.facevalue.title";
	/**
	 * {0}折
	 * @param faceValue 券面值
	 * @return
	 */
	public static final String ORDER_COUPON_DESC_FACEVALUE_TITLE(String faceValue) {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_DESC_FACEVALUE_TITLE, faceValue);
	}
	
	
	
	
	
	//优惠券文案描述end---------------
	
	
	
	
	
	
	/**
	 * 下单成功(轨迹标题)
	 */
	public static final String ORDER_CREATE_SUCCESS_TRACK_TITLE = "order.create.success.track.title";

	/**
	 * 下单成功
	 * 
	 * @return
	 */
	public static final String ORDER_CREATE_SUCCESS_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CREATE_SUCCESS_TRACK_TITLE);
	}

	/**
	 * 下单成功(轨迹文本内容)
	 */
	public static final String ORDER_CREATE_SUCCESS_NOT_PAY_TRACK_CONTENT = "order.create.success.notpay.track.content";

	/**
	 * 订单号：xxx请尽快支付
	 * 
	 * @return
	 */
	public static final String ORDER_CREATE_SUCCESS_NOT_PAY_TRACK_CONTENT(String orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_CREATE_SUCCESS_NOT_PAY_TRACK_CONTENT, orderNo);
	}

	/**
	 * 下单成功(轨迹文本内容)
	 */
	public static final String ORDER_CREATE_SUCCESS_HAS_PAY_TRACK_CONTENT = "order.create.success.haspay.track.content";

	/**
	 * 订单号：xxx
	 * 
	 * @return
	 */
	public static final String ORDER_CREATE_SUCCESS_HAS_PAY_TRACK_CONTENT(String orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_CREATE_SUCCESS_HAS_PAY_TRACK_CONTENT, orderNo);
	}

	/**
	 * 支付成功(轨迹标题)
	 */
	public static final String ORDER_PAY_SUCCESS_TRACK_TITLE = "order.pay.success.track.title";

	/**
	 * 支付成功
	 * 
	 * @return
	 */
	public static final String ORDER_PAY_SUCCESS_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_PAY_SUCCESS_TRACK_TITLE);
	}

	/**
	 * 支付成功(轨迹文本内容)
	 */
	public static final String ORDER_PAY_SUCCESS_TRACK_CONTENT = "order.pay.success.track.content";

	/**
	 * 已付款到米星担保账户，请等待商家确认
	 * 
	 * @return
	 */
	public static final String ORDER_PAY_SUCCESS_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_PAY_SUCCESS_TRACK_CONTENT);
	}

	/**
	 * 支付成功-等待接单(轨迹标题)
	 */
	public static final String ORDER_WAIT_ACCEPT_TRACK_TITLE = "order.wait.accept.track.title";

	/**
	 * 支付成功-等待接单
	 * 
	 * @return
	 */
	public static final String ORDER_WAIT_ACCEPT_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_WAIT_ACCEPT_TRACK_TITLE);
	}

	/**
	 * 支付成功-等待接单(轨迹文本内容)
	 */
	public static final String ORDER_WAIT_ACCEPT_TRACK_CONTENT = "order.wait.accept.track.content";

	/**
	 * 请等待商家确认
	 * 
	 * @return
	 */
	public static final String ORDER_WAIT_ACCEPT_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_WAIT_ACCEPT_TRACK_CONTENT);
	}

	/**
	 * 商家接单(轨迹标题)
	 */
	public static final String ORDER_STORE_ACCEPT_TRACK_TITLE = "order.store.accept.track.title";

	/**
	 * 商家接单
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_ACCEPT_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_ACCEPT_TRACK_TITLE);
	}

	/**
	 * 商家接单(轨迹文本内容)
	 */
	public static final String ORDER_STORE_ACCEPT_TRACK_CONTENT = "order.store.accept.track.content";

	/**
	 * 美食准备中，请耐心等待
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_ACCEPT_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_ACCEPT_TRACK_CONTENT);
	}

	/**
	 * 商家制作完成(轨迹标题，现在已经没有这个阶段，代码的判断逻辑还保留)
	 */
	public static final String ORDER_STORE_FINISH_COOKING_TRACK_TITLE = "order.store.finish.cooking.track.title";

	/**
	 * 商家制作完成
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_FINISH_COOKING_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_FINISH_COOKING_TRACK_TITLE);
	}

	/**
	 * 商家开始配送(轨迹标题)
	 */
	public static final String ORDER_STORE_DELIVERY_TRACK_TITLE = "order.store.delivery.track.title";

	/**
	 * 商家开始配送-外送途中
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_DELIVERY_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_DELIVERY_TRACK_TITLE);
	}

	/**
	 * 商家开始配送(轨迹文本内容)
	 */
	public static final String ORDER_STORE_DELIVERY_TRACK_CONTENT = "order.store.delivery.track.content";

	/**
	 * 商家已开始配送
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_DELIVERY_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_DELIVERY_TRACK_CONTENT);
	}
	
	/**
	 * 顺丰配送(轨迹标题)
	 */
	public static final String ORDER_SF_DELIVERY_TRACK_TITLE = "order.sf.delivery.track.title";

	/**
	 * 顺丰配送-外送途中
	 * 
	 * @return
	 */
	public static final String ORDER_SF_DELIVERY_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SF_DELIVERY_TRACK_TITLE);
	}
	
	/**
	 * 顺丰配送（轨迹文本内容）
	 */
	public static final String ORDER_SF_DELIVERY_TRACK_CONTENT = "order.sf.delivery.track.content";
	
	/**
	 * 顺丰配送（轨迹文本内容）
	 */
	public static final String ORDER_SF_DELIVERY_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SF_DELIVERY_TRACK_CONTENT);
	}

	/**
	 * 商家确认送达(轨迹标题)
	 */
	public static final String ORDER_STORE_CONFIRM_TRACK_TITLE = "order.store.confirm.track.title";

	/**
	 * 已送达
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_CONFIRM_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_CONFIRM_TRACK_TITLE);
	}

	/**
	 * 商家确认送达(轨迹文本内容)
	 */
	public static final String ORDER_STORE_CONFIRM_TRACK_CONTENT = "order.store.confirm.track.content";

	/**
	 * 家已确认送达
	 * 
	 * @return
	 */
	public static final String ORDER_STORE_CONFIRM_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STORE_CONFIRM_TRACK_CONTENT);
	}

	/**
	 * 用户已签收(轨迹标题)
	 */
	public static final String ORDER_USER_RECEIPT_TRACK_TITLE = "order.user.receipt.track.title";

	/**
	 * 已签收
	 * 
	 * @return
	 */
	public static final String ORDER_USER_RECEIPT_TRACK_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USER_RECEIPT_TRACK_TITLE);
	}

	/**
	 * 用户已签收(轨迹标题)
	 */
	public static final String ORDER_USER_RECEIPT_TRACK_CONTENT = "order.user.receipt.track.content";

	/**
	 * 请尽快评论以完成订单
	 * 
	 * @return
	 */
	public static final String ORDER_USER_RECEIPT_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USER_RECEIPT_TRACK_CONTENT);
	}

	/**
	 * 餐厅ID不能为空
	 */
	public static final String ORDER_STOREID_EMPTY = "order.storeId.empty";

	/**
	 * 餐厅ID不能为空
	 */
	public static final String ORDER_STOREID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREID_EMPTY);
	}

	/**
	 * 开始时间不能大于结束时间
	 */
	public static final String ORDER_TIME_STARTGREATERTHANEND = "order.time.startGreaterThanEnd";

	/**
	 * 开始时间不能大于结束时间
	 */
	public static final String ORDER_TIME_STARTGREATERTHANEND() {
		return MultiLanguageAdapter.fetchMessage(ORDER_TIME_STARTGREATERTHANEND);
	}

	/**
	 * orderNo错误
	 */
	public static final String ORDER_ORDERADMINAPI_ORDERDETAIL_ORDERNO_ERROR = "order.orderAdminApi.orderDetail.orderNo.error";

	/**
	 * orderNo错误
	 */
	public static final String ORDER_ORDERADMINAPI_ORDERDETAIL_ORDERNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_ORDERDETAIL_ORDERNO_ERROR);
	}

	/**
	 * 订单不存在
	 */
	public static final String ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST = "order.orderAdminApi.orderDetail.order.notexist";

	/**
	 * 订单不存在
	 */
	public static final String ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_ORDERDETAIL_ORDER_NOTEXIST);
	}

	/**
	 * 订单号不正确
	 */
	public static final String ORDER_ORDERADMINAPI_CANCELORDER_ORDERNO_ERROR = "order.orderAdminApi.cancelOrder.orderNo.error";

	/**
	 * 订单号不正确
	 */
	public static final String ORDER_ORDERADMINAPI_CANCELORDER_ORDERNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_CANCELORDER_ORDERNO_ERROR);
	}

	/**
	 * 取消订单的原因不能为空
	 */
	public static final String ORDER_ORDERADMINAPI_CANCELORDER_REMARK_EMPTY = "order.orderAdminApi.cancelOrder.remark.empty";

	/**
	 * 取消订单的原因不能为空
	 */
	public static final String ORDER_ORDERADMINAPI_CANCELORDER_REMARK_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_CANCELORDER_REMARK_EMPTY);
	}

	/**
	 * 订单格式不合法
	 */
	public static final String ORDER_ORDERADMINAPI_ACCEPTORDER_ORDERNO_ILLEGAL = "order.orderAdminApi.acceptOrder.orderNo.illegal";

	/**
	 * 订单格式不合法
	 */
	public static final String ORDER_ORDERADMINAPI_ACCEPTORDER_ORDERNO_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_ACCEPTORDER_ORDERNO_ILLEGAL);
	}

	/**
	 * 配送方式参数错误
	 */
	public static final String ORDER_ORDERADMINAPI_VALIDATE_PICKTYPE_ERROR = "order.orderAdminApi.validate.pickType.error";

	/**
	 * 配送方式参数错误
	 */
	public static final String ORDER_ORDERADMINAPI_VALIDATE_PICKTYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_VALIDATE_PICKTYPE_ERROR);
	}

	/**
	 * 分页参数错误
	 */
	public static final String ORDER_ORDERADMINAPI_VALIDATE_PAGEINDEX_ERROR = "order.orderAdminApi.validate.pageIndex.error";

	/**
	 * 分页参数错误
	 */
	public static final String ORDER_ORDERADMINAPI_VALIDATE_PAGEINDEX_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_VALIDATE_PAGEINDEX_ERROR);
	}

	/**
	 * 开始时间错误
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTOREORDERBYCONDITION_START_ERROR = "order.orderAdminApi.statStoreOrderByCondition.start.error";

	/**
	 * 开始时间错误
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTOREORDERBYCONDITION_START_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_STATSTOREORDERBYCONDITION_START_ERROR);
	}

	/**
	 * 结束时间错误
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTOREORDERBYCONDITION_END_ERROR = "order.orderAdminApi.statStoreOrderByCondition.end.error";

	/**
	 * 结束时间错误
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTOREORDERBYCONDITION_END_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_STATSTOREORDERBYCONDITION_END_ERROR);
	}

	/**
	 * 开始时间不能为空
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTORESALERANK_STARTTIME_EMPTY = "order.orderAdminApi.statStoreSaleRank.startTime.empty";

	/**
	 * 开始时间不能为空
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTORESALERANK_STARTTIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_STATSTORESALERANK_STARTTIME_EMPTY);
	}

	/**
	 * 支付类型错误
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTORESALERANK_PAYTYPE_ERROR = "order.orderAdminApi.statStoreSaleRank.payType.error";

	/**
	 * 支付类型错误
	 */
	public static final String ORDER_ORDERADMINAPI_STATSTORESALERANK_PAYTYPE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERADMINAPI_STATSTORESALERANK_PAYTYPE_ERROR);
	}

	/**
	 * storeId不能为空
	 */
	public static final String ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_STOREID_EMPTY = "order.orderApi.queryOrderListForExcel.storeId.empty";

	/**
	 * storeId不能为空
	 */
	public static final String ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_STOREID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_STOREID_EMPTY);
	}

	/**
	 * startTime不能为空
	 */
	public static final String ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_STARTTIME_EMPTY = "order.orderApi.queryOrderListForExcel.startTime.empty";

	/**
	 * startTime不能为空
	 */
	public static final String ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_STARTTIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_STARTTIME_EMPTY);
	}

	/**
	 * endTime不能为空
	 */
	public static final String ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_ENDTIME_EMPTY = "order.orderApi.queryOrderListForExcel.endTime.empty";

	/**
	 * endTime不能为空
	 */
	public static final String ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_ENDTIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERAPI_QUERYORDERLISTFOREXCEL_ENDTIME_EMPTY);
	}

	/**
	 * 订单<{0}>详情的规格id 数据不正确, dishKey=<{1}>, specId=<{2}>
	 */
	public static final String ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SPECID_ERROR = "order.orderSpecValidationApi.validateOrderDetail.specId.error";

	/**
	 * 订单<{0}>详情的规格id 数据不正确, dishKey=<{1}>, specId=<{2}>
	 */
	public static final String ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SPECID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SPECID_ERROR);
	}

	/**
	 * 订单<{0}>详情的规格名称数据不正确, dishKey=<{1}>, specId=<{2}>,specName=<{3}>
	 */
	public static final String ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SPECNAME_ERROR = "order.orderSpecValidationApi.validateOrderDetail.specName.error";

	/**
	 * 订单<{0}>详情的规格名称数据不正确, dishKey=<{1}>, specId=<{2}>,specName=<{3}>
	 */
	public static final String ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SPECNAME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SPECNAME_ERROR);
	}

	/**
	 * 订单<{0}>详情的规格快照不存在, dishKey=<{1}>, specId=<{2}>,specName=<{3}>
	 */
	public static final String ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SNAPSHOT_NOTEXIST = "order.orderSpecValidationApi.validateOrderDetail.snapshot.notexist";

	/**
	 * 订单<{0}>详情的规格快照不存在, dishKey=<{1}>, specId=<{2}>,specName=<{3}>
	 */
	public static final String ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SNAPSHOT_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSPECVALIDATIONAPI_VALIDATEORDERDETAIL_SNAPSHOT_NOTEXIST);
	}

	/**
	 * 订单ID错误，必须大于0
	 */
	public static final String ORDER_COMMENTLIST_GETORDERCOMMENT_ORDERID_ERROR = "order.commentList.getOrderComment.orderId.error";

	/**
	 * 订单ID错误，必须大于0
	 */
	public static final String ORDER_COMMENTLIST_GETORDERCOMMENT_ORDERID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENTLIST_GETORDERCOMMENT_ORDERID_ERROR);
	}

	/**
	 * 评论星级不合法
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_STAR_ILLEGAL = "order.comment.validateAddComment.star.illegal";

	/**
	 * 评论星级不合法
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_STAR_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_VALIDATEADDCOMMENT_STAR_ILLEGAL);
	}

	/**
	 * 订单编号不能为空
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_ORDERNO_EMPTY = "order.comment.validateAddComment.orderNo.empty";

	/**
	 * 订单编号不能为空
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_ORDERNO_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_VALIDATEADDCOMMENT_ORDERNO_EMPTY);
	}

	/**
	 * 请尽量详细地描述本次用餐体验， 以方便我们快速跟进问题(12个字以上)
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_LENGTH = "order.comment.validateAddComment.content.length";

	/**
	 * 请尽量详细地描述本次用餐体验， 以方便我们快速跟进问题(12个字以上)
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_LENGTH() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_LENGTH);
	}

	/**
	 * 评论内容不能含有特殊字符
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_EMOJI = "order.comment.validateAddComment.content.emoji";

	/**
	 * 评论内容不能含有特殊字符
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_EMOJI);
	}

	/**
	 * 评论内容过长
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_LENGTH_MAX = "order.comment.validateAddComment.content.length.max";

	/**
	 * 评论内容过长
	 */
	public static final String ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_VALIDATEADDCOMMENT_CONTENT_LENGTH_MAX);
	}

	/**
	 * 订单号错误，必须大于0
	 */
	public static final String ORDER_COMMENT_REPLYCOMMENT_ORDERNO_ERROR = "order.comment.replyComment.orderNo.error";

	/**
	 * 订单号错误，必须大于0
	 */
	public static final String ORDER_COMMENT_REPLYCOMMENT_ORDERNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_REPLYCOMMENT_ORDERNO_ERROR);
	}

	/**
	 * 评论Id错误，必须大于0
	 */
	public static final String ORDER_COMMENT_REPLYCOMMENT_COMMENTID_ERROR = "order.comment.replyComment.commentId.error";

	/**
	 * 评论Id错误，必须大于0
	 */
	public static final String ORDER_COMMENT_REPLYCOMMENT_COMMENTID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_REPLYCOMMENT_COMMENTID_ERROR);
	}

	/**
	 * 回复内容不能为空
	 */
	public static final String ORDER_COMMENT_REPLYCOMMENT_CONTENT_EMPTY = "order.comment.replyComment.content.empty";

	/**
	 * 回复内容不能为空
	 */
	public static final String ORDER_COMMENT_REPLYCOMMENT_CONTENT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_REPLYCOMMENT_CONTENT_EMPTY);
	}

	/**
	 * 订单编号不能为空
	 */
	public static final String ORDER_ORDERLIST_ORDERDETAIL_ORDERNO_EMPTY = "order.orderList.orderDetail.orderNo.empty";

	/**
	 * 订单编号不能为空
	 */
	public static final String ORDER_ORDERLIST_ORDERDETAIL_ORDERNO_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERLIST_ORDERDETAIL_ORDERNO_EMPTY);
	}

	/**
	 * 订单状态错误,用户已确认过收货
	 */
	public static final String ORDER_ORDERRECEIVEDCONFIRM_VALIDATE_ORDER_ALREADYRECEIVED = "order.orderReceivedConfirm.validate.order.alreadyReceived";

	/**
	 * 订单状态错误,用户已确认过收货
	 */
	public static final String ORDER_ORDERRECEIVEDCONFIRM_VALIDATE_ORDER_ALREADYRECEIVED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERRECEIVEDCONFIRM_VALIDATE_ORDER_ALREADYRECEIVED);
	}

	/**
	 * 订单已锁定,请联系商家
	 */
	public static final String ORDER_ORDERRECEIVEDCONFIRM_VALIDATE_ORDER_LOCKED = "order.orderReceivedConfirm.validate.order.locked";

	/**
	 * 订单已锁定,请联系商家
	 */
	public static final String ORDER_ORDERRECEIVEDCONFIRM_VALIDATE_ORDER_LOCKED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERRECEIVEDCONFIRM_VALIDATE_ORDER_LOCKED);
	}

	/**
	 * 您未选择任何美食，请继续挑选
	 */
	public static final String ORDER_ORDER_CONVERTTOPURCHASEDETAIL_PURCHASEDETAILLIST_EMPTY = "order.order.convertToPurchaseDetail.purchaseDetailList.empty";

	/**
	 * 您未选择任何美食，请继续挑选
	 */
	public static final String ORDER_ORDER_CONVERTTOPURCHASEDETAIL_PURCHASEDETAILLIST_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CONVERTTOPURCHASEDETAIL_PURCHASEDETAILLIST_EMPTY);
	}

	/**
	 * 您选择的美食已经下架，请刷新界面重新选择
	 */
	public static final String ORDER_ORDER_TOPURCHASEDETAIL_SPEC_EMPTY = "order.order.toPurchaseDetail.spec.empty";

	/**
	 * 您选择的美食已经下架，请刷新界面重新选择
	 */
	public static final String ORDER_ORDER_TOPURCHASEDETAIL_SPEC_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_TOPURCHASEDETAIL_SPEC_EMPTY);
	}

	/**
	 * 由于获取不到您的坐标，无法计算费用，请检查您的定位功能是否正确开启。
	 */
	public static final String ORDER_ORDER_DORELOADNEWSETTLEPARAM_POINT_EMPTY = "order.order.doReloadNewSettleParam.point.empty";

	/**
	 * 由于获取不到您的坐标，无法计算费用，请检查您的定位功能是否正确开启。
	 */
	public static final String ORDER_ORDER_DORELOADNEWSETTLEPARAM_POINT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_DORELOADNEWSETTLEPARAM_POINT_EMPTY);
	}

	/**
	 * 购买美食信息不能为空
	 */
	public static final String ORDER_ORDER_SPILTBUY_DISH_EMPTY = "order.order.spiltBuy.dish.empty";

	/**
	 * 购买美食信息不能为空
	 */
	public static final String ORDER_ORDER_SPILTBUY_DISH_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_SPILTBUY_DISH_EMPTY);
	}

	/**
	 * 购买数量不能为空
	 */
	public static final String ORDER_ORDER_SPILTBUY_DISH_NUM_EMPTY = "order.order.spiltBuy.dish.num.empty";

	/**
	 * 购买数量不能为空
	 */
	public static final String ORDER_ORDER_SPILTBUY_DISH_NUM_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_SPILTBUY_DISH_NUM_EMPTY);
	}

	/**
	 * 美食排序和购买数量排序不一致
	 */
	public static final String ORDER_ORDER_SPILTBUY_DISH_SEQUENCE_ERROR = "order.order.spiltBuy.dish.sequence.error";

	/**
	 * 美食排序和购买数量排序不一致
	 */
	public static final String ORDER_ORDER_SPILTBUY_DISH_SEQUENCE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_SPILTBUY_DISH_SEQUENCE_ERROR);
	}

	/**
	 * 购买美食的数量错误
	 */
	public static final String ORDER_ORDER_VALIDATEPURCHASEDETAILS_NUM_ERROR = "order.order.validatePurchaseDetails.num.error";

	/**
	 * 购买美食的数量错误
	 */
	public static final String ORDER_ORDER_VALIDATEPURCHASEDETAILS_NUM_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATEPURCHASEDETAILS_NUM_ERROR);
	}

	/**
	 * 支付方式不合法
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL = "order.order.validateCreateOrder.mode.illegal";

	/**
	 * 支付方式不合法
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATECREATEORDER_MODE_ILLEGAL);
	}

	/**
	 * 支付货币不合法
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_CURRENCYTYPE_ILLEGAL = "order.order.validateCreateOrder.currencyType.illegal";

	/**
	 * 支付货币不合法
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_CURRENCYTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATECREATEORDER_CURRENCYTYPE_ILLEGAL);
	}

	/**
	 * 支付类型不合法
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_PAYTYPE_ILLEGAL = "order.order.validateCreateOrder.payType.illegal";

	/**
	 * 支付类型不合法
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_PAYTYPE_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATECREATEORDER_PAYTYPE_ILLEGAL);
	}

	/**
	 * 备注长度超过限制。
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_REMARK_LENGTH_OVERLIMIT = "order.order.validateCreateOrder.remark.length.overlimit";

	/**
	 * 备注长度超过限制。
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_REMARK_LENGTH_OVERLIMIT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATECREATEORDER_REMARK_LENGTH_OVERLIMIT);
	}
	
	/**
	 * 桌号输入过长，请不要超过16个字
	 */
	public static final String ORDER_USER_TABLE_NO_LENGTH_OVERLIMIT = "order.user.table.no.length.overlimit";

	/**
	 * 桌号输入过长，请不要超过16个字
	 */
	public static final String ORDER_USER_TABLE_NO_LENGTH_OVERLIMIT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USER_TABLE_NO_LENGTH_OVERLIMIT);
	}

	/**
	 * 备注不能含有特殊字符
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_REMARK_EMOJI = "order.order.validateCreateOrder.remark.emoji";

	/**
	 * 备注不能含有特殊字符
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_REMARK_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATECREATEORDER_REMARK_EMOJI);
	}
	
	/**
	 * 桌号输入内容不能有特殊字符，请检查
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_TABLE_NO_EMOJI = "order.order.validateCreateOrder.table.no.emoji";

	/**
	 * 桌号输入内容不能有特殊字符，请检查
	 */
	public static final String ORDER_ORDER_VALIDATECREATEORDER_TABLE_NO_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATECREATEORDER_TABLE_NO_EMOJI);
	}

	/**
	 * 获取订单信息失败，请重新再试一次
	 */
	public static final String ORDER_COUPON_READYPAY_STORE_EMPTY = "order.coupon.readyPay.store.empty";

	/**
	 * 获取订单信息失败，请重新再试一次
	 */
	public static final String ORDER_COUPON_READYPAY_STORE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_READYPAY_STORE_EMPTY);
	}

	/**
	 * 兑换码不能为空
	 */
	public static final String ORDER_COUPON_EXCHANGECOUPON_COUPONCODE_EMPTY = "order.coupon.exchangeCoupon.couponCode.empty";

	/**
	 * 兑换码不能为空
	 */
	public static final String ORDER_COUPON_EXCHANGECOUPON_COUPONCODE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_EXCHANGECOUPON_COUPONCODE_EMPTY);
	}

	/**
	 * 订单商品不能为空
	 */
	public static final String ORDER_SETTLE_DISHID_EMPTY = "order.settle.dishId.empty";

	/**
	 * 订单商品不能为空
	 */
	public static final String ORDER_SETTLE_DISHID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_DISHID_EMPTY);
	}

	/**
	 * 订单商品数量不能为空
	 */
	public static final String ORDER_SETTLE_NUM_EMPTY = "order.settle.num.empty";

	/**
	 * 订单商品数量不能为空
	 */
	public static final String ORDER_SETTLE_NUM_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_NUM_EMPTY);
	}

	/**
	 * 用户的IM ID不合法
	 */
	public static final String ORDER_ORDERIM_GETINPROGRESSORDERS_IMID_ERROR = "order.orderIM.getInProgressOrders.imId.error";

	/**
	 * 用户的IM ID不合法
	 */
	public static final String ORDER_ORDERIM_GETINPROGRESSORDERS_IMID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERIM_GETINPROGRESSORDERS_IMID_ERROR);
	}

	/**
	 * 搜索内容不能为空
	 */
	public static final String ORDER_ORDERSEARCH_QUERY_CONTENT_EMPTY = "order.orderSearch.query.content.empty";

	/**
	 * 搜索内容不能为空
	 */
	public static final String ORDER_ORDERSEARCH_QUERY_CONTENT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSEARCH_QUERY_CONTENT_EMPTY);
	}

	/**
	 * 搜索类别不合法
	 */
	public static final String ORDER_ORDERSEARCH_QUERY_CATEGORY_ERROR = "order.orderSearch.query.category.error";

	/**
	 * 搜索类别不合法
	 */
	public static final String ORDER_ORDERSEARCH_QUERY_CATEGORY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSEARCH_QUERY_CATEGORY_ERROR);
	}

	/**
	 * 上报打印结果，订单编号不存在：{0}
	 */
	public static final String ORDER_ORDERSERVICE_REPORTPRINT_ORDER_EMPTY = "order.orderService.reportPrint.order.empty";

	/**
	 * 上报打印结果，订单编号不存在：{0}
	 */
	public static final String ORDER_ORDERSERVICE_REPORTPRINT_ORDER_EMPTY(long orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSERVICE_REPORTPRINT_ORDER_EMPTY, orderNo);
	}

	/**
	 * 用户ID不合法
	 */
	public static final String ORDER_ORDERSERVICE_HASNEWORDER_UID_ERROR = "order.orderService.hasNewOrder.uid.error";

	/**
	 * 用户ID不合法
	 */
	public static final String ORDER_ORDERSERVICE_HASNEWORDER_UID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSERVICE_HASNEWORDER_UID_ERROR);
	}

	/**
	 * 餐厅ID不合法
	 */
	public static final String ORDER_ORDERSERVICE_HASNEWORDER_STOREID_ERROR = "order.orderService.hasNewOrder.storeId.error";

	/**
	 * 餐厅ID不合法
	 */
	public static final String ORDER_ORDERSERVICE_HASNEWORDER_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERSERVICE_HASNEWORDER_STOREID_ERROR);
	}

	/**
	 * 订单序列号不合法,必须大于0
	 */
	public static final String ORDER_STOREORDEROPERATION_ACCEPTORDER_ORDERNO_ERROR = "order.storeOrderOperation.acceptOrder.orderNo.error";

	/**
	 * 订单序列号不合法,必须大于0
	 */
	public static final String ORDER_STOREORDEROPERATION_ACCEPTORDER_ORDERNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDEROPERATION_ACCEPTORDER_ORDERNO_ERROR);
	}

	/**
	 * 商家确认收货，订单编号不存在：{0}
	 */
	public static final String ORDER_STOREORDEROPERATION_FINISHCOOKING_ORDER_EMPTY = "order.storeOrderOperation.finishCooking.order.empty";

	/**
	 * 商家确认收货，订单编号不存在：{0}
	 */
	public static final String ORDER_STOREORDEROPERATION_FINISHCOOKING_ORDER_EMPTY(long orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDEROPERATION_FINISHCOOKING_ORDER_EMPTY, orderNo);
	}

	/**
	 * 配送失败，订单编号不存在：{0}
	 */
	public static final String ORDER_STOREORDEROPERATION_DELIVERY_ORDER_EMPTY = "order.storeOrderOperation.delivery.order.empty";

	/**
	 * 配送失败，订单编号不存在：{0}
	 */
	public static final String ORDER_STOREORDEROPERATION_DELIVERY_ORDER_EMPTY(long orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDEROPERATION_DELIVERY_ORDER_EMPTY, orderNo);
	}

	/**
	 * 订单开始时间不合法
	 */
	public static final String ORDER_STOREORDERSDEPRECATED_STORENEWORDERS2_ORDERBEGINTIME_ERROR = "order.storeOrdersDeprecated.storeNewOrders2.orderBeginTime.error";

	/**
	 * 订单开始时间不合法
	 */
	public static final String ORDER_STOREORDERSDEPRECATED_STORENEWORDERS2_ORDERBEGINTIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDERSDEPRECATED_STORENEWORDERS2_ORDERBEGINTIME_ERROR);
	}

	/**
	 * 订单编号不合法
	 */
	public static final String ORDER_STOREORDERS_DETAIL_ORDERNO_ERROR = "order.storeOrders.detail.orderNo.error";

	/**
	 * 订单编号不合法
	 */
	public static final String ORDER_STOREORDERS_DETAIL_ORDERNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDERS_DETAIL_ORDERNO_ERROR);
	}

	/**
	 * 订单排序号不合法
	 */
	public static final String ORDER_STOREORDERS_ORDERSONCOOKING2_SEQUENCE_ERROR = "order.storeOrders.ordersOnCooking2.sequence.error";

	/**
	 * 订单排序号不合法
	 */
	public static final String ORDER_STOREORDERS_ORDERSONCOOKING2_SEQUENCE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDERS_ORDERSONCOOKING2_SEQUENCE_ERROR);
	}

	/**
	 * 退款创建时间不合法
	 */
	public static final String ORDER_STOREORDERS_ORDERSONREFUND2_REFUNDCREATETIME_ERROR = "order.storeOrders.ordersOnRefund2.refundCreateTime.error";

	/**
	 * 退款创建时间不合法
	 */
	public static final String ORDER_STOREORDERS_ORDERSONREFUND2_REFUNDCREATETIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDERS_ORDERSONREFUND2_REFUNDCREATETIME_ERROR);
	}

	/**
	 * 订单编号不合法,必须大于0
	 */
	public static final String ORDER_STOREUSER_RATEUSER_ORDERNO_ERROR = "order.storeUser.rateUser.orderNo.error";

	/**
	 * 订单编号不合法,必须大于0
	 */
	public static final String ORDER_STOREUSER_RATEUSER_ORDERNO_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREUSER_RATEUSER_ORDERNO_ERROR);
	}

	/**
	 * 评论星级不合法
	 */
	public static final String ORDER_STOREUSER_RATEUSER_STAR_ERROR = "order.storeUser.rateUser.star.error";

	/**
	 * 评论星级不合法
	 */
	public static final String ORDER_STOREUSER_RATEUSER_STAR_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREUSER_RATEUSER_STAR_ERROR);
	}

	/**
	 * 已售馨
	 */
	public static final String ORDER_SURPLUS_ZERO = "order.surplus.zero";

	/**
	 * 已售馨<br>
	 * 
	 * 库存剩余数量=0时候，显示的提示语
	 */
	public static final String ORDER_SURPLUS_ZERO() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SURPLUS_ZERO);
	}

	/**
	 * 仅余{0}份
	 */
	public static final String ORDER_SURPLUS_NOT_ENOUGH = "order.surplus.notenough";

	/**
	 * 仅余{0}份<br>
	 * 
	 * 库存剩余数量不足，单>0时候，显示的提示语
	 */
	public static final String ORDER_SURPLUS_NOT_ENOUGH(int surplus) {
		return MultiLanguageAdapter.fetchMessage(ORDER_SURPLUS_NOT_ENOUGH, surplus);
	}

	/**
	 * 自行取消
	 */
	public static final String ORDER_USER_CANCEL_NOTPAY_REASON = "order.user.cancel.notPay.reason";

	/**
	 * 自行取消<br>
	 * 用户取消付订单-记录取消的原因
	 */
	public static final String ORDER_USER_CANCEL_NOTPAY_REASON() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USER_CANCEL_NOTPAY_REASON);
	}

	/**
	 * 超时自动取消<br>
	 * 
	 * 取消超时的订单-记录取消的原因
	 */
	public static final String ORDER_TIMEOUT_AUTOCANCEL_REASON = "order.timeout.autoCancel.reason";

	/**
	 * 超时自动取消
	 */
	public static final String ORDER_TIMEOUT_AUTOCANCEL_REASON() {
		return MultiLanguageAdapter.fetchMessage(ORDER_TIMEOUT_AUTOCANCEL_REASON);
	}

	/**
	 * 米星平台取消<br>
	 * 管理员取消的订单-记录取消的原因
	 */
	public static final String ORDER_ADMIN_CANCEL_REASON = "order.admin.cancel.reason";

	/**
	 * 米星平台取消
	 */
	public static final String ORDER_ADMIN_CANCEL_REASON() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ADMIN_CANCEL_REASON);
	}

	/**
	 * 无可用优惠券
	 */
	public static final String ORDER_AVAILABLE_COUPON_NONE = "order.available.coupon.none";

	/**
	 * 
	 * 客户端可用优惠券button标题-无可用优惠券
	 */
	public static final String ORDER_AVAILABLE_COUPON_NONE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_AVAILABLE_COUPON_NONE);
	}
	
	/**
	 * 无可用
	 */
	public static final String MAZING_PAY_AVAILABLE_COUPON_NONE = "mazingPay.available.coupon.none";

	/**
	 * 
	 * 客户端可用优惠券button标题-无可用（适用于米星付情况）
	 */
	public static final String MAZING_PAY_AVAILABLE_COUPON_NONE(){
		return MultiLanguageAdapter.fetchMessage(MAZING_PAY_AVAILABLE_COUPON_NONE);
	}

	/**
	 * 有可用优惠券
	 */
	public static final String ORDER_AVAILABLE_COUPON_HAS = "order.available.coupon.has";

	/**
	 * 有可用优惠券
	 */
	public static final String ORDER_AVAILABLE_COUPON_HAS() {
		return MultiLanguageAdapter.fetchMessage(ORDER_AVAILABLE_COUPON_HAS);
	}

	/**
	 * 你的请求已提交，请耐心等待
	 */
	public static final String ORDER_CREATE_ALREADY_ORDERED = "order.create.already.ordered";

	/**
	 * 你的请求已提交，请耐心等待
	 */
	public static final String ORDER_CREATE_ALREADY_ORDERED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CREATE_ALREADY_ORDERED);
	}

	/**
	 * 时间格式出错
	 */
	public static final String ORDER_REPORT_TIME_ERROR = "order.report.time.error";

	/**
	 * 时间格式出错
	 */
	public static final String ORDER_REPORT_TIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_TIME_ERROR);
	}

	/**
	 * 取消原因不能含有表情
	 */
	public static final String REJECTORDER_REJECTCOMMENT_HAS_EMOJI = "order.storeOrderOperation.rejectOrder.rejectComment.emoji";

	/**
	 * 取消原因不能含有表情
	 */
	public static final String REJECTORDER_REJECTCOMMENT_HAS_EMOJI() {
		return MultiLanguageAdapter.fetchMessage(REJECTORDER_REJECTCOMMENT_HAS_EMOJI);
	}

	/**
	 * 门店不存在
	 */
	public static final String ORDER_ORDERDETAIL_STORE_NOTEXIST = "order.orderDetail.store.notexist";

	/**
	 * 门店不存在
	 */
	public static final String ORDER_ORDERDETAIL_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_STORE_NOTEXIST);
	}

	/**
	 * 没操作订单的权限
	 */
	public static final String ORDER_ORDERDETAIL_AUTHORITY_NONE = "order.orderDetail.authority.none";

	/**
	 * 没操作订单的权限
	 */
	public static final String ORDER_ORDERDETAIL_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_AUTHORITY_NONE);
	}

	/**
	 * 订单开始时间或预计送达时间不能为空
	 */
	public static final String ORDER_ORDERDETAIL_STARTTIME_DELIVERYTIME_EMPTY = "order.orderDetail.startTime.deliveryTime.empty";

	/**
	 * 订单开始时间或预计送达时间不能为空
	 */
	public static final String ORDER_ORDERDETAIL_STARTTIME_DELIVERYTIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_STARTTIME_DELIVERYTIME_EMPTY);
	}

	/**
	 * 用户信息不存在
	 */
	public static final String ORDER_ORDER_CREATEORDER_PROFILE_NOTEXIST = "order.order.createOrder.profile.notexist";

	/**
	 * 用户信息不存在
	 */
	public static final String ORDER_ORDER_CREATEORDER_PROFILE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CREATEORDER_PROFILE_NOTEXIST);
	}

	/**
	 * 美食不存在或已下架，请刷新界面重新确认
	 */
	public static final String ORDER_ORDER_CREATEORDER_CREATEORDERDETAIL_NOTEXIST = "order.order.createOrder.createOrderDetail.notexist";

	/**
	 * 美食不存在或已下架，请刷新界面重新确认
	 */
	public static final String ORDER_ORDER_CREATEORDER_CREATEORDERDETAIL_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CREATEORDER_CREATEORDERDETAIL_NOTEXIST);
	}

	/**
	 * 美食不存在
	 */
	public static final String ORDER_ORDERDETAIL_DISH_NOTEXIST = "order.orderDetail.dish.notexist";

	/**
	 * 美食不存在
	 */
	public static final String ORDER_ORDERDETAIL_DISH_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_DISH_NOTEXIST);
	}

	/**
	 * 该美食已下架
	 */
	public static final String ORDER_ORDERDETAIL_DISH_OFFSHELF = "order.orderDetail.dish.offshelf";

	/**
	 * 该美食已下架
	 */
	public static final String ORDER_ORDERDETAIL_DISH_OFFSHELF() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_DISH_OFFSHELF);
	}

	/**
	 * 您选择的配送时间已经过期，请刷新界面重新选择
	 */
	public static final String ORDER_ORDER_CREATEORDER_ORDER_SERVICEDAY_EXPIRE = "order.order.createOrder.order.serviceDay.expire";

	/**
	 * 您选择的配送时间已经过期，请刷新界面重新选择
	 */
	public static final String ORDER_ORDER_CREATEORDER_ORDER_SERVICEDAY_EXPIRE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CREATEORDER_ORDER_SERVICEDAY_EXPIRE);
	}

	/**
	 * 保存订单失败
	 */
	public static final String ORDER_ORDER_CREATEORDER_SAVEORDER_FAILURE = "order.order.createOrder.saveOrder.failure";

	/**
	 * 保存订单失败
	 */
	public static final String ORDER_ORDER_CREATEORDER_SAVEORDER_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CREATEORDER_SAVEORDER_FAILURE);
	}

	/**
	 * 存在serialLock锁
	 */
	public static final String ORDER_ORDER_CREATEORDER_GETSERIAL_LOCK_EXIST = "order.order.createOrder.getSerial.lock.exist";

	/**
	 * 存在serialLock锁
	 */
	public static final String ORDER_ORDER_CREATEORDER_GETSERIAL_LOCK_EXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CREATEORDER_GETSERIAL_LOCK_EXIST);
	}

	/**
	 * 商家不存在
	 */
	public static final String ORDER_ORDER_GETPAYMENT_STORE_NOTEXIST = "order.order.getPayment.store.notexist";

	/**
	 * 商家不存在
	 */
	public static final String ORDER_ORDER_GETPAYMENT_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_GETPAYMENT_STORE_NOTEXIST);
	}

	/**
	 * 获取支付信息失败，请重新刷新再试
	 */
	public static final String ORDER_ORDER_GETPAYMENT_SYSTYPE_EMPTY = "order.order.getPayment.sysType.empty";

	/**
	 * 获取支付信息失败，请重新刷新再试
	 */
	public static final String ORDER_ORDER_GETPAYMENT_SYSTYPE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_GETPAYMENT_SYSTYPE_EMPTY);
	}

	/**
	 * 获取支付信息失败，请更换支付方式
	 */
	public static final String ORDER_ORDER_GETPAYMENT_PAYMODE_ERROR = "order.order.getPayment.payMode.error";

	/**
	 * 获取支付信息失败，请更换支付方式
	 */
	public static final String ORDER_ORDER_GETPAYMENT_PAYMODE_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_GETPAYMENT_PAYMODE_ERROR);
	}

	/**
	 * 您的订单由于超过30分钟未支付被系统取消，请重新下单
	 */
	public static final String ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_TIMEOUT = "order.order.changePayMode.order.status.timeout";

	/**
	 * 您的订单由于超过30分钟未支付被系统取消，请重新下单
	 */
	public static final String ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_TIMEOUT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_TIMEOUT);
	}

	/**
	 * 订单状态已改变，请重新刷新确认
	 */
	public static final String ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_CHANGED = "order.order.changePayMode.order.status.changed";

	/**
	 * 订单状态已改变，请重新刷新确认
	 */
	public static final String ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_CHANGED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CHANGEPAYMODE_ORDER_STATUS_CHANGED);
	}

	/**
	 * 很抱歉，订单不允许更换优惠券
	 */
	public static final String ORDER_ORDER_USECOUPON_COUPON_CHANGE = "order.order.useCoupon.coupon.change";

	/**
	 * 很抱歉，订单不允许更换优惠券
	 */
	public static final String ORDER_ORDER_USECOUPON_COUPON_CHANGE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USECOUPON_COUPON_CHANGE);
	}
	
	/**
	 * 米星券使用失败，请尝试重新下单
	 */
	public static final String ORDER_ORDER_CAN_NOT_USE_COUPON = "order.order.cannot.use.coupon";

	/**
	 * 米星券使用失败，请尝试重新下单
	 */
	public static final String ORDER_ORDER_CAN_NOT_USE_COUPON() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CAN_NOT_USE_COUPON);
	}

	/**
	 * 用户信息查询失败，请重试
	 */
	public static final String ORDER_ORDER_USECOUPON_GETUSER_FAILURE = "order.order.useCoupon.getUser.failure";

	/**
	 * 用户信息查询失败，请重试
	 */
	public static final String ORDER_ORDER_USECOUPON_GETUSER_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USECOUPON_GETUSER_FAILURE);
	}

	/**
	 * 此优惠券已过期
	 */
	public static final String ORDER_ORDER_USECOUPON_FAILURE = "order.order.useCoupon.failure";

	/**
	 * 此优惠券已过期
	 */
	public static final String ORDER_ORDER_USECOUPON_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USECOUPON_FAILURE);
	}

	/**
	 * 门店营业时间未设置或门店不合法
	 */
	public static final String ORDER_ORDER_INSERVICETIME_STORE_SCHEDULE_EMPTY = "order.order.inServiceTime.store.schedule.empty";

	/**
	 * 门店营业时间未设置或门店不合法
	 */
	public static final String ORDER_ORDER_INSERVICETIME_STORE_SCHEDULE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_INSERVICETIME_STORE_SCHEDULE_EMPTY);
	}

	/**
	 * 不支持货到付款
	 */
	public static final String ORDER_ORDER_UPDATEPAYMODETOCASH_CASH_UNSUPPORT = "order.order.updatePayModeToCash.cash.unsupport";

	/**
	 * 不支持货到付款
	 */
	public static final String ORDER_ORDER_UPDATEPAYMODETOCASH_CASH_UNSUPPORT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_UPDATEPAYMODETOCASH_CASH_UNSUPPORT);
	}

	/**
	 * 更换支付方式失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_ORDER_UPDATEPAYTYPEBYCASH_FAILURE = "order.order.updatePayTypeByCash.failure";

	/**
	 * 更换支付方式失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_ORDER_UPDATEPAYTYPEBYCASH_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_UPDATEPAYTYPEBYCASH_FAILURE);
	}

	/**
	 * 餐厅不存在，请重试
	 */
	public static final String ORDER_ORDER_UPDATEPAYMODETOONLINE_STORE_NOTEXIST = "order.order.updatePayModeToOnline.store.notexist";

	/**
	 * 餐厅不存在，请重试
	 */
	public static final String ORDER_ORDER_UPDATEPAYMODETOONLINE_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_UPDATEPAYMODETOONLINE_STORE_NOTEXIST);
	}

	/**
	 * 更换支付方式失败，请刷新数据
	 */
	public static final String ORDER_ORDER_UPDATEPAYNO_FAILURE = "order.order.updatePayNo.failure";

	/**
	 * 更换支付方式失败，请刷新数据
	 */
	public static final String ORDER_ORDER_UPDATEPAYNO_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_UPDATEPAYNO_FAILURE);
	}

	/**
	 * 评论不合法
	 */
	public static final String ORDER_ORDER_ORDERCOMMENT_COMMENTID_ILLEGAL = "order.order.orderComment.commentId.illegal";

	/**
	 * 评论不合法
	 */
	public static final String ORDER_ORDER_ORDERCOMMENT_COMMENTID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_ORDERCOMMENT_COMMENTID_ILLEGAL);
	}

	/**
	 * 订单不能重复评论
	 */
	public static final String ORDER_ORDER_ORDERCOMMENT_REPEAT = "order.order.orderComment.repeat";

	/**
	 * 订单不能重复评论
	 */
	public static final String ORDER_ORDER_ORDERCOMMENT_REPEAT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_ORDERCOMMENT_REPEAT);
	}

	/**
	 * 订单状态不正常，需为用户确认收货才能点评
	 */
	public static final String ORDER_ORDER_ORDERCOMMENT_ORDER_STATUS_NOTSATISFY = "order.order.orderComment.order.status.notsatisfy";

	/**
	 * 订单状态不正常，需为用户确认收货才能点评
	 */
	public static final String ORDER_ORDER_ORDERCOMMENT_ORDER_STATUS_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_ORDERCOMMENT_ORDER_STATUS_NOTSATISFY);
	}

	/**
	 * 备注太长
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_REMARK_LENGTH_MAX = "order.order.payNotify.remark.length.max";

	/**
	 * 备注太长
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_REMARK_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_PAYNOTIFY_REMARK_LENGTH_MAX);
	}

	/**
	 * 订单不存在，请重新试试
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_ORDERNO_NOTEXIST = "order.order.payNotify.orderNo.notexist";

	/**
	 * 订单不存在，请重新试试
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_ORDERNO_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_PAYNOTIFY_ORDERNO_NOTEXIST);
	}

	/**
	 * 支付记录不存在，请重新试试
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_PAYNO_NOTEXIST = "order.order.payNotify.payNo.notexist";

	/**
	 * 支付记录不存在，请重新试试
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_PAYNO_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_PAYNOTIFY_PAYNO_NOTEXIST);
	}

	/**
	 * 支付结果不合法
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_PAYSTATUS_ILLEGAL = "order.order.payNotify.payStatus.illegal";

	/**
	 * 支付结果不合法
	 */
	public static final String ORDER_ORDER_PAYNOTIFY_PAYSTATUS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_PAYNOTIFY_PAYSTATUS_ILLEGAL);
	}

	/**
	 * 订单是货到付款，不允许退款
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_REFUND_NOTSUPPORT = "order.order.applyRefundToOpen.order.refund.notsupport";

	/**
	 * 订单是货到付款，不允许退款
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_REFUND_NOTSUPPORT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_REFUND_NOTSUPPORT);
	}

	/**
	 * 订单状态已更新，请重新刷新
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_STATUS_CHANGED = "order.order.applyRefundToOpen.order.status.changed";

	/**
	 * 订单状态已更新，请重新刷新
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_STATUS_CHANGED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_STATUS_CHANGED);
	}

	/**
	 * 订单没付款，不允许退款
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_REFUND_NOTPAY = "order.order.applyRefundToOpen.order.refund.notpay";

	/**
	 * 订单没付款，不允许退款
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_REFUND_NOTPAY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_APPLYREFUNDTOOPEN_ORDER_REFUND_NOTPAY);
	}

	/**
	 * 订单状态已更新，请刷新界面
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_UPDATETOREFUND_FAILURE = "order.order.applyRefundToOpen.updateToRefund.failure";

	/**
	 * 订单状态已更新，请刷新界面
	 */
	public static final String ORDER_ORDER_APPLYREFUNDTOOPEN_UPDATETOREFUND_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_APPLYREFUNDTOOPEN_UPDATETOREFUND_FAILURE);
	}

	/**
	 * 订单不是申请退款
	 */
	public static final String ORDER_ORDER_SUCCESSREFUND_ORDER_STATUS_NOTAPPLY = "order.order.successRefund.order.status.notapply";

	/**
	 * 订单不是申请退款
	 */
	public static final String ORDER_ORDER_SUCCESSREFUND_ORDER_STATUS_NOTAPPLY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_SUCCESSREFUND_ORDER_STATUS_NOTAPPLY);
	}

	/**
	 * 商家已接单，不允许取消订单
	 */
	public static final String ORDER_ORDER_USERCANCELORDER_ORDER_CANCEL_FORBID = "order.order.userCancelOrder.order.cancel.forbid";

	/**
	 * 商家已接单，不允许取消订单
	 */
	public static final String ORDER_ORDER_USERCANCELORDER_ORDER_CANCEL_FORBID() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USERCANCELORDER_ORDER_CANCEL_FORBID);
	}

	/**
	 * 更新数据库失败
	 */
	public static final String ORDER_ORDER_CANCELORDER_UPDATETOCANCEL_FAILURE = "order.order.cancelOrder.updateToCancel.failure";

	/**
	 * 更新数据库失败
	 */
	public static final String ORDER_ORDER_CANCELORDER_UPDATETOCANCEL_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_CANCELORDER_UPDATETOCANCEL_FAILURE);
	}

	/**
	 * 用户收货地址不存在
	 */
	public static final String ORDER_ORDER_VALIDATEADDRESS_ADDRESS_NOTEXIST = "order.order.validateAddress.address.notexist";

	/**
	 * 用户收货地址不存在
	 */
	public static final String ORDER_ORDER_VALIDATEADDRESS_ADDRESS_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_VALIDATEADDRESS_ADDRESS_NOTEXIST);
	}

	/**
	 * 订单号：{0} 请尽快支付
	 */
	public static final String ORDER_ORDER_SAVEORDER_TEXT = "order.order.saveOrder.text";

	/**
	 * 订单号：{0} 请尽快支付
	 */
	public static final String ORDER_ORDER_SAVEORDER_TEXT(long orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_SAVEORDER_TEXT,String.valueOf(orderNo));
	}

	/**
	 * 购买的美食数量太大，请重新下单
	 */
	public static final String ORDER_ORDER_MERGEORDERDETAIL_TOTALFEE_TOOLARGE = "order.order.mergeOrderDetail.totalFee.toolarge";

	/**
	 * 购买的美食数量太大，请重新下单
	 */
	public static final String ORDER_ORDER_MERGEORDERDETAIL_TOTALFEE_TOOLARGE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_MERGEORDERDETAIL_TOTALFEE_TOOLARGE);
	}

	/**
	 * 订单正在被处理中, 请稍后
	 */
	public static final String ORDER_ORDER_ORDERRERUNPAYJOB_EXIST = "order.order.OrderRerunPayJob.exist";

	/**
	 * 订单正在被处理中, 请稍后
	 */
	public static final String ORDER_ORDER_ORDERRERUNPAYJOB_EXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_ORDERRERUNPAYJOB_EXIST);
	}

	/**
	 * 订单已锁定，请联系商家
	 */
	public static final String ORDER_ORDER_USERRECEIVECONFIRM_ORDER_LOCKED = "order.order.userReceiveConfirm.order.locked";

	/**
	 * 订单已锁定，请联系商家
	 */
	public static final String ORDER_ORDER_USERRECEIVECONFIRM_ORDER_LOCKED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USERRECEIVECONFIRM_ORDER_LOCKED);
	}

	/**
	 * 您已确认过收货
	 */
	public static final String ORDER_ORDER_USERRECEIVECONFIRM_ORDER_ALREADYCONFIRMED = "order.order.userReceiveConfirm.order.alreadyConfirmed";

	/**
	 * 您已确认过收货
	 */
	public static final String ORDER_ORDER_USERRECEIVECONFIRM_ORDER_ALREADYCONFIRMED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USERRECEIVECONFIRM_ORDER_ALREADYCONFIRMED);
	}

	/**
	 * 订单还不能确认收货
	 */
	public static final String ORDER_ORDER_USERRECEIVECONFIRM_ORDER_CONFIRM_NOTSATISFY = "order.order.userReceiveConfirm.order.confirm.notsatisfy";

	/**
	 * 订单还不能确认收货
	 */
	public static final String ORDER_ORDER_USERRECEIVECONFIRM_ORDER_CONFIRM_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_USERRECEIVECONFIRM_ORDER_CONFIRM_NOTSATISFY);
	}

	/**
	 * 确认收货失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_ORDER_UPDATEUSERCONFIRM_FAILURE = "order.order.updateUserConfirm.failure";

	/**
	 * 确认收货失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_ORDER_UPDATEUSERCONFIRM_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_UPDATEUSERCONFIRM_FAILURE);
	}

	/**
	 * 订单是线上支付，请退款处理
	 */
	public static final String ORDER_ORDER_ADMINCANCELORDER_PAYTYPE_ISONLINE = "order.order.adminCancelOrder.payType.isOnline";

	/**
	 * 订单是线上支付，请退款处理
	 */
	public static final String ORDER_ORDER_ADMINCANCELORDER_PAYTYPE_ISONLINE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_ADMINCANCELORDER_PAYTYPE_ISONLINE);
	}

	/**
	 * 订单非现金支付，请联系米星平台处理
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_PAYTYPE_NOTCASH = "order.order.storeCancelOrder.order.payType.notCash";

	/**
	 * 订单非现金支付，请联系米星平台处理
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_PAYTYPE_NOTCASH() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_STORECANCELORDER_ORDER_PAYTYPE_NOTCASH);
	}

	/**
	 * 该订单还未付款，不允许取消
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_STATUS_NOTPAY = "order.order.storeCancelOrder.order.status.notpay";

	/**
	 * 该订单还未付款，不允许取消
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_STATUS_NOTPAY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_STORECANCELORDER_ORDER_STATUS_NOTPAY);
	}

	/**
	 * 订单状态已改变，请刷新试试
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_STATUS_CHANGED = "order.order.storeCancelOrder.order.status.changed";

	/**
	 * 订单状态已改变，请刷新试试
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_STATUS_CHANGED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_STORECANCELORDER_ORDER_STATUS_CHANGED);
	}

	/**
	 * 订单已送达，不允许取消
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_ALREADY_DELIVERED = "order.order.storeCancelOrder.order.already.delivered";

	/**
	 * 订单已送达，不允许取消
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_ALREADY_DELIVERED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_STORECANCELORDER_ORDER_ALREADY_DELIVERED);
	}

	/**
	 * 用户已签收，不允许取消
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_FINISHED = "order.order.storeCancelOrder.order.finished";

	/**
	 * 用户已签收，不允许取消
	 */
	public static final String ORDER_ORDER_STORECANCELORDER_ORDER_FINISHED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_STORECANCELORDER_ORDER_FINISHED);
	}

	/**
	 * 没有可配送的人员, 包括店主
	 */
	public static final String ORDER_ORDER_STAFF_EMPTY = "order.order.staff.empty";

	/**
	 * 没有可配送的人员, 包括店主
	 */
	public static final String ORDER_ORDER_STAFF_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDER_STAFF_EMPTY);
	}

	/**
	 * 商家已确认订单
	 */
	public static final String ORDER_ORDERTRACK_SWIFTSTATUSNAME_ACCEPTORDER = "order.orderTrack.swiftStatusName.acceptOrder";

	/**
	 * 商家已确认订单
	 */
	public static final String ORDER_ORDERTRACK_SWIFTSTATUSNAME_ACCEPTORDER() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_SWIFTSTATUSNAME_ACCEPTORDER);
	}

	/**
	 * 已收货
	 */
	public static final String ORDER_ORDERTRACK_SWIFTSTATUSNAME_RECEIVED = "order.orderTrack.swiftStatusName.received";

	/**
	 * 已收货
	 */
	public static final String ORDER_ORDERTRACK_SWIFTSTATUSNAME_RECEIVED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_SWIFTSTATUSNAME_RECEIVED);
	}

	/**
	 * 订单不是已付款
	 */
	public static final String ORDER_ORDERTRACK_PAYSUCCESSORDERTRACK_ORDER_STATUS_NOTPAY = "order.orderTrack.paySuccessOrderTrack.order.status.notpay";

	/**
	 * 订单不是已付款
	 */
	public static final String ORDER_ORDERTRACK_PAYSUCCESSORDERTRACK_ORDER_STATUS_NOTPAY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_PAYSUCCESSORDERTRACK_ORDER_STATUS_NOTPAY);
	}

	/**
	 * 订单状态错误
	 */
	public static final String ORDER_ORDERTRACK_ADDORDERTRACK_ORDER_STATUS_ERROR = "order.orderTrack.addOrderTrack.order.status.error";

	/**
	 * 订单状态错误
	 */
	public static final String ORDER_ORDERTRACK_ADDORDERTRACK_ORDER_STATUS_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_ADDORDERTRACK_ORDER_STATUS_ERROR);
	}

	/**
	 * 订单状态跟踪描述不能为空
	 */
	public static final String ORDER_ORDERTRACK_ADDORDERTRACK_TEXT_EMPTY = "order.orderTrack.addOrderTrack.text.empty";

	/**
	 * 订单状态跟踪描述不能为空
	 */
	public static final String ORDER_ORDERTRACK_ADDORDERTRACK_TEXT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_ADDORDERTRACK_TEXT_EMPTY);
	}

	/**
	 * 找不到订单的轨迹记录，请确认数据是否正确！
	 */
	public static final String ORDER_ORDERTRACK_COMPUTEDURATION_ORDERTRACK_NOTEXIST = "order.orderTrack.computeDuration.orderTrack.notexist";

	/**
	 * 找不到订单的轨迹记录，请确认数据是否正确！
	 */
	public static final String ORDER_ORDERTRACK_COMPUTEDURATION_ORDERTRACK_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_COMPUTEDURATION_ORDERTRACK_NOTEXIST);
	}

	/**
	 * {0}取消原因：{1}
	 */
	public static final String ORDER_ORDERTRACK_ADMINREFUNDORDER_CANCEL_REASON = "order.orderTrack.adminRefundOrder.cancel.reason";

	/**
	 * {0}取消原因：{1}
	 */
	public static final String ORDER_ORDERTRACK_ADMINREFUNDORDER_CANCEL_REASON(String text, String remark) {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_ADMINREFUNDORDER_CANCEL_REASON, text, remark);
	}

	/**
	 * 订单不是待接单
	 */
	public static final String ORDER_ORDERTRACK_CASHPAYSUCCESS_ORDER_PENDINGACCEPT_NOT = "order.orderTrack.cashPaySuccess.order.pendingAccept.not";

	/**
	 * 订单不是待接单
	 */
	public static final String ORDER_ORDERTRACK_CASHPAYSUCCESS_ORDER_PENDINGACCEPT_NOT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_CASHPAYSUCCESS_ORDER_PENDINGACCEPT_NOT);
	}

	/**
	 * {0},配送员是［{1}],联系方式是［{2}]
	 */
	public static final String ORDER_ORDERTRACK_LISTALLORDERTRACK_TRACK_CONTENT = "order.orderTrack.listAllOrderTrack.track.content";

	/**
	 * {0},配送员是［{1}],联系方式是［{2}]
	 */
	public static final String ORDER_ORDERTRACK_LISTALLORDERTRACK_TRACK_CONTENT(String text, String userName, String phone) {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERTRACK_LISTALLORDERTRACK_TRACK_CONTENT,text,  userName,  phone);
	}

	/**
	 * 您的客户端版本过低，请升级版本
	 */
	public static final String ORDER_SETTLE_SETTLEORDER_VERSION_TIP = "order.settle.settleOrder.version.tip";

	/**
	 * 您的客户端版本过低，请升级版本
	 */
	public static final String ORDER_SETTLE_SETTLEORDER_VERSION_TIP() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_SETTLEORDER_VERSION_TIP);
	}

	/**
	 * 装载美食信息失败，请重新下单试试
	 */
	public static final String ORDER_SETTLE_SETTLEORDER2_SETTLEORDERDETAIL_NOTEXIST = "order.settle.settleOrder2.SettleOrderDetail.notexist";

	/**
	 * 装载美食信息失败，请重新下单试试
	 */
	public static final String ORDER_SETTLE_SETTLEORDER2_SETTLEORDERDETAIL_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_SETTLEORDER2_SETTLEORDERDETAIL_NOTEXIST);
	}

	/**
	 * 金额不能超过100万
	 */
	public static final String ORDER_SETTLE_SETTLEORDER2_TOTALFEE_MAX = "order.settle.settleOrder2.totalFee.max";

	/**
	 * 金额不能超过100万
	 */
	public static final String ORDER_SETTLE_SETTLEORDER2_TOTALFEE_MAX() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_SETTLEORDER2_TOTALFEE_MAX);
	}
	
	/**
	 * 小店休息中，请留意餐厅的营业时间，明天再试试。
	 */
	public static final String ORDER_SETTLE_SETTLEORDER2_STORE_BOOKINGTIME_EMPTY = "order.settle.settleOrder2.store.bookingTime.empty";

	/**
	 * 小店休息中，请留意餐厅的营业时间，明天再试试。
	 */
	public static final String ORDER_SETTLE_SETTLEORDER2_STORE_BOOKINGTIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_SETTLEORDER2_STORE_BOOKINGTIME_EMPTY);
	}

	/**
	 * 不在配送范围
	 */
	public static final String ORDER_SETTLE_LOADRANGE2_RANGE_EMPTY = "order.settle.loadRange2.range.empty";

	/**
	 * 不在配送范围
	 */
	public static final String ORDER_SETTLE_LOADRANGE2_RANGE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_LOADRANGE2_RANGE_EMPTY);
	}

	/**
	 * 不在配送范围，换收货地址试试吧
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_RANGE_EMPTY = "order.settle.querySpecSurplus.range.empty";

	/**
	 * 不在配送范围，换收货地址试试吧
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_RANGE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_QUERYSPECSURPLUS_RANGE_EMPTY);
	}

	/**
	 * 当前时间商家不营业，刷新或换个配送时间试试
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_STORE_SERVICEDAY_ZERO = "order.settle.querySpecSurplus.store.serviceDay.zero";

	/**
	 * 当前时间商家不营业，刷新或换个配送时间试试
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_STORE_SERVICEDAY_ZERO() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_QUERYSPECSURPLUS_STORE_SERVICEDAY_ZERO);
	}

	/**
	 * 获取美食库存失败
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_FINDSPECSURPLUS_FAILURE = "order.settle.querySpecSurplus.findSpecSurplus.failure";

	/**
	 * 获取美食库存失败
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_FINDSPECSURPLUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_QUERYSPECSURPLUS_FINDSPECSURPLUS_FAILURE);
	}

	/**
	 * 已下架
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_DISHDETAIL_STATUS_OFFSHELF = "order.settle.querySpecSurplus.dishDetail.status.offShelf";

	/**
	 * 已下架
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_DISHDETAIL_STATUS_OFFSHELF() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_QUERYSPECSURPLUS_DISHDETAIL_STATUS_OFFSHELF);
	}

	/**
	 * 刚好缺货了，回餐厅再挑选吧
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_DISHDETAIL_SURPLUS_ZERO = "order.settle.querySpecSurplus.dishDetail.surplus.zero";

	/**
	 * 刚好缺货了，回餐厅再挑选吧
	 */
	public static final String ORDER_SETTLE_QUERYSPECSURPLUS_DISHDETAIL_SURPLUS_ZERO() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SETTLE_QUERYSPECSURPLUS_DISHDETAIL_SURPLUS_ZERO);
	}

	/**
	 * 餐厅参数错误
	 */
	public static final String ORDER_STOREORDER_VALIDATE_STOREID_ERROR = "order.storeOrder.validate.storeId.error";

	/**
	 * 餐厅参数错误
	 */
	public static final String ORDER_STOREORDER_VALIDATE_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_VALIDATE_STOREID_ERROR);
	}

	/**
	 * 系统错误
	 */
	public static final String ORDER_STOREORDER_SERVER_ERROR = "order.storeOrder.server.error";

	/**
	 * 系统错误
	 */
	public static final String ORDER_STOREORDER_SERVER_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_SERVER_ERROR);
	}

	/**
	 * 不属于门店下的员工,没有权限查看
	 */
	public static final String ORDER_STOREORDER_ISONLYDELIVER_ROLES_EMPTY = "order.storeOrder.isOnlyDeliver.roles.empty";

	/**
	 * 不属于门店下的员工,没有权限查看
	 */
	public static final String ORDER_STOREORDER_ISONLYDELIVER_ROLES_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_ISONLYDELIVER_ROLES_EMPTY);
	}

	/**
	 * 没有权限查看
	 */
	public static final String ORDER_STOREORDER_ISONLYDELIVER_ROLES_AUTHORITY_NONE = "order.storeOrder.isOnlyDeliver.roles.authority.none";

	/**
	 * 没有权限查看
	 */
	public static final String ORDER_STOREORDER_ISONLYDELIVER_ROLES_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_ISONLYDELIVER_ROLES_AUTHORITY_NONE);
	}

	/**
	 * 搜索类别不正确
	 */
	public static final String ORDER_STOREORDER_STOREORDERQUERY_CATEGORY_ERROR = "order.storeOrder.storeOrderQuery.category.error";

	/**
	 * 搜索类别不正确
	 */
	public static final String ORDER_STOREORDER_STOREORDERQUERY_CATEGORY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_STOREORDERQUERY_CATEGORY_ERROR);
	}

	/**
	 * 订单不存在：{0}
	 */
	public static final String ORDER_STOREORDER_ACCEPTORDER_ORDERNO_NOTEXIST = "order.storeOrder.acceptOrder.orderNo.notexist";

	/**
	 * 订单不存在：{0}
	 */
	public static final String ORDER_STOREORDER_ACCEPTORDER_ORDERNO_NOTEXIST(long orderNo) {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_ACCEPTORDER_ORDERNO_NOTEXIST,orderNo);
	}

	/**
	 * 订单处理中，不可重复操作
	 */
	public static final String ORDER_STOREORDER_ACCEPTORDER_ORDER_INPROCESS = "order.storeOrder.acceptOrder.order.inprocess";

	/**
	 * 订单处理中，不可重复操作
	 */
	public static final String ORDER_STOREORDER_ACCEPTORDER_ORDER_INPROCESS() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_ACCEPTORDER_ORDER_INPROCESS);
	}

	/**
	 * 接单失败：有可能其他人同时接单，请刷新数据
	 */
	public static final String ORDER_STOREORDER_ACCEPTORDER_ORDER_ACCEPTORDER_FAILURE = "order.storeOrder.acceptOrder.order.acceptOrder.failure";

	/**
	 * 接单失败：有可能其他人同时接单，请刷新数据
	 */
	public static final String ORDER_STOREORDER_ACCEPTORDER_ORDER_ACCEPTORDER_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_ACCEPTORDER_ORDER_ACCEPTORDER_FAILURE);
	}

	/**
	 * 只有已经接受的订单才可以完成制作
	 */
	public static final String ORDER_STOREORDER_FINISHCOOKING_ORDER_STATUS_ACCEPT_NOT = "order.storeOrder.finishCooking.order.status.accept.not";

	/**
	 * 只有已经接受的订单才可以完成制作
	 */
	public static final String ORDER_STOREORDER_FINISHCOOKING_ORDER_STATUS_ACCEPT_NOT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_FINISHCOOKING_ORDER_STATUS_ACCEPT_NOT);
	}

	/**
	 * 设置订单制作完成失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_STOREORDER_ORDER_SETSTATUS_FAILURE = "order.storeOrder.order.setStatus.failure";

	/**
	 * 设置订单制作完成失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_STOREORDER_ORDER_SETSTATUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_ORDER_SETSTATUS_FAILURE);
	}

	/**
	 * 配送员ID不合法
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_DELIVERYID_ILLEGAL = "order.storeOrder.deliveryOrder.deliveryId.illegal";

	/**
	 * 配送员ID不合法
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_DELIVERYID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_DELIVERYID_ILLEGAL);
	}

	/**
	 * 订单已开始配送, 不能重复处理同一个订单
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_ORDER_DELIVERY_ALREADY = "order.storeOrder.deliveryOrder.order.delivery.already";

	/**
	 * 订单已开始配送, 不能重复处理同一个订单
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_ORDER_DELIVERY_ALREADY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_ORDER_DELIVERY_ALREADY);
	}

	/**
	 * 只有已经完成接受或完成制作的订单才可以开始配送
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_ORDER_FINISHCOOKING_NOT = "order.storeOrder.deliveryOrder.order.finishCooking.not";

	/**
	 * 只有已经完成接受或完成制作的订单才可以开始配送
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_ORDER_FINISHCOOKING_NOT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_ORDER_FINISHCOOKING_NOT);
	}

	/**
	 * 订单所属餐厅不存在
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_STORE_NOTEXIST = "order.storeOrder.deliveryOrder.store.notexist";

	/**
	 * 订单所属餐厅不存在
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_STORE_NOTEXIST);
	}

	/**
	 * 配送员用户不存在
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_DELIVER_NOTEXIST = "order.storeOrder.deliveryOrder.deliver.notexist";

	/**
	 * 配送员用户不存在
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_DELIVER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_DELIVER_NOTEXIST);
	}

	/**
	 * 配送员权限验证失败
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_DELIVER_ROLES_EMPTY = "order.storeOrder.deliveryOrder.deliver.roles.empty";

	/**
	 * 配送员权限验证失败
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_DELIVER_ROLES_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_DELIVER_ROLES_EMPTY);
	}

	/**
	 * 设置订单开始配送失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_SETSTATUS_FAILURE = "order.storeOrder.deliveryOrder.setStatus.failure";

	/**
	 * 设置订单开始配送失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_STOREORDER_DELIVERYORDER_SETSTATUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_DELIVERYORDER_SETSTATUS_FAILURE);
	}

	/**
	 * 只有已经开始配送或用户确认收货或用户已评论的的订单才可以开始配送
	 */
	public static final String ORDER_STOREORDER_STORECONFIRMORDER_STATUS_NOTSATISFY = "order.storeOrder.storeConfirmOrder.status.notsatisfy";

	/**
	 * 只有已经开始配送或用户确认收货或用户已评论的的订单才可以开始配送
	 */
	public static final String ORDER_STOREORDER_STORECONFIRMORDER_STATUS_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_STORECONFIRMORDER_STATUS_NOTSATISFY);
	}

	/**
	 * 该订单已被确认送达
	 */
	public static final String ORDER_STOREORDER_STORECONFIRMORDER_STATUS_ARRIVED_ALREADY = "order.storeOrder.storeConfirmOrder.status.arrived.already";

	/**
	 * 该订单已被确认送达
	 */
	public static final String ORDER_STOREORDER_STORECONFIRMORDER_STATUS_ARRIVED_ALREADY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_STORECONFIRMORDER_STATUS_ARRIVED_ALREADY);
	}

	/**
	 * 修改商家确认时间失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_STOREORDER_UPDATESTORECONFIRMTIME_FAILURE = "order.storeOrder.updateStoreConfirmTime.failure";

	/**
	 * 修改商家确认时间失败：更新数据库失败，请刷新数据
	 */
	public static final String ORDER_STOREORDER_UPDATESTORECONFIRMTIME_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_UPDATESTORECONFIRMTIME_FAILURE);
	}

	/**
	 * 只有已接受的订单才可以打印
	 */
	public static final String ORDER_STOREORDER_RECORDPRINT_STATUS_NOTSATISFY = "order.storeOrder.recordPrint.status.notsatisfy";

	/**
	 * 只有已接受的订单才可以打印
	 */
	public static final String ORDER_STOREORDER_RECORDPRINT_STATUS_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_RECORDPRINT_STATUS_NOTSATISFY);
	}

	/**
	 * 没有该订单数据
	 */
	public static final String ORDER_STOREORDER_VALIDATEOPERATIONDATA_ORDER_NOTEXIST = "order.storeOrder.validateOperationData.order.notexist";

	/**
	 * 没有该订单数据
	 */
	public static final String ORDER_STOREORDER_VALIDATEOPERATIONDATA_ORDER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_VALIDATEOPERATIONDATA_ORDER_NOTEXIST);
	}

	/**
	 * 参数餐厅Id与订单中的餐厅Id不相符
	 */
	public static final String ORDER_STOREORDER_VALIDATEOPERATIONDATA_STOREID_ERROR = "order.storeOrder.validateOperationData.storeId.error";

	/**
	 * 参数餐厅Id与订单中的餐厅Id不相符
	 */
	public static final String ORDER_STOREORDER_VALIDATEOPERATIONDATA_STOREID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_VALIDATEOPERATIONDATA_STOREID_ERROR);
	}

	/**
	 * 该订单没有所属的餐厅
	 */
	public static final String ORDER_STOREORDER_VALIDATEOPERATIONDATA_STORE_NOTEXIST = "order.storeOrder.validateOperationData.store.notexist";

	/**
	 * 该订单没有所属的餐厅
	 */
	public static final String ORDER_STOREORDER_VALIDATEOPERATIONDATA_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_VALIDATEOPERATIONDATA_STORE_NOTEXIST);
	}

	/**
	 * 不支持退回订单，请下载最新版本：http://www.mazing.com/down.html
	 */
	public static final String ORDER_STOREORDER_STORERETURNORDER_UPGRADE_TIP = "order.storeOrder.storeReturnOrder.upgrade.tip";

	/**
	 * 不支持退回订单，请下载最新版本：http://www.mazing.com/down.html
	 */
	public static final String ORDER_STOREORDER_STORERETURNORDER_UPGRADE_TIP() {
		return MultiLanguageAdapter.fetchMessage(ORDER_STOREORDER_STORERETURNORDER_UPGRADE_TIP);
	}

	/**
	 * 参数订单Id与数据库不一致
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_COMMENTID_ERROR = "order.commentReply.addReply.commentId.error";

	/**
	 * 参数订单Id与数据库不一致
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_COMMENTID_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENTREPLY_ADDREPLY_COMMENTID_ERROR);
	}

	/**
	 * 评论不存在
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_COMMENTID_NOTEXIST = "order.commentReply.addReply.commentId.notexist";

	/**
	 * 评论不存在
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_COMMENTID_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENTREPLY_ADDREPLY_COMMENTID_NOTEXIST);
	}

	/**
	 * 评论已经被回复
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_COMMENT_HASREPLY = "order.commentReply.addReply.comment.hasreply";

	/**
	 * 评论已经被回复
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_COMMENT_HASREPLY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENTREPLY_ADDREPLY_COMMENT_HASREPLY);
	}

	/**
	 * 你没有权限查看该订单的评论
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_AUTHORITY_NONE = "order.commentReply.addReply.authority.none";

	/**
	 * 你没有权限查看该订单的评论
	 */
	public static final String ORDER_COMMENTREPLY_ADDREPLY_AUTHORITY_NONE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENTREPLY_ADDREPLY_AUTHORITY_NONE);
	}

	/**
	 * 获取评论人信息失败
	 */
	public static final String ORDER_COMMENT_QUERYCOMMENT_USER_NOTEXIST = "order.comment.queryComment.user.notexist";

	/**
	 * 获取评论人信息失败
	 */
	public static final String ORDER_COMMENT_QUERYCOMMENT_USER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_QUERYCOMMENT_USER_NOTEXIST);
	}

	/**
	 * 订单状态不正确，需为用户确认收货才能点评
	 */
	public static final String ORDER_COMMENT_ADDCOMMENT_STATUS_NOTSATISFY = "order.comment.addComment.status.notsatisfy";

	/**
	 * 订单状态不正确，需为用户确认收货才能点评
	 */
	public static final String ORDER_COMMENT_ADDCOMMENT_STATUS_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_ADDCOMMENT_STATUS_NOTSATISFY);
	}

	/**
	 * 更新订单评论状态失败
	 */
	public static final String ORDER_COMMENT_ADDCOMMENT_UPDATE_FAILURE = "order.comment.addComment.update.failure";

	/**
	 * 更新订单评论状态失败
	 */
	public static final String ORDER_COMMENT_ADDCOMMENT_UPDATE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_ADDCOMMENT_UPDATE_FAILURE);
	}

	/**
	 * 订单评论不存在
	 */
	public static final String ORDER_COMMENT_GETCOMMENT_ORDERCOMMENT_NOTEXIST = "order.comment.getComment.orderComment.notexist";

	/**
	 * 订单评论不存在
	 */
	public static final String ORDER_COMMENT_GETCOMMENT_ORDERCOMMENT_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_GETCOMMENT_ORDERCOMMENT_NOTEXIST);
	}
	
	/**
	 * 订单评论已屏蔽
	 */
	public static final String ORDER_COMMENT_STATUS_HAS_SHIELDING = "order.comment.status.hasShielding";
	
	/**
	 * 订单评论已屏蔽
	 */
	public static final String ORDER_COMMENT_STATUS_HAS_SHIELDING() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_STATUS_HAS_SHIELDING);
	}

	/**
	 * 找不到该订单的评论信息
	 */
	public static final String ORDER_COMMENT_GETORDERCOMMENT_ORDERCOMMENT_NOTEXIST = "order.comment.getOrderComment.orderComment.notexist";

	/**
	 * 找不到该订单的评论信息
	 */
	public static final String ORDER_COMMENT_GETORDERCOMMENT_ORDERCOMMENT_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_GETORDERCOMMENT_ORDERCOMMENT_NOTEXIST);
	}

	/**
	 * 找不到该订单的用户信息
	 */
	public static final String ORDER_COMMENT_GETORDERCOMMENT_USER_NOTEXIST = "order.comment.getOrderComment.user.notexist";

	/**
	 * 找不到该订单的用户信息
	 */
	public static final String ORDER_COMMENT_GETORDERCOMMENT_USER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_GETORDERCOMMENT_USER_NOTEXIST);
	}

	/**
	 * 无法获得该订单餐厅的员工信息
	 */
	public static final String ORDER_COMMENTREPLY_VALIDATE_ROLE_EMPTY = "order.commentReply.validate.role.empty";

	/**
	 * 无法获得该订单餐厅的员工信息
	 */
	public static final String ORDER_COMMENTREPLY_VALIDATE_ROLE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENTREPLY_VALIDATE_ROLE_EMPTY);
	}

	/**
	 * 商家已接单，如要取消，请联系商家
	 */
	public static final String ORDER_REFUND_USERAPPLYREFUND_STATUS_ISBACK = "order.refund.userApplyRefund.status.isback";

	/**
	 * 商家已接单，如要取消，请联系商家
	 */
	public static final String ORDER_REFUND_USERAPPLYREFUND_STATUS_ISBACK() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_USERAPPLYREFUND_STATUS_ISBACK);
	}

	/**
	 * 商家已接单，不允许取消订单
	 */
	public static final String ORDER_REFUND_USERAPPLYREFUND_STATUS_NOTSATISFY = "order.refund.userApplyRefund.status.notsatisfy";

	/**
	 * 商家已接单，不允许取消订单
	 */
	public static final String ORDER_REFUND_USERAPPLYREFUND_STATUS_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_USERAPPLYREFUND_STATUS_NOTSATISFY);
	}

	/**
	 * 请求pay域，申请退款失败
	 */
	public static final String ORDER_REFUND_USERAPPLYREFUND_UPDATE_FAILURE = "order.refund.userApplyRefund.update.failure";

	/**
	 * 请求pay域，申请退款失败
	 */
	public static final String ORDER_REFUND_USERAPPLYREFUND_UPDATE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_USERAPPLYREFUND_UPDATE_FAILURE);
	}

	/**
	 * 用户已签收，不允许退款
	 */
	public static final String ORDER_REFUND_STOREAPPLYREFUND_STATUS_FINISHED = "order.refund.storeApplyRefund.status.finished";

	/**
	 * 用户已签收，不允许退款
	 */
	public static final String ORDER_REFUND_STOREAPPLYREFUND_STATUS_FINISHED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_STOREAPPLYREFUND_STATUS_FINISHED);
	}

	/**
	 * 订单已送达，不允许退款
	 */
	public static final String ORDER_REFUND_STOREAPPLYREFUND_STATUS_STORECONFIRMED = "order.refund.storeApplyRefund.status.storeConfirmed";

	/**
	 * 订单已送达，不允许退款
	 */
	public static final String ORDER_REFUND_STOREAPPLYREFUND_STATUS_STORECONFIRMED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_STOREAPPLYREFUND_STATUS_STORECONFIRMED);
	}

	/**
	 * 申请退款失败
	 */
	public static final String ORDER_REFUND_STOREAPPLYREFUND_UPDATE_FAILURE = "order.refund.storeApplyRefund.update.failure";

	/**
	 * 申请退款失败
	 */
	public static final String ORDER_REFUND_STOREAPPLYREFUND_UPDATE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_STOREAPPLYREFUND_UPDATE_FAILURE);
	}

	/**
	 * 订单已完成，不允许退款
	 */
	public static final String ORDER_REFUND_TIMERAPPLYREFUND_STATUS_FINISHED = "order.refund.timerApplyRefund.status.finished";

	/**
	 * 订单已完成，不允许退款
	 */
	public static final String ORDER_REFUND_TIMERAPPLYREFUND_STATUS_FINISHED() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_TIMERAPPLYREFUND_STATUS_FINISHED);
	}

	/**
	 * 退款申请对象类型参数不合法
	 */
	public static final String ORDER_REFUND_TIMERAPPLYREFUND_REFUNDTYPE_EMPTY = "order.refund.timerApplyRefund.refundType.empty";

	/**
	 * 退款申请对象类型参数不合法
	 */
	public static final String ORDER_REFUND_TIMERAPPLYREFUND_REFUNDTYPE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_TIMERAPPLYREFUND_REFUNDTYPE_EMPTY);
	}

	/**
	 * 退款结果状态不合法
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_APPLYSTATUS_ILLEGAL = "order.refund.notifyRefund.applyStatus.illegal";

	/**
	 * 退款结果状态不合法
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_APPLYSTATUS_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_NOTIFYREFUND_APPLYSTATUS_ILLEGAL);
	}

	/**
	 * 退款申请不存在
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_ORDERREFUND_NOTEXIST = "order.refund.notifyRefund.orderRefund.notexist";

	/**
	 * 退款申请不存在
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_ORDERREFUND_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_NOTIFYREFUND_ORDERREFUND_NOTEXIST);
	}

	/**
	 * 多次退款回调
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_STATUS_INPROCESS = "order.refund.notifyRefund.status.inprocess";

	/**
	 * 多次退款回调
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_STATUS_INPROCESS() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_NOTIFYREFUND_STATUS_INPROCESS);
	}

	/**
	 * 这张订单状态不是申请退款
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_ORDER_STATUS_NOTSATISFY = "order.refund.notifyRefund.order.status.notsatisfy";

	/**
	 * 这张订单状态不是申请退款
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_ORDER_STATUS_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_NOTIFYREFUND_ORDER_STATUS_NOTSATISFY);
	}

	/**
	 * 更新数据库失败，未知错误
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_UPDATE_FAILURE = "order.refund.notifyRefund.update.failure";

	/**
	 * 更新数据库失败，未知错误
	 */
	public static final String ORDER_REFUND_NOTIFYREFUND_UPDATE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_NOTIFYREFUND_UPDATE_FAILURE);
	}

	/**
	 * 餐厅ID参数不合法
	 */
	public static final String ORDER_REFUND_QUERYSTOREREFUND_STOREID_ILLEGAL = "order.refund.queryStoreRefund.storeId.illegal";

	/**
	 * 餐厅ID参数不合法
	 */
	public static final String ORDER_REFUND_QUERYSTOREREFUND_STOREID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REFUND_QUERYSTOREREFUND_STOREID_ILLEGAL);
	}

	/**
	 * 用户不能被重复评分
	 */
	public static final String ORDER_USERRATING_RATEUSER_RATE_ALREADY = "order.userRating.rateUser.rate.already";

	/**
	 * 用户不能被重复评分
	 */
	public static final String ORDER_USERRATING_RATEUSER_RATE_ALREADY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USERRATING_RATEUSER_RATE_ALREADY);
	}

	/**
	 * 用户不存在
	 */
	public static final String ORDER_USERRATING_RATEUSER_PROFILE_NOTEXIST = "order.userRating.rateUser.profile.notexist";

	/**
	 * 用户不存在
	 */
	public static final String ORDER_USERRATING_RATEUSER_PROFILE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USERRATING_RATEUSER_PROFILE_NOTEXIST);
	}

	/**
	 * 用户所属餐厅与订单中的餐厅不相符
	 */
	public static final String ORDER_USERRATING_RATEUSER_STOREID_ILLEGAL = "order.userRating.rateUser.storeId.illegal";

	/**
	 * 用户所属餐厅与订单中的餐厅不相符
	 */
	public static final String ORDER_USERRATING_RATEUSER_STOREID_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_USERRATING_RATEUSER_STOREID_ILLEGAL);
	}

	/**
	 * 更新数据失败，未知原因
	 */
	public static final String ORDER_COUPONRECORD_RECORDUSESUCCESS_FAILURE = "order.couponRecord.recordUseSuccess.failure";

	/**
	 * 更新数据失败，未知原因
	 */
	public static final String ORDER_COUPONRECORD_RECORDUSESUCCESS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPONRECORD_RECORDUSESUCCESS_FAILURE);
	}

	/**
	 * 参数不能为空
	 */
	public static final String ORDER_COUPONRECORD_RECORDUSEFAIL_COUPONRECORD_EMPTY = "order.couponRecord.recordUseFail.couponRecord.empty";

	/**
	 * 参数不能为空
	 */
	public static final String ORDER_COUPONRECORD_RECORDUSEFAIL_COUPONRECORD_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPONRECORD_RECORDUSEFAIL_COUPONRECORD_EMPTY);
	}

	/**
	 * 恢复优惠券使用状态失败
	 */
	public static final String ORDER_COUPONRECORD_RECORDUSEFAIL_FAILURE = "order.couponRecord.recordUseFail.failure";

	/**
	 * 恢复优惠券使用状态失败
	 */
	public static final String ORDER_COUPONRECORD_RECORDUSEFAIL_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPONRECORD_RECORDUSEFAIL_FAILURE);
	}

	/**
	 * 没有此用户
	 */
	public static final String ORDER_COUPON_LISTUSERCOUPON_USER_NOTEXIST = "order.coupon.listUserCoupon.user.notexist";

	/**
	 * 没有此用户
	 */
	public static final String ORDER_COUPON_LISTUSERCOUPON_USER_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_LISTUSERCOUPON_USER_NOTEXIST);
	}

	/**
	 * 获取优惠券失败，请重新刷新
	 */
	public static final String ORDER_COUPON_LISTUSERCOUPON_COUPON_EMPTY = "order.coupon.listUserCoupon.coupon.empty";

	/**
	 * 获取优惠券失败，请重新刷新
	 */
	public static final String ORDER_COUPON_LISTUSERCOUPON_COUPON_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_LISTUSERCOUPON_COUPON_EMPTY);
	}

	/**
	 * 登录状态已失效，请重新登录
	 */
	public static final String ORDER_COUPON_EXCHANGECOUPON_USER_EMPTY = "order.coupon.exchangeCoupon.user.empty";

	/**
	 * 登录状态已失效，请重新登录
	 */
	public static final String ORDER_COUPON_EXCHANGECOUPON_USER_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_EXCHANGECOUPON_USER_EMPTY);
	}

	/**
	 * 网络请求失败，请重新兑换
	 */
	public static final String ORDER_COUPON_EXCHANGECOUPON_FAILURE = "order.coupon.exchangeCoupon.failure";

	/**
	 * 网络请求失败，请重新兑换
	 */
	public static final String ORDER_COUPON_EXCHANGECOUPON_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COUPON_EXCHANGECOUPON_FAILURE);
	}

	/**
	 * 查询时间不能为空
	 */
	public static final String ORDER_REPORT_QUERYSTATISTICSDAY_TIME_EMPTY = "order.report.queryStatisticsDay.time.empty";

	/**
	 * 查询时间不能为空
	 */
	public static final String ORDER_REPORT_QUERYSTATISTICSDAY_TIME_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_QUERYSTATISTICSDAY_TIME_EMPTY);
	}

	/**
	 * 查询天数不合法
	 */
	public static final String ORDER_REPORT_SALEREPORT_DAY_ILLEGAL = "order.report.saleReport.day.illegal";

	/**
	 * 查询天数不合法
	 */
	public static final String ORDER_REPORT_SALEREPORT_DAY_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_SALEREPORT_DAY_ILLEGAL);
	}

	/**
	 * 餐厅不存在
	 */
	public static final String ORDER_REPORT_SALEREPORT_STORE_NOTEXIST = "order.report.saleReport.store.notexist";

	/**
	 * 餐厅不存在
	 */
	public static final String ORDER_REPORT_SALEREPORT_STORE_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_SALEREPORT_STORE_NOTEXIST);
	}

	/**
	 * 线上支付费用不能低于1分钱
	 */
	public static final String ORDER_REPORT_ADDFINISHORDERS_ONLINEFEE_MIN = "order.report.addFinishOrders.onlineFee.min";

	/**
	 * 线上支付费用不能低于1分钱
	 */
	public static final String ORDER_REPORT_ADDFINISHORDERS_ONLINEFEE_MIN() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_ADDFINISHORDERS_ONLINEFEE_MIN);
	}

	/**
	 * 货到付款费用不能低于1分钱
	 */
	public static final String ORDER_REPORT_ADDFINISHORDERS_CASHFEE_MIN = "order.report.addFinishOrders.cashFee.min";

	/**
	 * 货到付款费用不能低于1分钱
	 */
	public static final String ORDER_REPORT_ADDFINISHORDERS_CASHFEE_MIN() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_ADDFINISHORDERS_CASHFEE_MIN);
	}

	/**
	 * 交易费用不能低于1分钱
	 */
	public static final String ORDER_REPORT_ADDCREATEORDERS_CREATEFEE_MIN = "order.report.addCreateOrders.createFee.min";

	/**
	 * 交易费用不能低于1分钱
	 */
	public static final String ORDER_REPORT_ADDCREATEORDERS_CREATEFEE_MIN() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_ADDCREATEORDERS_CREATEFEE_MIN);
	}

	/**
	 * 报表日期不合法
	 */
	public static final String ORDER_REPORT_TIME_ILLEGAL = "order.report.time.illegal";

	/**
	 * 报表日期不合法
	 */
	public static final String ORDER_REPORT_TIME_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_TIME_ILLEGAL);
	}

	/**
	 * 月份不合法
	 */
	public static final String ORDER_REPORT_MONTH_ILLEGAL = "order.report.month.illegal";

	/**
	 * 月份不合法
	 */
	public static final String ORDER_REPORT_MONTH_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_MONTH_ILLEGAL);
	}

	/**
	 * 参数时间异常
	 */
	public static final String ORDER_REPORT_DAY_ERROR = "order.report.day.error";

	/**
	 * 参数时间异常
	 */
	public static final String ORDER_REPORT_DAY_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_REPORT_DAY_ERROR);
	}

	/**
	 * 科目不能为空
	 */
	public static final String ORDER_CALLPAY_SUBJECT_EMPTY = "order.callPay.subject.empty";

	/**
	 * 科目不能为空
	 */
	public static final String ORDER_CALLPAY_SUBJECT_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CALLPAY_SUBJECT_EMPTY);
	}

	/**
	 * 相关ID不能为空
	 */
	public static final String ORDER_CALLPAY_REFERENCEID_EMPTY = "order.callPay.referenceId.empty";

	/**
	 * 相关ID不能为空
	 */
	public static final String ORDER_CALLPAY_REFERENCEID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CALLPAY_REFERENCEID_EMPTY);
	}

	/**
	 * 货币类型不能为空
	 */
	public static final String ORDER_CALLPAY_CURRENCYTYPE_EMPTY = "order.callPay.currencyType.empty";

	/**
	 * 货币类型不能为空
	 */
	public static final String ORDER_CALLPAY_CURRENCYTYPE_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CALLPAY_CURRENCYTYPE_EMPTY);
	}

	/**
	 * 订单ID不能为空
	 */
	public static final String ORDER_CALLPAY_ORDERID_EMPTY = "order.callPay.orderId.empty";

	/**
	 * 订单ID不能为空
	 */
	public static final String ORDER_CALLPAY_ORDERID_EMPTY() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CALLPAY_ORDERID_EMPTY);
	}

	/**
	 * 更新门店余额及流水明细失败
	 */
	public static final String ORDER_CALLPAY_UPDATE_FAILURE = "order.callPay.update.failure";

	/**
	 * 更新门店余额及流水明细失败
	 */
	public static final String ORDER_CALLPAY_UPDATE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CALLPAY_UPDATE_FAILURE);
	}

	/**
	 * 预计送达时间超时
	 */
	public static final String ORDER_UTILS_RECOUNTDELIVERY_DELIVERY_TIMEOUT = "order.utils.recountDelivery.delivery.timeout";

	/**
	 * 预计送达时间超时
	 */
	public static final String ORDER_UTILS_RECOUNTDELIVERY_DELIVERY_TIMEOUT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_UTILS_RECOUNTDELIVERY_DELIVERY_TIMEOUT);
	}

	/**
	 * 库存不足，剩余{0}
	 */
	public static final String ORDER_UTILS_LOADUNDERSTOCK_MSG = "order.utils.loadUnderstock.msg";

	/**
	 * 库存不足，剩余{0}
	 */
	public static final String ORDER_UTILS_LOADUNDERSTOCK_MSG(Integer value) {
		return MultiLanguageAdapter.fetchMessage(ORDER_UTILS_LOADUNDERSTOCK_MSG,value);
	}

	/**
	 * 口味选择内容文字过长
	 */
	public static final String ORDER_UTILS_VALIDATE_FEATURECONTENT_LENGTH_MAX = "order.utils.validate.featureContent.length.max";

	/**
	 * 口味选择内容文字过长
	 */
	public static final String ORDER_UTILS_VALIDATE_FEATURECONTENT_LENGTH_MAX() {
		return MultiLanguageAdapter.fetchMessage(ORDER_UTILS_VALIDATE_FEATURECONTENT_LENGTH_MAX);
	}

	/**
	 * IP错误
	 */
	public static final String ORDER_UTILS_VALIDATE_USERIP_ERROR = "order.utils.validate.userIP.error";

	/**
	 * IP错误
	 */
	public static final String ORDER_UTILS_VALIDATE_USERIP_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_UTILS_VALIDATE_USERIP_ERROR);
	}

	/**
	 * 配送时间不正确
	 */
	public static final String ORDER_UTILS_VALIDATE_DELIVERYTIME_ERROR = "order.utils.validate.deliveryTime.error";

	/**
	 * 配送时间不正确
	 */
	public static final String ORDER_UTILS_VALIDATE_DELIVERYTIME_ERROR() {
		return MultiLanguageAdapter.fetchMessage(ORDER_UTILS_VALIDATE_DELIVERYTIME_ERROR);
	}

	/**
	 * 满￥{0}免运费
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_FULLFREE = "order.rangeUtils.countRange.fullFree";

	/**
	 * 满￥{0}免运费
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_FULLFREE(String amount) {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_COUNTRANGE_FULLFREE, amount);
	}

	/**
	 * 的士费到付
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_TAXIFEE = "order.rangeUtils.countRange.taxiFee";

	/**
	 * 的士费到付
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_TAXIFEE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_COUNTRANGE_TAXIFEE);
	}

	/**
	 * 来回的士费到付
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_TAXIFEEDOUBLE = "order.rangeUtils.countRange.taxiFeeDouble";

	/**
	 * 来回的士费到付
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_TAXIFEEDOUBLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_COUNTRANGE_TAXIFEEDOUBLE);
	}

	/**
	 * 免运费
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_FREE = "order.rangeUtils.countRange.free";

	/**
	 * 免运费
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_FREE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_COUNTRANGE_FREE);
	}

	/**
	 * 未达起送价，继续挑选或换地址看看
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_FEE_MIN = "order.rangeUtils.countRange.fee.min";

	/**
	 * 未达起送价，继续挑选或换地址看看
	 */
	public static final String ORDER_RANGEUTILS_COUNTRANGE_FEE_MIN() {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_COUNTRANGE_FEE_MIN);
	}

	/**
	 * 尽快送达
	 */
	public static final String ORDER_RANGEUTILS_SOONDELIVERY_TEXT = "order.rangeUtils.soonDelivery.text";

	/**
	 * 尽快送达
	 */
	public static final String ORDER_RANGEUTILS_SOONDELIVERY_TEXT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_SOONDELIVERY_TEXT);
	}

	/**
	 * 预计{0}送达
	 */
	public static final String ORDER_RANGEUTILS_SEND_TITLE = "order.rangeUtils.send.title";

	/**
	 * 预计{0}送达
	 */
	public static final String ORDER_RANGEUTILS_SEND_TITLE(String name) {
		return MultiLanguageAdapter.fetchMessage(ORDER_RANGEUTILS_SEND_TITLE, name);
	}

	/**
	 * 订单号：{0}请尽快支付
	 */
	public static final String ORDER_CREATE_SUCCESS_NOTPAY_TRACK_CONTENT = "order.create.success.notpay.track.content";

	/**
	 * 订单号：{0}请尽快支付
	 */
	public static final String ORDER_CREATE_SUCCESS_NOTPAY_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CREATE_SUCCESS_NOTPAY_TRACK_CONTENT);
	}

	/**
	 * 订单号：{0}
	 */
	public static final String ORDER_CREATE_SUCCESS_HASPAY_TRACK_CONTENT = "order.create.success.haspay.track.content";

	/**
	 * 订单号：{0}
	 */
	public static final String ORDER_CREATE_SUCCESS_HASPAY_TRACK_CONTENT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_CREATE_SUCCESS_HASPAY_TRACK_CONTENT);
	}

	/**
	 * 购买数量不合法
	 */
	public static final String ORDER_ORDERDETAIL_BUYNUM_ILLEGAL = "order.orderDetail.queryDish.buyNum.illegal";

	/**
	 * 购买数量不合法
	 */
	public static final String ORDER_ORDERDETAIL_BUYNUM_ILLEGAL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_BUYNUM_ILLEGAL);
	}

	/**
	 * 「{0}」的规格「{1}」已被餐厅修改，请在购物车删除它重新下单
	 */
	public static final String ORDER_ORDERDETAIL_DISH_SPEC_CHANGED = "order.orderDetail.dish.spec.changed";

	/**
	 * 「{0}」的规格「{1}」已被餐厅修改，请在购物车删除它重新下单
	 */
	public static final String ORDER_ORDERDETAIL_DISH_SPEC_CHANGED(String dishName, String specName) {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_DISH_SPEC_CHANGED,dishName,specName);
	}

	
	
	
	/**
	 * 餐厅未营业
	 */
	public static final String ORDER_ORDERDETAIL_STORE_INBUSSINESS_NOT = "order.orderDetail.store.inBussiness.not";

	/**
	 * 餐厅未营业
	 */
	public static final String ORDER_ORDERDETAIL_STORE_INBUSSINESS_NOT() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_STORE_INBUSSINESS_NOT);
	}

	/**
	 * 餐厅即将开业，敬请期待！
	 */
	public static final String ORDER_ORDERDETAIL_STORE_COMMINGSOON = "order.orderDetail.store.commingSoon";

	/**
	 * 餐厅即将开业，敬请期待！
	 */
	public static final String ORDER_ORDERDETAIL_STORE_COMMINGSOON() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_STORE_COMMINGSOON);
	}
	
	/**
	 * 支付金额太小
	 */
	public static final String ORDER_ORDERDETAIL_PAY_FEE_TOO_SMALL = "order.create.payfee.too.small";
	
	/**
	 * 更新订单评论状态数据失败
	 */
	public static final String ORDER_COMMENT_UPDATE_STATUS_FAILURE = "order.comment.update.status.failure";

	/**
	 * 支付金额太小
	 */
	public static final String ORDER_ORDERDETAIL_PAY_FEE_TOO_SMALL() {
		return MultiLanguageAdapter.fetchMessage(ORDER_ORDERDETAIL_PAY_FEE_TOO_SMALL);
	}
	
	/**
	 * 餐厅不支持米星付
	 */
	public static final String MAZINGPAY_STOREORDER_NOT_SUPPOPRT = "order.mazingPay.storeOrder.not.support";

	/**
	 * 餐厅不支持米星付
	 */
	public static final String MAZINGPAY_STOREORDER_NOT_SUPPOPRT() {
		return MultiLanguageAdapter.fetchMessage(MAZINGPAY_STOREORDER_NOT_SUPPOPRT);
	}
	
	/**
	 * 更新订单评论状态数据失败
	 */
	public static final String ORDER_COMMENT_UPDATE_STATUS_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_COMMENT_UPDATE_STATUS_FAILURE);
	}
	
	/**
	 * 优惠券标题字符串（适用于现金券）满X可减Y。暂时适用于米星付
	 */
	public static final String MAZING_PAY_COUPON_TITLE_BY_CASH = "order.mazingPay.coupon.title.cash";

	/**
	 * 优惠券标题字符串（适用于现金券）满X可减Y。暂时适用于米星付
	 */
	public static final String MAZING_PAY_COUPON_TITLE_BY_CASH(String enoughMoney, String faceValue) {
		return MultiLanguageAdapter.fetchMessage(MAZING_PAY_COUPON_TITLE_BY_CASH, enoughMoney, faceValue);
	}
	
	/**
	 * 优惠券标题字符串（适用于折扣券）满X可减Y。暂时适用于米星付
	 */
	public static final String MAZING_PAY_COUPON_TITLE_BY_DISCOUNT = "order.mazingPay.coupon.title.discount";

	/**
	 * 优惠券标题字符串（适用于折扣券）满X可减Y。暂时适用于米星付
	 * @param enoughMoney - “满X元”文本中的x
	 * @param faceValue - “享X折”文本中的x
	 * @param discountValue - 折扣率的百分比，用于显示英文版。如1折，则discountValue=90
	 */
	public static final String MAZING_PAY_COUPON_TITLE_BY_DISCOUNT(String enoughMoney, String faceValue, int discountValue) {
		return MultiLanguageAdapter.fetchMessage(MAZING_PAY_COUPON_TITLE_BY_DISCOUNT, enoughMoney, faceValue, discountValue);
	}
	
	/**
	 * 优惠券标题字符串（适用于满免券）满X可减Y。暂时适用于米星付
	 */
	public static final String MAZING_PAY_COUPON_TITLE_BY_FULLCUT = "order.mazingPay.coupon.title.fullcut";

	/**
	 * 优惠券标题字符串（适用于满免券）满X可减Y。暂时适用于米星付
	 */
	public static final String MAZING_PAY_COUPON_TITLE_BY_FULLCUT(String enoughMoney, String faceValue) {
		return MultiLanguageAdapter.fetchMessage(MAZING_PAY_COUPON_TITLE_BY_FULLCUT, enoughMoney, faceValue);
	}
	
	/**
	 * 优惠券标题字符串（本人使用即可减免）。暂时适用于预下单
	 */
	public static final String SETTLE_COUPON_TITLE_SELF_USE = "order.settle.coupon.title.self.use";

	/**
	 * 优惠券标题字符串（本人使用即可减免）。暂时适用于预下单
	 */
	public static final String SETTLE_COUPON_TITLE_SELF_USE() {
		return MultiLanguageAdapter.fetchMessage(SETTLE_COUPON_TITLE_SELF_USE);
	}
	
	/**
	 * 顺丰专送
	 */
	public static final String SF_EXPRESS_NAME="sf.express.name";
	
	/**
	 * 顺丰专送
	 */
	public static final String SF_EXPRESS_NAME() {
		return MultiLanguageAdapter.fetchMessage(SF_EXPRESS_NAME);
	}
	
	/**
	 * 配送费
	 */
	public static final String ORDER_SEND_INFO_TITLE="order.send.info.title";
	
	/**
	 * 配送费
	 */
	public static final String ORDER_SEND_INFO_TITLE() {
		return MultiLanguageAdapter.fetchMessage(ORDER_SEND_INFO_TITLE);
	}
	
	/**
	 * 此优惠券暂无法使用，请联系商家
	 */
	public static final String ORDER_PAY_FEE_TO_BIG="order.pay.fee.to.big";
	
	/**
	 * 此优惠券暂无法使用，请联系商家
	 */
	public static final String ORDER_PAY_FEE_TO_BIG() {
		return MultiLanguageAdapter.fetchMessage(ORDER_PAY_FEE_TO_BIG);
	}
	
	/**
	 * 一个账号只能购买一张噢
	 */
	public static final String ORDER_PAY_REPEAT_PURCHASE="order.pay.repeat.purchase";
	
	/**
	 * 一个账号只能购买一张噢
	 */
	public static final String ORDER_PAY_REPEAT_PURCHASE(){
		return MultiLanguageAdapter.fetchMessage(ORDER_PAY_REPEAT_PURCHASE);
	}
	
	/**
	 * 支付系统繁忙，请稍候再试
	 */
	public static final String ORDER_NOT_EXIST_EFFECTIVE_PAY_MODE = "order.not.exist.effective.payMode";
	
	/**
	 * 支付系统繁忙，请稍候再试
	 */
	public static final String ORDER_NOT_EXIST_EFFECTIVE_PAY_MODE(){
		return MultiLanguageAdapter.fetchMessage(ORDER_NOT_EXIST_EFFECTIVE_PAY_MODE);
	}
	
	
}
