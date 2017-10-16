package com.yogu.services.order.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.core.KeyValue;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.encrypt.AESUtil;
import com.yogu.commons.utils.encrypt.EncryptUtil;
import com.yogu.commons.utils.math.ThirtySixCountUtil;
import com.yogu.core.constant.CouponShowStatus;
import com.yogu.core.constant.CouponTypeConstants;
import com.yogu.core.enums.CouponStatus;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.utils.ComputeUtils;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;

/**
 * 优惠券相关工具类
 * 
 * @author Hins
 * @date 2015年12月23日 下午5:43:05
 */
public class CouponUtils {

	private static final Logger logger = LoggerFactory.getLogger(CouponUtils.class);

	// 暂时不考虑encrypt key变更的情况，如果变更了，请重启服务
	private static Map<String, List<KeyValue<Integer, Long>>> cache = new ConcurrentHashMap<>();
	private static String prefixB = "B-";// 百分比
	private static String prefixK = "K-";// 千分比
	private static int prefixLength = prefixB.length();

	public static void main(String[] args) {
		List<KeyValue<Integer, Long>> datas = new ArrayList<>();
		datas.add(new KeyValue<>(1, 111111L));
		datas.add(new KeyValue<>(3, 555555L));
		datas.add(new KeyValue<>(5, 303030L));
		datas.add(new KeyValue<>(11, 9999999L));
		datas.add(new KeyValue<>(80, 262626L));
		String str = encryptCouponIdByRisk(datas);
		System.out.println(str);

		Map<Long, Integer> count = new HashMap<>();
		for (int i = 1; i <= 1000000; i++) {
			// long id = riskCouponId(datas);
			long id = decryptCouponIdByRisk(str);
			Integer c = count.get(id);
			if (null == c)
				c = 1;
			c += 1;
			count.put(id, c);
		}
		System.out.println(count);
	}

	/**
	 * 加密多个优惠券ID和几率，格式为：B-xxx --> xxx: 几率>couponId [|几率>couponId]<br>
	 * <br>
	 * 举例：111111的几率为30%、555555的几率为70%<br>
	 * couponId和几率组成的串：30>111111|70>555555<br>
	 * 几率串加密后为：89ed8a20b5003515fc49e82b41531ffb047f79d213d9944e4630d35471eb1ebe<br>
	 * 最终返回的字符串为：B-89ed8a20b5003515fc49e82b41531ffb047f79d213d9944e4630d35471eb1ebe
	 */
	private static String encryptCouponIdByRisk(List<KeyValue<Integer, Long>> datas) {
		if (CollectionUtils.isEmpty(datas))
			throw new RuntimeException("输入的数据不正确！");
		StringBuilder sb = new StringBuilder();
		boolean one = true;
		for (KeyValue<Integer, Long> kv : datas) {
			if (!(one))
				sb.append("|");
			sb.append(kv.getKey()).append(">").append(kv.getValue());
			one = false;
		}
		System.out.println(sb.toString());
		return (prefixB + encryptString(sb.toString()));
	}

	/**
	 * 解密优惠券ID（支持几率形式的串）<br>
	 * 如果str（加密串）是以R+=开头，则表示是一个包含couponId和几率的串，算法参考encryptCouponIdByRisk方法<br>
	 * 如果是几率串，则解读并根据几率要求，返回其中一个couponId
	 */
	public static long decryptCouponIdByRisk(String str) {
		if (str.startsWith(prefixB) || str.startsWith(prefixK)) {
			int rNum = (str.startsWith(prefixK) ? 1000 : 100);// 百分比还是千分比
			List<KeyValue<Integer, Long>> datas = giveDatas(str);
			return riskCouponId(datas, rNum);
		} else {
			return decryptCouponId(str);
		}
	}

