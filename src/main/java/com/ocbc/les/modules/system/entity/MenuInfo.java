package com.ocbc.les.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocbc.les.common.annotation.dbkey.DistributedId;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单表
 *
 * @TableName menu_info
 */
@TableName(value = "menu_info")
@Data
public class MenuInfo implements Serializable {
    /**
     * 菜单ID
     */
    @TableField(fill = FieldFill.INSERT)
    @DistributedId
    private String menuId;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 菜单中文名称
     */
    private String menuNameZh;

    /**
     * 菜单英文名称
     */
    private String menuNameEn;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 显示顺序
     */
    private String menuSort;

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
    @AutoFill(FillType.TIME)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @AutoFill(FillType.USER_ID)
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 更新者机构ID
     */
    @AutoFill(FillType.ORG_ID)
    @TableField(fill = FieldFill.UPDATE)
    private String updateOrgId;

    /**
     * 更新时间
     */
    @AutoFill(FillType.TIME)
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}