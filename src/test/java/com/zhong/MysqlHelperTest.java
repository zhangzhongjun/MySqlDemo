package com.zhong;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @author 张中俊
 **/
public class MysqlHelperTest {
    @Test
    public void saveTaskTest() throws UnsupportedEncodingException {
        ArrayList<byte[]> t = new ArrayList<>();
        for(int i=0;i<100;i++){
            t.add(("ind"+i).getBytes("utf-8"));
        }
        Task task = new Task(t,"hello world",12306);
        MysqlHelper.saveTask(task);
    }

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
        MysqlHelper.batchSaveTaskOBJ(taskOBJS);
    }

    @Test
    public void getTaskTest(){
        Task task = MysqlHelper.getTask(1);
        System.out.println(task);
    }

    @Test
    public void getTaskObjTest(){
        TaskOBJ taskOBJ = MysqlHelper.getTaskObj("4b81751d-7aa5-41a2-a113-8426cfa68d0d");
        System.out.println(taskOBJ);
    }
}
