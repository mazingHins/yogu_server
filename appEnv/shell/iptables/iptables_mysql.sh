# mysql端口访问限制
# 只开放给内部服务器访问，因此这里设置防火墙
# jfan 2016-06-03

iptables -A INPUT -s 127.0.0.1 -p tcp --dport 3306 -j ACCEPT

iptables -A INPUT -s 10.46.70.246 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 120.55.191.38 -p tcp --dport 3306 -j ACCEPT

iptables -A INPUT -s 10.46.68.242 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 120.55.191.53 -p tcp --dport 3306 -j ACCEPT

iptables -A INPUT -s 10.160.84.98 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 112.124.59.179 -p tcp --dport 3306 -j ACCEPT

iptables -A INPUT -s 10.168.89.157 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 121.40.180.33 -p tcp --dport 3306 -j ACCEPT

iptables -A INPUT -s 10.162.105.24 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 114.215.169.213 -p tcp --dport 3306 -j ACCEPT

# 预发布
iptables -A INPUT -s 10.46.73.85 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 120.27.128.222 -p tcp --dport 3306 -j ACCEPT
# GIT
iptables -A INPUT -s 10.171.244.29 -p tcp --dport 3306 -j ACCEPT
iptables -A INPUT -s 121.40.81.176 -p tcp --dport 3306 -j ACCEPT


iptables -A INPUT -p tcp --dport 3306 -j DROP
