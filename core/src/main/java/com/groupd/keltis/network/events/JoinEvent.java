package com.groupd.keltis.network.events;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JoinEvent extends NetworkEvent{

    public String nick;

    public JoinEvent(){

    }

    public JoinEvent(String nick){
        this.nick = nick;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(nick);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        nick = dataIn.readUTF();
    }

    @Override
    public int getEventID() {
        return 1;
    }



}
