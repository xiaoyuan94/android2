package com.xxyuan.project.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.OnKeyboardListener;
import com.xxyuan.project.R;

/**
 * @ClassName XxyuanApplication
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 11:56
 * @Version 1.0
 */
public class XxyuanApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //对全局属性赋值
        mContext = getApplicationContext();
        //初始化utils
        initUtils();
    }



    /**
     * 初始化utils
     */
    private void initUtils() {
        Utils.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
