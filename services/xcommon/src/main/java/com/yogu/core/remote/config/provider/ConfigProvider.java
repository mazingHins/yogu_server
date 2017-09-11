/**
 * 
 */
package com.yogu.core.remote.config.provider;

import java.util.List;

import com.yogu.core.remote.config.Area;
import com.yogu.core.remote.config.Config;
import com.yogu.core.remote.config.CustomizePoolVO;
import com.yogu.core.remote.config.FilterTagCategoryVO;
import com.yogu.core.remote.config.StoreCategoryVO;
import com.yogu.core.remote.config.WhiteList;

/**
 * 获取配置的方式 定义 <br>
 * <br>
 * 如果是configapi，那么使用DB实现 <br>
 * 其他的域则是使用http实现 <br>
 * <br>
 * 注意：当前包下有个HTTP实现，在configapi项目下有个DB实现（DB实现的优先级高于HTTP）
 *
 * @author JFan 2015年10月22日 下午3:52:04
 */
public interface ConfigProvider {

	/**
	 * 获取全部的配置列表
	 */
	public List<Config> getAllConfig() throws Exception;

}
