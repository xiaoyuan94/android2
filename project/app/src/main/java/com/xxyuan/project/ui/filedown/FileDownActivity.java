package com.xxyuan.project.ui.filedown;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liulishuo.okdownload.core.Util;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileDownActivity extends BaseActivity<FileDownPresenter> implements FileDownView {


    @BindView(R.id.bt_down)
    Button btDown;
    @BindView(R.id.tv_pb_show)
    TextView tvPbShow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_down;
    }

    @Override
    protected FileDownPresenter createPresenter() {
        return new FileDownPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
//        Util.enableConsoleLog();//打开下载日志
    }

    @OnClick({R.id.bt_down})
    public void onClickedView(View v){
        switch (v.getId()){
            case R.id.bt_down:
                presenter.down();
                break;
        }
    }

}
