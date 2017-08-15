
#### commons	存放公共组件库

	其下的每一个project都可以独立存在，不受框架的影响，可随时迁移到其他项目中去使用

commons
 |--cache		缓存
 |--datasource	路由数据源，约定接口走主或从数据库
 |--json		JSON统一工具，包括jaxrs-json插件
 |--log			集中式日志
 |--server		与框架有关的公共服务代码，如健康检测、mock等
 |--utils		工具库
 |--validation	BeanValidation支持、自定义



#### services	存放当前项目的业务代码（按‘功能块’区分）

	其下每个project都是独立的一个域（可以独立拿出来发布，也可以整合到一个域中发布），因此代码必须没有关联
	各自project下都包含各自所需的各个层级的代码（resource、service、dao ==）
	xcode作为公共代码库的形式存在

services
 |--config		配置类
 |--order		订单
 |--pay			支付
 |--store		门店
 |--user		用户
 |--xcloud		公共代码库：云功能相关		<<**>>
 |--xcore		公共代码库、权限校验		<<**>>
 
 
 
#### web	

	war project，引入commons、services中的代码，以http的形式放出API

web
 |--api					主API，各项业务逻辑接口（此处不存放任何逻辑代码，逻辑代码应当放到services下的project中），这里主要是做一些框架性的代码
 |--backend-admin		平台级，管理后台
 |--backend-store	商户自己管理，后台
 |--test-api			（暂无）测试、文档API，罗列当前系统可用接口、入参、返回值等；罗列出文档（需按约定开发）
 |--log-server			（暂无）
 

