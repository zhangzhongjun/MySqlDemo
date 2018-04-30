package com.zhong.dao;

import com.zhong.bean.Student;
import com.zhong.bean.TaskOBJ;
import com.zhong.utils.MysqlHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @author 张中俊
 **/
public class TaskOBJDaoTest {

    @Test
    public void batchSaveTaskOBJTest(){
        Student s = new Student("zhangsang",1);
        TaskOBJ taskOBJ1 = new TaskOBJ(UUID.randomUUID().toString(),s,new Date());
        TaskOBJ taskOBJ2 = new TaskOBJ(UUID.randomUUID().toString(),s,new Date());
        TaskOBJ taskOBJ3 = new TaskOBJ(UUID.randomUUID().toString(),s,new Date());
        ArrayList<TaskOBJ> taskOBJS = new ArrayList<>();
        taskOBJS.add(taskOBJ1);
        taskOBJS.add(taskOBJ2);
        taskOBJS.add(taskOBJ3);
        TaskOBJDao.batchSaveTaskOBJ(taskOBJS);
    }

    @Test
    public void getTaskObjTest(){
        TaskOBJ taskOBJ = TaskOBJDao.getTaskObj("aa1180ad-939b-41b9-8a82-3a6b45fa58a9");
        System.out.println(taskOBJ);
    }

}
