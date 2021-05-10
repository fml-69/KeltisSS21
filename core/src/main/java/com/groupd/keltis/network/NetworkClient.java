package com.groupd.keltis.network;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkClient {

    private Socket client;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private boolean connected;
    private String message;



    public NetworkClient(String IP, int port, String nick){
        try {
            client = new Socket(IP, port);

            dataIn = new DataInputStream(client.getInputStream());
            dataOut = new DataOutputStream(client.getOutputStream());

            // upon connecting to server, client sends nick
            dataOut.writeUTF(nick);
            message = dataIn.readUTF();
            if(message.equals("OK")){
                connected = true;
            } else {
                connected = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
            message = "Could not connect to server.";
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public String getMessage(){
        return message;
    }
}
