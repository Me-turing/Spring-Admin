# Spring管理系统

## 项目介绍

xxxxxxxxxxxxxxxxxxx

### 技术栈

- 后端框架: Spring Boot 3.0.0
- 安全框架: Spring Security + JWT
- 持久层框架: MyBatis
- 数据库: SQLServer 2022
- 缓存: Redis
- API文档: Knife4j
- 项目管理: Maven

### 主要功能

- 用户认证与授权
- 系统管理
- 审计日志
- 性能监控

## 开发进度

### 已完成功能
- [x] 项目基础框架搭建
- [x] 用户认证与授权模块
  - JWT token认证
  - 基于角色的权限控制
  - 自定义密码加密(SM4)
- [x] 系统管理模块
  - 用户管理
  - 角色管理
  - 菜单管理
- [x] 基础功能
  - 统一响应处理
  - 全局异常处理
  - 操作日志记录
  - 接口文档生成

### 进行中功能
- [ ] 审计日志模块
  - 操作日志记录
  - 登录日志记录
  - 审计追踪
- [ ] 性能优化
  - Redis缓存集成
  - 数据库优化
  - 接口性能优化

### 待开发功能
- [ ] 规则引擎模块
  - 规则配置界面
  - 规则执行引擎
  - 规则版本管理
- [ ] 监控告警模块
  - 系统监控
  - 性能监控
  - 告警配置

### 已知问题
1. 密码加密问题
   - 问题: 默认密码加密方式与系统要求不匹配
   - 解决方案: 实现自定义PasswordEncoder支持SM4加密

2. 权限控制问题
   - 问题: 部分接口权限控制不完善
   - 解决方案: 完善Spring Security配置,细化权限控制

3. 缓存问题
   - 问题: Redis缓存未完全集成
   - 解决方案: 实现多级缓存架构

## 设计文档

- [系统架构设计](docs/architecture.md)
- [数据库设计](docs/database.md)
- [接口设计](docs/api.md)
- [安全设计](docs/security.md)
- [性能设计](docs/performance.md)

## 环境要求

- JDK: 17+
- Maven: 3.8+
- SQLServer: 2022
- Redis: 6.0+
- IDE: IntelliJ IDEA 2023+

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/your-username/risk-management.git
cd risk-management
```

### 2. 配置数据库

1. 创建数据库
```sql
CREATE DATABASE risk_management;
```

2. 修改数据库配置
```yaml
spring:
  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=risk_management
    username: your-username
    password: your-password
