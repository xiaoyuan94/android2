package com.xxyuan.project.ui.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity {

    public String TAG = getClass().getSimpleName() + "";

    protected T mPresenter;

    public Context mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityView(savedInstanceState);
        mContext = BaseActivity.this;
        //创建presenter
        mPresenter = createPresenter();
        // presenter与view绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        //绑定控件
        unbinder = ButterKnife.bind(this);
        initData();
    }


    /**
     * 关于Activity的界面填充的抽象方法，需要子类必须实现
     */
    protected abstract void initActivityView(Bundle savedInstanceState);

    /**
     * 创建Presenter 对象
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }
}

