package com.yogu.commons.cache.redis.invoker;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {
	public abstract T execute(Jedis jedis);
	
}

