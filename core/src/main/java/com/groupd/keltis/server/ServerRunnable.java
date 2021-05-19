package com.groupd.keltis.server;

import com.groupd.keltis.network.NetworkServer;

import java.util.concurrent.CountDownLatch;

public class ServerRunnable implements Runnable{

    private final NetworkServer networkServer;


    public ServerRunnable(int port, CountDownLatch countDownLatch){

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
        } while (true);
    }

}
