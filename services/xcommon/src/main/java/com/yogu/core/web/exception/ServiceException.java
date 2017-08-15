package com.yogu.core.web.exception;

/**
 * 服务抛出的异常
 * 
 * @author tendy 2014/2/12
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 6695773016967595377L;

	/**
	 * 错误代码
	 */
	protected int code;

	/**
	 * 该错误是否需要打印到日志中
	 */
	protected boolean needPrint;

	public int getCode() {
		return code;
	}

	public boolean isNeedPrint() {
		return needPrint;
	}

	public ServiceException(int code, String message) {
		this(code, message, true);
	}

	public ServiceException(int code, String message, boolean needPrint) {
		super(message);
		this.code = code;
		this.needPrint = needPrint;
	}

	public ServiceException(int code, String message, Throwable t) {
		this(code, message, true, t);
	}

	public ServiceException(int code, String message, boolean needPrint, Throwable t) {
		super(message, t);
		this.code = code;
		this.needPrint = needPrint;
	}

}
