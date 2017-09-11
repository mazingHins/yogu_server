/**
 * 
 */
package com.yogu.commons.cache.redis;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.CacheTranscoder;
import com.yogu.commons.cache.redis.impl.RedisClusterImpl;
import com.yogu.commons.cache.redis.impl.RedisImpl;
import com.yogu.commons.cache.redis.impl.RedisShardingImpl;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.SerializeUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;

import redis.clients.jedis.AutoJedisSentinelPool;
import redis.clients.jedis.AutoJedisShardedSentinelPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.MazingJedisCluster;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

/**
 * 根据配置进行Redis的实例化 工厂类 <br>
 *
 * @author JFan 2015年11月16日 下午6:38:55
 */
public class RedisFactory extends JedisPoolConfig {

	private static final Logger logger = LoggerFactory.getLogger(RedisFactory.class);
	private static final int DEFAULT_MAX_TOTAL = 128;
	private static final int DEFAULT_TIMEOUT = 3000;
	private static final int DEFAULT_WEIGHT = 1;// 权重，只针对多个实例的redis有效
	private static final int DEFAULT_MAX_REDIRECTIONS = 5;

	private int soTimeout;
	private int connectionTimeout;
	private CacheTranscoder transcoder;

	public RedisFactory() {
		setMaxTotal(DEFAULT_MAX_TOTAL);
		setMaxIdle(DEFAULT_MAX_TOTAL);// 最大空闲连接数（默认与最大连接数一致）
		setSoTimeout(DEFAULT_TIMEOUT);// 超时时间,默认3秒;
		setConnectionTimeout(DEFAULT_TIMEOUT);// 连接超时时间,默认3秒;
	}

	/**
	 * 常规形式初始化Redis并得到一个实例
	 * 
	 * @param server redis服务器配置；<br>
	 *            配置格式如下：[password@]host[:port] ---- 密码可选、端口不填则默认6379<br>
	 *            如果有多个server，以#分隔 ----> server1 # server2
	 * @return 返回Redis实例
	 */
	public Redis initRedis(String server) {
		return initRedis(server, false, false, 0);
	}

	/**
	 * 哨兵模式初始化Redis并得到一个实例
	 * 
	 * @param server redis服务器配置；<br>
	 *            配置格式如下：[password@]host[:port] ---- 密码可选、端口不填则默认6379<br>
	 *            如果有多个server，以#分隔 ----> server1 # server2
	 * @return 返回Redis实例
	 */
	public Redis initRedisSentinel(String server) {
		return initRedis(server, false, true, 0);
	}

	/**
	 * 集群模式初始化Redis并得到一个实例
	 * 
	 * @param server redis服务器配置；<br>
	 *            配置格式如下：[password@]host[:port] ---- 密码可选、端口不填则默认6379<br>
	 *            如果有多个server，以#分隔 ----> server1 # server2
	 * @param useClusterModel 是否使用集群模式
	 * @return 返回Redis实例
	 */
	public Redis initRedisCluster(String server) {
		return initRedis(server, true, false, 0);
	}

	/**
	 * 初始化Redis并得到一个实例
	 * 
	 * @param server redis服务器配置；<br>
	 *            配置格式如下：[password@]host[:port] ---- 密码可选、端口不填则默认6379<br>
	 *            如果有多个server，以#分隔 ----> server1 # server2
	 * @param useClusterModel 是否使用集群模式
	 * @param useSentinelModel 是否启用哨兵模式（在useClusterModel=false的情况下）
	 * @param initPoolSize 初始化的连接数
	 * @return 返回Redis实例
	 */
	public Redis initRedis(String server, boolean useClusterModel, boolean useSentinelModel, int initPoolSize) {
		// 解密配置，并解读配置
		LinkedList<JedisShardInfo> servers = splitServerConfig(server);
		// 得到redis实例
		// int max = getMaxActive();
		int max = getMaxTotal();
		// 序列化、反序列处理器
		CacheTranscoder transcoder = giveTranscoder();
		return init0(servers, useClusterModel, useSentinelModel, transcoder, max, initPoolSize);
	}

