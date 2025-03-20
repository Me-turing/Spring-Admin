package com.ocbc.les.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求上下文工具类
 */
public class RequestContextUtils {
    private static final String UNKNOWN = "unknown";
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
            "X-Real-IP"
    };

    /**
     * 获取当前请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    /**
     * 获取客户端IP地址
     */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return UNKNOWN;
        }

        // 从请求头中获取IP
        for (String header : IP_HEADERS) {
            String ip = request.getHeader(header);
            if (isValidIp(ip)) {
                return ip;
            }
        }

        // 从代理服务器获取IP
        String ip = request.getHeader("X-Real-IP");
        if (isValidIp(ip)) {
            return ip;
        }

        // 从X-Forwarded-For获取第一个IP
        ip = request.getHeader("X-Forwarded-For");
        if (isValidIp(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            }
            return ip;
        }

        // 从请求中获取IP
        ip = request.getRemoteAddr();
        return isValidIp(ip) ? ip : UNKNOWN;
    }

    /**
     * 获取请求头信息
     */
    public static Map<String, String> getHeaders() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return new HashMap<>();
        }

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }
        return headers;
    }

    /**
     * 获取当前请求URL
     */
    public static String getRequestUrl() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return request.getRequestURL().toString();
    }

    /**
     * 获取当前请求方法
     */
    public static String getRequestMethod() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return request.getMethod();
    }

    /**
     * 获取当前请求参数
     */
    public static String getRequestParams() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return "";
        }
        return request.getQueryString();
    }

    /**
     * 验证IP地址是否有效
     */
    private static boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !UNKNOWN.equalsIgnoreCase(ip);
    }
} 