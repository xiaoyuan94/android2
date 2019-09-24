package com.xxyuan.project.http.mode2;

/**
 * @ClassName BaseApi
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:19
 * @Version 1.0
 */

import io.reactivex.Observable;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseApi {
    @GET
    Observable<ResponseBody> executeGet(@Url String paramString);

    @GET
    Observable<ResponseBody> executeGet(@Url String paramString, @QueryMap Map<String, Object> paramMap);

    @POST
    Observable<ResponseBody> executePost(@Url String paramString);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> executePost(@Url String paramString, @FieldMap Map<String, Object> paramMap);
}