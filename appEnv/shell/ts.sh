#!/bin/bash
#
# name:  Mazing项目Tomcat启动、停止脚本
# author: jfan
# date:    2016/10/08
#
source /etc/profile
internalIp=`ifconfig eth0 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`
internetIp=`ifconfig eth1 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`

if [ ! $# -ge 2 ];then
     echo "usage: $0 start|restart|stop (c|configapi)|(u|userapi)|(s|storeapi)|(o|orderapi)|(p|payapi)|(a|admin)|(wu|webuser)|(m|mobile)|(act|activity)"
     exit 1
fi

action=$1
model=$2
sname=$2
# 是否跳过slb的操作（===skipSlb才跳过）
skipSlb=$3
CATALINA_OPTS="-server -Xmx256m -Xms128m"
test ".$PROJENV" = .prod && CATALINA_OPTS="-server -Xmx1024m -Xms1024m"

case "$2" in
    c|configapi)
    model=configapi
    sname=api-config
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5001
    ;;
    u|userapi)
    model=userapi
    sname=api-user
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5002
    ;;
    s|storeapi)
    model=storeapi
    sname=api-store
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5003
    CATALINA_OPTS="-server -Xmx384m -Xms128m"
    test ".$PROJENV" = .prod && CATALINA_OPTS="-server -Xmx2048m -Xms2048m"
    ;;
    o|orderapi)
    model=orderapi
    sname=api-order
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5004
    ;;
    p|payapi)
    model=payapi
    sname=api-pay
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5005
    ;;
    a|admin)
    model=admin
    sname=backend-admin
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=6001
    ;;
    wu|webuser)
    model=user
    sname=web-user
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5006
    ;;
    m|mobile)
    model=m
    sname=web-mobile
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5007
    ;;
    act|activity)
    model=activityapi
    sname=api-activity
    test ".$PROJENV" = .internal -o ".$PROJENV" = .dev && DEBUG_ADD=5008
    ;;
    *)
        echo "------------------------------------------------------" 
        echo "usage: $0 start|restart|stop (c|configapi)|(u|userapi)|(s|storeapi)|(o|orderapi)|(p|payapi) |(a|admin)|(wu|webuser)|(m|mobile)|(act|activity)"
        exit 1
;;
esac

logpath="-"
if [ "$model"x == "userx" ]; then
    logpath=webuser
elif [ "$model"x == "mx" ]; then
    logpath=webmobile
else
    logpath=$model
fi


CONF="/data/app/mazing/config/$PROJENV/tomcat/$model.mazing.com.xml"
SERVER="mazing-$sname"

if [ ! -f $CONF ];then
     echo "$CONF not exists!"
     exit 1
fi

# Tomcat
export TOMCAT_USER=www-data
export SERVICE_START_WAIT_TIME=10
export CATALINA_HOME=/usr/local/tomcat
export CATALINA_BASE=/usr/local/tomcat
export CATALINA_PID=$CATALINA_TMP/$SERVER.pid
export CATALINA_TMP=$CATALINA_HOME/temp/$logpath
export CATALINA_TMPDIR=$CATALINA_HOME/temp/$logpath
export CATALINA_OUT=/data/log/tomcat/jvm-$SERVER.log
export CATALINA_MAIN="org.apache.catalina.startup.Bootstrap -config $CONF"
export LOGGING_CONFIG="-Djava.util.logging.config.file=/data/app/mazing/config/$PROJENV/tomcat/logging.properties"

# JVM
CATALINA_OPTS="$CATALINA_OPTS -DlogDir=tomcat -XX:MaxGCPauseMillis=200 -Dnetworkaddress.cache.ttl=600 -Dlog4j2.disable.jmx=true"
CATALINA_OPTS="$CATALINA_OPTS -verbose.gc -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/data/log/tomcat/$logpath/gc.log"

if [ -n $DEBUG_ADD ]; then
    CATALINA_OPTS="$CATALINA_OPTS -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_ADD"
fi

# CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=121.43.155.222 -Dcom.sun.management.jmxremote.port=9651"
export CATALINA_OPTS


