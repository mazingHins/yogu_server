# redis端口访问限制
# 只开放给内部服务器访问，因此这里设置防火墙
# ten 2016/3/9

iptables -A INPUT -s 127.0.0.1 -p tcp --dport 6379 -j ACCEPT

iptables -A INPUT -s 10.160.84.98 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 112.124.59.179 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 10.162.105.24 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 114.215.169.213 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 10.46.70.246 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 120.55.191.38 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 10.46.68.242 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 120.55.191.53 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 10.168.89.157 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 121.40.180.33 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 10.46.73.85 -p tcp --dport 6379 -j ACCEPT
iptables -A INPUT -s 120.27.128.222 -p tcp --dport 6379 -j ACCEPT

iptables -A INPUT -p tcp --dport 6379 -j DROP




iptables -A INPUT -s 127.0.0.1 -p tcp --dport 6479 -j ACCEPT

iptables -A INPUT -s 10.160.84.98 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 112.124.59.179 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 10.162.105.24 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 114.215.169.213 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 10.46.70.246 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 120.55.191.38 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 10.46.68.242 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 120.55.191.53 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 10.168.89.157 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 121.40.180.33 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 10.46.73.85 -p tcp --dport 6479 -j ACCEPT
iptables -A INPUT -s 120.27.128.222 -p tcp --dport 6479 -j ACCEPT

iptables -A INPUT -p tcp --dport 6479 -j DROP
