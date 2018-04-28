package com.zhong;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 张中俊
 **/
public class TimeStampTest {
    @Test
    public void convertTest(){
        Date d = new Date();
        Timestamp timestamp = new Timestamp(d.getTime());
        System.out.println(d);
        System.out.println(timestamp);
    }
}
