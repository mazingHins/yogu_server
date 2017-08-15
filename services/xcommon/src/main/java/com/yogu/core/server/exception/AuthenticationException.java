package com.yogu.core.server.exception;

/**
 * 表示“无法访问”的异常 <br>
 * 有两个地方会发生这个异常：<br>
 * 1：ApiAuthenticatorImpl校验失败时<br>
 * 2：AmassFrequencyFilter访问受限时<br>
 * <br>
 * 
 * @author JFan 2015年7月17日 下午5:31:30
 */
public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -3550120846120210565L;

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

	public AuthenticationException(int code, String message) {
		this(code, message, true);
	}

	public AuthenticationException(int code, String message, boolean needPrint) {
		super(message);
		this.code = code;
		this.needPrint = needPrint;
	}

	public AuthenticationException(int code, String message, Throwable t) {
		this(code, message, true, t);
	}

	public AuthenticationException(int code, String message, boolean needPrint, Throwable t) {
		super(message, t);
		this.code = code;
		this.needPrint = needPrint;
	}

}
