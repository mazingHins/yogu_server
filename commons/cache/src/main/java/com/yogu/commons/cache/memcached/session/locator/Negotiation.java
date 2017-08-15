/**
 * 
 */
package com.yogu.commons.cache.memcached.session.locator;

/**
 * 在key hash之前进行处理<br>
 * 已知可用的场景：HotKey的处理<br>
 * 
 * JFan 2015年3月4日 下午6:07:14
 */
public interface Negotiation {

    public String keyNegotiation(String key);

}
