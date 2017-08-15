# 访问SLB操作的接口，允许的IP
# 访问URL：curl --connect-timeout 3 -m 10 "http://10.171.244.29:20079/***"    -- 179 内网IP
# 查看端口占用情况：netstat –apn | grep 20079
# jfan 2016-05-26

iptables -A INPUT -s 127.0.0.1 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -s 10.46.70.246 -p tcp --dport 20079 -j ACCEPT
iptables -A INPUT -s 120.55.191.38 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -s 10.46.68.242 -p tcp --dport 20079 -j ACCEPT
iptables -A INPUT -s 120.55.191.53 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -s 10.160.84.98 -p tcp --dport 20079 -j ACCEPT
iptables -A INPUT -s 112.124.59.179 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -s 10.168.89.157 -p tcp --dport 20079 -j ACCEPT
iptables -A INPUT -s 121.40.180.33 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -s 10.162.105.24 -p tcp --dport 20079 -j ACCEPT
iptables -A INPUT -s 114.215.169.213 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -s 10.46.73.85 -p tcp --dport 20079 -j ACCEPT
iptables -A INPUT -s 120.27.128.222 -p tcp --dport 20079 -j ACCEPT

## dev1
#iptables -A INPUT -s 10.168.35.60 -p tcp --dport 20079 -j ACCEPT
#iptables -A INPUT -s 121.40.160.217 -p tcp --dport 20079 -j ACCEPT
## dev2
#iptables -A INPUT -s 10.252.172.43 -p tcp --dport 20079 -j ACCEPT
#iptables -A INPUT -s 121.43.155.222 -p tcp --dport 20079 -j ACCEPT

iptables -A INPUT -p tcp --dport 20079 -j DROP
