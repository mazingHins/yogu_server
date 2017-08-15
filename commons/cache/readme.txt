
缓存基本服务包，包括：
Memcache Cache
Eh Cache
两层缓存（支持通知）


增加类：redis.clients.jedis.MazingJedisCluster
尝试让批量操作根据落点node来分组
暂时没有用


新增了两个类
	redis.clients.jedis.AutoJedisSentinelPool
	redis.clients.jedis.AutoJedisShardedSentinelPool


