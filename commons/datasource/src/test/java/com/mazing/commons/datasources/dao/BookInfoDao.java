package com.mazing.commons.datasources.dao;

import java.util.List;

import com.mazing.commons.datasources.entry.BookInfo;
import com.mazing.commons.datasources.entry.BookInfoVO;
import com.yogu.commons.datasource.annocation.TheDataDao;

/**
 * @author red
 * 
 */
@TheDataDao
public interface BookInfoDao {

	/**
	 * 新增书籍
	 * 
	 * @param bi
	 */
	public void insert(BookInfo bi);

	/**
	 * 根据书籍id删除
	 * 
	 * @param id
	 */
	public void delete(String book_id);

	/**
	 * 修改书籍
	 * 
	 * @param bi
	 */
	public void update(BookInfo bi);

	/**
	 * 根据id获取BookInfo
	 * 
	 * @param id
	 * @return
	 */
	public BookInfoVO get(BookInfo bi);

	/**
	 * 通过条形码获取书本列表
	 * 
	 * @param bookId
	 * @return
	 */
	public BookInfoVO getByBookId(String bookId);

	/**
	 * 
	 * @param bi
	 * @return
	 */
	public int count(BookInfo bi);

	/**
	 * 
	 * @param book_name
	 * @return
	 */
	public List<BookInfo> listByName(String book_name);

	/**
	 * 
	 * @param book_name
	 * @return
	 */
	public List<BookInfo> listByName10(String book_name);
}
