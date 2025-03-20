package com.ocbc.les.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

/**
 * IP工具类
 */
public class IpUtils {
    private static final String UNKNOWN = "unknown";
    private static final String[] IP_HEADERS = {
        "X-Forwarded-For",
        "Proxy-Client-IP",
        "WL-Proxy-Client-IP",
        "HTTP_CLIENT_IP",
        "HTTP_X_FORWARDED_FOR"
    };

    /**
     * 获取IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        
        // 从请求头中获取IP
        for (String header : IP_HEADERS) {
            ip = request.getHeader(header);
            if (isValidIp(ip)) {
                break;
            }
        }
        
        // 如果还是获取不到,则使用getRemoteAddr
        if (!isValidIp(ip)) {
            ip = request.getRemoteAddr();
        }
        
        // 多个代理的情况,第一个IP为客户端真实IP
        if (StringUtils.hasText(ip) && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        
        return ip;
    }

    /**
     * 判断IP是否有效
     */
    private static boolean isValidIp(String ip) {
        return StringUtils.hasText(ip) && !UNKNOWN.equalsIgnoreCase(ip);
    }
} 