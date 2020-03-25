package com.xxyuan.project.ui.h5;

public interface BridgeHandler {

    /**
     * 处理消息
     * @param data						消息内容
     * @param function					回调
     */
    void handler(String data, CallBackFunction function);

}
