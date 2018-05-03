package com.example.ddd.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DataTimeTypeHandler
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:15 2018/5/2
 */
public class DataTimeTypeHandler  extends BaseTypeHandler<Date> {
    /**
     *
     * 这里应该是在保存数据的时候，把前台的日期类型转换成数据的varchar 我们使用了SimpleDateFormat来转换data类型
     * */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    Date parameter, JdbcType jdbcType) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ps.setString(i, sdf.format(parameter));
    }

    /**
     * 下面三个函数应该都是返回到java层时调用，我们把varchar转换成Date类型
     */
    @Override
    public Date getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String varchartime = rs.getString(columnName);
        if (varchartime!=null)
            return new Date(Long.parseLong(varchartime));
        else return null;
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String varchartime = rs.getString(columnIndex);
        if (varchartime!=null)
            return new Date(Long.parseLong(varchartime));
        else return null;
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String varchartime = cs.getString(columnIndex);
        if (varchartime!=null)
            return new Date(Long.parseLong(varchartime));
        else return null;
    }
}
