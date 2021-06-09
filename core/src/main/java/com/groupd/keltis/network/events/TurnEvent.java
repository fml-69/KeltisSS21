package com.groupd.keltis.network.events;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TurnEvent extends NetworkEvent {

    private String turnJson;

    public TurnEvent(){

    }

    public TurnEvent(String turnJson) {
        this.turnJson = turnJson;
    }



    @Override
    public int getEventID() {
        return 3;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(this.turnJson);
    }


    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        turnJson = dataIn.readUTF();
    }


    public String getTurnJson() {
        return turnJson;
    }
}
