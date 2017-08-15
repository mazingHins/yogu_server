/**
 * 
 */
package com.yogu.commons.cache.memcached.session.locator;

import java.util.Collection;

import net.rubyeye.xmemcached.MemcachedSessionLocator;

import com.google.code.yanf4j.core.Session;

/**
 * <br>
 * 
 * JFan 2015年3月4日 下午6:05:23
 */
public class NegotiationSessionLocator implements MemcachedSessionLocator {

    public NegotiationSessionLocator() {
        // this(new KetamaMemcachedSessionLocator(), null);
    }

    public NegotiationSessionLocator(MemcachedSessionLocator sessionLocator) {
        this(sessionLocator, null);
    }

    public NegotiationSessionLocator(MemcachedSessionLocator sessionLocator, Negotiation negotiation) {
        this.negotiation = negotiation;
    }

    private MemcachedSessionLocator sessionLocator;
    private Negotiation negotiation;

    /*
     * （非 Javadoc）
     * 
     * @see net.rubyeye.xmemcached.MemcachedSessionLocator#getSessionByKey(java.lang.String)
     */
    @Override
    public Session getSessionByKey(String key) {
        String k;
        if (null != negotiation)
            k = negotiation.keyNegotiation(key);
        else
            k = key;

        return sessionLocator.getSessionByKey(k);
    }

    /*
     * （非 Javadoc）
     * 
     * @see net.rubyeye.xmemcached.MemcachedSessionLocator#updateSessions(java.util.Collection)
     */
    @Override
    public void updateSessions(Collection<Session> list) {
        sessionLocator.updateSessions(list);
    }

    /*
     * （非 Javadoc）
     * 
     * @see net.rubyeye.xmemcached.MemcachedSessionLocator#setFailureMode(boolean)
     */
    @Override
    public void setFailureMode(boolean failureMode) {
        sessionLocator.setFailureMode(failureMode);
    }

    /**
     * @param sessionLocator 要设置的 sessionLocator
     */
    public void setSessionLocator(MemcachedSessionLocator sessionLocator) {
        this.sessionLocator = sessionLocator;
    }

    /**
     * @param negotiation 要设置的 negotiation
     */
    public void setNegotiation(Negotiation negotiation) {
        this.negotiation = negotiation;
    }

}
