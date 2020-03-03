package com.xxyuan.project.http.model3;


import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time: 2019/7/13
 * Author: ZF
 * Description:
 */

public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request request = chain.request()//
                .newBuilder()//
//                .addHeader("From", Const.FROM)//
                .build();

        return chain.proceed(request);
    }
}
