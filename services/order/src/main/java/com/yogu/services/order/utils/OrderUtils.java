package com.yogu.services.order.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.alarm.AlarmSender;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisLock;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.base.BaseParams;
import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.enums.order.OrderConstants;
import com.yogu.core.enums.order.OrderStatus;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.enums.pay.PayType;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.utils.OrderChannelUtils;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.remote.user.dto.UserAddress;
import com.yogu.remote.user.dto.UserAndAddress;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.services.order.OrderCacheKey;
import com.yogu.services.order.base.dao.OrderDao;
import com.yogu.services.order.base.dto.Order;
import com.yogu.services.order.base.dto.vo.OrderErrorVO;
import com.yogu.services.order.base.dto.vo.SendInfoVO;
import com.yogu.services.order.base.service.param.PurchaseDetail;
import com.yogu.services.store.StoreConstants;
import com.yogu.services.store.base.dto.Store;
import com.yogu.services.store.base.dto.StoreServiceRangeVO;
import com.yogu.services.store.dish.dto.Dish;
import com.yogu.services.store.order.StoreCreateOrderVO;

/**
 * 订单信息相关工具类
 * 
 * @author Hins
 * @date 2015年11月7日 下午3:10:12
 */
public class OrderUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderUtils.class);
	
	private static final String OPEN_ALIPAY_PAY = "1";	// 平台启用支付宝支付的开关值
	
	private static final String OPEN_WECHAT_PAY = "1";	// 平台启用微信支付的开关值

	/**
	 * 装载订单其他数据<br>
	 * 包括：订单状态，创建时间，收货地址信息，订单金额，配送信息，门店信息
	 * 
	 * @author Hins
	 * @date 2015年8月22日 下午3:18:08
	 * 
	 * @param order - 订单对象
	 * @param ua - 用户、收货地址
	 * @param storeOrder - store域获取的下单信息
	 */
	public static void loadOrder(Order order, UserAndAddress ua, StoreCreateOrderVO storeOrder) {
		
		StoreServiceRangeVO range = storeOrder.getRange();
		Date deliveryTime = storeOrder.getDeliveryTime();
		UserProfile user = ua.getUserProfile();
		UserAddress address = ua.getUserAddress();
		Store store = storeOrder.getStore();
		
		order.setServiceRangeName(range.getName());
		order.setServiceRangeId(range.getRangeId());
		order.setServiceTime(range.getMinute());
		order.setDeliveryTime(deliveryTime);
		order.setServiceDay(storeOrder.getServiceDay());
		
		order.setUid(user.getUid());
		// felix 记录用户IM ID信息
		order.setImId(user.getImId());
		order.setGoodsFee(storeOrder.getGoodsFee());
		order.setSerialNumber("0");
		// 过滤掉 \n, \t, 2015/12/7 by ten
		order.setRemark(order.getRemark().replaceAll("\n", "").replaceAll("\t", ""));
		order.setRejectRemark("");
		// 增加订单的下单渠道
		order.setOrderChannel(OrderChannelUtils.getOrderChannel(SecurityContext.getBaseParams()));
		
		Date createTime = new Date();
		long orderNo = OrderNO.next(order.getUid(), order.getOrderId());
		order.setStatus(OrderStatus.NON_PAYMENT.getValue());
		order.setCreateTime(createTime);
		order.setUpdateTime(createTime);
		order.setOrderNo(orderNo);
		if (StringUtils.isBlank(address.getFullAddress())) {
			order.setFullAddress("");
		} else {
			order.setFullAddress(address.getFullAddress());
		}
		order.setContacts(address.getContacts());
		order.setPhone(address.getPhone());
		order.setStoreId(store.getStoreId());
		order.setDiscountFee(0);
		SendInfoVO sendInfo = RangeUtils.countRange(order.getGoodsFee(), range);
		order.setTotalFee(order.getGoodsFee() + sendInfo.getFee());
		order.setActualFee(order.getTotalFee());
		order.setDeliveryFee(sendInfo.getFee());
		order.setDeliveryRemark(sendInfo.getContent());
		order.setResult(OrderConstants.RESULT_CODE_SUCCESS);
		order.setSupportCash(store.getSupportCash());
		order.setPayType(PayType.ONLINE.getValue());
		// 设置用户下单的桌号备注 2016/8/25 add by hins
		order.setUserTableNoRemark("");
		order.setStoreRemark("");
		order.setIsThirdExpress(range.getIsSf() == BooleanConstants.TRUE ? BooleanConstants.TRUE : BooleanConstants.FALSE); 
		order.setIsStoreExpress(range.getIsSf() == BooleanConstants.TRUE ? BooleanConstants.FALSE : BooleanConstants.TRUE);
	}
	
	/**
	 * 判断并返回创建订单时候，错误的美食信息<br>
	 * 暂时只判断美食是否下架
	 * 
	 * @param list - 购买信息
	 * @param dishMap - 美食信息，key：美食key，value：美食对象
	 * @author hins
	 * @date 2016年10月2日 下午1:34:49
	 * @return 错误的信息list，若无，返回empty list
	 */
	public static List<OrderErrorVO> initErrorOrder(List<PurchaseDetail> list, Map<Long, Dish> dishMap){
		
		List<OrderErrorVO> result = new ArrayList<>(0);
		for (PurchaseDetail buy : list) {
			Dish dish = dishMap.get(buy.getDishKey());
			if (dish.getStatus() == CommonConstants.DISH_STATUS_OFF_SHELF) {
				OrderErrorVO vo = new OrderErrorVO();
				vo.setDishId(dish.getDishKey());
				vo.setMessage(OrderMessages.ORDER_ORDERDETAIL_DISH_OFFSHELF());
				result.add(vo);
			}
		}
		
		return result;
		
	}
	
	/**
	 * 重新计算预计送达时间。<br>
	 * 前置条件：用户下单后，经过一段时间（10分钟），再选择货到付款/支付成功回调，则要更新预计送达时间。<br>
	 * 计算方式：计算当前时间加上配送区域预计时间=time，判断time是否大于原来预计送达时间<br>
	 * 若大于，则将delivery=time
	 * 
	 * @author Hins
	 * @date 2015年10月26日 下午5:34:12
	 * 
	 * @param delivery - 原预计送达时间
	 * @return 计算后的预计送达时间
	 */
	public static Date reloadDelivery(Date delivery, int minute) {
		Calendar now = DateUtils.getCalendar();
		now.add(Calendar.MINUTE, minute);
		if (now.getTime().after(delivery)) {
			delivery = now.getTime();
		}
		return delivery;
	}
	
	/**
	 * 根据送达时间和订单序列号, 获得订单排序号 预计送达时间 YYMMDDHHMM * 100000 + 序列号
	 * 
	 * @param deliveryTime 预计送达时间
	 * @param serialNum 订单序列号
	 * @return
	 */
	public static long getSequenceNum(Date deliveryTime, String serialNum) {
		long sequence = 0;

		sequence = Long.parseLong(DateUtils.formatDate(deliveryTime, DateUtils.YYMMDDHHMM)) * 100000 + Long.parseLong(serialNum);

		return sequence;
	}
	
	/**
	 * 装载库存不足信息
	 * 
	 * @param map - 库存不足菜品剩余数，key=specKey，value=剩余数量
	 * @param detailList 购买美食的详细列表，主要用来读取 dishKey
	 * @modified by ten 2016/2/25
	 */

	public static List<OrderErrorVO> loadUnderstock(Map<Long, Integer> map,
													List<PurchaseDetail> detailList) {
		Set<?> set = map.entrySet();
		Iterator<?> i = set.iterator();
		List<OrderErrorVO> list = new ArrayList<OrderErrorVO>(map.size());
		Map<Long, Long> specKeyToDishKeyMap = new HashMap<>();
		for (PurchaseDetail d : detailList) {
			specKeyToDishKeyMap.put(d.getSpecKey(), d.getDishKey());
		}
		while (i.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<Long, Integer> entry = (Entry<Long, Integer>) i.next();
			OrderErrorVO vo = new OrderErrorVO();
			vo.setSpecKey(entry.getKey());
			vo.setMessage(OrderMessages.ORDER_UTILS_LOADUNDERSTOCK_MSG(entry.getValue()));

			// 2016/2/25 by ten
			Long dishKey = specKeyToDishKeyMap.get(entry.getKey());
			// 理论上不可能为null
			if (dishKey != null) {
				vo.setDishId(dishKey); // 实际是dishKey
			}
			// end

			list.add(vo);
		}
		return list;
	}
	/**
	 * 判断费用金额是否超限
	 * 
	 * @author Hins
	 * @date 2015年12月3日 上午10:06:25
	 * 
	 * @param fee - 金额（单位分）
	 */
	public static void validateFeeOverLimit(long fee) {
		// 金额小于0和大于阀值，分开不同的提示语
		if (fee <= 0) {
			logger.error("order#service#validateFeeOverLimit | 订单金额小于等于0 | totalFee: {}", fee);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDERDETAIL_PAY_FEE_TOO_SMALL());
		}
		// 2015-12-02 modify by hins 内容：判断下单的最大金额是否超限
		if (fee > OrderConstants.ORDER_FEE_THRESHOLD) {
			logger.error("order#service#validateFeeOverLimit | 订单金额超过阀值 | totalFee: {}", fee);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_SETTLE_SETTLEORDER2_TOTALFEE_MAX());
		}
	}
	
	/**
	 * 获取订单可选支付方式<br>
	 * 在以下支付方式中选择1-支付宝；2-微信；3-现金
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午6:41:02
	 * 
	 * @param supportCash - 是否支持现金支付
	 * @param useCoupon - 订单是否使用优惠券（如果使用了优惠券，则不支持现金支付，取值范围{@link com.yogu.core.enums.BooleanConstants}）
	 * @param payType - 支付类型（米星付不支持出现现金支付）
	 * @param isThirdExpress - 是否第三方配送
	 * @return 可选支付方式，多个用逗号分隔
	 */
	public static String getOptionsPayMode(short supportCash, short useCoupon, short payType, short isThirdExpress){
		// 20177-01-22 add by hins 内容：判断平台是否启用支付宝、微信支付。不判断2个都不启用的情况，没时间啊
		String alipaySwitch = ConfigRemoteService.getConfig(ConfigGroupConstants.SERVER_CONFIG, ConfigGroupConstants.ALIPAY_PAY_SWITCH);
		String optionsPayMode = OPEN_ALIPAY_PAY.equals(alipaySwitch) ? String.valueOf(PayMode.ALIPAY.getValue()) : null;
		try {
			if (supportWechat()) {
				optionsPayMode = optionsPayMode == null ? String.valueOf(PayMode.WECHAT.getValue()) : optionsPayMode + "," + PayMode.WECHAT.getValue();
			}
		} catch (Exception e) {
			logger.error("判断可选支付方式是否支持微信支付异常, e: " + e.getMessage());
		}
		
		if (optionsPayMode == null) { // 防止都没有启用的情况，不能返回null
			optionsPayMode = "";
		}
		
		// 只有支持现金支付、没有使用优惠券、订单不是米星付、没有使用第三方配送的订单才满足 货到付款
		if (supportCash != StoreConstants.SUPPORT_CASH)
			return optionsPayMode;

		if (useCoupon == BooleanConstants.TRUE)
			return optionsPayMode;

		if (payType == PayType.MAZING_PAY.getValue())
			return optionsPayMode;

		if (isThirdExpress == BooleanConstants.TRUE) {
			return optionsPayMode;
		}
		return StringUtils.isBlank(optionsPayMode) ? String.valueOf(OrderConstants.PAY_MODE_CACH) : optionsPayMode + "," + OrderConstants.PAY_MODE_CACH;
	}
	
	/**
	 * 获取订单可选支付方式<br>
	 * 在以下支付方式中选择1-支付宝；2-微信<br>
	 * 该方法暂时只提供给米星付相关送你，不会返回现金支付，请谨慎调用
	 * 
	 * @author hins
	 * @date 2016年6月15日 下午5:14:41
	 * @return 可选支付方式，多个用逗号分隔
	 */
	public static String getMazingPayOptionsPayMode(){
		// 20177-01-22 add by hins 内容：判断平台是否启用支付宝、微信支付。如果2个都没有启用，抛出异常
		String alipaySwitch = ConfigRemoteService.getConfig(ConfigGroupConstants.SERVER_CONFIG, ConfigGroupConstants.ALIPAY_PAY_SWITCH);
		String wechatSwitch = ConfigRemoteService.getConfig(ConfigGroupConstants.SERVER_CONFIG, ConfigGroupConstants.WECHAT_PAY_SWITCH);
		String optionsPayMode = OPEN_ALIPAY_PAY.equals(alipaySwitch) ? String.valueOf(PayMode.ALIPAY.getValue()) : null;
		if (OPEN_WECHAT_PAY.equals(wechatSwitch)) {
			optionsPayMode = optionsPayMode == null ? String.valueOf(PayMode.WECHAT.getValue()) : optionsPayMode + "," + PayMode.WECHAT.getValue();
		}
		
		if (optionsPayMode == null) {
			AlarmSender.sendAlarm(CommonConstants.ORDER_DOMAIN, "2个支付渠道都没有开通", "2个支付渠道在config配置上都关闭了，无法支付啦！");
			throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_NOT_EXIST_EFFECTIVE_PAY_MODE);
		}
				
		return optionsPayMode;
	}
	
	/**
	 * 判断可选支付方式是否支持微信支付<br>
	 * 根据ios、Android的版本号，小于指定版本号的不支持微信支付，返回false
	 * 
	 * @author Hins
	 * @date 2016年2月2日 下午5:14:42
	 * 
	 * @return
	 */
	private static boolean supportWechat() {
		// 20177-01-22 add by hins 内容：如果平台不启用微信支付，直接返回不支持
		String wechatSwitch = ConfigRemoteService.getConfig(ConfigGroupConstants.SERVER_CONFIG, ConfigGroupConstants.WECHAT_PAY_SWITCH);
		if (!OPEN_WECHAT_PAY.equals(wechatSwitch)) {
			return false;
		}
		// 最低兼容版本号
		int iosCurrBuildId = 170;
		int androidCurrBuildId = 170;

		BaseParams baseParam = SecurityContext.getBaseParams();

		String aname = baseParam.getAppName();
		String aver = baseParam.getAppVersion();

		if (StringUtils.isNotBlank(aname)) {
			aname = aname.toLowerCase();
			if (aname.contains("iphone")) {
				if (StringUtils.isNotBlank(aver)) {

					logger.info("OrderUtils#supportWechat | 获取当前app的验签参数,判断可选支付方式是否支持微信支付 | baseParam: {}", baseParam);

					String[] arr = aver.trim().split("\\.");

					int hundred = Integer.valueOf(arr[0]) * 100;
					int ten = Integer.valueOf(arr[1]) * 10;
					int bits = Integer.valueOf(arr[2]);

					int buildId = hundred + ten + bits;

					if (buildId >= iosCurrBuildId)
						return true;
				} else {
					logger.warn("OrderUtils#supportWechat | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}

			} else if (aname.contains("android")) {
				if (StringUtils.isNotBlank(aver)) {

					logger.info("OrderUtils#supportWechat | 获取当前app的验签参数,判断可选支付方式是否支持微信支付 | baseParam: {}", baseParam);

					String[] arr = aver.trim().split("\\.");

					int hundred = Integer.valueOf(arr[0]) * 100;
					int ten = Integer.valueOf(arr[1]) * 10;
					int bits = Integer.valueOf(arr[2]);

					int buildId = hundred + ten + bits;

					if (buildId >= androidCurrBuildId)
						return true;
				} else {
					logger.warn("OrderUtils#supportWechat | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}
			}

		} else {
			logger.warn("OrderUtils#supportWechat | 没有获取验签参数 aname | baseParam: {}", baseParam);
		}
		return false;

	}

	/**
	 * 判断结算（预下单）方法是否要加载优惠券列表<br>
	 * 根据ios、Android的版本号，小于指定版本号的不加载优惠券，返回false
	 * 
	 * @author hins
	 * @date 2016年8月1日 上午11:17:20
	 * @return boolean
	 */
	public static boolean supportCouponBySettle() {
		// 最低兼容版本号
		int iosAver = 211;
		int androidAver = 210;
		int androidBuild = 84;

		BaseParams baseParam = SecurityContext.getBaseParams();

		String aname = baseParam.getAppName();
		String aver = baseParam.getAppVersion();

		if (StringUtils.isNotBlank(aname)) {
			aname = aname.toLowerCase();
			if (aname.contains("iphone")) {
				if (StringUtils.isNotBlank(aver)) {

					logger.info("order#service#supportCoupon | 获取当前app的验签参数,判断可选支付方式是否加载优惠券 | baseParam: {}", baseParam);

					String[] arr = aver.trim().split("\\.");

					int hundred = Integer.valueOf(arr[0]) * 100;
					int ten = Integer.valueOf(arr[1]) * 10;
					int bits = Integer.valueOf(arr[2]);

					int iaver = hundred + ten + bits;

					if (iaver >= iosAver)
						return true;
				} else {
					logger.warn("order#service#supportCoupon | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}

			} else if (aname.contains("android")) {
				if (StringUtils.isNotBlank(aver)) {

					logger.info("order#service#supportCoupon | 获取当前app的验签参数,判断可选支付方式是否加载优惠券 | baseParam: {}", baseParam);

					String[] arr = aver.trim().split("\\.");

					int hundred = Integer.valueOf(arr[0]) * 100;
					int ten = Integer.valueOf(arr[1]) * 10;
					int bits = Integer.valueOf(arr[2]);
					int build = Integer.valueOf(arr[3]);

					int aaver = hundred + ten + bits;

					if (aaver >= androidAver && build >= androidBuild)
						return true;
				} else {
					logger.warn("order#service#supportCoupon | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}
			}

		} else {
			logger.warn("order#service#supportCoupon | 没有获取验签参数 aname | baseParam: {}", baseParam);
		}
		return false;
	}
	
	/**
	 * 校验客户端版本是否支持装载h5链接优惠券的适用对象<br>
	 * 历史原因：因为ios旧版本，如果返回“h5链接”类型的优惠券item，点击优惠券，会进入“空白”的餐厅miniblog
	 * 
	 * @author hins
	 * @date 2016年11月24日 下午6:04:47
	 * @return boolean
	 */
	public static boolean supportLinkUrlCoupon() {
		// 最低兼容版本号
		int iosAver = 289;

		BaseParams baseParam = SecurityContext.getBaseParams();

		String aname = baseParam.getAppName();
		String aver = baseParam.getAppVersion();

		if (StringUtils.isNotBlank(aname)) {
			aname = aname.toLowerCase();
			if (aname.contains("iphone")) {
				if (StringUtils.isNotBlank(aver)) {

					logger.info("order#service#supportLinkUrlCoupon | 获取当前app的验签参数,判断支持点击“网页跳转”的优惠券 | baseParam: {}", baseParam);
					try {
						String[] arr = aver.trim().split("\\.");
						int iaver = Integer.valueOf(arr[arr.length - 1]);

						if (iaver >= iosAver)
							return true;
					} catch (Exception e) {
						logger.error("order#service#supportLinkUrlCoupon | 解析aver发生异常 | baseParam: {}", baseParam, e);
						return false;
					}
					
				} else {
					logger.warn("order#service#supportLinkUrlCoupon | 没有获取验签参数 aver | baseParam: {}", baseParam);
				}

			} else if (aname.contains("android")) {
				return true;
			}

		} else {
			logger.warn("order#service#supportLinkUrlCoupon | 没有获取验签参数 aname | baseParam: {}", baseParam);
		}
		return false;
	}
	
	/**
	 * 把规格的备注列表变成 String，英文逗号隔开。
	 * 如果 List 为null或空，返回 ""
	 * @param list 备注列表
	 * @return 返回不为null的String
	 * @author ten 2016/2/23
	 */
	@Deprecated
	public static String supplementsToString(List<String> list) {
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		Collections.sort(list); // 按字母排序，保证任何情况下，顺序都一样
		String supplements = org.apache.commons.lang3.StringUtils.join(list, ',');
		return supplements;
	}
	
	/**
	 * 适配 取消订单的多语言提示信息
	 * @param vo
	 */
	public static String adaptCancelReason(String rejectReason) {
		if (StringUtils.isNotBlank(rejectReason)) {
			rejectReason = rejectReason.trim();

			String rejectRemark = rejectReason;

			if (OrderConstants.USER_CANCEL_NOT_PAY_ORDER_REASON.equals(rejectReason))
				rejectRemark = OrderMessages.ORDER_USER_CANCEL_NOTPAY_REASON();
			else if (OrderConstants.TIME_OUT_ORDER_REASON.equals(rejectReason))
				rejectRemark = OrderMessages.ORDER_TIMEOUT_AUTOCANCEL_REASON();
			else if (OrderConstants.ADMIN_ORDER_REASON.equals(rejectReason))
				rejectRemark = OrderMessages.ORDER_ADMIN_CANCEL_REASON();
			return rejectRemark;

		}

		return null;
	}
	
	/**
	 * 校验指定配送时间，是否可以开始顺丰配送
	 * @param deliveryTime
	 * @author hins
	 * @date 2016年10月16日 下午6:49:56
	 * @return boolean
	 */
	public static boolean veryfyCanBeginSfExpress(Date deliveryTime){
		Calendar tmp = DateUtils.getCalendar();
		tmp.add(Calendar.HOUR_OF_DAY, 3);
		return deliveryTime.before(tmp.getTime());
	}
	
	/**
	 * 获取门店当天的订单序列号
	 * 
	 * @author Hins
	 * @date 2015年9月6日 下午4:57:42
	 * 
	 * @param storeId - 门店ID
	 * @param deliveryTime - 配送时间
	 * @return 门店当天的订单序列号
	 */
	public static String getSerial(long storeId, int time) {
		// 从缓存中读取门店当天的序列号+1，并将新的序列号覆盖redis原有的值
		// 若从缓存中读取失败，则查询数据库，统计最新的订单系列号，并重新赋入redis
		// String time = DateUtils.formatDate(deliveryTime, DateUtils.YYYYMMDD);
		String serialKey = OrderCacheKey.serialKey(storeId, String.valueOf(time));

		Redis redis = (Redis) MainframeBeanFactory.getBean("redis");
		OrderDao orderDao = MainframeBeanFactory.getBeanOneOfType(OrderDao.class);
		
		long serial = redis.incr(serialKey);
		if (serial == 1) {// 缓存失效，重新读取
			// 获取serialKey“锁”
			// redis.setnx若返回1，则取得“锁”。1.统计商家当天最大的序列号+1；2.将计算得到的序列号重新赋入reids
			// redus.setnx若返回0，则表示当前“锁”已被暂用。
			String serialLockKey = serialKey + "_lock";
			RedisLock lock = new RedisLock(redis, serialLockKey);
			if (lock.lock()) {
				try {
					// 查询从库，不考虑同一家餐厅在今天首单短时间内
					serial = orderDao.statMaxSerial(storeId, time);
					serial++;
					redis.set(serialKey, serial, OrderCacheKey.TIME_24H);
				} finally {
					lock.unlock();
				}
			} else {// 存在锁
				logger.error("orderUtils#getSerial | 存在serialLock锁 | serialLockKey: {}", serialLockKey);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_ORDER_CREATEORDER_GETSERIAL_LOCK_EXIST());
			}
		}
		return String.valueOf(serial);
	}
}
