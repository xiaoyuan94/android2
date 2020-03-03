package com.xxyuan.project.http.mode2;


import com.xxyuan.project.constant.URLConst;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Time: 2019/7/15
 * Author: ZF
 * Description:
 */

public class RetrofitHelper {

    private volatile static RetrofitHelper mInstance = null;
    private Retrofit retrofit = null;
    private static long connectTimeout = 10;
    private static long writeTimeout = 60;
    private static long readTimeout = 30;
    private final BaseApi baseApi;

    public RetrofitHelper() {

        retrofit = new Retrofit.Builder()//
                .baseUrl(URLConst.BASE_URL) //
                .client(getOkHttpClient())//
                .addConverterFactory(GsonConverterFactory.create())//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
                .build();

        baseApi = retrofit.create(BaseApi.class);

    }

    private OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder clientBuild = new OkHttpClient.Builder();
        clientBuild.connectTimeout(connectTimeout, TimeUnit.SECONDS)//
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)//
                .readTimeout(readTimeout, TimeUnit.SECONDS)//
                .addInterceptor(new HttpHeaderInterceptor())
                .cookieJar(new SimpleCookieJar())
                .retryOnConnectionFailure(true);

        return clientBuild.build();
    }

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }

    public <T> void request(MethodType type, String url, ResponseObserver observer) {
        Observable<ResponseBody> myObservable = null;
        if (MethodType.GET == type) {
            myObservable = baseApi.executeGet(url);
        } else if (MethodType.POST == type) {
            myObservable = baseApi.executePost(url);
        }

        subscribeOn(myObservable, observer);
    }

    public <T> void request(MethodType type, String url, Map<String, Object> params, ResponseObserver observer) {
        if (params == null || params.size() == 0) {
            request(type, url, observer);
            return;
        }

        Observable<ResponseBody> myObservable = null;
        if (MethodType.GET == type) {
            myObservable = baseApi.executeGet(url, params);
        } else if (MethodType.POST == type) {
            myObservable = baseApi.executePost(url, params);
        }
        subscribeOn(myObservable, observer);

    }

    public void subscribeOn(Observable<ResponseBody> myObservable, ResponseObserver observer) {

        myObservable.subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(observer);
    }


}
