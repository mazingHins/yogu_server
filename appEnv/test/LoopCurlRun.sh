#!/bin/bash
#

internalIp=`ifconfig eth0 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`


## sleep
sleepRandom() {
    srt=$(($RANDOM % 8 + 2))
    sleep 0.$srt
}


## SOA 33
if [ "10.168.89.157x" = "$internalIp"x ]; then
	## order - store
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.153.27 storeapi.internal.mazing.com >> 157_order_slb_store.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.162.105.24 storeapi.internal.mazing.com >> 157_order_ecs_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 storeapi.internal.mazing.com >> 157_order_ecs_store_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.162.105.24:5203 storeapi.internal.mazing.com >> 157_order_java_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5203 storeapi.internal.mazing.com >> 157_order_java_store_157.log &
	
	## store - user
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.210.187 userapi.internal.mazing.com >> 157_store_slb_user.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.162.105.24 userapi.internal.mazing.com >> 157_store_ecs_user_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 userapi.internal.mazing.com >> 157_store_ecs_user_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.162.105.24:5202 userapi.internal.mazing.com >> 157_store_java_user_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5202 userapi.internal.mazing.com >> 157_store_java_user_98.log &
fi

## UAOPM 179
if [ "10.160.84.98x" = "$internalIp"x ]; then
	## order - store
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.153.27 storeapi.internal.mazing.com >> 98_order_slb_store.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.162.105.24 storeapi.internal.mazing.com >> 98_order_ecs_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 storeapi.internal.mazing.com >> 98_order_ecs_store_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.162.105.24:5203 storeapi.internal.mazing.com >> 98_order_java_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5203 storeapi.internal.mazing.com >> 98_order_java_store_157.log &
	
	## pay - order
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.228.8 orderapi.internal.mazing.com >> 98_pay_slb_order.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 orderapi.internal.mazing.com >> 98_pay_ecs_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 orderapi.internal.mazing.com >> 98_pay_ecs_order_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5204 orderapi.internal.mazing.com >> 98_pay_java_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5204 orderapi.internal.mazing.com >> 98_pay_java_order_98.log &
fi

## USPM 213
if [ "10.162.105.24x" = "$internalIp"x ]; then
	## store - user
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.210.187 userapi.internal.mazing.com >> 24_store_slb_user.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.162.105.24 userapi.internal.mazing.com >> 24_store_ecs_user_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 userapi.internal.mazing.com >> 24_store_ecs_user_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.162.105.24:5202 userapi.internal.mazing.com >> 24_store_java_user_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5202 userapi.internal.mazing.com >> 24_store_java_user_98.log &
	
	## pay - order
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.228.8 orderapi.internal.mazing.com >> 24_pay_slb_order.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 orderapi.internal.mazing.com >> 24_pay_ecs_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 orderapi.internal.mazing.com >> 24_pay_ecs_order_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5204 orderapi.internal.mazing.com >> 24_pay_java_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5204 orderapi.internal.mazing.com >> 24_pay_java_order_98.log &
fi

## 38
if [ "10.46.70.246x" = "$internalIp"x ]; then
	## consumer - activi
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.153.252 activityapi.internal.mazing.com >> 246_consumer_slb_activity.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 activityapi.internal.mazing.com >> 246_consumer_ecs_activity_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 activityapi.internal.mazing.com >> 246_consumer_ecs_activity_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5208 activityapi.internal.mazing.com >> 246_consumer_java_activity_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5208 activityapi.internal.mazing.com >> 246_consumer_java_activity_98.log &
	
	## consumer - order
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.228.8 orderapi.internal.mazing.com >> 246_consumer_slb_order.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 orderapi.internal.mazing.com >> 246_consumer_ecs_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 orderapi.internal.mazing.com >> 246_consumer_ecs_order_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5204 orderapi.internal.mazing.com >> 246_consumer_java_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5204 orderapi.internal.mazing.com >> 246_consumer_java_order_98.log &
	
	## consumer - store
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.153.27 storeapi.internal.mazing.com >> 246_consumer_slb_store.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.162.105.24 storeapi.internal.mazing.com >> 246_consumer_ecs_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 storeapi.internal.mazing.com >> 246_consumer_ecs_store_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.162.105.24:5203 storeapi.internal.mazing.com >> 246_consumer_java_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5203 storeapi.internal.mazing.com >> 246_consumer_java_store_157.log &
fi

## 53
if [ "10.46.68.242x" = "$internalIp"x ]; then
	## consumer - activi
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.153.252 activityapi.internal.mazing.com >> 242_consumer_slb_activity.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 activityapi.internal.mazing.com >> 242_consumer_ecs_activity_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 activityapi.internal.mazing.com >> 242_consumer_ecs_activity_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5208 activityapi.internal.mazing.com >> 242_consumer_java_activity_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5208 activityapi.internal.mazing.com >> 242_consumer_java_activity_98.log &
	
	## consumer - order
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.228.8 orderapi.internal.mazing.com >> 242_consumer_slb_order.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 orderapi.internal.mazing.com >> 242_consumer_ecs_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.160.84.98 orderapi.internal.mazing.com >> 242_consumer_ecs_order_98.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5204 orderapi.internal.mazing.com >> 242_consumer_java_order_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.160.84.98:5204 orderapi.internal.mazing.com >> 242_consumer_java_order_98.log &
	
	## consumer - store
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh SLB 100.98.153.27 storeapi.internal.mazing.com >> 242_consumer_slb_store.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.162.105.24 storeapi.internal.mazing.com >> 242_consumer_ecs_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh ECS 10.168.89.157 storeapi.internal.mazing.com >> 242_consumer_ecs_store_157.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.162.105.24:5203 storeapi.internal.mazing.com >> 242_consumer_java_store_24.log &
	sleepRandom
	/tmp/LoopCurl/LoopCurl.sh JAVA 10.168.89.157:5203 storeapi.internal.mazing.com >> 242_consumer_java_store_157.log &
fi

