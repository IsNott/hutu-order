# 前序
糊涂点餐，一个基于uniapp+vue2+springboot2+mybatis-plus的点餐系统，适用于小型餐饮店、咖啡饮品店等业务场景。

## 在线预览

待部署。

## 项目文档

待更新。

## 技术选型

商家后台管理端使用vue.js 2.x<br>
小程序端使用uni-app，方便后续可同步部署到支付宝、微信小程序、H5、app端<br>
服务端使用的框架技术：SpringBoot2.x、Vue2.x、Netty4.x<br>
Sa-token、Spring Security、Redis、Mybatis-plus、AspectJ、Feign<br>
项目中使用的协议：Http、WebSocket

## 对接系统

已开发
- 微信小程序登录

开发中
- 叫号大屏
- 小票打印

将开发
- 语言播报
- 同城跑腿

## 系统模块

```
├─db # 数据库脚本（mysql）
├─hutu-web # REST API模块（api+管理）
├─hutu-webSocket # ws通信模块（Netty）
├─hutu-feign # 跨应用调用api（openFeign）
├─hutu-model # 实体
├─hutu-external # 外部系统api（支付宝、微信）
├─hutu-common # 通用模块
├─hutu-security # 权限验证模块
├─hutu-service # 服务类模块
├─uniapp-hutu-user # 用户小程序端
├─vue-hutu-admin # 后台管理web端
├─vue-hutu-stuff # 商家叫号屏
```
## 系统截图

待更新

## 运行项目

待更新

## 开发规范

对象模型

|对象| 作用              |是否强制|命名|
|--|-----------------|--|--|
|VO(View Object)| 服务端返回前端的数据对象    |是|业务逻辑名称+Vo|
|DTO(Data transfer Object)| 前后端、控制层与服务层传输对象 |是|业务逻辑名称+DTO|
|PO(Persistent Object)| ORM框架中的持久化数据对象  |是|与数据表一一对应|

开发守则

1. 永不嵌套。语句嵌套不超过三层（最多三层），当使用语句嵌套时还未完成业务逻辑，需考虑重构或进行抽象。
2. 脱离注释。协同开发时不要强制依赖于前人的注释。
3. 提前返回。尽量多地使用卫语句，将程序提前结束返回，将真正的业务逻辑放在最后。
4. 先做出来。代码不需要一次性就达到完美地运行、效率最高目的。
5. 打好日志。当程序存在静默处理（定时任务、异步处理等得不到明确的返回结果任务）时需显式地打印日志。
6. 明确提交。Git提交信息需最大程度地精炼出当前commit包含的修改内容。


## 支持项目

如果您恰巧有餐饮小程序开发经验（包括前端、服务端、UI设计）<br>
目前开发贡献仅有作者一人，如有兴趣可提供PR或Issues参与此项目的开发，谢谢。
