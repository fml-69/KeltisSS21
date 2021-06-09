package com.groupd.keltis.network;

import com.badlogic.gdx.Gdx;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.network.events.TurnEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




public class NetworkClient {

    private Socket client;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private boolean connected;
    private String message;
    private Keltis keltis;

    public static final NetworkClient INSTANCE = new NetworkClient();


    private NetworkClient(){

    }

    public void connect(Keltis keltis, String iP, int port, String nick){

        this.keltis = keltis;

        try {
             client = new Socket(iP, port);

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

    public void disconnect() throws IOException {
        client.close();
    }

    public boolean isConnected() {
        return connected;
    }

    public String getMessage(){
        return message;
    }


    // send event to Server through DataOutputStream
    public void sendEvent(NetworkEvent event){
        try {
            dataOut.writeInt(event.getEventID());
            event.encode(dataOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // handle events coming from server to client
    public void receiveEvents(){
        try {

            if(dataIn.available() > 0){
                int eventID = dataIn.readInt();
                if(eventID == 1){
                    JoinEvent event = new JoinEvent();
                    event.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(event);

                } else if (eventID == 2){
                    StartGameEvent startEvent = new StartGameEvent();
                    startEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(startEvent);

                } else if(eventID == 3) {
                    TurnEvent turnEvent = new TurnEvent();
                    turnEvent.decode(dataIn);
                   keltis.sceneManager.getActiveScene().onNetworkEvent(turnEvent);

                } else if(eventID == 4){

                }else {
                    Gdx.app.error("Error", "Invalid Network EventID");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
