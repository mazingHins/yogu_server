package com.yogu.mq.impl.local;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.mq.MQConsumer;
import com.yogu.mq.MQContext;
import com.yogu.mq.ServerMsgNotice;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 消费者的redis实现
 * @author linyi 2015/7/21.
 */
public class RedisConsumerImpl implements MQContext, MQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RedisConsumerImpl.class);

    private Redis redis;

    private static Map<String, List<ServerMsgNotice>> noticeMap = new HashMap<>();

    public RedisConsumerImpl(Redis redis) {
        this.redis = redis;
    }

    @Override
    public synchronized void add(String type, ServerMsgNotice listener) {
        List<ServerMsgNotice> list = noticeMap.get(type);
        if (list == null) {
            list = new LinkedList<>();
            noticeMap.put(type, list);
        }
        list.add(listener);
    }

    private Redis getRedis() {
//    	if (null == redis)
//			redis = MainframeBeanFactory.getBean(Redis.class);
        if (redis == null)
            logger.error("redis has not been initialized !");
        return redis;
    }

    @Override
    public void init() {
        final Redis redis = getRedis();
        logger.info("mq#RedisConsumerImpl#init start ... redis: " + redis.getHost(null) + ":" + redis.getPort(null));
        new Thread(new Runnable() {
            @Override
            public void run() {
                redis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        String[] array = message.split(RedisProducerImpl.SPLIT_CHAR);
                        String messageId = array[1];
                        logger.info("mq#RedisConsumerImpl#onMessage | messageId: " + messageId);
                        if (!isMessageConsumed(messageId)) {

                            List<ServerMsgNotice> listeners = noticeMap.get(array[0]);
                            if (listeners != null) {
                                long t1 = System.currentTimeMillis();
                                try {
                                    for (ServerMsgNotice listener : listeners) {
                                        listener.notice(messageId, array[2]);
                                    }
                                    // 设置这条消息已经被消费了
                                    setMessageBeConsumed(messageId);

                                    long time = System.currentTimeMillis() - t1;
                                    logger.info("mq#RedisConsumerImpl#onMessage | 处理消息成功 | messageId: {}, time: {}",
                                            messageId, time);
                                } catch (Exception e) {
                                    logger.error("mq#RedisConsumerImpl#onMessage | 订阅消息出错", e);
                                }
                            }
                        }
                    }

                    @Override
                    public void onPMessage(String pattern, String channel, String message) {

                    }

                    @Override
                    public void onSubscribe(String channel, int subscribedChannels) {

                    }

                    @Override
                    public void onUnsubscribe(String channel, int subscribedChannels) {

                    }

                    @Override
                    public void onPUnsubscribe(String pattern, int subscribedChannels) {

                    }

                    @Override
                    public void onPSubscribe(String pattern, int subscribedChannels) {

                    }
                }, RedisProducerImpl.CHANNEL);
            }
        }, "RedisConsumerImpl-subscribe").start();

        logger.info("mq#RedisConsumerImpl#init end ...");
    }

    /**
     * 判断消息是否被消费过
     * @param messageId 消息ID
     * @return
     */
    private boolean isMessageConsumed(String messageId) {
        String key = getMessageLogKey(messageId);
        return StringUtils.isNotEmpty(getRedis().get(key));
    }

    /**
     * 设置消息已经被消费
     * @param messageId 消息ID
     */
    private void setMessageBeConsumed(String messageId) {
        String key = getMessageLogKey(messageId);
        // 只保留3天
        getRedis().setex(key, 24 * 3600 * 3, "1");
    }

    private String getMessageLogKey(String messageId) {
        // 注：每个消息只能被处理一遍
        return "local:mq:c:" + messageId;
    }

    @Override
    public void destroy() {

    }
}
