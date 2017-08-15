package com.yogu.commons.server.mock.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import com.yogu.commons.server.mock.MockManageServlet;
import com.yogu.commons.utils.StringUtils;

@Named
public class MockManageServiceImpl implements MockManageService {

	private static final Logger logger = LoggerFactory.getLogger(MockManageServiceImpl.class);

	private AntPathMatcher antMatcher = new AntPathMatcher();

	private Properties dataMap = new Properties();
	private boolean available = false;
	private String filePath;

    private boolean initial;
    private void initial() {
        String key = MockManageServlet.MOCK_FILE_KEY;
        String file = (null == key ? key : System.getProperty(key));
        if (StringUtils.isBlank(file)) {
            available = false;
            filePath = null;
        } else {
            available = true;
            filePath = file;
            reload();
        }
    }

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.vip.xapi.server.mock.service.MockManageService#isAvailable()
	 */
	@Override
	public boolean isAvailable() {
        if (!initial)
            synchronized (dataMap) {
                initial();
                initial = true;
            }
		return available;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.vip.xapi.server.mock.service.MockManageService#result(java.lang.String)
	 */
	@Override
	public String result(String key) {
		if (!isAvailable() || StringUtils.isBlank(key))
			return null;
		String value = dataMap.getProperty(key);
		if (logger.isDebugEnabled())
			logger.debug("RESULT MOCK key: {}, value: {}.", key, value);
		return value;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.vip.xapi.server.mock.service.MockManageService#select(java.lang.String)
	 */
	@Override
	public Map<String, String> select(String select) {
		if (!isAvailable())
			return null;
		Map<String, String> selectResult = new HashMap<String, String>();

		Set<Object> keySet = dataMap.keySet();
		if (null != keySet) {
			boolean ok = StringUtils.isBlank(select);
			for (Object key : keySet) {
				String keyStr = key.toString();
				if (StringUtils.isBlank(keyStr))
					continue;
				if (ok || antMatcher.matchStart(select, keyStr)) {
					selectResult.put(keyStr, dataMap.getProperty(keyStr));
				}
			}
		}

		return selectResult;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.vip.xapi.server.mock.service.MockManageService#add(java.lang.String, java.lang.String)
	 */
	@Override
	public void add(String key, String value) {
		if (!isAvailable() || StringUtils.isBlank(key))
			return;
		if (logger.isDebugEnabled())
			logger.debug("ADD MOCK key: {}, value: {}.", key, value);
		dataMap.setProperty(key, value);
		out();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.vip.xapi.server.mock.service.MockManageService#delete(java.lang.String)
	 */
	@Override
	public void delete(String key) {
		if (!isAvailable() || StringUtils.isBlank(key))
			return;
		if (logger.isDebugEnabled())
			logger.debug("DELETE MOCK key: {}.", key);
		dataMap.remove(key);
		out();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.vip.xapi.server.mock.service.MockManageService#reload()
	 */
	@Override
	public void reload() {
		if (StringUtils.isBlank(filePath))
			return;
		InputStream inputStream = null;
		try {
			File file = new File(filePath);
			if (!file.exists())
				file.createNewFile();
			if (file.isDirectory())
				throw new RuntimeException("This is a directory '" + filePath + "', you need to specify a specific file.");

			inputStream = new FileInputStream(file);
			Properties tmp = new Properties();
			tmp.load(inputStream);

			dataMap = tmp;
		} catch (Exception e) {
			logger.error("could not found mock data properties");
		} finally {
			if (null != inputStream)
				try {
					inputStream.close();
				} catch (IOException e) {
					// ignore
				}
		}
	}

	private void out() {
		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			dataMap.store(fos, null);
		} catch (Exception e) {
			logger.error("OUT MOCK ERROR, file: {}.", filePath, e);
		}
	}

}
