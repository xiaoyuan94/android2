package com.xxyuan.project.ui.mvp;

import android.os.Bundle;
import android.view.View;

import com.xxyuan.project.R;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity<LoginContract.LoginView, LoginPresenter>
        implements LoginContract.LoginView, View.OnClickListener {


    @Override
    protected void initActivityView(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_login);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
    }


    @Override
    public void onLoginSuccess(Login login) {
    }

    @Override
    public void onLoginFail(String errorInfo) {
    }

    @Override
    public void onClick(View view) {
    }
}

