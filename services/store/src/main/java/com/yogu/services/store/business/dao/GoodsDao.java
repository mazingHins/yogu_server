package com.yogu.services.store.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.business.entry.GoodsPO;

/**
 * yg_goods 表的操作接口
 *
 */
@TheDataDao
public interface GoodsDao {
	
	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(GoodsPO po);

	/**
	 * 根据主键读取记录
	 */
	public GoodsPO getById(@Param("goodsId") long goodsId);
	
	/**
	 * 根据商品key读取记录
	 */
	public GoodsPO getByKey(@Param("goodsKey") long goodsKey);
	
	/**
	 * 根据店铺id，获取店铺下所有指定状态的商品信息，结果按照sequence从小到大排序
	 * 
	 * @param storeId - 店铺id
	 * @param status - 商品状态
	 * @return 符合的记录列表，若无，返回empty list
	 */
	public List<GoodsPO> listByStoreId(@Param("storeId") long storeId, @Param("status") short status);

}
