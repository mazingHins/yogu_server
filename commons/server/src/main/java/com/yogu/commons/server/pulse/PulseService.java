/**
 * 
 */
package com.yogu.commons.server.pulse;

import java.util.Map;

/**
 * 取得服务端的最新状态<br>
 * 具体由服务端，自己实现<br>
 * <br>
 * 0：系统正常<br>
 * 1：DB异常<br>
 * 2：MC异常<br>
 * 3：Redis异常<br>
 * 
 * 5：CC配置中心异常<br>
 * ==<br>
 * 
 * @author JFan 2014年5月13日 上午11:32:46
 */
public interface PulseService {

	/**
	 * 返回全部的状态码描述<br>
	 * 
	 * -2、-1、0 为系统默认
	 */
	public Map<Integer, String> help();

	/**
	 * 返回最新服务端状态<br>
	 * <br>
	 * 0：系统正常<br>
	 * 1：DB异常<br>
	 * 2：MC异常<br>
	 * 3：Redis异常<br>
	 */
	public Integer newestState();

}
