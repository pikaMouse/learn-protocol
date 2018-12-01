package com.example.pikamouse.learn_protocol.callback;

import com.example.pikamouse.learn_protocol.protocol.DataProtocol;

/**
 * create by liting 2018/11/10
 */
public interface ResponseCallBack {

    void targetIsOffline(DataProtocol reciveMsg);

    void targetIsOnline(String clientIp);
}
