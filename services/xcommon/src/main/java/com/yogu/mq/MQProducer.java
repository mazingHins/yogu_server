package com.yogu.mq;

/**
 * 消息发布（生产者）
 * @author linyi 2015/7/2.
 */
public interface MQProducer {

    /**
     * 发送消息
     * @param type 消息类型，例如 REGISTER/LOGIN
     * @param message 消息内容
     */
    void send(String type, String message);

    /**
     * 发送JSON消息
     * @param type 消息类型，例如 REGISTER/LOGIN
     * @param obj 对象内容
     */
    void sendJSON(String type, Object obj);
}
