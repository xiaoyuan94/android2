package com.xxyuan.project.ui.filedown;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.liulishuo.okdownload.DownloadTask;
import com.xxyuan.project.base.BasePresenter;
import com.xxyuan.project.constant.ConfigConstant;

import java.io.File;

public class FileDownPresenter extends BasePresenter<FileDownView> {
    public FileDownPresenter(FileDownView baseView) {
        super(baseView);
    }

    public void down(){
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setUrl("https://qd.myapp.com/myapp/qqteam/AndroidQQ/Android_8.2.8.4440_537063338.apk");
        itemInfo.setPkgName("qq.apk");
        DownloadTask downloadTask = createDownloadTask(itemInfo);
        downloadTask.enqueue(new FileDownloadListener());//异步执行任务
    }


    private DownloadTask createDownloadTask(ItemInfo itemInfo) {
        return new DownloadTask.Builder(itemInfo.url, new File(ConfigConstant.PARENT_PATH)) //设置下载地址和下载目录，这两个是必须的参数
                .setFilename(itemInfo.pkgName)//设置下载文件名，没提供的话先看 response header ，再看 url path(即启用下面那项配置)
                .setFilenameFromResponse(false)//是否使用 response header or url path 作为文件名，此时会忽略指定的文件名，默认false
                .setPassIfAlreadyCompleted(true)//如果文件已经下载完成，再次下载时，是否忽略下载，默认为true(忽略)，设为false会从头下载
                .setConnectionCount(1)  //需要用几个线程来下载文件，默认根据文件大小确定；如果文件已经 split block，则设置后无效
                .setPreAllocateLength(false) //在获取资源长度后，设置是否需要为文件预分配长度，默认false
                .setMinIntervalMillisCallbackProcess(100) //通知调用者的频率，避免anr，默认3000
                .setWifiRequired(false)//是否只允许wifi下载，默认为false
                .setAutoCallbackToUIThread(true) //是否在主线程通知调用者，默认为true
                //.setHeaderMapFields(new HashMap<String, List<String>>())//设置请求头
                //.addHeader(String key, String value)//追加请求头
                .setPriority(0)//设置优先级，默认值是0，值越大下载优先级越高
                .setReadBufferSize(4096)//设置读取缓存区大小，默认4096
                .setFlushBufferSize(16384)//设置写入缓存区大小，默认16384
                .setSyncBufferSize(65536)//写入到文件的缓冲区大小，默认65536
                .setSyncBufferIntervalMillis(2000) //写入文件的最小时间间隔，默认2000
                .build();
    }
}
