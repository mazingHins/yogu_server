package com.yogu.alarm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.core.consumer.BussinessType;
import com.yogu.core.consumer.messageBean.AlarmSendBean;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * 系统出现报警信息的处理，通知值班人员。可以指定email和sms发送，默认2者发送。
 * 
 * <pre>
 * （1）报警信息利用MQ发送；
 * （2）报警信息的接收人由MQ从后台管理系统获取（#TODO 暂时在配置表指定）；
 * </pre>
 * 
 * @author Hins
 * @date 2015年9月28日 下午5:26:10
 */
public class AlarmSender {

	private static final Logger logger = LoggerFactory.getLogger(AlarmSender.class);

	/**
	 * 报警级别：警告
	 */
	public static final String WARN = "WARN";

	/**
	 * 报警级别：严重错误
	 */
	public static final String ALARM = "ALARM";

	/**
	 * 调用MQ，发送报警信息到指定的的email和sms。<br>
	 * 适用于警告级别。
	 * 
	 * @author Hins
	 * @date 2015年10月7日 上午10:04:08
	 * 
	 * @param domain - 域名
	 * @param title - 报警标题
	 * @param message - 报警信息内容
	 * @param phone - 接受者的手机号码（可多个，用英文逗号分隔）
	 * @param email - 接受者的邮箱地址（可多个，用英文逗号分隔）
	 */
	public static void sendWarnToReceiver(String domain, String title, String message, String phone, String email) {
		logger.info("log#sendWarnToReceiver | 出现警告级别报警信息！！！  | domain: {}, title: {}, message: {}", domain, title, message);
		AlarmSendBean bean = new AlarmSendBean();
		bean.setDomain(domain);
		bean.setLevel(WARN);
		bean.setTitle(title);
		bean.setMessage(message);
		bean.setEmail(email);
		bean.setPhone(phone);
		CommandMQProducer.get().sendJSON(BussinessType.ALARM_SEND, bean);
	}

	/**
	 * 调用MQ，发送报警信息到默认的email和sms。<br>
	 * 适用于警告级别。
	 * 
	 * @author Hins
	 * @date 2015年9月28日 下午5:37:07
	 * 
	 * @param domain - 域名
	 * @param title - 报警标题
	 * @param message - 报警信息内容
	 * @param phone - 接受者的手机号码（可多个，用英文逗号分隔）
	 * @param email - 接受者的邮箱地址（可多个，用英文逗号分隔）
	 */
	public static void sendWarn(String domain, String title, String message) {
		logger.info("log#sendWarn | 出现警告级别报警信息！！！  | domain: {}, title: {}, message: {}", domain, title, message);
		AlarmSendBean bean = new AlarmSendBean();
		bean.setDomain(domain);
		bean.setLevel(WARN);
		bean.setTitle(title);
		bean.setMessage(message);
		CommandMQProducer.get().sendJSON(BussinessType.ALARM_SEND, bean);
	}

	/**
	 * 调用MQ，发送报警信息到指定的的email和sms。<br>
	 * 适用于严重错误级别。
	 * 
	 * @author Hins
	 * @date 2015年10月7日 上午10:39:46
	 * 
	 * @param domain - 域名
	 * @param title - 报警标题
	 * @param message - 报警信息内容
	 * @param phone - 接受者的手机号码（可多个，用英文逗号分隔）
	 * @param email - 接受者的邮箱地址（可多个，用英文逗号分隔）
	 */
	public static void sendAlarmToReceiver(String domain, String title, String message, String phone, String email) {
		logger.info("log#sendAlarmToReceiver | 出现严重错误级别报警信息！！！  | domain: {}, title: {}, message: {}, phone: {}, email: {}", domain, title,
				message, phone, email);
		AlarmSendBean bean = new AlarmSendBean();
		bean.setDomain(domain);
		bean.setLevel(ALARM);
		bean.setTitle(title);
		bean.setMessage(message);
		bean.setEmail(email);
		bean.setPhone(phone);
		CommandMQProducer.get().sendJSON(BussinessType.ALARM_SEND, bean);
	}

