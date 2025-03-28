package com.ocbc.les.modules.system.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ocbc.les.common.response.Result;
import com.ocbc.les.common.util.RequestContextUtils;
import com.ocbc.les.frame.cache.entity.UserDetailCache;
import com.ocbc.les.modules.system.dto.GetUserDetailsDTO;
import com.ocbc.les.modules.system.service.UserInfoService;
import com.ocbc.les.modules.system.service.UserOrgService;
import com.ocbc.les.modules.system.service.UserRoleService;
import com.ocbc.les.modules.system.vo.GetUserOrgVO;
import com.ocbc.les.modules.system.vo.GetUserRoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色控制
 */
@Tag(name = "角色管理", description = "只要用于系统角色的管理")
@RestController
@Slf4j
@RequestMapping("/login")
public class SystemLoginController {
    @Autowired
    private UserOrgService userOrgServiceImpl;

    @Autowired
    private UserRoleService userRoleServiceImpl;

    @Autowired
    private UserInfoService userInfoServiceImpl;
    @Operation(summary = "获取当前用户的机构列表", description = "根据当前登录的用户(token)获取当前用户的机构列表")
    @GetMapping("/get-user-org-list")
    @ApiOperationSupport(order = 1)
    public Result<GetUserOrgVO> getUserOrgList() {
        String requestUserId = RequestContextUtils.getRequestUserId();
        GetUserOrgVO  getUserOrgVO = userOrgServiceImpl.getUserOrgListByUserId(requestUserId);
        return Result.success(getUserOrgVO);
    }

    @Operation(summary = "获取当前用户的角色列表", description = "根据当前登录的用户(token)获取当前用户的角色列表")
    @GetMapping("/get-user-role")
    @ApiOperationSupport(order = 2)
    public Result<GetUserRoleVO> getUserRole() {
        String requestUserId = RequestContextUtils.getRequestUserId();
        GetUserRoleVO  getUserRoleVO = userRoleServiceImpl.getUserRoleListByUserId(requestUserId);
        return Result.success(getUserRoleVO);
    }

    @Operation(summary = "获取当前用户详情", description = "根据当前登录的用户(token+org+role)获取当前用户的详情")
    @GetMapping("/get-user-details")
    @ApiOperationSupport(order = 3)
    public Result<UserDetailCache> getUserDetails(GetUserDetailsDTO userDetailsDTO) {
        UserDetailCache userDetailCache = userInfoServiceImpl.getUserDetails(userDetailsDTO);
        return Result.success(userDetailCache);
    }
}
