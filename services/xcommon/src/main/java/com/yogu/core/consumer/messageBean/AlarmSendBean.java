package com.yogu.core.consumer.messageBean;

import java.io.Serializable;

/**
 * 系统出现报警信息的处理，通知值班人员的 messageBean
 * 
 * @author Hins
 * @date 2015年9月28日 下午6:10:07
 */
public class AlarmSendBean implements Serializable {

	private static final long serialVersionUID = -2191588593431624014L;
	
	/**
	 * 邮件标题，如果不指定，默认[{0}][域名: {1}, 时间:{2}, 出现异常]]
	 */
	private String subject = "";

	/**
	 * 日志级别
	 */
	private String level;

	/**
	 * 域名 orderapi,payapi等
	 */
	private String domain;

	/**
	 * 报警信息标题
	 */
	private String title;
	
	/**
	 * 报警信息内容
	 */
	private String message;

	/**
	 * 接受者的手机号码（可多个，用英文逗号分隔）
	 */
	private String phone;

	/**
	 * 接受者的邮箱地址（可多个，用英文逗号分隔）
	 */
	private String email;
	
	/**
	 * 是否需要再次格式化邮件内容，如格式化成标题：title，内容：message
	 */
	private boolean needFormatMessage = true;
	
	/**
	 * 邮件内容数
	 */
	private int count = 0;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isNeedFormatMessage() {
		return needFormatMessage;
	}

	public void setNeedFormatMessage(boolean needFormatMessage) {
		this.needFormatMessage = needFormatMessage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
