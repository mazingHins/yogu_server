package com.yogu.services.user.config.service;

import java.util.List;
import java.util.Map;

import com.yogu.core.remote.config.Config;

/**
 * 基础配置表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface ConfigService {

	/**
	 * 保存数据，如果有冲突就更新数据
	 * 
	 * @param dto 对象
	 */
	public void save(Config dto);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param dto - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(Config dto);

	/**
	 * 根据主键读取记录
	 */
	public Config getById(String configKey, String groupCode);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<Config> listAll();
	
	/**
	 * 根据分组读取记录
	 * 
	 * @return 返回分组的数据, 如果没有数据, 返回empty list
	 */
	public Map<String, String> getByGroupCode(String groupCode);

}
