package com.ocbc.les.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocbc.les.common.annotation.dbkey.DistributedId;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 审计日志表
 *
 * @TableName audit_log
 */
@TableName(value = "audit_log")
@Data
public class AuditLog implements Serializable {
    /**
     * 审计ID
     */
    @TableField(fill = FieldFill.INSERT)
    @DistributedId
    private String auditId;

    /**
     * 操作时间
     */
    @AutoFill(FillType.TIME)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime operationTime;

    /**
     * 操作IP
     */
    @AutoFill(FillType.IP)
    @TableField(fill = FieldFill.INSERT)
    private String operationIp;

    /**
     * 操作人ID
     */
    @AutoFill(FillType.USER_ID)
    @TableField(fill = FieldFill.INSERT)
    private String operatorId;

    /**
     * 操作机构ID
     */
    @AutoFill(FillType.ORG_ID)
    @TableField(fill = FieldFill.INSERT)
    private String operatorOrgId;

    /**
     * 操作角色ID
     */
    @AutoFill(FillType.ROLE_ID)
    @TableField(fill = FieldFill.INSERT)
    private String roleId;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应结果
     */
    private String responseResult;

    /**
     * 操作URL
     */
    private String operationUrl;

    /**
     * 操作描述
     */
    private String operationDesc;

    /**
     * 操作结果(0:成功,1:失败)
     */
    private String operationResult;

    /**
     * 错误信息
     */
    private String errorMsg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}