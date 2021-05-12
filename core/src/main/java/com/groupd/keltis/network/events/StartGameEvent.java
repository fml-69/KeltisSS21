package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class StartGameEvent extends NetworkEvent {

    @Override
    public int getEventID() {
        return 2;
    }

    @Override
    public void encode(DataOutputStream dataOut) {
        super.encode(dataOut);
    }


    @Override
    public void decode(DataInputStream dataIn) {
        super.decode(dataIn);
    }
}
