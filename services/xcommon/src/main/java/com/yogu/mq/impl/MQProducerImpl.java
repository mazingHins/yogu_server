package com.yogu.mq.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Named;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.mq.MQContext;
import com.yogu.mq.MQProducer;
import com.yogu.mq.impl.local.RedisProducerImpl;

/**
 * @author linyi 2015/7/2.
 */
//@Lazy
//@Primary
@Named("mqProducer")
public class MQProducerImpl implements MQProducer, MQContext {

    @Resource(name="redis")
    private Redis redis;

    private MQProducer producer;

    @PostConstruct
    @Override
    public void init() {
        createProducer();
        if (producer instanceof MQContext) {
            ((MQContext)producer).init();
        }
    }

    private void createProducer() {
        // 阿里云
//        if (GlobalSetting.CLOUD_ALIYUN.equals(GlobalSetting.getCloud())) {
//            producer = new AliyunProducerImpl();
//        }
        // 仅用于本地测试
//        else if (GlobalSetting.CLOUD_LOCAL.equalsIgnoreCase(GlobalSetting.getCloud())) {
            producer = new RedisProducerImpl(redis);
//        }
//        else
//            throw new IllegalStateException("Not support cloud: " + GlobalSetting.getCloud());
    }

    @PreDestroy
    @Override
    public void destroy() {
        if (producer instanceof MQContext) {
            ((MQContext)producer).destroy();
        }
    }

    @Override
    public void send(String type, String message) {
        producer.send(type, message);
    }

    @Override
    public void sendJSON(String type, Object obj) {
        producer.sendJSON(type, obj);
    }

}
