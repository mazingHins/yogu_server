package com.yogu.mq.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Named;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.mq.MQContext;
import com.yogu.mq.ServerMsgNotice;
import com.yogu.mq.ServerMsgService;
import com.yogu.mq.impl.local.LocalBroadcastRedisImpl;

/**
 * 广播消息实现，根据配置 GlobalSetting.getCloud() 调用不同的实现。
 * 广播消息，只推送一次，不能成功还是失败。
 *
 * 本服务依赖 Redis 实现，实际使用中需引用 project: mazing-common
 * @author linyi 2015/6/27.
 */
@Named
//@DependsOn({"globalSetting", "redis"})
public class BroadcastMessageImpl implements ServerMsgService {

    private ServerMsgService serverMsgService;

    @Resource(name="redisMsg")
    private Redis redis;

    @PostConstruct
    public void init() {

        createMQService();

        // 初始化
        if (serverMsgService instanceof MQContext) {
            ((MQContext) serverMsgService).init();
        }

    }

    @PreDestroy
    public void destroy() {
        if (serverMsgService != null && serverMsgService instanceof MQContext) {
            ((MQContext)serverMsgService).destroy();
        }
    }

    /**
     * 执行创建 ServerMsgService
     * @return 根据 GlobalSetting.getCloud() 返回 ServerMsgService 实例
     */
    private ServerMsgService createMQService() {

        if (serverMsgService == null) {
            serverMsgService = new LocalBroadcastRedisImpl(redis);
        }

        return serverMsgService;
    }

    @Override
    public boolean send(String key, String message) {
        return serverMsgService.send(key, message);
    }

    @Override
    public void regNotice(String key, ServerMsgNotice notice) {
        serverMsgService.regNotice(key, notice);
    }


}
