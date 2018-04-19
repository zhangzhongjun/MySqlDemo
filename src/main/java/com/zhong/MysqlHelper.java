package com.zhong;

/**
 * @author 张中俊
 * @create 2018-04-03 8:38
 **/

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    /**
     * 向数据库中插入数据
     *
     * @param task
     *         要插入的数据
     */
    public static void saveTask(Task task) {
        Connection conn = MysqlConnPool.getConnection();
        PreparedStatement pres = null;
        String sql1 = "insert into test_obj(obj) values(?)";
        String sql2 = "insert into test(str,i,obj) values(?,?,?)";

        try {
            //这里为了演示批量插入
            pres = conn.prepareStatement(sql1);
            for (int i = 0; i < task.getObj().size(); i++) {
                pres.setBytes(1, MyUtils.msg2Byte(task.getObj().get(i)));
                pres.addBatch(); //实现批量插入
            }
            pres.executeBatch();//批量插入到数据库中

            pres = conn.prepareStatement(sql2);
            pres.setString(1, task.getStr());
            pres.setInt(2, task.getI());
            pres.setBytes(3, MyUtils.msg2Byte(task.getObj()));
            pres.execute();

            if (pres != null)
                pres.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接时候出错");
            }
        }
    }

    /**
     * 从数据库表中查询数据
     *
     * @param id
     *         要查询的id
     *
     * @return 查询出的结果
     */
    public static Task getTask(int id) {
        String sql_TSets = "select * from " + "test" + " where id=(?) limit 1";
        Connection conn = MysqlConnPool.getConnection();
        PreparedStatement pres = null;
        Task tSet = null;
        try {
            pres = conn.prepareStatement(sql_TSets);
            pres.setInt(1, id);
            ResultSet res = pres.executeQuery();
            while (res.next()) {
                int idd = res.getInt(1);
                String str = res.getString(2);
                int i = res.getInt(3);
                ArrayList<byte[]> obj = (ArrayList<byte[]>) MyUtils.byte2Msg(res.getBytes(4));
                tSet = new Task(obj, str, i);
            }
            if (pres != null)
                pres.close();
            return tSet;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接时候出错");
            }
        }
        return null;
    }


    /**
     * 是否在表中
     *
     * @param id
     *         要查询的id
     *
     * @return 是否在表中
     */
    public static boolean isInTables(int id) {
        Connection conn = MysqlConnPool.getConnection();
        PreparedStatement pres = null;
        Task task = null;
        String sql_XSets = "select count(*) from test WHERE id=(?)";
        try {
            pres = conn.prepareStatement(sql_XSets);
            pres.setInt(1, id);

            ResultSet res = pres.executeQuery();
            res.next();
            int count = res.getInt(1);

            if (pres != null)
                pres.close();

            if (count == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接时候出错");
            }
        }
        return false;
    }
}

