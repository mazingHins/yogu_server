package com.yogu.mq.impl.local;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.OSUtil;
import com.yogu.mq.MQContext;
import com.yogu.mq.ServerMsgNotice;
import com.yogu.mq.ServerMsgService;

/**
 * Redis实现的发布、订阅消息
 * 
 * @author linyi 2015/7/3.
 */
public class LocalBroadcastRedisImpl implements MQContext, ServerMsgService {

	private static final Logger logger = LoggerFactory.getLogger(LocalBroadcastRedisImpl.class);

	private Map<String, List<ServerMsgNotice>> noticeMap = new HashMap<>();

	private static final String CHANNEL = "broadcast";

	private static final String SPLIT_CHAR = "\t\n";

	private JedisPubSub jedisPubSub;
	private Thread thread;
	private Redis redis;

	public LocalBroadcastRedisImpl(Redis redis) {
		this.redis = redis;
	}

	@Override
	public void init() {
		final Redis redis = getRedis();
		//logger.info("mq#LocalBroadcastRedisImpl#init start ... redis: " + LogUtil.hidePassport(redis.getHost(null)) + ":"
		//		+ redis.getPort(null));

		jedisPubSub = new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				String[] array = message.split(SPLIT_CHAR);
				String messageId = array[1];
				logger.info("mq#LocalBroadcastRedisImpl#onMessage | messageId: " + messageId);

				if (!isMessageConsumed(messageId)) {
					logger.info("mq#LocalBroadcastRedisImpl#onMessage | 开始处理消息 | messageArr: " + Arrays.toString(array));
					// 根据注册的broadcastKey 获取注册者
					List<ServerMsgNotice> listeners = noticeMap.get(array[0]);
					if (listeners != null) {
						long t1 = System.currentTimeMillis();
						try {
							for (ServerMsgNotice listener : listeners) {
								logger.info("mq#LocalBroadcastRedisImpl#onMessage | 处理消息 ... | class: {}, message: {}",
										listener.getClass().getName(), array[2]);
								listener.notice(messageId, array[2]);
							}
							// 设置这条消息已经被消费了
							setMessageBeConsumed(messageId);

							long time = System.currentTimeMillis() - t1;
							logger.info("mq#LocalBroadcastRedisImpl#onMessage | 处理消息成功 | messageArr: {}, time: {}", Arrays.toString(array),
									time);
						} catch (Exception e) {
							logger.error("mq#LocalBroadcastRedisImpl#onMessage | 订阅消息消费出错 | message: '{}'", message, e);
						}
					} else {
						logger.error("mq#LocalBroadcastRedisImpl#onMessage | 没有找到 listener | message: '{}'", message);
					}
				} else {
					logger.warn("mq#LocalBroadcastRedisImpl#onMessage | 消息已经处理过了 | messageId: {}, message: {}", messageId, message);
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
		};

		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				redis.subscribe(jedisPubSub, CHANNEL);
			}
		}, "LocalBroadcastRedisImpl-subscribe");
		thread.start();

		logger.info("mq#LocalBroadcastRedisImpl#init end ...");
	}

	@Override
	public void destroy() {
		try {
			thread.interrupt();
			thread = null;
		} catch (Exception e) {
			// ignore
		}

		try {
			jedisPubSub.unsubscribe(CHANNEL);
			jedisPubSub = null;
		} catch (Exception e) {
			// ignore
		}
	}

	@Override
	public synchronized void regNotice(String key, ServerMsgNotice notice) {
		List<ServerMsgNotice> list = noticeMap.get(key);
		if (list == null) {
			list = new LinkedList<>();
			noticeMap.put(key, list);
		}
		list.add(notice);
		logger.info("mq#LocalBroadcastRedisImpl#regNotice | 注册订阅者成功 | key: {}, listenerSize: {}", key, list.size());
	}

	@Override
	public boolean send(String key, String message) {
		Redis redis = getRedis();
		long t1 = System.currentTimeMillis();
		String messageId = UUID.randomUUID().toString();
		redis.publish(CHANNEL, key + SPLIT_CHAR + messageId + SPLIT_CHAR + message);
		long time = System.currentTimeMillis() - t1;
		logger.info("mq#LocalBroadcastRedisImpl#send | 发送消息成功 | messageId: {}, time: {}", messageId, time);
		return true;
	}

	/**
	 * 判断消息是否被消费过
	 * 
	 * @param messageId 消息ID
	 * @return
	 */
	private boolean isMessageConsumed(String messageId) {
		String key = getMessageLogKey(messageId);
		return StringUtils.isNotEmpty(getRedis().get(key));
	}

	/**
	 * 设置消息已经被消费
	 * 
	 * @param messageId 消息ID
	 */
	private void setMessageBeConsumed(String messageId) {
		String key = getMessageLogKey(messageId);
		// 只保留3天
		getRedis().setex(key, 24 * 3600 * 3, "1");
	}

	private String getMessageLogKey(String messageId) {
		// 注：每个JVM可以消费一次，所以要加上pid
		return "local:br:c:" + messageId + ":" + OSUtil.getPid() + ":" + OSUtil.getLocalIp();
	}

	private Redis getRedis() {
		// if (null == redis)
		// redis = MainframeBeanFactory.getBean(Redis.class);
		if (redis == null)
			logger.error("redis has not been initialized !");
		return redis;
	}
}
