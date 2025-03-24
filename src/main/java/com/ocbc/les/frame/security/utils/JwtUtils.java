package com.ocbc.les.frame.security.utils;

import com.ocbc.les.frame.security.config.CustomAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JWT工具类
 */
@Slf4j
@Component
public class JwtUtils {

    //JWT密钥
    @Value("${jwt.secret}")
    private String secret;

    //JWT过期时间
    @Value("${jwt.expiration}")
    private Long expiration;

    //JWt签发者
    @Value("${jwt.issuer}")
    private String issuer;

    /**
     * 生成Token
     */
    public String generateToken(CustomAuthentication customAuthentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", customAuthentication.getUserId());
        claims.put("userName", customAuthentication.getName());
        // 将用户权限添加到claims中
        List<String> authorities = customAuthentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        claims.put("authorities", authorities);
        
        return doGenerateToken(claims, customAuthentication.getUsername());
    }

    /**
     * 从Token中获取用户名
     */
    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return claims.get("userName", String.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("userName", String.class);
        } catch (Exception e) {
            log.error("无法从Token中获取用户名称", e);
            return null;
        }
    }

    /**
     * 从Token中获取用户ID
     */
    public String getUserIdFromToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return claims.get("userId", String.class);
        } catch (ExpiredJwtException e) {
            return e.getClaims().get("userId", String.class);
        } catch (Exception e) {
            log.error("无法从Token中获取用户ID", e);
            return null;
        }
    }

    /**
     * 从Token中获取权限列表
     */
    @SuppressWarnings("unchecked")
    public List<String> getAuthoritiesFromToken(String token) {
        try {
            Claims claims = getAllClaimsFromToken(token);
            return (List<String>) claims.get("authorities");
        } catch (ExpiredJwtException e) {
            return (List<String>) e.getClaims().get("authorities");
        } catch (Exception e) {
            log.error("无法从Token中获取权限信息", e);
            return List.of();
        }
    }

    /**
     * 从Token中获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 获取Token创建时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * 获取Token剩余有效期（秒）
     */
    public long getTokenRemainingTime(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return (expiration.getTime() - System.currentTimeMillis()) / 1000;
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        } catch (ExpiredJwtException e) {
            return claimsResolver.apply(e.getClaims());
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
} 