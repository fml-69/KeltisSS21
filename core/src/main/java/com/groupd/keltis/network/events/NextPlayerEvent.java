package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NextPlayerEvent extends NetworkEvent{


    public NextPlayerEvent(){

    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
    }

    @Override
    public int getEventID() {
        return 33;
    }
}