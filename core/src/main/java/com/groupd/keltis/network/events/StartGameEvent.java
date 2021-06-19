package com.groupd.keltis.network.events;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StartGameEvent extends NetworkEvent {
    private String json;

    public StartGameEvent(){

    }

    public StartGameEvent(String json) {
        this.json = json;
    }


    @Override
    public int getEventID() {
        return 2;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(json);

    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        json = dataIn.readUTF();
    }

    public String getJson() {
        return json;
    }
}
