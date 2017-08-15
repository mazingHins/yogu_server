# 读取微信公众号 access token 的服务端口是 20090
# 只开放给内部服务器访问，因此这里设置防火墙
# access token 的作用用于分享的时候可以使用微信 jsapi 时的签名
# 参考：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
# 访问URL：curl "http://121.40.81.176:20090/"
# ten 2015/1/7
iptables -A INPUT -s 127.0.0.1 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.168.35.60 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 121.40.160.217 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.168.178.202 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 121.41.35.67 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.46.73.85 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 120.27.128.222 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.161.226.55 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 114.215.193.126 -p tcp --dport 20090 -j ACCEPT

iptables -A INPUT -s 10.160.84.98 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 112.124.59.179 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.162.105.24 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 114.215.169.213 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.46.70.246 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 120.55.191.38 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.46.68.242 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 120.55.191.53 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.168.89.157 -p tcp --dport 20090 -j ACCEPT

iptables -A INPUT -s 121.40.180.33 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 10.252.172.43 -p tcp --dport 20090 -j ACCEPT
iptables -A INPUT -s 121.43.155.222 -p tcp --dport 20090 -j ACCEPT

iptables -A INPUT -p tcp --dport 20090 -j DROP
