#!/bin/bash
#
# name:  Mazing 项目编译脚本
# author: linyi
# date:    2015/5/23
#
source /etc/profile

# if this is not a mazing project directory, exit
if [ ! -d ./web/api ] ; then
    echo "** Please cd mazing project SOURCE CODE directory first ."
    exit 1
fi

# update code
#git fetch origin master
git pull

# compile
mvn clean install -Dmaven.test.skip -P prod

module=$1
case "$module" in
a|m|wu)
    echo "Compile admin finished ..."
;;
o)
    echo "Compiling Order project ..."
    cd web/api
    mvn package -f pom-modul.xml -Dmaven.test.skip -P prod -DmzModul=order
    cd ../..
;;
s)
    echo "Compiling Store project ..."
    cd web/api
    mvn package -f pom-modul.xml -Dmaven.test.skip -P prod -DmzModul=store
    cd ../..
;;
u)
    echo "Compiling User project ..."
    cd web/api
    mvn package -f pom-modul.xml -Dmaven.test.skip -P prod -DmzModul=user
    cd ../..
;;
*)
    echo "Compiling web projects ..."
    # package 5 war
    cd web/api
    mvn package -f pom-modul.xml -Dmaven.test.skip -P prod -DmzModul=order
    mvn package -f pom-modul.xml -Dmaven.test.skip -P prod -DmzModul=store
    mvn package -f pom-modul.xml -Dmaven.test.skip -P prod -DmzModul=user
    cd ../..
    cd web/api-spring
    cd ../..
;;
esac

# 保证目录都存在
destweb=/data/app/yogu/web/
desttask=/data/app/yogu/task/
mkdir -p $destweb
mkdir -p $desttask/timer/
mkdir -p $desttask/timer/lib/
mkdir -p $desttask/consumer/
mkdir -p $desttask/consumer/lib/
mkdir -p /data/app/yogu/config/
mkdir -p /data/app/yogu/shell/

# copy war & configuration files
echo
echo "Copying web files ..."
case "$module" in
admin|a|m)
    echo "Copying admin files ..."
;;
o)
    rm -f $destweb/yogu-api-order.war
    cp -f ./web/api/target/mazing-api-order.war $destweb
;;
s)
    rm -f $destweb/yogu-api-store.war
    cp -f ./web/api/target/mazing-api-store.war $destweb
;;
u)
    rm -f $destweb/yogu-api-user.war
    cp -f ./web/api/target/mazing-api-user.war $destweb
;;
*)
    rm -f $destweb/mazing-api-*.war
    cp -f ./web/api/target/yogu-api-order.war $destweb
    cp -f ./web/api/target/yogu-api-store.war $destweb
    cp -f ./web/api/target/yogu-api-user.war $destweb
;;
esac

rm -f $destweb/yogu-backend-*.war
cp -f ./web/backend-admin/target/yogu-backend-admin.war $destweb
rm -f $destweb/yogu-web-*.war
cp -f ./web/web-mobile/target/yogu-web-mobile.war $destweb
cp -f ./web/web-user/target/yogu-web-user.war $destweb


echo "Copying configuration files ..."
rm -rf /data/app/yogu/config/*
cp -Rf ./appEnv/config/* /data/app/yogu/config/
rm -rf /data/app/yogu/shell/*
cp -Rf ./appEnv/shell/* /data/app/yogu/shell/


# done
echo 
echo "Use 'rs|ts' to restart services"
echo "Use 'timer' to restart (job|consumer) task"
echo
