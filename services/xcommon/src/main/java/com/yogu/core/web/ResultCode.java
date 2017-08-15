package com.yogu.core.web;

import com.yogu.core.web.exception.ServiceException;

/**
 * 错误代码定义
 * 
 * @author tendy 2014/1/24
 */
public class ResultCode {

	/** 成功 */
	public static final int SUCCESS = 1;

	/** 失败 */
	public static final int FAILURE = 0;

	/** 参数错误 */
	public static final int PARAMETER_ERROR = 10;

	/** 记录不存在 */
	public static final int RECORD_NOT_EXIST = 50;

	/** 记录已经存在 */
	public static final int RECORD_EXISTED = 51;

	/** 系统配置错误 */
	public static final int SYSTEM_CONFIG_ERROR = 60;

	/** 频率超出范围 */
	public static final int FREQUENCY_OVERLOAD = 70;

	/** token错误 */
	public static final int TOKEN_ERROR = 80;

	/** 未授权访问 */
	public static final int UNAUTHORIZED_ACCESS = 90;

	/** 被拒绝的操作请求 */
	public static final int REJECTED_OPERATION = 110;
	
	/** 重复提交 */
	public static final int REPEAT_SUBMIT = 201;

	/** 未开通该城市服务 */
	public static final int CITY_NO_OPEN = 401;

	/** 未知错误 */
	public static final int UNKNOWN_ERROR = -1;

	/**
	 * 数据不不在
	 */
	public static ServiceException notExistExcp(String message) {
		return new ServiceException(RECORD_NOT_EXIST, message);
	}

	/**
	 * 得到一个参数错误的异常对象
	 */
	public static ServiceException paramExcp(String message) {
		return new ServiceException(PARAMETER_ERROR, message);
	}

	/**
	 * 失败
	 */
	public static ServiceException failureExcp(String message) {
		return new ServiceException(FAILURE, message);
	}

}
