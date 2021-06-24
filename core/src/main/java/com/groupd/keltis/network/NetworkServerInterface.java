package com.groupd.keltis.network;

import com.groupd.keltis.network.events.NetworkEvent;

public interface NetworkServerInterface {
    // handle events coming from clients to server
    void receivePackets();

    // send an event to specific client
    void sendEvent(String receiver, NetworkEvent event);

    // send to all clients
    void broadCast(NetworkEvent event);
}
