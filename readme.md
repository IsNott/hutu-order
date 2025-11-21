<p align="center">
  <h2 align="center">糊涂点餐</h2>
  <p align="center">
    <img src="https://img.shields.io/badge/Spring Boot-2.2.0RELEASE-blue"/>
    <img src="https://img.shields.io/badge/Spring Security-yellow"/>
    <!-- <img src="https://img.shields.io/badge/Vue 2.0-green"/> -->
    <!-- <img src="https://img.shields.io/badge/Uni APP-gree"/> -->
    <!-- <img src="https://img.shields.io/badge/Eletron-blue"/> -->
    <img src="https://img.shields.io/badge/Redis-red"/>
    <img src="https://img.shields.io/badge/SaToken-orange"/>
    <img src="https://img.shields.io/badge/Mybatis Plus-red"/>
    <img src="https://img.shields.io/badge/Netty-black"/>
  </p>
  <p align="center">
    〽️糊涂点餐，一款基于springboot2+mybatis-plus的点餐系统，适用于小型餐饮店、咖啡饮品店等业务场景。
    用于创建覆盖多端的点餐系统，包括小程序、web端，覆盖叫号屏、打印和外卖等多种业务场景。
    <br/>
    <br/>
    <!-- <a href="https://github.com/IsNott/hutu-order/issues">提个Issues</a> -->
  </p>
</p>

## 文档目录

- [技术选型](#技术选型)
    <!-- - [开发进度](#开发进度) -->
    - [运行环境](#运行环境)
    - [运行项目](#运行项目)
    - [文件目录](#文件目录)
<!-- - [版本控制](#版本控制) -->

### 技术选型

<!-- 小程序端:`uni-app`，方便后续可同步部署到支付宝、微信小程序、H5端<br> -->
<!-- 商家管理：`vue.js 2.x`<br> -->
<!-- 叫号屏：`Vue2.x`、`Eletron`<br> -->
协议：`Http`、`WebSocket`<br>
服务端：`SpringBoot2.x`、`Vue2.x`、`Netty4.x`、`Sa-token`、`Spring Security`、`Redis`、`Mybatis-plus`、`AspectJ`、`Spring-Cloud-OpenFeign`<br>

<!-- #### 开发进度

已开发：<br>
  1. Api + uniapp-hutu-user：商品展示、用户登录（微信+ 本地账号登录）、用户点餐流程（除支付）
  2. Oss： 文件上传管理
  3. WebSocket + vue-hutu-stuff：叫号大屏显示、小票打印
  4. Admin + vue-hutu-admin: 基础登录，其余管理功能暂未开发

待开发：Admin管理功能<br>
待优化：用户点餐、叫号大屏显示、小票打印<br> -->

#### 运行环境

| Name    | version  |
|---------|----------|
| JDK     | 1.8      |
| Node.js | 16       |

### 文件目录

```
├─db # 数据库脚本（mysql）
├─hutu-app # REST API模块（api+管理+文件对象存储）
├─hutu-webSocket # ws通信模块（Netty）
├─hutu-feign # 跨应用调用api（openFeign）
├─hutu-model # 实体
├─hutu-external # 外部系统api（支付宝、微信）
├─hutu-common # 通用模块
├─hutu-security # 权限验证模块
├─hutu-service # 服务类模块
<!-- ├─uniapp-hutu-user # 用户小程序端 -->
<!-- ├─vue-hutu-admin # 后台管理web端 -->
<!-- ├─vue-hutu-stuff # 商家叫号屏 -->
```

#### 运行项目

- 小程序服务端：`hutu-app模块下的api模块`：`HutuApiApplication`
- 管理服务端：`hutu-app模块下的admin模块`：`HutuApiApplication`
- 文件对象存储：`hutu-app模块下的oss模块`：`HutuOssApplication`
- WebSocket：`hutu-webSocket模块`：`HutuWebSocketApplication`
<!-- - 管理后台：`vue-hutu-admin`: `npm run serve` -->






