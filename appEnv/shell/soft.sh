#!/bin/bash
#
# name   : 启动、停止、重启 相关服务
# autho  : JFan
# date   : 2016/06/17
#

source /etc/profile
DIRECTION="usage: $0 start|stop|restart (redis|zookeeper|kafka)"

if [ ! $# == 2 ]; then
     echo $DIRECTION
     exit 1
fi

action=$1
soft=$2


####
#### 启动
####
start() {
    case "$soft" in
    redis)
        PIDS=$(ps ax | grep -i 'redis-server' | grep -v grep | awk '{print $1}')
        if [ -z "$PIDS" ]; then
            /usr/local/redis/bin/redis-server &
        else 
            echo "The redis service is already running. [$PIDS]"
        fi
    ;;
    zookeeper)
        PIDS=$(ps ax | grep -i 'zookeeper\.server' | grep -v grep | awk '{print $1}')
        if [ -z "$PIDS" ]; then
            /usr/local/zookeeper/bin/zkServer.sh start
        else 
            echo "The zookeeper service is already running. [$PIDS]"
        fi
    ;;
    kafka)
        PIDS=$(ps ax | grep -i 'kafka\.Kafka' | grep java | grep -v grep | awk '{print $1}')
        if [ -z "$PIDS" ]; then
            /usr/local/kafka/bin/kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties
        else 
            echo "The kafka service is already running. [$PIDS]"
        fi
    ;;
    *)
        showHelp
    ;;
    esac
}


####
#### 停止
####
stop() {
    case "$soft" in
    redis)
        PIDS=$(ps ax | grep -i 'redis-server' | grep -v grep | awk '{print $1}')
        stopByPid
    ;;
    zookeeper)
        /usr/local/zookeeper/bin/zkServer.sh stop
    ;;
    kafka)
        /usr/local/kafka/bin/kafka-server-stop.sh
    ;;
    *)
        showHelp
    ;;
    esac
}

#停止进程，需要参数：PIDS
stopByPid() {
    if [ -z "$PIDS" ]; then
        echo "No server to stop"
        exit 1
    else 
        kill -s TERM $PIDS
    fi
}


####
#### 显示使用帮助
####
showHelp() {
    echo "------------------------------------------------------"
    echo $DIRECTION
    exit 1
}



case "$action" in
start)
    start
;;
stop)
    stop
;;
restart)
    stop
    sleep 3
    start
;;
*)
    showHelp
;;
esac
