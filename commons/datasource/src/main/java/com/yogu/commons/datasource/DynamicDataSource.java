package com.yogu.commons.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 主从数据库选择 <br>
 * 
 * <br>
 * JFan - 2015年7月13日 下午2:45:16
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		Source source = DBContextHolder.getSource();
		if (null == source)
			return Source.MASTER.getName();

		logger.debug("Data source is : {}", source);
		return source.getName();
	}
}
