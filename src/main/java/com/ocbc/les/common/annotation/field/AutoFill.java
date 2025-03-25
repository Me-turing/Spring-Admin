package com.ocbc.les.common.annotation.field;

import java.lang.annotation.*;

/**
 * 自动填充注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoFill {
    /**
     * 填充类型
     */
    FillType value();

} 