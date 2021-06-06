package com.groupd.keltis.network;

import com.badlogic.gdx.Gdx;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.network.events.TurnEvent;
import com.groupd.keltis.server.ServerRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


public class NetworkServer {

    private ServerSocket socket;
    private CountDownLatch countDownLatch;
    private ServerRunnable server;

    // to store clients
    private final Map<String, NetworkClientChannel> clients = new HashMap<>();


    public NetworkServer(int port, CountDownLatch countDownLatch, ServerRunnable server) {
        try {

            this.countDownLatch = countDownLatch;
            this.server = server;
            socket = new ServerSocket(port);
            Thread acceptorThread = new Thread(new AcceptorRunnable());
            acceptorThread.setDaemon(true);
            acceptorThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private class AcceptorRunnable implements Runnable {
        @Override
        public void run() {

            countDownLatch.countDown();

            do {

                try {
                    Socket client = socket.accept();

                    NetworkClientChannel channel = new NetworkClientChannel(client);
                    String nick = channel.dataIn.readUTF();
                    if (clients.containsKey(nick)) {
                        channel.dataOut.writeUTF("Nick already taken, chose another.");
                        client.close();
                    } else {

                        channel.dataOut.writeUTF("OK");
                        // add new client to list of clients
                        clients.put(nick, channel);

                        server.join(nick);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            } while (true);
        }
    }

    // handle events coming from clients to server
    public void receivePackets() {

        // check if any clients are sending new events
        for (Map.Entry<String, NetworkClientChannel> client : clients.entrySet()) {
            NetworkClientChannel channel = client.getValue();

            try {

                // check eventID's and create corresponding events
                if (channel.dataIn.available() > 0) {
                    int eventID = channel.dataIn.readInt();
                   // might be obsolete
                    if (eventID == 1) {
                        JoinEvent event = new JoinEvent();
                        event.decode(channel.dataIn);

                    } else if (eventID == 2) {
                        StartGameEvent startEvent = new StartGameEvent();
                        startEvent.decode(channel.dataIn);
                        server.onStartGame(startEvent, client.getKey());

                    } else if(eventID == 3){
                        TurnEvent turnEvent = new TurnEvent();
                        turnEvent.decode(channel.dataIn);
                        server.onTurn(turnEvent);

                    } else {
                        Gdx.app.error("Error", "Invalid Network EventID");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // send an event to specific client
    public void sendEvent(String receiver, NetworkEvent event) {

        NetworkClientChannel channel = clients.get(receiver);
        if (channel != null) {
            try {
                channel.dataOut.writeInt(event.getEventID());
                event.encode(channel.dataOut);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    // send to all clients
    public void broadCast(NetworkEvent event) {

        for (Map.Entry<String, NetworkClientChannel> client : clients.entrySet()) {
            NetworkClientChannel channel = client.getValue();

            if (channel != null) {
                try {
                    channel.dataOut.writeInt(event.getEventID());
                    event.encode(channel.dataOut);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}