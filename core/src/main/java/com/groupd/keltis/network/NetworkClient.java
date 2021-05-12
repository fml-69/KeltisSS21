package com.groupd.keltis.network;

import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.StartGameEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkClient {

    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private boolean connected;
    private String message;



    public NetworkClient(String iP, int port, String nick){
        try {
            Socket client = new Socket(iP, port);

            dataIn = new DataInputStream(client.getInputStream());
            dataOut = new DataOutputStream(client.getOutputStream());

            // upon connecting to server, client sends nick
            dataOut.writeUTF(nick);
            message = dataIn.readUTF();
            connected = message.equals("OK");

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

    public void sendEvent(NetworkEvent event){
        try {
            dataOut.writeInt(event.getEventID());
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.encode(dataOut);
    }


    public void receiveEvents(){
        try {

            if(dataIn.available() > 0){
                int eventID = dataIn.readInt();
                if(eventID == 1){
                    JoinEvent event = new JoinEvent();
                    event.decode(dataIn);

                } else if (eventID == 2){
                    StartGameEvent startEvent = new StartGameEvent();
                    startEvent.decode(dataIn);

                } else {
                    System.out.print("Invalid Network EventID");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
