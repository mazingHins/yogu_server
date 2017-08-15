package com.yogu.commons.server.mock;

import javax.ws.rs.core.UriInfo;

/**
 * MOCK 适配器 <br>
 * 主要做识别请求
 * 
 * JFan 2014年12月15日 下午4:02:46
 */
public interface MockProvider {

	/**
	 * 决定当前请求，是否走MOCK<br>
	 * 
	 * 返回null | "" 则不走mock <br>
	 * 
	 * 返回了内容，并不表示一定去到mock，还需要看看mockService中是否取得到值
	 */
	public String distinguish(UriInfo uriInfo);

}
