package com.groupd.keltis.server;

import com.groupd.keltis.network.NetworkServer;

import java.util.concurrent.CountDownLatch;

public class ServerRunnable implements Runnable{

    private NetworkServer networkServer;


    public ServerRunnable(int port, CountDownLatch countDownLatch){

        networkServer = new NetworkServer(port, countDownLatch);

    }

    @Override
    public void run() {

    }

}
