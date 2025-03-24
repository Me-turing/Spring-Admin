package com.ocbc.les.frame.security.service;

import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.vo.TokenVO;

/**
 * 认证服务接口
 */
public interface AuthService {

    /**
     * 用户登录
     */
    TokenVO login(LoginRequestDTO loginRequest);

}
