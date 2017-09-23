package com.yogu.services.user.config.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.ParameterUtil;
import com.yogu.services.user.config.dao.IdGenDao;
import com.yogu.services.user.config.entry.IdGenPO;
import com.yogu.services.user.config.service.IdGenService;

/**
 * IdGenService 的实现类
 * 
 * @author JFan 2015-07-13
 */
@Named
@Singleton
public class IdGenServiceImpl implements IdGenService {

	private static final Logger logger = LoggerFactory.getLogger(IdGenServiceImpl.class);
	private static Object lock = new Object();

	@Inject
	private IdGenDao dao;

	@Override
	public long[] genNextIdRange(String idName) {
		synchronized (lock) {
			ParameterUtil.assertNotBlank(idName, "idName cannot be null");
			IdGenPO current = dao.getById(idName);
			if (current != null) {
				int n = 0;
				while (n++ < 3) {
					// 最多重试3次
					int rows = dao.updateNextStart(idName);// current.getNextStartId()
					if (rows > 0) {
						long end = current.getNextStartId() + current.getFetchLen() - 1;
						logger.info("gen id success|start=" + current.getNextStartId() + "|end=" + end);
						return new long[] { current.getNextStartId(), end };
					}
				}
				logger.error("gen id fail|idName=" + idName + "|result=重试获取3次失败");
			} else {
				logger.error("gen id fail|idName=" + idName + "|result=这个idName不存在");
			}
			return null;
		}
	}

}
