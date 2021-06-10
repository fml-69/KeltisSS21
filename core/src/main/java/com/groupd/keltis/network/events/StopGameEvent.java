package com.groupd.keltis.network.events;

public class StopGameEvent extends NetworkEvent{
    @Override
    public int getEventID() {
        return 69;
    }
}
