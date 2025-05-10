## 框架介绍

quick（quickAdmin）是国密前后端分离快速开发平台，集成国密加解密插件，
软件层面完全符合等保测评要求，同时实现国产化机型、中间件、数据库适配，是您的不二之选！
技术框架与密码结合，让更多的人认识密码，使用密码；更是让前后分离“密”不可分。

采用SpringBoot+MybatisPlus+AntDesignVue+Vite 等更多组件及前沿技术开发，注释丰富，代码简洁，开箱即用！

## 快速启动

全栈工程师推荐idea

### 前端支撑

| 插件      | 版本  | 用途             |
|---------|-----|----------------|
| node.js | ≥18 | JavaScript运行环境 |

### 启动前端

```
npm install
```

```
npm run dev
```

### 后端支撑

| 插件     | 版本        | 用途     |
|--------|-----------|--------|
| jdk    | 17        | java环境 |
| lombok | idea内     | 代码简化插件 |
| maven  | 最新版       | 包管理工具  |
| redis  | 最新版       | 缓存库    |
| mysql  | 8.0 / 5.7 | 数据库    |

### 启动后端

开发工具内配置好maven并在代码中配置数据库即可启动

## 代码结构

quick3.0框架对代码以插件化的模式进行分包，使得包层级结构更加清晰合理，同时降低了耦合度，关于插件模块化开发的规范请查阅文档【quick开源文档——前端手册or后端手册——开发规范】板块。

```
quick
  |-quick-admin-web == 前端
    |-public == 基础静态文件
    |-src == 前端源代码
      |-api == API接口转发
      |-assets == 静态文件
      |-components == VUE组件
      |-config == 基础配置
      |-layout == 基础布局
      |-locales == 多语言配置
      |-router == 基础路由配置
      |-store == Pinia缓存配置
      |-style == 样式风格配置
      |-utils == 工具类
      |-views == 所有视图界面
  |-quick-common == 基础通用模块
  |-quick-plugin == 插件包
    |-quick-plugin-auth == 登录鉴权插件
    |-quick-plugin-biz == 业务功能插件
    |-quick-plugin-client == C端功能插件
    |-quick-plugin-dev == 开发工具插件
    |-quick-plugin-gen == 代码生成插件
    |-quick-plugin-mobile == 移动端管理插件
    |-quick-plugin-sys == 系统功能插件
  |-quick-plugin-api == 插件api包
    |-quick-plugin-auth-api == 登录鉴权插件api接口
    |-quick-plugin-biz-api == 业务功能插件api接口
    |-quick-plugin-client-api == C端功能插件api接口
    |-quick-plugin-dev-api == 开发工具插件api接口
    |-quick-plugin-gen == 代码生成插件api接口
    |-quick-plugin-mobile == 移动端管理插件api接口
    |-quick-plugin-sys-api == 系统功能插件api接口
  |-quick-web-app == 主启动模块
```

## 密码分步

| 功能     | 算法类型                 |
|--------|----------------------|
| 登录     | SM2前端加密，后端解密         |
| 登录登出日志 | SM2对登录登出日志做签名完整性保护存储 |
| 操作日志   | SM2对操作日志做签名完整性保护存储   |
| 用户密码   | SM3完整性保护存储，登录时做完整性校验 |
| 用户手机号  | SM4（cbc模式）加解密使用字段脱敏  |