	private static List<KeyValue<Integer, Long>> giveDatas(String str) {
		List<KeyValue<Integer, Long>> result = cache.get(str);
		if (null == result) {
			try {
				String tmp = str.substring(prefixLength);
				tmp = decryptCouponId0(tmp);

				String[] ss = tmp.split("[|]");
				List<KeyValue<Integer, Long>> datas = new LinkedList<>();
				for (String s : ss) {
					String[] ss2 = s.split("[>]");
					datas.add(new KeyValue<>(Integer.parseInt(ss2[0]), Long.parseLong(ss2[1])));
				}

				result = datas;
				cache.put(str, result);
			} catch (Exception e) {
				logger.error("mobile#share#coupon | 解密ID<几率>失败 | str: '{}'", str, e);
			}
		}
		return result;
	}

	private static long riskCouponId(List<KeyValue<Integer, Long>> datas, int rNum) {
		if (CollectionUtils.isEmpty(datas))
			return 0;
		Random r = ThreadLocalContext.getGlobalValue("T_CU_RANDOM");
		if (null == r) {
			r = new Random();
			ThreadLocalContext.setGlobalValue("T_CU_RANDOM", r);
		}

		int value = 0;
		int risk = r.nextInt(rNum);// [0~100) [0~1000)
		for (KeyValue<Integer, Long> kv : datas) {
			value += kv.getKey();
			if (risk <= value)
				return kv.getValue();
		}

		return 0;
	}

	// ************************************************************************

	/**
	 * 加密优惠券ID 加密券包ID
	 */
	public static String encryptCouponId(long id) {
		String str = Long.toString(id);
		return encryptString(str);
	}

	public static String encryptString(String str) {
		String key = couponIdEDKey();
		try {
			return AESUtil.encrypt(key, str).toLowerCase();
		} catch (Exception e) {
			logger.error("store#share | 加密优惠券ID出错", e);
		}
		throw new ServiceException(ResultCode.FAILURE, "系统错误，请重试");
	}

	private static String couponIdEDKey() {
		String key = ConfigRemoteService.getConfig(ConfigGroupConstants.SHARE_INFO, ConfigGroupConstants.CONFIG_KEY_COUPON_ACCESSKEY);
		return key;
	}

	/**
	 * 解密优惠券的ID
	 */
	public static long decryptCouponId(String strId) {
		String str = StringUtils.trimToNull(strId);
		if (null != str)
			try {
				if (str.length() >= 16) {
					String tmp = decryptCouponId0(str);
					return NumberUtils.toInt(tmp, 0);
				}
			} catch (Exception e) {
				logger.error("mobile#share#coupon | 解密ID失败 | id: {}", strId, e);
			}

		logger.error("mobile#share#coupon | 错误的ID | id: {}, projenv: {}", strId, GlobalSetting.getProjenv());
		return 0;
	}

	private static String decryptCouponId0(String str) throws Exception {
		// AES加密：
		// 在原始数据长度为16的整数倍时，假如原始数据长度等于16*n，则使用NoPadding时加密后数据长度等于16*n，
		// 其它情况下加密数据长 度等于16*(n+1)。在不足16的整数倍的情况下，假如原始数据长度等于16*n+m[其中m小于16]，
		// 除了NoPadding填充之外的任何方 式，加密数据长度都等于16*(n+1)；
		String key = couponIdEDKey();
		return AESUtil.decrypt(key, str);
	}

	// ************************************************************************

	/**
	 * 访问 优惠券、券包 时，生成token、sign
	 * 
	 * @param couponId 优惠券、券包 ID
	 * @param func 哪个方法的请求（参考CouponConstants常量，只限：FUNC_SHARE | FUNC_BAG_SHARE）
	 * @param params 请求时附带的参数列表
	 * @return KV<token, sign>
	 */
	public static KeyValue<String, String> signCouponAccess(String couponId, String func) {
		if (StringUtils.isBlank(couponId) || StringUtils.isBlank(func))
			throw ResultCode.paramExcp("参数错误！");

		String token = RandomStringUtils.random(20, CommonConstants.LETTER_NUMBER_CHARS);
		long flag = accessSignOperation(couponId, func, token);
		String sign = EncryptUtil.getMD5(token + Long.toString(flag));
		return new KeyValue<String, String>(token, sign);
	}

