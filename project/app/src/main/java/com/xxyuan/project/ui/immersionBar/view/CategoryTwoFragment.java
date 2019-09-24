package com.xxyuan.project.ui.immersionBar.view;

import com.gyf.immersionbar.ImmersionBar;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseImmersionFragment;

/**
 * @author geyifeng
 * @date 2017/7/20
 */

public class CategoryTwoFragment extends BaseImmersionFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two_category;
    }

    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .navigationBarColor(R.color.btn1)
                .init();
    }
}
