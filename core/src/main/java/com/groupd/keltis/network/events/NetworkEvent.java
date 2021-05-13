package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class NetworkEvent {


    public abstract int getEventID();

    public void encode(DataOutputStream dataOut){

    }

    public void decode(DataInputStream dataIn){

    }
}