	/**
	 * 验证 访问 优惠券、券包 时的token、sign是否正确
	 */
	public static boolean valiCouponAccesSign(String couponId, String func, String token, String sign) {
		if (StringUtils.isBlank(couponId) || StringUtils.isBlank(func) || StringUtils.isBlank(token) || StringUtils.isBlank(sign))
			return false;

		long flag = accessSignOperation(couponId, func, token);
		String newSign = EncryptUtil.getMD5(token + Long.toString(flag));
		return newSign.equals(sign);
	}

	/**
	 * couponId、func、token 进行预定义的数值运算
	 */
	private static long accessSignOperation(String couponId, String func, String token) {
		long cnum = ThirtySixCountUtil.gen(EncryptUtil.getMD5(couponId).toUpperCase());
		long fnum = ThirtySixCountUtil.gen(EncryptUtil.getMD5(func).toUpperCase());
		long tnum = ThirtySixCountUtil.gen(token.toUpperCase());

		// （36进制）fnum + tnum 的值决定：cnum 和 fnum 之间做什么运算
		int operation = accessSignOperation0(fnum, tnum, true);
		long num1 = accessSignOperation1(cnum, fnum, operation);

		// （36进制）fnum + cnum 的值决定：tnum 和 fnum 之间做什么运算
		operation = accessSignOperation0(fnum, cnum, false);
		long num2 = accessSignOperation1(tnum, fnum, operation);

		return num1 + num2;
	}

	/**
	 * @param odd true：单数位、false：偶数为
	 * @return 1234分别表示：加减乘除
	 */
	private static int accessSignOperation0(long num1, long num2, boolean odd) {
		// +1234598765是防止 单双 取不到值的情况
		long num = num1 + num2 + 1234598765;
		char[] cs = Long.toString(num).toCharArray();

		// odd=true说明：num -> "单数位的值" -> newNum；例如：123456789 -> 13579
		// odd=false说明：num -> "双数位的值" -> newNum；例如：123456789 -> 2468
		StringBuilder sb = new StringBuilder();
		boolean one = true;
		for (char c : cs) {
			if ((one && odd) || ((!(one) && !(odd))))
				sb.append(c);
			one = !(one);
		}
		long newNum = Long.valueOf(sb.toString());

		// 转换成 1~4 分别表示 加减乘除
		long n = num % newNum;
		return Math.abs((int) n % 4) + 1;
	}

	private static long accessSignOperation1(long num1, long num2, int operation) {
		if (1 == operation)
			return num1 + num2;
		if (2 == operation)
			return num1 - num2;
		if (3 == operation)
			return num1 * num2;
		return num1 / num2;
	}

	// ************************************************************************

	/**
	 * 优惠券（或券包）转让时，生成的token、sign（会存储到redis中，24小时）
	 * 
	 * @param couponId 优惠券、券包 ID
	 * @param imid 转让人的imid
	 * @return KV<token, sign>
	 */
	public static KeyValue<String, String> signCouponTransfer(Redis redis, long couponId, String imid) {
		if (0 >= couponId || StringUtils.isBlank(imid))
			throw ResultCode.paramExcp("参数错误！");

		String token = RandomStringUtils.random(16, CommonConstants.LETTER_NUMBER_CHARS);
		String sign = EncryptUtil.getMD5(token + couponId + imid);

		// 取标志位（跟couponId 和 sign有关）
		long flag = operationThirtySix(Long.toString(couponId), sign);

		// 设置标志位，24小时后过期！
		redis.setex(getCouponTransferKey(sign), 24 * 3600, Long.toString(flag));

		return new KeyValue<String, String>(token, sign);
	}

	/**
	 * 交验 优惠券（或券包）转让时token、sign是否正确、有效
	 */
	public static boolean valiCouponTransferSign(Redis redis, long couponId, String imid, String token, String sign) {
		if (0 >= couponId || StringUtils.isBlank(imid) || StringUtils.isBlank(token) || StringUtils.isBlank(sign))
			return false;

		String value = redis.get(getCouponTransferKey(sign));
		if (StringUtils.isEmpty(value))
			return false;

		String newSign = EncryptUtil.getMD5(token + couponId + imid);

		// 取标志位（跟couponId 和 sign有关）
		long flag = operationThirtySix(Long.toString(couponId), newSign);
		return value.equals(Long.toString(flag));
	}

