package com.ocbc.les.frame.handler.changetype;

import com.ocbc.les.common.exception.TypeConversionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {
    
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat dateFormat;
    
    public DateTypeHandler() {
        this(DEFAULT_FORMAT);
    }
    
    public DateTypeHandler(String format) {
        this.dateFormat = new SimpleDateFormat(format);
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) 
            throws SQLException {
        if (parameter != null) {
            ps.setString(i, dateFormat.format(parameter));
        } else {
            ps.setNull(i, Types.VARCHAR);
        }
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return toDate(value);
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return toDate(value);
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return toDate(value);
    }
    
    private Date toDate(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            synchronized (dateFormat) {
                return dateFormat.parse(value.trim());
            }
        } catch (ParseException e) {
            throw new TypeConversionException("无法将字符串转换为Date: " + value, e);
        }
    }
}