package com.wismap.springsecuritydemo.utils;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShortToBooleanTypeHandler implements TypeHandler<Boolean> {

    @Override
    public Boolean getResult(ResultSet rs, String columnName)
            throws SQLException {
        Short result=rs.getShort(columnName);
        if (result>0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Short result=rs.getShort(columnIndex);
        if (result>0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Short result=cs.getShort(columnIndex);
        if (result>0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i,
                            Boolean parameter, JdbcType jdbcType) throws SQLException {
        StringBuffer result = new StringBuffer();
        if (parameter)
            ps.setInt(i, 1);
        else
            ps.setInt(i,0);
    }

}
