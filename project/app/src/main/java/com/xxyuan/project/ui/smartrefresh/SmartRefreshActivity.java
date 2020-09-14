package com.xxyuan.project.ui.smartrefresh;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.horizontal.HorizontalFooter;
import com.scwang.smart.refresh.horizontal.HorizontalHeader;
import com.scwang.smart.refresh.horizontal.SmartRefreshHorizontal;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.wrapper.RefreshFooterWrapper;
import com.xxyuan.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xxyuan.project.app.XxyuanApplication.getContext;

public class SmartRefreshActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView2)
    RecyclerView recyclerView2;
    @BindView(R.id.refreshLayout2)
    SmartRefreshHorizontal refreshLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_refresh_activity);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

//        refreshLayout2.setRefreshHeader(new MaterialHeader(getContext()));
//        refreshLayout2.setRefreshFooter(new RefreshFooterWrapper(new MaterialHeader(getContext())), -1, -2);

        refreshLayout2.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout2.setRefreshFooter(new RefreshFooterWrapper(new ClassicsFooter(getContext())), -1, -2);

        refreshLayout2.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout2.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout2.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshLayout2.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }
}