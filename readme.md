<p align="center">
  <h2 align="center">糊涂点餐</h2>
  <p align="center">
    <img src="https://img.shields.io/badge/Spring Boot-2.2.0RELEASE-blue"/>
    <img src="https://img.shields.io/badge/Spring Security-yellow"/>
    <img src="https://img.shields.io/badge/Vue 3.0-green"/>
    <img src="https://img.shields.io/badge/Uni APP-gree"/>
    <img src="https://img.shields.io/badge/Eletron-blue"/>
    <img src="https://img.shields.io/badge/Redis-red"/>
    <img src="https://img.shields.io/badge/JJWT-orange"/>
    <img src="https://img.shields.io/badge/Mybatis Plus-red"/>
    <img src="https://img.shields.io/badge/Netty-black"/>
  </p>
  <p align="center">
    〽️糊涂点餐，一款基于springboot2+mybatis-plus的点餐系统。
    <br/>
  </p>
</p>

## 相关仓库

小程序：https://github.com/Isnott/hutu-order-web<br>
叫号屏：https://github.com/Isnott/hutu-order-board<br>
管理后台：https://github.com/Isnott/hutu-order-admin

## 文档目录

  - [技术选型](#技术选型)
  - [开发进度](#开发进度) 
  - [运行](#运行)
  - [文件目录](#文件目录)

### 技术选型

协议：`Http`、`WebSocket`<br>
服务端：`SpringBoot2.x`、`Netty4.x`、`JJWT`、`Spring Security`、`Redis`、`Mybatis-plus`、`AspectJ`、`Spring-Cloud-OpenFeign`<br>
小程序端:`uni-app` `Vite` `Vue3.x` <br>
商家管理：`Vite` `Vue3.x`<br>
叫号屏：`Vite` `Vue3.x` `Electron`<br>

### 开发进度

目前正进行全面的重构，已重构的内容：
1. 将各子项目从此仓库中剥离到各自的代码仓进行管理
2. 小程序端完成静态mock数据的重构
3. 管理端完成基础的业务管理功能+RABC权限设计
4. 叫号屏完成叫号取餐的接口暴露。

### 运行

0. 添加项目中的nginx配置到你的nginx应用中
1. 运行redis、mysql数据库
2. 执行db/hutu-order.sql
3. 运行HutuXXApplication.java

### 文件目录

```
├─db # 数据库脚本（mysql）
├─hutu-app # REST API模块（api+管理+文件对象存储）
├─hutu-web-socket # ws通信模块（Netty）
├─hutu-feign # openFeign api
├─hutu-model # 实体
├─hutu-external # 外部系统api
├─hutu-common # 通用模块
├─hutu-security # 权限验证模块
├─hutu-service # 服务类模块
├─nginx # nginx配置案例
```






