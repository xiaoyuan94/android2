package com.xxyuan.project.ui.mvp2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MvpActivity extends BaseActivity<MvpPresenter> implements MvpView {


    @BindView(R.id.bt_click)
    Button btClick;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected MvpPresenter createPresenter() {
        return new MvpPresenter(this);
    }

    @OnClick({R.id.bt_click})
    public void onClickedView(View v){
        switch (v.getId()){
            case R.id.bt_click:
                presenter.getDouban();
                break;
        }
    }


}
