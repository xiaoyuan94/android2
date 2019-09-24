package com.xxyuan.project.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

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

    }
    public static Context getContext() {
        return mContext;
    }
}
