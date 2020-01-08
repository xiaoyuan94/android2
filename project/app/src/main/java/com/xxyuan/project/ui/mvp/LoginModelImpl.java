package com.xxyuan.project.ui.mvp;

/**
 * Description: 登录 Model实现类
 * Created by jia on 2017/12/20.
 * 人之所以能，是相信能
 */
public class LoginModelImpl implements LoginContract.LoginModel {

    /**
     * 登录方法
     * @param name
     * @param password
     * @param callBack
     */
    @Override
    public void login(String name, String password, final LoginContract.LoginCallBack callBack) {
//        LoginNetUtils.getInstance().login(name, password, new BaseSubscriber<Login>() {
//            @Override
//            public void onSuccess(Login login) {
//                callBack.onSuccess(login);
//            }
//
//            @Override
//            public void onFail(String info) {
//                callBack.onFail(info);
//            }
//        });
    }
}

