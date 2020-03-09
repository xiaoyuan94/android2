package com.xxyuan.project.constant;

import android.os.Environment;

import com.blankj.utilcode.util.FileUtils;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConfigConstant
 * @Description TODO
 * @Author Administrator
 * @Date 2019/9/24 14:18
 * @Version 1.0
 */
public class ConfigConstant {
    public static final String PARENT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/xxyuan/";
    public static Map<String, String> cookieHashMap = new HashMap();
}
