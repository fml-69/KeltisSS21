package com.groupd.keltis.server;

import com.groupd.keltis.network.NetworkServer;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.*;

public class ServerRunnable implements Runnable{

    private final NetworkServer networkServer;


    public ServerRunnable(int port, CountDownLatch countDownLatch){

        networkServer = new NetworkServer(port, countDownLatch);

    }

    @Override
    public void run() {
        while(true){
            networkServer.receivePackets();
            try {
                sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
