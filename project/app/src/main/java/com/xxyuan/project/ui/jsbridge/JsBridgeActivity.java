package com.xxyuan.project.ui.jsbridge;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.xxyuan.project.R;
import com.xxyuan.project.base.BaseActivity;
import com.xxyuan.project.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import wendu.dsbridge.DWebView;
import wendu.dsbridge.OnReturnValue;

public class JsBridgeActivity extends BaseActivity {

    @BindView(R.id.dwv_echart)
    DWebView dwvEchart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_js_bridge;
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        super.initData();
        DWebView.setWebContentsDebuggingEnabled(true);
        dwvEchart.addJavascriptObject(new JsApi(), null);
//        dwvEchart.addJavascriptObject(new JsEchoApi(),null);
//        dwvEchart.loadUrl("file:///android_asset/native-call-js.html");
        dwvEchart.loadUrl("file:///android_asset/js-call-native.html");
    }

    @Override
    protected void setListener() {
        super.setListener();
        dwvEchart.callHandler("addValue", new Object[]{3, 4}, new OnReturnValue<Integer>() {
            @Override
            public void onValue(Integer retValue) {
                LogUtils.d("addValue result:" + retValue);
            }
        });
    }
}
