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
 * 机构表
 *
 * @TableName org_info
 */
@TableName(value = "org_info")
@Data
public class OrgInfo implements Serializable {
    /**
     * 机构ID
     */
    @TableField(fill = FieldFill.INSERT)
    @DistributedId
    private String orgId;

    /**
     * 机构关键字
     */
    private String orgKey;

    /**
     * 机构中文名称
     */
    private String orgNameZh;

    /**
     * 机构英文名称
     */
    private String orgNameEn;

    /**
     * 父机构ID
     */
    private String parentId;

    /**
     * 机构类型（1：总部，2：分部，3：支部，4：组）
     */
    private String orgType;

    /**
     * 机构排序
     */
    private String orgSort;

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