package com.xxyuan.project.ui.immersionBar.view;

import android.widget.ImageView;

import com.gyf.immersionbar.ImmersionBar;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseImmersionFragment;

import butterknife.BindView;

/**
 * @author geyifeng
 * @date 2017/7/20
 */

public class HomeTwoFragment extends BaseImmersionFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two_home;
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(false)
                .init();
    }

    @Override
    protected void initView() {

    }
}
