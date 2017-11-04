package com.yogu.services.store.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.store.base.entry.GoodsTagMpPO;
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
	
	public void deleteById(@Param("goodsId") long goodsId);
	
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
	
	/**
	 * 分页获取商品信息，结果按照创建时间倒序排序
	 * 
	 * @param lastTime -上一条商品创建时间（为空标示不查）
	 * @param pageSize - 每页大小
	 * @param status - 商品状态
	 * @return 符合的记录列表，若无，返回empty list
	 */
	public List<GoodsPO> listByPage(@Param("lastTime") Long lastTime, @Param("pageSize") int pageSize, @Param("status") short status);
	
	/**
	 * 通过商品名称模糊查询获取商品信息息，结果按照创建时间倒序排序
	 * 
	 * @param goodsName - 商品名称
	 * @param lastTime -上一条商品创建时间（为空标示不查）
	 * @param pageSize - 每页大小
	 * @param status - 商品状态
	 * @return 符合的记录列表，若无，返回empty list
	 */
	public List<GoodsPO> listByName(@Param("goodsName") String goodsName, @Param("lastTime") Long lastTime, @Param("pageSize") int pageSize, @Param("status") short status);
	
	/**
	 * 关联标签表，查询指定标签下的商品信息
	 * @author qiujun
	 * @date 2017年10月9日 上午11:35:23 
	 * 
	 * @param tagId - 标签id
	 * @param pageSize - 每页大小
	 * @param offset - 分页下标
	 * @param orderBy - 排序方式 ,1-无序；2-按照价格倒序；3-按照价格升序
	 * @param status - 商品状态
	 * @return 符合的记录列表，若无，返回empty list
	 */
	public List<GoodsPO> listByTagId(@Param("tagId") long tagId, @Param("pageSize") int pageSize, @Param("offset") int offset,  @Param("orderBy") int orderBy, @Param("status") short status);

	public List<GoodsPO> listByKey(@Param("goodsKeys") List<Long> goodsKeys);
	
	public List<GoodsPO> listByLikeName(@Param("goodsName") String goodsName, @Param("offset") int offset, @Param("pageSize") int pageSize);
	
	/**
     * 修改菜品的上下架状态和排序值，即时生效，但对修改前已经下的订单不会产生影响。
     * 只能修改自己店的数据，角色限制：店主、管理员（待定）
     * 如果角色不对，返回没有权限的错误。
     * @param storeId 菜品ID
     * @param status 需要修改的上下架状态。
     *             1表示正常上架，2表示下架。
     * @param sequence 排序值
     * @return result.success=true为成功，result.object为null
     */
	int updateStatus(@Param("goodsId") long goodsId, @Param("status") short status);

}
