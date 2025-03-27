package com.ocbc.les.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 码值库表
 *
 * @TableName code_library
 */
@TableName(value = "code_library")
@Data
public class CodeLibrary implements Serializable {

    /**
     * 码值类型
     */
    private String codeType;

    /**
     * 码值
     */
    private String codeValue;

    /**
     * 码值名称
     */
    private String codeName;

    /**
     * 显示顺序
     */
    private String codeSort;

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
     * 码值描述
     */
    private String codeDesc;

    /**
     * 拓展字段1
     */
    private String attribute1;

    /**
     * 拓展字段2
     */
    private String attribute2;

    /**
     * 拓展字段3
     */
    private String attribute3;

    /**
     * 拓展字段4
     */
    private String attribute4;

    /**
     * 拓展字段5
     */
    private String attribute5;

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