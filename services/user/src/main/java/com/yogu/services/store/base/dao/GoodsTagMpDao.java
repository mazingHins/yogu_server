package com.yogu.services.store.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.base.entry.GoodsTagMpPO;
import com.yogu.services.store.base.entry.GoodsTagPO;

/**
 * yg_goods_tag 表的操作接口
 * 
 * @author JFan 2015-08-07
 */
@TheDataDao
public interface GoodsTagMpDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(GoodsTagMpPO po);

	/**
	 * 根据主键读取记录
	 */
	public GoodsTagMpPO getById(@Param("tagId") long tagId, @Param("goodsId") long goodsId);

	/**
	 * 通过标签id，获取标签下的所有商品，结果无序
	 * 
	 * @param tagId - 表情id
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<GoodsTagMpPO> listByTagId(@Param("tagId") long tagId);

}
