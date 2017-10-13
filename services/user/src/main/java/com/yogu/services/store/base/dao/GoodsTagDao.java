package com.yogu.services.store.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.base.entry.GoodsCategoryPO;
import com.yogu.services.store.base.entry.GoodsTagPO;

/**
 * yg_goods_tag 表的操作接口
 * 
 * @author JFan 2015-08-07
 */
@TheDataDao
public interface GoodsTagDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(GoodsTagPO po);

	/**
	 * 根据主键读取记录
	 */
	public GoodsTagPO getById(@Param("tagId") long tagId);

	/**
	 * 通过分类id，获取改分类下所有的标签列表，结果按照sequence顺序排序
	 * 
	 * @param categoryId - 分类id
	 * @return 返回符合的数据，如果没有数据，返回emtpy list
	 */
	public List<GoodsTagPO> listByCategoryId(@Param("categoryId") long categoryId);

}
