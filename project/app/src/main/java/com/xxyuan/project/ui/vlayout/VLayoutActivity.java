package com.xxyuan.project.ui.vlayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.xxyuan.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VLayoutActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_layout);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);

        LinearLayoutHelper linearHelper = new LinearLayoutHelper(5);
        delegateAdapter.addAdapter(new RVAdapter(this, linearHelper));

        recyclerView.setAdapter(delegateAdapter);


    }
}