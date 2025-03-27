package com.ocbc.les.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocbc.les.common.annotation.dbkey.DistributedId;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {
    /**
     * 用户ID
     */
    @TableField(fill = FieldFill.INSERT)
    @DistributedId()
    private String userId;

    /**
     * 登录ID
     */
    private String loginId;

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
     * 状态（0：正常，1：停用）
     */
    private String status;

    /**
     * 删除标志（0：存在，1：删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 创建者
     */
    @AutoFill(FillType.USER_ID)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建者机构ID
     */
    @AutoFill(FillType.ORG_ID)
    @TableField(fill = FieldFill.INSERT)
    private String createOrgId;

    /**
     * 创建时间
     */
    @AutoFill(FillType.Date)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @AutoFill(value = FillType.USER_ID)
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 更新者机构ID
     */
    @AutoFill(value = FillType.ORG_ID)
    @TableField(fill = FieldFill.UPDATE)
    private String updateOrgId;

    /**
     * 更新时间
     */
    @AutoFill(value = FillType.Date)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}