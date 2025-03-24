package com.ocbc.les.frame.cache.util;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ocbc.les.common.util.RedisUtils;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.cache.entity.JwtCache;
import com.ocbc.les.frame.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Jwt的缓存工具
 * 此处采用二级缓存: Caffeine + Redis
 * 注意: Caffeine的初始化有固定大小本地没有会尝试从云端同步
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtCacheUtils {

    // region Caffeine缓存配置
    private static final Cache<String, JwtCache> userLocalCache;

    static {
        userLocalCache = Caffeine.newBuilder()
                .maximumSize(80)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .removalListener((key, value, cause) ->
                        log.info("Key {} was removed from Caffeine cache, cause: {}", key, cause))
                .recordStats()
                .build();
    }
    // endregion

    // region Redis缓存配置
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.refresh}")
    private Long refreshTime;

    //JWT前缀
    private static final String JWT_KEY_PREFIX = "les:jwt:";
    //黑名单
    private static final String JWT_BLACKLIST_KEY = JWT_KEY_PREFIX + "blacklist:";

    // endregion


    /**
     * 初始化initJwt
     * @param token 用户Token
     * @param userId 用户ID
     * @return JwtCache
     */
    public JwtCache initJwt(String token , String userId, List<String> authorities) {
         return JwtCache.builder()
                 .token(token)
                 .userId(userId)
                 .loginIp(RequestContextUtils.getIpAddress())
                 .createdTime(jwtUtils.getIssuedAtDateFromToken(token))
                 .expirationTime(jwtUtils.getExpirationDateFromToken(token))
                 .authorities(authorities)
                 .refreshTime(System.currentTimeMillis() + refreshTime * 1000)
                 .build();
    }


    /**
     * 获取Jwt缓存信息(获取缓存)
     */
    public JwtCache getJwt(String userId) {
        //尝试从本地获取
        JwtCache jwtCache = userLocalCache.getIfPresent(userId);
        if (ObjectUtil.isEmpty(jwtCache)){
            //尝试从云端获取
            jwtCache = (JwtCache) redisUtils.get(JWT_KEY_PREFIX+userId);
        }
        return jwtCache ;
    }

    /**
     * 添加或更新用户缓存(登录)
     */
    public void putJwt(String userId, JwtCache jwtCache) {
        //在本地存放
        userLocalCache.put(userId, jwtCache);
        //云端同步
        redisUtils.set(JWT_KEY_PREFIX+userId,jwtCache,expiration);
    }

    /**
     * 删除用户缓存(登出)
     */
    public void removeJwt(String userId) {
        //Token添加到黑名单
        JwtCache jwt = this.getJwt(userId);
        if (ObjectUtil.isNotEmpty(jwt)){
            this.invalidJwt(jwt.getToken());
        }
        //本地删除
        userLocalCache.invalidate(userId);
        //云端删除
        redisUtils.del(JWT_KEY_PREFIX+userId);
    }

    /**
     * 将指定用户的Token失效(登出后操作)
     * @param token 加入黑名单的Token
     */
    public void invalidJwt(String token){
        //添加到黑名单
        redisUtils.set(JWT_BLACKLIST_KEY+token,"1",expiration);
    }

    /**
     * 判断是否在黑名单
     * @param token 用户Token
     * @return 是否黑名单
     */
    public boolean isBlackToken(String token){
        return redisUtils.hasKey(JWT_BLACKLIST_KEY+token);
    }

    /**
     * 判断是否IP一致
     * @param userId 用户ID
     * @return 是否一致
     */
    public boolean checkIpChange(String userId){
        //获取当前的IP
        String ipAddress = RequestContextUtils.getIpAddress();
        JwtCache jwt = getJwt(userId);
        if (ObjectUtil.isNotEmpty(jwt)){
            return !ipAddress.equals(jwt.getLoginIp());
        }
        return true;
    }

    /**
     * 判断Token是否一致
     * @param userId 用户ID
     * @return 是否一致
     */
    public boolean checkTokenChange(String userId,String token){
        //获取当前的IP
        JwtCache jwt = getJwt(userId);
        if (ObjectUtil.isNotEmpty(jwt)&& StrUtil.isNotEmpty(token)){
            return !token.equals(jwt.getToken());
        }
        return true;
    }

    /**
     * 判断是否超时
     * @param userId 用户ID
     * @return 是否活跃
     */
    public boolean checkActive(String userId){
        JwtCache jwt = getJwt(userId);
        if (ObjectUtil.isNotEmpty(jwt)){
            Long refreshTime = jwt.getRefreshTime();
            return System.currentTimeMillis() > refreshTime;
        }
        return true;
    }

    /**
     * 给Token续期更新
     * @param userId 用户ID
     */
    public void renewJwt(String userId){
        JwtCache jwt = getJwt(userId);
        if (ObjectUtil.isNotEmpty(jwt)){
            jwt.setRefreshTime(System.currentTimeMillis() + refreshTime*1000);
        }
        putJwt(userId,jwt);
    }

} 