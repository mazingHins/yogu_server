package com.yogu.mq;

/**
 * 支持init(), destroy() 的接口
 * @author linyi 2015/6/27.
 */
public interface MQContext {

    /**
     * 初始化
     */
    void init();

    /**
     * 销毁
     */
    void destroy();
}
