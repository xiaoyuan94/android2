package com.xxyuan.project.http.mode2;

/**
 * @ClassName SimpleCookieJar
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:37
 * @Version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public final class SimpleCookieJar implements CookieJar {
    private static List<Cookie> cookies;

    private final List<Cookie> allCookies = new ArrayList();

    public static List<Cookie> getCookies() {
        List list = cookies;
        return (list != null) ? list : new ArrayList();
    }

    public static void setCookies(List<Cookie> paramList) {
        cookies = paramList;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        allCookies.addAll(cookies);
        setCookies(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : allCookies) {
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }
}