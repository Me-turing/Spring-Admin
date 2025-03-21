package com.ocbc.les.frame.security.controller;

import com.ocbc.les.common.response.Result;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "认证管理", description = "用户认证相关接口")
public class AuthController {


    @Autowired
    private AuthService authServiceImpl;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录接口,返回JWT token")
    public Result<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return authServiceImpl.getToken(loginRequest);
    }
} 