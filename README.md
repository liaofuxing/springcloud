# spring cloud

#### 介绍
基于spring cloud 实现的微服务脚手架工具，主要用于初步了解spring cloud项目，包含一下模块
1.spring security 基于前后端分离json串登录，和短信验证码登录
2.spring social QQ 微信登录
3.新增基于spi服务的短息模块

#### 软件架构图
![项目架构图](https://images.gitee.com/uploads/images/2020/0307/142228_9cd47c8b_2025409.png "项目大体架构图.png")

Spring Security 过滤器链示意图
![Spring Security 过滤器链示意图](https://images.gitee.com/uploads/images/2020/0219/185107_c429d896_2025409.png "过滤器示意图.png")

#### 版本

0.0.1版本 2020-02-26 updates
1. 【update】update spring boot 2.0.0.RELEASE --> spring boot 2.2.2.RELEASE
2. 【update】update spring cloud Finchley.SR2 --> spring cloud Hoxton.SR1
3. 【add】添加日志系统日志
4. 【add】新增基于spi服务的短息模块
5. 【add】新增spring security登录模块，json格式登录
6. 【add】新增vue前端项目[vue-admin-template](http://gitee.com/liaofuxing/vue-admin-template) ps：基于 @花裤衩 [vue-admin-template](https://gitee.com/panjiachen/vue-admin-template.git)的项目改写

0.0.2版本 2020-02-28 updates
1. 【add】新增手机号短信验证码登录方式，路径user/smsCode-login
2. 【fix】优化security过滤器链
3. 【fix】优化spi短信接口

0.0.2.1版本 2020-03-07 updates
1. 【fix】优化token验证流程，token验证每次请求不用再查询数据库，查询redis比较验证，减少数据库访问次数
2. 【update】update spring cloud Hoxton.SR1 --> spring cloud Hoxton.SR3
3. 【add】 新增分页查询实例

0.0.3版本 2020-03-10 updates
1. 【fix】优化打包方式，现在可以在springcloud 路径下运行 mvn package 进行整体项目打包
2. 【add】新增hystrix熔断器
