package com.xxyuan.project.ui.database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataBaseActivity extends BaseActivity<DataBasePresenter> implements DataBaseView {


    @BindView(R.id.bt_insert)
    Button btInsert;
    @BindView(R.id.bt_query_data)
    Button btQueryData;
    @BindView(R.id.tv_show_data)
    TextView tvShowData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_base;
    }

    @Override
    protected DataBasePresenter createPresenter() {
        return new DataBasePresenter(this);
    }

    @OnClick({R.id.bt_insert,R.id.bt_query_data})
    public void onClickedView(View v) {
        switch (v.getId()) {
            case R.id.bt_insert:
                presenter.insertData();
                break;
            case R.id.bt_query_data:
                presenter.queryData();
                break;
        }
    }

    @Override
    public void showData(String string) {
        tvShowData.setText(string);
    }

}
