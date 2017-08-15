
## 增加健康检测

1：实现com.mazing.commons.server.pulse.PulseService接口
2：配置com.mazing.commons.server.pulse.PulseServlet到web.xml中


## 增加MOCK功能

1：实现com.mazing.commons.server.mock.MockProvider适配接口（可以有多个）
2：配置com.mazing.commons.server.mock.MockManageServlet到web.xml中（用于管理mock数据）
3：将com.mazing.commons.server.mock.MockTogetherFilter注册到jax-rs中去

