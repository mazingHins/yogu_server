package com.yogu.mq;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.mq.impl.local.RedisConsumerImpl;

/**
 * MQConsumer消费者工厂类。
 * 使用factory创建MQConsumer实例，避免 spring 自动初始化。
 * 不是每个 jvm 实例都需要 MQ 消费，MQ的消费应该隔离处理。
 * 在 mazing 系统里，应该由一个消息订阅者中心统一处理，其他的 WEB 实例、命令行实例
 * 都不能用 MQConsumerFactory 实例化 MQConsumer。
 * @author ten 2015/8/15.
 */
public class MQConsumerFactory {

    /**
     * 实例
     */
    private static MQConsumer instance;

    /**
     * 创建消费者
     * @param redis redis实例，用于判断消息是否重复消费
     * @return 返回MQConsumer实例
     */
    public static MQConsumer createConsumer(Redis redis) {
        if (instance == null) {
            createConsumerBySetting(redis);
        }
        return instance;
    }

    /**
     * 根据系统环境配置创建不同的 consumer。
     * @param redis Redis实例
     */
    private static synchronized void createConsumerBySetting(Redis redis) {
        if (instance == null) {
            // 阿里云
//            if (GlobalSetting.CLOUD_ALIYUN.equals(GlobalSetting.getCloud())) {
//                instance = new AliyunConsumerImpl(redis);
//            }
            // 仅用于本地测试
            if (GlobalSetting.CLOUD_LOCAL.equalsIgnoreCase(GlobalSetting.getCloud())) {
                instance = new RedisConsumerImpl(redis);
            } else
                throw new IllegalStateException("Not support cloud: " + GlobalSetting.getCloud());

            if (instance instanceof  MQContext) {
                ((MQContext) instance).init();
            }
        }
    }
}
