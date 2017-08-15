
》》》》开始

1：导入project到IDE中

2：新建一个maven启动配置
2.1：配置goals为：clean tomcat7:run

3：启动该配置（启动tomcat，8080端口）


可选的goals配置参数有：

-Dapi.mock.file=<...file>
指向一个文件
表示开启mock模式

-Dtomcat.port=8090
修改tomcat端口


完整例子：

clean tomcat7:run -Dtomcat.port=8090 -Dapi.mock.file=C://mock.txt



》》》》DB配置

以maven tomcat插件启动，读取的是 resources/META-INF/mazing-db.properties 文件

如果 maven打包时，带上 -Ppro_build 参数
	会使用 src/main/pro/META-INF/mazing-db.properties 代替


》》》》日志配置（同上）
	-Ppro_build 时使用 src/main/pro/META-INF/log4j.properties 代替


