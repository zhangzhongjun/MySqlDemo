package com.zhong;

/**
 * @author 张中俊
 * @create 2018-04-03 8:30
 **/
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * MySql连接池
 */
public class MysqlConnPool {
    //public static final String url = "jdbc:mysql://10.170.32.244:3306/QWSS";
    public static final String url = "jdbc:mysql://localhost:3306/QWSS?serverTimezone=UTC";
    public static final String username = "root";
    public static final String password = "root";

    private static final MysqlConnPool instance = new MysqlConnPool();
    private static ComboPooledDataSource comboPooledDataSource;

    static {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl(url);
            comboPooledDataSource.setUser(username);
            comboPooledDataSource.setPassword(password);
            //下面是设置连接池的一配置
            comboPooledDataSource.setMaxPoolSize(20);
            comboPooledDataSource.setMinPoolSize(3);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Connection getConnection() {
        Connection connection = null;
        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

}

