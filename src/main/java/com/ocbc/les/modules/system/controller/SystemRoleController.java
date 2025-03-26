package com.ocbc.les.modules.system.controller;

import com.ocbc.les.common.response.Result;
import com.ocbc.les.common.util.RequestContextUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色控制
 */
@Tag(name = "角色管理", description = "只要用于系统角色的管理")
@RestController
@Slf4j
@RequestMapping("/role")
public class SystemRoleController {

    @Operation(summary = "获取当前用户的角色列表", description = "根据当前登录的用户(token)获取当前用户的角色列表")
    @GetMapping("/get-user-role")
    public Result<?> getUserRole() {
        String requestUserId = RequestContextUtils.getRequestUserId();
        return Result.success("Hello, OCBC Risk Management System!");
    }
}
