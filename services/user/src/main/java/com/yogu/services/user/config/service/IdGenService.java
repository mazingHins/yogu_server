package com.yogu.services.user.config.service;

/**
 * ID生成表，所有数字ID由ID服务统一生成 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface IdGenService {

	/**
	 * 生成下一段ID的范围，一次取一批ID，而不是一个。 ID的多少，由 IdGenPO.fetchLen 决定。
	 * 
	 * @param idName - ID名
	 * @return 如果idName存在，返回 [start, end]，否则返回null
	 */
	public long[] genNextIdRange(String idName);

}
