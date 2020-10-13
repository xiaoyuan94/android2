package com.xxyuan.project.ui.barrage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.didichuxing.doraemonkit.okgo.utils.IOUtils;
import com.xxyuan.project.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
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


    private  class BackgroundCacheStuffer extends SpannedCacheStuffer {
        // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
         Paint paint = new Paint();

        @Override
        public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
            danmaku.padding = 10;  // 在背景绘制模式下增加padding
            super.measure(danmaku, paint, fromWorkerThread);
        }


        @Override
        public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
            paint.setColor(0x8125309b);  //弹幕背景颜色
            RectF rectf = new RectF(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku.paintHeight - 2);
//            canvas.drawRect(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku.paintHeight - 2, paint);
            canvas.drawRoundRect(rectf,30, 30, paint);//第二个参数是x半径，第三个参数是y半径
        }


        @Override
        public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
            // 禁用描边绘制
        }
    }

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {

        private Drawable mDrawable;

        /**
         * 在弹幕显示前使用新的text,使用新的text
         * @param danmaku
         * @param fromWorkerThread 是否在工作(非UI)线程,在true的情况下可以做一些耗时操作(例如更新Span的drawblae或者其他IO操作)
         * @return 如果不需重置，直接返回danmaku.text
         */
        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
//            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
//                // FIXME 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
//                new Thread() {
//
//                    @Override
//                    public void run() {
//                        String url = "http://www.bilibili.com/favicon.ico";
//                        InputStream inputStream = null;
//                        Drawable drawable = mDrawable;
//                        if (drawable == null) {
//                            try {
//                                URLConnection urlConnection = new URL(url).openConnection();
//                                inputStream = urlConnection.getInputStream();
//                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap");
//                                mDrawable = drawable;
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } finally {
//                                IOUtils.closeQuietly(inputStream);
//                            }
//                        }
//                        if (drawable != null) {
//                            drawable.setBounds(0, 0, 100, 100);
//                            SpannableStringBuilder spannable = createSpannable(drawable);
//                            danmaku.text = spannable;
//                            if (mDanmakuView != null) {
//                                mDanmakuView.invalidateDanmaku(danmaku, false);
//                            }
//                            return;
//                        }
//                    }
//                }.start();
//            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
        }
    };

    /***
     * 一些初始化工作
     */
    private void init() {
        mDanmu.enableDanmakuDrawingCache(true);
        danmakuContext = DanmakuContext.create();
        // 设置弹幕的最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 2); // 滚动弹幕最大显示2行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_LR, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_BOTTOM, true);
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3) //设置描边样式
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.1f) //是否启用合并重复弹幕
                .setScaleTextSize(1.2f) //设置弹幕滚动速度系数,只对滚动弹幕有效
                .setCacheStuffer(new BackgroundCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer  设置缓存绘制填充器，默认使用{@link SimpleTextCacheStuffer}只支持纯文字显示, 如果需要图文混排请设置{@link SpannedCacheStuffer}如果需要定制其他样式请扩展{@link SimpleTextCacheStuffer}|{@link SpannedCacheStuffer}
                .setMaximumLines(maxLinesPair) //设置最大显示行数
                .preventOverlapping(overlappingEnablePair); //设置防弹幕重叠，null为允许重叠
        mDanmu.prepare(mBaseDanmakuParser, danmakuContext);
        mDanmu.enableDanmakuDrawingCache(true);  //提升屏幕绘制效率
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
        danmaku.textSize = sp2px(14);
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
