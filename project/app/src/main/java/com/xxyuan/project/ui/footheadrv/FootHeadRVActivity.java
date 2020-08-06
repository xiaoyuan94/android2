package com.xxyuan.project.ui.footheadrv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xxyuan.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootHeadRVActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    HeaderRecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_head_r_v);
        ButterKnife.bind(this);
    }

    private void showLinearList() {
        LinearAdapter adapter = new LinearAdapter(this);
        HeaderViewAdapter headerViewAdapter = new HeaderViewAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
//        View hv1 = LayoutInflater.from(this).inflate(R.layout.layout_header, rvList, false);
//        View hv2 = LayoutInflater.from(this).inflate(R.layout.layout_header_view, rvList, false);
//        View fv = LayoutInflater.from(this).inflate(R.layout.layout_footer_view, rvList, false);
//        headerViewAdapter.addHeaderView(hv1);
//        headerViewAdapter.addHeaderView(hv2);
//        headerViewAdapter.addFooterView(fv);
        rvList.setAdapter(headerViewAdapter);
    }

}
