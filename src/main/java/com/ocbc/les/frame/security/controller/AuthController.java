package com.ocbc.les.frame.security.controller;

import com.ocbc.les.common.response.Result;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.dto.RefreshTokenRequest;
import com.ocbc.les.frame.security.dto.TokenDTO;
import com.ocbc.les.frame.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 */
@Tag(name = "认证管理", description = "认证相关接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "登录", description = "用户登录获取token")
    @PostMapping("/login")
    public Result<TokenDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return Result.success(authService.login(loginRequest));
    }

    @Operation(summary = "刷新Token", description = "使用刷新令牌获取新的访问令牌")
    @PostMapping("/refresh")
    public Result<TokenDTO> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return Result.success(authService.refreshToken(request));
    }
} 