	/**
	 * 调用MQ，往指定的邮件接受者发送email<br>
	 * 邮件的内容不经过处理直接发送
	 * 
	 * @author Hins
	 * @date 2016年1月22日 下午3:22:15
	 * 
	 * @param subject - 邮件头标题
	 * @param message - 邮件内容
	 * @param email - 接受者
	 */
	public static void sendToReceiver(String subject, String message, String email) {
		AlarmSendBean bean = new AlarmSendBean();
		bean.setLevel(ALARM);
		bean.setMessage(message);
		bean.setEmail(email);
		bean.setSubject(subject);
		bean.setNeedFormatMessage(Boolean.FALSE);
		CommandMQProducer.get().sendJSON(BussinessType.ALARM_SEND, bean);
	}

	/**
	 * 调用MQ，往指定的邮件接受者发送email<br>
	 * 
	 * @author felix
	 * @date 2016年5月3日
	 * 
	 * @param subject - 邮件头标题
	 * @param message - 邮件内容
	 * @param email - 接受者
	 * @param needFormat 是否需要格式化
	 */
	public static void sendToReceiver(String subject, String message, String email, String title, boolean needFormat) {
		AlarmSendBean bean = new AlarmSendBean();
		bean.setLevel(ALARM);
		bean.setMessage(message);
		bean.setEmail(email);
		bean.setSubject(subject);
		bean.setTitle(title);
		bean.setNeedFormatMessage(needFormat);
		CommandMQProducer.get().sendJSON(BussinessType.ALARM_SEND, bean);
	}

	/**
	 * 调用MQ，发送报警信息到默认的email和sms。<br>
	 * 适用于严重错误级别。
	 * 
	 * @author Hins
	 * @date 2015年9月28日 下午5:37:07
	 * 
	 * @param domain - 域名
	 * @param title - 报警标题
	 * @param message - 报警信息内容
	 */
	public static void sendAlarm(String domain, String title, String message) {
		logger.info("log#sendAlarm | 出现严重错误级别报警信息！！！  | domain: {}, title: {}, message: {}", domain, title, message);
		AlarmSendBean bean = new AlarmSendBean();
		bean.setDomain(domain);
		bean.setLevel(ALARM);
		bean.setTitle(title);
		bean.setMessage(message);
		CommandMQProducer.get().sendJSON(BussinessType.ALARM_SEND, bean);
	}

	/**
	 * 打印异常堆栈， 用于需要邮件告知的异常， 但无需阻断程序进行的功能
	 * 
	 * @param message 异常信息
	 * @param className 异常来源的类名，simpleName
	 * @param method 异常来源的方法
	 * @param args 异常需要打印的参数,可以是多个
	 * @param values 参数值
	 * @author sky 2016-09-01
	 */
	public static void printStackTrace(String message, String className, String method, String[] args, String[] values) {

		try {

			// 参数拼接串
			String params = "";

			if (ArrayUtils.isNotEmpty(args)) {

				for (int i = 0; i < args.length; i++) {
					String name = args[i];
					String value = "";
					try {
						value = values[i];
					} catch (Exception e) {
					}
					// 参数<-->参数值对
					String pair = name + " : " + value + ", ";
					params += pair;
				}
			}

			params = params.substring(0, params.lastIndexOf(","));

			String errorMsg = "" + className + "#" + method + " | " + message + "  | " + params;

			// 打印日志
			logger.error(errorMsg);
			// 抛出封装过(完整的参数信息)的异常信息, 然后捕捉, 用于堆栈
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMsg);

		} catch (Exception e) {
			// 打印异常信息堆栈(异常邮件会 抓获该堆栈信息)
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		AlarmSender.printStackTrace("MQ参数为空", "SmsConsumer", "getCommonSmsNotifyBean",
				new String[] { "payType", "message", "bussinessType" }, new String[] { "xxx", "yyy", "zzz" });
	}

}
