package com.ocbc.les.frame.handler.changetype;

import com.ocbc.les.common.exception.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.sql.*;

public class BigDecimalTypeHandler extends BaseTypeHandler<BigDecimal> {
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BigDecimal parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.stripTrailingZeros().toPlainString());
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public BigDecimal getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return toBigDecimal(value);
    }

    @Override
    public BigDecimal getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return toBigDecimal(value);
    }

    @Override
    public BigDecimal getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return toBigDecimal(value);
    }
    
    private BigDecimal toBigDecimal(String value) {
        try {
            return StringUtils.isBlank(value) ? null : new BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            throw new TypeConversionException("无法将字符串转换为BigDecimal: " + value, e);
        }
    }
}
