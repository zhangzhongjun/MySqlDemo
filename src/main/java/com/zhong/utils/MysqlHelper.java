package com.zhong.utils;

/**
 * @author 张中俊
 * @create 2018-04-03 8:38
 **/

import java.sql.*;

public class MysqlHelper {
    /**
     * 执行update、insert、delete等语句，返回值为受影响的行数
     *
     * @param sql
     *         sql语句
     *
     * @return 受到影响的行数
     */
    public static int executeUpdate(String sql) {
        Connection conn = MysqlConnPool.getConnection();
        int resCount = 0;
        if (sql == null || sql.isEmpty()) {
            System.out.println("sql语句不能为空");
            return resCount;
        }
        PreparedStatement ps = null;
        System.out.println("sql--> " + sql);
        try {
            ps = conn.prepareStatement(sql);
            resCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接时候出错");
            }
        }
        return resCount;
    }

    /**
     * 执行能够返回结果集的查询语句
     *
     * @param sql
     *         要执行的sql语句
     *
     * @return 查询结果集
     */
    public static ResultSet executeQuery(String sql) {
        Connection conn = MysqlConnPool.getConnection();
        if (sql.isEmpty()) {
            System.out.println("sql语句不为空");
            return null;
        }
        ResultSet rs = null;
        PreparedStatement ps = null;
        System.out.println("sql--> " + sql);
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接时候出错");
            }
        }
        return rs;
    }


}

