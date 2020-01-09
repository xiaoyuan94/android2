package com.xxyuan.project.ui.mvp;

/**
 * Description:
 * Created by jia on 2017/12/20.
 * 人之所以能，是相信能
 */
public class LoginContract {

    public interface LoginView{

        void onLoginSuccess(Login login);

        void onLoginFail(String errorInfo);
    }

    public interface LoginModel{
        void login(String name,String password,LoginCallBack callBack);
    }

    public interface LoginCallBack{
        void onSuccess(Login login);

        void onFail(String errorInfo);
    }
}

