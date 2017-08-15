package redis.clients.jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;
import redis.clients.util.Sharded;

/**
 * 根据jedis源码：redis.clients.jedis.JedisSentinelPool更改而来，更改了以下地方：<br>
 * 1：返回的是《分片对象》ShardedJedis<br>
 * 2：不必指定masterName，会自动发现（不一样会抛异常）<br>
 * 3：更换master的时候，加上锁（因为有多个Sentinel的情况，会收到多次通知）<br>
 * 
 * @author jfan 2016年10月28日 下午5:56:18
 */
public class AutoJedisShardedSentinelPool extends Pool<ShardedJedis> {

  private static final Logger log = LoggerFactory.getLogger(AutoJedisShardedSentinelPool.class);

  protected GenericObjectPoolConfig poolConfig;

  protected int connectionTimeout = Protocol.DEFAULT_TIMEOUT;
  protected int soTimeout = Protocol.DEFAULT_TIMEOUT;

  protected String password;

  protected int database = Protocol.DEFAULT_DATABASE;

  protected String clientName;

  protected Set<MasterListener> masterListeners = new HashSet<MasterListener>();

  private Object lock = new Object();// static ? volatile ?
  private volatile ShardedJedisFactory factory;
  private volatile List<HostAndPort> currentHostMasters;

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig) {
    this(sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, null,
        Protocol.DEFAULT_DATABASE);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels) {
    this(sentinels, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, null,
        Protocol.DEFAULT_DATABASE);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels, String password) {
    this(sentinels, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, password);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, int timeout, final String password) {
    this(sentinels, poolConfig, timeout, password, Protocol.DEFAULT_DATABASE);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, final int timeout) {
    this(sentinels, poolConfig, timeout, null, Protocol.DEFAULT_DATABASE);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, final String password) {
    this(sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, password);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, int timeout, final String password,
      final int database) {
    this(sentinels, poolConfig, timeout, timeout, password, database);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, int timeout, final String password,
      final int database, final String clientName) {
    this(sentinels, poolConfig, timeout, timeout, password, database, clientName);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, final int timeout, final int soTimeout,
      final String password, final int database) {
    this(sentinels, poolConfig, timeout, soTimeout, password, database, null);
  }

  public AutoJedisShardedSentinelPool(List<String> sentinels,
      final GenericObjectPoolConfig poolConfig, final int connectionTimeout, final int soTimeout,
      final String password, final int database, final String clientName) {
    this.poolConfig = poolConfig;
    this.connectionTimeout = connectionTimeout;
    this.soTimeout = soTimeout;
    this.password = password;
    this.database = database;
    this.clientName = clientName;

    List<HostAndPort> masters = initSentinels(sentinels);
    initPool(masters);
  }

  public void destroy() {
    for (MasterListener m : masterListeners) {
      m.shutdown();
    }

    super.destroy();
  }

  public List<HostAndPort> getCurrentHostMasters() {
    return currentHostMasters;
  }

  private void initPool(List<HostAndPort> masters) {
    if (!(equals(currentHostMasters, masters))) {
      currentHostMasters = masters;
      log.info("Created ShardedSentinelJedisPool to masters at [" + arr2str(masters) + "]");

      if (null == factory) {
        List<JedisShardInfo> shards = giveShards(masters);
        factory = new ShardedJedisFactory(shards, Hashing.MURMUR_HASH, null);
        initPool(poolConfig, factory);
      } else {
        List<JedisShardInfo> shards = giveShards(masters);
        factory.setShards(shards);
        // although we clear the pool, we still have to check the
        // returned object
        // in getResource, this call only clears idle instances, not
        // borrowed instances
        internalPool.clear();
      }
    }
  }

  private List<JedisShardInfo> giveShards(List<HostAndPort> masters) {
    List<JedisShardInfo> shards = new ArrayList<>();
    for(HostAndPort master : masters) {
      JedisShardInfo info = new JedisShardInfo(master.getHost(), master.getPort(), connectionTimeout, soTimeout, Sharded.DEFAULT_WEIGHT);
      info.setPassword(password);
  	  shards.add(info);
    }
    return shards;
  }

  private boolean equals(Collection<HostAndPort> arr1, Collection<HostAndPort> arr2) {
    if (null != arr1 && null != arr2 && arr1.size() == arr2.size()) {
  	  // set1 2 stringSet
      Set<String> tmp = new HashSet<>();
      for (HostAndPort hap : arr1)
        tmp.add(hap.toString());
      // contain
      for (HostAndPort hap : arr2)
        if(!(tmp.contains(hap.toString())))
          return false;
      // size consistent
      if (arr1.size() == arr2.size())
        return true;
    }
    return false;
  }

  private <T> String arr2str(Collection<T> set) {
    int index = 0;
    StringBuilder sb = new StringBuilder();
    for (T t : set)
      sb.append(index++ > 0 ? ", " : "").append(t.toString());
    return sb.toString();
  }

  private List<HostAndPort> initSentinels(List<String> sentinels) {

    boolean sentinelAvailable = false;
    Set<String> masterNames = new HashSet<>();
    List<HostAndPort> masters = new LinkedList<>();
    Map<String, HostAndPort> tmpCache = new HashMap<>();
    log.info("Trying to find master from available Sentinels...");

    for (String sentinel : sentinels) {
      final HostAndPort hap = HostAndPort.parseString(sentinel);

      log.info("Connecting to Sentinel " + hap);

      Jedis jedis = null;
      try {
        jedis = new Jedis(hap.getHost(), hap.getPort());

        // connected to sentinel...
        sentinelAvailable = true;

        List<Map<String, String>> sentinelMasters = jedis.sentinelMasters();

        if (CollectionUtils.isEmpty(sentinelMasters)) {
          log.warn("Can connect to sentinel, but no master redis node returns...");
          continue;
        }

        for (Map<String, String> sentinelMaster : sentinelMasters) {
          String masterName = sentinelMaster.get("name");
          String portStr = sentinelMaster.get("port");
          String host = sentinelMaster.get("ip");
          HostAndPort master = new HostAndPort(host, Integer.parseInt(portStr));

          if (masterNames.contains(masterName)) {
            HostAndPort tmp = tmpCache.get(masterName);// tmp Should not be null
            if (!(tmp.equals(master)))
              throw new JedisException("Can be connected to the outpost"
                  + ", But with the same '" + masterName + "', there are different nodes: (" + tmp + ") <=/=> (" + master + ")");
            continue;
          }

          log.info("Found Redis master at '" + masterName + "' -> (" + master + ").");
          tmpCache.put(masterName, master);
          masterNames.add(masterName);
          masters.add(master);
        }
      } catch (JedisException e) {
        // resolves #1036, it should handle JedisException there's another chance
        // of raising JedisDataException
        log.warn("Cannot get master address from sentinel running @ " + hap + ". Reason: " + e
            + ". Trying next one.");
      } finally {
        if (jedis != null) {
          jedis.close();
        }
      }
    }

    if (CollectionUtils.isEmpty(masters)) {
      if (sentinelAvailable) {
        // can connect to sentinel, but master name seems to not
        // monitored
        throw new JedisException("Can connect to sentinel, but there is no available master redis...");
      } else {
        throw new JedisConnectionException("All sentinels down, cannot determine where all master is running...");
      }
    }

    log.info("Redis master running at [" + arr2str(masters) + "], starting Sentinel listeners...");

    for (String sentinel : sentinels) {
      final HostAndPort hap = HostAndPort.parseString(sentinel);
      MasterListener masterListener = new MasterListener(masterNames, hap.getHost(), hap.getPort());
      // whether MasterListener threads are alive or not, process can be stopped
      masterListener.setDaemon(true);
      masterListeners.add(masterListener);
      masterListener.start();
    }

    return masters;
  }

  @Override
  public ShardedJedis getResource() {
    ShardedJedis jedis = super.getResource();
    jedis.setDataSource(this);
    return jedis;
  }

  /**
   * @deprecated starting from Jedis 3.0 this method will not be exposed. Resource cleanup should be
   *             done using @see {@link redis.clients.jedis.Jedis#close()}
   */
  @Override
  @Deprecated
  public void returnBrokenResource(final ShardedJedis resource) {
    if (resource != null) {
      returnBrokenResourceObject(resource);
    }
  }

  /**
   * @deprecated starting from Jedis 3.0 this method will not be exposed. Resource cleanup should be
   *             done using @see {@link redis.clients.jedis.Jedis#close()}
   */
  @Override
  @Deprecated
  public void returnResource(final ShardedJedis resource) {
    if (resource != null) {
      resource.resetState();
      returnResourceObject(resource);
    }
  }

  /**
   * PoolableObjectFactory custom impl.
   */
  private static class ShardedJedisFactory implements PooledObjectFactory<ShardedJedis> {
    private List<JedisShardInfo> shards;
    private Hashing algo;
    private Pattern keyTagPattern;

    public ShardedJedisFactory(List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
      this.shards = shards;
      this.algo = algo;
      this.keyTagPattern = keyTagPattern;
    }

    @Override
    public PooledObject<ShardedJedis> makeObject() throws Exception {
      ShardedJedis jedis = new ShardedJedis(shards, algo, keyTagPattern);
      return new DefaultPooledObject<ShardedJedis>(jedis);
    }

    public void setShards(List<JedisShardInfo> shards) {
    	this.shards = shards;
    }

    @Override
    public void destroyObject(PooledObject<ShardedJedis> pooledShardedJedis) throws Exception {
      final ShardedJedis shardedJedis = pooledShardedJedis.getObject();
      for (Jedis jedis : shardedJedis.getAllShards()) {
        try {
          try {
            jedis.quit();
          } catch (Exception e) {

          }
          jedis.disconnect();
        } catch (Exception e) {

        }
      }
    }

    @Override
    public boolean validateObject(PooledObject<ShardedJedis> pooledShardedJedis) {
      try {
        ShardedJedis jedis = pooledShardedJedis.getObject();
        for (Jedis shard : jedis.getAllShards()) {
          if (!shard.ping().equals("PONG")) {
            return false;
          }
        }
        return true;
      } catch (Exception ex) {
        return false;
      }
    }

    @Override
    public void activateObject(PooledObject<ShardedJedis> p) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<ShardedJedis> p) throws Exception {

    }
  }

  protected class MasterListener extends Thread {

    protected Set<String> masterNames;
    protected String host;
    protected int port;
    protected long subscribeRetryWaitTimeMillis = 5000;
    protected volatile Jedis j;
    protected AtomicBoolean running = new AtomicBoolean(false);

    protected MasterListener() {
    }

    public MasterListener(Set<String> masterNames, String host, int port) {
      super(String.format("MasterListener-%s-[%s:%d]", arr2str(masterNames), host, port));
      this.masterNames = masterNames;
      this.host = host;
      this.port = port;
    }

    public MasterListener(Set<String> masterName, String host, int port,
        long subscribeRetryWaitTimeMillis) {
      this(masterName, host, port);
      this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
    }

    @Override
    public void run() {

      running.set(true);

      while (running.get()) {

        j = new Jedis(host, port);

        try {
          // double check that it is not being shutdown
          if (!running.get()) {
            break;
          }

          j.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
              log.info("Sentinel " + host + ":" + port + " published: " + message + ".");

              String[] switchMasterMsg = message.split(" ");

              if (switchMasterMsg.length > 3) {

                if (masterNames.contains(switchMasterMsg[0])) {
                  int port = Integer.parseInt(switchMasterMsg[4]);
                  String host = switchMasterMsg[3];
                  HostAndPort newMaster = new HostAndPort(host, port);
                  port = Integer.parseInt(switchMasterMsg[2]);
                  host = switchMasterMsg[1];
                  HostAndPort oldMaster = new HostAndPort(host, port);

                  int index = currentHostMasters.indexOf(oldMaster);
                  if (-1 < index) {
                    synchronized (lock) {
                      index = currentHostMasters.indexOf(oldMaster);
                      if (-1 < index) {
                        log.info("Original configuration list [" + arr2str(currentHostMasters) + "].");
                        log.info("Fault node replacement: the failure of the node (" + oldMaster + "), used to replace the node (" + newMaster + ").");
                        List<HostAndPort> tmp = new LinkedList<>(currentHostMasters);
                        log.info("The old node (" + oldMaster + ") where the index " + index);
                        tmp.set(index, newMaster);
                        log.info("New configuration list [" + arr2str(tmp) + "].");
                        initPool(tmp);
                      }
                    }
                  }
                } else {
                  log.debug("Ignoring message on +switch-master for master name "
                      + switchMasterMsg[0] + ", our monitor master name are [" + arr2str(masterNames) + "]");
                }

              } else {
                log.error("Invalid message received on Sentinel " + host + ":" + port
                    + " on channel +switch-master: " + message);
              }
            }
          }, "+switch-master");

        } catch (JedisConnectionException e) {

          if (running.get()) {
            log.error("Lost connection to Sentinel at " + host + ":" + port
                + ". Sleeping 5000ms and retrying.", e);
            try {
              Thread.sleep(subscribeRetryWaitTimeMillis);
            } catch (InterruptedException e1) {
              log.error("Sleep interrupted: ", e1);
            }
          } else {
            log.info("Unsubscribing from Sentinel at " + host + ":" + port);
          }
        } finally {
          j.close();
        }
      }
    }

    public void shutdown() {
      try {
        log.info("Shutting down listener on " + host + ":" + port);
        running.set(false);
        // This isn't good, the Jedis object is not thread safe
        if (j != null) {
          j.disconnect();
        }
      } catch (Exception e) {
        log.error("Caught exception while shutting down: ", e);
      }
    }
  }
}