```

### 3. 配置Redis

修改Redis配置
```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: your-password
```

### 4. 启动项目

```bash
mvn spring-boot:run
```

访问地址: http://localhost:8080

## 项目结构

```
risk-management/
├── docs/                           # 项目文档
│   ├── architecture.md             # 系统架构设计
│   ├── database.md                 # 数据库设计
│   ├── api.md                      # 接口设计
│   ├── security.md                 # 安全设计
│   └── performance.md              # 性能设计
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ocbc/les/
│   │   │       ├── common/                # 公共组件
│   │   │       │   ├── config/            # 全局配置
│   │   │       │   │   ├── RedisConfig.java          # Redis配置
│   │   │       │   │   ├── SecurityConfig.java       # 安全配置
│   │   │       │   │   ├── SwaggerConfig.java        # Swagger配置
│   │   │       │   │   └── WebConfig.java            # Web配置
│   │   │       │   ├── exception/         # 异常处理
│   │   │       │   │   ├── GlobalExceptionHandler.java  # 全局异常处理
│   │   │       │   │   └── BusinessException.java       # 业务异常
│   │   │       │   ├── util/              # 工具类
│   │   │       │   │   ├── JwtUtil.java              # JWT工具类
│   │   │       │   │   ├── RedisUtil.java           # Redis工具类
│   │   │       │   │   └── SecurityUtil.java        # 安全工具类
│   │   │       │   ├── aspect/            # 切面
│   │   │       │   │   ├── LogAspect.java           # 日志切面
│   │   │       │   │   └── SecurityAspect.java      # 安全切面
│   │   │       │   ├── annotation/        # 注解
│   │   │       │   │   ├── Log.java                 # 日志注解
│   │   │       │   │   └── Security.java            # 安全注解
│   │   │       │   └── response/          # 响应结构
│   │   │       │       ├── Result.java              # 统一响应对象
│   │   │       │       └── ResultCode.java          # 响应码
│   │   │       ├── frame/                 # 框架功能
│   │   │       │   ├── security/          # 安全相关
│   │   │       │   │   ├── filter/                  # 过滤器
│   │   │       │   │   ├── handler/                 # 处理器
│   │   │       │   │   └── service/                 # 安全服务
│   │   │       │   ├── redis/             # Redis相关
│   │   │       │   │   ├── config/                  # Redis配置
│   │   │       │   │   └── service/                 # Redis服务
│   │   │       │   └── mybatis/           # MyBatis相关
│   │   │       │       ├── config/                  # MyBatis配置
│   │   │       │       └── service/                 # MyBatis服务
│   │   │       └── modules/               # 业务模块
│   │   │           ├── system/            # 系统管理
│   │   │           │   ├── controller/             # 控制器
│   │   │           │   ├── service/                # 服务层
│   │   │           │   ├── mapper/                 # 数据访问层
│   │   │           │   ├── entity/                 # 实体类
│   │   │           │   ├── dto/                    # 数据传输对象
│   │   │           │   └── vo/                     # 视图对象
│   │   │           └── risk/              # 风险管理
│   │   │               ├── controller/             # 控制器
│   │   │               ├── service/                # 服务层
│   │   │               ├── mapper/                 # 数据访问层
│   │   │               ├── entity/                 # 实体类
│   │   │               ├── dto/                    # 数据传输对象
│   │   │               └── vo/                     # 视图对象
│   │   └── resources/
│   │       ├── application.yml            # 主配置文件
│   │       ├── application-dev.yml        # 开发环境配置
│   │       ├── application-test.yml       # 测试环境配置
│   │       ├── application-prod.yml       # 生产环境配置
│   │       ├── mapper/                    # MyBatis映射文件
│   │       │   ├── system/                # 系统模块映射
│   │       │   └── risk/                  # 风险模块映射
│   │       └── static/                    # 静态资源
│   └── test/                              # 测试目录
│       ├── java/                          # 测试代码
│       └── resources/                     # 测试资源
├── scripts/                               # 脚本目录
│   ├── sql/                              # SQL脚本
│   │   ├── schema.sql                    # 表结构
│   │   └── data.sql                      # 初始数据
│   └── deploy/                           # 部署脚本
│       ├── start.sh                      # 启动脚本
│       └── stop.sh                       # 停止脚本
├── pom.xml                               # Maven配置
└── README.md                             # 项目说明
```

## 框架文件说明

### 配置文件
- application.yml: 主配置文件,包含基础配置
- application-dev.yml: 开发环境配置
- application-test.yml: 测试环境配置
- application-prod.yml: 生产环境配置

### 公共组件
1. 全局配置(config)
   - RedisConfig: Redis配置类
   - SecurityConfig: 安全配置类
   - SwaggerConfig: Swagger配置类
   - WebConfig: Web配置类

2. 异常处理(exception)
   - GlobalExceptionHandler: 全局异常处理器
   - BusinessException: 业务异常类

3. 工具类(util)
   - JwtUtil: JWT工具类
   - RedisUtil: Redis工具类
   - SecurityUtil: 安全工具类

4. 切面(aspect)
   - LogAspect: 日志切面
   - SecurityAspect: 安全切面

5. 注解(annotation)
   - Log: 日志注解
   - Security: 安全注解

6. 响应结构(response)
   - Result: 统一响应对象
   - ResultCode: 响应码

### 框架功能
1. 安全框架(security)
   - filter: 安全过滤器
   - handler: 安全处理器
   - service: 安全服务

2. Redis框架(redis)
   - config: Redis配置
   - service: Redis服务

3. MyBatis框架(mybatis)
   - config: MyBatis配置
   - service: MyBatis服务

### 业务模块
1. 系统管理(system)
   - controller: 控制器层
   - service: 服务层
   - mapper: 数据访问层
   - entity: 实体类
   - dto: 数据传输对象
   - vo: 视图对象

2. 风险管理(risk)
   - controller: 控制器层
   - service: 服务层
   - mapper: 数据访问层
   - entity: 实体类
   - dto: 数据传输对象
   - vo: 视图对象

## 开发规范

### 代码规范

- 遵循阿里巴巴Java开发手册
- 使用统一的代码格式化工具
- 保持代码简洁清晰

### 命名规范

- 类名: 使用PascalCase
- 方法名: 使用camelCase
- 变量名: 使用camelCase
- 常量名: 使用UPPER_SNAKE_CASE
- 包名: 使用小写字母
- 数据库表名: 使用snake_case

### 注释规范

- 类注释: 说明类的功能、作者、日期
- 方法注释: 说明方法的功能、参数、返回值
- 关键代码注释: 说明代码的作用和实现逻辑

## 部署说明

### 打包命令

```bash
mvn clean package -DskipTests
```

### 部署步骤

1. 准备环境
   - 安装JDK 17+
   - 安装SQLServer 2022
   - 安装Redis 6.0+

2. 配置应用
   - 修改application-prod.yml
   - 配置数据库连接
   - 配置Redis连接

3. 启动应用
```bash
java -jar risk-management.jar
```

### 配置说明

主要配置项:
- 数据库连接配置
- Redis连接配置
- JWT配置
- 日志配置
- 安全配置

## 接口文档

访问地址: http://localhost:8080/doc.html

## 贡献指南

1. Fork 项目
2. 创建特性分支
3. 提交代码
4. 创建Pull Request

## 许可证

[MIT License](LICENSE) 
