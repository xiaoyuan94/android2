package com.xxyuan.project.ui.database;

import com.xxyuan.project.app.XxyuanApplication;
import com.xxyuan.project.base.BasePresenter;
import com.xxyuan.project.ui.database.db.StudentDaoOpe;
import com.xxyuan.project.ui.database.model.Student;

import java.util.List;

public class DataBasePresenter extends BasePresenter<DataBaseView> {
    public DataBasePresenter(DataBaseView baseView) {
        super(baseView);
    }


    public void insertData() {
        Student student = new Student();
        student.setAge(12);
        student.setName("张三");
        student.setNum("1234123");
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
