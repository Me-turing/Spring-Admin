# OCBC LES Risk Management System

## 项目简介
OCBC LES风险管理系统是一个基于Spring Boot 3的企业级风险控制平台，提供全面的风险评估、监控和管理功能。

## 技术栈
- 后端框架：Spring Boot 3.2.3
- 安全框架：Spring Security + JWT
- 持久层框架：MyBatis 3.0.3
- 数据库：SQL Server 2022
- 缓存：Redis + Caffeine
- 连接池：Druid 1.2.24
- API文档：Knife4j 4.5.0
- 开发工具：Lombok

## 环境要求
- JDK 21+
- Maven 3.8+
- SQL Server 2022
- Redis 6.0+
- IDE推荐：IntelliJ IDEA 2023.3+

## 快速开始

### 1. 克隆项目
```bash
git clone https://github.com/your-username/risk-management.git
cd risk-management
```

### 2. 配置数据库
- 创建数据库
- 修改`application-dev.yml`中的数据库配置

### 3. 配置Redis
- 确保Redis服务已启动
- 修改`application-dev.yml`中的Redis配置

### 4. 编译运行
```bash
mvn clean install
mvn spring-boot:run
```

### 5. 访问系统
- 接口文档：http://localhost:8080/doc.html
- 监控页面：http://localhost:8080/druid

## 项目结构
```
src/
├── main/
│   ├── java/
│   │   └── com/ocbc/les/
│   │       ├── common/                # 公共组件
│   │       │   ├── config/            # 全局配置类
│   │       │   ├── exception/         # 全局异常处理
│   │       │   ├── util/              # 通用工具类
│   │       │   ├── constant/          # 常量定义
│   │       │   ├── aspect/            # 切面定义
│   │       │   ├── annotation/        # 自定义注解
│   │       │   └── response/          # 统一响应结构
│   │       ├── frame/                 # 框架功能
│   │       │   ├── security/          # 安全相关
│   │       │   ├── redis/             # Redis相关
│   │       │   ├── mybatis/           # MyBatis相关
│   │       │   └── swagger/           # Swagger配置
│   │       ├── modules/               # 业务模块
│   │       │   ├── system/            # 系统管理模块
│   │       │   └── risk/              # 风险管理模块
│   │       └── RiskManagementApplication.java
│   └── resources/
│       ├── application.yml            # 主配置文件
│       ├── application-dev.yml        # 开发环境配置
│       ├── application-test.yml       # 测试环境配置
│       ├── application-prod.yml       # 生产环境配置
│       └── mapper/                    # MyBatis映射文件
└── test/                             # 测试代码
```

## 开发规范

### 代码规范
- 遵循阿里巴巴Java开发手册
- 使用统一的代码格式化工具
- 类名使用PascalCase
- 方法名使用camelCase
- 常量使用UPPER_SNAKE_CASE

### 提交规范
- feat: 新功能
- fix: 修复bug
- docs: 文档更新
- style: 代码格式调整
- refactor: 重构
- test: 测试相关
- chore: 构建过程或辅助工具的变动

### 分支管理
- main: 主分支，用于生产环境
- develop: 开发分支
- feature/*: 功能分支
- hotfix/*: 紧急修复分支
- release/*: 发布分支

## 部署说明

### 开发环境
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

### 测试环境
```bash
mvn spring-boot:run -Dspring.profiles.active=test
```

### 生产环境
```bash
mvn spring-boot:run -Dspring.profiles.active=prod
```

## 贡献指南
1. Fork 项目
2. 创建功能分支
3. 提交代码
4. 发起 Pull Request

## 许可证
[MIT License](LICENSE) 