package com.ocbc.les.common.util;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * SM4加密工具类
 */
@Component
public class Sm4Utils {

    @Value("${sm4.key:1234567890123456}")
    private String sm4Key;

    private SM4 sm4;

    @PostConstruct
    public void init() {
        // 初始化SM4加密器
        sm4 = SmUtil.sm4(sm4Key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加密
     *
     * @param content 待加密内容
     * @return 加密后的内容
     */
    public String encrypt(String content) {
        return sm4.encryptHex(content);
    }

    /**
     * 解密
     *
     * @param encryptContent 加密内容
     * @return 解密后的内容
     */
    public String decrypt(String encryptContent) {
        return sm4.decryptStr(encryptContent, StandardCharsets.UTF_8);
    }

    /**
     * 验证密码
     *
     * @param password 明文密码
     * @param encryptPassword 加密密码
     * @return 是否匹配
     */
    public boolean verifyPassword(String password, String encryptPassword) {
        return password.equals(decrypt(encryptPassword));
    }
} 