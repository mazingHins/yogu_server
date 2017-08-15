package com.yogu.services.user.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.PushStatisticPO;

/**
 * mz_push_statistic 表的操作接口
 * 
 * @author Mazing 2016-04-11
 */
@TheDataDao
public interface PushStatisticDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(PushStatisticPO po);

	// /**
	// * 根据主键删除数据
	// */
	// public int deleteById(@Param("pid") long pid);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(PushStatisticPO po);

	/**
	 * 根据主键读取记录
	 */
	public PushStatisticPO getById(@Param("pid") long pid);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<PushStatisticPO> listAll();

	/**
	 * 更新发送总数
	 * 
	 * @param pid
	 * @param total
	 * @return
	 */
	public int updateTotal(@Param("pid") long pid, @Param("total") int total);

	/**
	 * 更新成功发送总数
	 * 
	 * @param pid
	 * @param successTotal
	 * @return
	 */
	public int updateSuccessTotal(@Param("pid") long pid, @Param("successTotal") int successTotal);
	
	/**
	 * 统计消息推送数据
	 * @return
	 * @author jack 2016/4/11
	 */
	List<Map<String, Object>> listPullMessage(Map<String, String> map);

}
