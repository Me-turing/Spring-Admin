package com.ocbc.les.frame.security.service.impl;

import com.ocbc.les.common.exception.BusinessException;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.security.config.Sm4PasswordEncoder;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.dto.RefreshTokenRequest;
import com.ocbc.les.frame.security.dto.TokenDTO;
import com.ocbc.les.frame.security.service.AuthService;
import com.ocbc.les.frame.security.utils.JwtUtils;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserInfoService userInfoService;
    private final JwtUtils jwtUtils;
    private final Sm4PasswordEncoder sm4PasswordEncoder;

    @Override
    public TokenDTO login(LoginRequestDTO loginRequest) {
        // 加载用户信息
        UserInfo userInfo = userInfoService.getUserByLoginId(loginRequest.getUserId());
        if (userInfo == null) {
            throw new BusinessException("用户名或密码错误");
        }

        // 判断用户状态
        if (!"0".equals(userInfo.getStatus())) {
            throw new BusinessException("用户已被禁用");
        }

        // 返回UserDetails对象
        UserDetails userDetails = new User(
                userInfo.getLoginId(),
                userInfo.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        // 验证密码
        if (!sm4PasswordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        // 更新用户最后登录信息
        try {
            userInfo.setLastLoginTime(LocalDateTime.now());
            userInfo.setLastLoginIp(RequestContextUtils.getIpAddress());
            userInfoService.updateUser(userInfo);
        } catch (Exception e) {
            log.error("更新用户登录信息失败", e);
            // 不影响登录流程,继续执行
        }

        // 生成访问令牌和刷新令牌
        String accessToken = jwtUtils.generateAccessToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);

        // 构建并返回TokenDTO
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireIn(jwtUtils.getTokenRemainingTime(accessToken))
                .refreshTokenExpireIn(jwtUtils.getTokenRemainingTime(refreshToken))
                .build();
    }

    @Override
    public TokenDTO refreshToken(RefreshTokenRequest request) {
        try {
            // 验证刷新令牌
            String username = jwtUtils.getUsernameFromToken(request.getRefreshToken());
            if (username == null) {
                throw new BusinessException("无效的刷新令牌");
            }

            // 检查刷新令牌是否过期
            if (jwtUtils.isTokenExpired(request.getRefreshToken())) {
                throw new BusinessException("刷新令牌已过期");
            }

            // 加载用户信息
            UserInfo userInfo = userInfoService.getUserByLoginId(username);
            if (userInfo == null) {
                throw new BusinessException("用户不存在");
            }

            // 判断用户状态
            if (!"0".equals(userInfo.getStatus())) {
                throw new BusinessException("用户已被禁用");
            }

            // 创建UserDetails对象
            UserDetails userDetails = new User(
                    userInfo.getLoginId(),
                    userInfo.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

            // 生成新的访问令牌
            String newAccessToken = jwtUtils.generateAccessToken(userDetails);
            
            // 如果刷新令牌即将过期，也生成新的刷新令牌
            String newRefreshToken = request.getRefreshToken();
            if (jwtUtils.shouldTokenBeRefreshed(request.getRefreshToken())) {
                newRefreshToken = jwtUtils.generateRefreshToken(userDetails);
            }

            // 构建并返回TokenDTO
            return TokenDTO.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .accessTokenExpireIn(jwtUtils.getTokenRemainingTime(newAccessToken))
                    .refreshTokenExpireIn(jwtUtils.getTokenRemainingTime(newRefreshToken))
                    .build();

        } catch (Exception e) {
            log.error("刷新令牌失败", e);
            throw new BusinessException("刷新令牌失败");
        }
    }
}
