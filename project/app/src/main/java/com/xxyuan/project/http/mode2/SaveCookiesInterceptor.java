package com.xxyuan.project.http.mode2;

/**
 * @ClassName SaveCookiesInterceptor
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:36
 * @Version 1.0
 */

import com.xxyuan.project.constant.ConfigConstant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SaveCookiesInterceptor implements Interceptor {
    private static final String COOKIE_PREF = "cookies_prefs";

    private String encodeCookie(List<String> paramList) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Iterator iterator = paramList.iterator();
        while (iterator.hasNext()) {
            for (String str : ((String) iterator.next()).split(";")) {
                if (!arrayList.contains(str))
                    arrayList.add(str);
            }
        }
        iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            stringBuilder.append((String) iterator.next());
            stringBuilder.append(";");
        }
        int i = stringBuilder.lastIndexOf(";");
        if (stringBuilder.length() - 1 == i)
            stringBuilder.deleteCharAt(i);
        return stringBuilder.toString();
    }

    private void saveCookie(String paramString1, String paramString2, String paramString3) {
        ConfigConstant.cookieHashMap.put(paramString2, paramString3);
    }

    public Response intercept(Interceptor.Chain paramChain) throws IOException {
        Request request = paramChain.request();
        Response response = paramChain.proceed(request);
        if (!response.headers("set-cookie").isEmpty()) {
            String str = encodeCookie(response.headers("set-cookie"));
            saveCookie(request.url().toString(), request.url().host(), str);
        }
        return response;
    }
}

