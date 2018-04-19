package com.zhong;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
    public void getTaskTest(){
        Task task = MysqlHelper.getTask(1);
        System.out.println(task);
    }
}
