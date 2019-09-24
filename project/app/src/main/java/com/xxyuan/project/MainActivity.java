package com.xxyuan.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xxyuan.project.adapter.RecyclerViewAdapter;
import com.xxyuan.project.model.MainItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private List<MainItem> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
        RecyclerViewAdapter recyclerViewMain = new RecyclerViewAdapter(mData);
        recyclerView.setAdapter(recyclerViewMain);
    }
    private void initData() {
        mData.add(new MainItem("H5使用",MainActivity.class));
    }

}
