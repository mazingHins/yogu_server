package com.yogu.language;

/**
 * 优惠券域的 提示信息
 * @author sky 2016-04-21
 *
 */
public class ActivityMessages {
	/**
	 * 您没有登录,请先登录
	 */
	public static final String COUPONSERVICE_EXCHANGECOUPON_USER_NOTLOGIN = "couponService.exchangeCoupon.user.notLogin";

	/**
	 * 您没有登录,请先登录
	 */
	public static String COUPONSERVICE_EXCHANGECOUPON_USER_NOTLOGIN() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_EXCHANGECOUPON_USER_NOTLOGIN);
	}

	/**
	 * 兑换成功
	 */
	public static final String COUPONSERVICE_EXCHANGECOUPON_SUCCESS = "couponService.exchangeCoupon.success";

	/**
	 * 兑换成功
	 */
	public static String COUPONSERVICE_EXCHANGECOUPON_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_EXCHANGECOUPON_SUCCESS);
	}

	/**
	 * 兑换失败,请重试
	 */
	public static final String COUPONSERVICE_EXCHANGECOUPON_FAILURE = "couponService.exchangeCoupon.failure";

	/**
	 * 兑换失败,请重试
	 */
	public static String COUPONSERVICE_EXCHANGECOUPON_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_EXCHANGECOUPON_FAILURE);
	}

	/**
	 * 优惠码无效，兑换失败
	 */
	public static final String COUPONSERVICE_ISCOUPONCANBEEXCHANGED_CODE_INVALID = "couponService.isCouponCanBeExchanged.code.invalid";

	/**
	 * 优惠码无效，兑换失败
	 */
	public static String COUPONSERVICE_ISCOUPONCANBEEXCHANGED_CODE_INVALID() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISCOUPONCANBEEXCHANGED_CODE_INVALID);
	}

	/**
	 * 兑换的优惠券超过领取次数限制，兑换失败
	 */
	public static final String COUPONSERVICE_ISCOUPONCANBEEXCHANGED_OVERLIMIT = "couponService.isCouponCanBeExchanged.overlimit";

	/**
	 * 兑换的优惠券超过领取次数限制，兑换失败
	 */
	public static String COUPONSERVICE_ISCOUPONCANBEEXCHANGED_OVERLIMIT() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISCOUPONCANBEEXCHANGED_OVERLIMIT);
	}

	/**
	 * 优惠码已过期，兑换失败
	 */
	public static final String COUPONSERVICE_ISCOUPONCANBEEXCHANGED_COUPON_EXPIRED = "couponService.isCouponCanBeExchanged.coupon.expired";

	/**
	 * 优惠码已过期，兑换失败
	 */
	public static String COUPONSERVICE_ISCOUPONCANBEEXCHANGED_COUPON_EXPIRED() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISCOUPONCANBEEXCHANGED_COUPON_EXPIRED);
	}

	/**
	 * 不存在该优惠券, 不可使用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTEXIST = "couponService.isOrderAvailable.coupon.notExist";

	/**
	 * 不存在该优惠券, 不可使用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTEXIST);
	}

	/**
	 * 该优惠券并不属于你, 不可使用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTOWNER = "couponService.isOrderAvailable.coupon.notOwner";

	/**
	 * 该优惠券并不属于你, 不可使用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTOWNER() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTOWNER);
	}

	/**
	 * 优惠券不在有效使用时间范围, 不可用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_COUPON_TIME_NOTEFFECTIVE = "couponService.isOrderAvailable.coupon.time.notEffective";

	/**
	 * 优惠券不在有效使用时间范围, 不可用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_COUPON_TIME_NOTEFFECTIVE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_COUPON_TIME_NOTEFFECTIVE);
	}

	/**
	 * 该优惠券为非可用状态, 不可用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTAVAILABLE = "couponService.isOrderAvailable.coupon.notavailable";

	/**
	 * 该优惠券为非可用状态, 不可用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTAVAILABLE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_COUPON_NOTAVAILABLE);
	}

	/**
	 * 该优惠券正被其它订单占用并锁定, 不可用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_COUPON_LOCKED = "couponService.isOrderAvailable.coupon.locked";

	/**
	 * 该优惠券正被其它订单占用并锁定, 不可用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_COUPON_LOCKED() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_COUPON_LOCKED);
	}

	/**
	 * 优惠券已失效, 不可使用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_COUPON_UNABLE = "couponService.isOrderAvailable.coupon.unable";

	/**
	 * 优惠券已失效, 不可使用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_COUPON_UNABLE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_COUPON_UNABLE);
	}

	/**
	 * 您的订单还未满足使用优惠券的最低消费金额条件,请买满再用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_ORDER_FEE_NOTSATISFY = "couponService.isOrderAvailable.order.fee.notSatisfy";

	/**
	 * 您的订单还未满足使用优惠券的最低消费金额条件,请买满再用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_ORDER_FEE_NOTSATISFY() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_ORDER_FEE_NOTSATISFY);
	}

	/**
	 * 收货人手机号码需要与使用者手机号码一致才可使用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_PHONE_NOTTHESAME = "couponService.isOrderAvailable.phone.notthesame";

	/**
	 * 收货人手机号码需要与使用者手机号码一致才可使用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_PHONE_NOTTHESAME() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_PHONE_NOTTHESAME);
	}

	/**
	 * 该类型优惠券每天只能使用{0}张, 您已经使用超过限制, 明天再用吧
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_DAYUSECOUNT_LIMIT = "couponService.isOrderAvailable.dayUseCount.limit";

	/**
	 * 该类型优惠券每天只能使用{0}张, 您已经使用超过限制, 明天再用吧
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_DAYUSECOUNT_LIMIT(short useTotal) {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_DAYUSECOUNT_LIMIT, useTotal);
	}

	/**
	 * 该优惠券有使用范围限制, 请查看优惠券具体使用范围后再用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_USERANGE_LIMIT = "couponService.isOrderAvailable.useRange.limit";

	/**
	 * 该优惠券有使用范围限制, 请查看优惠券具体使用范围后再用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_USERANGE_LIMIT() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_USERANGE_LIMIT);
	}

	/**
	 * 订单可用
	 */
	public static final String COUPONSERVICE_ISORDERAVAILABLE_ORDER_AVAILABLE = "couponService.isOrderAvailable.order.available";

	/**
	 * 订单可用
	 */
	public static String COUPONSERVICE_ISORDERAVAILABLE_ORDER_AVAILABLE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_ISORDERAVAILABLE_ORDER_AVAILABLE);
	}

	/**
	 * 该优惠券不存在
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_COUPON_NOTEXIST = "couponService.unlockCoupon.coupon.notExist";

	/**
	 * 该优惠券不存在
	 */
	public static String COUPONSERVICE_UNLOCKCOUPON_COUPON_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_UNLOCKCOUPON_COUPON_NOTEXIST);
	}

	/**
	 * 该优惠券并不属于该用户
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_USER_NOTOWNER = "couponService.unlockCoupon.user.notOwner";

	/**
	 * 该优惠券并不属于该用户
	 */
	public static String COUPONSERVICE_UNLOCKCOUPON_USER_NOTOWNER() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_UNLOCKCOUPON_USER_NOTOWNER);
	}

	/**
	 * 使用该优惠券的订单不正确
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_ORDER_NOTTHESAME = "couponService.unlockCoupon.order.notTheSame";

	/**
	 * 使用该优惠券的订单不正确
	 */
	public static String COUPONSERVICE_UNLOCKCOUPON_ORDER_NOTTHESAME() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_UNLOCKCOUPON_ORDER_NOTTHESAME);
	}

	/**
	 * 未知错误,请稍后再试
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_UPDATE_FAILURE = "couponService.unlockCoupon.update.failure";

	/**
	 * 未知错误,请稍后再试
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_UPDATE_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_UNLOCKCOUPON_UPDATE_FAILURE);
	}

	/**
	 * 解锁成功
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_SUCCESS = "couponService.unlockCoupon.success";

	/**
	 * 解锁成功
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_UNLOCKCOUPON_SUCCESS);
	}

	/**
	 * 该优惠券并没有被锁定
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_STATUS_NOTLOCK = "couponService.unlockCoupon.status.notLock";

	/**
	 * 该优惠券并没有被锁定
	 */
	public static final String COUPONSERVICE_UNLOCKCOUPON_STATUS_NOTLOCK() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_UNLOCKCOUPON_STATUS_NOTLOCK);
	}

	/**
	 * 使用优惠券失败, 请稍后再试
	 */
	public static final String COUPONSERVICE_USECOUPON_FAILURE = "couponService.useCoupon.failure";

	/**
	 * 使用优惠券失败, 请稍后再试
	 */
	public static final String COUPONSERVICE_USECOUPON_FAILURE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_USECOUPON_FAILURE);
	}

	/**
	 * 操作过快, 请稍后再试
	 */
	public static final String COUPONSERVICE_OBTAIN_OPERATION_OVERLIMIT = "couponService.obtain.operation.overLimit";

	/**
	 * 操作过快, 请稍后再试
	 */
	public static final String COUPONSERVICE_OBTAIN_OPERATION_OVERLIMIT() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_OBTAIN_OPERATION_OVERLIMIT);
	}

	/**
	 * 活动已结束，感谢支持！
	 */
	public static final String COUPONSERVICE_OBTAIN_ACTIVITY_NOTEXIST = "couponService.obtain.activity.notExist";

	/**
	 * 活动已结束，感谢支持！
	 */
	public static final String COUPONSERVICE_OBTAIN_ACTIVITY_NOTEXIST() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_OBTAIN_ACTIVITY_NOTEXIST);
	}

	/**
	 * 领取次数已满，打开米星即可用券哦
	 */
	public static final String COUPONSERVICE_OBTAIN_COUNT_OVERLIMIT = "couponService.obtain.count.overLimit";

	/**
	 * 领取次数已满，打开米星即可用券哦
	 */
	public static String COUPONSERVICE_OBTAIN_COUNT_OVERLIMIT() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_OBTAIN_COUNT_OVERLIMIT);
	}

	/**
	 * 数量有限，已经被领光啦，感谢支持！
	 */
	public static final String COUPONSERVICE_OBTAIN_FULL = "couponService.obtain.full";

	/**
	 * 数量有限，已经被领光啦，感谢支持！
	 */
	public static String COUPONSERVICE_OBTAIN_FULL() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_OBTAIN_FULL);
	}

	/**
	 * 领取成功
	 */
	public static final String COUPONSERVICE_OBTAIN_SUCCESS = "couponService.obtain.success";

	/**
	 * 领取成功
	 */
	public static String COUPONSERVICE_OBTAIN_SUCCESS() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_OBTAIN_SUCCESS);
	}

	/**
	 * 这张优惠券不能转赠
	 */
	public static final String COUPONSERVICE_TRANSFER_USER_NOTOWNER = "couponService.transfer.user.notOwner";

	/**
	 * 这张优惠券不能转赠
	 */
	public static String COUPONSERVICE_TRANSFER_USER_NOTOWNER() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_TRANSFER_USER_NOTOWNER);
	}

	/**
	 * 送出去的优惠券只能别人领取哦
	 */
	public static final String COUPONSERVICE_TRANSFER_SELF_OBTAIN_CANNOT = "couponService.transfer.self.obtain.cannot";

	/**
	 * 送出去的优惠券只能别人领取哦
	 */
	public static String COUPONSERVICE_TRANSFER_SELF_OBTAIN_CANNOT() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_TRANSFER_SELF_OBTAIN_CANNOT);
	}

	/**
	 * 这张优惠券不支持被转赠
	 */
	public static final String COUPONSERVICE_TRANSFER_COUPON_TRANSFER_NOTSUPPORT = "couponService.transfer.coupon.transfer.notSupport";

	/**
	 * 这张优惠券不支持被转赠
	 */
	public static String COUPONSERVICE_TRANSFER_COUPON_TRANSFER_NOTSUPPORT() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_TRANSFER_COUPON_TRANSFER_NOTSUPPORT);
	}

	/**
	 * 这张优惠券并没有被转赠
	 */
	public static final String COUPONSERVICE_TRANSFER_COUPON_STATUS_NOTTRANSFER = "couponService.transfer.coupon.status.notTransfer";

	/**
	 * 这张优惠券并没有被转赠
	 */
	public static String COUPONSERVICE_TRANSFER_COUPON_STATUS_NOTTRANSFER() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_TRANSFER_COUPON_STATUS_NOTTRANSFER);
	}

	/**
	 * 下手晚了，优惠券被领走了……
	 */
	public static final String COUPONSERVICE_TRANSFER_COUPON_STATUS_TRANSFERED = "couponService.transfer.coupon.status.transfered";

	/**
	 * 下手晚了，优惠券被领走了……
	 */
	public static String COUPONSERVICE_TRANSFER_COUPON_STATUS_TRANSFERED() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_TRANSFER_COUPON_STATUS_TRANSFERED);
	}

	/**
	 * 优惠券已被使用，不能赠送了
	 */
	public static final String COUPONSERVICE_TRANSFER_COUPON_STATUS_USED = "couponService.transfer.coupon.status.used";

	/**
	 * 优惠券已被使用，不能赠送了
	 */
	public static String COUPONSERVICE_TRANSFER_COUPON_STATUS_USED() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_TRANSFER_COUPON_STATUS_USED);
	}

	/**
	 * 您可在“我的-我的优惠券”查看领到的优惠券，直接进入适用餐厅进行选购。也可看看附近餐厅，开启你的美食之旅哦~
	 */
	public static final String COUPONSERVICE_NEWREGASSIGN_ASSIGN_MESSAGE = "couponService.newRegAssign.assign.message";

	/**
	 * 您可在“我的-我的优惠券”查看领到的优惠券，直接进入适用餐厅进行选购。也可看看附近餐厅，开启你的美食之旅哦~
	 */
	public static String COUPONSERVICE_NEWREGASSIGN_ASSIGN_MESSAGE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_NEWREGASSIGN_ASSIGN_MESSAGE);
	}

	/**
	 * 欢迎登陆米星
	 */
	public static final String COUPONSERVICE_NEWREGASSIGN_ASSIGN_TITLE = "couponService.newRegAssign.assign.title";

	/**
	 * 欢迎登陆米星
	 */
	public static String COUPONSERVICE_NEWREGASSIGN_ASSIGN_TITLE() {
		return MultiLanguageAdapter.fetchMessage(COUPONSERVICE_NEWREGASSIGN_ASSIGN_TITLE);
	}

	/**
	 * 领到{0}张优惠券,合计{1}! <br/>
	 * 下次手快点哦 !
	 */
	public static final String COUPONBAGSERVICE_OBTAIN_TOTAL_NOTENOUGH = "couponBagService.obtain.total.notEnough";

	/**
	 * 领到{0}张优惠券,合计{1}! <br/>
	 * 下次手快点哦 !
	 */
	public static String COUPONBAGSERVICE_OBTAIN_TOTAL_NOTENOUGH(int successTotal, String faceValue) {
		return MultiLanguageAdapter.fetchMessage(COUPONBAGSERVICE_OBTAIN_TOTAL_NOTENOUGH, successTotal, faceValue);
	}

}
