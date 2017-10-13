package com.yogu.services.store.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.base.entry.GoodsCategoryPO;

/**
 * yg_goods_category 表的操作接口
 * 
 * @author JFan 2015-08-07
 */
@TheDataDao
public interface GoodsCategoryDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(GoodsCategoryPO po);

	/**
	 * 根据主键读取记录
	 */
	public GoodsCategoryPO getById(@Param("categoryId") long categoryId);

	/**
	 * 查询全部记录，结果按照sequence倒序排序
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<GoodsCategoryPO> listAllBySequence();

}
