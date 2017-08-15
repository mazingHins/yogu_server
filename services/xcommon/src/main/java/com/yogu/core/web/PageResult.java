package com.yogu.core.web;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用于记录服务调用的结果，带有页数
 * @author tendy
 * 2014/1/23
 */
public class PageResult<T> extends RestResult<T> {

	public static final int DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * 每页记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 总记录数
	 */
	private int count;

	@Deprecated
	public PageResult() {
	}

	public PageResult(T object) {
		super(object);
	}
	
	public PageResult(int code, String message) {
		super(code, message);
	}
	
	public PageResult(int code, T object) {
		super(code, null, object);
	}
	
	public PageResult(int code, T object, String message) {
		super(code, message, object);
	}

    public PageResult(int pageSize, int currentPage, int count, T object) {
    	super(object);

    	if (pageSize <= 0 || currentPage <= 0 || count < 0)
    		throw new IllegalArgumentException("The paramters is not valid.");

    	this.pageSize = pageSize;
    	this.currentPage = currentPage;
    	this.count = count;
		this.totalPage = getTotalPage(pageSize, count);
	}

	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		this.totalPage = getTotalPage(pageSize, count);
	}
	
	private static int getTotalPage(int everyPage, int totalRecords) {
		int totalPage = 0;

		if (totalRecords % everyPage == 0)
			totalPage = totalRecords / everyPage;
		else
			totalPage = totalRecords / everyPage + 1;

		return totalPage;
	}

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * 只输出code, success, message信息的toString方法
	 * @return
	 */
	public String toShortString() {
		StringBuilder buf = new StringBuilder(64);
		buf.append("Result[code=").append(code).append(",message=");
		if (message == null) {
			buf.append("<null>");
		}
		else {
			buf.append(message);
		}
		buf.append(",success=").append(success).append( "]");
		return buf.toString();
	}

}
