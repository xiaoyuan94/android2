package com.xxyuan.project;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xxyuan.project.adapter.RecyclerViewAdapter;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.model.MainItem;
import com.xxyuan.project.ui.immersionBar.view.ImmersionBarActivity;

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

    protected void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        RecyclerViewAdapter recyclerViewMain = new RecyclerViewAdapter(mData);
        recyclerView.setAdapter(recyclerViewMain);
    }
    protected void initData() {
        mData.add(new MainItem("沉侵栏的使用", ImmersionBarActivity.class));
    }

}
