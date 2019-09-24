package com.xxyuan.project.http.mode2;

/**
 * @ClassName AddCookiesInterceptor
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:17
 * @Version 1.0
 */

import android.text.TextUtils;
import android.util.Log;

import com.xxyuan.project.constant.ConfigConstant;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    private static final String COOKIE_PREF = "cookies_prefs";

    private String getCookie(String paramString1, String paramString2) { return (String) ConfigConstant.cookieHashMap.get(paramString2); }

    public Response intercept(Interceptor.Chain paramChain) throws IOException {
        Request request = paramChain.request();
        Request.Builder builder = request.newBuilder();
        String str = getCookie(request.url().toString(), request.url().host());
        if (!TextUtils.isEmpty(str)) {
            builder.addHeader("Cookie", str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("request url: ");
            stringBuilder.append(request.url().toString());
            stringBuilder.append("  interceptor addHeader Cookie: ");
            stringBuilder.append(str);
            Log.d("AddCookiesInterceptor", stringBuilder.toString());
        }
        return paramChain.proceed(builder.build());
    }
}
