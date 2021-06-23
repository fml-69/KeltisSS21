package com.groupd.keltis.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.network.events.CardDisplaySyncEvent;
import com.groupd.keltis.network.events.CheatQueryEvent;
import com.groupd.keltis.network.events.CheatScoreEvent;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.MoveBecauseOfShamrockEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.NextPlayerEvent;
import com.groupd.keltis.network.events.RoadcardsRemoveSyncEvent;
import com.groupd.keltis.network.events.RoadcardsSyncEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.network.events.StopGameEvent;
import com.groupd.keltis.network.events.TurnEvent;
import com.groupd.keltis.scenes.board.InfoDialog;
import com.groupd.keltis.scenes.board.YesNoDialog;
import com.groupd.keltis.utils.AssetPaths;

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
    private String nickName;
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

            nickName = nick;

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

                } else if (eventID == 4) {
                    Gdx.app.log("Info","event4" );
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    cheatQueryEvent.decode(dataIn);
                    String message = cheatQueryEvent.getMessage();
                    Gdx.app.log("Info","Client2: " + cheatQueryEvent.getMessage());
                    if(!message.isEmpty()){
                        Gdx.app.log("Info","Client: " + message);
                        /*InfoDialog infoDialog = new InfoDialog("Schummelverdacht",
                                keltis.assetManager.get(AssetPaths.DIALOG_SKIN),message);
                        NetworkClient.INSTANCE.showDialog(infoDialog,keltis.sceneManager.getActiveScene().stage,3);*/
                        keltis.sceneManager.getActiveScene().onNetworkEvent(cheatQueryEvent);
                    }
                } else if(eventID == 3) {
                    Gdx.app.log("NETWORK", "EVENT ID: 3 - TURN EVENT");
                    TurnEvent turnEvent = new TurnEvent();
                    turnEvent.decode(dataIn);
                   keltis.sceneManager.getActiveScene().onNetworkEvent(turnEvent);
                }
                else if(eventID == 42) {
                    Gdx.app.log("NETWORK", "EVENT ID: 42 - CARD SYNC");
                    CardDisplaySyncEvent cardEvent = new CardDisplaySyncEvent();
                    cardEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(cardEvent);

                }
                else if(eventID == 33) {
                    Gdx.app.log("NETWORK", "EVENT ID: 33 - NEXT PLAYER");
                    NextPlayerEvent nextPlayerEvent = new NextPlayerEvent();
                    nextPlayerEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(nextPlayerEvent);

                } else if(eventID == 99){
                    Gdx.app.log("NETWORK", "EVENT ID: 99 - ROADCARD SYNC");
                    RoadcardsSyncEvent roadcardsSyncEvent = new RoadcardsSyncEvent();
                    roadcardsSyncEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(roadcardsSyncEvent);
                }else if(eventID == 55){
                    Gdx.app.log("NETWORK", "EVENT ID: 55 - ROADCARDREMOVE SYNC");
                    RoadcardsRemoveSyncEvent roadcardsRemoveSyncEvent = new RoadcardsRemoveSyncEvent();
                    roadcardsRemoveSyncEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(roadcardsRemoveSyncEvent);
                }else if(eventID == 70){
                    MoveBecauseOfShamrockEvent moveBecauseOfShamrockEvent = new MoveBecauseOfShamrockEvent();
                    moveBecauseOfShamrockEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(moveBecauseOfShamrockEvent);
                }else if(eventID == 9){
                    CheatScoreEvent cheatScoreEvent = new CheatScoreEvent();
                    cheatScoreEvent.decode(dataIn);
                    keltis.gameLogic.cheatScoreUpdate(cheatScoreEvent.getNick(),cheatScoreEvent.getScore());
                } else if(eventID == 69){
                    StopGameEvent stopGameEvent = new StopGameEvent();
                    stopGameEvent.decode(dataIn);
                    keltis.sceneManager.getActiveScene().onNetworkEvent(stopGameEvent);
                    disconnect();
                }else {
                    Gdx.app.error("Error", "Invalid Network EventID");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDialog(Dialog dialog, Stage stage, float scale) {
        dialog.show(stage);
        dialog.setScale(scale);
        dialog.setOrigin(Align.center);
    }

    /**
     * Used to return the current client nick name
     * @return nick name
     */
    public String getNickName(){
        return nickName;
    }

    /**
     * Get current client socket
     * @return client Socket
     */
    public Socket getClient(){
        return client;
    }
}
