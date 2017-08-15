package com.yogu.core.utils;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NoHttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hins
 * @version 创建时间：2015年7月14日 下午12:20:22 类说明
 */
//@Deprecated // 此工具类已过期, 请使用SmsServiceFactory
public class SmsUtil {
	private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	// 通用发送接口的http地址
	private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

	/**
	 * 2015/10/19 更换为 mzcloud 的key
	 */
	private static final String API_KEY = "28f83c4f6b98a39fe88831d31f278e5f";

	private static final Integer TIME_OUT = 5000;// 请求超时时间

	/** 批量发送短信的每批数量 */
	private static final int BATCH_LIMIT = 10;

	/**
	 * 发送短信
	 * 
	 * @param mobile - 手机号码
	 * @param text - 短信内容
	 * @param country - 国家代码，暂时无用
	 * 
	 * @deprecated 此工具类已过期, 请使用SmsServiceFactory
	 */
	private static boolean send(String mobile, String text, String country) { if (StringUtils.isBlank(mobile)) {
			logger.error("util#sms | 错误的手机号码 | mobile: " + mobile);
			return false;
		}
		if (StringUtils.isBlank(text)) {
			logger.error("util#sms | 内容不能为空 | mobile: " + hideMobile(mobile) + ", text: empty");
			return false;
		}

		Map<String, String> params = new HashMap<>();
		params.put("apikey", API_KEY);
		params.put("text", text);
		params.put("mobile", mobile);
		String str = null;
		try {
			str = HttpClientUtils.doPost(URI_SEND_SMS, TIME_OUT, params, "UTF-8", "UTF-8");
		} catch (Exception e) {
			if (e instanceof NoHttpResponseException) {
				logger.error("util#sms | 调用发送短信接口失败 | mobile: " + mobile + ", text: " + text);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信失败，请重试");
			}
			throw e;
		}

		logger.info("util#sms | 发送短信结果 | mobile: {}, text: {}, sms result: {}", hideMobile(mobile), text, str);
		if (str != null) {
			if (str.indexOf("\"code\":0") >= 0) {
				// 简单处理，不转换成Map判断了
				return true;
			} else if (str.indexOf("\"code\":22") >= 0) {
				logger.error("util#sms | 短信验证码发送过于频繁 | mobile: " + mobile);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信过于频繁，请1小时后再试");
			}
		}

		return false;
	}

	/**
	 * 批量发送短信
	 * 
	 * @param mobile 手机号码, 多个用英文逗号分隔
	 * @param text 内容
	 * @param country 国家代码，暂时无用
	 * 
	 * @deprecated 此工具类已过期, 请使用SmsServiceFactory
	 */
	private static void sendByBatch(String mobile, String text, String country) {
		// 基本校验
		if (StringUtils.isBlank(mobile)) {
			logger.error("util#batchSms | 批量发送短信手机号码不能为空");
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "批量发送短信手机号码不能为空");
		}
		if (StringUtils.isBlank(text)) {
			logger.error("util#batchSms | 内容不能为空 | " + "text: empty");
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "内容不能为空");
		}

		String[] numbers = ParameterUtil.str2strs(mobile);

		if (null != numbers && numbers.length > 0) {
			// 计算需要发送的批次数
			// 假如有 1 ~ 100 个号码, 则 1~100 - 1 = 0 ~ 99, 然后 除以 100再加1等于第一批
			for (int i = 0; i < ((numbers.length - 1) / BATCH_LIMIT) + 1; i++) {
				StringBuilder sBuilder = new StringBuilder();
				StringBuilder hideBuilder = new StringBuilder();
				// 分批拼接号码字符串
				// 1, 正常的号码
				// 2, 错误提示时的隐藏号码
				// 第一批下标 0 ~ 99, 第二批 100 ~ 199, 一次类推
				int boundary = (i + 1) * BATCH_LIMIT > numbers.length ? numbers.length : (i + 1) * BATCH_LIMIT;
				for (int j = i * BATCH_LIMIT; j < boundary; j++) {
					if (StringUtils.isBlank(numbers[j]) || numbers[j].length() != 11) {
						logger.error("util#batchSms | 错误的手机号码 | mobile: " + numbers[j]);
						continue;
					}
					hideBuilder.append(",").append(hideMobile(numbers[j]));
					sBuilder.append(",").append(numbers[j]);
				}

				String batchMobile = sBuilder.toString().substring(1);
				String batchHideMobile = hideBuilder.toString().substring(1);

				Map<String, String> params = new HashMap<>();
				params.put("apikey", API_KEY);
				params.put("text", text);
				params.put("mobile", batchMobile);
				String str = null;
				try {
					str = HttpClientUtils.doPost(URI_SEND_SMS, TIME_OUT, params, "UTF-8", "UTF-8");
				} catch (Exception e) {
					if (e instanceof NoHttpResponseException) {
						logger.error("util#batchSms | 调用发送短信接口失败 | mobile: " + batchHideMobile + ", text: " + text);
						throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信失败，请重试");
					}
					throw e;
				}

				logger.info("util#batchSms | 发送短信结果 | mobile: {}, text: {}, sms result: {}", batchHideMobile, text, str);
				if (str != null) {
					if (str.indexOf("\"code\":0") >= 0) {
						// 简单处理，不转换成Map判断了
					} else if (str.indexOf("\"code\":22") >= 0) {
						logger.error("util#batchSms | 短信验证码发送过于频繁 | mobile: " + batchHideMobile);
						throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信过于频繁，请1小时后再试");
					}
				}

			}
		}
	}

	/**
	 * 隐藏手机的号码，避免直接输出手机号码
	 * 
	 * @param mobile - 手机号码
	 * @return
	 */
	public static String hideMobile(String mobile) {
		if (mobile != null && mobile.length() >= 11) {
			return mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4);
		}
		return mobile;
	}

	/**
	 * 把国家代码里的 + 号去掉
	 * 
	 * @param code 国家代码，如果为null，结果返回null
	 * @return 去掉加号后的结果，
	 */
	public static String trimCountryCode(String code) {
		if (code != null) {
			code = code.trim();
			if (code.startsWith("+")) {
				code = code.substring(1);
			}
		}
		return code;
	}

	public static void main(String[] args) {
		String mobileString = "13580480984,18620075025,14013114225";
		String msg = "【米星科技】您有一笔新订单201542323295359，请尽快处理！";
		send(mobileString, msg, "");
		sendByBatch(mobileString, msg, "+86");
	}

}
