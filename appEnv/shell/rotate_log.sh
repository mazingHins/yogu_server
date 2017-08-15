#!/bin/bash
#


####
#### 将.gz文件迁移到指定目录下的【年月】目录中，
#### 7天内产生的文件不移动
#### 需要参数1：sourceDir 源目录
#### 需要参数2：destDir 目的目录
####
archiveEserveRecall() {
    if [ ! -d "$sourceDir" ]; then
        echo 'archiveEserveRecall: directory [$sourceDir] does not exist!'
        return
    fi

    for file in $sourceDir/*.gz ; do
        # 读取文件名中的  年月日 （可能只有年月，也可能只有一个数字 i%）
        #read a b c < <(echo ${file} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $1,$2,$3}')
        a=`echo ${file} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $1}'`
        b=`echo ${file} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $2}'`
        c=`echo ${file} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $3}'`
        #echo "'$a' - '$b' - '$c'"

        # 计算目的地前缀（不包括目的目录）
        fileDate=""
        destPrefix=""
        if [ -n "$c" ]; then
            fileDate="$b$c"
            destPrefix="$a$b/"
        elif [ -n "$b" ]; then
            fileDate="$a$b"
            destPrefix="$YESTERDAY_YESH$a/"
        fi
        #echo "'$destPrefix'"

        #保留7天的文件，不移动
        if [ "$fileDate"x = "$YESTERDAY_MD"x ] \
                || [ "$fileDate"x = "$YESTERDAY_MD_BEFORE2"x ] \
                || [ "$fileDate"x = "$YESTERDAY_MD_BEFORE3"x ] \
                || [ "$fileDate"x = "$YESTERDAY_MD_BEFORE4"x ] \
                || [ "$fileDate"x = "$YESTERDAY_MD_BEFORE5"x ] \
                || [ "$fileDate"x = "$YESTERDAY_MD_BEFORE6"x ]; then
            #echo "%%%%%%%%%%%%%%% Yesterday Or Before. continue => '$apiFile'"
            continue
        fi

        # 移动文件
        destDDD="$destDir/$destPrefix"
        #echo "'$file' ---> '$destDDD'"
        mkdir -p $destDDD
        ## cp $file $destDDD
        mv $file $destDDD
    done
}

####
#### 递归目录进行archiveEserveRecall方法调用
#### 需要参数1：sourceDD 源目录
#### 需要参数2：destDD 目的目录
####
sourceToDestDD() {
    if [ ! -d "$sourceDD" ]; then
        echo 'sourceToDestDD: directory [$sourceDD] does not exist!'
        return
    fi

    cd $sourceDD
    currentDirName=""
    for currentDir in `ls | find -type d -maxdepth 1` ; do
        currentDirName=`echo ${currentDir} | awk -F'/' '{print $(NF)}'`
        #echo "'$currentDir' ===> '$currentDirName'"
        if [ "x" = "$currentDirName"x ] || [ ".x" = "$currentDirName"x ]; then
            #echo "%%%%%%%%%%%%%%% continue => '$currentDir'"
            continue
        fi

        # 迁移文件到归档目录
        sourceDir=$sourceDD/$currentDirName
        destDir=$destDD/$currentDirName
        archiveEserveRecall
    done
}




YESTERDAY=$(date -d "yesterday" +%Y-%m-%d)
YESTERDAY_YESH=$(date -d "yesterday" +%Y)
YESTERDAY_MD=$(date -d "yesterday" +%m%d)
YESTERDAY_MD_BEFORE2=$(date -d "2 days ago" +%m%d)
YESTERDAY_MD_BEFORE3=$(date -d "3 days ago" +%m%d)
YESTERDAY_MD_BEFORE4=$(date -d "4 days ago" +%m%d)
YESTERDAY_MD_BEFORE5=$(date -d "5 days ago" +%m%d)
YESTERDAY_MD_BEFORE6=$(date -d "6 days ago" +%m%d)




# nginx日志分割脚本
# ten 2015/12/8
#

echo "Nginx log rotate $YESTERDAY"

NGINX_LOGS_PATH=/data/log/nginx
NGINX_BACKUP_PATH="/data/log/backup/nginx"
mkdir -p $NGINX_BACKUP_PATH

# 将log文件改成 日期后缀的 备份文件
dest=""
cd $NGINX_LOGS_PATH
for file in ${NGINX_LOGS_PATH}/*.log ; do
    if [ -s $file ]; then
        dest="${file}.${YESTERDAY}"
        #echo "$file  $dest"
        mv $file $dest
    else
        rm -f $file
    fi
done
kill -USR1 `cat /usr/local/nginx/logs/nginx.pid`

# 压缩刚才生成的 备份文件
gzip *.log.$YESTERDAY

# 将gz压缩文件，移动到年月目录中去
sourceDir=$NGINX_LOGS_PATH
destDir=$NGINX_BACKUP_PATH
archiveEserveRecall

# 健康监测的不需要保留，直接放到归档目录
for healthFile in /data/log/nginx/*.health.log.*.gz ; do
    a=`echo ${healthFile} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $1}'`
    b=`echo ${healthFile} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $2}'`
    c=`echo ${healthFile} | awk -F'.' '{print $(NF-1)}' | awk -F'-' '{print $3}'`

    # 计算目的地前缀（不包括目的目录）
    healthFileDate=""
    healthDestPrefix=""
    if [ -n "$c" ]; then
        healthFileDate="$b$c"
        healthDestPrefix="$a$b/"
    elif [ -n "$b" ]; then
        healthFileDate="$a$b"
        healthDestPrefix="$YESTERDAY_YESH$a/"
    fi

    # 移动文件
    healthDestDDD="$NGINX_BACKUP_PATH/$healthDestPrefix"
    mkdir -p $healthDestDDD
    mv $healthFile $healthDestDDD
done

echo "Nginx log rotate done"








# resin 自身的log（access log） 有新的文件，就压缩并移走
# jfan 2016/06/07
#
#
#echo "Resin access log rotate $YESTERDAY"
#
#RESIN_LOG_DIR="/usr/local/resin/log"
#cd $RESIN_LOG_DIR
#gzip access.log.*
#
#for resinLogFile in $RESIN_LOG_DIR/*.gz ; do
#    if [ -f "$resinLogFile" ]; then
#        #echo "<$resinLogFile>"
#        mv $resinLogFile $RESIN_BACKUP_PATH
#    fi
#done
#
#echo "Resin access log rotate done"








# resin Application 日志按 年月 存放到目录中
# jfan 2016/05/05
#

echo "Resin Application log rotate $YESTERDAY"

#### resin下的各个应用日志
sourceDD=/data/log/resin
destDD="/data/log/backup"
sourceToDestDD

#### config下的 埋点日志(resin)
sourceDD=/data/log/resin/configapi
destDD="/data/log/backup"
sourceToDestDD

echo "Resin Application log rotate done"







# tomcat Application 日志按 年月 存放到目录中
# jfan 2016/10/10
#

echo "Tomcat Application log rotate $YESTERDAY"

#### tomcat下的各个应用日志
sourceDD=/data/log/tomcat
destDD="/data/log/backup"
sourceToDestDD

#### config下的 埋点日志(tomcat)
sourceDD=/data/log/tomcat/configapi
destDD="/data/log/backup"
sourceToDestDD

echo "Tomcat Application log rotate done"








# tasks 日志移动到归档目录中
# jfan 2016/05/09
#

echo "Tasks log rotate $YESTERDAY"

#### job log file
sourceDir=/data/log/tasks/job
destDir=/data/log/backup/tasks/job
archiveEserveRecall

#### consumer log file
sourceDir=/data/log/tasks/consumer
destDir=/data/log/backup/tasks/consumer
archiveEserveRecall

echo "Tasks log rotate done"




cd -
