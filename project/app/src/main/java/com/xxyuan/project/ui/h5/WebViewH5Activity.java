package com.xxyuan.project.ui.h5;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.tencent.smtt.sdk.WebView;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewH5Activity extends BaseActivity implements WebViewH5View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_tool_bar)
    Toolbar titleToolBar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progress)
    ProgressBar progress;
    private X5WebChromeClient x5WebChromeClient;
    private X5WebViewClient x5WebViewClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view_h5;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new WebViewH5Presenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
        initToolBar();
        initWebView();
    }

    private void initWebView() {
//        x5WebChromeClient = webView.getX5WebChromeClient();
//        x5WebViewClient = webView.getX5WebViewClient();
//        x5WebChromeClient.setVideoWebListener(videoWebListener);
//        x5WebViewClient.setWebListener(interWebListener);
//        x5WebChromeClient.setWebListener(interWebListener);
    }

    private void initToolBar() {
        setSupportActionBar(titleToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        titleToolBar.setOverflowIcon(ContextCompat.getDrawable(this, R.mipmap.icon_more));
        tvTitle.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTitle.setSelected(true);
            }
        }, 1000);
        tvTitle.setText("加载中……");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //全屏播放退出全屏
            if (x5WebChromeClient!=null && x5WebChromeClient.inCustomView()) {
                x5WebChromeClient.hideCustomView();
                return true;
                //返回网页上一页
            } else if (webView.canGoBack()) {
                webView.goBack();
                return true;
                //退出网页
            } else {
                handleFinish();
            }
        }
        return false;
    }

    public void handleFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (x5WebChromeClient!=null){
                x5WebChromeClient.removeVideoView();
            }
            //有音频播放的web页面的销毁逻辑
            //在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
            //但是注意：webview调用destory时,webview仍绑定在Activity上
            //这是由于自定义webview构建时传入了该Activity的context对象
            //因此需要先从父容器中移除webview,然后再销毁webview:
            if (webView != null) {
                ViewGroup parent = (ViewGroup) webView.getParent();
                if (parent != null) {
                    parent.removeView(webView);
                }
                webView.removeAllViews();
                webView.destroy();
                webView = null;
            }
        } catch (Exception e) {
            Log.e("X5WebViewActivity", e.getMessage());
        }
        super.onDestroy();
    }

}
