package com.example.pikamouse.learn_protocol.callback;

import com.example.pikamouse.learn_protocol.protocol.BasicProtocol;

/**
 * create by liting 2018/11/10
 */
public interface RequestCallBack {

    void onSuccess(BasicProtocol msg);

    void onFailed(int errorCode, String msg);
}
