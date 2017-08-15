package com.yogu.services.user.business.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.AndroidProfilePO;

/**
 * mz_android_profile 表的操作接口
 * 
 * @author Mazing 2015-11-18
 */
@TheDataDao
public interface AndroidProfileDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(AndroidProfilePO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(@Param("pid") int pid);

	/**
	 * 根据uid、deviceToken 删除 记录
	 * 
	 * @param did
	 * @return
	 * @author sky 2016-03-09
	 */
	public int deleteByDeviceToken(@Param("uid") long uid, @Param("did") String did);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(AndroidProfilePO po);

	/**
	 * 根据主键读取记录
	 */
	public AndroidProfilePO getById(@Param("pid") int pid);

	/**
	 * 根据用户id分页查询记录
	 * 
	 * @param uid - 用户ID
	 * @param offset - 起始下标
	 * @param rows - 行数
	 * @return
	 */
	public List<AndroidProfilePO> listByUid(@Param("uid") long uid, @Param("offset") int offset, @Param("rows") int rows);

	/**
	 * 查询全部记录
	 * 
	 * @param offset 第几条记录开始
	 * @param rows 返回多少条记录
	 * @param lang 
	 * @param city 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<AndroidProfilePO> listAll(@Param("offset") int offset, @Param("rows") int rows, @Param("lang") String lang, @Param("city")String city);

	/**
	 *  根据uid和dtype 获取最近的一条数据(updatetime desc limit 1)
	 * 
	 * @param uid 用户id
	 * @param dtype 设备类型
	 * @return
	 * @author sky
	 */
	public AndroidProfilePO getLatestByUidAndDtype(@Param("uid") long uid, @Param("dtype") short dtype);

	/**
	 * 通过deviceToken查找记录(将unique key 改为 deviceToken后，最多只能查找到一条记录)
	 * 
	 * @param did
	 * @return
	 * @author sky 2016-03-09
	 */
	public AndroidProfilePO getByDeviceToken(@Param("did") String did);

	/**
	 * 根据主键更新 Android push 信息
	 * 
	 * @param pid 主键id
	 * @param did 设备唯一标识
	 * @param channel 渠道
	 * @param version 版本
	 * @param updateTime 更新时间
	 * @return
	 * @author sky
	 */
	public int updateAndroidProfileById(@Param("pid") int pid, @Param("did") String did, @Param("channel") String channel,
			@Param("version") String version, @Param("updateTime") Date updateTime);
}
