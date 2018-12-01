package com.example.pikamouse.learn_protocol.client;

import com.example.pikamouse.learn_protocol.callback.RequestCallBack;
import com.example.pikamouse.learn_protocol.protocol.DataProtocol;

/**
 * create by liting 2018/11/10
 */
public class ConnectionClient {

    private boolean isClosed;

    private ClientRequestTask mClientRequestTask;

    public ConnectionClient(RequestCallBack requestCallBack) {
        mClientRequestTask = new ClientRequestTask(requestCallBack);
        new Thread(mClientRequestTask).start();
    }

    public void addNewRequest(DataProtocol data) {
        if (mClientRequestTask != null && !isClosed)
            mClientRequestTask.addRequest(data);
    }

    public void closeConnect() {
        isClosed = true;
        mClientRequestTask.stop();
    }
}

