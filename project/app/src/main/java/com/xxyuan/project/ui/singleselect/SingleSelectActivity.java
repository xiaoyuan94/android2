package com.xxyuan.project.ui.singleselect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xxyuan.project.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleSelectActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private SingleSelectAdapter selectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_select);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        selectAdapter = new SingleSelectAdapter();
        recyclerView.setAdapter(selectAdapter);
    }

    private void initData() {
        List<SingleSelectVo> list = new ArrayList<>();
        SingleSelectVo singleSelectVo1 = new SingleSelectVo();
        singleSelectVo1.setName("测试1");
        SingleSelectVo singleSelectVo2 = new SingleSelectVo();
        singleSelectVo2.setName("测试2");
        SingleSelectVo singleSelectVo3 = new SingleSelectVo();
        singleSelectVo3.setName("测试3");
        list.add(singleSelectVo1);
        list.add(singleSelectVo2);
        list.add(singleSelectVo3);
        selectAdapter.notifyDataSetChanged(list);
    }

}