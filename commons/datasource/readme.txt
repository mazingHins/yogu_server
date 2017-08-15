
1：pom.xml 中引入 commons-datasource

2：properties文件中设置好所需的参数，参数请参考：applicationContext-datasource.xml 文件中的内容

3：设定 TypeAlias	使用 @TheTypeAlias 注解来确定
	先确定 applicationContext-datasource.xml配置文件中 id="myBaitsSessionFactory" 的 typeAliasAnnoBasicPackage 值
	可以在某个包的package-info中使用@TheTypeAlias，表示这个包下的所有类都是 typeAlias
	也可以在某个具体的类上使用@TheTypeAlias，表示该类是一个 typeAlias

4：设定DAO	使用@TheDataDao 注解来确定
	新建一个 Interface，并加上 @TheDataDao 注解
	定义相关的接口方法

5：书写SQL XML 文件
	在resources下新建sql目录
	在sql下新建一个.xml 文件，文件名与 《4》中创建的DAO民称一致
	在xml中书写对应的SQL语句，（ID需要与接口中的方法一致）


#### 具体请参考 test 源文件夹中的文件、配置
