package com.ocbc.les.frame.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailCache {
    private String userId;           // 用户ID
    private String userNameZh;       // 用户中文名
    private String userNameEn;       // 用户英文名
    private String orgId;            // 组织ID
    private String orgNameZh;        // 组织中文名
    private String orgNameEn;        // 组织英文名
    private String roleId;           // 角色ID
    private String roleNameZh;       // 角色中文名
    private String roleNameEn;       // 角色英文名
    private LocalDateTime loginTime;       // 登陆时间
    private String loginIp;       // 登陆时间
    private List<String> permissionKeys; // 权限标识列表
    private List<MenuTree> menuTrees; // 菜单树

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MenuTree{
        private String menuId;           // 菜单ID
        private String menuNameZh;       // 菜单中文名
        private String menuNameEn;       // 菜单英文名
        private String menuPath;         // 菜单路径
        private String menuIcon;         // 菜单图标
        private String menuSort;         // 显示顺序
        private List<MenuTree> children;  // 子菜单列表
    }
}