	public static String getCouponTransferKey(String sign) {
		return CouponConstants.FUNC_TRANSFER + "_" + sign;
	}

	/**
	 * 两个字符串转36进制之后相加
	 */
	private static long operationThirtySix(String str1, String str2) {
		long num = ThirtySixCountUtil.gen(str1.toUpperCase());
		num += ThirtySixCountUtil.gen(str2.toUpperCase());
		return num;
	}

	// ########################################################################
	// ########################################################################
	// ########################################################################

	/**
	 * 根据优惠券类型，转换优惠券面值字符串结果
	 * 
	 * @author Hins
	 * @date 2015年12月23日 下午6:05:01
	 * 
	 * @param enoughMoney - 满减条件
	 * @param couponType - 优惠券类型
	 * @param faceValue - 优惠券面值（int类型）
	 * @return 面值字符串
	 */
	public static String formatFaceValue(long enoughMoney, short couponType, long faceValue){
		if (couponType == CouponTypeConstants.CASH_COUPON) { // #TODO 优惠券类型要用枚举/常量判断
			return OrderMessages.ORDER_COUPON_DESC_TYPE_CASH_FACEVALUE(ComputeUtils.fenToYuanInt(faceValue));
		}

		if (couponType == CouponTypeConstants.DISCOUNT_COUPON) {
			if(SecurityContext.change2English()){
				String discount = (100-faceValue) + "";
				return OrderMessages.ORDER_COUPON_DESC_FACEVALUE_TITLE(discount);
			}else{
				return OrderMessages.ORDER_COUPON_DESC_FACEVALUE_TITLE(ComputeUtils.divTen(faceValue));
			}
		}

		if (couponType == CouponTypeConstants.FULLCUT_COUPON) {
			return OrderMessages.ORDER_COUPON_DESC_TYPE_FULLCUT_FACEVALUE(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.fenToYuanInt(faceValue));
		}
		
		return null;
	}
	
	/**
	 * 根据优惠券类型，转换优惠券面值字符串结果<br>
	 * 此方法暂时适用于米星付
	 * 
	 * @param enoughMoney - 满减条件
	 * @param couponType - 优惠券类型
	 * @param faceValue - 优惠券面值（int类型）
	 * @author hins
	 * @date 2016年6月29日 上午10:36:17
	 * @return 面值字符串
	 */
	public static String formatMazingPayFaceValue(long enoughMoney, short couponType, long faceValue){
		if (couponType == CouponTypeConstants.CASH_COUPON) { 
			return OrderMessages.ORDER_COUPON_DESC_TYPE_CASH_FACEVALUE(ComputeUtils.fenToYuanInt(faceValue));
		}

		if (couponType == CouponTypeConstants.DISCOUNT_COUPON) {
			if(SecurityContext.change2English()){
				String discount = (100-faceValue) + "";
				return OrderMessages.ORDER_COUPON_DESC_FACEVALUE_TITLE(discount);
			}else{
				return OrderMessages.ORDER_COUPON_DESC_FACEVALUE_TITLE(ComputeUtils.divTen(faceValue));
			}
		}

		if (couponType == CouponTypeConstants.FULLCUT_COUPON) {
			return OrderMessages.ORDER_COUPON_DESC_TYPE_FULLCUT_FACEVALUE(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.fenToYuanInt(faceValue));
		}
		
		return null;
	}
	
