package com.ocbc.les.frame.security.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ocbc.les.common.config.Sm4PasswordEncoder;
import com.ocbc.les.common.exception.BusinessException;
import com.ocbc.les.common.response.Result;
import com.ocbc.les.common.util.MessageUtils;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.cache.entity.JwtCache;
import com.ocbc.les.frame.cache.util.JwtCacheUtils;
import com.ocbc.les.frame.security.config.CustomAuthentication;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.service.AuthService;
import com.ocbc.les.frame.security.utils.JwtUtils;
import com.ocbc.les.frame.security.vo.TokenVO;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserInfoService userInfoService;
    private final JwtUtils jwtUtils;
    private final Sm4PasswordEncoder sm4PasswordEncoder;
    private final JwtCacheUtils jwtCacheUtils;

    @Override
    public TokenVO login(LoginRequestDTO loginRequest) {
        // 加载用户信息
        UserInfo userInfo = userInfoService.getUserById(loginRequest.getUserId());
        if (ObjectUtil.isEmpty(userInfo)) {
            throw new BusinessException(MessageUtils.getMessage("auth.login.wrongcredentials"));
        }

        // 判断用户状态
        if (!"0".equals(userInfo.getStatus())) {
            throw new BusinessException(MessageUtils.getMessage("auth.login.disabled"));
        }

        // 返回UserDetails对象
        //TODO: 此处需要处理用户的权限
        List<String> roleList = Arrays.asList("USER", "ADMIN");
        CustomAuthentication customAuthentication = new CustomAuthentication(userInfo.getUserId(), userInfo.getUserNameZh(), userInfo.getPassword(), roleList);


        // 验证密码 TODO: 注意当前使用的是明文
        if (!sm4PasswordEncoder.matches(loginRequest.getPassword(), customAuthentication.getPassword())) {
            throw new BusinessException(MessageUtils.getMessage("auth.login.wrongcredentials"));
        }

        // 更新用户最后登录信息
        try {
            userInfo.setLastLoginTime(LocalDateTime.now());
            userInfo.setLastLoginIp(RequestContextUtils.getIpAddress());
            userInfoService.updateUser(userInfo);
        } catch (Exception e) {
            log.error(MessageUtils.getMessage("user.update.error"), e);
            // 不影响登录流程,继续执行
        }

        // 生成访问令牌
        String accessToken = jwtUtils.generateToken(customAuthentication);

        // 初始化存储Token信息
        JwtCache jwtCache = jwtCacheUtils.initJwt(accessToken, userInfo.getUserId(),roleList);
        jwtCacheUtils.putJwt(userInfo.getUserId(), jwtCache);

        // 构建并返回TokenDTO
        return TokenVO.builder()
                .accessToken(accessToken)
                .accessTokenExpireIn(jwtUtils.getTokenRemainingTime(accessToken))
                .build();
    }

    @Override
    public Result<?> logout(String requestUserId) {
        jwtCacheUtils.removeJwt(requestUserId);
        return Result.success(MessageUtils.getMessage("auth.logout.success"));
    }
}
