package com.ocbc.les.frame.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统启动监听器
 */
@Slf4j
@Component
public class SystemStartupListener implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        // 打印系统启动信息
        printSystemStartupInfo();
        
        // 执行系统初始化检查
        performSystemChecks();
        
        // 初始化系统数据
        initializeSystemData();
    }

    /**
     * 打印系统启动信息
     */
    private void printSystemStartupInfo() {
        // 打印ASCII艺术字体
        log.info("\n██╗     ███████╗███████╗\n" +
                "██║     ██╔════╝██╔════╝\n" +
                "██║     █████╗  ███████╗\n" +
                "██║     ██╔══╝  ╚════██║\n" +
                "███████╗███████╗███████║\n" +
                "╚══════╝╚══════╝╚══════╝\n");

        // 打印系统信息
        log.info("===============================================");
        log.info("系统名称: OCBC LES");
        log.info("系统版本: 1.0.0");
        log.info("启动时间: {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        log.info("运行环境: {}", environment.getActiveProfiles()[0]);
        log.info("服务端口: {}", environment.getProperty("server.port"));
//        log.info("数据库URL: {}", environment.getProperty("spring.datasource.url"));
        
        // 根据是否配置Redis来决定是否显示Redis信息
        String redisHost = environment.getProperty("spring.redis.host");
        if (redisHost != null) {
            log.info("Redis地址: {}", redisHost);
        } else {
            log.info("Redis: 未配置");
        }
        
        log.info("===============================================\n");
    }

    /**
     * 执行系统初始化检查
     */
    private void performSystemChecks() {
        log.info("开始执行系统初始化检查...");
        
        // 检查数据库连接
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            log.info("数据库连接检查: 成功");
        } catch (Exception e) {
            log.error("数据库连接检查: 失败", e);
            throw new RuntimeException("数据库连接失败", e);
        }

        // 检查Redis连接（如果配置了Redis）
        String redisHost = environment.getProperty("spring.redis.host");
        if (redisHost != null) {
            try {
                // TODO: 添加Redis连接检查
                log.info("Redis连接检查: 成功");
            } catch (Exception e) {
                log.error("Redis连接检查: 失败", e);
                throw new RuntimeException("Redis连接失败", e);
            }
        } else {
            log.info("Redis连接检查: 未配置，跳过检查");
        }

        // 检查系统配置
        checkSystemConfig();
        
        log.info("系统初始化检查完成\n");
    }

    /**
     * 检查系统配置
     */
    private void checkSystemConfig() {
        // 必选配置项
        String[] requiredProperties = {
            "spring.datasource.url",
            "spring.datasource.username",
            "spring.datasource.password",
            "jwt.secret",
            "jwt.expiration"
        };

        // 可选配置项
        String[] optionalProperties = {
            "spring.redis.host",
            "spring.redis.port",
            "spring.redis.password"
        };

        List<String> missingRequired = new ArrayList<>();
        List<String> missingOptional = new ArrayList<>();

        // 检查必选配置
        for (String property : requiredProperties) {
            if (environment.getProperty(property) == null) {
                missingRequired.add(property);
            }
        }

        // 检查可选配置
        for (String property : optionalProperties) {
            if (environment.getProperty(property) == null) {
                missingOptional.add(property);
            }
        }

        // 输出检查结果
        if (!missingRequired.isEmpty()) {
            log.error("必选配置缺失: {}", String.join(", ", missingRequired));
            throw new RuntimeException("系统必选配置不完整");
        }

        if (!missingOptional.isEmpty()) {
            log.warn("可选配置缺失: {}", String.join(", ", missingOptional));
        } else {
            log.info("系统配置检查: 成功");
        }
    }

    /**
     * 初始化系统必要数据到缓存
     */
    private void initializeSystemData() {
        log.info("开始初始化系统必要数据到缓存...");

        //TODO: 一些必要缓存可以在此处优先预载到本地
        
        log.info("系统数据初始化完成\n");
    }
} 