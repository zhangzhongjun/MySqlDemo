package com.zhong.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 张中俊
 **/
public class TaskOBJ {
    private String id;
    private Student student;
    private Date uploadtime;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: "+id);
        sb.append(System.lineSeparator());
        sb.append("student: "+student);
        sb.append(System.lineSeparator());
        sb.append("uploadtime: "+uploadtime);
        return sb.toString();
    }

    public TaskOBJ(String id, Student student, Date uploadtime) {
        this.id = id;
        this.student = student;
        this.uploadtime = uploadtime;
    }

    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Date getUploadtime() {
        return uploadtime;
    }
}

