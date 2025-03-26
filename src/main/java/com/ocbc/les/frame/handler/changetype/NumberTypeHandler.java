package com.ocbc.les.frame.handler.changetype;

import com.ocbc.les.common.exception.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class NumberTypeHandler extends BaseTypeHandler<Number> {
    
    private final Class<? extends Number> targetType;
    
    public NumberTypeHandler(Class<? extends Number> targetType) {
        this.targetType = targetType;
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Number parameter, JdbcType jdbcType) 
            throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.toString());
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public Number getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return toNumber(value);
    }

    @Override
    public Number getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return toNumber(value);
    }

    @Override
    public Number getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return toNumber(value);
    }
    
    private Number toNumber(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            value = value.trim();
            if (targetType == Integer.class) {
                return Integer.valueOf(value);
            } else if (targetType == Long.class) {
                return Long.valueOf(value);
            } else if (targetType == Double.class) {
                return Double.valueOf(value);
            } else if (targetType == Float.class) {
                return Float.valueOf(value);
            } else {
                throw new TypeConversionException("不支持的数字类型: " + targetType);
            }
        } catch (NumberFormatException e) {
            throw new TypeConversionException("无法将字符串转换为" + targetType.getSimpleName() + ": " + value, e);
        }
    }
}