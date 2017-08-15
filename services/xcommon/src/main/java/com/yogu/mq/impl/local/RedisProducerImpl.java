package com.yogu.mq.impl.local;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.mq.MQContext;
import com.yogu.mq.MQProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Redis的生产者实现
 * @author linyi 2015/7/21.
 */
public class RedisProducerImpl implements MQProducer, MQContext {

    private static final Logger logger = LoggerFactory.getLogger(RedisProducerImpl.class);

    static final String CHANNEL = "redis_mqueue_x";

    static final String SPLIT_CHAR = "\t\n";

    private Redis redis;

    public RedisProducerImpl(Redis redis) {
        this.redis = redis;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void send(String type, String message) {
        Redis redis = getRedis();
        long t1 = System.currentTimeMillis();
        String messageId = UUID.randomUUID().toString();
        redis.publish(CHANNEL, type + SPLIT_CHAR + messageId + SPLIT_CHAR + message);
        long time = System.currentTimeMillis() - t1;
        logger.info("mq#RedisProducerImpl#send | 发送消息成功 | messageId: {}, time: {}", messageId, time);
    }

    @Override
    public void sendJSON(String type, Object obj) {
        String json = JsonUtils.toJSONString(obj);
        send(type, json);
    }

    private Redis getRedis() {
//    	if (null == redis)
//			redis = MainframeBeanFactory.getBean(Redis.class);
        if (redis == null)
            logger.error("redis has not been initialized !");
        return redis;
    }
}
