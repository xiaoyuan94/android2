package com.xxyuan.project.ui.barrage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.didichuxing.doraemonkit.okgo.utils.IOUtils;
import com.xxyuan.project.R;
import com.xxyuan.project.ui.scanner.card.BitmapUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class BarrageActivity extends AppCompatActivity {

    private static final int ADD_DAMU = 1001;
    @BindView(R.id.danmu)
    DanmakuView mDanmu;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.sw)
    Switch sw;

    private BaseDanmakuParser mBaseDanmakuParser = new BaseDanmakuParser() {//弹幕解析器
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private DanmakuContext danmakuContext;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barrage);
        ButterKnife.bind(this);
        init();
        setOnListener();
    }


    private class BackgroundCacheStuffer extends SpannedCacheStuffer {
        // 通过扩展SimpleTextCacheStuffer或SpannedCacheStuffer个性化你的弹幕样式
        Paint paint = new Paint();

        @Override
        public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
            //测量的相应方法
            danmaku.padding = 10;  // 在背景绘制模式下增加padding
            super.measure(danmaku, paint, fromWorkerThread);
        }


        @Override
        public void drawBackground(BaseDanmaku danmaku, Canvas canvas, float left, float top) {
            paint.setColor(0x8125309b);  //弹幕背景颜色
//            RectF rectf = new RectF(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku.paintHeight - 2);
            canvas.drawRect(left + 2, top + 2, left + danmaku.paintWidth - 2, top + danmaku.paintHeight - 2, paint);
//            canvas.drawRoundRect(rectf, 30, 30, paint);//第二个参数是x半径，第三个参数是y半径
        }


        @Override
        public void drawStroke(BaseDanmaku danmaku, String lineText, Canvas canvas, float left, float top, Paint paint) {
            // 禁用描边绘制
            //绘制的相应方法
        }
    }

    public class MyCacheStuffer extends BaseCacheStuffer {

        /**
         * 文字右边间距
         */
        private float RIGHTMARGE;
        /**
         * 文字和头像间距
         */
        private float LEFTMARGE;
        /**
         * 文字和右边线距离
         */
        private int TEXT_RIGHT_PADDING;
        /**
         * 文字大小
         */
        private float TEXT_SIZE;
        /**
         * 头像的大小
         */
        private float IMAGEHEIGHT;

        public MyCacheStuffer(Activity activity) {
            // 初始化固定参数，这些参数可以根据自己需求自行设定
            LEFTMARGE = activity.getResources().getDimension(R.dimen.DIMEN_13dp);
            RIGHTMARGE = activity.getResources().getDimension(R.dimen.DIMEN_22PX);
            IMAGEHEIGHT = activity.getResources().getDimension(R.dimen.DIMEN_60PX);
            TEXT_SIZE = activity.getResources().getDimension(R.dimen.DIMEN_24PX);
        }

        @Override
        public void measure(BaseDanmaku danmaku, TextPaint paint, boolean fromWorkerThread) {
            // 初始化数据
            Map<String, Object> map = (Map<String, Object>) danmaku.tag;
            String content = (String) map.get("content");
            Bitmap bitmap = (Bitmap) map.get("bitmap");

            // 设置画笔
            paint.setTextSize(TEXT_SIZE);

            // 计算名字和内容的长度，取最大值
            float contentWidth = paint.measureText(content);

            // 设置弹幕区域的宽度
            danmaku.paintWidth = contentWidth + IMAGEHEIGHT + LEFTMARGE + RIGHTMARGE;
            // 设置弹幕区域的高度
            danmaku.paintHeight = IMAGEHEIGHT * 2;
        }

        @Override
        public void clearCaches() {

        }

        @Override
        public void drawDanmaku(BaseDanmaku danmaku, Canvas canvas, float left, float top, boolean fromWorkerThread, AndroidDisplayer.DisplayerConfig displayerConfig) {
            // 初始化数据
            Map<String, Object> map = (Map<String, Object>) danmaku.tag;
            String content = (String) map.get("content");
            Bitmap bitmap = (Bitmap) map.get("bitmap");
            String color = (String) map.get("color");

            // 设置画笔
            Paint paint = new Paint();
            paint.setTextSize(TEXT_SIZE);

            //绘制背景
            int textLength = (int) paint.measureText(content);
            //随机数，主要是为了生成不同颜色的背景的
            paint.setColor(Color.parseColor(color));

            //获取图片的宽度
            float rectBgLeft = left;
            float rectBgTop = top;
            float rectBgRight = left + IMAGEHEIGHT + textLength + LEFTMARGE + RIGHTMARGE;
            float rectBgBottom = top + IMAGEHEIGHT;
            canvas.drawRoundRect(new RectF(rectBgLeft, rectBgTop, rectBgRight, rectBgBottom), IMAGEHEIGHT / 2, IMAGEHEIGHT / 2, paint);

            // 绘制头像
            float avatorRight = left + IMAGEHEIGHT;
            float avatorBottom = top + IMAGEHEIGHT;
            canvas.drawBitmap(bitmap, null, new RectF(left, top, avatorRight, avatorBottom), paint);

            // 绘制弹幕内容,文字白色的
            paint.setColor(Color.WHITE);
            float contentLeft = left + IMAGEHEIGHT + LEFTMARGE;
            //计算文字的相应偏移量
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            //为基线到字体上边框的距离,即上图中的top
            float textTop = fontMetrics.top;
            //为基线到字体下边框的距离,即上图中的bottom
            float textBottom = fontMetrics.bottom;

            float contentBottom = top + IMAGEHEIGHT / 2;
            //基线中间点的y轴计算公式
            int baseLineY = (int) (contentBottom - textTop / 2 - textBottom / 2);
            //绘制文字
            canvas.drawText(content, contentLeft, baseLineY, paint);
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
                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer  设置缓存绘制填充器，默认使用{@link SimpleTextCacheStuffer}只支持纯文字显示, 如果需要图文混排请设置{@link SpannedCacheStuffer}如果需要定制其他样式请扩展{@link SimpleTextCacheStuffer}|{@link SpannedCacheStuffer}
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
                //弹幕准备好的时候回掉，这里启动弹幕
                mDanmu.start();//启动弹幕
                generateSomeDanmu();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {
                //定时器更新的时候回掉
            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {
                //弹幕展示的时候回掉
            }

            @Override
            public void drawingFinished() {
                //弹幕绘制完成时回掉
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                hideAllDanMuView(isChecked);
            }
        });

        mDanmu.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {
            @Override
            public boolean onDanmakuClick(IDanmakus danmakus) {
                //点击事件
                BaseDanmaku latest = danmakus.last();
                if (null != latest) {
                    Map<String, Object> map = (Map<String, Object>) latest.tag;
                    //获取相应的数据
                    String userId = (String) map.get("content");
                    return true;
                }
                return false;
            }

            @Override
            public boolean onDanmakuLongClick(IDanmakus danmakus) {
                //长按事件
                return false;
            }

            @Override
            public boolean onViewClick(IDanmakuView view) {
                //这个我没有尝试，但是应该是内部View的点击事件吧！猜测
                return false;
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
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (showDanma) {
//                    int time = new Random().nextInt(300);
//                    String content = "" + time;
//                    addDamu(content, false);
//                    try {
//                        Thread.sleep(time);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        //定时发送
//        timer.schedule(new AsyncAddTask(), 0, 1000);
        mHandler.sendEmptyMessage(ADD_DAMU);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case ADD_DAMU:
                    sendDamu();
                    break;
            }
        }
    };

    private void sendDamu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    int time = new Random().nextInt(800);
                    String content = "timer" + time;
                    addDamu(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(ADD_DAMU);
            }
        }).start();
    }

    class AsyncAddTask extends TimerTask {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                int time = new Random().nextInt(300);
                String content = "timer" + time;
                addDamu(content, false);
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addDamu2(){
        //创建一条弹幕
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);

        if (danmaku == null || mDanmu == null) {
            return;
        }

        //设置相应的数据
        Bitmap showBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        showBitmap = BitmapUtils.getShowPicture(showBitmap);
        Map<String, Object> map = new HashMap<>(16);
        map.put("content", "这里是显示的内容");
        map.put("bitmap", showBitmap);
        Random random = new Random();
//        int randomNum = random.nextInt(mContentColorBg.length);
//        map.put("color", mContentColorBg[randomNum]);
        //设置相应的tag
//        danmaku.tag = map;
        danmaku.textSize = 0;
        danmaku.padding = 10;
        danmaku.text = "";
        // 一定会显示, 一般用于本机发送的弹幕
        danmaku.priority = 1;
        danmaku.isLive = false;
        danmaku.setTime(mDanmu.getCurrentTime());
        danmaku.textColor = Color.WHITE;
        // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmaku.textShadowColor = 0;
        //添加一条
        mDanmu.addDanmaku(danmaku);
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
        if (mDanmu != null) {
            mDanmu.release();
            mDanmu = null;
        }
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
    }
}
