/**
 * 
 */
package redis.clients.jedis;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.util.JedisClusterCRC16;

/**
 * 主要是提供getJedisPoolFromKey()方法，用于定位key所对应的jedisPool对象
 * 
 * @author jfan 2016年11月4日 下午1:26:14
 */
public class MazingJedisCluster extends JedisCluster {

	public MazingJedisCluster(HostAndPort node, GenericObjectPoolConfig poolConfig) {
		super(node, poolConfig);
	}

	public MazingJedisCluster(HostAndPort node, int timeout, GenericObjectPoolConfig poolConfig) {
		super(node, timeout, poolConfig);
	}

	public MazingJedisCluster(HostAndPort node, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(node, timeout, maxAttempts, poolConfig);
	}

	public MazingJedisCluster(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(node, connectionTimeout, soTimeout, maxAttempts, poolConfig);
	}

	// public MazingJedisCluster(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String
	// password, GenericObjectPoolConfig poolConfig) {
	// super(node, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
	// }

	// public MazingJedisCluster(HostAndPort node, int connectionTimeout, int soTimeout, int maxAttempts, String
	// password, String clientName, GenericObjectPoolConfig poolConfig) {
	// super(node, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig);
	// }

	public MazingJedisCluster(HostAndPort node, int timeout, int maxAttempts) {
		super(node, timeout, maxAttempts);
	}

	public MazingJedisCluster(HostAndPort node, int timeout) {
		super(node, timeout);
	}

	public MazingJedisCluster(HostAndPort node) {
		super(node);
	}

	public MazingJedisCluster(Set<HostAndPort> nodes, GenericObjectPoolConfig poolConfig) {
		super(nodes, poolConfig);
	}

	public MazingJedisCluster(Set<HostAndPort> nodes, int timeout, GenericObjectPoolConfig poolConfig) {
		super(nodes, timeout, poolConfig);
	}

	public MazingJedisCluster(Set<HostAndPort> jedisClusterNode, int timeout, int maxAttempts, GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, timeout, maxAttempts, poolConfig);
	}

	public MazingJedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts,
			GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, poolConfig);
	}

	public MazingJedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int maxAttempts, String password,
			GenericObjectPoolConfig poolConfig) {
		super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);
	}

	// public MazingJedisCluster(Set<HostAndPort> jedisClusterNode, int connectionTimeout, int soTimeout, int
	// maxAttempts, String password, String clientName, GenericObjectPoolConfig poolConfig) {
	// super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, clientName, poolConfig);
	// }

	public MazingJedisCluster(Set<HostAndPort> nodes, int timeout, int maxAttempts) {
		super(nodes, timeout, maxAttempts);
	}

	public MazingJedisCluster(Set<HostAndPort> nodes, int timeout) {
		super(nodes, timeout);
	}

	public MazingJedisCluster(Set<HostAndPort> nodes) {
		super(nodes);
	}

	// ####

	/**
	 * 根据key获取 应该使用哪个jedis池对象
	 */
	public JedisPool getJedisPoolFromKey(byte[] key) {
		int slot = JedisClusterCRC16.getSlot(key);
		return connectionHandler.cache.getSlotPool(slot);
	}

	/**
	 * 根据key获取 应该使用哪个jedis池对象
	 */
	public JedisPool getJedisPoolFromKey(String key) {
		int slot = JedisClusterCRC16.getSlot(key);
		return connectionHandler.cache.getSlotPool(slot);
	}

}
