/**
 * 
 */
package com.yogu.commons.cache.memcached.session.locator;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import net.rubyeye.xmemcached.HashAlgorithm;
import net.rubyeye.xmemcached.impl.AbstractMemcachedSessionLocator;
import net.rubyeye.xmemcached.impl.MemcachedTCPSession;
import net.rubyeye.xmemcached.networking.MemcachedSession;

import com.google.code.yanf4j.core.Session;
























/**
 * 从2.0.0版本中同名的类复制而来
 * @deprecated 请不要使用这个类，是用来测试的
 * 
 * JFan 2015年3月4日 上午11:22:44
 */
public class KetamaMemcachedSessionLocator extends 
AbstractMemcachedSessionLocator {

    static final int NUM_REPS = 160;
    private transient volatile TreeMap<Long, List<Session>> ketamaSessions = new TreeMap<Long, List<Session>>();
    private final HashAlgorithm hashAlg;
    private volatile int maxTries;
    private final Random random = new Random();

    /**
     * compatible with nginx-upstream-consistent,patched by wolfg1969
     */
    static final int DEFAULT_PORT = 11211;
    private final boolean cwNginxUpstreamConsistent;

    public KetamaMemcachedSessionLocator() {
        this.hashAlg = HashAlgorithm.KETAMA_HASH;
        this.cwNginxUpstreamConsistent = false;
    }

    public KetamaMemcachedSessionLocator(boolean cwNginxUpstreamConsistent) {
        this.hashAlg = HashAlgorithm.KETAMA_HASH;
        this.cwNginxUpstreamConsistent = cwNginxUpstreamConsistent;
    }

    public KetamaMemcachedSessionLocator(HashAlgorithm alg) {
        this.hashAlg = alg;
        this.cwNginxUpstreamConsistent = false;
    }

    public KetamaMemcachedSessionLocator(HashAlgorithm alg,
            boolean cwNginxUpstreamConsistent) {
        this.hashAlg = alg;
        this.cwNginxUpstreamConsistent = cwNginxUpstreamConsistent;
    }

    public KetamaMemcachedSessionLocator(List<Session> list, HashAlgorithm alg) {
        super();
        this.hashAlg = alg;
        this.cwNginxUpstreamConsistent = false;
        this.buildMap(list, alg);
    }

    private final void buildMap(Collection<Session> list, HashAlgorithm alg) {
        llllllllll("重新生成Session Map: "+list.size() +"\t"+alg);
        TreeMap<Long, List<Session>> sessionMap = new TreeMap<Long, List<Session>>();

        for (Session session : list) {
            String sockStr = null;
            if (this.cwNginxUpstreamConsistent) {
                InetSocketAddress serverAddress = session
                        .getRemoteSocketAddress();
                sockStr = serverAddress.getAddress().getHostAddress();
                if (serverAddress.getPort() != DEFAULT_PORT) {
                    sockStr = sockStr + ":" + serverAddress.getPort();
                }
            } else {
                if (session instanceof MemcachedTCPSession) {
                    // Always use the first time resolved address.
                    sockStr = ((MemcachedTCPSession) session)
                            .getInetSocketAddressWrapper()
                            .getRemoteAddressStr();
                }
                if (sockStr == null) {
                    sockStr = String.valueOf(session.getRemoteSocketAddress());
                }
            }
            llllllllll(sockStr);
            /**
             * Duplicate 160 X weight references
             */
            int numReps = NUM_REPS;
            if (session instanceof MemcachedTCPSession) {
                int weight = ((MemcachedSession) session).getWeight();
                numReps *= weight;
                llllllllll("weight："+weight+"\t->\t"+numReps);
            }
            if (alg == HashAlgorithm.KETAMA_HASH) {
                for (int i = 0; i < numReps / 4; i++) {
                    byte[] digest = HashAlgorithm.computeMd5(sockStr + "-" + i);
                    for (int h = 0; h < 4; h++) {
                        long k = (long) (digest[3 + h * 4] & 0xFF) << 24
                                | (long) (digest[2 + h * 4] & 0xFF) << 16
                                | (long) (digest[1 + h * 4] & 0xFF) << 8
                                | digest[h * 4] & 0xFF;
                        this.getSessionList(sessionMap, k).add(session);
                    }

                }
            } else {
                for (int i = 0; i < numReps; i++) {
                    long key = alg.hash(sockStr + "-" + i);
                    this.getSessionList(sessionMap, key).add(session);
                }
            }
        }
        llllllllll("MapSize: "+sessionMap.size());
        this.ketamaSessions = sessionMap;
        this.maxTries = list.size();
    }

    private List<Session> getSessionList(
            TreeMap<Long, List<Session>> sessionMap, long k) {
        List<Session> sessionList = sessionMap.get(k);
        if (sessionList == null) {
            sessionList = new ArrayList<Session>();
            sessionMap.put(k, sessionList);
        }
        return sessionList;
    }

    public final Session getSessionByKey(final String key) {
        llllllllll("使用KEY来查询内容："+key);
        if (this.ketamaSessions == null || this.ketamaSessions.size() == 0) {
            return null;
        }
        long hash = this.hashAlg.hash(key);
        Session rv = this.getSessionByHash(hash);
        int tries = 0;
        while (!this.failureMode && (rv == null || rv.isClosed())
                && tries++ < this.maxTries) {
            hash = this.nextHash(hash, key, tries);
            rv = this.getSessionByHash(hash);
        }
        
        tj(key, (null == rv?"-null-":rv.toString()));
        return rv;
    }

    public final Session getSessionByHash(final long hash) {
        llllllllll("mapsize: "+ketamaSessions.size()+"\thash: "+hash);
        TreeMap<Long, List<Session>> sessionMap = this.ketamaSessions;
        if (sessionMap.size() == 0) {
            return null;
        }
        Long resultHash = hash;
        if (!sessionMap.containsKey(hash)) {
            // Java 1.6 adds a ceilingKey method, but xmemcached is compatible
            // with jdk5,So use tailMap method to do this.
            SortedMap<Long, List<Session>> tailMap = sessionMap.tailMap(hash);
            if (tailMap.isEmpty()) {
                resultHash = sessionMap.firstKey();
            } else {
                resultHash = tailMap.firstKey();
            }
        }
        //
        // if (!sessionMap.containsKey(resultHash)) {
        // resultHash = sessionMap.ceilingKey(resultHash);
        // if (resultHash == null && sessionMap.size() > 0) {
        // resultHash = sessionMap.firstKey();
        // }
        // }
        List<Session> sessionList = sessionMap.get(resultHash);
        if (sessionList == null || sessionList.size() == 0) {
            return null;
        }
        int size = sessionList.size();
        Session s = sessionList.get(this.random.nextInt(size));
        llllllllll(">>>"+resultHash+"\t"+s);
        return s;
    }
    
    private Map<String, String> tj = new HashMap<>();
    private void tj(String s1, String s2){
        tj.put(s1, s2);
    }
    
    public Map<String, String> tjsj(Map<String, String> old) {
        Map<String, String> map = tj;
        tj = new HashMap<>();

        // 热点机器
        Map<String, Integer> rd = new HashMap<>();
        Set<Entry<String, String>> entrySet = map.entrySet();
        for (Entry<String, String> entry : entrySet) {
            String k = entry.getValue();
            Integer c = rd.get(k);
            if (null == c)
                c = 1;
            else
                c += 1;
            rd.put(k, c);
        }
        List<Entry<String, Integer>> rds = new ArrayList<Entry<String, Integer>>(rd.entrySet());
        Collections.sort(rds, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        System.out.println("热点数据：");
        for (Entry<String, Integer> entry : rds)
            System.out.println(entry.getKey() + "\t" + entry.getValue());

        // 变更的
        if (null != old) {
            Map<String, String> tmp = new HashMap<>(map);
            for (Entry<String, String> entry : old.entrySet()) {
                String k = entry.getKey();
                String v = tmp.get(k);
//                if (null == v)
//                    System.out.println(k + "\t不存在。。。");
                if (entry.getValue().equals(v))
                    tmp.remove(k);
            }
            System.out.println("总大小：" + map.size() + "\t变更了的：" + (map.size()-tmp.size()));
        }

        return map;
    }
    
    boolean l = false;
    private void llllllllll(String msg){
        if(l)
        System.out.println(msg);
    }

    public final long nextHash(long hashVal, String key, int tries) {
        long tmpKey = this.hashAlg.hash(tries + key);
        hashVal += (int) (tmpKey ^ tmpKey >>> 32);
        hashVal &= 0xffffffffL; /* truncate to 32-bits */
        return hashVal;
    }

    public final void updateSessions(final Collection<Session> list) {
        this.buildMap(list, this.hashAlg);
    }
}
