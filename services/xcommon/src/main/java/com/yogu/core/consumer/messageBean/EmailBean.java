package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 邮件发送类
 * 
 * @author east
 * @date 2016年11月25日
 */
public class EmailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3508649189882165417L;

	/**
	 * 邮件标题
	 */
	private String title;

	/**
	 * 邮件内容
	 */
	private String message;

	/**
	 * 邮箱地址
	 */
	private String email;

	/**
	 * 是否以字节码数组发送附件
	 */
	private boolean isByte;

	/**
	 * 附件的字节码数组
	 */
	private byte[] byteArray;
	
	public EmailBean() {}

	public EmailBean(String title, String message, String email, boolean isByte, byte[] byteArray, String fileName) {
		this.title = title;
		this.message = message;
		this.email = email;
		this.isByte = isByte;
		this.byteArray = byteArray;
		this.fileName = fileName;
	}

	/**
	 * 附件名称
	 */
	private String fileName;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isByte() {
		return isByte;
	}

	public void setByte(boolean isByte) {
		this.isByte = isByte;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "EmailBean [title=" + title + ", message=" + message + ", email=" + email + ", isByte=" + isByte
				+ ", byteArray=" + Arrays.toString(byteArray) + ", fileName=" + fileName + "]";
	}

}
