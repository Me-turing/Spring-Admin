# 分布式ID生成器设计文档

## 主要功能点

### 1. 自定义ID格式支持
- 支持前缀(如AB)
- 支持日期格式(如yyyyMMdd)
- 支持补充位数(如6位)
- 支持批量获取数量(默认100)
- 最终格式如: AB20250326000001

### 2. 数据库存储
- 需要建表存储配置信息
- 包含字段:
  * entity_name: 实体类名
  * table_name: 表名
  * prefix: 前缀
  * date_format: 日期格式
  * padding_length: 补充位数
  * batch_size: 批量获取数量
  * current_id: 当前起始ID
  * current_date: 当前日期
  * last_update_time: 最后更新时间

### 3. 分布式锁
- 使用Redis实现分布式锁
- 防止多服务同时更新DB中的ID记录
- 锁粒度: 表名 + 日期

### 4. 本地缓存
- 使用Caffeine实现本地缓存
- 每次从DB获取一批ID存入本地
- 以表名为key存储
- 缓存失效时间: 1天
- 缓存失效处理: 重新从DB获取

## 工作流程

### 1. 插入时检查字段是否有ID注入注解
- 检查@DistributedId注解
- 读取注解配置信息

### 2. 读取注解配置,查询DB是否存在记录
- 根据表名查询配置记录
- 检查日期是否需要更新

### 3. DB不存在或日期变更:
- 加分布式锁
- 生成初始ID(如AB20250326000001)
- 写入DB配置
- 生成batchSize个ID存入本地缓存
- 释放分布式锁

### 4. DB存在且日期未变更:
- 加分布式锁
- 更新DB中的起始ID
- 生成新的batchSize个ID存入本地缓存
- 释放分布式锁

### 5. 从本地缓存获取ID
- 获取第一个ID注入字段
- 本地缓存数量减1

## 异常处理

### 1. 分布式锁获取失败
- 等待100ms后重试
- 最多重试3次
- 重试失败抛出异常

### 2. 本地缓存失效
- 重新从DB获取batchSize个ID
- 更新本地缓存
- 继续使用新获取的ID

### 3. 服务重启
- 本地缓存清空
- 重新从DB获取ID
- 保证ID连续性

### 4. 日期变更
- 检测到日期变更时重置ID
- 新日期从000001开始
- 更新DB中的current_date

## 注解设计

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedId {
    // 前缀
    String prefix() default "";
    
    // 日期格式
    String dateFormat() default "yyyyMMdd";
    
    // 补充位数
    int paddingLength() default 6;
    
    // 批量获取数量
    int batchSize() default 100;
}
```

## 使用示例

```java
public class Order {
    @DistributedId(prefix = "ORD", dateFormat = "yyyyMMdd", paddingLength = 6, batchSize = 100)
    private String orderId;
}
```

## 注意事项

### 1. 分布式锁设计
- 锁粒度: 表名 + 日期
- 例如: "order_20240326"
- 避免不同日期的ID生成互相影响
- 提高并发性能

### 2. 本地缓存处理
- 缓存失效直接重新获取
- 不保证ID连续性
- 简化逻辑,提高性能

### 3. 服务重启处理
- 重启后直接获取新的ID段
- 不保证ID连续性
- 避免复杂的恢复逻辑

### 4. 日期变更处理
- 每次生成ID时检查当前日期
- 日期变更时重置ID从000001开始
- 更新DB中的current_date

### 5. 批量获取数量限制
- 根据补充位数计算最大可获取数量
- 例如6位最多999999个
- 空间不足时的扩充方案(压缩补足位数):
  1. 压缩补足位数方案
     * 初始使用6位数字: 000001-999999
     * 数字用完时,第一位改为字母A: A00001-A99999
     * A用完时,第一位改为字母B: B00001-B99999
     * 以此类推...
     * 容量计算:
       - 6位数字: 999999个
       - 1位字母+5位数字: 26 * 99999 = 2599974个
       - 2位字母+4位数字: 26 * 26 * 9999 = 67499974个
     * 优点: 
       - ID长度保持不变
       - 不需要修改数据库结构
       - 不需要修改代码逻辑
       - 容量大大增加
     * 缺点:
       - 需要处理字母进位
       - 需要记录当前字母位置