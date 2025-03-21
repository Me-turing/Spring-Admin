package com.ocbc.les.frame.security.service;

import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.dto.RefreshTokenRequest;
import com.ocbc.les.frame.security.dto.TokenDTO;

/**
 * 认证服务接口
 */
public interface AuthService {

    /**
     * 用户登录
     */
    TokenDTO login(LoginRequestDTO loginRequest);

    /**
     * 刷新Token
     */
    TokenDTO refreshToken(RefreshTokenRequest request);
}