	/**
	 * 根据优惠券类型，转换优惠券标题字符串结果<br>
	 * 此方法暂时适用于米星付,用于客户端动态计算优惠券的显示内容
	 * 
	 * @param enoughMoney - 满减条件
	 * @param couponType - 优惠券类型
	 * @param faceValue - 优惠券面值（int类型）
	 * @author hins
	 * @date 2016年7月4日 下午7:19:55
	 * @return String
	 */
	public static String formatMazingPayOptionalCouponTitle(long enoughMoney, short couponType, long faceValue){
		
		if (couponType == CouponTypeConstants.CASH_COUPON) { 
			return OrderMessages.MAZING_PAY_COUPON_TITLE_BY_CASH(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.fenToYuanInt(faceValue));
//			return "满" + ComputeUtils.fenToYuanInt(enoughMoney) + "可减" + ComputeUtils.fenToYuanInt(faceValue);
		}

		if (couponType == CouponTypeConstants.DISCOUNT_COUPON) {
			return OrderMessages.MAZING_PAY_COUPON_TITLE_BY_DISCOUNT(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.divTen(faceValue), 100 - (int) faceValue);
//			return "满" + ComputeUtils.fenToYuanInt(enoughMoney) + "享" + ComputeUtils.divTen(faceValue) + "折";
		}

		if (couponType == CouponTypeConstants.FULLCUT_COUPON) {
			return OrderMessages.MAZING_PAY_COUPON_TITLE_BY_FULLCUT(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.fenToYuanInt(faceValue));
//			return "凑" + ComputeUtils.fenToYuanInt(enoughMoney) + "减" + ComputeUtils.fenToYuanInt(faceValue);
		}
		
		return null;
	}
	
	/**
	 * 根据优惠券类型、状态，转换优惠券标题字符串结果<br>
	 * 此方法暂时适用于预下单使用优惠券,用于客户端动态计算优惠券的显示内容
	 * 
	 * @param showStatus - 优惠券显示状态{@link com.mazing.services.order.constants.CouponShowStatus}
	 * @param enoughMoney - 满减条件
	 * @param couponType - 优惠券类型
	 * @param faceValue - 优惠券面值（int类型）
	 * @author hins
	 * @date 2016年8月4日 上午11:07:48
	 * @return String
	 */
	public static String formatSettleOptionalCouponTitle(short showStatus, long enoughMoney, short couponType, long faceValue){
		
		if(showStatus == CouponShowStatus.DID_NOT_MEET_PHONE.getValue()){	// 本人使用即可减免
			return OrderMessages.SETTLE_COUPON_TITLE_SELF_USE();
		}
		
		if (couponType == CouponTypeConstants.CASH_COUPON) { 
			return OrderMessages.MAZING_PAY_COUPON_TITLE_BY_CASH(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.fenToYuanInt(faceValue));
		}

		if (couponType == CouponTypeConstants.DISCOUNT_COUPON) {
			return OrderMessages.MAZING_PAY_COUPON_TITLE_BY_DISCOUNT(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.divTen(faceValue), 100 - (int) faceValue);
		}

		if (couponType == CouponTypeConstants.FULLCUT_COUPON) {
			return OrderMessages.MAZING_PAY_COUPON_TITLE_BY_FULLCUT(ComputeUtils.fenToYuanInt(enoughMoney), ComputeUtils.fenToYuanInt(faceValue));
		}
		
		return null;
		
	}
	
	
	/**
	 * 根据优惠券状态，有效期，转换成显示状态
	 * 
	 * @author Hins
	 * @date 2015年12月24日 下午3:24:55
	 * 
	 * @param status - 优惠券状态
	 * @param startTime - 优惠券有效期开始时间
	 * @param endTime - 优惠券有效期截止时间
	 * @return
	 */
	public static short swiftCouponStatus(short status, Date startTime, Date endTime) {
		if (status == CouponStatus.USING.getValue()) {
			return CouponShowStatus.HAS_USED.getValue();
		}
		if (status == CouponStatus.USED.getValue()) {
			return CouponShowStatus.HAS_USED.getValue();
		}
		if (status == CouponStatus.INVALID.getValue()) {
			return CouponShowStatus.HAS_OVERDUE.getValue();
		}

		Date now = new Date(); // 当前时间，用于验证优惠券是否过期
		if (endTime != null && now.after(endTime)) {
			return CouponShowStatus.HAS_OVERDUE.getValue();
		}

		// 还没到开始使用时间，显示未使用
		if (startTime != null && now.before(startTime)) {
			return CouponShowStatus.UNUSED.getValue();
		}

		if (status == CouponStatus.UNUSE.getValue()) {
			return CouponShowStatus.UNUSED.getValue();
		}

		logger.error("order#coupon#swiftCouponStatus | 优惠券的状态不在可转换的代码判断条件内 | status: {}", status);
		return status;
	}
	
