package com.springcloud.generatecode.utils;

import com.springcloud.generatecode.generate.entity.FieldInfo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具
 */
public class DBUtils {

    private DataSource dataSource;

    public DBUtils(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据库中所有的表名称
     *
     * @return tables 该数据库中所有的表名称
     *
     */
    public List<String> getTables() throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            rs = metaData.getTables(conn.getCatalog(), "%", null, new String[]{"TABLE"});
            List<String> tables = new ArrayList<>();
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tables.add(tableName);
            }
            return tables;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            assert rs != null;
            rs.close();
            conn.close();
        }
        return null;
    }

    /**
     * 获取指定表的所有字段名称
     *
     * @param tableName 表名称
     * @return 该表所有的字段名称
     *
     */
    public List<FieldInfo> getDBFields(String tableName) throws SQLException {
        ResultSet rs = null;
        Connection conn = null;
        String SQL = "select * from ";
        try {
            conn = dataSource.getConnection();
            PreparedStatement cs = conn.prepareStatement(SQL + tableName);
            rs = cs.executeQuery(SQL + tableName);
            ResultSetMetaData data = rs.getMetaData();
            List<FieldInfo> fields = new ArrayList<>();
            for (int i = 1; i <= data.getColumnCount(); i++) {
                String columnName = data.getColumnName(i);
                //获得指定列的数据类型名
                String columnTypeName = data.getColumnTypeName(i);
                FieldInfo fieldInfo = new FieldInfo(columnName, columnTypeName);
                fields.add(fieldInfo);
            }
            return fields;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            assert rs != null;
            rs.close();
            conn.close();
        }
        return null;
    }

}
