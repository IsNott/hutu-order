<p align="center">
  <h2 align="center">糊涂点餐</h2>
  <p align="center">
    <img src="https://img.shields.io/badge/Spring Boot-2.2.0RELEASE-blue"/>
    <img src="https://img.shields.io/badge/Spring Security-yellow"/>
    <img src="https://img.shields.io/badge/Vue 2.0-green"/>
    <img src="https://img.shields.io/badge/Uni APP-gree"/>
    <img src="https://img.shields.io/badge/Eletron-blue"/>
    <img src="https://img.shields.io/badge/Redis-red"/>
    <img src="https://img.shields.io/badge/SaToken-orange"/>
    <img src="https://img.shields.io/badge/Mybatis Plus-red"/>
    <img src="https://img.shields.io/badge/Netty-black"/>
  </p>
  <p align="center">
    〽️糊涂点餐，一款基于uniapp+vue2+springboot2+mybatis-plus的点餐系统，适用于小型餐饮店、咖啡饮品店等业务场景。
    用于创建覆盖多端的点餐系统，包括小程序、web端，覆盖叫号屏、打印和外卖等多种业务场景。
    <br/>
    <br/>
<!--     <a href="https://github.com/IsNott/hutu-order?tab=readme-ov-file#%E5%95%86%E4%B8%9A%E5%90%88%E4%BD%9C">商业合作</a>
    · -->
<!--     <a href="https://github.com/IsNott/hutu-order?tab=readme-ov-file#%E4%BA%A4%E6%B5%81%E7%BE%A4%E8%81%8A">加入群聊</a> 
    ·-->
    <a href="https://github.com/IsNott/hutu-order/issues">提个Issues</a>
  </p>
</p>

## 文档目录

- [开发架构](#开发架构)
- [技术选型](#技术选型)
    - [运行环境](#运行环境)
    - [运行项目](#运行项目)
    - [对接系统](#对接系统)
    - [文件目录](#文件目录)
- [系统预览](#系统预览)
    - [系统截图](#系统截图)
- [贡献者](#贡献者)
    - [如何参与](#如何参与)
- [版本控制](#版本控制)

### 技术选型

小程序端使用`uni-app`进行开发，方便后续可同步部署到支付宝、微信小程序、H5、app端<br>
商家管理：`vue.js 2.x`<br>
服务端框架技术：`SpringBoot2.x`、`Vue2.x`、`Netty4.x`
`Sa-token`、`Spring Security`、`Redis`、`Mybatis-plus`、`AspectJ`、`Spring-Cloud-OpenFeign`<br>
叫号屏使用：`Vue2.x`、`Eletron`<br>
使用的协议：`Http`、`WebSocket`

#### 运行环境

| Name    | version  |
|---------|----------|
| JDK     | 1.8      |
| Node.js | 16       |

#### 运行项目

- 小程序服务端：`hutu-web模块下的api模块`：`HutuApiApplication`
- 管理服务端：`hutu-web模块下的admin模块`：`HutuApiApplication`
- WebSocket：`hutu-webSocket模块`：`HutuWebSocketApplication`
- 管理后台：`vue-hutu-admin`: `npm run serve`
- 叫号大屏：`vue-hutu-stuff`: `npm run electron:serve`

#### 对接系统

已开发

- 微信小程序登录

开发中

- 叫号大屏
- 小票打印

将开发

- 语言播报
- 同城跑腿

### 文件目录

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

### 开发架构

图片制作中

### 系统预览

待更新

#### 系统截图

待更新

### 贡献者

待更新

#### 如何参与

假如您有兴趣参与到项目的开发中，可以通过以下方式：

1. Fork 本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的改动 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到远程仓库 (`git push origin feature/AmazingFeature`)
5. 提交一个 Pull Request
6. 等待审核
7. 通过审核后，合并到主分支
8. 恭喜您，您已经成为项目的一份子

### 版本控制

使用Git进行版本控制，目前仅存在*master*分支。

### 作者

GitHub:[IsNott](https://github.com/IsNott) &ensp;
掘金:[IsNott](https://juejin.cn/user/3037330789107972)  &ensp;

### 商业合作

待更新





