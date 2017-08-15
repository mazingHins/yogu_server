package com.yogu.commons.cache.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PreDestroy;

import com.yogu.commons.cache.redis.LoadingData;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.JsonUtils;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.MazingJedisCluster;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

public class RedisClusterImpl implements Redis {

	private MazingJedisCluster jedis;

	public RedisClusterImpl(MazingJedisCluster jedis) {
		this.jedis = jedis;
	}

	@PreDestroy
	public void destroy() {
		try {
			jedis.close();
		} catch (Exception e) {
			// ignore
		}
	}

	@Override
	public void set(String key, Object value) {
		set(key, value, defTimeout);
	}

	@Override
	public void set(String key, Object value, int seconds) {
		String string = null;
		if (null != value)// value为null，则set null进去
			string = JsonUtils.toJSONString(value);
		setex(key, seconds, string);
	}

	@Override
	public <T> T get(String key, Class<T> clz) {
		String value = get(key);
		if (null == value)// 这里只判断null，如果value是一个空字符串，那应该是set出了问题
			return null;
		return JsonUtils.parseObject(value, clz);
	}

	@Override
	public <K, V> List<V> mget(List<K> keys, LoadingData<K, V> load) {
		List<V> result = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(keys)) {
			int size = keys.size();
			// 先批量去cache中取值
			byte[][] tmp = new byte[size][];
			for (int index = 0; index < size; index++) {
				K key = keys.get(index);
				String redisKey = load.toRedisKey(key);
				tmp[index] = redisKey.getBytes();
			}
			List<V> cacheList = mget(tmp);
			// 检查没有取到的，单独加载
			for (int index = 0; index < size; index++) {
				V value = cacheList.get(index);
				// 没有装载到的
				if (null == value) {
					K key = keys.get(index);
					value = load.sourceData(key);
				}
				result.add(value);
			}
		}
		return result;
	}

	@Override
	public <T> List<T> mget(byte[]... keys) {
		return RedisHandler.mgetByCluster(jedis, keys);
	}

	@Override
	public List<String> mget(String... keys) {
		return RedisHandler.mgetByCluster(jedis, keys);
	}

	@Override
	public String set(String key, String value, String nxxx, String expx, long time) {
		return jedis.set(key, value, nxxx, expx, time);
	}

	@Override
	@Deprecated
	public String set(String key, String value, String nxxx) {
		return jedis.set(key, value, nxxx);
	}

	@Override
	public Long persist(String key) {
		return jedis.persist(key);
	}

	@Override
	public Long pexpire(String key, long milliseconds) {
		return jedis.pexpire(key, milliseconds);
	}

	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		return jedis.pexpireAt(key, millisecondsTimestamp);
	}

	@Override
	public Long pttl(String key) {
		return jedis.pttl(key);
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		return jedis.setbit(key, offset, value);
	}

	@Override
	public String psetex(String key, long milliseconds, String value) {
		return jedis.psetex(key, milliseconds, value);
	}

	@Override
	public Double incrByFloat(String key, double value) {
		return jedis.incrByFloat(key, value);
	}

	@Override
	public Double hincrByFloat(String key, String field, double value) {
		return jedis.hincrByFloat(key, field, value);
	}

	@Override
	public Set<String> spop(String key, long count) {
		return jedis.spop(key, count);
	}

	@Override
	public List<String> srandmember(String key, int count) {
		return jedis.srandmember(key, count);
	}

	@Override
	public Long strlen(String key) {
		return jedis.strlen(key);
	}

	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		return jedis.zadd(key, score, member, params);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
		return jedis.zadd(key, scoreMembers, params);
	}

	@Override
	public Double zincrby(String key, double score, String member, ZIncrByParams params) {
		return jedis.zincrby(key, score, member, params);
	}

	@Override
	public Long zlexcount(String key, String min, String max) {
		return jedis.zlexcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		return jedis.zrangeByLex(key, min, max);
	}

	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		return jedis.zrangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		return jedis.zrevrangeByLex(key, max, min);
	}

	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		return jedis.zrevrangeByLex(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		return jedis.zremrangeByLex(key, min, max);
	}

	@Override
	@Deprecated
	public List<String> blpop(String arg) {
		return jedis.blpop(arg);
	}

	@Override
	public List<String> blpop(int timeout, String key) {
		return jedis.blpop(timeout, key);
	}

	@Override
	@Deprecated
	public List<String> brpop(String arg) {
		return jedis.brpop(arg);
	}

	@Override
	public List<String> brpop(int timeout, String key) {
		return jedis.brpop(timeout, key);
	}

	@Override
	public String echo(String string) {
		return jedis.echo(string);
	}

	@Override
	@Deprecated
	public Long move(String key, int dbIndex) {
		return jedis.move(key, dbIndex);
	}

	@Override
	public Long bitcount(String key) {
		return jedis.bitcount(key);
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		return jedis.bitcount(key, start, end);
	}

	@Override
	public Long bitpos(String key, boolean value) {
		return jedis.bitpos(key, value);
	}

	@Override
	public Long bitpos(String key, boolean value, BitPosParams params) {
		return jedis.bitpos(key, value, params);
	}

	@Override
	@Deprecated
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		return jedis.hscan(key, cursor);
	}

	@Override
	@Deprecated
	public ScanResult<String> sscan(String key, int cursor) {
		return jedis.sscan(key, cursor);
	}

	@Override
	@Deprecated
	public ScanResult<Tuple> zscan(String key, int cursor) {
		return jedis.zscan(key, cursor);
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		return jedis.hscan(key, cursor);
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
		return jedis.hscan(key, cursor, params);
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		return jedis.sscan(key, cursor);
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
		return jedis.sscan(key, cursor, params);
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		return jedis.zscan(key, cursor);
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
		return jedis.zscan(key, cursor, params);
	}

	@Override
	public Long pfadd(String key, String... elements) {
		return jedis.pfadd(key, elements);
	}

	@Override
	public long pfcount(String key) {
		return jedis.pfcount(key);
	}

	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		return jedis.geoadd(key, longitude, latitude, member);
	}

	@Override
	public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
		return jedis.geoadd(key, memberCoordinateMap);
	}

	@Override
	public Double geodist(String key, String member1, String member2) {
		return jedis.geodist(key, member1, member2);
	}

	@Override
	public Double geodist(String key, String member1, String member2, GeoUnit unit) {
		return jedis.geodist(key, member1, member2, unit);
	}

	@Override
	public List<String> geohash(String key, String... members) {
		return jedis.geohash(key, members);
	}

	@Override
	public List<GeoCoordinate> geopos(String key, String... members) {
		return jedis.geopos(key, members);
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit) {
		return jedis.georadius(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit,
			GeoRadiusParam param) {
		return jedis.georadius(key, longitude, latitude, radius, unit, param);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
		return jedis.georadiusByMember(key, member, radius, unit);
	}

	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit, GeoRadiusParam param) {
		return jedis.georadiusByMember(key, member, radius, unit, param);
	}

	@Override
	public List<Long> bitfield(String key, String... arguments) {
		return jedis.bitfield(key, arguments);
	}

	@Override
	public Long append(String key, String value) {
		return jedis.append(key, value);
	}

	@Override
	public String set(String key, String value) {
		return jedis.set(key, value);
	}

	@Override
	public String set(byte[] key, byte[] value) {
		return jedis.set(key, value);
	}

	@Override
	public Long del(String key) {
		return jedis.del(key);
	}

	@Override
	public Long del(byte[] key) {
		return jedis.del(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedis.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedis.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return jedis.incr(key);
	}

	@Override
	public String get(String key) {
		return jedis.get(key);
	}

	@Override
	public byte[] get(byte[] key) {
		return jedis.get(key);
	}

	@Override
	public String getSet(String key, String value) {
		return jedis.getSet(key, value);
	}

	@Override
	public Long zcard(String key) {
		return jedis.zcard(key);
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		return jedis.zrevrangeWithScores(key, start, end);
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		return jedis.zrevrange(key, start, end);
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		return jedis.zrange(key, start, end);
	}

	@Override
	public Long zadd(String key, double score, String member) {
		return jedis.zadd(key, score, member);
	}

	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		return jedis.zadd(key, scoreMembers);
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		return jedis.setrange(key, offset, value);
	}

	@Override
	public Long srem(String key, String... member) {
		return jedis.srem(key, member);
	}

	@Override
	public Boolean exists(String key) {
		return jedis.exists(key);
	}

	@Override
	public String type(String key) {
		return jedis.type(key);
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		return jedis.expireAt(key, unixTime);
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		return jedis.setbit(key, offset, value);
	}

	@Override
	public Boolean getbit(String key, long offset) {
		return jedis.getbit(key, offset);
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		return jedis.getrange(key, startOffset, endOffset);
	}

	@Override
	public Long setnx(String key, String value) {
		return jedis.setnx(key, value);
	}

	@Override
	public String setex(String key, int seconds, String value) {
		return jedis.setex(key, seconds, value);
	}

	@Override
	public String setex(byte[] key, int seconds, byte[] value) {
		return jedis.setex(key, seconds, value);
	}

	@Override
	public Long decrBy(String key, long integer) {
		return jedis.decrBy(key, integer);
	}

	@Override
	public Long decr(String key) {
		return jedis.decr(key);
	}

	@Override
	public Long incrBy(String key, long integer) {
		return jedis.incrBy(key, integer);
	}

	@Override
	public String substr(String key, int start, int end) {
		return jedis.substr(key, start, end);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedis.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedis.hget(key, field);
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		return jedis.hsetnx(key, field, value);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		return jedis.hmset(key, hash);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		return jedis.hmget(key, fields);
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		return jedis.hincrBy(key, field, value);
	}

	@Override
	public Boolean hexists(String key, String field) {
		return jedis.hexists(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedis.hdel(key, field);
	}

	@Override
	public Long hlen(String key) {
		return jedis.hlen(key);
	}

	@Override
	public Set<String> hkeys(String key) {
		return jedis.hkeys(key);
	}

	@Override
	public List<String> hvals(String key) {
		return jedis.hvals(key);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		return jedis.hgetAll(key);
	}

	@Override
	public Long rpush(String key, String... strings) {
		return jedis.rpush(key, strings);
	}

	@Override
	public Long lpush(String key, String... strings) {
		return jedis.lpush(key, strings);
	}

	@Override
	public Long llen(String key) {
		return jedis.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		return jedis.lrange(key, start, end);
	}

	@Override
	public String ltrim(String key, long start, long end) {
		return jedis.ltrim(key, start, end);
	}

	@Override
	public String lindex(String key, long index) {
		return jedis.lindex(key, index);
	}

	@Override
	public String lset(String key, long index, String value) {
		return jedis.lset(key, index, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		return jedis.lrem(key, count, value);
	}

	@Override
	public String lpop(String key) {
		return jedis.lpop(key);
	}

	@Override
	public String rpop(String key) {
		return jedis.rpop(key);
	}

	@Override
	public Long sadd(String key, String... members) {
		return jedis.sadd(key, members);
	}

	@Override
	public Set<String> smembers(String key) {
		return jedis.smembers(key);
	}

	@Override
	public String spop(String key) {
		return jedis.spop(key);
	}

	@Override
	public Long scard(String key) {
		return jedis.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		return jedis.sismember(key, member);
	}

	@Override
	public String srandmember(String key) {
		return jedis.srandmember(key);
	}

	@Override
	public Long zrem(String key, String... members) {
		return jedis.zrem(key, members);
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		return jedis.zincrby(key, score, member);
	}

	@Override
	public Long zrank(String key, String member) {
		return jedis.zrank(key, member);
	}

	@Override
	public Long zrevrank(String key, String member) {
		return jedis.zrevrank(key, member);
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		return jedis.zrangeWithScores(key, start, end);
	}

	@Override
	public Double zscore(String key, String member) {
		return jedis.zscore(key, member);
	}

	@Override
	public List<String> sort(String key) {
		return jedis.sort(key);
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		return jedis.sort(key, sortingParameters);
	}

	@Override
	public Long zcount(String key, double min, double max) {
		return jedis.zcount(key, min, max);
	}

	@Override
	public Long zcount(String key, String min, String max) {
		return jedis.zcount(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		return jedis.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		return jedis.zrangeByScore(key, min, max);
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		return jedis.zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		return jedis.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		return jedis.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		return jedis.zrevrangeByScore(key, max, min);
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		return jedis.zrangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		return jedis.zrevrangeByScore(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		return jedis.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		return jedis.zrangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		return jedis.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		return jedis.zrevrangeByScoreWithScores(key, max, min);
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		return jedis.zremrangeByRank(key, start, end);
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		return jedis.zremrangeByScore(key, start, end);
	}

	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		return jedis.zremrangeByScore(key, start, end);
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		return jedis.linsert(key, where, pivot, value);
	}

	@Override
	public Long lpushx(String key, String... strings) {
		return jedis.lpushx(key, strings);
	}

	@Override
	public Long rpushx(String key, String... strings) {
		return jedis.rpushx(key, strings);
	}

	@Override
	public String getHost(String key) {
		// throw new IllegalStateException("Not supported");
		JedisPool jedisPool = jedis.getJedisPoolFromKey(key);
		Jedis j = null;
		try {
			j = jedisPool.getResource();
			return j.getClient().getHost();
		} catch (Exception e) {
			// ignore
		} finally {
			if (null != j)
				j.close();
		}
		return null;
	}

	@Override
	public Integer getPort(String key) {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public String flushDB() {
		throw new IllegalStateException("Not supported");
	}

	@Override
	public Long publish(String channel, String message) {
		return jedis.publish(channel, message);
	}

	@Override
	public void subscribe(JedisPubSub jedisPubSub, String... channels) {
		jedis.subscribe(jedisPubSub, channels);
	}

	@Override
	public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {
		jedis.psubscribe(jedisPubSub, patterns);
	}

}
