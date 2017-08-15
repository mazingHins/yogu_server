package com.mazing.commons.datasources2;

import java.util.List;

import com.yogu.commons.datasource.annocation.TheDataDao;

/**
 * @author red
 * 
 */
@TheDataDao
public interface BookTypeDao {

	/**
	 * 新增书籍
	 * 
	 * @param bi
	 */
	public void insert(BookType bt);

	/**
	 * 根据书籍id删除
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 修改书籍
	 * 
	 * @param bi
	 */
	public void update(BookType bt);

	/**
	 * 根据id获取BookInfo
	 * 
	 * @param id
	 * @return
	 */
	public BookType get(int id);

	/**
	 * 根据id获取BookInfo
	 * 
	 * @param id
	 * @return
	 */
	public List<BookType> listByParentId(String super_type_id);

	/**
	 * 
	 * @return
	 */
	public int getMaxId();

	/**
	 * 获取按分类名称相同父类ID下面的分类
	 * 
	 * @param bt
	 * @return
	 */
	public BookType getByName(BookType bt);

	/**
	 * 获取所有的分类
	 * 
	 * @return
	 */
	public List<BookType> listAll();
}
