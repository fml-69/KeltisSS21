package com.groupd.keltis.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;



//import sun.rmi.transport.tcp.TCPChannel;

public class NetworkServer {

    private ServerSocket socket;
    private CountDownLatch countDownLatch;
    private Thread acceptorThread;

    // to store clients
    private Map<String, NetworkClientChannel> clients = new HashMap<>();


    public NetworkServer(int port, CountDownLatch countDownLatch){
        try {

            this.countDownLatch = countDownLatch;
            socket = new ServerSocket(port);
            acceptorThread = new Thread(new AcceptorRunnable());
            acceptorThread.setDaemon(true);
            acceptorThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private class AcceptorRunnable implements Runnable{
        @Override
        public void run() {

            countDownLatch.countDown();

            while(true){

                try {
                    Socket client = socket.accept();

                    NetworkClientChannel channel = new NetworkClientChannel(client);
                    String nick = channel.dataIn.readUTF();
                    if(clients.containsKey(nick)){
                        channel.dataOut.writeUTF("Nick already taken, chose another.");
                        client.close();
                    } else {

                        channel.dataOut.writeUTF("OK");
                        // add new client to list of clients
                        clients.put(nick, channel);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
