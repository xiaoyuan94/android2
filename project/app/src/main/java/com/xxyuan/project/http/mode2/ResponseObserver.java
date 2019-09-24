package com.xxyuan.project.http.mode2;

/**
 * @ClassName ResponseObserver
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:25
 * @Version 1.0
 */

import android.content.Intent;

import com.blankj.utilcode.util.JsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.xxyuan.project.constant.ConfigConstant;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.io.IOException;

import okhttp3.ResponseBody;

public abstract class ResponseObserver extends Object implements Observer<ResponseBody> {
    public void onComplete() {
    }

    public void onError(Throwable paramThrowable) {
        onFailure(paramThrowable.getMessage(), "");
    }

    public abstract void onFailure(String paramString1, String paramString2);

    public void onNext(ResponseBody paramResponseBody) {
        try {
            onNextEvent(paramResponseBody.string());
        } catch (Exception e) {
            e.printStackTrace();
            onFailure(e.getMessage(), "");
        }
    }

    public abstract void onNextEvent(String paramString);

    public void onReLogin(String paramString) {
        ToastUtils.showShort(paramString);
        SPUtils.getInstance().put("userNo", "");
        ConfigConstant.cookieHashMap.clear();
    }

    public void onSubscribe(Disposable paramDisposable) {
    }
}