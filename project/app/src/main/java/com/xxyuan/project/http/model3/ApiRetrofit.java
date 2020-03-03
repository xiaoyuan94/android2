package com.xxyuan.project.http.model3;

import android.util.Log;

import com.xxyuan.project.app.XxyuanApplication;
import com.xxyuan.project.http.model3.api.ApiServer;
import com.xxyuan.project.http.model3.gson.BaseConverterFactory;
import com.xxyuan.project.http.model3.persistentcookiejar.ClearableCookieJar;
import com.xxyuan.project.http.model3.persistentcookiejar.PersistentCookieJar;
import com.xxyuan.project.http.model3.persistentcookiejar.cache.SetCookieCache;
import com.xxyuan.project.http.model3.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xxyuan.project.utils.AppUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 作者： ch
 * 时间： 2016/12/27.13:56
 * 描述：
 * 来源：
 */
public class ApiRetrofit {

    public final String BASE_SERVER_URL = "https://douban.uieee.com/";

    private static ApiRetrofit apiRetrofit;
    private Retrofit retrofit;
    private OkHttpClient client;
    private ApiServer apiServer;

    public ApiRetrofit() {
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
                new SharedPrefsCookiePersistor(XxyuanApplication.getContext()));
        client = new OkHttpClient.Builder()
                //添加log拦截器
                .addInterceptor(new LogInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                //添加自定义的解析器
                .addConverterFactory(BaseConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }

}