	/**
	 * 获取单实例的RedisImpl
	 * 
	 * 此方法是特殊的地方需要用到，其他地方请勿使用
	 */
	public RedisImpl giveOneRedis(JedisShardInfo server) {
		int soTimeout = server.getSoTimeout();
		int connectionTimeout = server.getConnectionTimeout();
		// JedisPool jedisPool = new JedisPool(this, server.getHost(), server.getPort(), connectionTimeout, soTimeout,
		// server.getPassword(), Protocol.DEFAULT_DATABASE, null);
		URI uri = giveUri(server);
		logger.debug("redis#factory | {}", uri);
		JedisPool jedisPool = new JedisPool(this, uri, connectionTimeout, soTimeout);
		return new RedisImpl(jedisPool, transcoder, getMaxTotal(), 0);
	}

	/**
	 * 根据配置获取一个连接池<br>
	 * 如果只有一个server，则使用简单实现<br>
	 * 如果存在多个server，则使用分片实现
	 */
	private Redis init0(LinkedList<JedisShardInfo> servers, //
			boolean useClusterModel, boolean useSentinelModel, CacheTranscoder transcoder, int maxActive, int initPoolSize) {
		Args.check(CollectionUtils.isNotEmpty(servers), "'server'");
		// ##############################################
		// 集群模式
		if (useClusterModel) {
			return initByCluster(servers);
		}
		// ##########################################
		// 常规模式
		else {
//			if (useSentinelModel)
//				return initBySentinel(servers, maxActive, initPoolSize);
			return initByDef(servers, maxActive, initPoolSize);
		}
	}

	private Redis initByCluster(LinkedList<JedisShardInfo> servers) {
		// TODO 我要FK一下，如果redis实例设置了pass，难道JedisCluster不支持么。。。靠。。。后面的暂时忽略pass

		// config
		JedisShardInfo server = servers.get(0);
		int soTimeout = server.getSoTimeout();
		int maxAttempts = DEFAULT_MAX_REDIRECTIONS;
		int connectionTimeout = server.getConnectionTimeout();

		MazingJedisCluster jedis = null;
		// 单机
		if (1 == servers.size()) {
			HostAndPort node = giveHAP(server);
			logger.info("redis#factory | Instance: Single machine cluster");
			jedis = new MazingJedisCluster(node, connectionTimeout, soTimeout, maxAttempts, this);
		}
		// 多机
		else {
			Set<HostAndPort> nodes = new HashSet<>();
			for (JedisShardInfo info : servers)
				nodes.add(giveHAP(info));
			logger.info("redis#factory | Instance: multi machine cluster");
			jedis = new MazingJedisCluster(nodes, connectionTimeout, soTimeout, maxAttempts, this);
		}
		return new RedisClusterImpl(jedis);
	}

