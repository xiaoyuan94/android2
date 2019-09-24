package com.xxyuan.project.ui.h5;

/**
 * @ClassName CookieUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:47
 * @Version 1.0
 */

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.xxyuan.project.http.mode2.SimpleCookieJar;

import java.util.List;
import okhttp3.Cookie;

public class CookieUtils {
    public static void removeCookies(Context paramContext) {
        CookieSyncManager.createInstance(paramContext);
        CookieManager.getInstance().removeSessionCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static void synCookies(Context paramContext, String paramString) {
        CookieSyncManager.createInstance(paramContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        List<Cookie> list = SimpleCookieJar.getCookies();
        StringBuffer stringBuffer = new StringBuffer();
        for (Cookie cookie : list) {
            String str1 = cookie.name();
            String str2 = cookie.value();
            if (!TextUtils.isEmpty(str1) && !TextUtils.isEmpty(str2)) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str1);
                stringBuilder2.append("=");
                stringBuffer.append(stringBuilder2.toString());
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(str2);
                stringBuilder1.append(";");
                stringBuffer.append(stringBuilder1.toString());
            }
        }
        String[] arrayOfString = stringBuffer.toString().split(";");
        for (byte b = 0; b < arrayOfString.length; b++) {
            Log.d("cookie[i]", arrayOfString[b]);
            cookieManager.setCookie(paramString, arrayOfString[b]);
        }
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
            return;
        }
        CookieManager.getInstance().flush();
    }
}

