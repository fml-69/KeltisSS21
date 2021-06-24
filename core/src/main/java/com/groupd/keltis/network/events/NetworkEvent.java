package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class NetworkEvent {


    public abstract int getEventID();

    public void encode(DataOutputStream dataOut) throws IOException {

    }

    public void decode(DataInputStream dataIn) throws IOException {

    }
}
