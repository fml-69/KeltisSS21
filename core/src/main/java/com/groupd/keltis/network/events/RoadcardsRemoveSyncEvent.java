package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RoadcardsRemoveSyncEvent extends NetworkEvent{
    public String json;

    public RoadcardsRemoveSyncEvent(){

    }

    public RoadcardsRemoveSyncEvent(String json){
        this.json = json;
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

    @Override
    public int getEventID() {
        return 55;
    }

    public String getJson(){
        return json;
    }
}
