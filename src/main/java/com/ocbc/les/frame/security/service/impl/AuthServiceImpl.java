package com.ocbc.les.frame.security.service.impl;

import com.ocbc.les.common.response.Result;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.security.config.Sm4PasswordEncoder;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.service.AuthService;
import com.ocbc.les.frame.security.utils.JwtUtils;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {


//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private Sm4PasswordEncoder sm4PasswordEncoder;

    @Override
    public Result<?> getToken(LoginRequestDTO loginRequest) {
        // 加载用户信息
        UserInfo userInfo = userInfoService.getUserByLoginId(loginRequest.getUsername());
        if (userInfo == null) {
            return Result.fail("用户名或密码错误");
        }

        // 判断用户状态
        if (!"0".equals(userInfo.getStatus())) {
            return Result.fail("用户已被禁用");
        }

        // 返回UserDetails对象,使用SM4加密的密码
        UserDetails userDetails =  new User(
                userInfo.getLoginId(),
                userInfo.getPassword(), // 数据库中存储的是SM4加密后的密码
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );

        // 验证密码
        if (!sm4PasswordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            return Result.fail("用户名或密码错误");
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

        // 生成JWT token
       return Result.success(jwtUtils.generateToken(userDetails));
    }
}
