package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class JoinEvent extends NetworkEvent{

    @Override
    public int getEventID() {
        return 1;
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
