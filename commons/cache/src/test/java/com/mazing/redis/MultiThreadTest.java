package com.mazing.redis;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisFactory;
import com.yogu.commons.cache.redis.impl.RedisShardingImpl;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;

import java.util.concurrent.CountDownLatch;
  
public class MultiThreadTest {  
	
	private static Redis redis;
	
    public static void main(String[] args) {  
    	redis = createRedis();
    	test(10, 1000);
    }  
    
    private static Redis createRedis(){
    	int maxActive = 30;
		String server1 = "redis.mazing.com:6379";
		String server2 = "redis.mazing.com:6379";
		String server3 = "redis.mazing.com:6379";
		String server = new DesPropertiesEncoder().encode(server1 + "#" + server2 + "#" + server3);

		RedisFactory factory = new RedisFactory();
		factory.setMaxTotal(maxActive);

		RedisShardingImpl redis = (RedisShardingImpl) factory.initRedis(server);
//    	int maxActive = 20;
//		String server = "redis.mazing.com:6377";
//		
//		RedisImpl redis = new RedisImpl();
//		redis.setServer(server);
//		redis.setMaxActive(maxActive);
//		redis.init();
		return redis;
    }
  
    private static void test(int threadNum, final int count) {  
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);  
        long t1 = System.currentTimeMillis();  
        for (int i = 0; i <= threadNum; i++) {  
            Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						for(int i = 0; i <= count; i++){
				    		String key = Thread.currentThread()+"_"+i;
				    		String value = "aaaaaa"+i;
				    		redis.set(key, value);
				    		String val = redis.get(key);
				    		if(!(value.equals(val)))
				    			throw new RuntimeException("XXXXXXXXXX");
				    	}
					}finally{
						countDownLatch.countDown();  
					}
				}
			});
            thread.start();  
        }  
        try {  
            countDownLatch.await();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        long t2 = System.currentTimeMillis();  
        long cost = (t2-t1)/1000;
        int allTps = (int)(count * threadNum / cost);
        System.out.println("count: " + count*threadNum + " threadNum: "+threadNum+ " cost time="  + cost + " all_tps: "+allTps);  
    }  
}  