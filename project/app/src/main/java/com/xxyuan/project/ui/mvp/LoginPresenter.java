package com.xxyuan.project.ui.mvp;

/**
 * Description: 登录主持类
 * Created by jia on 2017/12/20.
 * 人之所以能，是相信能
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> {

    private LoginModelImpl model;

    public LoginPresenter() {
        model = new LoginModelImpl();
    }

    /**
     * 检查格式
     *
     * @param name
     * @param password
     */
    public void checkFormat(String name, String password) {
//        if (TextUtils.isEmpty(name)) {
//            getView().onCheckFormatFail("请输入用户名");
//        } else if (TextUtils.isEmpty(password)) {
//            getView().onCheckFormatFail("请输入密码");
//        } else if (password.length() < 6 || password.length() > 18) {
//            getView().onCheckFormatFail("密码格式不正确");
//        } else {
//            getView().onCheckFormatSuccess();
//            login(name, password);
//        }
    }

    /**
     * 登录
     *
     * @param name
     * @param password
     */
    public void login(String name, String password) {
        model.login(name, password, new LoginContract.LoginCallBack() {
            @Override
            public void onSuccess(Login login) {
                getView().onLoginSuccess(login);
            }

            @Override
            public void onFail(String errorInfo) {
                getView().onLoginFail(errorInfo);
            }
        });
    }

}

