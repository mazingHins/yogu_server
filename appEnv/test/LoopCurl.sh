#!/bin/bash
#


internalIp=`ifconfig eth0 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`
internetIp=`ifconfig eth1 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`

#target=ECS
#targetIp=10.168.89.157
#targetHost=activityapi.mazing.com

target=$1
targetIp=$2
targetHost=$3


domain=`echo $targetHost | awk -F. '{print $1}'`
userAgent="[CURL $target, $internalIp > $targetIp ($domain)]"

#暂停 x/10 秒（0.x）    [3~6]
spTime=$(($RANDOM % 4 + 3))

echo "开始时间：`date "+%Y-%m-%d %H:%M:%S"`"
echo "发起请求的机器，内网IP：$internalIp，外网IP：$internetIp"
echo "请求的目标IP：$targetIp、domain：$domain"、reqType：$target
echo "curl 附带的参数：userAngen: $userAgent"
echo "Loop Sleep Time 0.$spTime(s)"
echo "*********************************************"

currentHour=`date "+%H"`
hourCount=0

while [ "1" = "1" ]; do

    #一小时输出一个log
    nowHour=`date "+%H"`
    if [ "$nowHour"x != "$currentHour"x ]; then
        echo "$currentHour Run ($hourCount) ... `date "+%Y-%m-%d %H:%M:%S"`"
        currentHour=$nowHour
        hourCount=0
    fi

    #请求健康监测url
    #result=$(curl -s --connect-timeout 1 -m 1 "http://activityapi.internal.mazing.com/open/health/status")
    result=$(curl --silent -H "Host: $targetHost" --user-agent "$userAgent" -s --connect-timeout 1 -m 1 "http://$targetIp/open/health/status")

    #结果判定，正常应该返回1
    if [ "$result"x != "1x" ]; then
        echo "ERROR >>> `date "+%Y-%m-%d %H:%M:%S %N"`"
        echo "Result is '$result'"
        echo "---------------------------------------"
    fi

    hourCount=`expr $hourCount + 1`
    #sleep 0.4
    sleep 0.$spTime
done

