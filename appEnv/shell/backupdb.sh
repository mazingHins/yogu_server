#!/bin/bash

NOW=`date +%Y-%m-%d\ %H:%M:%S`
echo "backup db start by $NOW ..."

YM=`date +%Y%m`
DAY=`date +%d`

bakpath="/data/backup/db/$YM/$DAY"
echo "backup db DIR: $bakpath"
mkdir -p $bakpath

adminsql="${bakpath}/mz_admin.sql"
ordersql="${bakpath}/mz_order.sql"
merchantsql="${bakpath}/mz_merchant.sql"
paysql="${bakpath}/mz_pay.sql"
configsql="${bakpath}/mz_config.sql"
usersql="${bakpath}/mz_user.sql"
activitysql="${bakpath}/mz_activity.sql"

bakuser="mazingbak"
bakpassword="XXXXXXXXXX"

echo "backup admin ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_admin > $adminsql
echo "backup config ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_config > $configsql
echo "backup merchant ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_merchant > $merchantsql
echo "backup pay ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_pay > $paysql
echo "backup order ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_order > $ordersql
echo "backup user ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_user > $usersql
echo "backup activity ..."
/usr/bin/mysqldump -u$bakuser -p$bakpassword --default-character-set=utf8 -e --max-allowed-packet=32m --net_buffer_length=16k mz_activity > $activitysql

echo "GZIP ..."
cd $bakpath
gzip *.*
cd -
echo "done"
