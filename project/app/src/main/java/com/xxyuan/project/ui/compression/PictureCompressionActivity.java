package com.xxyuan.project.ui.compression;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.xxyuan.project.R;

import java.io.File;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.blankj.utilcode.util.CrashUtils.init;

public class PictureCompressionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_compression);
        initData();
    }

    private void initData() {
        /**
         * load	传入原图
         * filter	设置开启压缩条件
         * ignoreBy	不压缩的阈值，单位为K
         * setFocusAlpha	设置是否保留透明通道
         * setTargetDir	缓存压缩图片路径    String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
         * setCompressListener	压缩回调接口
         * setRenameListener	压缩前重命名接口
         */
        Luban.with(this)
//                .load(photos)
                .ignoreBy(100)
//                .setTargetDir(getPath())
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();
    }
}