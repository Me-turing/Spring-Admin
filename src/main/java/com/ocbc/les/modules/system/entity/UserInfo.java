package com.ocbc.les.modules.system.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息实体类
 */
@Data
public class UserInfo {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 中文名
     */
    private String userNameZh;

    /**
     * 英文名
     */
    private String userNameEn;

    /**
     * 所属机构ID
     */
    private Long orgId;

    /**
     * 状态（0：正常，1：停用）
     */
    private String status;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 删除标志（0：存在，1：删除）
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建者机构ID
     */
    private Long createOrgId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新者机构ID
     */
    private Long updateOrgId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;
} 