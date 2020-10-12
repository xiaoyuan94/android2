package com.xxyuan.project.ui.barrage;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.xxyuan.project.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class BarrageActivity extends AppCompatActivity {

    @BindView(R.id.danmu)
    DanmakuView mDanmu;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.sw)
    Switch sw;

    private boolean showDanma;
    private BaseDanmakuParser mBaseDanmakuParser = new BaseDanmakuParser() {//弹幕解析器
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private DanmakuContext danmakuContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barrage);
        ButterKnife.bind(this);
        init();
        setOnListener();
    }

    /***
     * 一些初始化工作
     */
    private void init() {
        mDanmu.enableDanmakuDrawingCache(true);
        danmakuContext = DanmakuContext.create();
        danmakuContext.setScaleTextSize(1.1f);
        mDanmu.prepare(mBaseDanmakuParser, danmakuContext);
    }

    /***
     * 弹幕的准备工作，发送按钮监听。。
     */
    private void setOnListener() {

        mDanmu.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanma = true;
                mDanmu.start();//启动弹幕
                generateSomeDanmu();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideAllDanMuView(isChecked);
            }
        });
    }

    /**
     * 显示或者隐藏弹幕
     *
     * @param hide
     */
    private void hideAllDanMuView(boolean hide) {
        if (hide) {
            if (mDanmu != null) {
                mDanmu.hide();
            }
        } else {
            if (mDanmu != null) {
                mDanmu.show();
            }
        }
    }

    /***
     * 随机产生一些弹幕
     */
    private void generateSomeDanmu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanma) {
                    int time = new Random().nextInt(300);
                    String content = "" + time;
                    addDamu(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /***
     * 添加弹幕的方法
     * @param content 弹幕的内容
     * @param isSelf 是否是用户发送的弹幕
     */
    private void addDamu(String content, boolean isSelf) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.priority = 0;
        danmaku.textSize = sp2px(20);
        danmaku.setTime(mDanmu.getCurrentTime());
        danmaku.textColor = Color.argb(new Random().nextInt(256), new Random().nextInt(256),
                new Random().nextInt(256), new Random().nextInt(256));
        if (isSelf) {
            danmaku.borderColor = Color.GREEN;
        }
        mDanmu.addDanmaku(danmaku);
    }

    private float sp2px(int i) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (i * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmu != null && mDanmu.isPrepared()) {
            mDanmu.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmu != null && mDanmu.isPrepared() && mDanmu.isPaused()) {
            mDanmu.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanma = false;
        if (mDanmu != null) {
            mDanmu.release();
            mDanmu = null;
        }
    }
}
