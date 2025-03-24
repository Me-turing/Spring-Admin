package com.ocbc.les.frame.security.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 自定义权限判断服务
 * 权限配置在权限表,我们可以查询当前用户ID的权限判断当前接口是否在授权之中
 * 一级菜单:二级菜单:三级菜单:操作
 */
@Service
public class PermissionService {

    /**
     * 判断是否是指定用户
     * @param targetUserId 指定用户ID
     * @return 是否通过授权
     */
    public boolean hasPermission(String targetUserId) {
        // 获取当前认证信息
        CustomAuthentication authentication =
                (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();

        // 判断是否是管理员
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // 判断是否是自己的数据
        boolean isSelfData = authentication.getUserId().equals(targetUserId);

        return isAdmin || isSelfData;
    }
}