package com.yogu.services.backend.admin.context;

import java.io.Serializable;

/**
 * 登录的用户信息
 * @author linyi 2015/6/7.
 */
public class AdminLoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private long accountId;

	private int appId;

	private String token;

	public AdminLoginUser() {
	}

	public AdminLoginUser(int accountId, int appId) {
		this.accountId = accountId;
		this.appId = appId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getToken() {
		return token == null ? "" : token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("AdminContext:").append(accountId).append(",").append(appId).append(",").append(token);
		return buf.toString();
	}

	public static AdminLoginUser parse(String context) {
		String s = context.substring("AdminContext:".length());
		String[] array = s.split(",");
		AdminLoginUser user = new AdminLoginUser();
		user.setAccountId(Integer.parseInt(array[0]));
		user.setAppId(Integer.parseInt(array[1]));
		user.setToken(array[2].toString());
		return user;
	}

}
