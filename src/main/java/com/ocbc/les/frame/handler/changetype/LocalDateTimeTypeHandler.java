package com.ocbc.les.frame.handler.changetype;

import com.ocbc.les.common.exception.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeTypeHandler() {
        this(DEFAULT_FORMAT);
    }

    public LocalDateTimeTypeHandler(String format) {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter != null) {
            ps.setString(i, parameter.format(dateTimeFormatter));
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return toLocalDateTime(value);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return toLocalDateTime(value);
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return toLocalDateTime(value);
    }

    private LocalDateTime toLocalDateTime(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return LocalDateTime.parse(value.trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new TypeConversionException("无法将字符串转换为LocalDateTime: " + value, e);
        }
    }
}