package com.yogu.services.store.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.base.entry.GoodsRecommendPO;

/**
 * yg_goods_tag 表的操作接口
 * 
 * @author JFan 2015-08-07
 */
@TheDataDao
public interface GoodsRecommendDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(GoodsRecommendPO po);

	/**
	 * 根据主键读取记录
	 */
	public GoodsRecommendPO getById(@Param("adid") long adid);

	/**
	 * 获取指定状态的记录，结果按照创建时间倒序排序
	 * 
	 * @param status - 状态
	 * @param pageSize - 每页大小
	 * @param offset - 分页下标
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<GoodsRecommendPO> listByStatus(@Param("status") short status, @Param("pageSize") int pageSize, @Param("lastTime") long lastTime);

}
