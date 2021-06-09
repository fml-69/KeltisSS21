package com.groupd.keltis.network.events;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StartGameEvent extends NetworkEvent {

    @Override
    public int getEventID() {
        return 2;
    }

}
