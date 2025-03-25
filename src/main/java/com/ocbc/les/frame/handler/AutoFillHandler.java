package com.ocbc.les.frame.handler;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ocbc.les.common.annotation.field.AutoFill;
import com.ocbc.les.common.annotation.field.FillType;
import com.ocbc.les.frame.cache.util.FieldCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自动填充处理器
 */
@Slf4j
@Component
public class AutoFillHandler implements MetaObjectHandler {

    @Autowired
    private FieldCacheUtils fieldCacheUtils;

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
     * 处理单个字段
     */
    private void processField(Field field, Object obj, OperateType operateType) {
        AutoFill autoFill = field.getAnnotation(AutoFill.class);
        TableField tableField = field.getAnnotation(TableField.class);

        if (autoFill == null || tableField == null || !shouldFill(tableField.fill(), operateType) ){
            return;
        }

        try {
            field.setAccessible(true);
            Object value = getFieldValue(autoFill.value());
            field.set(obj, value);
            log.debug("字段 {} 自动填充完成, 填充值: {}", field.getName(), value);
        } catch (Exception e) {
            log.error("字段 {} 自动填充失败", field.getName(), e);
        }
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
     * 获取字段值
     */
    private Object getFieldValue(FillType fillType) {
        return switch (fillType) {
            //TODO : 此处需要从缓存中获取当前操作人的信息
            case USER_ID -> "userId";
            case ORG_ID -> 100L;
            case TIME -> LocalDateTime.now();
            case Date -> new Date();
        };
    }

    private enum OperateType {
        INSERT, UPDATE
    }
} 