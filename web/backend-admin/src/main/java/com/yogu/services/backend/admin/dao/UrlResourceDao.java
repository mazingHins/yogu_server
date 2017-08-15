package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.UrlResourcePO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_url_resource 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface UrlResourceDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(UrlResourcePO po);

	/**
	 * 根据主键删除数据
	 *
	 */
	public int deleteById(@Param("resId") int resId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(UrlResourcePO po);

	/**
	 * 根据主键读取记录
	 */
	public UrlResourcePO getById(@Param("resId") int resId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<UrlResourcePO> listAll();

	/**
	 * 根据应用系统ID和uri读取资源
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param uri
	 *            - uri
	 * @return 如果记录存在，返回该记录，否则返回null
	 */
	UrlResourcePO getByAppIdAndUri(@Param("appId") int appId, @Param("uri") String uri);

	/**
	 * 根据appId和 uri 更新资源数据： name,parent_res_id,operator,type,sequence=,last_modify
	 *
	 * @param po
	 * @return rows affected
	 */
	int updateRes(UrlResourcePO po);

	/**
	 * 根据Id列表获取资源
	 *
	 * @param ids
	 * @return
	 */
	List<UrlResourcePO> listByIds(List<Integer> ids);

	/**
	 * 依据应用获取资源
	 * @param appId
	 * @return
	 */
	List<UrlResourcePO> listByApp(@Param("appId") int appId);
}