	/**
	 * 根据优惠券状态，有效期，手机尾号等转换成显示状态<br>
	 * 该方法适用于转换预下单优惠券信息，即获取没使用的优惠券列表（但分为可使用和没满足使用条件）<br>
	 * 
	 * @param status - 优惠券状态
	 * @param startTime - 优惠券有效期开始时间
	 * @param endTime - 优惠券有效期截止时间
	 * @author hins
	 * @date 2016年7月28日 下午2:41:26
	 * @return short
	 */
	public static short swiftSettleCouponStatus(short status, Date startTime, Date endTime) {
		// 一般只会有没使用的优惠券进入该方法，多余的代码暂时保留！！
		CouponStatus cs = CouponStatus.from(status);
		switch (cs) {
		case USING:
			return CouponShowStatus.HAS_USED.getValue();
		case USED:
			return CouponShowStatus.HAS_USED.getValue();
		case INVALID:
			return CouponShowStatus.HAS_OVERDUE.getValue();
		case DELETED:
			return CouponShowStatus.INVALID.getValue();
		case DID_NOT_MEET_PHONE:
			return CouponShowStatus.DID_NOT_MEET_PHONE.getValue();
		case DID_NOT_MEET_MONEY:
			return CouponShowStatus.DID_NOT_MEET_MONEY.getValue();
		case UNUSE:
			return CouponShowStatus.CAN_USE.getValue();
		default:
			break;
		}

		logger.error("order#coupon#swiftSettleCouponStatus | 优惠券的状态不在可转换的代码判断条件内 | status: {}", status);
		return status;

	}
	
	/**
	 * 获取优惠券最终优惠金额： 因为可能存在 订单总金额 小于 优惠券面值的情况, 这种情况最终优惠 = 订单总金额
	 * 
	 * @param faceValue 订单面值(现金券该值为优惠金额x100(单位 ：分); 折扣券该值为折扣值x100)
	 * @param totalFee 订单总金额
	 * @param couponType 优惠券类型
	 * @param mostOffer 折扣券最高优惠金额， 折扣券才判断改值
	 * @return 最终优惠金额值
	 * @author east 2016-11-15 从CouponServiceImpl迁移到这里来
	 */
	public static long getFinalValue(long faceValue, long totalFee, short couponType) {
		long finalValue = 0;

		if (CouponTypeConstants.CASH_COUPON == couponType) {// 现金券

			if (totalFee < faceValue) {// 订单总金额 小于 优惠券面值
				finalValue = totalFee;
			} else {
				finalValue = faceValue;
			}

		} else if (CouponTypeConstants.DISCOUNT_COUPON == couponType) {// 折扣券

			// 2016-01-06 modify by hins 内容：优惠金额向下取整
			// 最终优惠价格 (discount 折扣值（如：75表示 7.5折）在存入数据库中时,faceValue= 折扣值 x100 )
			finalValue = ComputeUtils.initCouponFee(totalFee, (int) faceValue);

		} else if (CouponTypeConstants.FULLCUT_COUPON == couponType) {// 满减券

			if (totalFee < faceValue) {// 订单总金额 小于 优惠券面值 (满减券这种情况应该不会发生)
				finalValue = totalFee;
			} else {
				finalValue = faceValue;
			}
		} else {
			logger.warn("couponService#getFinalValue | 获取优惠券最终计算的优惠值 发生错误,  暂不支持的优惠券类型 | couponType: {}", couponType);
		}

		return finalValue;
	}

}
