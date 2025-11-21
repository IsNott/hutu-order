<p align="center">
  <h2 align="center">糊涂点餐</h2>
  <p align="center">
    糊涂点餐，一款基于uniapp+vue2+springboot2+mybatis-plus的点餐系统，适用于小型餐饮店、咖啡饮品店等业务场景。
    <br/>
    致力于创建覆盖多端的点餐系统，包括小程序、web端。囊括叫号屏、打印和外卖等多种业务场景。
    <br/>
    <a href="https://github.com/shaojintian/Best_README_template">商业合作</a>
    ·
    <a href="https://github.com/shaojintian/Best_README_template/issues">加入群聊</a>
    ·
    <a href="https://github.com/shaojintian/Best_README_template/issues">提个Issues</a>
  </p>
</p>

## 文档目录

- [开发架构](#开发架构)
- [技术选型](#技术选型)
    - [对接系统](#对接系统)
    - [文件目录](#文件目录)
- [系统预览](#系统预览)
    - [系统截图](#系统截图)
- [贡献者](#贡献者)
    - [如何参与](#如何参与)
- [版本控制](#版本控制)
- [联系作者](#作者)
    - [商业合作](#商业合作)
    - [交流群聊](#交流群聊)

### 技术选型

小程序端使用`uni-app`进行开发，方便后续可同步部署到支付宝、微信小程序、H5、app端<br>
商家管理：`vue.js 2.x`<br>
服务端框架技术：`SpringBoot2.x`、`Vue2.x`、`Netty4.x`
`Sa-token`、`Spring Security`、`Redis`、`Mybatis-plus`、`AspectJ`、`Spring-Cloud-OpenFeign`<br>
叫号屏使用：`Vue2.x`、`Eletron`<br>
使用的协议：`Http`、`WebSocket`

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
├─hutu-app # REST API模块（api+管理）
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

- IsNott

#### 如何参与

假如您有兴趣参与到项目的开发中，可以通过以下方式：

1. Fork 本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的改动 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到远程仓库 (`git push origin feature/AmazingFeature`)
5. 提交一个 Pull Request
6. 等待作者审核
7. 通过审核后，合并到主分支
8. 恭喜您，您已经成为项目的一份子

### 版本控制

使用Git进行版本控制，目前仅存在*master*分支。

### 作者

GitHub:[IsNott](https://github.com/IsNott) &ensp;
Gitee: [糊涂](https://gitee.com/isnott) &ensp;
掘金:[IsNott](https://juejin.cn/user/3037330789107972)  &ensp;
知乎:[IsNott](https://www.zhihu.com/people/zou-a-p) &ensp;

### 商业合作

如您有落地实施、销售、商业合作等意见或想法，请扫描下图二维码加我微信，本人会尽快回复您的消息。

### 交流群聊

假如您有交流项目经验的想法或想要交流对于项目的意见建议，请扫描下图二维码加作者微信，备注：`点餐系统`，看到消息我会拉您进入交流群，
期待与您的交流。






