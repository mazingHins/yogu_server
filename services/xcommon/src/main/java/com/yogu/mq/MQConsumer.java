package com.yogu.mq;

/**
 * 消息订阅（消费者）。
 * 在阿里云ONS实现里，订阅者者是负载均衡的，每一个 MQConsumer 都可以消费到任何消息。
 * 在 mazing 系统里，应该由一个消息订阅者中心统一处理，其他的 WEB 实例、命令行实例
 * 都不能用 MQConsumerFactory 实例化 MQConsumer。
 * 因此任何人在创建一个 MQConsumer 之前，请先确认是否有必要。
 * @author linyi 2015/7/2.
 */
public interface MQConsumer {

    /**
     * 增加消息监听类
     * @param type 消息类型
     * @param listener 监听类
     */
    void add(String type, ServerMsgNotice listener);
}
