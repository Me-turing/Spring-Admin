package com.ocbc.les.modules.system.controller;

import com.ocbc.les.common.response.Result;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.security.utils.JwtUtils;
import com.ocbc.les.frame.security.config.Sm4PasswordEncoder;
import com.ocbc.les.modules.system.dto.request.LoginRequest;
import com.ocbc.les.modules.system.entity.UserInfo;
import com.ocbc.les.modules.system.service.UserInfoService;
import com.ocbc.les.modules.system.service.impl.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 认证控制器
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "认证管理", description = "用户认证相关接口")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private Sm4PasswordEncoder sm4PasswordEncoder;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录接口,返回JWT token")
    @Parameter(name = "loginRequest", description = "登录参数", required = true)
    public Result<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        // 加载用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if (userDetails == null) {
            return Result.fail("用户名或密码错误");
        }

        // 验证密码
        if (!sm4PasswordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            return Result.fail("用户名或密码错误");
        }

        // 更新用户最后登录信息
        try {
            UserInfo userInfo = userInfoService.getUserByLoginId(loginRequest.getUsername());
            if (userInfo != null) {
                userInfo.setLastLoginTime(LocalDateTime.now());
                userInfo.setLastLoginIp(RequestContextUtils.getIpAddress());
                userInfoService.updateUser(userInfo);
            }
        } catch (Exception e) {
            log.error("更新用户登录信息失败", e);
            // 不影响登录流程,继续执行
        }

        // 生成JWT token
        String token = jwtUtils.generateToken(userDetails);

        return Result.success(token);
    }
} 