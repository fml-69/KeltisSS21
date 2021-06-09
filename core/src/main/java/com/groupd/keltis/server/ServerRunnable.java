package com.groupd.keltis.server;

import com.groupd.keltis.network.NetworkServer;

import java.util.concurrent.CountDownLatch;

public class ServerRunnable implements Runnable{

    private final NetworkServer networkServer;

    private boolean flag;


    public ServerRunnable(int port, CountDownLatch countDownLatch){
        flag = true;
        networkServer = new NetworkServer(port, countDownLatch);
    }

    @Override
    public void run() {
        do {
            networkServer.receivePackets();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        } while (flag);
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

}
