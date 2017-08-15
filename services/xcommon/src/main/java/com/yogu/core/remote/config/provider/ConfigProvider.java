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

	/**
	 * 根据code获取地理信息对象
	 */
	public Area getArea(String code) throws Exception;

	/**
	 * 获得平台上：已开通的城市列表
	 */
	public List<Area> listAllCity();

	/**
	 * 读取所有的白名单列表
	 * @return 如果没有数据，返回 empty list
	 * @author ten 2016/1/14
	 */
	List<WhiteList> listAllWhiteList();
	
	/**
	 * 读取所有的餐厅标签组(组内包含标签)
	 * @return 如果没有数据，返回 empty list
	 * @author felix 2016/1/19
	 */
	List<StoreCategoryVO> listAllCategoryTag();
	
	/**
	 * 根据城市code获取该城市下的标签定制池
	 * 
	 * @param cityCode
	 * @param lang
	 * @author hins
	 * @date 2016年12月16日 下午4:01:48
	 * @return 定制池列表，如果不存在，返回empty list
	 */
	List<CustomizePoolVO> listCustomizeByCityCode(String cityCode, String lang);

	/**
	 * 读取所有的筛选标签组(组内包含标签)
	 * @return {"categoryId":1,"categoryName":"菜式","list":[{"tagId":1,"tagName":"中餐"},{"tagId":2,"tagName":"日餐"}]}
	 * @author east
	 * @date 2016年12月26日 上午11:18:23
	 */
	List<FilterTagCategoryVO> listAllFilterTag();

	/**
	 * 获取所有有效的标签定制池
	 * @author hins
	 * @date 2017年1月24日 下午4:29:09
	 * @return 定制池列表，如果不存在，返回empty list
	 */
	List<CustomizePoolVO> listAllEffective();
}
