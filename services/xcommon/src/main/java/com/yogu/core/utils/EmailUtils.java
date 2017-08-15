package com.yogu.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;

import com.yogu.core.consumer.messageBean.EmailBean;

/**
 * 发送邮件通用工具类, 区别于AlarmSender
 * @author east
 * @date 2016年11月25日
 */
public class EmailUtils {
	private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);
	
	/**
	 * 发送附件
	 * @param bean     
	 * @author east
	 * @date 2016年11月25日
	 */
	public static void sendEmail(EmailBean bean){
		JavaMailSender jms = ChartMailSender.createJavaMailSender();
		ChartMailSender sender = new ChartMailSender(jms);
		sender.setSubject(bean.getTitle());
		sender.setTo(bean.getEmail());
		sender.append(bean.getMessage() == null ? "" : bean.getMessage());
		sender.setByteArray(bean.getByteArray());
		sender.setByte(bean.isByte());
		sender.setFileName(bean.getFileName());
		try {
			sender.send();
		} catch (Exception e) {
			logger.error("发送email失败. | email: {}, message: {}", bean.getEmail(), bean.getMessage(), e.getMessage());
		}
	}
}
