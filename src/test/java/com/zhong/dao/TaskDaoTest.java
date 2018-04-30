package com.zhong.dao;

import com.zhong.bean.Task;
import com.zhong.utils.MysqlHelper;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author 张中俊
 **/
public class TaskDaoTest {
    @Test
    public void saveTaskTest() throws UnsupportedEncodingException {
        ArrayList<byte[]> t = new ArrayList<>();
        for(int i=0;i<100;i++){
            t.add(("ind"+i).getBytes("utf-8"));
        }
        Task task = new Task(t,"hello world",12306);
        TaskDao.saveTask(task);
    }

    @Test
    public void getTaskTest(){
        Task task = TaskDao.getTask(1);
        System.out.println(task);
    }


}
