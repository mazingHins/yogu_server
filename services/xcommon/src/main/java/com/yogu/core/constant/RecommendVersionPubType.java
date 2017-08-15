package com.yogu.core.constant;

public class RecommendVersionPubType {

	/**
	 * 测试版
	 */
	public static final short BETA = 1;

	/**
	 * 正式版
	 */
	public static final short RELEASE = 2;

	/**
	 * 测试版的历史版
	 */
	public static final short BETA_HISTORY = 3;

	/**
	 * 正式版的历史版
	 */
	public static final short RELEASE_HISTORY = 4;

	/**
	 * 被回退的版本（以前是正式版）
	 */
	public static final short RELEASE_BACK = 5;

	/**
	 * 版本类型名称
	 * 
	 * @param pubType
	 * @return
	 */
	public static String getPubVersionName(short pubType) {
		switch (pubType) {
		case BETA:
			return "测试版";
		case RELEASE:
			return "正式版";
		case BETA_HISTORY:
			return "测试版的历史版";
		case RELEASE_HISTORY:
			return "正式版的历史版";
		case RELEASE_BACK:
			return "被回退的版本";
		default:
			return "";
		}
	}

}
