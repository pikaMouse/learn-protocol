package com.example.pikamouse.learn_protocol;

import com.example.pikamouse.learn_protocol.callback.ResponseCallBack;
import com.example.pikamouse.learn_protocol.protocol.Config;
import com.example.pikamouse.learn_protocol.protocol.DataProtocol;
import com.example.pikamouse.learn_protocol.server.ServerResponseTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by liting 2018/11/10
 */
public class MainService {

    private static boolean isStart = true;
    private static ServerResponseTask serverResponseTask;

    public MainService() {

    }

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            serverSocket = new ServerSocket(Config.PORT);
            while (isStart) {
                Socket socket = serverSocket.accept();
                serverResponseTask = new ServerResponseTask(socket,
                        new ResponseCallBack() {

                            @Override
                            public void targetIsOffline(DataProtocol reciveMsg) {// 对方不在线
                                if (reciveMsg != null) {
                                    System.out.println(reciveMsg.getData());
                                }
                            }

                            @Override
                            public void targetIsOnline(String clientIp) {
                                System.out.println(clientIp + " is onLine");
                                System.out.println("-----------------------------------------");
                            }
                        });

                if (socket.isConnected()) {
                    executorService.execute(serverResponseTask);
                }
            }

            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    isStart = false;
                    serverSocket.close();
                    if (serverSocket != null)
                        serverResponseTask.stop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

