# 大额风险管理系统

## 项目简介
大额风险管理系统是一个基于Spring Boot 3的企业级风险管理系统,用于管理和控制大额交易风险。系统提供完整的用户权限管理、风险规则配置、交易监控等功能。

## 技术栈
- 后端框架: Spring Boot 3
- 安全框架: Spring Security + JWT
- 持久层框架: MyBatis
- 数据库: SQL Server 2022
- 缓存: Redis
- API文档: Swagger 2 + Knife4j
- 数据库连接池: Druid
- 日志框架: SLF4J + Logback

## 项目结构
```
src/
├── main/
│   ├── java/
│   │   └── com/ocbc/les/
│   │       ├── common/                # 公共组件
│   │       │   ├── config/            # 全局配置类
│   │       │   ├── exception/         # 全局异常处理
│   │       │   ├── response/          # 统一响应结构
│   │       ├── frame/                 # 框架功能
│   │       │   ├── security/          # 安全相关
│   │       │   └── swagger/           # Swagger配置
│   │       ├── modules/               # 业务模块
│   │       │   └── system/            # 系统管理模块
│   │       └── RiskManagementApplication.java
│   └── resources/
│       ├── application.yml            # 主配置文件
│       ├── application-dev.yml        # 开发环境配置
│       └── db/                        # 数据库脚本
│           ├── schema.sql             # 表结构
│           └── dml.sql                # 初始数据
```

## 功能特性
- 用户认证与授权
- 角色权限管理
- 菜单管理
- 组织架构管理
- 风险规则配置
- 交易监控
- 操作审计

## 开发环境要求
- JDK 17+
- Maven 3.8+
- SQL Server 2022
- Redis 6.0+

## 快速开始

### 1. 环境准备
- 安装JDK 17
- 安装Maven 3.8
- 安装SQL Server 2022
- 安装Redis 6.0

### 2. 数据库配置
- 创建数据库
- 执行 `db/schema.sql` 创建表结构
- 执行 `db/dml.sql` 初始化数据

### 3. 配置文件
- 修改 `application-dev.yml` 中的数据库连接信息
- 修改 `application-dev.yml` 中的Redis连接信息

### 4. 启动项目
```bash
mvn spring-boot:run
```

### 5. 访问接口
- 接口文档: http://localhost:8080/doc.html
- 默认账号: admin
- 默认密码: 123456

## 开发规范
- 遵循阿里巴巴Java开发手册
- 使用统一的代码格式化工具
- 提交代码前进行代码审查
- 编写单元测试用例

## 部署说明
1. 打包
```bash
mvn clean package
```

2. 运行
```bash
java -jar target/risk-management-1.0.0.jar
```

## 版本历史
- v1.0.0 (2024-03-20)
  - 初始化项目
  - 完成基础框架搭建
  - 实现用户认证与授权
  - 完成系统管理模块

## 贡献指南
1. Fork 项目
2. 创建特性分支
3. 提交代码
4. 创建Pull Request

## 许可证
[MIT License](LICENSE) 