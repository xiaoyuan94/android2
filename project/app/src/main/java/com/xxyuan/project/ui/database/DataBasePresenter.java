package com.xxyuan.project.ui.database;

import com.xxyuan.project.app.XxyuanApplication;
import com.xxyuan.project.base.BasePresenter;
import com.xxyuan.project.db.StudentDaoOpe;
import com.xxyuan.project.ui.database.model.Student;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataBasePresenter extends BasePresenter<DataBaseView> {
    public DataBasePresenter(DataBaseView baseView) {
        super(baseView);
    }


    public void insertData() {
        Student student = new Student();
        student.setAge(24);
        student.setName("李四");
        student.setNum("123123");
        student.setInfo("李四的信息");
        student.setText01("测试数据库升级01");
        student.setText02("数据库升级02");
        StudentDaoOpe.insertData(XxyuanApplication.getContext(),student);
    }

    public void queryData() {
        List<Student> students = StudentDaoOpe.queryAll(XxyuanApplication.getContext());
        StringBuffer buffer  = new StringBuffer();
        for (Student student :students){
            buffer.append(student.toString()+"   ");
        }
        baseView.showData(buffer.toString());
    }
}
