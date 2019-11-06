package com.xxyuan.project.base;

/**
 * @ClassName BaseActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 15:41
 * @Version 1.0
 */

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.xxyuan.project.R;
import com.xxyuan.project.utils.AppManager;

import butterknife.ButterKnife;

/**
 * Activity基类
 *
 * @author geyifeng
 * @date 2017/5/9
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String mTag = this.getClass().getSimpleName();

    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        mActivity = this;
        setContentView(getLayoutId());
        //绑定控件
        ButterKnife.bind(this);
        //初始化沉浸式
        initImmersionBar();
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    /**
     * 子类设置布局Id
     *
     * @return the layout id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
                .navigationBarColor(R.color.colorPrimary)
                .keyboardEnable(true).init();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void setListener() {
    }
}

