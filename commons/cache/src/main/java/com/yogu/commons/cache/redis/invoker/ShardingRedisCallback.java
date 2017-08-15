package com.yogu.commons.cache.redis.invoker;

import redis.clients.jedis.ShardedJedis;

public interface ShardingRedisCallback<T> {

	public T execute(ShardedJedis shardedJedis);
}
