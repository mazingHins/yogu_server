package com.yogu.services.store;

public class AuditConstants {

	/** 未提交 **/
	public static short BEFORE_AUDIT = 0;
	/** 审核中 **/
	public static short IN_AUDIT = 1;
	/** 审核通过 **/
	public static short FINISH_AUDIT = 2;
	/** 认证未通过 **/
	public static short REJECT_AUDIT = 3;

	/**
	 * 所有认证都通过
	 */
	public static short PERMIT_ALL = 1;

	/**
	 * 进度完成 ,所有模块都验证通过
	 */
	public static short PROGRESS_COMPLETED = 1;

	/**
	 * 证件照-营业执照
	 */
	public static short LICENSE_OF_BUSINESS = 1;

	/**
	 * 证件照- catering certification
	 */
	public static short LICENSE_OF_CC = 2;
}
