package com.ocbc.les.frame.handler;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ocbc.les.common.annotation.dbkey.DistributedId;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import com.ocbc.les.frame.cache.util.FieldCacheUtils;
import com.ocbc.les.frame.dbkey.DistributedIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * 自动填充处理器
 */
@Slf4j
@Component
public class AutoFillHandler implements MetaObjectHandler {

    private final FieldCacheUtils fieldCacheUtils;
    private final DistributedIdGenerator idGenerator;

    @Autowired
    public AutoFillHandler(FieldCacheUtils fieldCacheUtils,
                           @Lazy DistributedIdGenerator idGenerator) {
        this.fieldCacheUtils = fieldCacheUtils;
        this.idGenerator = idGenerator;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("开始进行插入操作自动填充");
        fillFields(metaObject, OperateType.INSERT);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("开始进行更新操作自动填充");
        fillFields(metaObject, OperateType.UPDATE);
    }

    /**
     * 填充字段
     */
    private void fillFields(MetaObject metaObject, OperateType operateType) {
        try {
            Object originalObject = metaObject.getOriginalObject();
            if (originalObject == null) {
                return;
            }

            Class<?> clazz = originalObject.getClass();
            Field[] fields = fieldCacheUtils.getFields(clazz);

            for (Field field : fields) {
                processField(field, originalObject, operateType);
            }
        } catch (Exception e) {
            log.error("自动填充字段失败", e);
        }
    }

    /**
     * 处理字段
     */
    private void processField(Field field, Object obj, OperateType operateType) {
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField == null || !shouldFill(tableField.fill(), operateType)) {
            return;
        }

        try {
            field.setAccessible(true);

            // 处理自动填充
            AutoFill autoFill = field.getAnnotation(AutoFill.class);
            if (autoFill != null) {
                Object value = getFieldValue(autoFill.value());
                field.set(obj, value);
                log.debug("字段 {} 自动填充完成, 填充值: {}", field.getName(), value);
                return;
            }

            // 处理分布式ID
            DistributedId distributedId = field.getAnnotation(DistributedId.class);
            if (distributedId != null) {
                // 获取类上的@TableName注解的值
                String tableName = getTableName(obj.getClass());
                String entityName = obj.getClass().getSimpleName();

                String id = idGenerator.generateId(tableName, entityName, distributedId);
                field.set(obj, id);
                log.debug("字段 {} 分布式ID填充完成, 填充值: {}", field.getName(), id);
            }
        } catch (Exception e) {
            log.error("字段 {} 填充失败", field.getName(), e);
        }
    }

    /**
     * 获取表名
     */
    private String getTableName(Class<?> clazz) {
        TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);
        if (tableNameAnnotation != null && StringUtils.hasText(tableNameAnnotation.value())) {
            return tableNameAnnotation.value();
        }
        // 如果没有@TableName注解或值为空,使用类名(首字母小写)作为表名
        String className = clazz.getSimpleName();
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }

    /**
     * 判断是否需要填充
     */
    private boolean shouldFill(FieldFill fieldFill, OperateType operateType) {
        return switch (operateType) {
            case INSERT -> fieldFill.equals(FieldFill.INSERT) || fieldFill.equals(FieldFill.INSERT_UPDATE);
            case UPDATE -> fieldFill.equals(FieldFill.UPDATE) || fieldFill.equals(FieldFill.INSERT_UPDATE);
        };
    }

    /**
     * 获取字段值 TODO: 此处应该从缓存中获取
     */
    private Object getFieldValue(FillType fillType) {
        return switch (fillType) {
            case USER_ID -> "System";
            case ORG_ID -> "000000";
            case ROLE_ID -> "999999";
            case IP -> "127.0.0.1";
            case TIME -> LocalDateTime.now();
        };
    }

    private enum OperateType {
        INSERT, UPDATE
    }
}