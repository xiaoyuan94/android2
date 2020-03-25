package com.xxyuan.project.ui.h5;

public interface WebViewJavascriptBridge {

    void send(String data);
    void send(String data, CallBackFunction responseCallback);

}