	private Redis initByDef(LinkedList<JedisShardInfo> servers, int maxActive, int initPoolSize) {
		// 单机
		if (1 == servers.size()) {
			JedisShardInfo server = servers.get(0);
			int soTimeout = server.getSoTimeout();
			int connectionTimeout = server.getConnectionTimeout();
			// JedisPool jedisPool = new JedisPool(this, server.getHost(), server.getPort(), connectionTimeout,
			// soTimeout, server.getPassword(), Protocol.DEFAULT_DATABASE, null);
			URI uri = giveUri(server);
			JedisPool jedisPool = new JedisPool(this, uri, connectionTimeout, soTimeout);
			logger.info("redis#factory | Instance: Single machine");
			return new RedisImpl(jedisPool, transcoder, maxActive, initPoolSize);
		}
		// 多机
		else {
			ShardedJedisPool shardedJedisPool = new ShardedJedisPool(this, servers, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
			logger.info("redis#factory | Instance: multi machine");
			return new RedisShardingImpl(shardedJedisPool, transcoder, maxActive, initPoolSize);
		}
	}

	private Redis initBySentinel(LinkedList<JedisShardInfo> servers, int maxActive, int initPoolSize) {
		JedisShardInfo server = servers.get(0);
		int connectionTimeout = server.getConnectionTimeout();
		String password = server.getPassword();
		int soTimeout = server.getSoTimeout();

		// 单机
		if (1 == servers.size()) {
			Set<String> sentinels = new HashSet<String>();
			sentinels.add(server.getHost() + ":" + server.getPort());

			AutoJedisSentinelPool jedisPool = new AutoJedisSentinelPool(sentinels, this, connectionTimeout, soTimeout, password,
					Protocol.DEFAULT_DATABASE);
			logger.info("redis#factory | Instance: Sentinel Single machine");
			return new RedisImpl(jedisPool, transcoder, maxActive, initPoolSize);
		}
		// 多机
		else {
			List<String> sentinels = new LinkedList<String>();
			for (JedisShardInfo info : servers)
				sentinels.add(info.getHost() + ":" + info.getPort());

			AutoJedisShardedSentinelPool shardedJedisPool = new AutoJedisShardedSentinelPool(sentinels, this, soTimeout, password,
					Protocol.DEFAULT_DATABASE);
			logger.info("redis#factory | Instance: sentinel multi machine");
			return new RedisShardingImpl(shardedJedisPool, transcoder, maxActive, initPoolSize);
		}
	}

	private HostAndPort giveHAP(JedisShardInfo server) {
		return new HostAndPort(server.getHost(), server.getPort());
	}

	// redis.uri的格式：redis://[密码]@[服务器地址]:[端口]/[db index]
	private URI giveUri(JedisShardInfo server) {
		StringBuilder sb = new StringBuilder();
		sb.append("redis://");// 暂时不考虑ssl的形式（rediss://）
		if (StringUtils.isNotBlank(server.getPassword()))
			sb.append(server.getPassword()).append("@");
		sb.append(server.getHost());
		if (0 < server.getPort())
			sb.append(":").append(server.getPort());
		if (0 < server.getDb())
			sb.append("/").append(server.getDb());

		try {
			return new URI(sb.toString());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解读server配置<br>
	 * 
	 * 配置格式如下：[password@]host[:port] ---- 密码可选、端口不填则默认6379<br>
	 * 如果有多个server，以#分隔 ----> server1 # server2<br>
	 */
	public LinkedList<JedisShardInfo> splitServerConfig(String config) {
		// 解密配置
		String server = new DesPropertiesEncoder().decode(config);

		LinkedList<JedisShardInfo> serverList = new LinkedList<>();

		String[] servers = StringUtils.split(server, '#');
		int index = 1;
		for (String tmp : servers) {
			String serverConf = StringUtils.trimToNull(tmp);
			if (null == serverConf)
				continue;
			JedisShardInfo info = readServer(serverConf, index);
			String string = LogUtil.hidePassport(info.getHost()) + ":" + info.getPort();
			logger.info("redis#factory | server{} | {}", index, string);
			serverList.add(info);
			index++;
		}

		return serverList;
	}

	/**
	 * 读取单个服务的配置，并构建JedisShardInfo对象<br>
	 * 
	 * @param config 单个服务器的配置（一定不为null）格式为：[password@]host[:port] >> 密码可选、端口不填则默认6379
	 */
	private JedisShardInfo readServer(String config, int index) {
		// 分解配置：密码@server
		String[] serverInfo = StringUtils.split(config, '@');
		int length = serverInfo.length;
		if (2 < length)
			throw new RuntimeException("redis服务器配置不正确（[auth@]host:port）：" + config);
		String auth = (2 == length ? serverInfo[0] : null);
		String server = (2 == length ? serverInfo[1] : serverInfo[0]);

		// 分解host配置：host[:port]
		String[] hostInfo = StringUtils.split(server, ':');
		int hLength = hostInfo.length;
		if (2 < hLength)
			throw new RuntimeException("redis服务器配置不正确（host[:port]）：" + server);
		String host = hostInfo[0];

		// 端口配置，有可能为null，为null时使用默认6379端口
		String portStr = (2 == hLength ? hostInfo[1] : null);
		if (null != portStr && !(StringUtils.isNumeric(portStr)))
			throw new RuntimeException("redis端口配置不正确（int）：" + portStr);

		int port = (null == portStr ? 6379 : Integer.parseInt(portStr));

		JedisShardInfo info = new JedisShardInfo(host, port, connectionTimeout, soTimeout, DEFAULT_WEIGHT);
		if (null != auth)
			info.setPassword(auth);
		return info;
	}

	private CacheTranscoder giveTranscoder() {
		if (null != transcoder)
			return transcoder;

		// 默认序列化、反序列化处理器
		return new CacheTranscoder() {

			@Override
			public byte[] serialize(Object obj) {
				try {
					return SerializeUtil.serialize(obj);
				} catch (Exception e) {
					throw new IllegalArgumentException("Non-serializable object", e);
				}
			}

			@Override
			public Object deserialize(byte[] bs) {
				try {
					return SerializeUtil.unserialize(bs);
				} catch (Exception e) {
					throw new IllegalArgumentException("Non-deserialize object", e);
				}
			}

		};
	}

	// ####
	// ## set func
	// ####

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @param transcoder 要设置的 transcoder
	 */
	public void setTranscoder(CacheTranscoder transcoder) {
		this.transcoder = transcoder;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

}
