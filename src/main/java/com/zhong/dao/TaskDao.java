package com.zhong.dao;

import com.zhong.utils.MyUtils;
import com.zhong.utils.MysqlConnPool;
import com.zhong.bean.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 张中俊
 **/
public class TaskDao {

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


    /**
     * 向数据库中插入数据
     *
     * @param task
     *         要插入的数据
     */
    public static void saveTask(Task task) {
        Connection conn = MysqlConnPool.getConnection();
        PreparedStatement pres = null;

        String sql2 = "insert into test(str,i,obj) values(?,?,?)";

        try {
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

}
