package com.xxyuan.project.ui.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private int age;
    private String num;
    private  String info;
    private String text01;
    private String text02;
    
    
    @Generated(hash = 1577919575)
    public Student(Long id, String name, int age, String num, String info,
            String text01, String text02) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.num = num;
        this.info = info;
        this.text01 = text01;
        this.text02 = text02;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getText01() {
        return this.text01;
    }
    public void setText01(String text01) {
        this.text01 = text01;
    }

    public String getText02() {
        return this.text02;
    }
    public void setText02(String text02) {
        this.text02 = text02;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", num='" + num + '\'' +
                ", info='" + info + '\'' +
                ", text01='" + text01 + '\'' +
                ", text02='" + text02 + '\'' +
                '}';
    }
}
