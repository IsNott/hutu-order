# 前序
糊涂点餐，一个基于uniapp+vue2+springboot2+mybatis-plus的点餐系统，适用于小型餐饮店、咖啡饮品店等业务场景。
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

将开发
- 叫号大屏
- 小票打印
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

## 支持项目

如果您恰巧有餐饮小程序开发经验（包括前端、服务端、UI设计）<br>
目前开发贡献仅有作者一人，如有兴趣可提供PR或Issues参与此项目的开发，谢谢。