package com.xxyuan.project.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.AppUtils;
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
        //初始化路由
        initRoter();
    }

    private void initRoter() {
        if (AppUtils.isAppDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
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
