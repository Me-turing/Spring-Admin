package com.ocbc.les;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * OCBC LES 风险管理系统启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@MapperScan("com.ocbc.les.modules.*.mapper")
public class RiskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiskManagementApplication.class, args);
    }

} 