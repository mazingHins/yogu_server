package com.yogu.services.store.business.dao;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.business.entry.GoodsTrackPO;

/**
 * yg_goods 表的操作接口
 *
 */
@TheDataDao
public interface GoodsTrackDao {
	
	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(GoodsTrackPO po);

	/**
	 * 根据主键读取记录
	 */
	public GoodsTrackPO getById(@Param("goodsId") long goodsId);
	
	/**
	 * 根据商品key读取记录
	 */
	public GoodsTrackPO getByKey(@Param("goodsKey") long goodsKey);
	
}
