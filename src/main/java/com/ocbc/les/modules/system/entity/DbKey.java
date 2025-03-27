package com.ocbc.les.modules.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @TableName db_key
 */
@TableName(value = "db_key")
@Data
public class DbKey implements Serializable {

    /**
     * 实体类名
     */
    private String entityName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * ID前缀
     */
    private String prefix;

    /**
     * 日期格式
     */
    private String dateFormat;

    /**
     * 补充位数
     */
    private Integer paddingLength;

    /**
     * 批量获取数量
     */
    private Integer batchSize;

    /**
     * 当前起始ID
     */
    private Long currentId;

    /**
     * 当前字母位置(用于扩展容量)
     */
    private Integer currentLetterPosition;

    /**
     * 当前日期
     */
    private Date recordDate;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @AutoFill(FillType.TIME)
    private LocalDateTime lastUpdateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}