######
###### 摘掉slb中某台机器，服务返回1才正常，否则打印返回值，并终止shell
slbEcsDisable() {
    if [ "$skipSlb"x == "skipSlbx" ] || [ "$PROJENV"x != "prodx" ]; then
        return
    fi
    echo "停用slb后端机器 (model: $model, ip0: $internalIp, ip1: $internetIp) ..."
    slbEcsDisableResult=$(curl -s --connect-timeout 3 -m 10 "http://10.171.244.29:20079/slb/ecs/disable?model=$model&ip=$internalIp")
    if [ "$slbEcsDisableResult"x != "1x" ]; then
        echo "终止发布，原因：$slbEcsDisableResult"
        echo "如果不需要操作SLB，请在rs命令增加第三个参数skipSlb；例如：rs restart xxx skipSlb"
        exit 1
    fi
    echo "停用slb后端机器成功"
    sleep 3
}

######
###### 将某台机器增加到slb中，服务返回1才正常，否则打印返回值，并终止shell
slbEcsEnable() {
    if [ "$skipSlb"x == "skipSlbx" ] || [ "$PROJENV"x != "prodx" ]; then
        return
    fi

    ## 监测服务健康状态
    httpProtocol="http"
    if [ "$model"x == "userx" ]; then
        httpProtocol="https"
    fi
    ## 每隔4秒钟检测一次，最多检测20次
    ## 注意：就算20次之后没有检测到服务正常，也会将启用后端服务器
    sleepNum=20
    while [ $sleepNum -ge 1 ]; do
        echo "检测服务状态；剩余次数：$sleepNum .... （请不要停止shell）"
        enableCheckResult=$(curl --silent -H "Host: $model.mazing.com" -s --connect-timeout 1 -m 1 "$httpProtocol://127.0.0.1/open/health/status")
        if [ "$enableCheckResult"x = "1x" ]; then
            echo "检测服务状态: 服务启动正常"
            break
        fi
        sleepNum=`expr $sleepNum - 1 `
        sleep 4
    done

    echo "启用slb后端机器 (model: $model, ip0: $internalIp, ip1: $internetIp) ..."
    slbEcsEnableResult=$(curl -s --connect-timeout 3 -m 10 "http://10.171.244.29:20079/slb/ecs/enable?model=$model&ip=$internalIp")
    if [ "$slbEcsEnableResult"x != "1x" ]; then
        echo "启用slb后端机器失败，原因：$slbEcsEnableResult"
        #echo "如果不需要操作SLB，请在rs命令增加第三个参数skipSlb；例如：rs restart xxx skipSlb"
    else
        echo "启用slb后端机器成功"
        sleep 3
    fi
}


######
###### 停止服务
stopServer() {
    echo 'stop tomcat......'
    echo "conf=$CONF, server=$SERVER"
    #### 常规的方式
    # $CATALINA_HOME/bin/shutdown.sh -config $CONF
    #### 非root用户的方式
    $CATALINA_HOME/bin/daemon.sh stop
}


######
###### 启动服务
startServer() {
    GC_FILE="/data/log/tomcat/$logpath/gc.log"
    GCALL_FILE="/data/log/tomcat/$logpath/gcAll.log"

    # 复制 gc.log 文件到 gcAll.log 中
    if [ -f "$GC_FILE" ]; then
        echo "Append $GC_FILE >> $GCALL_FILE"
        cat $GC_FILE >> $GCALL_FILE
        echo '' > $GC_FILE
    fi

    echo 'start tomcat......'
    echo "conf=$CONF, server=$SERVER"
    #### 常规的方式
    # $CATALINA_HOME/bin/startup.sh -config $CONF
    #### 非root用户的方式
    $CATALINA_HOME/bin/daemon.sh start
}



echo "-----------------------start----------------------------------------------------------------------"

case "$action" in
start)
    startServer
    slbEcsEnable
;;
stop)
    slbEcsDisable
    stopServer
    sleep 5
;;
restart)
    slbEcsDisable
    stopServer
    echo "******************************************************************"
    sleep 5
    startServer
    slbEcsEnable
;;
*)
    echo "------------------------------------------------------" 
    echo "usage: $0 start|restart|stop (c|configapi)|(u|userapi)|(s|storeapi)|(o|orderapi)|(p|payapi)|(a|admin)|(wu|webuser)|(m|mobile)|(act|activity)"
    exit 1
;;
esac

echo "======================= end ======================================================================"

