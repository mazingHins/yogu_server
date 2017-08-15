/**
 * 
 */
package com.yogu.commons.cache.memcached;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.transcoders.Transcoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.AddCacheService;

/**
 * 基于Memcached集中式缓存服务 <br>
 * <br>
 * 
 * @author JFan - 2014年10月30日 上午11:02:26
 */
//@ManagedBean
@SuppressWarnings("unchecked")
public class MemcachedServiceImpl implements AddCacheService {

	private static final Logger logger = LoggerFactory.getLogger(MemcachedServiceImpl.class);

	private ByteArrayTranscoder transcoder = new ByteArrayTranscoder();

	@Resource(name = "memcachedClient")
	private MemcachedClient memcachedClient;

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public <V> V get(String key) {
		if (null == key) {
			logger.warn("Get MemCache By KEY: Null.");
			return null;
		}

		try {
			Object value = memcachedClient.get(key);
			if (logger.isDebugEnabled())
				logger.debug("Get MemCache, key: {}, value: {}", key, value);
			return (V) value;
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("Get MemCache ERROR, Params. key: {}", key, e);
		}
		return null;
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public boolean isExist(String key) {
		// 集中式缓存，那么就有可能出现get的对象是其他系统放进来的，而自身系统中不存在这个类文件
		// 这里直接获取byte[] ，不为空即表示存在这个缓存
		boolean exist = (getByTranscoder(key, transcoder) != null);
		if (logger.isDebugEnabled())
			logger.debug("IsExist MemCache, key: {}, exist: {}", key, exist);
		return exist;
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public boolean delete(String key) {
		if (null == key) {
			logger.warn("Delete MemCache By key: Null.");
			return true;
		}

		boolean result = false;
		try {
			result = memcachedClient.delete(key);
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("Delete MemCache ERROR. key: {}", key, e);
		}

		if (logger.isDebugEnabled())
			logger.debug("Delete MemCache, key: {}, resule: {}", key, result);
		return result;
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public <V> boolean set(String key, V value) {
		return set(key, value, DEF_EXP);
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public <V> boolean set(String key, V value, int exp) {
		if (null == key || null == value) {
			logger.warn("Set MemCache The NULL. key: {}, value: {}, exp: {}", key, value, exp);
			return false;
		}

		int exp_ = (exp > 0 ? exp : DEF_EXP);
		exp_ = exp_ >= DEF_EXP ? DEF_EXP : exp_;

		try {
			boolean result = memcachedClient.set(key, exp_, value);
			if (logger.isDebugEnabled())
				logger.debug("Set MemCache toCache. key: {}, exp: {}, result: {}, value: {}", key, exp_, result, value);
			return result;
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("Set MemCache ERROR. key: {}, exp: {}, value: {}", key, exp_, value, e);
		}
		return false;
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public <V> boolean add(String key, V value) {
		return add(key, value, DEF_EXP);
	}

	/**
	 * {@inheritDoc} <br>
	 */
	@Override
	public <V> boolean add(String key, V value, int exp) {
		if (null == key || null == value) {
			logger.warn("Add MemCache The NULL Param... key: {}, value: {}, exp: {}.", key, value, exp);
			return false;
		}

		int exp_ = (exp > 0 ? exp : DEF_EXP);
		exp_ = exp_ >= DEF_EXP ? DEF_EXP : exp_;

		try {
			boolean result = memcachedClient.add(key, exp_, value);
			if (logger.isDebugEnabled())
				logger.debug("Add MemCache toCache. key: {}, exp: {}, result: {}, value: {}", key, exp_, result, value);
			return result;
		} catch (TimeoutException | InterruptedException | MemcachedException e) {
			logger.error("Add MemCache ERROR, Params... key: {}, exp: {}, value: {}", key, exp_, value, e);
		}
		return false;
	}

	// ####
	// private func

	/**
	 * 暂时不开放出去
	 */
	private <T> T getByTranscoder(String key, Transcoder<T> transcoder) {
		T object = null;
		if (key != null)
			try {
				object = memcachedClient.get(key, transcoder);
			} catch (TimeoutException | InterruptedException | MemcachedException e) {
				String transName = transcoder.getClass().getSimpleName();
				logger.error("Get MemCache ERROR. key: {}, trans: {}", key, transName, e);
			}
		return object;
	}

	// ####
	// set func

	/**
	 * @param memcachedClient 要设置的 memcachedClient
	 */
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	// ####
	// inner class

	/**
	 * Returns the original data<br>
	 * <br>
	 * 
	 * @author JFan - 2014年10月30日 上午11:03:12
	 */
	private class ByteArrayTranscoder extends SerializingTranscoder {

		/*
		 * （非 Javadoc）
		 * 
		 * @see net.rubyeye.xmemcached.transcoders.BaseSerializingTranscoder#deserialize (byte[])
		 */
		@Override
		protected Object deserialize(byte[] in) {
			return in;
		}

	}

}
