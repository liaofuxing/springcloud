# spring cloud

#### 项目介绍
基于spring cloud 实现的微服务脚手架工具，包含简洁美观的Vue前端项目，非常适合中小企业在此项目上做二次开发（OA，商城等类型项目），也可以当作spring cloud入门项目加以学习

 **架构技术：** 基于 spring boot 2.2.2.RELEASE，spring cloud Hoxton.SR3 构建，使用zuul、eureka、hystrix、feign微服务架构体系，spring security 作为安全模块

#### 项目亮点
功能完善，已集成用户管理，代码生成器等功能，有配套的前端项目，是不错的spring cloud 项目脚手架，后期将拆分为单独的spring boot项目
ps：代码生成器与本项目配套，依赖本项目common模块，并且基于spring data jpa，并不能生成 mybatis 类型代码，代码生成器有可视化页面

#### 项目前端
前端Vue项目地址[vue-admin-template](http://gitee.com/liaofuxing/vue-admin-template) ps：前端项目框架并不是本人自己开发，基于[@花裤衩](http://gitee.com/panjiachen/vue-admin-template.git)开源项目修改而来，本人只是实现前后端登录，以及基于数据库Vue动态路由等功能

#### github地址

[后端项目github跳转地址](https://github.com/liaofuxing/springcloud)

[前端项目github跳转地址](https://github.com/liaofuxing/vue-admin-template)

#### 软件架构图
![项目架构图](https://images.gitee.com/uploads/images/2020/0321/115010_6a559f47_2025409.png "项目架构图.png")


#### 功能

1. 用户管理
2. 部门管理
3. 角色管理
4. 根据用户角色动态菜单管理
5. 用户登录登出，登录超时，强制下线
6. 代码生成器（独立版本:v0.8）


#### 项目打包
1. 本项目是聚合项目，需要在根目录下运行mvn install 将项目整体打包入本地厂库，然后再在根目录下运行mvn package即可
2. 代码生成器项目需要单独打包
3. 前端项目单独打包

#### 项目最小化部署
| 模块名              | 模块描述          | 是否必须 | 最小数量 |
| ------------------ | ---------------- | -------- | -------- |
| api-gateway        | zuul网关         | 是       | 1        |
| common             | 公共模块         | 是       | 1        |
| eureka             | 注册中心         | 是       | 1        |
| system             | 运营平台         | 是       | 1        |
| mall               | --              | 否       | 0        |
| generate-code      | 代码生成器       | 否       | 0        |
| vue-admin-template | 运营平台前端项目 | 是       | 1        |


