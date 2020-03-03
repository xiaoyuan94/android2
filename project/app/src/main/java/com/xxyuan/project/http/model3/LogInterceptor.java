package com.xxyuan.project.http.model3;

import android.util.Log;

import com.xxyuan.project.utils.AppUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.d(TAG, "----------Request Start----------------");
        Log.d(TAG, "| " + request.toString() + request.headers().toString());
        Log.d(TAG, "| Response:" + AppUtils.unicodeToUTF_8(content));
        Log.d(TAG, "----------Request End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
