package com.zhong.dao;

import com.zhong.utils.MyUtils;
import com.zhong.utils.MysqlConnPool;
import com.zhong.bean.Student;
import com.zhong.bean.TaskOBJ;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 张中俊
 **/
public class TaskOBJDao {

    /**
     * 从数据库表中查询数据
     *
     * @param id
     *         要查询的id
     *
     * @return 查询出的结果
     */
    public static TaskOBJ getTaskObj(String id) {
        String sql = "select * from " + "test_obj" + " where id=(?) limit 1";
        Connection conn = MysqlConnPool.getConnection();
        PreparedStatement pres = null;
        TaskOBJ tSet = null;
        try {
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            ResultSet res = pres.executeQuery();
            while (res.next()) {
                String idd = res.getString(1);
                Student s = (Student) MyUtils.byte2Msg(res.getBytes(2));
                Timestamp timestamp = res.getTimestamp(3);
                Date date = new Date(timestamp.getTime());
                tSet = new TaskOBJ(idd,s,date);
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
     * 批处理，插入
     *
     * @param taskOBJS 要插入的类
     *
     */
    public static void batchSaveTaskOBJ(ArrayList<TaskOBJ> taskOBJS) {
        Connection conn = MysqlConnPool.getConnection();
        PreparedStatement pres = null;
        String sql1 = "insert into test_obj(id,obj,uploadtime) values(?,?,?)";

        try {
            //这里为了演示批量插入
            pres = conn.prepareStatement(sql1);
            for(TaskOBJ taskOBJ : taskOBJS){
                pres.setString(1, taskOBJ.getId());
                pres.setObject(2,MyUtils.msg2Byte(taskOBJ.getStudent()));
                pres.setTimestamp(3,new Timestamp(taskOBJ.getUploadtime().getTime()));
                pres.addBatch(); //实现批量插入
            }
            pres.executeBatch();//批量插入到数据库中

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
