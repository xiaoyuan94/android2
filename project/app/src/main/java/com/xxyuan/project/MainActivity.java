package com.xxyuan.project;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.xxyuan.project.adapter.RecyclerViewAdapter;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.base.BasePresenter;
import com.xxyuan.project.model.MainItem;
import com.xxyuan.project.ui.database.DataBaseActivity;
import com.xxyuan.project.ui.filedown.FileDownActivity;
import com.xxyuan.project.ui.h5.WebViewH5Activity;
import com.xxyuan.project.ui.immersionBar.view.ImmersionBarActivity;
import com.xxyuan.project.ui.jsbridge.JsBridgeActivity;
import com.xxyuan.project.ui.mvp2.MvpActivity;
import com.xxyuan.project.ui.tablayout.TabActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<MainItem> mData = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        RecyclerViewAdapter recyclerViewMain = new RecyclerViewAdapter(mData);
        recyclerView.setAdapter(recyclerViewMain);
    }
    protected void initData() {
        mData.add(new MainItem("沉侵栏的使用", ImmersionBarActivity.class));
        mData.add(new MainItem("tab使用", TabActivity.class));
        mData.add(new MainItem("dsbridge桥接的使用", JsBridgeActivity.class));
        mData.add(new MainItem("mvp2的使用", MvpActivity.class));
        mData.add(new MainItem("文件下载", FileDownActivity.class));
        mData.add(new MainItem("数据库使用", DataBaseActivity.class));
        mData.add(new MainItem("x5使用", WebViewH5Activity.class));
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
    }
}
