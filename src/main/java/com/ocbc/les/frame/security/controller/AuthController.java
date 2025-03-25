package com.ocbc.les.frame.security.controller;

import cn.hutool.core.util.StrUtil;
import com.ocbc.les.common.response.Result;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.common.util.MessageUtils;
import com.ocbc.les.frame.security.dto.LoginRequestDTO;
import com.ocbc.les.frame.security.service.AuthService;
import com.ocbc.les.frame.security.vo.TokenVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Result<TokenVO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return Result.success(MessageUtils.getMessage("auth.login.success"), authService.login(loginRequest));
    }


    @Operation(summary = "登出", description = "用户登录获取token")
    @GetMapping("/logout")
    public Result<?> logout() {
        String requestUserId = RequestContextUtils.getRequestUserId();
        if (StrUtil.isNotEmpty(requestUserId)){
            authService.logout(requestUserId);
        }
        return Result.success(MessageUtils.getMessage("auth.logout.success"));
    }
} 