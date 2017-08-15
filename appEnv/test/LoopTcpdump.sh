#!/bin/bash
#


internalIp=`ifconfig eth0 | grep "inet addr" | awk -F: '{print $2}' | awk '{print $1}'`


#10分钟生成一个包
spTime=600

index=1
while [ "1" = "1" ]; do
    if [ $index -gt 10 ]; then
        index=1
    fi

	pkill -9 tcpdump
	sleep 3

	## SOA 33
	if [ "10.168.89.157x" = "$internalIp"x ]; then
		tcpdump -i eth0 -w $index-33_slb_store.pcap tcp and  host 100.98.153.27 &
		tcpdump -i eth0 -w $index-slb_33.pcap tcp port 80 &
	fi
	
	## UAOPM 179
	if [ "10.160.84.98x" = "$internalIp"x ]; then
		tcpdump -i eth0 -w $index-179_slb_store.pcap tcp and  host 100.98.153.27 &
	fi
	
	## USPM 213
	if [ "10.162.105.24x" = "$internalIp"x ]; then
		tcpdump -i eth0 -w $index-slb_213.pcap tcp port 80 &
	fi

    sleep $spTime

    index=`expr $index + 1`
done

