package com.zhong.bean;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * 我们将Task作为生产和消费的单位：
 * @author 张中俊
 * @create 2018-03-30 11:05
 **/
public class Task {
    /**
     * 对应数据库中的blob
     */
    private ArrayList<byte[]> obj;
    /**
     * 对应数据库中的varchar
     */
    private String str;
    /**
     * 对应数据库中的int
     */
    private int i;

    public ArrayList<byte[]> getObj() {
        return obj;
    }

    public String getStr() {
        return str;
    }

    public int getI() {
        return i;
    }

    public Task(ArrayList<byte[]> obj, String str, int i) {

        this.obj = obj;
        this.str = str;
        this.i = i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====您正在查看Task中的内容=====");
        sb.append(System.lineSeparator());
        for(byte[] t : obj){
            sb.append(Arrays.toString(t));
            sb.append(System.lineSeparator());
        }
        sb.append("str= "+str);
        sb.append(System.lineSeparator());
        sb.append("i= "+i);
        sb.append(System.lineSeparator());
        sb.append("================================");
        return sb.toString();
    }
}
