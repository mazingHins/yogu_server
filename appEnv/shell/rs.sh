#!/bin/bash
#
# name:  Mazing 项目启动、停止脚本
# author: linyi
# date:    2015/5/23
#
source /etc/profile
internalIp=`ifconfig eth0 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`
internetIp=`ifconfig eth1 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`

if [ ! $# -ge 2 ];then
     echo "usage: $0 start|restart|stop (u|userapi)|(s|storeapi)|(o|orderapi)(a|admin)"
     exit 1
fi

action=$1
model=$2
sname=$2
# 是否跳过slb的操作（===skipSlb才跳过）
skipSlb=$3


case "$2" in
    u|userapi)
    model=userapi
    sname=api-user
    ;;
    s|storeapi)
    model=storeapi
    sname=api-store
    ;;
    o|orderapi)
    model=orderapi
    sname=api-order
    ;;
    a|admin)
    model=admin
    sname=backend-admin
    ;;   
    *)
        echo "------------------------------------------------------" 
        echo "usage: $0 start|restart|stop (u|userapi)|(s|storeapi)|(o|orderapi)(a|admin)"
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


CONF="/data/app/yogu/config/$PROJENV/resin/$model.mazing.com.xml"
LOG_PATH="/data/log/resin/"
SERVER="yogu-$sname"

if [ ! -f $CONF ];then
     echo "$CONF not exists!"
     exit 1
fi


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
    echo 'stop resin......'
    echo "conf=$CONF, server=$SERVER"
    /usr/local/resinpro/bin/resin.sh -conf $CONF -log-directory $LOG_PATH -root-directory /usr/local/resinpro -server $SERVER shutdown 
}


######
###### 启动服务
startServer() {
    GC_FILE="/data/log/resin/$logpath/gc.log"
    GCALL_FILE="/data/log/resin/$logpath/gcAll.log"

    # 复制 gc.log 文件到 gcAll.log 中
    if [ -f "$GC_FILE" ]; then
        echo "Append $GC_FILE >> $GCALL_FILE"
        cat $GC_FILE >> $GCALL_FILE
        echo '' > $GC_FILE
    fi

    echo 'start resin......'
    echo "conf=$CONF, server=$SERVER"
    /usr/local/resinpro/bin/resin.sh -conf $CONF -log-directory $LOG_PATH -root-directory /usr/local/resinpro -server $SERVER start 
}



echo "-----------------------start----------------------------------------------------------------------"

case "$action" in
start)
    startServer
#    slbEcsEnable
;;
stop)
#    slbEcsDisable
    stopServer
;;
restart)
#    slbEcsDisable
    stopServer
    echo "******************************************************************"
    sleep 3
    startServer
#    slbEcsEnable
;;
*)
    echo "------------------------------------------------------" 
    echo "usage: $0 start|restart|stop (c|configapi)|(u|userapi)|(s|storeapi)|(o|orderapi)|(p|payapi)|(a|admin)|(wu|webuser)|(m|mobile)|(act|activity)"
    exit 1
;;
esac

echo "======================= end ======================================================================"

