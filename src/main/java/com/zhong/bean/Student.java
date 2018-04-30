package com.zhong.bean;

import java.io.Serializable;

/**
 * @author 张中俊
 **/

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int sex;

    @Override
    public String toString() {
        return "name="+name+" "+"sex="+sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {

        return name;
    }

    public int getSex() {
        return sex;
    }

    public Student(String name, int sex) {

        this.name = name;
        this.sex = sex;
    }
}