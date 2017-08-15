#!/bin/bash
#
# name        : Mazing 定时任务启动、停止脚本
# author    : JFan
# date        : 2015/08/28
#

source /etc/profile
DIRECTION="usage: $0 start|stop|restart (job|consumer|tail|redis|wx|slb)"
TASK_DIR=/data/app/mazing/task

#### 需要运行的 Main类全路径；以及参数
####
_RUNCLASS=""
_RUNPARAM=""
JAVA_OPTS="-Xms128m -Xmx256m -server -d64"
RUN_JAR=""
RUN_LIBS=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib/rt.jar

if [ ! $# == 2 ]; then
     echo $DIRECTION
     exit 1
fi

case "$2" in
    consumer)
        _RUNCLASS=com.mazing.tasks.consumer.StartMain
        ## 如果有需要，你可以更改 JAVA_OPTS 的配置
        JAVA_OPTS="-Dtimer.watchdog=mazing-consumer -Xms256m -Xmx512m -server -d64"
        RUN_LIBS=$RUN_LIBS:$TASK_DIR/consumer/lib/*
        RUN_JAR="$TASK_DIR/consumer/mazing-tasks-consumer.jar"
    ;;
    job)

        _RUNCLASS=com.mazing.tasks.timer.StartJobMain
        ## 如果有需要，你可以更改 JAVA_OPTS 的配置
        JAVA_OPTS="-Dtimer.watchdog=mazing-job -Xms256m -Xmx512m -server -d64"
        RUN_LIBS=$RUN_LIBS:$TASK_DIR/timer/lib/*
        RUN_JAR="$TASK_DIR/timer/mazing-tasks-timer.jar"
    ;;
    tail)
        _RUNCLASS=com.mazing.monitor.logtail.TailMain
            #-- 要监控的文件名列表，英文逗号隔开
            BD="/data/log/resin"
            files=$BD/configapi/config-api.log
            files=$files,$BD/userapi/user-api.log,$BD/storeapi/store-api.log,$BD/activityapi/activity-api.log
            files=$files,$BD/orderapi/order-api.log,$BD/payapi/pay-api.log,$BD/webmobile/web-mobile.log
            files=$files,/data/log/tasks/job/job.log,/data/log/tasks/consumer/consumer.log
            #-- 报警发送的email列表，英文逗号隔开，这里不起效果
            emails=t-server@mazing.com
            #-- 要监控的异常关键字列表，英文逗号隔开
            exceptions=Exception,Truncation
            #-- 需要排除不进行监控的关键字列表，英文逗号隔开
            excludes=EOFException

        _RUNPARAM="$files $emails $exceptions $excludes"
        ## 如果有需要，你可以更改 JAVA_OPTS 的配置
        JAVA_OPTS="-Dtimer.watchdog=mazing-logtail -Xms128m -Xmx256m -server -d64"
        RUN_JAR="$TASK_DIR/timer/mazing-tasks-timer.jar"
        RUN_LIBS=$RUN_LIBS:$TASK_DIR/timer/lib/*
    ;;
    redis)
        _RUNCLASS=com.mazing.monitor.redis.RedisMonitorMain
        ## 需要3个参数；1：内存达到多少M就报警；2：客户端连接数达到多少个就报警；3：碎片率达到多少就报警
        _RUNPARAM="1500 400 1.4"
        RUN_JAR="$TASK_DIR/timer/mazing-tasks-timer.jar"
        RUN_LIBS=$RUN_LIBS:$TASK_DIR/timer/lib/*
    ;;
    wx)
        _RUNCLASS=com.mazing.wx.WxHttpServer
        RUN_JAR="$TASK_DIR/timer/mazing-tasks-timer.jar"
        RUN_LIBS=$RUN_LIBS:$TASK_DIR/timer/lib/*
    ;;
    slb)
        _RUNCLASS=com.mazing.aliyun.slb.SlbHttpServer
        ## 有个空格需要用 \ 转义
        _RUNPARAM="/5n3/FqAcpWWsSbYgvfQS/P/SqVESzK7 6OqcUd+wVcyzN9Qh+ufcA+/gLilm7lcw4vYF3oIkhLI="
        RUN_JAR="$TASK_DIR/timer/mazing-tasks-timer.jar"
        RUN_LIBS=$RUN_LIBS:$TASK_DIR/timer/lib/*
    ;;
    *)
        echo "------------------------------------------------------"
        echo $DIRECTION
        exit 1
    ;;
esac


action=$1
model=$2
TIMER_DIR=/data/log/tasks/$model
TIMER_PID=$TIMER_DIR/$model.pid
TIMER_OUT=$TIMER_DIR/catalina.out
mkdir -p $TIMER_DIR

if [ ! -f $RUN_JAR ];then
     echo "$RUN_JAR not exists!"
     exit 1
fi


#### 运行java的基本参数："java 启动参数 classpath"
####
CLASSPATH=$RUN_LIBS:$RUN_JAR
_RUNJAVA="$JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH"


####
#### 启动脚本方法
####
start() {
  #### 如果PID文件存在，那么尝试：
  #### 1：读取文件中的pid，并查看进程是否存在，存在则停止启动
  #### 2：进程不存在，则尝试删除或者清空文件内容，失败则停止启动

  if [ ! -z "$TIMER_PID" ]; then
    if [ -f "$TIMER_PID" ]; then
      if [ -s "$TIMER_PID" ]; then
        # echo "Existing PID file found during start."
        if [ -r "$TIMER_PID" ]; then
          PID=`cat "$TIMER_PID"`
          ps -p $PID >/dev/null 2>&1
          if [ $? -eq 0 ] ; then
            echo "$model appears to still be running with PID $PID. Start aborted."
            exit 1
          else
            # echo "Removing/clearing stale PID file."
            rm -f "$TIMER_PID" >/dev/null 2>&1
            if [ $? != 0 ]; then
              if [ -w "$TIMER_PID" ]; then
                cat /dev/null > "$TIMER_PID"
              else
                echo "Unable to remove or clear stale PID file. Start aborted."
                exit 1
              fi
            fi
          fi
        else
          echo "Unable to read PID file. Start aborted."
          exit 1
        fi
      else
        rm -f "$TIMER_PID" >/dev/null 2>&1
        if [ $? != 0 ]; then
          if [ ! -w "$TIMER_PID" ]; then
            echo "Unable to remove or write to empty PID file. Start aborted."
            exit 1
          fi
        fi
      fi
    fi
  fi

    echo "start $model ......"
    exec $_RUNJAVA $_RUNCLASS $_RUNPARAM $* >> $TIMER_OUT 2>&1 &
    
    if [ ! -z "$TIMER_PID" ]; then
        echo $! > "$TIMER_PID"
    fi
    echo "$model started."
}

####
#### 停止脚本方法
#### STOP_ERR_EXIT用于指示，stop过程中‘出现问题’时是否退出[0,1]（异常包括：没有指定pid，或pid不存在 等等）
####
STOP_ERR_EXIT=1
stop() {

  #### 检查PID任务是否正在运行
  ####
  if [ ! -z "$TIMER_PID" ]; then
    if [ -f "$TIMER_PID" ]; then
      if [ -s "$TIMER_PID" ]; then
        kill -0 `cat "$TIMER_PID"` >/dev/null 2>&1
        if [ $? -gt 0 ]; then
          if [ $STOP_ERR_EXIT -eq 0 ]; then
            return
          fi
          echo "PID file found but no matching process was found. Stop aborted."
          exit 1
        fi
      else
        echo "PID file is empty and has been ignored."
      fi
    else
      if [ $STOP_ERR_EXIT -eq 0 ]; then
        return
      fi
      echo "$model process does not exist. Stop aborted."
      exit 1
    fi
  fi

    echo "stop $model ......"
    kill -15 `cat $TIMER_PID`  

  #### 检查，最多3秒；如果PID任务还存在，则警告“手动处理”
  ####
  SLEEP=3
  if [ ! -z "$TIMER_PID" ]; then
    if [ -f "$TIMER_PID" ]; then
      while [ $SLEEP -ge 0 ]; do
        kill -0 `cat "$TIMER_PID"` >/dev/null 2>&1
        if [ $? -gt 0 ]; then
          rm -f "$TIMER_PID" >/dev/null 2>&1
          if [ $? != 0 ]; then
            if [ -w "$TIMER_PID" ]; then
              cat /dev/null > "$TIMER_PID"
            else
              echo "The PID file could not be removed or cleared."
            fi
          fi
          echo "$model stopped."
          break
        fi
        if [ $SLEEP -gt 0 ]; then
          sleep 1
        fi
        if [ $SLEEP -eq 0 ]; then
          #if [ $FORCE -eq 0 ]; then
            PID=`cat "$TIMER_PID"`
            echo "$model Can't stop, please handle. PID: $PID"
          #fi
        fi
        SLEEP=`expr $SLEEP - 1 `
      done
    fi
  fi
}


case "$action" in
start)
    start
;;
stop)
    STOP_ERR_EXIT=1
    stop
;;
restart)
    STOP_ERR_EXIT=0
    stop
    sleep 3
    start
;;
*)
    echo "------------------------------------------------------"
    echo $DIRECTION
    exit 1
;;
esac
