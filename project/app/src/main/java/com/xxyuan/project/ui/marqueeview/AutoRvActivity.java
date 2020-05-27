package com.xxyuan.project.ui.marqueeview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.xxyuan.project.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoRvActivity extends AppCompatActivity {

    private static final int MAINTAIN_INFO = 1001;
    @BindView(R.id.rv_gundong)
    RecyclerView rvGundong;
    private ArrayList mList;
    private MaintainInfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_rv);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add("如何做好队汽车的轮胎养护0");
        mList.add("如何做好队汽车的轮胎养护1");
        mList.add("如何做好队汽车的轮胎养护2");
        mList.add("如何做好队汽车的轮胎养护3");
        rvGundong.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MaintainInfoAdapter(mList);
        rvGundong.setAdapter(mAdapter);
        Message msg = new Message();
        msg.what = MAINTAIN_INFO;
        sHandler.sendMessageDelayed(msg, 3000);
    }
    //当前显示的item
    private int pos = 0;
    private Handler sHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MAINTAIN_INFO:
                    rvGundong.smoothScrollBy(0, SizeUtils.dp2px(60));
                    pos++;
                    Message message = new Message();
                    message.what = MAINTAIN_INFO;
                    sHandler.removeMessages(MAINTAIN_INFO);
                    sHandler.sendMessageDelayed(message, 3000);
                    break;
            }
        }
    };

}
